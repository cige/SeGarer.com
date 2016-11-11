package model.dao;

import java.util.List;

import org.hibernate.Query;

import model.entities.Address;
import model.entities.Spot;

public class SpotDao extends Dao<Spot> {
	

	private final static SpotDao SINGLETON = new SpotDao();

	private SpotDao() {
		super();
	}

	protected static SpotDao getInstance() {
		return SINGLETON;
	}

	/**
	 * Return a list with at most n spots.
	 */
	public List<Spot> findClosestSposts(Address position, float dist, int n) {

		Double longMin = position.getLongitude()
				- (dist / Math.abs(Math.cos(Math.toRadians(position.getLatitude() * 69))));
		Double longMax = position.getLongitude()
				+ (dist / Math.abs(Math.cos(Math.toRadians(position.getLatitude() * 69))));
		Double latMin = position.getLatitude() - (dist / 69);
		Double latMax = position.getLatitude() + (dist / 69);
		Query query = session.createQuery(
				"from Spot as s where s.address.longitude between :lgmin and :lgmax and s.address.latitude between :ltmin and :ltmax");

		query.setDouble("lgmin", longMin);
		query.setDouble("lgmax", longMax);
		query.setDouble("ltmin", latMin);
		query.setDouble("ltmax", latMax);

		return query.list();
	}

	public Spot findSpotByAddress(Address addr) {
		Query query = session.createQuery("From Spot s where s.address.longitude=:long and s.address.latitude=:lat");
		query.setDouble("long", addr.getLongitude());
		query.setDouble("lat", addr.getLatitude());
		return (Spot) query.uniqueResult();
	}

	public void purgeAll(List<Spot> spots) {
		session.beginTransaction();
		spots.forEach(p -> session.delete(p));
		session.getTransaction().commit();
	}

}
