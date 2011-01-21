package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Administrator;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Stateless
@Name("administratorAction")
public class AdministratorActionBean implements AdministratorActionLocal {

	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	@Override
	public Administrator findAdministrator(long id) {
		Administrator administrator = entityManager.find(Administrator.class,
				id);
		return administrator;
	}

	@Override
	public void persist(Administrator administrator)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		administrator.setPassword(Administrator.generatePasswordHash(
				administrator.getPassword(), administrator.getUsername()));
		entityManager.persist(administrator);
		entityManager.flush();
	}

	@Override
	public void update(Administrator administrator) {
		entityManager.merge(administrator);
		entityManager.flush();
	}

	@Override
	public void remove(Administrator administrator) {
		entityManager.remove(entityManager.merge(administrator));
		entityManager.flush();
	}

	@Override
	public boolean isManaged(Administrator administrator) {
		if (entityManager.contains(administrator)) {
			return true;
		}
		return false;
	}

}
