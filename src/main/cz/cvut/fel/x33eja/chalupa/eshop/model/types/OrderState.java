package cz.cvut.fel.x33eja.chalupa.eshop.model.types;

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