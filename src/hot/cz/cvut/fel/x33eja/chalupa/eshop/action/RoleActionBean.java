package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Role;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Stateless
@Name("roleAction")
public class RoleActionBean implements RoleActionLocal {
	@Logger
	private Log log;

	@In
	StatusMessages statusMessages;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Role findRole(int id) {
		Role role = em.find(Role.class, id);
		return role;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getAllRoles() {
		return em.createNamedQuery("Role.findAllRoles").getResultList();
	}

}
