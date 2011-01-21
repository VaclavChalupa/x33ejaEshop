package cz.cvut.fel.x33eja.chalupa.eshop.action;

import javax.ejb.Local;

import cz.cvut.fel.x33eja.chalupa.eshop.model.DeliveryType;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Local
public interface DeliveryTypeActionLocal {

	DeliveryType findDeliveryType(int id);

	void persist(DeliveryType deliveryType);

	void update(DeliveryType deliveryType);

	void remove(DeliveryType deliveryType);

	boolean isManaged(DeliveryType deliveryType);

}
