package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.List;

import javax.ejb.Local;

import cz.cvut.fel.x33eja.chalupa.eshop.action.exception.FileSaveException;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Photo;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Product;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Local
public interface PhotoActionLocal {
	Photo findPhoto(long id);

	void persist(Photo photo, byte[] file) throws FileSaveException;

	void update(Photo photo);

	void remove(Photo photo);

	boolean isManaged(Photo photo);

	public List<Photo> getAllPhotos(Product product);

}
