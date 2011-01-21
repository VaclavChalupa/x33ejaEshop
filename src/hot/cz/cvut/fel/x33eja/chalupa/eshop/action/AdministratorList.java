package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Administrator;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Name("administratorList")
public class AdministratorList extends EntityQuery<Administrator> {

	private static final String EJBQL = "select administrator from Administrator administrator";

	private static final String[] RESTRICTIONS = {
			"lower(administrator.firstname) like lower(concat(#{administratorList.administrator.firstname},'%'))",
			"lower(administrator.surname) like lower(concat(#{administratorList.administrator.surname},'%'))",
			"lower(administrator.username) like lower(concat(#{administratorList.administrator.username},'%'))",
			"lower(administrator.email) like lower(concat(#{administratorList.administrator.email},'%'))", };

	private final Administrator administrator = new Administrator();

	public AdministratorList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Administrator getAdministrator() {
		return administrator;
	}
}
