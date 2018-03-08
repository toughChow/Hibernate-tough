package online.shixun.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import online.shixun.model.MembershipCard;
import online.shixun.model.User;

public class HibernateOneToOneTest {

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
		transaction.begin();
		
	}

	public void destroy() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	public void saveUserAndMembershipCard() throws ParseException {
		init();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse("1994-09-19");
		User user = new User("李四", "123456", new Date());
		
		MembershipCard membershipCard = new MembershipCard(888,666);
		membershipCard.setUser(user);
		user.setMembershipCard(membershipCard);
		session.save(user);
		destroy();
	}

	public void getUserAndMembershipCard() {
		init();
		User user = (User) session.get(User.class, 2);
		System.out.println(user);
		destroy();
	}

	public static void main(String[] args) {
		HibernateOneToOneTest test = new HibernateOneToOneTest();
		try {
			test.saveUserAndMembershipCard();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		test.getUserAndMembershipCard();
	}
}
