package cz.cvut.fel.x33eja.chalupa.eshop.action;

import javax.ejb.Local;

import cz.cvut.fel.x33eja.chalupa.eshop.model.PaymentType;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Local
public interface PaymentTypeActionLocal {

	PaymentType findPaymentType(int id);

	void persist(PaymentType paymentType);

	void update(PaymentType paymentType);

	void remove(PaymentType paymentType);

	boolean isManaged(PaymentType paymentType);

}
