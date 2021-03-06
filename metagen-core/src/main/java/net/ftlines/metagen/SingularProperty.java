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

package net.ftlines.metagen;

import java.io.ObjectStreamException;

/**
 * @author igor
 * 
 * @param <C>
 *            type that contains the attribute
 * @param <R>
 *            type of the attribute
 */
public class SingularProperty<C, R> extends Property<C, R>
{
	public SingularProperty(String name, Class<?> container, String fieldName, String getterName, String setterName)
	{
		super(name, container, fieldName, getterName, setterName);
	}


	Object writeReplace() throws ObjectStreamException
	{
		return new SerializedSingularProperty(this);
	}

	static class SerializedSingularProperty extends SerializedProperty
	{

		public SerializedSingularProperty(SingularProperty<?,?> p)
		{
			super(p);
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		Object readResolve() throws ObjectStreamException
		{
			try
			{
				return new SingularProperty(n, Class.forName(cn), fn, gn, sn);
			}
			catch (ClassNotFoundException e)
			{
				throw new RuntimeException("Could not deserialize SingularProperty", e);
			}
		}

	}
}
