package online.shixun.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import online.shixun.model.Order;
import online.shixun.model.Status;

public class HibernateManyToOneTest {
	Configuration configuration = null;
	ServiceRegistry serviceRegistry = null;
	SessionFactory sessionFactory = null;
	Session session = null;
	Transaction transaction = null;

	public void init() {
		configuration = new Configuration().configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.getTransaction();
		transaction.begin(); // 开启事务
	}

	public void destroy() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	/**
	 * 保存order
	 */
	public void saveOrder() {
		Status status = new Status("已发货", new Date());
		for (int i = 0; i < 5; i++) {
			Order order = new Order(1001+i, new Date(),status);
			session.save(order);
		}
	}

	/**
	 * 获取order
	 */
	public void getOrder() {
		Order order = (Order) session.load(Order.class, 4);
		System.out.println(order.toString());
	}

	public static void main(String[] args) {
		HibernateManyToOneTest test = new HibernateManyToOneTest();
		test.init();// 初始化session

//		 test.saveOrder();

		test.getOrder();

		test.destroy();// 提交事务
	}
}
