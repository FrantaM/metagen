/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package net.ftlines.metagen.processor.trim;

import static net.ftlines.metagen.processor.MetaAsserts.*;
import static org.junit.Assert.*;
import net.ftlines.metagen.processor.MetaPackageTest;

import org.junit.Test;

public class NestedClassTest extends MetaPackageTest
{

// no longer trim
//	@Test
//	public void trimmedTopLevel() throws Exception
//	{
//		assertTrue(result.isClean());
//		assertMetaClassNotGenerated(result, Bean.One.class);
//		assertMetaClassNotGenerated(result, Bean.class);
//	}

	@Test
	public void beansWithGaps() throws Exception
	{
		assertMetaClassGenerated(result, Bean2.class);
		assertMetaClassGenerated(result, Bean2.One.class);
		assertMetaClassGenerated(result, Bean2.One.Two.class);
		assertMetaPropertyGenerated(result, Bean2.One.Two.class, "p");
		assertMetaClassNotGenerated(result, Bean2.One.Two.Three.class);
		assertMetaClassNotGenerated(result, Bean2.One.Four.class);
		assertMetaClassGenerated(result, Bean2.Five.class);
		assertMetaPropertyGenerated(result, Bean2.Five.class, "p");
		assertMetaClassNotGenerated(result, Bean2.Five.Six.class);
	}

	@Test
	public void metaAnnotation()
	{
		assertMetaClassGenerated(result, Bean3.class);
		assertMetaClassGenerated(result, Bean3.One.class);
		assertMetaClassGenerated(result, Bean3.One.Two.class);
	}

}
