package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.model.OrderLineItem;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Product;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Stateful
@Name("cartAction")
@Scope(ScopeType.SESSION)
public class CartActionBean implements CartActionLocal {
	@Logger
	private Log log;

	@In
	StatusMessages statusMessages;

	@DataModel
	private List<OrderLineItem> orderLines;

	@DataModelSelection
	private OrderLineItem selectedLine;

	@Override
	@Factory("orderLines")
	public void initAssignedProducts() {
		init();
	}

	private void init() {
		if (orderLines == null) {
			orderLines = new ArrayList<OrderLineItem>(0);
		}
	}

	@Override
	public void addProduct(Product product) {
		init();
		for (OrderLineItem o : orderLines) {
			if (o.getProduct().equals(product)) {
				o.setCount(o.getCount() + 1);
				return;
			}
		}
		OrderLineItem od = new OrderLineItem();
		od.setProduct(product);
		od.setCount(1);
		orderLines.add(od);
	}

	@Override
	public void removeProduct() {
		init();
		for (OrderLineItem o : orderLines) {
			if (o.equals(selectedLine)) {
				if (o.getCount() > 1) {
					o.setCount(o.getCount() - 1);
					return;
				}
			}
		}
		orderLines.remove(selectedLine);
	}

	@Override
	public String confirmOrder() {
		statusMessages.add(Severity.INFO, "order confirmed and saved");
		return "saved";
	}

	@Override
	@Remove
	public void destroy() {
	}

}
