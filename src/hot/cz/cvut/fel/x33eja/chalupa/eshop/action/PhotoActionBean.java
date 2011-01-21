package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.action.exception.FileSaveException;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Administrator;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Photo;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Product;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Stateless
@Name("photoAction")
public class PhotoActionBean implements PhotoActionLocal {
	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	@In(scope = ScopeType.SESSION, required = false)
	private Administrator currentAdministrator;

	@In(value = "#{facesContext}")
	FacesContext facesContext;

	@Override
	public boolean isManaged(Photo photo) {
		if (entityManager.contains(photo)) {
			return true;
		}
		return false;
	}

	@Override
	public Photo findPhoto(long id) {
		Photo photo = entityManager.find(Photo.class, id);
		return photo;
	}

	@Override
	public void persist(Photo photo, byte[] file) throws FileSaveException {

		if (currentAdministrator == null) {
			return;
		}

		String name = ".jpg";
		short order = 0;

		try {
			Photo lastOrder = (Photo) entityManager
					.createNamedQuery("Photo.getLastOrderPhoto")
					.setParameter("product", photo.getProduct())
					.setMaxResults(1).getSingleResult();
			order = (short) (lastOrder.getOrder() + 1);
		} catch (NoResultException e) {
			order = 1;
		}

		photo.setAdministrator(currentAdministrator);
		photo.setOrder(order);
		photo.setName(name);

		entityManager.persist(photo);

		try {
			handleUpload(file, photo.getId() + name);
		} catch (IOException e) {
			log.info(e.getMessage());
			// rollback
			throw new FileSaveException("Uploud Failded");
		}
	}

	private void handleUpload(byte[] file, String name) throws IOException {
		String path = ((ServletContext) facesContext.getExternalContext()
				.getContext()).getRealPath("/photos");
		File f = new File(path, name);
		log.info(f);

		FileOutputStream fout = new FileOutputStream(f);
		fout.write(file);
		fout.flush();
		fout.close();
	}

	@Override
	public void update(Photo photo) {
		log.info("ASSS" + photo.toString());
		entityManager.merge(photo);
		entityManager.flush();
	}

	@Override
	public void remove(Photo photo) {
		entityManager.remove(entityManager.merge(photo));
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> getAllPhotos(Product product) {
		return entityManager.createNamedQuery("Photo.findAllPhotos")
				.setParameter("product", product).getResultList();
	}

}
