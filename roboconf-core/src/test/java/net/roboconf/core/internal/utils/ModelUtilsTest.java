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

package net.roboconf.core.internal.utils;

import java.net.URI;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import net.roboconf.core.Constants;
import net.roboconf.core.model.parsing.AbstractBlockHolder;
import net.roboconf.core.model.parsing.BlockFacet;
import net.roboconf.core.model.parsing.BlockProperty;
import net.roboconf.core.model.parsing.FileDefinition;

import org.junit.Test;

/**
 * @author Vincent Zurczak - Linagora
 */
public class ModelUtilsTest {

	@Test
	public void testGetPropertyValue() {

		FileDefinition def = new FileDefinition((URI) null);
		AbstractBlockHolder holder = new BlockFacet( def );
		holder.getInnerBlocks().add( new BlockProperty( def, "name", "value" ));
		holder.getInnerBlocks().add( new BlockProperty( def, "address", null ));
		holder.getInnerBlocks().add( new BlockProperty( def, "age", "30" ));
		holder.getInnerBlocks().add( new BlockProperty( def, "alias", "" ));

		Assert.assertEquals( "value", ModelUtils.getPropertyValue( holder, "name" ));
		Assert.assertEquals( "30", ModelUtils.getPropertyValue( holder, "age" ));
		Assert.assertEquals( "", ModelUtils.getPropertyValue( holder, "alias" ));
		Assert.assertNull( ModelUtils.getPropertyValue( holder, "address" ));
		Assert.assertNull( ModelUtils.getPropertyValue( holder, "oops" ));
	}


	@Test
	public void testGetPropertyValues() {

		FileDefinition def = new FileDefinition((URI) null);
		AbstractBlockHolder holder = new BlockFacet( def );
		holder.getInnerBlocks().add( new BlockProperty( def, "names", "value1, value2 , value3, value 4  " ));
		holder.getInnerBlocks().add( new BlockProperty( def, "address", null ));
		holder.getInnerBlocks().add( new BlockProperty( def, "age", "30" ));
		holder.getInnerBlocks().add( new BlockProperty( def, "alias", "" ));

		List<String> values = ModelUtils.getPropertyValues( holder, "names" );
		Assert.assertEquals( 4, values.size());
		Assert.assertEquals( "value1", values.get( 0 ));
		Assert.assertEquals( "value2", values.get( 1 ));
		Assert.assertEquals( "value3", values.get( 2 ));
		Assert.assertEquals( "value 4", values.get( 3 ));

		values = ModelUtils.getPropertyValues( holder, "age" );
		Assert.assertEquals( 1, values.size());
		Assert.assertEquals( "30", values.get( 0 ));

		values = ModelUtils.getPropertyValues( holder, "alias" );
		Assert.assertEquals( 0, values.size());

		values = ModelUtils.getPropertyValues( holder, "address" );
		Assert.assertEquals( 0, values.size());

		values = ModelUtils.getPropertyValues( holder, "oops" );
		Assert.assertEquals( 0, values.size());
	}


	@Test
	public void testGetExportedVariables() {

		String holderName = "facet-name";
		FileDefinition def = new FileDefinition((URI) null);
		AbstractBlockHolder holder = new BlockFacet( def );
		holder.setName( holderName );
		BlockProperty prop = new BlockProperty( def, Constants.PROPERTY_GRAPH_EXPORTS, "" );
		holder.getInnerBlocks().add( prop );

		// One variable
		String varName1 = holderName + ".var1";

		prop.setValue( "var1" );
		Map<String,String> map = ModelUtils.getExportedVariables( holder );
		Assert.assertEquals( 1, map.size());
		Assert.assertTrue( map.containsKey( varName1 ));
		Assert.assertNull( map.get( varName1 ));

		prop.setValue( "var1 = 5" );
		map = ModelUtils.getExportedVariables( holder );
		Assert.assertEquals( 1, map.size());
		Assert.assertEquals( "5", map.get( varName1 ));

		prop.setValue( "var1=5" );
		map = ModelUtils.getExportedVariables( holder );
		Assert.assertEquals( 1, map.size());
		Assert.assertEquals( "5", map.get( varName1 ));

		prop.setValue( "var1= 5895" );
		map = ModelUtils.getExportedVariables( holder );
		Assert.assertEquals( 1, map.size());
		Assert.assertEquals( "5895", map.get( varName1 ));

		// Two variables
		String varName2 = holderName + ".var2";

		prop.setValue( "var1, var2" );
		map = ModelUtils.getExportedVariables( holder );
		Assert.assertEquals( 2, map.size());
		Assert.assertTrue( map.containsKey( varName1 ));
		Assert.assertTrue( map.containsKey( varName2 ));
		Assert.assertNull( map.get( varName1 ));
		Assert.assertNull( map.get( varName2 ));

		prop.setValue( "var1, var2 = 587" );
		map = ModelUtils.getExportedVariables( holder );
		Assert.assertEquals( 2, map.size());
		Assert.assertTrue( map.containsKey( varName1 ));
		Assert.assertNull( map.get( varName1 ));
		Assert.assertEquals( "587", map.get( varName2 ));

		prop.setValue( "var1 = abc, var2 = 587" );
		map = ModelUtils.getExportedVariables( holder );
		Assert.assertEquals( 2, map.size());
		Assert.assertEquals( "abc", map.get( varName1 ));
		Assert.assertEquals( "587", map.get( varName2 ));

	}
}
