package cz.cvut.fel.x33eja.chalupa.eshop.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class DeliveryTypeActionLocalTest extends SeamTest {

	@Test
	public void test_deliveryTypeAction() throws Exception {
		new FacesRequest("/deliveryTypeAction.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{deliveryTypeAction.deliveryTypeAction}");
			}
		}.run();
	}
}
