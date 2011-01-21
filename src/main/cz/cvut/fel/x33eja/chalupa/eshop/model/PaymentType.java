package cz.cvut.fel.x33eja.chalupa.eshop.model;

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
@Table(name = "payment_types", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class PaymentType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3500700583670323621L;
	private Integer id;
	private String name;
	private String description;
	private Set<Order> orderses = new HashSet<Order>(0);

	public PaymentType() {
	}

	public PaymentType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public PaymentType(Integer id, String name, String description,
			Set<Order> orderses) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.orderses = orderses;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentType")
	public Set<Order> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Order> orderses) {
		this.orderses = orderses;
	}

}
