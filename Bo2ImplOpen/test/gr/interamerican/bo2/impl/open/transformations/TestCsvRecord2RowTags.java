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
package gr.interamerican.bo2.impl.open.transformations;

import gr.interamerican.bo2.impl.open.records.CsvRecord;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for {@link CsvRecord2RowTags}.
 */
public class TestCsvRecord2RowTags {
	
	/**
	 * Unit test for execute.
	 */
	@SuppressWarnings("nls")
	@Test
	public void testExecute() {
		CsvRecord rec = new CsvRecord(4);
		rec.setString(0, "a");
		rec.setString(1, "b");
		rec.setString(2, "c");
		rec.setString(3, "d");
		CsvRecord2RowTags toRow = new CsvRecord2RowTags(4);
		String actual = toRow.execute(rec);
		String expected = "<tr><td>a</td><td>b</td><td>c</td><td>d</td></tr>";
		Assert.assertEquals(expected, actual);
		CsvRecord2RowTags toHeader = new CsvRecord2RowTags(4, "tr", "th");
		actual = toHeader.execute(rec);
		expected = "<tr><th>a</th><th>b</th><th>c</th><th>d</th></tr>";
		Assert.assertEquals(expected, actual);
	}

}
