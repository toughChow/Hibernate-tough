package online.shixun.model;

import java.util.HashSet;
import java.util.Set;

public class User {

	private Integer userId;

	private String username;

	private String password;

	private String status;
	
	private Set<Role> roles = new HashSet<>();

	// constructor

	public User() {
		super();
	}

	public User(String username, String password, String status) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
	}

	// getter setter

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", status=" + status
				+ "]";
	}

	public String toStringWithRoleAndRes() {
		return "User [username=" + username + ", roles=" + roles + "]";
	}

	
	
	
	
}
