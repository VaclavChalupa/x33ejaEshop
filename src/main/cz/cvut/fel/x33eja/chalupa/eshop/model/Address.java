package cz.cvut.fel.x33eja.chalupa.eshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Entity
@Table(name = "addresses")
public class Address implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8506451554974931733L;
	private Long id;
	private String street;
	private String houseNumber;
	private String city;
	private String postCode;
	private String country;

	public Address() {
	}

	public Address(Long id, String houseNumber, String city, String postCode,
			String country) {
		this.id = id;
		this.houseNumber = houseNumber;
		this.city = city;
		this.postCode = postCode;
		this.country = country;
	}

	public Address(Long id, String street, String houseNumber, String city,
			String postCode, String country) {
		this.id = id;
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
		this.postCode = postCode;
		this.country = country;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "street")
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Column(name = "house_number", nullable = false, length = 50)
	@NotNull
	@Length(max = 50)
	public String getHouseNumber() {
		return this.houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	@Column(name = "city", nullable = false, length = 50)
	@NotNull
	@Length(max = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "post_code", nullable = false, length = 10)
	@NotNull
	@Length(max = 10)
	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Column(name = "country", nullable = false, length = 100)
	@NotNull
	@Length(max = 100)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
