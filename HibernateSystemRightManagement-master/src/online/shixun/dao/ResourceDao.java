package online.shixun.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import online.shixun.model.Resource;
import online.shixun.model.Role;
import online.shixun.model.User;
import online.shixun.util.HibernateUtils;

public class ResourceDao {

	public List<Resource> myResourceList(int userID) {
		Session session = HibernateUtils.getSession();
		User user = (User) session.get(User.class, userID);
		Set<Role> roles = user.getRoles();
		List<Resource> myResourceList = new ArrayList<>();
		for (Role role : roles) {
			Integer id = role.getRoleId();
			Role roleTemp = (Role) session.get(Role.class, id);
			Set<Resource> resources = roleTemp.getResources();
			for (Resource resource : resources) {
				myResourceList.add(resource);
			}
		}
		for (Resource resource : myResourceList) {
			System.out.println(resource);
		}
		HibernateUtils.closeSession();
		return myResourceList;
	}

	public void updateResMessage() {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		
		System.out.println("请输入要修改的资源名称:");
		
		transaction.commit();
		HibernateUtils.closeSession();
		
	}
}
