package cz.cvut.fel.x33eja.chalupa.eshop.model.types;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
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