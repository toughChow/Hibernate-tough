package online.shixun.action;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import online.shixun.model.Role;
import online.shixun.model.User;
import online.shixun.util.HibernateUtils;

public class SystemRightManagment {

	private static int userID; // 用户id
	private static int num = 0; // 選擇系統入口

	public static void main(String args[]) {
		while (true) {
			switch (num) {
			case 0:
				userID = VisitorBlock.visitor(); // 普通游客 返回值num為當前用戶id
				getUserRole();
				break;
			case 1: // 普通用戶
				NormalBlock.normalUser(userID);
				break;
			case 2: // 系統管理
				AdminBlock.adminUser(userID);
				break;
			default:
				break;
			}
			
		}
	}
	
	/**
	 * 根据num获取当前用户最高权限
	 */
	public static void getUserRole(){
		int temp = -1; // 存储最高权限
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		User user = (User) session.get(User.class, userID);
		Set<Role> roles = user.getRoles();
		for (Role role : roles) {
			if(role.getRoleId() > temp ){
				temp = role.getRoleId();
			}
		}
		num = temp;
		transaction.commit();
		HibernateUtils.closeSession();
	}

}
