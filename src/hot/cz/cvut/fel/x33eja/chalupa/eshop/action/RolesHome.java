package cz.cvut.fel.x33eja.chalupa.eshop.action;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Role;

@Name("rolesHome")
public class RolesHome extends EntityHome<Role> {

	public void setRolesId(Integer id) {
		setId(id);
	}

	public Integer getRolesId() {
		return (Integer) getId();
	}

	@Override
	protected Role createInstance() {
		Role roles = new Role();
		return roles;
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

	public Role getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/*
	 * public List<AdministratorRole> getAdministratorRoles() { return
	 * getInstance() == null ? null : new ArrayList<AdministratorRole>(
	 * getInstance().getAdministratorRoles()); }
	 */

}
