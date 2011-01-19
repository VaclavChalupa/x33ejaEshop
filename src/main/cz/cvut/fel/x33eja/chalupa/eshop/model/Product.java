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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import cz.cvut.fel.x33eja.chalupa.eshop.model.types.ProductState;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name = "products")
public class Product implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4026520594864848160L;
	private Long id;
	private Administrator administrators;
	private String name;
	private String description;
	private double price;
	private boolean inAction;
	private Double actionPrice;
	private ProductState state;
	private Date created;
	private Set<OrderLineItem> orderLineItems = new HashSet<OrderLineItem>(0);
	private Set<Photo> photoses = new HashSet<Photo>(0);
	private Set<RegisteredUser> registeredUsers = new HashSet<RegisteredUser>(0);

	public Product() {
	}

	public Product(Long id, Administrator administrators, String name,
			String description, double price, boolean inAction,
			ProductState state, Date created) {
		this.id = id;
		this.administrators = administrators;
		this.name = name;
		this.description = description;
		this.price = price;
		this.inAction = inAction;
		this.state = state;
		this.created = created;
	}

	public Product(Long id, Administrator administrators, String name,
			String description, double price, boolean inAction,
			Double actionPrice, ProductState state, Date created,
			Set<OrderLineItem> orderLineItems, Set<Photo> photos) {
		this.id = id;
		this.administrators = administrators;
		this.name = name;
		this.description = description;
		this.price = price;
		this.inAction = inAction;
		this.actionPrice = actionPrice;
		this.state = state;
		this.created = created;
		this.orderLineItems = orderLineItems;
		this.photoses = photos;
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
	public Administrator getAdministrators() {
		return this.administrators;
	}

	public void setAdministrators(Administrator administrators) {
		this.administrators = administrators;
	}

	@Column(name = "name", nullable = false)
	@NotNull
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = false)
	@NotNull
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "price", nullable = false, scale = 0)
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "in_action", nullable = false)
	public boolean isInAction() {
		return this.inAction;
	}

	public void setInAction(boolean inAction) {
		this.inAction = inAction;
	}

	@Column(name = "action_price", scale = 0)
	public Double getActionPrice() {
		return this.actionPrice;
	}

	public void setActionPrice(Double actionPrice) {
		this.actionPrice = actionPrice;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "state", nullable = false, length = 30)
	@NotNull
	@Length(max = 30)
	public ProductState getState() {
		return this.state;
	}

	public void setState(ProductState state) {
		this.state = state;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<OrderLineItem> getOrderLineItems() {
		return this.orderLineItems;
	}

	public void setOrderLineItems(Set<OrderLineItem> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<Photo> getPhotoses() {
		return this.photoses;
	}

	public void setPhotoses(Set<Photo> photoses) {
		this.photoses = photoses;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "registered_user_product", joinColumns = { @JoinColumn(name = "product") }, inverseJoinColumns = { @JoinColumn(name = "registered_user") })
	public Set<RegisteredUser> getRegisteredUsers() {
		return registeredUsers;
	}

	public void setRegisteredUsers(Set<RegisteredUser> registeredUsers) {
		this.registeredUsers = registeredUsers;
	}

}