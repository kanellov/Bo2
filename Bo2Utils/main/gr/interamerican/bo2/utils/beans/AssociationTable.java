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
package gr.interamerican.bo2.utils.beans;

import gr.interamerican.bo2.utils.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Associates pairs of elements.
 * 
 * Each element can have only one associate.
 * @param <L> 
 * @param <R> 
 */
public class AssociationTable<L,R> 
implements Iterable<Pair<L, R>>{
	
	/**
	 * Map with the right elements.
	 */
	HashMap<L, R> rights = new HashMap<L, R>();
	
	/**
	 * Map with the left elements.
	 */
	HashMap<R, L> lefts = new HashMap<R, L>();
	
	/**
	 * Associates left with right.
	 * 
	 * @param left 
	 * @param right
	 */
	public void associate(L left, R right) {
		R oldRight = rights.get(left);
		L oldLeft = lefts.get(right);
		
		lefts.remove(right);
		lefts.remove(oldRight);
		rights.remove(left);
		rights.remove(oldLeft);
		
		lefts.put(right, left);
		rights.put(left, right);
	}
	
	/**
	 * Gets the element associated with left.
	 * 
	 * @param left 
	 * @return Returns the element associated with left.
	 */
	public R getRight(L left) {
		return rights.get(left);
	}
	
	/**
	 * Gets the element associated with right.
	 * 
	 * @param right 
	 * @return Returns the element associated with right.
	 */	
	public L getLeft(R right) {
		return lefts.get(right);
	}
	
	/**
	 * Clears the association table.
	 */
	public void clear() {
		lefts.clear();
		rights.clear();
	}

	@SuppressWarnings("nls")
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		boolean empty = true;
		
		for (Map.Entry<L, R> entry : rights.entrySet()) {			
			if (!empty) {
				sb.append(",");
			}
			L leftElement = entry.getKey();
			R rightElement = entry.getValue();
			sb.append("[");
			sb.append(StringUtils.toString(leftElement));
			sb.append(",");
			sb.append(StringUtils.toString(rightElement));
			sb.append("]");
			empty = false; 
		}
		sb.append("}");		
		return sb.toString();
	}
	
	/**
	 * Gets the size of the {@link AssociationTable}.
	 * 
	 * @return Returns the size of the {@link AssociationTable}.
	 */
	public int size() {
		return lefts.size();
	}

	
	@Override
	public Iterator<Pair<L, R>> iterator() {	
		return new SimpleMapIterator<L, R>(rights);
	}
	
	
	

	
	/**
	 * Associates left with right.
	 * 
	 * @param left 
	 * @param right
	 * 
	 * @return Returns true if the specified left was found
	 *         in the association table.
	 *  
	 */
	public boolean removeLeft(L left) {
		R right = rights.get(left);
		if (right==null) {
			return false;
		}
		rights.remove(left);
		lefts.remove(right);
		return true;
	}
	
	/**
	 * Associates left with right.
	 * 
	 * @param left 
	 * @param right
	 * 
	 * @return Returns true if the specified left was found
	 *         in the association table.
	 *  
	 */
	public boolean removeRight(R right) {
		L left = lefts.get(right);
		if (left==null) {
			return false;
		}
		lefts.remove(right);
		rights.remove(left);		
		return true;
	}
	

}
