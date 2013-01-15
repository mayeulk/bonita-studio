/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.migration.ui.view;

import java.io.IOException;

import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.migration.MigrationPlugin;
import org.bonitasoft.studio.migration.i18n.Messages;
import org.bonitasoft.studio.migration.model.report.Change;
import org.bonitasoft.studio.migration.model.report.MigrationReportPackage;
import org.bonitasoft.studio.migration.model.report.Report;
import org.bonitasoft.studio.migration.ui.action.ExportMigrationReportAsPDFAction;
import org.bonitasoft.studio.migration.ui.action.HideReviewedAction;
import org.bonitasoft.studio.migration.ui.action.HideValidStatusAction;
import org.bonitasoft.studio.migration.ui.action.ToggleLinkingAction;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;

/**
 * @author Aurelien Pupier
 * @author Romain Bioteau
 */
public class MigrationStatusView extends ViewPart implements ISelectionListener,ISelectionChangedListener {

	public static String ID = "org.bonitasoft.studio.migration.view";
	private TableViewer tableViewer;
	private ISelectionProvider selectionProvider;
	private ExportMigrationReportAsPDFAction exportAction;
	private String searchQuery;
	private ToggleLinkingAction linkAction;
	private Text descripitonText;

	@Override
	public void createPartControl(Composite parent) {
		Composite mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(0, 0, 0, 5).create());

		createTopComposite(mainComposite);
		createTableComposite(mainComposite);
		createBottomComposite(mainComposite);

