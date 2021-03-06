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

package org.fusesource.ide.fabric.navigator;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.fusesource.ide.commons.tree.GraphableNode;
import org.fusesource.ide.commons.tree.Node;
import org.fusesource.ide.commons.tree.RefreshableCollectionNode;
import org.fusesource.ide.jmx.ui.internal.views.navigator.ContextMenuProvider;

public abstract class FabricNodeSupport extends RefreshableCollectionNode
		implements GraphableNode, ContextMenuProvider {

	private final Fabric fabric;

	public FabricNodeSupport(Node parent, Fabric fabric) {
		super(parent);
		this.fabric = fabric;
	}

	public Fabric getFabric() {
		return fabric;
	}

	@Override
	public List<Node> getChildrenGraph() {
		return getFabric().getChildrenGraph();
	}

	@Override
	public void provideContextMenu(IMenuManager menu) {
	}
}