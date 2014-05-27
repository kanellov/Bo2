package gr.interamerican.bo2.texttemplate.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 */
public class FreemarkerUtility {

	/**
	 * Template name.
	 */
	private static String TEMPLATE_NAME = "templateName"; //$NON-NLS-1$

	/**
	 * {@link Configuration}.
	 */
	static Configuration cfg = new Configuration();

	/**
	 * Parse the given template string with the given model in the Freemarker
	 * format. For example: ${exampleObject.name} by ${exampleObject.lastName}
	 * 
	 * @param template
	 * @param model
	 * @return the parsed template.
	 * @throws IOException
	 * @throws TemplateException
	 */
	static String fix(String template, Object model) throws IOException, TemplateException {
		Template t = new Template(TEMPLATE_NAME, new StringReader(template), cfg);
		Writer out = new StringWriter();
		t.process(model, out);
		return out.toString();
	}

}
