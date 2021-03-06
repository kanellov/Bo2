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
package gr.interamerican.wicket.bo2.markup.html.form;

import gr.interamerican.bo2.utils.meta.descriptors.DateBoPropertyDescriptor;
import gr.interamerican.wicket.bo2.utils.SelfDrawnUtils;
import gr.interamerican.wicket.utils.MarkupConstants;

import java.util.Date;

import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DateField;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Self-drawn DateField.
 */
public class SelfDrawnDateField extends DateField {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new SelfDrawnDateField object.
	 * 
	 * @param id
	 * @param descriptor
	 */
	public SelfDrawnDateField(String id, DateBoPropertyDescriptor descriptor) {
		super(id, new Model<Date>());
		SelfDrawnUtils.<Date> standardSelfDrawnFormComponentStuff(this, descriptor);
	}

	/**
	 * Creates a new SelfDrawnDateField object.
	 * 
	 * @param id
	 * @param descriptor
	 * @param model
	 */
	public SelfDrawnDateField(String id, IModel<Date> model, DateBoPropertyDescriptor descriptor) {
		super(id, model);
		SelfDrawnUtils.<Date> standardSelfDrawnFormComponentStuff(this, descriptor);
	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		tag.setName(MarkupConstants.SPAN);
		super.onComponentTag(tag);
	}

	/**
	 * Gets the internal DateTextField used by this component implementation.
	 * <br/>
	 * This is useful, for instance when we need to add a behavior on the 'onchange'
	 * javascript event of this field.
	 * 
	 * @return the internal DateTextField instance.
	 */
	public DateTextField getInternalDateTextField() {
		return (DateTextField) get(DATE);
	}

}
