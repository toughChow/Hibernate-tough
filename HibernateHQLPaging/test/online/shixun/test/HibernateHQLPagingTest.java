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


public class HibernateHQLPagingTest {
	
	public static void main(String[] args) {
		HibernateHQLPagingTest test = new HibernateHQLPagingTest();
		test.saveCommodity();
		// 查询第一页的 一页设置两行
		test.listCommoditiesWithPaging();
	}
	
	public void saveCommodity(){
		init();
		
		session.save(new Commodity("明博电视","家庭必备",new Date(),new Date()));
		session.save(new Commodity("创维电视","高清视频播放",new Date(),new Date()));
		session.save(new Commodity("TCL电视","高清视频播放",new Date(),new Date()));
		session.save(new Commodity("海尔电视","高清视频播放",new Date(),new Date()));
		session.save(new Commodity("康佳电视","高清视频播放",new Date(),new Date()));
		session.save(new Commodity("长虹电视","高清视频播放",new Date(),new Date()));
		destry();
	}

	public void listCommoditiesWithPaging(){
		init();
		String hql = "FROM Commodity";
		Query query = session.createQuery(hql);
		query.setMaxResults(2);
		query.setFirstResult(0);
		List<Commodity> commodities = query.list();
		for (Commodity commodity : commodities) {
			System.out.println(commodity);
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
