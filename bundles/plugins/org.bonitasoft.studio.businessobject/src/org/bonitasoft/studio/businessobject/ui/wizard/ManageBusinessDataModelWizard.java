/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.Index;
import org.bonitasoft.engine.bdm.model.UniqueConstraint;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.validator.BusinessObjectModelValidator;
import org.bonitasoft.engine.bdm.validator.ValidationStatus;
import org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMStackTraceResolver;
import org.bonitasoft.studio.businessobject.core.operation.GenerateBDMOperation;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.dialog.ProblemsDialog;
import org.bonitasoft.studio.ui.provider.TypedLabelProvider;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class ManageBusinessDataModelWizard extends Wizard {

    private BusinessDataModelWizardPage page;

    private final BusinessObjectModelFileStore fStore;

    private BusinessObjectModel businessObjectModel;

    private boolean newBdm = false;

    private IDiffLogger diffLogger;

    public ManageBusinessDataModelWizard(final BusinessObjectModelFileStore fStore, IDiffLogger diffLogger) {
        this.fStore = fStore;
        businessObjectModel = fStore.getContent();
        if (businessObjectModel == null) {
            newBdm = true;
            businessObjectModel = new BusinessObjectModel();
        }
        this.diffLogger = diffLogger;
        setDefaultPageImageDescriptor(Pics.getWizban());
        setWindowTitle(Messages.manageBusinessDataModelTitle);
        setNeedsProgressMonitor(true);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        page = new BusinessDataModelWizardPage(businessObjectModel, diffLogger);
        page.setTitle(Messages.manageBusinessDataModelTitle);
        page.setDescription(Messages.manageBusinessDataModelDesc);
        addPage(page);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        if (businessObjectModel != null) {
            boolean confirm = true;
            if (!newBdm) {
                final IPreferenceStore preferenceStore = getPreferenceStore();
                final MessageDialogWithToggle confirmDialog = MessageDialogWithPrompt.openOkCancelConfirm(getShell(),
                        Messages.bdmCompatibilityTitle,
                        Messages.bdmCompatibilityMsg,
                        Messages.clearExistingBusinessData,
                        preferenceStore.getBoolean(EnginePreferenceConstants.DROP_BUSINESS_DATA_DB_ON_INSTALL),
                        preferenceStore,
                        EnginePreferenceConstants.DROP_BUSINESS_DATA_DB_ON_INSTALL);
                confirm = confirmDialog.getReturnCode() == Dialog.OK;
            }
            if (confirm) {
                final boolean isValid = validateAndSaveBDM();
                if (isValid) {
                    return installBDM();
                }
            }
        }
        return false;
    }

    private boolean validateAndSaveBDM() {
        try {
            getContainer().run(true, false, new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    if (businessObjectModel.getBusinessObjects().isEmpty()) {
                        fStore.delete();
                    } else {
                        monitor.beginTask(Messages.validatingBDM, IProgressMonitor.UNKNOWN);
                        final ValidationStatus validate = new BusinessObjectModelValidator().validate(businessObjectModel);
                        if (!validate.getErrors().isEmpty()) {
                            Display.getDefault().asyncExec(new Runnable() {

                                @Override
                                public void run() {
                                    new ProblemsDialog<String>(getShell(), Messages.modelValidationFailedTitle,
                                            Messages.modelValidationFailedMsg, MessageDialog.ERROR,
                                            new String[] { IDialogConstants.OK_LABEL }) {

                                        @Override
                                        protected TypedLabelProvider<String> getTypedLabelProvider() {
                                            return new TypedLabelProvider<String>() {

                                                @Override
                                                public String getText(String element) {
                                                    return element;
                                                }

                                                @Override
                                                public Image getImage(String element) {
                                                    return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
                                                }

                                            };
                                        }

                                        @Override
                                        protected Collection<String> getInput() {
                                            return validate.getErrors();
                                        }
                                    }.open();
                                }
                            });
                            throw new InterruptedException();
                        } else {
                            save(monitor);
                        }
                        monitor.done();
                    }
                }

            });
        } catch (final InvocationTargetException e) {
            MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.modelValidationFailedTitle,
                    e.getCause().getMessage());
            BonitaStudioLog.error(e);
            return false;
        } catch (final InterruptedException e) {
            return false;
        }
        return true;
    }

    protected void save(final IProgressMonitor monitor) {
        monitor.setTaskName(Messages.saving);
        fStore.save(businessObjectModel);
    }

    protected boolean installBDM() {
        try {
            getContainer().run(true, false, new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    new GenerateBDMOperation(fStore).run(monitor);
                    new DeployBDMOperation(fStore).run(monitor);
                }
            });
        } catch (final InvocationTargetException e) {
            new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.installFailedTitle,
                    Messages.installFailedMessage,
                    new DeployBDMStackTraceResolver().reduceHibernateException(e))
                            .open();
            return false;
        } catch (final InterruptedException e) {
            return false;
        }
        return true;
    }

    protected IDiffLogger getDiffLogger() {
        return diffLogger;
    }

    protected IPreferenceStore getPreferenceStore() {
        return EnginePlugin.getDefault().getPreferenceStore();
    }

    protected void validateUniqueConstraint(final UniqueConstraint uc, final BusinessObject bo)
            throws InvocationTargetException {
        if (uc.getFieldNames() == null || uc.getFieldNames().isEmpty()) {
            throw new InvocationTargetException(
                    new Exception(Messages.bind(Messages.atLeastOneAttributeShouldBelongToConstraint, uc.getName())));
        }
        for (final String fName : uc.getFieldNames()) {
            boolean exists = false;
            for (final Field f : bo.getFields()) {
                if (f.getName().equals(fName)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                throw new InvocationTargetException(
                        new Exception(Messages.bind(Messages.attributeInConstraintNotExists, fName, uc.getName())));
            }
        }
    }

    protected void validateIndex(final Index index, final BusinessObject bo) throws InvocationTargetException {
        if (index.getFieldNames() == null || index.getFieldNames().isEmpty()) {
            throw new InvocationTargetException(
                    new Exception(Messages.bind(Messages.atLeastOneAttributeShouldBelongToIndex, index.getName())));
        }
        for (final String fName : index.getFieldNames()) {
            boolean exists = false;
            for (final Field f : bo.getFields()) {
                if (f.getName().equals(fName)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                throw new InvocationTargetException(
                        new Exception(Messages.bind(Messages.attributeInIndexNotExists, fName, index.getName())));
            }
        }
    }

}
