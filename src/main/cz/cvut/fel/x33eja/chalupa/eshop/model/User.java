package cz.cvut.fel.x33eja.chalupa.eshop.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.security.management.UserPassword;
import org.jboss.seam.annotations.security.management.UserPrincipal;

import sun.misc.BASE64Encoder;
import cz.cvut.fel.formbuilder.annotation.FormRoles;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1166118125848036621L;
	private Long id;
	private String username;
	private String password;
	private String firstname;
	private String surname;
	private String email;
	private Date inserted;
	private Date lastLogged;

	public User() {
	}

	public User(long id, String username, String password, String firstname,
			String surname, String email, Date inserted, char type) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
		this.inserted = inserted;
	}

	public User(long id, String username, String password, String firstname,
			String surname, String email, Date inserted, Date lastLogged,
			char type, RegisteredUser registeredUser,
			Administrator administrators) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.surname = surname;
		this.email = email;
		this.inserted = inserted;
		this.lastLogged = lastLogged;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	@FormRoles({ "search" })
	@Restrict("#{s:hasRole('admin')}")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "username", unique = true, nullable = false, length = 20)
	@NotNull
	@Length(max = 20)
	@UserPrincipal
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@UserPassword()
	@Column(name = "password", nullable = false)
	@NotNull
	@FormRoles({ "edit" })
	@Restrict("#{s:hasRole('admin')}")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "firstname", nullable = false, length = 50)
	@NotNull
	@Length(max = 50)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "surname", nullable = false, length = 50)
	@NotNull
	@Length(max = 50)
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "email", nullable = false)
	@NotNull
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "inserted", nullable = false, insertable = false, length = 29)
	@FormRoles({ "show" })
	public Date getInserted() {
		return this.inserted;
	}

	public void setInserted(Date inserted) {
		this.inserted = inserted;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_logged", length = 29)
	@FormRoles({ "show" })
	public Date getLastLogged() {
		return this.lastLogged;
	}

	public void setLastLogged(Date lastLogged) {
		this.lastLogged = lastLogged;
	}

	public static String generatePasswordHash(String password, String salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		digest.update(salt.getBytes("UTF-8"));
		byte[] hash = digest.digest(password.getBytes("UTF-8"));
		return (new BASE64Encoder()).encode(hash);
	}

}
