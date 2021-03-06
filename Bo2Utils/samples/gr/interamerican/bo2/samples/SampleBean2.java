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
package gr.interamerican.bo2.samples;

import java.io.Serializable;

/**
 * sample bean class
 */
public class SampleBean2 implements Serializable {
	/**
 * 
 */
	private static final long serialVersionUID = 1L;

	/**
	 * field1
	 */
	String field1;

	/**
	 * field 2
	 */
	String field2;

	/**
	 * Gets the field1.
	 * 
	 * @return Returns the field1
	 */
	public String getField1() {
		return field1;
	}

	/**
	 * Assigns a new value to the field1.
	 * 
	 * @param field1
	 *            the field1 to set
	 */
	public void setField1(String field1) {
		this.field1 = field1;
	}

	/**
	 * Gets the field2.
	 * 
	 * @return Returns the field2
	 */
	public String getField2() {
		return field2;
	}

	/**
	 * Assigns a new value to the field2.
	 * 
	 * @param field2
	 *            the field2 to set
	 */
	public void setField2(String field2) {
		this.field2 = field2;
	}

	/**
	 * Gets the input value.
	 * 
	 * @param valueToGet
	 * @return Returns the input parameter valueToGet.
	 */
	public String getField2(String valueToGet) {
		return valueToGet;
	}

}
