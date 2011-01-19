package cz.cvut.fel.x33eja.chalupa.eshop.action;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import cz.cvut.fel.x33eja.chalupa.eshop.model.PaymentType;

@Name("paymentTypesList")
public class PaymentTypesList extends EntityQuery<PaymentType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6666795978518689006L;

	private static final String EJBQL = "select paymentType from PaymentType paymentType";

	private static final String[] RESTRICTIONS = {
			"lower(paymentType.description) like lower(concat(#{paymentTypesList.paymentType.description},'%'))",
			"lower(paymentType.name) like lower(concat(#{paymentTypesList.paymentType.name},'%'))", };

	private final PaymentType paymentType = new PaymentType();

	public PaymentTypesList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public PaymentType getPaymentTypes() {
		return paymentType;
	}
}