		ISelectionService ss = getSite().getWorkbenchWindow().getSelectionService();
		ss.addPostSelectionListener(this);
		if(getSite().getPage().getActiveEditor() != null){
			selectionProvider =  getSite().getPage().getActiveEditor().getEditorSite().getSelectionProvider();
			getSite().setSelectionProvider(selectionProvider);
		}
		createActions();

	}

	protected void createActions() {
		IActionBars actionBars = getViewSite().getActionBars();
		IMenuManager dropDownMenu = actionBars.getMenuManager();
		IToolBarManager toolBar = actionBars.getToolBarManager();
		exportAction = new ExportMigrationReportAsPDFAction();
		exportAction.setReport(getReportFromEditor(getSite().getPage().getActiveEditor()));
		dropDownMenu.add(exportAction);

		linkAction = new ToggleLinkingAction();
		linkAction.setViewer(tableViewer);
		linkAction.setEditor((DiagramEditor) getSite().getPage().getActiveEditor());
		toolBar.add(linkAction);

		final HideValidStatusAction hideStatusAction = new HideValidStatusAction();
		hideStatusAction.setViewer(tableViewer);
		dropDownMenu.add(hideStatusAction);

		final HideReviewedAction hideReviewedAction = new HideReviewedAction();
		hideReviewedAction.setViewer(tableViewer);
		dropDownMenu.add(hideReviewedAction);
	}




	protected void createBottomComposite(Composite mainComposite) {
		final Composite bottomComposite = new Composite(mainComposite, SWT.NONE);
		bottomComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(0, 10, 0, 0).create());
		bottomComposite.setLayoutData(GridDataFactory.fillDefaults().create());

		createMarkAsCompletedButton(bottomComposite);

	}

	protected void createMarkAsCompletedButton(Composite bottomComposite) {
		Button markAsCompletedButton = new Button(bottomComposite, SWT.NONE);
		markAsCompletedButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).grab(true, false).create());
		markAsCompletedButton.setText(Messages.completeImport);
		markAsCompletedButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);				
				MessageDialogWithToggle mdwt = MessageDialogWithToggle.openYesNoQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Complete import", "Completing the import will remove the import status report from your repository.\n Do you want to continue?", "Export the import status report", true, MigrationPlugin.getDefault().getPreferenceStore(), "toggleStateForImportExportStatus");
				if(IDialogConstants.YES_ID == mdwt.getReturnCode()){
					if(mdwt.getToggleState()){
						exportAction.run();
					}
					try {
						clearMigrationReport(true);
					} catch (IOException e1) {
						BonitaStudioLog.error(e1,MigrationPlugin.PLUGIN_ID);
					}
					final String id = BonitaPerspectivesUtils.getPerspectiveId((IEditorPart) tableViewer.getInput());
					if (id != null) {
						BonitaPerspectivesUtils.switchToPerspective(id);
					}
				}

			}
		});
	}

	private void clearMigrationReport(boolean save) throws IOException{
		final IEditorPart editorPart = (IEditorPart) tableViewer.getInput();
		if(editorPart != null && editorPart instanceof DiagramEditor){
			final Resource emfResource = ((DiagramEditor)editorPart).getDiagramEditPart().getNotationView().eResource();
			final Report report = getMigrationReport(emfResource); 
			if(report != null){
				final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(emfResource);
				if(domain != null){
					domain.getCommandStack().execute(new RecordingCommand(domain) {
						protected void doExecute() {
							emfResource.getContents().remove(report);
						}
					});
					if(save){
						ICommandService service = (ICommandService) getSite().getService(ICommandService.class);
						Command cmd = service.getCommand("org.eclipse.ui.file.save");
						try {
							cmd.executeWithChecks(new ExecutionEvent());
						} catch (Exception e) {
							BonitaStudioLog.error(e,MigrationPlugin.PLUGIN_ID);
						} 
					}
				}
			}
		}
	}

	private Report getMigrationReport(Resource resource) {
		for(EObject r : resource.getContents()){
			if(r instanceof Report){
				return (Report) r;
			}
		}
		return null;
	}

	protected void createTableComposite(Composite mainComposite) {
		Composite tableComposite = new Composite(mainComposite, SWT.NONE);
		tableComposite.setLayout(GridLayoutFactory.fillDefaults().create());
		tableComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());


		tableViewer = new TableViewer(tableComposite,SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION) ;
		tableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(400, SWT.DEFAULT).create());
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.getTable().setLinesVisible(true);
		tableViewer.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return viewerSelect(element,searchQuery) ;
			}
		}) ;

		addElementTypeColumn();
		addElementNameColumn();
		addPropertyColumn();
		addStatusColumn();
		addReviewedColumn();

		TableLayout layout = new TableLayout();
		layout.addColumnData(new ColumnWeightData(25));
		layout.addColumnData(new ColumnWeightData(25));
		layout.addColumnData(new ColumnWeightData(25));
		layout.addColumnData(new ColumnWeightData(10));
		layout.addColumnData(new ColumnWeightData(15));

		tableViewer.getTable().setLayout(layout);

		tableViewer.setContentProvider(new ReportContentProvider());
		IEditorPart activeEditor = getSite().getPage().getActiveEditor();
		tableViewer.setInput(activeEditor);
		tableViewer.addSelectionChangedListener(this);
		tableViewer.getTable().addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				event.height = 25;
			}
		});

		final Label descriptionLabel = new Label(tableComposite, SWT.NONE);
		descriptionLabel.setText(Messages.description);
		descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

		descripitonText = new Text(tableComposite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.READ_ONLY);
		descripitonText.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		descripitonText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 100).create());

	}

	protected void addElementNameColumn() {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
		column.getColumn().setText(Messages.name);
		column.getColumn().setAlignment(SWT.CENTER);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getElementName();
				}
				return Messages.unknown;
			}
			@Override
			public String getToolTipText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getElementName();
				}

				return null ;
			}

			@Override
			public int getToolTipTimeDisplayed(Object object) {
				return 4000 ;
			}

			@Override
			public int getToolTipDisplayDelayTime(Object object) {
				return 50;
			}

			@Override
			public Point getToolTipShift(Object object) {
				return new Point(5,5);
			}
		});
	}

	protected void addElementTypeColumn() {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.CENTER);
		column.getColumn().setText(Messages.elementType);
		column.getColumn().setAlignment(SWT.CENTER);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getElementType();
				}
				return Messages.unknown;
			}
			@Override
			public String getToolTipText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getElementType();
				}

				return null ;
			}

			@Override
			public int getToolTipTimeDisplayed(Object object) {
				return 4000 ;
			}

			@Override
			public int getToolTipDisplayDelayTime(Object object) {
				return 50;
			}

			@Override
			public Point getToolTipShift(Object object) {
				return new Point(5,5);
			}
		});
	}

	protected void addPropertyColumn() {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
		column.getColumn().setText(Messages.property);
		column.getColumn().setAlignment(SWT.CENTER);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getPropertyName();
				}
				return Messages.unknown;
			}
			@Override
			public String getToolTipText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getPropertyName();
				}

				return null ;
			}

			@Override
			public int getToolTipTimeDisplayed(Object object) {
				return 4000 ;
			}

			@Override
			public int getToolTipDisplayDelayTime(Object object) {
				return 50;
			}

			@Override
			public Point getToolTipShift(Object object) {
				return new Point(5,5);
			}
		});
	}


	protected void addStatusColumn() {
		final TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
		column.getColumn().setText(Messages.status);
		column.getColumn().setAlignment(SWT.CENTER);

		column.setLabelProvider(new StatusColumnLabelProvider());

	}

	protected void addReviewedColumn() {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
		column.getColumn().setText(Messages.reviewed);
		column.getColumn().setAlignment(SWT.CENTER);
		column.setLabelProvider(new CheckboxLabelProvider(tableViewer.getControl()));

		column.setEditingSupport(new EditingSupport(tableViewer) {

			@Override
			protected void setValue(Object element, Object value) {
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(element);
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, MigrationReportPackage.Literals.CHANGE__REVIEWED, value));
				}

				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						tableViewer.refresh();		
					}
				}) ;
			}

			@Override
			protected Object getValue(Object element) {
				return ((Change)element).isReviewed();
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				return new CheckboxCellEditor(tableViewer.getTable(), SWT.CHECK);
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}
		});

		TableColumnSorter sorter = new TableColumnSorter(tableViewer) ;
		sorter.setColumn(column.getColumn()) ;
	}

	protected void createTopComposite(Composite mainComposite) {
		final Composite topComposite = new Composite(mainComposite, SWT.NONE);
		topComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(5, 5, 5, 0).create());
		topComposite.setLayoutData(GridDataFactory.fillDefaults().create());

		createFilterComposite(topComposite);

	}

	protected void createFilterComposite(Composite topComposite) {
		final Text findText = new Text(topComposite, SWT.BORDER | SWT.SEARCH | SWT.ICON_CANCEL | SWT.ICON_SEARCH);
		findText.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).align(SWT.RIGHT, SWT.CENTER).hint(150, SWT.DEFAULT).create());
		findText.setMessage(Messages.find);
		findText.addModifyListener(new ModifyListener() {



			@Override
			public void modifyText(ModifyEvent e) {
				searchQuery = findText.getText() ;
				tableViewer.refresh() ;

			}
		});
	}

	protected boolean viewerSelect(Object element, String searchQuery) {
		if(searchQuery == null || searchQuery.isEmpty()
				|| (((Change)element).getElementType() != null && ((Change)element).getElementType().toLowerCase().contains(searchQuery.toLowerCase()))
				|| (((Change)element).getElementName() != null && ((Change)element).getElementName().toLowerCase().contains(searchQuery.toLowerCase()))
				|| (((Change)element).getPropertyName() != null && ((Change)element).getPropertyName().toLowerCase().contains(searchQuery.toLowerCase()))){
			return true ;
		}
		return false ;
	}

	@Override
	public void setFocus() {}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if(selection instanceof StructuredSelection && !tableViewer.getTable().isDisposed()){
			Object selectedEP = ((StructuredSelection) selection).getFirstElement();
			if(selectedEP instanceof IGraphicalEditPart){
				IEditorPart editorPart = getSite().getPage().getActiveEditor();
				if(editorPart != null && !editorPart.equals(tableViewer.getInput())){
					selectionProvider = editorPart.getEditorSite().getSelectionProvider();
					getSite().setSelectionProvider(selectionProvider); 
					tableViewer.setInput(editorPart);
					exportAction.setReport(getReportFromEditor(editorPart));
					linkAction.setEditor((DiagramEditor) editorPart);
				}else if(editorPart != null && editorPart.equals(tableViewer.getInput())){
					tableViewer.refresh();
				}
				tableViewer.getTable().layout(true,true);
			}
		}
	}

	private Report getReportFromEditor(IEditorPart editorPart) {
		if(editorPart instanceof DiagramEditor){
			final Resource resource = ((DiagramEditor) editorPart).getDiagramEditPart().getNotationView().eResource();
			for(EObject r : resource.getContents()){
				if(r instanceof Report){
					return (Report) r;
				}
			}
		}
		return null;
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == IPropertySheetPage.class){
			return getSite().getPage().getActiveEditor().getAdapter(adapter);
		}else if(adapter == IEditingDomainProvider.class){
			return new IEditingDomainProvider() {
				@Override
				public EditingDomain getEditingDomain() {
					IEditorPart part = getSite().getPage().getActiveEditor();
					if(part instanceof DiagramEditor){
						return ((DiagramEditor) part).getEditingDomain();
					}
					return null;
				}
			};
		}
		return super.getAdapter(adapter);
	}


	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if(!event.getSelection().isEmpty()){
			descripitonText.setText(((Change) ((IStructuredSelection) event.getSelection()).getFirstElement()).getDescription());
		}


	}



}
