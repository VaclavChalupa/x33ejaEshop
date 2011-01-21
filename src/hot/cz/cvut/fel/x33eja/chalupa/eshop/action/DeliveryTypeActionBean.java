package cz.cvut.fel.x33eja.chalupa.eshop.action;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.model.DeliveryType;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Stateless
@Name("deliveryTypeAction")
public class DeliveryTypeActionBean implements DeliveryTypeActionLocal {
	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	@Override
	public boolean isManaged(DeliveryType deliveryType) {
		if (entityManager.contains(deliveryType)) {
			return true;
		}
		return false;
	}

	@Override
	public DeliveryType findDeliveryType(int id) {
		DeliveryType deliveryType = entityManager.find(DeliveryType.class, id);
		return deliveryType;
	}

	@Override
	public void persist(DeliveryType deliveryType) {
		entityManager.persist(deliveryType);
	}

	@Override
	public void update(DeliveryType deliveryType) {
		entityManager.merge(deliveryType);
		entityManager.flush();
	}

	@Override
	public void remove(DeliveryType deliveryType) {
		entityManager.remove(entityManager.merge(deliveryType));
		entityManager.flush();
	}

}
