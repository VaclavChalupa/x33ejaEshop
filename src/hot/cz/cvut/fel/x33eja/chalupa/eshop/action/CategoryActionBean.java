package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Category;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Stateless
@Name("categoryAction")
public class CategoryActionBean implements CategoryActionLocal {
	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	@Override
	public boolean isManaged(Category category) {
		if (entityManager.contains(category)) {
			return true;
		}
		return false;
	}

	@Override
	public Category findCategory(long id) {
		Category category = entityManager.find(Category.class, id);
		return category;
	}

	@Override
	public void persist(Category category) {
		entityManager.persist(category);
	}

	@Override
	public void update(Category category) {
		entityManager.merge(category);
		entityManager.flush();
	}

	@Override
	public void remove(Category category) {
		entityManager.remove(entityManager.merge(category));
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategories() {
		return entityManager.createNamedQuery("Category.findAllCategories")
				.getResultList();
	}

}
