package cz.cvut.fel.x33eja.chalupa.eshop.action;

import cz.cvut.fel.x33eja.chalupa.eshop.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("registeredUserList")
public class RegisteredUserList extends EntityQuery<RegisteredUser> {

	private static final String EJBQL = "select registeredUser from RegisteredUser registeredUser";

	private static final String[] RESTRICTIONS = {};

	private RegisteredUser registeredUser = new RegisteredUser();

	public RegisteredUserList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}
}
