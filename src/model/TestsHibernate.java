package model;

import java.sql.Connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class TestsHibernate {
	private static final Integer port = 3306;

	/**
	 * Pour communiquer avec MySQL
	 */
	private Connection connexion;

	/**
	 * Objet Session de Hibernate
	 */
	private Session session;

	/**
	 * Constructeur établissant une connexion avec Hibernate
	 */
	public TestsHibernate() {
		Configuration configuration = new Configuration().configure();

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		session = sessionFactory.openSession();
		
	}

	public void saveVehicle(Vehicle v){
		session.beginTransaction();
		session.save(v);
		session.getTransaction().commit();
	}
}