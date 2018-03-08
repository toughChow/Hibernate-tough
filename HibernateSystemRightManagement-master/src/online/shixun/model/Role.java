package online.shixun.model;

import java.util.HashSet;
import java.util.Set;

public class Role {

	private Integer roleId;

	private String roleName;

	private String description;

	private String status;

	private Set<Resource> resources = new HashSet<>();
	
	private Set<User> users = new HashSet<>();

	// constructor

	public Role() {
		super();
	}

	public Role(String roleName, String description, String status) {
		super();
		this.roleName = roleName;
		this.description = description;
		this.status = status;
	}

	// getter setter

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [roleName=" + roleName + ", resources=" + resources + "]";
	}

	

	
}
