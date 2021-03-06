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

package org.fusesource.ide.fabric.servicemix.navigator;


import org.apache.servicemix.nmr.management.ManagedEndpointMBean;
import org.eclipse.swt.graphics.Image;
import org.fusesource.ide.commons.tree.NodeSupport;
import org.fusesource.ide.commons.ui.ImageProvider;
import org.fusesource.ide.fabric.FabricPlugin;


public class EndpointNode extends NodeSupport implements ImageProvider {
	private final ManagedEndpointMBean mbean;

	public EndpointNode(EndpointsNode endpointsNode, ManagedEndpointMBean mbean) {
		super(endpointsNode);
		this.mbean = mbean;
		setPropertyBean(mbean);
	}

	@Override
	public String toString() {
		return mbean.getName();
	}

	@Override
	public Image getImage() {
		return FabricPlugin.getDefault().getImage("endpoint_node.png");
	}

}
