package cz.cvut.fel.x33eja.chalupa.eshop.model.types;

public enum ProductState {

	IN_STOCK("in stock"), OUT_OF_STOCK("out of stock");

	private final String label;

	ProductState(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}