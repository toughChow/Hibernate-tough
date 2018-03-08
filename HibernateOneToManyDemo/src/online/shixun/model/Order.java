package online.shixun.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "OTM_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;

	@Column(name = "m_code")
	private long code;

	@Temporal(TemporalType.DATE)
	@Column(name = "m_createDate")
	private Date createDate;

	public Order() {
		super();
	}

	public Order(long code, Date createDate) {
		super();
		this.code = code;
		this.createDate = createDate;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", code=" + code + ", createDate=" + createDate + "]";
	}

}
