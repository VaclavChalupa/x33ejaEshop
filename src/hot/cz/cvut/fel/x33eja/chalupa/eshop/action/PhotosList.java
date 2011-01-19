package cz.cvut.fel.x33eja.chalupa.eshop.action;

import cz.cvut.fel.x33eja.chalupa.eshop.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("photosList")
public class PhotosList extends EntityQuery<Photo> {

	private static final String EJBQL = "select photos from Photos photos";

	private static final String[] RESTRICTIONS = {"lower(photos.name) like lower(concat(#{photosList.photos.name},'%'))",};

	private Photo photos = new Photo();

	public PhotosList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Photo getPhotos() {
		return photos;
	}
}
