package cz.cvut.fel.x33eja.chalupa.eshop.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class AdministratorActionLocalTest extends SeamTest {

	@Test
	public void test_administratorAction() throws Exception {
		new FacesRequest("/administratorAction.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{administratorAction.administratorAction}");
			}
		}.run();
	}
}
