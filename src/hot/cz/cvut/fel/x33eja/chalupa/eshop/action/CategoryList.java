package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Category;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Name("categoryList")
public class CategoryList extends EntityQuery<Category> {

	private static final String EJBQL = "select category from Category category";

	private static final String[] RESTRICTIONS = { "lower(category.name) like lower(concat(#{categoryList.category.name},'%'))", };

	private final Category category = new Category();

	public CategoryList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Category getCategory() {
		return category;
	}
}
