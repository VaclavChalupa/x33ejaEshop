package cz.cvut.fel.x33eja.chalupa.eshop.model;

// Generated Jan 17, 2011 2:57:03 PM by Hibernate Tools 3.2.4.GA

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * Roles generated by hbm2java
 */
@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = "rolename"))
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

}
