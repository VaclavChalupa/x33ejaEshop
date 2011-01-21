/**
 * Copyright (c) 2010 Vaclav Chalupa (vac.chalupa@gmail.com)
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package cz.cvut.fel.x33eja.chalupa.eshop.action.components;

import javax.servlet.ServletContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.contexts.ServletLifecycle;
import org.jboss.seam.log.Log;

import cz.cvut.fel.formbuilder.FormBuilder;
import cz.cvut.fel.formbuilder.config.ConfigurationFactory;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Administrator;

/**
 * Component connected to FormBuilder 2.0 Return generated form location to
 * facelets
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Name("formBuilderGenerator")
public class FormBuilderGenerator {

	@Logger
	private Log log;

	@In(required = false)
	private Administrator currentAdministrator;

	public String generateForm(String entityClassName) {
		return generateForm(entityClassName, true, null);
	}

	public String generateForm(String entityClassName, boolean useRuntimeRoles,
			String formRolesString) {

		log.info("HELLO FROM FORM GENERATOR ");

		String[] formRoles = null;
		if (formRolesString != null && !"".equals(formRolesString)) {
			formRoles = formRolesString.split("-");
		}

		// set absolute path to WEB-INF for FormBuilder
		ServletContext ctx = ServletLifecycle.getServletContext();
		String appPath = ctx.getRealPath("WEB-INF/form-builder/form-config");
		ConfigurationFactory.setWorkingDirectory(appPath);

		// get alphabetical userRoles
		String roles = useRuntimeRoles && currentAdministrator != null ? currentAdministrator
				.getRolesAsString() : null;

		String formPath = "";
		try {
			// return absolute path to generated form
			formPath = FormBuilder.generateFormFromClassToDirectory(
					Class.forName(entityClassName), roles, formRoles);
			log.info("Form generated to " + formPath);
		} catch (ClassNotFoundException e) {
			log.error("Class for {0} not found: Form not generated!",
					entityClassName);
			return formPath;
		}

		// remove absolute part of path + customize relative path to output
		// directory in form-config.xml
		String relativePath = formPath != null ? formPath.replace(appPath
				+ "/../../../", "") : "error-location";

		log.info("Form relative path for JSF: {0}", relativePath);

		return relativePath;
	}

	public String generateTable(String entityClassName) {
		return generateTable(entityClassName, true, null);
	}

	public String generateTable(String entityClassName,
			boolean useRuntimeRoles, String tableRolesString) {

		log.info("HELLO FROM TABLE GENERATOR");

		String[] tableRoles = null;
		if (tableRolesString != null && !"".equals(tableRolesString)) {
			tableRoles = tableRolesString.split("-");
		}

		// set absolute path to WEB-INF for FormBuilder
		ServletContext ctx = ServletLifecycle.getServletContext();
		String appPath = ctx.getRealPath("WEB-INF/form-builder/table-config");
		ConfigurationFactory.setWorkingDirectory(appPath);

		// get alphabetical userRoles
		String roles = useRuntimeRoles && currentAdministrator != null ? currentAdministrator
				.getRolesAsString() : null;

		String formPath = "";
		try {
			// return absolute path to generated form
			formPath = FormBuilder.generateFormFromClassToDirectory(
					Class.forName(entityClassName), roles, tableRoles);
			log.info("table generated to {0}", formPath);
		} catch (ClassNotFoundException e) {
			log.error("Class for {0} not found: Table not generated!",
					entityClassName);
			return "";
		}

		// remove absolute part of path + customize relative path to output
		// directory in form-config.xml
		String relativePath = formPath != null ? formPath.replace(appPath
				+ "/../../../", "") : "error-location";

		log.info("Table relative path for JSF: {0}", relativePath);

		return relativePath;
	}
}
