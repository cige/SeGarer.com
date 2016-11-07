package model.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;

import model.entities.Address;
import model.entities.Spot;
import model.entities.User;

public class SpotDao extends Dao<Spot> {
	
	private final static SpotDao SINGLETON = new SpotDao();

	private SpotDao(){
		super();
	}

	protected static SpotDao getInstance(){
		return SINGLETON;
	}
	
	/**
	 * Return a list with at most n spots.
	 */
	public List<Spot> findSpots(User currentUser,Spot currentSpot,int n){
		
		session.beginTransaction();
		Query query = session.createQuery("From Spot s");
		query.setMaxResults(n);
		List<Spot> list =query.list();
		session.getTransaction().commit();
		
		return list;
	}

}
