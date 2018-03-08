package online.shixun.dao;

import java.util.Set;

import org.hibernate.Session;

import online.shixun.model.Role;
import online.shixun.model.User;
import online.shixun.util.HibernateUtils;

public class RoleDao {

	public Set<Role> getMyRoleList(int userID) {
		Session session = HibernateUtils.getSession();

		User user = (User) session.get(User.class, userID);
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			System.out.println(role);
		}
		HibernateUtils.closeSession();

		return roles;
	}

}
