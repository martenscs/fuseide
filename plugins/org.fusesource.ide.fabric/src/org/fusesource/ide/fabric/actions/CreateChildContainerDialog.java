/*******************************************************************************
 * Copyright (c) 2010 JVM Monitor project. All rights reserved.
 * 
 * This code is distributed under the terms of the Eclipse Public License v1.0
 * which is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/*******************************************************************************
 * Copyright (c) 2013 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/

package org.fusesource.ide.fabric.actions;


import org.eclipse.swt.widgets.Shell;
import org.fusesource.ide.commons.ui.form.FormDialogSupport;
import org.fusesource.ide.fabric.navigator.ContainerNode;


/**
 * The dialog for creating child agents
 */
public class CreateChildContainerDialog extends FormDialogSupport {
	private final CreateChildContainerForm childContainerForm;

	public CreateChildContainerDialog(ContainerNode agentNode, String defaultAgentName) {
		childContainerForm = new CreateChildContainerForm(this, agentNode, defaultAgentName);
		setForm(childContainerForm);
	}

	public CreateChildContainerForm getChildContainerForm() {
		return childContainerForm;
	}


	/*
	 * @see Window#configureShell(Shell)
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.createChildAgentTitle);
	}

}