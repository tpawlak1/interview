package pl.tp.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.tp.model.Event;

public class HibernateUtil {
	 
	private SessionFactory sessionFactory;
	private Session session;
	
	public void startup() {
		buildSessionFactory();
		openSession();
	}
	
	private void openSession() {
		session = getSessionFactory().openSession();
	}
	
	private void buildSessionFactory() {
		try {
			//SessionFactory from hibernate.cfg.xml
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Initial SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
	}
 
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public Session getSession() {
		return session;
	}
 
	public void shutdown() {
		getSession().close();
		getSessionFactory().close();
	}
	
	public void save (Event Event) {
		Session session = getSession();
		session.save(Event);
	}
	
	public void update (Event Event) {
		Session session = getSession();
		session.update(Event);
	}
	
	public <T extends Event> T get (Class<T> clazz, String id) {
		Session session = getSession();
		T res = session.get(clazz, id);
		return res;
	}

	public void delete (Event Event) {
		Session session = getSession();
		session.delete(Event);
	}
}