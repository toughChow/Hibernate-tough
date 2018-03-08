package online.shixun.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import online.shixun.model.Order;
import online.shixun.model.User;

public class HibernateHQLSubqueryDemoTest {
	
	public static void main(String[] args) {
		HibernateHQLSubqueryDemoTest test = new HibernateHQLSubqueryDemoTest();
		test.saveUserAndOrders();
		test.listOrders();
	}
	
	public void saveUserAndOrders(){
		init();
		User user = new User("张三", "123456", new Date());
		Order o1 = new Order(1001L, new Date());
		Order o2 = new Order(1002L, new Date());
		Order o3 = new Order(1003L, new Date());
		Order o4 = new Order(1004L, new Date());
		user.getOrders().add(o1);
		user.getOrders().add(o2);
		user.getOrders().add(o3);
		user.getOrders().add(o4);
		session.save(user);
		User user2 = new User("李四","666666",new Date());
		Order o5 = new Order(1005L, new Date());
		user2.getOrders().add(o5);
		session.save(user2);
		destry();
	}
	
	public void listOrders(){
		init();
		String hql = "FROM Order o WHERE o.user.id in (SELECT id FROM User u WHERE u.id = o.user.id)";
//		String hql2 = "FROM Order o INNER JOIN User u on o.user.id = u.id";
		Query query = session.createQuery(hql);
		List<Order> list = query.list();
		for (Order order : list) {
			System.out.println(order);
		}
		destry();
	}
	

	Configuration configuration = null;
	ServiceRegistry serviceRegistry = null;
	SessionFactory sessionFactory = null;
	Session session = null;
	Transaction transaction = null;

	public void destry() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	public void init() {
		configuration = new Configuration().configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.getTransaction();
		transaction.begin();
	}
}
