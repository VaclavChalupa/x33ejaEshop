package cz.cvut.fel.x33eja.chalupa.eshop.action;

import cz.cvut.fel.x33eja.chalupa.eshop.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("administratorsList")
public class AdministratorsList extends EntityQuery<Administrator> {

	private static final String EJBQL = "select administrators from Administrators administrators";

	private static final String[] RESTRICTIONS = {};

	private Administrator administrators = new Administrator();

	public AdministratorsList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Administrator getAdministrators() {
		return administrators;
	}
}
