package cz.cvut.fel.x33eja.chalupa.eshop.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class PhotoActionLocalTest extends SeamTest {

	@Test
	public void test_photoAction() throws Exception {
		new FacesRequest("/photoAction.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{photoAction.photoAction}");
			}
		}.run();
	}
}
