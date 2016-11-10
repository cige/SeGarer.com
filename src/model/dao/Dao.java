package model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import model.entities.Vehicle;

public class Dao<T> {

	Session session;

	public Dao() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
	}

	public void save(T entity) {
		session.beginTransaction();
		session.persist(entity);
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

	public Vehicle getVehicleByModel(String model) {
		Query query = session.createQuery("From Vehicle v where v.model=:model");
		query.setString("model", model);
		return (Vehicle) query.uniqueResult();
	}

}
