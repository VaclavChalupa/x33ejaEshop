package cz.cvut.fel.x33eja.chalupa.eshop.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "delivery_types", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class DeliveryType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5584065572491292491L;
	private Integer id;
	private String name;
	private String description;
	private BigDecimal price;
	private Set<Order> orders = new HashSet<Order>(0);

	public DeliveryType() {
	}

	public DeliveryType(Integer id, String name, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public DeliveryType(Integer id, String name, String description,
			BigDecimal price, Set<Order> orderses) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.orders = orderses;
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

	@Column(name = "name", unique = true, nullable = false, length = 100)
	@NotNull
	@Length(max = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "price", nullable = false, precision = 2, scale = 19)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "deliveryType")
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
