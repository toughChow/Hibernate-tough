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
@Table(name = "commodity")
public class Commodity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Temporal(TemporalType.TIME)
	@Column(name = "createTime")
	private Date createTime;

	@Column(name = "description")
	private String description;

	@Column(name = "modifyDate")
	@Temporal(TemporalType.DATE)
	private Date modifyDate;

	@Column(name = "name")
	private String name;

	public Commodity() {
		super();
	}

	public Commodity(Date createTime, String description, Date modifyDate, String name) {
		super();
		this.createTime = createTime;
		this.description = description;
		this.modifyDate = modifyDate;
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Commodity [id=" + id + ", createTime=" + createTime + ", description=" + description + ", modifyDate="
				+ modifyDate + ", name=" + name + "]";
	}

}
