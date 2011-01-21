package cz.cvut.fel.x33eja.chalupa.eshop.viewmanagement;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.action.CategoryActionLocal;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Category;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Name("categoryManager")
@Scope(ScopeType.PAGE)
public class CategoryManager {

	@Logger
	private Log log;

	@In(create = true)
	CategoryActionLocal categoryAction;

	@In
	StatusMessages statusMessages;

	private Category category;

	public CategoryManager() {
		category = null;
	}

	public boolean isManaged() {
		if (category != null && category.getId() != null
				&& category.getId() > 0) {
			return true;
		}
		return false;
	}

	public Category getCategory() {
		if (category == null) {
			category = new Category();
		}
		return category;
	}

	public Long getCategoryId() {
		return getCategory().getId();
	}

	public void setCategoryId(Long id) {
		if (id != 0 && id != null) {
			category = categoryAction.findCategory(id);
		}
	}

	public String persist() {
		categoryAction.persist(category);
		return "persisted";
	}

	public String update() {
		categoryAction.update(category);
		return "updated";
	}

	public String remove() {
		categoryAction.remove(category);
		return "removed";
	}

}
