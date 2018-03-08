package online.shixun.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {

	private static SessionFactory sessionFactory;
	
	private HibernateUtils(){};
	
	public static Session getSession() {
//		if(sessionFactory.isClosed() || sessionFactory == null){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//		}
		return sessionFactory.openSession();
	}

	public static void closeSession() {
		getSession().close();
		sessionFactory.close();
	}
}
