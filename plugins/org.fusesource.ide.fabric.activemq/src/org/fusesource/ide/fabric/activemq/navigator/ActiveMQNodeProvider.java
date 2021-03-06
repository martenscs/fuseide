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

package org.fusesource.ide.fabric.activemq.navigator;

import java.util.List;

import javax.management.MBeanServerConnection;


import org.eclipse.core.runtime.CoreException;
import io.fabric8.activemq.facade.BrokerFacade;
import io.fabric8.activemq.facade.JmxTemplateBrokerFacade;
import io.fabric8.activemq.facade.RemoteBrokerFacade;
import org.fusesource.ide.fabric.activemq.FabricActiveMQPlugin;
import org.fusesource.ide.fabric.navigator.ContainerNode;
import org.fusesource.ide.fabric.navigator.NodeProvider;
import org.fusesource.ide.jmx.core.IJMXRunnable;
import org.fusesource.ide.jmx.core.JMXException;
import org.fusesource.ide.jmx.core.tree.Root;


public class ActiveMQNodeProvider implements NodeProvider, org.fusesource.ide.jmx.core.tree.NodeProvider {

	public void provide(ContainerNode agentNode) {
		BrokerFacade facade = new JmxTemplateBrokerFacade(agentNode.getJmxTemplate());
		String brokerName = null;
		try {
			brokerName = facade.getBrokerName();
		} catch (Exception e) {
			FabricActiveMQPlugin.getLogger().warning("Could not find Broker name: " + e, e);
		}
		if (brokerName == null) {
			brokerName = "Broker";
		}
		agentNode.addChild(new BrokerNode(agentNode, facade, brokerName));
	}

	@Override
	public void provide(final Root root) {
		if (root.containsDomain("org.apache.activemq")) {
			
			try {
				// TODO replace with better JmxTemplate reusing the Connection!!!
				root.getConnection().run(new IJMXRunnable() {
					
					@Override
					public void run(MBeanServerConnection connection) throws JMXException {
						// TODO REPLACE WITH BETTER JmxTemplateImpl...
						BrokerFacade facade = new RemoteBrokerFacade(connection);
						BrokerNode broker = new BrokerNode(root, facade, "Broker");
						root.addChild(broker);
					}
				});
			} catch (CoreException e) {
				FabricActiveMQPlugin.getLogger().warning("Failed to connect to JMX: " + e, e);
			}
		}
		
	}

	@Override
	public void provideRootNodes(List<org.fusesource.ide.jmx.core.tree.NodeProvider> list) {
	}
	
}
