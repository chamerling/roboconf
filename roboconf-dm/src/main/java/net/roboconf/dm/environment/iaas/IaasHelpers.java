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

package net.roboconf.dm.environment.iaas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.roboconf.core.internal.utils.Utils;
import net.roboconf.core.model.runtime.Instance;
import net.roboconf.dm.utils.ResourceUtils;
import net.roboconf.iaas.api.IaasInterface;

/**
 * Utilities related to Iaas.
 * @author Vincent Zurczak - Linagora
 */
public final class IaasHelpers {

	/**
	 * Empty private constructor.
	 */
	private IaasHelpers() {
		// nothing
	}


	/**
	 * Loads the IaaS properties.
	 * @param applicationFilesDirectory the directory where application resources are stored
	 * @param rootInstance the root instance to find the IaaS properties
	 * @return a non-null properties
	 * @throws IOException if the IaaS properties file was not found
	 */
	public static Map<String, String> loadIaasProperties( File applicationFilesDirectory, Instance rootInstance ) throws IOException {

		if( rootInstance.getParent() != null )
			throw new IllegalArgumentException( "A root instance was expected as parameter." );

		File f = ResourceUtils.findInstanceResourcesDirectory( applicationFilesDirectory, rootInstance );
		f = new File( f, IaasInterface.DEFAULT_IAAS_PROPERTIES_FILE_NAME );

		Map<String, String> result = new HashMap<String, String>();
		InputStream in = null;
		try {
			Properties p = new Properties();
			in = new FileInputStream(f);
			p.load(in);
			for(Object name : p.keySet()) {
				result.put(name.toString(), p.get(name).toString());
			}
		} finally {
			Utils.closeQuietly( in );
		}

		return result;
	}
}
