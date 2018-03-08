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

public class HibernateOneToManyToOneTest {

	Configuration configuration = null;
	ServiceRegistry serviceRegistry = null;
	SessionFactory sessionFactory = null;
	Session session = null;
	Transaction transaction = null;
	
	/**
	 * 初始化
	 */
	public void init(){
	
		configuration = new Configuration().configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.getTransaction();
		transaction.begin();
	}
	
	/**
	 * 事务提交
	 */
	public void destroy(){
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * 通过1方来插入n方
	 */
	public void saveUserAndOrders(){
		User user = new User("张三","123456",new Date());
		Order order1 = new Order(1001L,new Date());
		Order order2 = new Order(1002L,new Date());
		Order order3 = new Order(1003L,new Date());
		user.getOrders().add(order1);
		user.getOrders().add(order2);
		user.getOrders().add(order3);
		session.save(user);
	}
	
	public void getUserAndOrders(){
		User user = (User) session.get(User.class,1);
		System.out.println(user.toString());
	}
	
	/**
	 * 通过n方插入1方
	 */
	public void saveOrderAndUsers(){
		User user1 = new User("李四","654321",new Date());
		User user2 = new User("王五","666666",new Date());
		Order order1 = new Order(1004L, new Date());
		order1.setUser(user1);
		order1.setUser(user2);
		session.save(order1);
	}
	
	public void getOrderAndUsers(){
		Order order = (Order) session.get(Order.class, 1);
		System.out.println(order.toString());
	}
	
	public static void main(String[] args) {
		HibernateOneToManyToOneTest test = new HibernateOneToManyToOneTest();
		test.init();
	
		test.saveUserAndOrders();
		test.getUserAndOrders();
		System.out.println("------分隔符------");
		test.saveOrderAndUsers();
		test.getOrderAndUsers();
		
		test.destroy();
	}
}
