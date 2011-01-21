package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Order;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Name("ordersList")
public class OrdersList extends EntityQuery<Order> {

	private static final String EJBQL = "select orders from Orders orders";

	private static final String[] RESTRICTIONS = { "lower(orders.description) like lower(concat(#{ordersList.orders.description},'%'))", };

	private final Order orders = new Order();

	public OrdersList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Order getOrders() {
		return orders;
	}
}
