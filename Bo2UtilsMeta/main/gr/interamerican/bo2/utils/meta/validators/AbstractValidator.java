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
package gr.interamerican.bo2.utils.meta.validators;

import gr.interamerican.bo2.utils.StringConstants;
import gr.interamerican.bo2.utils.meta.Meta;
import gr.interamerican.bo2.utils.meta.exceptions.ValidationException;

/**
 * Basic abstract Validator.
 */
public abstract class AbstractValidator {

	/**
	 * Creates a {@link ValidationException} for a value.
	 * 
	 * @return ValidationException 
	 */
	protected ValidationException invalid() {
		String messageKey = 
			this.getClass().getName() + StringConstants.DOT + "message"; //$NON-NLS-1$
		String message = Meta.getMessages().getString(messageKey);
		return new ValidationException(message);
	}

}
