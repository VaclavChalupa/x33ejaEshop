package cz.cvut.fel.x33eja.chalupa.eshop.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = "rolename"))
@NamedQuery(name = "Role.findAllRoles", query = "SELECT r FROM Role r")
public class Role implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8497009923366070436L;
	private Integer id;
	private String rolename;
	private Set<Administrator> administrators = new HashSet<Administrator>(0);

	public Role() {
	}

	public Role(Integer id, String rolename) {
		this.id = id;
		this.rolename = rolename;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "rolename", unique = true, nullable = false, length = 50)
	@NotNull
	@Length(max = 50)
	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "administrator_role", joinColumns = { @JoinColumn(name = "role") }, inverseJoinColumns = { @JoinColumn(name = "administrator") })
	public Set<Administrator> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(Set<Administrator> administrators) {
		this.administrators = administrators;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
