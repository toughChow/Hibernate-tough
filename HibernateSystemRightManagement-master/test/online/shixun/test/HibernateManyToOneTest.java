package online.shixun.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


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
		System.out.println("begin");
	}

	public void destroy() {
		transaction.commit();
		session.close();
		sessionFactory.close();
		System.out.println("close");
	}


	public static void main(String[] args) {
		HibernateManyToOneTest test = new HibernateManyToOneTest();
		test.init();
		
		test.destroy();


	}
}
