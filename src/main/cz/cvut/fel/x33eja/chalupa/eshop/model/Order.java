package cz.cvut.fel.x33eja.chalupa.eshop.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import cz.cvut.fel.x33eja.chalupa.eshop.model.types.OrderState;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Entity
@Table(name = "orders")
public class Order implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8585961297703848803L;
	private Long id;
	private RegisteredUser registeredUser;
	private PaymentType paymentType;
	private DeliveryType deliveryType;
	private Date created;
	private String description;
	private OrderState state;
	private Set<OrderLineItem> orderLineItems = new HashSet<OrderLineItem>(0);

	public Order() {
	}

	public Order(Long id, RegisteredUser registeredUser,
			PaymentType paymentType, DeliveryType deliveryType, Date created,
			OrderState state) {
		this.id = id;
		this.registeredUser = registeredUser;
		this.paymentType = paymentType;
		this.deliveryType = deliveryType;
		this.created = created;
		this.state = state;
	}

	public Order(Long id, RegisteredUser registeredUser,
			PaymentType paymentTypes, DeliveryType deliveryTypes, Date created,
			String description, OrderState state,
			Set<OrderLineItem> orderLineItems) {
		this.id = id;
		this.registeredUser = registeredUser;
		this.paymentType = paymentTypes;
		this.deliveryType = deliveryTypes;
		this.created = created;
		this.description = description;
		this.state = state;
		this.orderLineItems = orderLineItems;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	@NotNull
	public RegisteredUser getRegisteredUser() {
		return this.registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_type", nullable = false)
	@NotNull
	public PaymentType getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_type", nullable = false)
	@NotNull
	public DeliveryType getDeliveryType() {
		return this.deliveryType;
	}

	public void setDeliveryType(DeliveryType deliveryTypes) {
		this.deliveryType = deliveryTypes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, insertable = false, length = 29)
	@NotNull
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "state", nullable = false, length = 30)
	@NotNull
	@Length(max = 30)
	public OrderState getState() {
		return this.state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<OrderLineItem> getOrderLineItems() {
		return this.orderLineItems;
	}

	public void setOrderLineItems(Set<OrderLineItem> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}

}
