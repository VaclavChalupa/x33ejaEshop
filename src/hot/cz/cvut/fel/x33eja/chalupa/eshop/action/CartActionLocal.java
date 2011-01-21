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
public interface CartActionLocal {

	void initAssignedProducts();

	void addProduct(Product product);

	void removeProduct();

	public void destroy();

	public String confirmOrder();

}
