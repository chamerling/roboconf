/**
 * Copyright 2013-2014 Linagora, Université Joseph Fourier
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

package net.roboconf.messaging.messages.from_agent_to_agent;

import net.roboconf.messaging.messages.Message;

/**
 * A message to indicate we need an import.
 * @author Noël - LIG
 */
public class MsgCmdImportRequest extends Message {

	private static final long serialVersionUID = 5366599037551758208L;
	private final String componentOrFacetName;


	/**
	 * Constructor.
	 * @param variableName
	 * @param subChannelName
	 */
	public MsgCmdImportRequest( String componentOrFacetName ) {
		super();
		this.componentOrFacetName = componentOrFacetName;
	}

	/**
	 * @return the component or facet name
	 */
	public String getComponentOrFacetName() {
		return this.componentOrFacetName;
	}
}
