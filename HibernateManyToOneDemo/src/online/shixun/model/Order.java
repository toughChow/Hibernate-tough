package online.shixun.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mto_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;

	@Column(name = "code")
	private long code;

	@Column(name = "createTime")
	@Temporal(TemporalType.DATE)
	private Date createTime;

	// 多对一
	@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;

	public Order() {
		super();
	}

	public Order(long code, Date createTime, Status status) {
		super();
		this.code = code;
		this.createTime = createTime;
		this.status = status;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", code=" + code + ", createTime=" + createTime + ", status=" + status
				+ "]";
	}
	
	

}
