package cz.cvut.fel.x33eja.chalupa.eshop.action;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Address;

@Name("addressesHome")
public class AddressesHome extends EntityHome<Address> {

	public void setAddressesId(Long id) {
		setId(id);
	}

	public Long getAddressesId() {
		return (Long) getId();
	}

	@Override
	protected Address createInstance() {
		Address addresses = new Address();
		return addresses;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public Address getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/*
	 * public List<RegisteredUser> getRegisteredUsersForBillingAddress() {
	 * return getInstance() == null ? null : new ArrayList<RegisteredUser>(
	 * getInstance().getRegisteredUsersForBillingAddress()); } public
	 * List<RegisteredUser> getRegisteredUsersForDeliveryAddress() { return
	 * getInstance() == null ? null : new ArrayList<RegisteredUser>(
	 * getInstance().getRegisteredUsersForDeliveryAddress()); }
	 */

}
