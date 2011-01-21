package cz.cvut.fel.x33eja.chalupa.eshop.viewmanagement;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.action.DeliveryTypeActionLocal;
import cz.cvut.fel.x33eja.chalupa.eshop.model.DeliveryType;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Name("deliveryTypeManager")
@Scope(ScopeType.PAGE)
public class DeliveryTypeManager {

	@Logger
	private Log log;

	@In(create = true)
	DeliveryTypeActionLocal deliveryTypeAction;

	@In
	StatusMessages statusMessages;

	private DeliveryType deliveryType;

	public DeliveryTypeManager() {
		deliveryType = null;
	}

	public boolean isManaged() {
		if (deliveryType != null && deliveryType.getId() != null
				&& deliveryType.getId() > 0) {
			return true;
		}
		return false;
	}

	public DeliveryType getDeliveryType() {
		if (deliveryType == null) {
			deliveryType = new DeliveryType();
		}
		return deliveryType;
	}

	public Integer getDeliveryTypeId() {
		return getDeliveryType().getId();
	}

	public void setdeliveryTypeId(Integer id) {
		if (id != 0 && id != null) {
			deliveryType = deliveryTypeAction.findDeliveryType(id);
		}
	}

	public String persist() {
		deliveryTypeAction.persist(deliveryType);
		return "persisted";
	}

	public String update() {
		deliveryTypeAction.update(deliveryType);
		return "updated";
	}

	public String remove() {
		deliveryTypeAction.remove(deliveryType);
		return "removed";
	}

}
