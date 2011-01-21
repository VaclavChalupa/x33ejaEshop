package cz.cvut.fel.x33eja.chalupa.eshop.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class CartActionLocalTest extends SeamTest {

	@Test
	public void test_cartAction() throws Exception {
		new FacesRequest("/cartAction.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{cartAction.cartAction}");
			}
		}.run();
	}
}
