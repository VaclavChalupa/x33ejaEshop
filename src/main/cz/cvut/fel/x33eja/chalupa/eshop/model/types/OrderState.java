package cz.cvut.fel.x33eja.chalupa.eshop.model.types;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
public enum OrderState {

	PROCESSING("processing"), SHIPPED("shipped"), DELIVERED("delivered"), CANCELLED(
			"cancelled");

	private final String label;

	OrderState(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}