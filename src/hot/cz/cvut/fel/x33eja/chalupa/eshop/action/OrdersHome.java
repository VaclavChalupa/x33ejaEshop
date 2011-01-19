package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import cz.cvut.fel.x33eja.chalupa.eshop.model.DeliveryType;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Order;
import cz.cvut.fel.x33eja.chalupa.eshop.model.OrderLineItem;
import cz.cvut.fel.x33eja.chalupa.eshop.model.PaymentType;
import cz.cvut.fel.x33eja.chalupa.eshop.model.RegisteredUser;

@Name("ordersHome")
public class OrdersHome extends EntityHome<Order> {

	@In(create = true)
	DeliveryTypesHome deliveryTypesHome;
	@In(create = true)
	PaymentTypesHome paymentTypesHome;
	@In(create = true)
	RegisteredUserHome registeredUserHome;

	public void setOrdersId(Long id) {
		setId(id);
	}

	public Long getOrdersId() {
		return (Long) getId();
	}

	@Override
	protected Order createInstance() {
		Order orders = new Order();
		return orders;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		DeliveryType deliveryTypes = deliveryTypesHome.getDefinedInstance();
		if (deliveryTypes != null) {
			getInstance().setDeliveryType(deliveryTypes);
		}
		PaymentType paymentTypes = paymentTypesHome.getDefinedInstance();
		if (paymentTypes != null) {
			getInstance().setPaymentType(paymentTypes);
		}
		RegisteredUser registeredUser = registeredUserHome.getDefinedInstance();
		if (registeredUser != null) {
			getInstance().setRegisteredUser(registeredUser);
		}
	}

	public boolean isWired() {
		if (getInstance().getDeliveryType() == null)
			return false;
		if (getInstance().getPaymentType() == null)
			return false;
		if (getInstance().getRegisteredUser() == null)
			return false;
		return true;
	}

	public Order getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<OrderLineItem> getOrderLineItems() {
		return getInstance() == null ? null : new ArrayList<OrderLineItem>(
				getInstance().getOrderLineItems());
	}

}
