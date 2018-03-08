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

import online.shixun.model.Commodity;

public class HibernateHQLConditionDemoTest {
	
	public static void main(String[] args) {
		HibernateHQLConditionDemoTest test = new HibernateHQLConditionDemoTest();
		test.saveCommodity();
		test.listCommodity();
		test.queryFromNamedHQL();
	}
	
	public void saveCommodity(){
		init();
		session.save(new Commodity("名博电视","家庭必备",new Date(),new Date()));
		session.save(new Commodity("广角电视","高清视频播放",new Date(),new Date()));
		session.save(new Commodity("毛巾","毛巾擦脸的工具",new Date(),new Date()));
		destry();
	}
	
	public void queryFromNamedHQL(){
		init();
		
		Query query = session.getNamedQuery("queryMop");
		query.setString("name", "%毛巾%");
		query.setInteger("id", 4);
		List<Commodity> list = query.list();
		for (Commodity commodity : list) {
			System.out.println(commodity);
		}
		destry();
	}
	
	public void listCommodity(){
		init();
		String hql = "FROM Commodity c WHERE c.name LIKE ?";
		
		Query query = session.createQuery(hql);
		query.setString(0, "%电视%");
		List<Commodity> list = query.list();
		destry();
		for(Commodity c : list){
			System.out.println(c);
		}
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
