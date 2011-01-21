package cz.cvut.fel.x33eja.chalupa.eshop.action.components;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.Redirect;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Name("loginPageRedirector")
@Scope(ScopeType.PAGE)
public class LoginPageRedirector {

	@In
	private Redirect redirect;

	private boolean isAdminLogin;

	public boolean getLoginView() {
		return isAdminLogin;
	}

	public void checkAdminLogin() {
		String view = redirect.getViewId();

		// Disable conversation propagation, otherwise a open
		// conversation exists after the login procedure.
		// redirect.setConversationPropagationEnabled(false);
		// log.info("AHOJ");
		// log.info("AHOJ");
		// log.info(view);

		if (view != null && view.contains("administration")) {
			isAdminLogin = true;
		} else {
			isAdminLogin = false;
		}
	}

}