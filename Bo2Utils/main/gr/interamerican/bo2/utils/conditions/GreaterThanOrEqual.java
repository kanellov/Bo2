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
package gr.interamerican.bo2.utils.conditions;

import gr.interamerican.bo2.utils.comparators.ComparablesComparator;

import java.util.Comparator;

/**
 * Comparison condition that check that the specified object is greater
 * than or equal to an object that is specified at the constructor of the
 * condition.
 * 
 * @param <T> 
 *        Type of object being checked.
 * 
 */
public class GreaterThanOrEqual<T> 
extends ComparisonCondition<T>
implements Condition<T> {
	
	
	public boolean check(T t) {		
		return (comparator.compare(t, comparedValue) >= 0); 		
	}

	/**
	 * Creates a new GreaterThanOrEqual object. 
	 *
	 * @param comparator
	 *        Comparator doing the comparison.
	 * @param comparedValue
	 *        Value being compared against objects being checked by the
	 *        condition.
	 */
	public GreaterThanOrEqual(Comparator<T> comparator, T comparedValue) {
		super(comparator, comparedValue);
	}

	/**
	 * Creates a new GreaterThanOrEqual object. 
	 * 
	 * This constructor will use the default comparator for comparable objects
	 * ({@link ComparablesComparator}). This comparator can be used only if
	 * comparedValue is {@link Comparable}.
	 *
	 * @param comparedValue
	 *        Value being compared against objects being checked by the
	 *        condition.
	 */
	public GreaterThanOrEqual(T comparedValue) {
		super(comparedValue);
	}

}


