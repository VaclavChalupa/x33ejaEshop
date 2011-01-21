package cz.cvut.fel.x33eja.chalupa.eshop.viewmanagement;

import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.action.CartActionLocal;
import cz.cvut.fel.x33eja.chalupa.eshop.action.PhotoActionLocal;
import cz.cvut.fel.x33eja.chalupa.eshop.action.ProductActionLocal;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Photo;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Product;
import cz.cvut.fel.x33eja.chalupa.eshop.model.types.ProductState;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Name("productManager")
@Scope(ScopeType.PAGE)
public class ProductManager {

	@Logger
	private Log log;

	@In(create = true)
	ProductActionLocal productAction;

	@In(create = true)
	PhotoActionLocal photoAction;

	@In(create = true)
	CartActionLocal cartAction;

	@In
	StatusMessages statusMessages;

	private Product product;

	public ProductManager() {
		product = null;
	}

	public boolean isManaged() {
		if (product != null && product.getId() != null && product.getId() > 0) {
			return true;
		}
		return false;
	}

	public Product getProduct() {
		if (product == null) {
			product = new Product();
		}
		return product;
	}

	public Long getProductId() {
		return getProduct().getId();
	}

	public void setProductId(Long id) {
		if (id != 0 && id != null) {
			product = productAction.findProduct(id);
		}
	}

	public String persist() {
		productAction.persist(product);
		return "persisted";
	}

	public String update() {
		productAction.update(product);
		return "updated";
	}

	public String remove() {
		productAction.remove(product);
		return "removed";
	}

	public SelectItem[] getProductStateValues() {
		SelectItem[] items = new SelectItem[ProductState.values().length];
		int i = 0;
		for (ProductState ps : ProductState.values()) {
			items[i++] = new SelectItem(ps, ps.getLabel());
		}
		return items;
	}

	public List<Photo> getPhotos() {
		return photoAction.getAllPhotos(product);
	}

	public String addToCart() {
		if (product != null) {
			cartAction.addProduct(product);
		}
		return "added";
	}

}
