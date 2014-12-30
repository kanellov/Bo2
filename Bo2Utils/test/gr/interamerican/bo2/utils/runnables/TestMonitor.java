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
package gr.interamerican.bo2.utils.runnables;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import gr.interamerican.bo2.utils.adapters.VoidOperation;
import gr.interamerican.bo2.utils.adapters.cmd.SimpleCommandSequence;
import gr.interamerican.bo2.utils.attributes.SimpleCommand;
import gr.interamerican.bo2.utils.beans.Pair;
import gr.interamerican.bo2.utils.conditions.Condition;

import org.junit.Test;

/**
 * Unit test for {@link Monitor}.
 */
public class TestMonitor {
	
	/**
	 * test for the constructor.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void testConstructor() {
		Object o = new Object();
		Condition<Object> stop = mock(Condition.class);
		Monitor<Object> monitor = new Monitor<Object>(o, stop);
		assertEquals(o, monitor.system);
		assertEquals(0, monitor.interval);
		assertEquals(stop, monitor.mustStop);
		assertFalse(monitor.started);
		assertNotNull(monitor.operations);
		assertEquals(0, monitor.operations.size());
	}
	
	/**
	 * test for the constructor.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void testAddOperation_withValid() {
		Object system = new Object();	
		Condition<Object> stop = mock(Condition.class);			
		Monitor<Object> monitor = new Monitor<Object>(system, stop);
		MonitoringOperation<Object> mo1 = mock(MonitoringOperation.class);
		when(mo1.isValid()).thenReturn(true);
		Long l1 = 1000L;
		monitor.addOperation(mo1, l1);
		Pair<MonitoringOperation<Object>, Long> pair = monitor.operations.get(0);
		assertEquals(mo1, pair.getLeft());
		assertEquals(l1, pair.getRight());
		assertEquals(l1.longValue(), monitor.interval);
	}
	
	/**
	 * test for the constructor.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void testAddOperation_withInValid() {
		Object system = new Object();	
		Condition<Object> stop = mock(Condition.class);			
		Monitor<Object> monitor = new Monitor<Object>(system, stop);
		MonitoringOperation<Object> mo1 = mock(MonitoringOperation.class);
		when(mo1.isValid()).thenReturn(false);		
		monitor.addOperation(mo1, 2000L);
		assertEquals(0, monitor.operations.size());
		assertEquals(0L, monitor.interval);		
	}
	
	/**
	 * test for the constructor.
	 */
	@Test(expected=RuntimeException.class)
	@SuppressWarnings("unchecked")
	public void testAddOperation_whenStarted() {
		Object system = new Object();	
		Condition<Object> stop = mock(Condition.class);			
		Monitor<Object> monitor = new Monitor<Object>(system, stop);
		monitor.started = true;
		MonitoringOperation<Object> mo1 = mock(MonitoringOperation.class);
		when(mo1.isValid()).thenReturn(false);		
		monitor.addOperation(mo1, 2000L);
	}
	
	/**
	 * test for the constructor.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void testRun() {
		Object o = new Object();
		Condition<Object> stop = mock(Condition.class);		
		MonitoringOperation<Object> mo1 = mock(MonitoringOperation.class);
		when(mo1.isValid()).thenReturn(true);
		when(stop.check(o)).thenReturn(false,false,false,true);		
		Monitor<Object> monitor = new Monitor<Object>(o, stop);
		monitor.addOperation(mo1, 2L);		
		monitor.run();
		verify(mo1,times(4)).execute(o);
	}

}
