package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.List;

import javax.ejb.Local;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Role;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Local
public interface RoleActionLocal {

	public Role findRole(int id);

	public List<Role> getAllRoles();

}
