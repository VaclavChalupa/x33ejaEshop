package cz.cvut.fel.x33eja.chalupa.eshop.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class RoleActionTest extends SeamTest {

	@Test
	public void test_roleAction() throws Exception {
		new FacesRequest("/roleAction.xhtml") {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{RoleAction.roleAction}");
			}
		}.run();
	}
}
