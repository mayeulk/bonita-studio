/**
 * Copyright (C) 2011-2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.PageFlowTransition;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Romain Bioteau
 *
 */
public class PropertySelectionProvider {


	private static PropertySelectionProvider INSTANCE ;


	private PropertySelectionProvider(){

	}

	public static PropertySelectionProvider getInstance(){
		if(INSTANCE == null){
			INSTANCE = new PropertySelectionProvider();
		}
		return INSTANCE ;
	}

	public void fireSelectionChanged(IGraphicalEditPart ep ,EObject element){
		TabbedPropertySheetPage page;
		try {
			if(element == null){
				element = ep.resolveSemanticElement();
			}
			page = getTabbedPropertySheetPage(element);
			if(page != null){
				page.selectionChanged(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor(), new StructuredSelection(ep)) ;
			}
		} catch (PartInitException e) {
			BonitaStudioLog.error(e) ;
		}
	}


	private TabbedPropertySheetPage getTabbedPropertySheetPage(EObject element) throws PartInitException {
		IViewPart viewPart = null ;
		for(IViewReference vr : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences() ){
			if(displayApplicationTab(element)){
				if(vr.getId().equals("org.bonitasoft.studio.views.properties.application")){
					viewPart = vr.getView(true) ;
				}
			}else{
				if(vr.getId().equals("org.bonitasoft.studio.views.properties.process.general")){
					viewPart = vr.getView(true) ;
				}
			}
			if(vr.getId().equals("org.bonitasoft.studio.views.properties.form.general")){
				viewPart = vr.getView(true) ;
			}
		}
		if(viewPart != null){
			viewPart.getViewSite().getWorkbenchWindow().getActivePage().showView(viewPart.getSite().getId()) ;
			return (TabbedPropertySheetPage) viewPart.getAdapter(TabbedPropertySheetPage.class);
		}
		return null;
	}

	private boolean displayApplicationTab(EObject element) {
		return element.eClass().getEPackage().getName().equals(FormPackage.eINSTANCE.getName())
				|| (element instanceof Data && isTransientData(element)) || element instanceof PageFlowTransition || element instanceof AssociatedFile || element instanceof ResourceFolder || element instanceof ResourceFile;
	}

	private boolean isTransientData(EObject element) {
		return element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getRecapFlow_RecapTransientData()) ||
				element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getViewPageFlow_ViewTransientData()) ||
				element.eContainingFeature().equals(ProcessPackage.eINSTANCE.getPageFlow_TransientData()) ;
	}
}
