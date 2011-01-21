package cz.cvut.fel.x33eja.chalupa.eshop.action;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.model.PaymentType;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Stateless
@Name("paymentTypeAction")
public class PaymentTypeActionBean implements PaymentTypeActionLocal {
	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	@Override
	public boolean isManaged(PaymentType paymentType) {
		if (entityManager.contains(paymentType)) {
			return true;
		}
		return false;
	}

	@Override
	public PaymentType findPaymentType(int id) {
		PaymentType paymentType = entityManager.find(PaymentType.class, id);
		return paymentType;
	}

	@Override
	public void persist(PaymentType paymentType) {
		entityManager.persist(paymentType);
	}

	@Override
	public void update(PaymentType paymentType) {
		entityManager.merge(paymentType);
		entityManager.flush();
	}

	@Override
	public void remove(PaymentType paymentType) {
		entityManager.remove(entityManager.merge(paymentType));
		entityManager.flush();
	}

}
