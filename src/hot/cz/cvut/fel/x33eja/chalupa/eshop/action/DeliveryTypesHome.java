package cz.cvut.fel.x33eja.chalupa.eshop.action;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import cz.cvut.fel.x33eja.chalupa.eshop.model.DeliveryType;

@Name("deliveryTypesHome")
public class DeliveryTypesHome extends EntityHome<DeliveryType> {

	public void setDeliveryTypesId(Integer id) {
		setId(id);
	}

	public Integer getDeliveryTypesId() {
		return (Integer) getId();
	}

	@Override
	protected DeliveryType createInstance() {
		DeliveryType deliveryTypes = new DeliveryType();
		return deliveryTypes;
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

	public DeliveryType getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/*
	 * public List<Orders> getOrderses() { return getInstance() == null ? null :
	 * new ArrayList<Orders>( getInstance().getOrderses()); }
	 */

}
