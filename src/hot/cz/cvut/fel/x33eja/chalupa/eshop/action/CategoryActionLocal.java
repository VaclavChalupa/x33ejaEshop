package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.List;

import javax.ejb.Local;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Category;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Local
public interface CategoryActionLocal {

	Category findCategory(long id);

	void persist(Category category);

	void update(Category category);

	void remove(Category category);

	boolean isManaged(Category category);

	public List<Category> getAllCategories();

}
