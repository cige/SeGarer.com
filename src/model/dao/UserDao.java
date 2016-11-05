package model.dao;

import java.util.List;

import org.hibernate.Query;

import model.entities.User;

public class UserDao extends Dao<User> {

	private final static UserDao SINGLETON = new UserDao();

	private UserDao(){
		super();
	}

	protected static UserDao getInstance(){
		return SINGLETON;
	}

	public User getUserFromEmail(String email) {
		session.beginTransaction();
		Query query = session.createQuery("From User  u where u.email=:email");
		query.setString("email", email);
		List<User> list =query.list();
		session.getTransaction().commit();
		if (!list.isEmpty())
			return list.get(0);
		return null;
	}

	public User getUserFromPseudo(String pseudo) {
		session.beginTransaction();
		Query query = session.createQuery("From User u where u.pseudo=:pseudo");
		query.setString("pseudo", pseudo);
		List<User> list =query.list();
		session.getTransaction().commit();
		if (!list.isEmpty())
			return list.get(0);
		return null;
	}
	
	public User getUserFromLogin(String pseudoOrEmail){
		User u1 = getUserFromEmail(pseudoOrEmail);
		if(u1 != null)
			return u1;
		u1 = getUserFromPseudo(pseudoOrEmail);
		return u1;
	}

	public boolean checkIfExists(String pseudo, String email){
		User u1 = getUserFromEmail(email);
		if(u1 != null)
			return true;
		User u2 = getUserFromPseudo(pseudo);
		if(u2 != null)
			return true;
		return false;
	}



}
