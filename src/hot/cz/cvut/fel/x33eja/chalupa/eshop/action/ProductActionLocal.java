package cz.cvut.fel.x33eja.chalupa.eshop.action;

import javax.ejb.Local;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Product;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Local
public interface ProductActionLocal {

	Product findProduct(long id);

	void persist(Product product);

	void update(Product product);

	void remove(Product product);

	boolean isManaged(Product product);

}
