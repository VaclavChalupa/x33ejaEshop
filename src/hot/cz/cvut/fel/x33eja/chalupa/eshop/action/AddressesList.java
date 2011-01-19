package cz.cvut.fel.x33eja.chalupa.eshop.action;

import cz.cvut.fel.x33eja.chalupa.eshop.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("addressesList")
public class AddressesList extends EntityQuery<Address> {

	private static final String EJBQL = "select addresses from Addresses addresses";

	private static final String[] RESTRICTIONS = {
			"lower(addresses.city) like lower(concat(#{addressesList.addresses.city},'%'))",
			"lower(addresses.country) like lower(concat(#{addressesList.addresses.country},'%'))",
			"lower(addresses.houseNumber) like lower(concat(#{addressesList.addresses.houseNumber},'%'))",
			"lower(addresses.postCode) like lower(concat(#{addressesList.addresses.postCode},'%'))",
			"lower(addresses.street) like lower(concat(#{addressesList.addresses.street},'%'))",};

	private Address addresses = new Address();

	public AddressesList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Address getAddresses() {
		return addresses;
	}
}
