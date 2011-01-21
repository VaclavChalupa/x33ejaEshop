package cz.cvut.fel.x33eja.chalupa.eshop.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.security.management.UserRoles;

import cz.cvut.fel.formbuilder.annotation.FormRoles;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Entity
@Table(name = "administrators")
@NamedQuery(name = "Administrator.findByUsername", query = "SELECT a FROM Administrator a WHERE a.username = :username")
public class Administrator extends User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -165835638638417769L;
	private boolean archived;
	private Set<Product> products = new HashSet<Product>(0);
	private Set<Photo> photos = new HashSet<Photo>(0);
	private Set<Role> roles = new HashSet<Role>(0);

	public Administrator() {
	}

	public Administrator(boolean archived) {
		this.archived = archived;
	}

	public Administrator(boolean archived, Set<Product> products,
			Set<Photo> photos) {
		this.archived = archived;
		this.products = products;
		this.photos = photos;
	}

	@Column(name = "archived", nullable = false)
	@FormRoles({ "edit" })
	@Restrict("#{s:hasRole('admin')}")
	public boolean isArchived() {
		return this.archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "administrator")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "administrator")
	public Set<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

	@UserRoles
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "administrator_role", joinColumns = { @JoinColumn(name = "administrator") }, inverseJoinColumns = { @JoinColumn(name = "role") })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Transient
	public String getRolesAsString() {
		StringBuffer result = new StringBuffer();
		boolean isFirst = true;

		for (Role r : roles) {
			if (!isFirst) {
				result.append("_");
			} else {
				isFirst = false;
			}
			result.append(r.getRolename());
		}
		return result.toString();
	}

}
