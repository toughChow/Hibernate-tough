package online.shixun.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import online.shixun.model.Role;
import online.shixun.model.User;
import online.shixun.util.HibernateUtils;

public class UserDao {

	private Session session;
	private Transaction transction;
	
	public void changePassword(int userID, String password1) {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();

		User user = (User) session.get(User.class, userID);
		user.setPassword(password1);
		session.saveOrUpdate(user);

		transaction.commit();
		HibernateUtils.closeSession();
	}

	/**
	 * 更改用户名
	 * 更改前先判断该用户名是否
	 * 若存在则返回-1 否则更新并返回1
	 */
	public int changeUsername(int userID, String username) {
		// 判斷當前用戶名是否已經存在
		String username2 = getUsername(username);
		// 為空證明數據庫中不存在該用戶名 可以正常插入
		if(username2 != null){
			return -1;
		}

		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();

		User user = (User) session.get(User.class, userID);
		user.setUsername(username);
		session.saveOrUpdate(user);

		transaction.commit();
		HibernateUtils.closeSession();
		return 1;
	}

	/**
	 * 用户登录 返回 -1 提示用户名或密码错误
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public int login(String username, String password) {
		session = HibernateUtils.getSession();
		// User user = (User)
		// session.createCriteria(User.class).add(Restrictions.eq("username",
		// username))
		// .add(Restrictions.eq("password", password)).uniqueResult();
		String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
		User user = (User) session.createQuery(hql).setString(0, username).setString(1, password).uniqueResult();
		if (user != null) {
			Integer temp = user.getUserId();
			// System.out.println("1:" + user.getUserId());
			// 死代码？？？
			// 返回當前用戶id
			return temp;
		}
		HibernateUtils.closeSession();
		return -1;
	}

	/**
	 * 用户注册 用户名已存在 -1 用户名不存在 1
	 * 
	 * @param user
	 * @return
	 */
	public int registUser(User user) {
		// 判断用户是否存在
		String judge = getUsername(user.getUsername());
		if (judge != null) {
			return -1;
		}
		insertUser(user);
		return 1;
	}

	/**
	 * 插入数据
	 * 
	 * @param user
	 */
	public void insertUser(User user) {
		// 逻辑处理
		session = HibernateUtils.getSession();
		transction = session.beginTransaction();
		Role role = (Role) session.get(Role.class, 1);
		role.getUsers().add(user);
		session.saveOrUpdate(role);
		transction.commit();
		HibernateUtils.closeSession();
	}

	/**
	 * 获取用户名 判断是否存在该用户名
	 * 
	 * @param username
	 * @return
	 */
	public String getUsername(String username) {
		session = HibernateUtils.getSession();
		User user = (User) session.createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult();
		if (user == null) {
			// 用户名为空 不存在
			return null;
		}
		HibernateUtils.closeSession();
		return user.getUsername();
	}

	public void getAllMessage() {
		session = HibernateUtils.getSession();
		
		String hql = "FROM User";
		Query query = session.createQuery(hql);
		List<User> user = query.list();
		for (User user2 : user) {
			System.out.println(user2.toStringWithRoleAndRes());
		}
		
		HibernateUtils.closeSession();
	}

	
}
