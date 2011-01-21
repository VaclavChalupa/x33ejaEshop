package cz.cvut.fel.x33eja.chalupa.eshop.action;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Administrator;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Product;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Stateless
@Name("productAction")
public class ProductActionBean implements ProductActionLocal {
	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	@In(scope = ScopeType.SESSION, required = false)
	private Administrator currentAdministrator;

	@Override
	public boolean isManaged(Product product) {
		if (entityManager.contains(product)) {
			return true;
		}
		return false;
	}

	@Override
	public Product findProduct(long id) {
		Product product = entityManager.find(Product.class, id);
		return product;
	}

	@Override
	public void persist(Product product) {
		if (currentAdministrator != null) {
			product.setAdministrator(currentAdministrator);
			entityManager.persist(product);
		}
	}

	@Override
	public void update(Product product) {
		entityManager.merge(product);
		entityManager.flush();
	}

	@Override
	public void remove(Product product) {
		entityManager.remove(entityManager.merge(product));
		entityManager.flush();
	}

}
