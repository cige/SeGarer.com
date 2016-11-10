package model.dao;

import org.hibernate.Query;

import model.entities.User;

public class UserDao extends Dao<User> {

	private final static UserDao SINGLETON = new UserDao();

	protected static UserDao getInstance() {
		return SINGLETON;
	}

	public User getUserFromEmail(String email) {
		Query query = session.createQuery("From User  u where u.email=:email");
		query.setString("email", email);
		return (User) query.uniqueResult();
	}

	public User getUserFromPseudo(String pseudo) {
		Query query = session.createQuery("From User u where u.pseudo=:pseudo");
		query.setString("pseudo", pseudo);
		return (User) query.uniqueResult();
	}

	public User getUserFromLogin(String pseudoOrEmail) {
		User u1 = getUserFromEmail(pseudoOrEmail);
		if (u1 != null)
			return u1;
		u1 = getUserFromPseudo(pseudoOrEmail);
		return u1;
	}

	public boolean checkIfExists(String pseudo, String email) {
		User u1 = getUserFromEmail(email);
		if (u1 != null)
			return true;
		User u2 = getUserFromPseudo(pseudo);
		if (u2 != null)
			return true;
		return false;
	}

}
