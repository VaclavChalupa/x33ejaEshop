package cz.cvut.fel.x33eja.chalupa.eshop.action;

import cz.cvut.fel.x33eja.chalupa.eshop.model.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("paymentTypesHome")
public class PaymentTypesHome extends EntityHome<PaymentType> {

	public void setPaymentTypesId(Integer id) {
		setId(id);
	}

	public Integer getPaymentTypesId() {
		return (Integer) getId();
	}

	@Override
	protected PaymentType createInstance() {
		PaymentType paymentTypes = new PaymentType();
		return paymentTypes;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public PaymentType getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Order> getOrderses() {
		return getInstance() == null ? null : new ArrayList<Order>(
				getInstance().getOrderses());
	}

}
