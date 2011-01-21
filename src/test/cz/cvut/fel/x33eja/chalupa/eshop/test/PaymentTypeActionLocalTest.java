package cz.cvut.fel.x33eja.chalupa.eshop.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class PaymentTypeActionLocalTest extends SeamTest {

	@Test
	public void test_paymentTypeAction() throws Exception {
		new FacesRequest("/paymentTypeAction.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{paymentTypeAction.paymentTypeAction}");
			}
		}.run();
	}
}
