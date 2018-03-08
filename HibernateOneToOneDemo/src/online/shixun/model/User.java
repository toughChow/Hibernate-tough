package online.shixun.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "o2o_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String loginName;
	
	private String password;
	
	@Temporal(TemporalType.DATE)
	private Date createTime;
	
//	@PrimaryKeyJoinColumn
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="user")
	private MembershipCard membershipCard;

	public User() {
		super();
	}

	public User(String loginName, String password, Date createTime) {
		super();
		this.loginName = loginName;
		this.password = password;
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public MembershipCard getMembershipCard() {
		return membershipCard;
	}

	public void setMembershipCard(MembershipCard membershipCard) {
		this.membershipCard = membershipCard;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", password=" + password + ", createTime=" + createTime
				+ "]";
	}
	
}
