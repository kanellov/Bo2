package gr.interamerican.bo2.utils.adapters.trans.date;

import gr.interamerican.bo2.utils.DateUtils;
import gr.interamerican.bo2.utils.adapters.trans.date.Month;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link CalculateMonth}.
 */
public class TestMonth {
	
	/**
	 * Test for execute().
	 */
	@Test
	public void testExecute() {
		Month calc = new Month();
		Integer expected = 7;
		Date input = DateUtils.getDate(2013, Calendar.JULY, 13); 
		Integer actual = calc.execute(input);		
		Assert.assertEquals(expected, actual);
	}

}
