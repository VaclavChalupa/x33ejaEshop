package cz.cvut.fel.x33eja.chalupa.eshop.action;

import cz.cvut.fel.x33eja.chalupa.eshop.model.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("orderLineItemHome")
public class OrderLineItemHome extends EntityHome<OrderLineItem> {

	@In(create = true)
	OrdersHome ordersHome;
	@In(create = true)
	ProductHome productHome;

	public void setOrderLineItemId(Long id) {
		setId(id);
	}

	public Long getOrderLineItemId() {
		return (Long) getId();
	}

	@Override
	protected OrderLineItem createInstance() {
		OrderLineItem orderLineItem = new OrderLineItem();
		return orderLineItem;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Order orders = ordersHome.getDefinedInstance();
		if (orders != null) {
			getInstance().setOrders(orders);
		}
		Product product = productHome.getDefinedInstance();
		if (product != null) {
			getInstance().setProduct(product);
		}
	}

	public boolean isWired() {
		if (getInstance().getOrders() == null)
			return false;
		if (getInstance().getProduct() == null)
			return false;
		return true;
	}

	public OrderLineItem getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
