/**
 * Copyright (c) 2010 Vaclav Chalupa (vac.chalupa@gmail.com)
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package cz.cvut.fel.x33eja.chalupa.eshop.action;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Administrator;
import cz.cvut.fel.x33eja.chalupa.eshop.model.RegisteredUser;
import cz.cvut.fel.x33eja.chalupa.eshop.model.Role;

/**
 * Authenticator, stateless
 * 
 * Note: edited: 18.1.2011
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Stateless
@Name("authenticator")
public class AuthenticatorBean implements Authenticator {
	@Logger
	private Log log; // seam logger

	@PersistenceContext
	private EntityManager em;

	@In
	Identity identity;

	@In
	Credentials credentials; // form security - return username, passwd

	@Out(required = false, scope = ScopeType.SESSION)
	private Administrator currentAdministrator;

	@Out(required = false, scope = ScopeType.SESSION)
	private RegisteredUser currentUser;

	@Override
	public boolean authenticate() {

		log.info("authenticating {0}", credentials.getUsername());

		String authenticationType = (String) Contexts.getPageContext().get(
				"authenticationType");

		try {

			if (authenticationType != null
					&& authenticationType.equals("admin")) {
				// admin login
				Administrator admin = (Administrator) em
						.createNamedQuery("Administrator.findByUsername")
						.setParameter("username", credentials.getUsername())
						.getSingleResult();

				String hp = Administrator.generatePasswordHash(
						credentials.getPassword(), admin.getUsername());

				log.info("HESLA {0} - {1}", hp, admin.getPassword());

				if (!hp.equals(admin.getPassword())) {
					return false;
				}

				// outject
				currentAdministrator = admin;

				if (admin.getRoles() != null) {
					for (Role role : admin.getRoles()) {
						identity.addRole(role.getRolename());
					}
				}

			} else {
				if (!credentials.getUsername().equals("user")
						|| !credentials.getPassword().equals("test")) {
					return false;
				}

				currentUser = new RegisteredUser();
				currentUser.setEmail("user@test.com");
				currentUser.setFirstname("jmeno");
				currentUser.setSurname("prijmeni");
				currentUser.setUsername("user");
				identity.addRole("customer");

			}

			return true;

		} catch (Exception e) {
			log.info("Logging error: {0}", e.getMessage());
			return false;
		}

	}

	public void setAdminAuthentizationType() {
		Contexts.getConversationContext().set("authentizationType", 1);
	}

	public void setUserAuthentizationType() {
		Contexts.getConversationContext().set("authentizationType", 0);
	}

	/*
	 * @Observer(Identity.EVENT_LOGGED_OUT) public void destroyCurrentUser() {
	 * log.info("AAAAAAAAAAAAAAAAAAAAA"); // currentUser = null; }
	 */

}