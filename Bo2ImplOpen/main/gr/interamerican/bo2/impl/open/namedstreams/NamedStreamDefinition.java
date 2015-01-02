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
package gr.interamerican.bo2.impl.open.namedstreams;

import static gr.interamerican.bo2.utils.StringConstants.EQUALS;
import static gr.interamerican.bo2.utils.StringConstants.COMMA;
import gr.interamerican.bo2.utils.Bo2UtilsEnvironment;
import gr.interamerican.bo2.utils.StringConstants;
import gr.interamerican.bo2.utils.StringUtils;

import java.nio.charset.Charset;

/**
 * Definition properties for a NamedStream.
 */
public class NamedStreamDefinition {
	
	/**
	 * String that gets replaced with current timestamp when found in a stream definition file uri.
	 */
	public static final String TIMESTAMP = "<TIMESTAMP>"; //$NON-NLS-1$
	
	/**
	 * String that gets replaced with current date when found in a stream definition file uri.
	 */
	public static final String DATE = "<DATE>"; //$NON-NLS-1$
	
	/**
	 * Prefix of encoding attribute of a stream definition description.
	 */
	public static final String ENCODING_PREFIX = "enc:"; //$NON-NLS-1$
	
	/**
	 * Prefix of record length attribute of a stream definition description.
	 */
	public static final String RECORD_LENGTH_PREFIX = "rec:"; //$NON-NLS-1$
	
	/**
	 * Logical name.
	 */
	String name;
	/**
	 * uri.
	 */
	String uri;
	/**
	 * stream type.
	 */
	StreamType type;
	/**
	 * resource type.
	 */
	StreamResource resourceType;	
	/**
	 * record length.
	 */
	int recordLength;
	/**
	 * stream encoding. 
	 */
	Charset encoding = Bo2UtilsEnvironment.getDefaultTextCharset();
	
	/**
	 * Gets the name.
	 *
	 * @return Returns the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Assigns a new value to the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the uri.
	 *
	 * @return Returns the uri
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * Assigns a new value to the uri.
	 *
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * Gets the type.
	 *
	 * @return Returns the type
	 */
	public StreamType getType() {
		return type;
	}
	/**
	 * Assigns a new value to the type.
	 *
	 * @param type the type to set
	 */
	public void setType(StreamType type) {
		this.type = type;
	}
	/**
	 * Gets the recordLength.
	 *
	 * @return Returns the recordLength
	 */
	public int getRecordLength() {
		return recordLength;
	}
	/**
	 * Assigns a new value to the recordLength.
	 *
	 * @param recordLength the recordLength to set
	 */
	public void setRecordLength(int recordLength) {
		this.recordLength = recordLength;
	}
	/**
	 * Gets the resourceType.
	 *
	 * @return Returns the resourceType
	 */
	public StreamResource getResourceType() {
		return resourceType;
	}
	/**
	 * Assigns a new value to the resourceType.
	 *
	 * @param resourceType the resourceType to set
	 */
	public void setResourceType(StreamResource resourceType) {
		this.resourceType = resourceType;
	}
	/**
	 * Gets the encoding.
	 *
	 * @return Returns the encoding
	 */
	public Charset getEncoding() {
		return encoding;
	}
	/**
	 * Assigns a new value to the encoding.
	 *
	 * @param encoding the encoding to set
	 */
	public void setEncoding(Charset encoding) {
		this.encoding = encoding;
	}
	
	/**
	 * Gets the specification string.
	 * 
	 * The specifications string is a string that specifies the value
	 * of the property that is named after the named stream defined by
	 * this definition.
	 * 
	 * @return returns the specification string.
	 */
	@SuppressWarnings("unchecked")
	public String getSpecsString() {
		String reclen = RECORD_LENGTH_PREFIX+Integer.toString(recordLength);
		String charset = ENCODING_PREFIX+encoding.toString();
		return StringUtils.concatSeparated(COMMA, uri, type, resourceType, reclen, charset);
	}
	
	@Override
	public String toString() {
		return StringUtils.concat(name, EQUALS, getSpecsString());
	}

}
