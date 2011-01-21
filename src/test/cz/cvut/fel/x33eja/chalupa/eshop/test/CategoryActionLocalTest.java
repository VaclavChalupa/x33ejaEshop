package cz.cvut.fel.x33eja.chalupa.eshop.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class CategoryActionLocalTest extends SeamTest {

	@Test
	public void test_categoryAction() throws Exception {
		new FacesRequest("/categoryAction.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{categoryAction.categoryAction}");
			}
		}.run();
	}
}
