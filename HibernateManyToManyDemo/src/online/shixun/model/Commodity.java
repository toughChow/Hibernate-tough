package online.shixun.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "m2m_commodity")
public class Commodity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;

	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="m2m_temp",
		joinColumns = { @JoinColumn(name="commodity_id")},
		inverseJoinColumns={@JoinColumn(name="order_id")}
			)
	private Set<Order> orders = new HashSet<>();
	
	public Commodity() {
		super();
	}

	public Commodity(String description, Date createDate, Date modifyDate) {
		super();
		this.description = description;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public String toStringCommodityAndOrders() {
		return "Commodity [id=" + id + ", description=" + description + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", orders=" + orders + "]";
	}

	@Override
	public String toString() {
		return "Commodity [id=" + id + ", description=" + description + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + "]";
	}
	
	

	
}
