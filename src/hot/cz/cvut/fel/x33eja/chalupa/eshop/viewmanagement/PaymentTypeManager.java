package cz.cvut.fel.x33eja.chalupa.eshop.viewmanagement;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.action.PaymentTypeActionLocal;
import cz.cvut.fel.x33eja.chalupa.eshop.model.PaymentType;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Name("paymentTypeManager")
@Scope(ScopeType.PAGE)
public class PaymentTypeManager {

	@Logger
	private Log log;

	@In(create = true)
	PaymentTypeActionLocal paymentTypeAction;

	@In
	StatusMessages statusMessages;

	private PaymentType paymentType;

	public PaymentTypeManager() {
		paymentType = null;
	}

	public boolean isManaged() {
		if (paymentType != null && paymentType.getId() != null
				&& paymentType.getId() > 0) {
			return true;
		}
		return false;
	}

	public PaymentType getPaymentType() {
		if (paymentType == null) {
			paymentType = new PaymentType();
		}
		return paymentType;
	}

	public Integer getPaymentTypeId() {
		return getPaymentType().getId();
	}

	public void setPaymentTypeId(Integer id) {
		if (id != 0 && id != null) {
			paymentType = paymentTypeAction.findPaymentType(id);
		}
	}

	public String persist() {
		paymentTypeAction.persist(paymentType);
		return "persisted";
	}

	public String update() {
		paymentTypeAction.update(paymentType);
		return "updated";
	}

	public String remove() {
		paymentTypeAction.remove(paymentType);
		return "removed";
	}

}
