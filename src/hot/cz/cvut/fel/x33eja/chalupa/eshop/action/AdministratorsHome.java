package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Administrator;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Photo;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Product;

@Name("administratorsHome")
public class AdministratorsHome extends EntityHome<Administrator> {

	/*
	 * @In(create = true) UsersHome usersHome;
	 */

	public void setAdministratorsId(Long id) {
		setId(id);
	}

	public Long getAdministratorsId() {
		return (Long) getId();
	}

	@Override
	protected Administrator createInstance() {
		Administrator administrators = new Administrator();
		return administrators;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		/*
		 * User users = usersHome.getDefinedInstance(); if (users != null) {
		 * getInstance().setUsers(users); }
		 */
	}

	public boolean isWired() {
		return true;
	}

	public Administrator getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/*
	 * public List<AdministratorRole> getAdministratorRoles() { return
	 * getInstance() == null ? null : new ArrayList<AdministratorRole>(
	 * getInstance().getAdministratorRoles()); }
	 */
	public List<Photo> getPhotoses() {
		return getInstance() == null ? null : new ArrayList<Photo>(
				getInstance().getPhotos());
	}

	public List<Product> getProducts() {
		return getInstance() == null ? null : new ArrayList<Product>(
				getInstance().getProducts());
	}

}
