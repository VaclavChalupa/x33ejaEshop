package cz.cvut.fel.x33eja.chalupa.eshop.viewmanagement;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.action.AdministratorActionLocal;
import cz.cvut.fel.x33eja.chalupa.eshop.action.RoleActionLocal;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Administrator;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Role;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Name("administratorManager")
@Scope(ScopeType.PAGE)
public class AdministratorManager {

	@Logger
	private Log log;

	@In(create = true)
	AdministratorActionLocal administratorAction;

	@In(create = true)
	RoleActionLocal roleAction;

	@In
	StatusMessages statusMessages;

	@DataModel
	private List<Role> adminRoles;

	@DataModelSelection
	private Role selectedRole;

	private Administrator administrator;

	public AdministratorManager() {
		administrator = null;
	}

	@Factory("adminRoles")
	public void findAllRoles() {
		adminRoles = roleAction.getAllRoles();
	}

	public boolean isManaged() {
		if (administrator != null && administrator.getId() != null
				&& administrator.getId() > 0) {
			return true;
		}
		return false;
	}

	public Administrator getAdministrator() {
		if (administrator == null) {
			administrator = new Administrator();
		}
		return administrator;
	}

	public Long getAdministratorId() {
		return getAdministrator().getId();
	}

	public void setAdministratorId(Long id) {
		if (id != 0 && id != null) {
			administrator = administratorAction.findAdministrator(id);
		}
	}

	public String persist() throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		administratorAction.persist(administrator);
		return "persisted";
	}

	public String update() {
		administratorAction.update(administrator);
		return "updated";
	}

	public String remove() {
		administratorAction.remove(administrator);
		return "removed";
	}

	public boolean hasRole(Role role) {
		return getAdministrator().getRoles().contains(role);
	}

	public String addRole() {
		log.info("ADD ROLE");
		administrator.getRoles().add(selectedRole);
		return null;
	}

	public String removeRole() {
		log.info("REMOVE ROLE");
		administrator.getRoles().remove(selectedRole);
		return null;
	}

}
