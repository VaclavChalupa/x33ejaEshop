package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import cz.cvut.fel.x33eja.chalupa.eshop.model.DeliveryType;

@Name("deliveryTypesList")
public class DeliveryTypesList extends EntityQuery<DeliveryType> {

	private static final String EJBQL = "select deliveryType from DeliveryType deliveryType";

	private static final String[] RESTRICTIONS = {
			"lower(deliveryType.description) like lower(concat(#{deliveryTypesList.deliveryType.description},'%'))",
			"lower(deliveryType.name) like lower(concat(#{deliveryTypesList.deliveryType.name},'%'))", };

	private final DeliveryType deliveryType = new DeliveryType();

	public DeliveryTypesList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DeliveryType getDeliveryType() {
		return deliveryType;
	}
}
