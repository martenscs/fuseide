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

package org.fusesource.ide.deployment.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.PreferencesUtil;

/**
 * @author lhein
 */
public class ConfigureDeploymentsHandler extends AbstractHandler {
	
	private static final String DEPLOYMENT_PREFERENCES_PAGE_ID = "org.fusesource.ide.preferences.deployment.page";
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		openPreferencesDialog();
		return null;
	}

	public static void openPreferencesDialog() {
		PreferenceDialog createPreferenceDialogOn = PreferencesUtil.createPreferenceDialogOn(
                Display.getCurrent().getActiveShell(), DEPLOYMENT_PREFERENCES_PAGE_ID, null, null);
		createPreferenceDialogOn.open();
	}
}
