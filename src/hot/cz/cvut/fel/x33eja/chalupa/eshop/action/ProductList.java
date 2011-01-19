package cz.cvut.fel.x33eja.chalupa.eshop.action;

import cz.cvut.fel.x33eja.chalupa.eshop.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("productList")
public class ProductList extends EntityQuery<Product> {

	private static final String EJBQL = "select product from Product product";

	private static final String[] RESTRICTIONS = {
			"lower(product.description) like lower(concat(#{productList.product.description},'%'))",
			"lower(product.name) like lower(concat(#{productList.product.name},'%'))",};

	private Product product = new Product();

	public ProductList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Product getProduct() {
		return product;
	}
}
