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
package gr.interamerican.wicket.bo2.util.resource;

import static gr.interamerican.wicket.bo2.protocol.http.Bo2WicketRequestCycle.provider;
import gr.interamerican.bo2.arch.exceptions.InitializationException;
import gr.interamerican.bo2.impl.open.namedstreams.NamedStreamDefinition;
import gr.interamerican.bo2.impl.open.namedstreams.NamedStreamUtils;
import gr.interamerican.bo2.impl.open.namedstreams.resourcetypes.StreamResource;
import gr.interamerican.bo2.impl.open.namedstreams.types.NamedInputStream;
import gr.interamerican.bo2.impl.open.namedstreams.types.StreamType;
import gr.interamerican.bo2.utils.Bo2UtilsEnvironment;
import gr.interamerican.wicket.bo2.protocol.http.Bo2WicketRequestCycle;
import gr.interamerican.wicket.bo2.test.MockApplicationForWicketBo2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit tests for {@link Bo2WicketRequestCycle}.
 * 
 */
public class TestNamedInputStreamAsResourceStream {
	
	/**
	 * the WicketTester
	 */
	public WicketTester wicketTester =
		new WicketTester(new MockApplicationForWicketBo2());
	
	/**
	 * Unit test for a request cycle.
	 * @throws InitializationException 
	 * @throws IOException 
	 */
	@SuppressWarnings("nls")
	@Test
	public void testLifeCycle() throws InitializationException, IOException{
		Bo2WicketRequestCycle.beginRequest(RequestCycle.get());
		String name = "MockNs";
		String manager = "LOCALFS";	
		InputStream stream = Mockito.mock(InputStream.class);
		
		Charset encoding=Charset.defaultCharset();
		NamedInputStream nis = new NamedInputStream(null, stream, name, 100, null, encoding,null);
		NamedStreamUtils.registerStream(nis, provider(), manager);
		NamedInputStreamAsResourceStream resource = 
			new NamedInputStreamAsResourceStream(manager, name);
		Assert.assertEquals(nis, resource.nis);
		resource.close();
		Bo2WicketRequestCycle.endRequest(RequestCycle.get());
	}
	
}
