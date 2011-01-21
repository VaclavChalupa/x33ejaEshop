package cz.cvut.fel.x33eja.chalupa.eshop.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * 
 * @author Vaclav Chalupa (vac.chalupa@gmail.com)
 * @version 1.0
 * 
 */
@Entity
@Table(name = "photos")
@NamedQueries({
		@NamedQuery(name = "Photo.findAllPhotos", query = "SELECT p FROM Photo p WHERE p.product = :product"),
		@NamedQuery(name = "Photo.getLastOrderPhoto", query = "SELECT p FROM Photo p WHERE p.product = :product ORDER BY p.order DESC LIMIT 1") })
public class Photo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5802721580852825966L;
	private Long id;
	private Administrator administrator;
	private Product product;
	private String name;
	private String title;
	private short order;
	private Date created;

	public Photo() {
	}

	public Photo(Long id, Administrator administrator, Product product,
			String name, short order, Date created) {
		this.id = id;
		this.administrator = administrator;
		this.product = product;
		this.name = name;
		this.order = order;
		this.created = created;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product", nullable = false)
	@NotNull
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "name", nullable = false, length = 100)
	@NotNull
	@Length(max = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "title", nullable = true, length = 100)
	@NotNull
	@Length(max = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "ph_order", nullable = false)
	public short getOrder() {
		return this.order;
	}

	public void setOrder(short order) {
		this.order = order;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, insertable = false, length = 29)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
