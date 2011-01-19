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

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import cz.cvut.fel.x33eja.chalupa.eshop.model.Administrator;
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

	// @Out(required = true, value = "currentUser", scope = ScopeType.SESSION)
	// private CurrentUser currentUser;

	@Override
	public boolean authenticate() {

		log.info("authenticating {0}", credentials.getUsername());

		try {

			Administrator admin = (Administrator) em
					.createNamedQuery("User.findByLogin")
					.setParameter("password", credentials.getPassword())
					.setParameter("username", credentials.getUsername())
					.getSingleResult();

			currentUser = new CurrentUser(admin.getFirstname(),
					admin.getLastname());

			if (admin.getRoles() != null) {
				for (Role role : admin.getRoles()) {
					identity.addRole(role.getRolename());
					currentUser.getRoles().add(role);
				}
			}

			return true;

		} catch (Exception e) {
			log.info("Logging error: {0}", e.getMessage());
			return false;
		}

	}

	/*
	 * @Observer(Identity.EVENT_LOGGED_OUT) public void destroyCurrentUser() {
	 * log.info("AAAAAAAAAAAAAAAAAAAAA"); // currentUser = null; }
	 */

}