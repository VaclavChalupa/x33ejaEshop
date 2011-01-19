package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Administrator;
import cz.cvut.fel.x33eja.chalupa.eshop.model.OrderLineItem;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Photo;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Product;

@Name("productHome")
public class ProductHome extends EntityHome<Product> {

	@In(create = true)
	AdministratorsHome administratorsHome;

	public void setProductId(Long id) {
		setId(id);
	}

	public Long getProductId() {
		return (Long) getId();
	}

	@Override
	protected Product createInstance() {
		Product product = new Product();
		return product;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Administrator administrators = administratorsHome.getDefinedInstance();
		if (administrators != null) {
			getInstance().setAdministrators(administrators);
		}
	}

	public boolean isWired() {
		if (getInstance().getAdministrators() == null)
			return false;
		return true;
	}

	public Product getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<OrderLineItem> getOrderLineItems() {
		return getInstance() == null ? null : new ArrayList<OrderLineItem>(
				getInstance().getOrderLineItems());
	}

	public List<Photo> getPhotoses() {
		return getInstance() == null ? null : new ArrayList<Photo>(
				getInstance().getPhotoses());
	}
	/*
	 * public List<RegisteredUserProduct> getRegisteredUserProducts() { return
	 * getInstance() == null ? null : new
	 * ArrayList<RegisteredUserProduct>(getInstance()
	 * .getRegisteredUserProducts()); }
	 */

}
