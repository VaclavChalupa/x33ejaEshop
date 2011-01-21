package cz.cvut.fel.x33eja.chalupa.eshop.model;

import java.math.BigDecimal;
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

import org.hibernate.validator.NotNull;

import cz.cvut.fel.x33eja.chalupa.eshop.model.types.ProductState;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Entity
@Table(name = "products")
public class Product implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4026520594864848160L;
	private Long id;
	private Administrator administrator;
	private String name;
	private String description;
	private BigDecimal price;
	private boolean inAction;
	private BigDecimal actionPrice;
	private ProductState state;
	private Date created;
	private Set<OrderLineItem> orderLineItems = new HashSet<OrderLineItem>(0);
	private Set<Photo> photoses = new HashSet<Photo>(0);
	private Set<RegisteredUser> registeredUsers = new HashSet<RegisteredUser>(0);
	private Category category;

	public Product() {
	}

	public Product(Long id, Administrator administrator, String name,
			String description, BigDecimal price, boolean inAction,
			ProductState state, Date created) {
		this.id = id;
		this.administrator = administrator;
		this.name = name;
		this.description = description;
		this.price = price;
		this.inAction = inAction;
		this.state = state;
		this.created = created;
	}

	public Product(Long id, Administrator administrator, String name,
			String description, BigDecimal price, boolean inAction,
			BigDecimal actionPrice, ProductState state, Date created,
			Set<OrderLineItem> orderLineItems, Set<Photo> photos) {
		this.id = id;
		this.administrator = administrator;
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
	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
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

	@Column(name = "price", nullable = false, precision = 2, scale = 19)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "in_action", nullable = false)
	public boolean isInAction() {
		return this.inAction;
	}

	public void setInAction(boolean inAction) {
		this.inAction = inAction;
	}

	@Column(name = "action_price", precision = 2, scale = 19)
	public BigDecimal getActionPrice() {
		return this.actionPrice;
	}

	public void setActionPrice(BigDecimal actionPrice) {
		this.actionPrice = actionPrice;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "state", nullable = false, length = 30)
	@NotNull
	public ProductState getState() {
		return this.state;
	}

	public void setState(ProductState state) {
		this.state = state;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, insertable = false, length = 29)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category", nullable = false)
	@NotNull
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
