package cz.cvut.fel.x33eja.chalupa.eshop.action;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import cz.cvut.fel.x33eja.chalupa.eshop.model.RegisteredUser;

@Name("registeredUserHome")
public class RegisteredUserHome extends EntityHome<RegisteredUser> {

	@In(create = true)
	AddressesHome addressesHome;

	/*
	 * @In(create = true) UsersHome usersHome;
	 */

	public void setRegisteredUserId(Long id) {
		setId(id);
	}

	public Long getRegisteredUserId() {
		return (Long) getId();
	}

	@Override
	protected RegisteredUser createInstance() {
		RegisteredUser registeredUser = new RegisteredUser();
		return registeredUser;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		/*
		 * getInstance(); Address addressesByBillingAddress = addressesHome
		 * .getDefinedInstance(); if (addressesByBillingAddress != null) {
		 * getInstance().setAddressesByBillingAddress(
		 * addressesByBillingAddress); } Address addressesByDeliveryAddress =
		 * addressesHome .getDefinedInstance(); if (addressesByDeliveryAddress
		 * != null) { getInstance().setAddressesByDeliveryAddress(
		 * addressesByDeliveryAddress); } User users =
		 * usersHome.getDefinedInstance(); if (users != null) {
		 * getInstance().setUsers(users); }
		 */
	}

	/*
	 * public boolean isWired() { if
	 * (getInstance().getAddressesByDeliveryAddress() == null) return false;
	 * return true; }
	 */

	public RegisteredUser getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/*
	 * public List<Orders> getOrderses() { return getInstance() == null ? null :
	 * new ArrayList<Orders>( getInstance().getOrderses()); }
	 * 
	 * public List<RegisteredUserProduct> getRegisteredUserProducts() { return
	 * getInstance() == null ? null : new
	 * ArrayList<RegisteredUserProduct>(getInstance()
	 * .getRegisteredUserProducts()); }
	 */

}
