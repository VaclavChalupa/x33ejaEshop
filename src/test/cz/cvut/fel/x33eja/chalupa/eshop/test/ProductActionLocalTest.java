package cz.cvut.fel.x33eja.chalupa.eshop.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class ProductActionLocalTest extends SeamTest {

	@Test
	public void test_productAction() throws Exception {
		new FacesRequest("/productAction.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{productAction.productAction}");
			}
		}.run();
	}
}
