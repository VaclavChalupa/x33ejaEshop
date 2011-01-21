package cz.cvut.fel.x33eja.chalupa.eshop.viewmanagement;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import cz.cvut.fel.x33eja.chalupa.eshop.action.PhotoActionLocal;
import cz.cvut.fel.x33eja.chalupa.eshop.action.ProductActionLocal;
import cz.cvut.fel.x33eja.chalupa.eshop.action.exception.FileSaveException;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Photo;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Name("photoManager")
@Scope(ScopeType.PAGE)
public class PhotoManager {

	@Logger
	private Log log;

	@In(create = true)
	ProductActionLocal productAction;

	@In(create = true)
	PhotoActionLocal photoAction;

	@In
	StatusMessages statusMessages;

	private Photo photo;

	private byte[] file;

	public PhotoManager() {
		photo = null;
		file = null;
	}

	public boolean isManaged() {
		if (photo != null && photo.getId() != null && photo.getId() > 0) {
			return true;
		}
		return false;
	}

	public Photo getPhoto() {
		if (photo == null) {
			photo = new Photo();
		}
		return photo;
	}

	public Long getPhotoProductId() {
		if (getPhoto().getProduct() == null) {
			return null;
		}
		return getPhoto().getProduct().getId();
	}

	public void setPhotoProductId(Long id) {
		if (id != 0 && id != null) {
			if (photo == null) {
				photo = new Photo();
			}
			photo.setProduct(productAction.findProduct(id));
		}
	}

	public Long getPhotoId() {
		return getPhoto().getId();
	}

	public void setPhotoId(Long id) {
		if (id != 0 && id != null) {
			photo = photoAction.findPhoto(id);
		}
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String persist() {
		if (file == null || file.length < 2) {
			statusMessages.add(Severity.ERROR, "Choose image please");
			return null;
		}
		try {
			photoAction.persist(photo, file);
			return "persisted";
		} catch (FileSaveException e) {
			statusMessages.add(Severity.ERROR, "Uploading error");
			return null;
		}
	}

	public String update() {
		photoAction.update(photo);
		return "updated";
	}

	public String remove() {
		photoAction.remove(photo);
		return "removed";
	}

}
