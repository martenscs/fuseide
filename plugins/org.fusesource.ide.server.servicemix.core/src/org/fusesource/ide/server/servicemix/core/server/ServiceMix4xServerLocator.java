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

package org.fusesource.ide.server.servicemix.core.server;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.IServerType;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.internal.provisional.ServerLocatorDelegate;
import org.fusesource.ide.server.servicemix.core.runtime.ServiceMix4xRuntimeLocator;


/**
 * @author lhein
 */
public class ServiceMix4xServerLocator extends ServerLocatorDelegate {

	/**
	 * empty default constructor
	 */
	public ServiceMix4xServerLocator() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.core.internal.provisional.ServerLocatorDelegate#searchForServers(java.lang.String, org.eclipse.wst.server.core.internal.provisional.ServerLocatorDelegate.IServerSearchListener, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void searchForServers(String host, final IServerSearchListener listener,
			final IProgressMonitor monitor) {
		ServiceMix4xRuntimeLocator.IRuntimeSearchListener listener2 = new ServiceMix4xRuntimeLocator.IRuntimeSearchListener() {
			/*
			 * (non-Javadoc)
			 * @see org.eclipse.wst.server.core.model.RuntimeLocatorDelegate.IRuntimeSearchListener#runtimeFound(org.eclipse.wst.server.core.IRuntimeWorkingCopy)
			 */
			public void runtimeFound(IRuntimeWorkingCopy runtime) {
				String runtimeTypeId = runtime.getRuntimeType().getId();
				String serverTypeId = runtimeTypeId.substring(0, runtimeTypeId.length() - 8);
				IServerType serverType = ServerCore.findServerType(serverTypeId);
				try {
					IServerWorkingCopy server = serverType.createServer(serverTypeId, null, runtime, monitor);
					listener.serverFound(server);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		new ServiceMix4xRuntimeLocator().search(null, listener2, monitor);
	}
}