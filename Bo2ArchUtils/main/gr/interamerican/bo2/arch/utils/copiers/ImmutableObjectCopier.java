/*******************************************************************************
 * Copyright (c) 2013 INTERAMERICAN PROPERTY AND CASUALTY INSURANCE COMPANY S.A. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/copyleft/lesser.html
 * 
 * This library is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU Lesser General Public License for more details.
 ******************************************************************************/
package gr.interamerican.bo2.arch.utils.copiers;

import gr.interamerican.bo2.arch.ext.Copier;

/**
 * Copier for immutable objects.
 * 
 * @param <T> 
 *        Type of object beng copied.
 */
public class ImmutableObjectCopier<T> implements Copier<T> {
	
	public T copy(T objectToCopy) {
		return objectToCopy; //This is a new object, since it is immutable.
	}

}
