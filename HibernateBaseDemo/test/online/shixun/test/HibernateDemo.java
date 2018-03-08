package online.shixun.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import online.shixun.model.Commodity;

public class HibernateDemo {

	Configuration configuration = null;
	ServiceRegistry serviceRegistry = null;
	SessionFactory sessionFactory = null;
	Session session = null;
	Transaction transaction = null;
	@Before
	public void beforeSession() {
		configuration = new Configuration().configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		System.out.println("数据库连接开启...");
	}

	@After
	public void afterSession() {
		transaction.commit();
		session.close();
		sessionFactory.close();
		System.out.println("数据库连接断开...");
	}
	
	/**
	 * 增加
	 */
	@Test
	public void testCreate(){
		Commodity c = new Commodity(new Date(),"第一个",new Date(),"张三");
		session.save(c);
	}
	
	/**
	 * 删除
	 */
	@Test
	public void testDelete(){
		Commodity c = (Commodity) session.load(Commodity.class,5);
		session.delete(c);
	}
	
	/**
	 * 修改
	 */
	@Test
	public void testUpdate(){
		Commodity c = new Commodity(new Date(),"修改前的第二个",new Date(),"张三");
		session.saveOrUpdate(c);
		c.setDescription("修改后的第二个");
		session.saveOrUpdate(c);
		System.out.println("------");
		Commodity c2 = (Commodity) session.load(Commodity.class, 2);
		c2.setDescription("又一次修改的第二个");
		session.saveOrUpdate(c2);
	}
	
	
	/**
	 * 查找
	 */
	@Test
	public void testRetrieve(){
		Commodity c = (Commodity) session.get(Commodity.class, 2);
		System.out.println(c.toString());
	}
}
