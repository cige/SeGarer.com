package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Dao<T> {

	Session session;

	public Dao() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
	}

	public void persist(T entity) {
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
	}

	public T get(Class<T> objClass, long id) {
		return (T) session.get(objClass, id);

	}

	public List<T> findAll(Class<T> objClass) {

		return session.createQuery("from " + objClass.getName()).list();

	}

	public void delete(T entity) {
		session.beginTransaction();
		session.delete(entity);
		session.getTransaction().commit();
	}
}
