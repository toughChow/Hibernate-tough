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
@Table(name = "mto_status")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int status_id;

	@Column(name = "statusName")
	private String name;

	@Column(name = "createTime")
	@Temporal(TemporalType.DATE)
	private Date createTime;

	public Status(String name, Date createTime) {
		super();
		this.name = name;
		this.createTime = createTime;
	}

	public Status() {
		super();
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Status [status_id=" + status_id + ", name=" + name + ", createTime=" + createTime + "]";
	}

	
}
