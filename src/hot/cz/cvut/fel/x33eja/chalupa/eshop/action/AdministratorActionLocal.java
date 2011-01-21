package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Local;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Administrator;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Local
public interface AdministratorActionLocal {

	Administrator findAdministrator(long id);

	void persist(Administrator administrator) throws NoSuchAlgorithmException,
			UnsupportedEncodingException;

	void update(Administrator administrator);

	void remove(Administrator administrator);

	boolean isManaged(Administrator administrator);

}
