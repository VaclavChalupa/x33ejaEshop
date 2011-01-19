package cz.cvut.fel.x33eja.chalupa.eshop.action;

import cz.cvut.fel.x33eja.chalupa.eshop.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("orderLineItemList")
public class OrderLineItemList extends EntityQuery<OrderLineItem> {

	private static final String EJBQL = "select orderLineItem from OrderLineItem orderLineItem";

	private static final String[] RESTRICTIONS = {};

	private OrderLineItem orderLineItem = new OrderLineItem();

	public OrderLineItemList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public OrderLineItem getOrderLineItem() {
		return orderLineItem;
	}
}
