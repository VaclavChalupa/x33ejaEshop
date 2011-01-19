package cz.cvut.fel.x33eja.chalupa.eshop.action;

import cz.cvut.fel.x33eja.chalupa.eshop.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("rolesList")
public class RolesList extends EntityQuery<Role> {

	private static final String EJBQL = "select roles from Roles roles";

	private static final String[] RESTRICTIONS = {"lower(roles.rolename) like lower(concat(#{rolesList.roles.rolename},'%'))",};

	private Role roles = new Role();

	public RolesList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Role getRoles() {
		return roles;
	}
}
