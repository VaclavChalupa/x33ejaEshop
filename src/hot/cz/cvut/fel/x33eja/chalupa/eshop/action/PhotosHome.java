package cz.cvut.fel.x33eja.chalupa.eshop.action;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Administrator;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Photo;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Product;

@Name("photosHome")
public class PhotosHome extends EntityHome<Photo> {

	@In(create = true)
	AdministratorsHome administratorsHome;
	@In(create = true)
	ProductHome productHome;

	public void setPhotosId(Long id) {
		setId(id);
	}

	public Long getPhotosId() {
		return (Long) getId();
	}

	@Override
	protected Photo createInstance() {
		Photo photos = new Photo();
		return photos;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Administrator administrators = administratorsHome.getDefinedInstance();
		/*
		 * if (administrators != null) {
		 * getInstance().setAdministrators(administrators); }
		 */
		Product product = productHome.getDefinedInstance();
		if (product != null) {
			getInstance().setProduct(product);
		}
	}

	public boolean isWired() {
		/*
		 * if (getInstance().getAdministrators() == null) return false;
		 */
		if (getInstance().getProduct() == null)
			return false;
		return true;
	}

	public Photo getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
