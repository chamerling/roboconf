/**
 * Copyright 2014 Linagora, Université Joseph Fourier
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.roboconf.messaging.messages.from_agent_to_dm;

import java.util.Collection;
import java.util.Map;

import net.roboconf.core.model.helpers.InstanceHelpers;
import net.roboconf.core.model.runtime.Import;
import net.roboconf.core.model.runtime.Instance;
import net.roboconf.core.model.runtime.Instance.InstanceStatus;
import net.roboconf.messaging.messages.Message;

/**
 * @author Noël - LIG
 */
public class MsgNotifModelOnNodeChanged extends Message {

	private static final long serialVersionUID = -5023778542512797206L;

	private final String instancePath;
	private Map<String,Collection<Import>> newImports;
	private InstanceStatus newStatus;


	/**
	 * Constructor.
	 * @param componentInstance
	 */
	public MsgNotifModelOnNodeChanged( Instance instance ) {
		super();
		this.instancePath = InstanceHelpers.computeInstancePath( instance );
		this.newImports = instance.getImports();
		this.newStatus = instance.getStatus();
	}

	/**
	 * @return the instancePath
	 */
	public String getInstancePath() {
		return this.instancePath;
	}

	/**
	 * @return the newImports
	 */
	public Map<String, Collection<Import>> getNewImports() {
		return this.newImports;
	}

	/**
	 * @param newImports the newImports to set
	 */
	public void setNewImports( Map<String, Collection<Import>> newImports ) {
		this.newImports = newImports;
	}

	/**
	 * @return the newStatus
	 */
	public InstanceStatus getNewStatus() {
		return this.newStatus;
	}

	/**
	 * @param newStatus the newStatus to set
	 */
	public void setNewStatus( InstanceStatus newStatus ) {
		this.newStatus = newStatus;
	}
}
