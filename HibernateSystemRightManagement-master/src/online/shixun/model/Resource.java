package online.shixun.model;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

public class Resource {

	private Integer resourceId;

	private String resourceName;

	private String url;

	private Blob image;

	private String description;
	
	private Set<Role> roles = new HashSet<>();

	// 构造函数

	public Resource() {
		super();
	}

	public Resource(String resourceName, String url, Blob image, String status) {
		super();
		this.resourceName = resourceName;
		this.url = url;
		this.image = image;
		this.description = status;
	}

	// getter setter

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Resource [resourceId=" + resourceId + ", resourceName=" + resourceName + ", url=" + url + ", image="
				+ image + ", description=" + description + "]";
	}

}
