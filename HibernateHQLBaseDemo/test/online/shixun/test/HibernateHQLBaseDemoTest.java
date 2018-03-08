package online.shixun.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import online.shixun.model.Commodity;

public class HibernateHQLBaseDemoTest {

	Configuration configuration = null;
	ServiceRegistry serviceRegistry = null;
	SessionFactory sessionFactory = null;
	Session session = null;
	Transaction transaction = null;
	
	public static void main(String[] args) {
		HibernateHQLBaseDemoTest test = new HibernateHQLBaseDemoTest();
		test.saveCommodity();
		test.listCommodity();
		System.out.println("---------------");
		test.iteratorCommodity();
	}
	
	public void listCommodity(){
		init();

		String hql = "FROM Commodity c WHERE c.id < 6";
		Query query = session.createQuery(hql);
		List<Commodity> list = query.list();
		for (Commodity commodity : list) {
			System.out.println(commodity);
		}
		destry();
	}

	public void iteratorCommodity(){
		init();
		
		String hql = "FROM Commodity c WHERE c.id < 6";
		Query query = session.createQuery(hql);
		Iterator iterate = query.iterate();
		while(iterate.hasNext()){
			System.out.println(iterate.next());
		}
		
		destry();
	}
	
	public void saveCommodity() {
		init();
		Commodity commodity;
		for (int i = 0; i < 8; i++) {
			commodity = new Commodity("张三" + i, "666" + 1, new Date(), new Date());
			session.save(commodity);
		}
		destry();
	}

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
