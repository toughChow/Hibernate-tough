package online.shixun.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import online.shixun.model.Commodity;
import online.shixun.model.Order;

public class HibernateManyToManyTest {

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
	
	public void saveOrderAndCommodities(){
		init();
		Order order = new Order(1001L,new Date());
		Commodity commodity = new Commodity("生活用品", new Date(), new Date());
		Commodity commodity2 = new Commodity("运动器材", new Date(), new Date());
		Commodity commodity3 = new Commodity("学习用品", new Date(), new Date());
		order.getCommodities().add(commodity);
		order.getCommodities().add(commodity2);
		order.getCommodities().add(commodity3);
		session.save(order);
		destroy();
	}
	
	
	public void getOrderAndCommodities(){
		init();
		Order order = (Order)session.get(Order.class, 1);
		System.out.println(order.toStringOrderAndCommodities());
		destroy();
	}
	
	public void saveCommodityAndOrders(){
		init();
		Commodity commodity = new Commodity("生活用品", new Date(), new Date());
		Order order1 = new Order(1002L,new Date(19940919));
		Order order2 = new Order(1003L,new Date());
		Order order3 = new Order(1004L,new Date());
		Order order4 = new Order(1005L,new Date());
		commodity.getOrders().add(order1);
		commodity.getOrders().add(order2);
		commodity.getOrders().add(order3);
		commodity.getOrders().add(order4);
		session.save(commodity);
		destroy();
	}
	
	public void getCommodityAndOrders(){
		init();
		Commodity commodity = (Commodity)session.get(Commodity.class, 1);
		System.out.println(commodity.toStringCommodityAndOrders());
		destroy();
	}
	public static void main(String[] args) {
		HibernateManyToManyTest test = new HibernateManyToManyTest();

		// order为控制方
		test.saveOrderAndCommodities();
		test.getOrderAndCommodities();
		
		// commodites为控制方
		test.saveCommodityAndOrders();
		test.getCommodityAndOrders();
		
	}
}
