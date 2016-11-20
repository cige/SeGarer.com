package model.dao;

import java.util.Random;

import org.hibernate.Query;

import model.entities.User;
import model.entities.Vehicle;
import servlets.ServletUtil;

public class UserDao extends Dao<User> {

	public static final String BASE_DOMAINE = "@stl.upmc.fr";
	public static final String BASE_LOGIN = "student";
	public static final String BASE_PASSWORD = "student";
	public static final Vehicle COMMON_CAR = new Vehicle("AUDI for Students", Vehicle.MEDIUM_SIZE);

	private final static UserDao SINGLETON = new UserDao();

	protected static UserDao getInstance() {
		return SINGLETON;
	}

	public User getUserFromEmail(String email, String password) {
		Query query = session.createQuery("From User  u where u.email=:email and u.password:=password");
		query.setString("email", email);
		query.setString("password", password);
		return (User) query.uniqueResult();
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

	public User getUserFromPseudo(String pseudo, String password) {
		Query query = session.createQuery("From User u where u.pseudo=:pseudo and u.password:=password");
		query.setString("pseudo", pseudo);
		query.setString("password", password);
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

	public User makeRandomUser() {
		Random rand = new Random();
		int id = rand.nextInt(100);
		String login = BASE_LOGIN + id;
		String email = login + BASE_DOMAINE;
		User user = new User(email, login, ServletUtil.cryptPassword(BASE_PASSWORD));
		user.setVehicle(COMMON_CAR);
		return user;
	}
}