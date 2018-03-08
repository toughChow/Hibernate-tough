package online.shixun.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import online.shixun.model.Order;
import online.shixun.model.User;

public class HibernateOneToManyDemo {

	Configuration configuration = null;
	Session session = null;
	SessionFactory sessionFactory = null;
	ServiceRegistry serviceRegistry = null;
	Transaction transaction = null;

	public void init() {
		configuration = new Configuration().configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	public void destroy() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	public void saveUser(){
		User user = new User(new Date(),"张三","123456");
		user.getOrders().add(new Order(1001,new Date()));
		user.getOrders().add(new Order(1002,new Date()));
		user.getOrders().add(new Order(1003,new Date()));
		user.getOrders().add(new Order(1004,new Date()));
		session.save(user);
	}
	
	public void getUser(){
		User user = (User) session.get(User.class, 1);
		System.out.println(user.toString());
	}
	
	public void deleteUser(){
		User user = (User) session.get(User.class, 1);
		session.delete(user);
	}
	
	public static void main(String[] args) {
		HibernateOneToManyDemo test = new HibernateOneToManyDemo();
		test.init(); //开启事务
		
		test.saveUser(); // 保存用户
		test.getUser(); // 获取用户
//		test.deleteUser(); // 删除用户
		
		test.destroy(); // 关闭事物
	}
}
