package model.dao;

import java.util.List;

import org.hibernate.Query;

import model.entities.Address;
import model.entities.Spot;

public class SpotDao extends Dao<Spot> {
	public static final int DISTANCE_MAX = 10;

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
	public List<Spot> findClosestSpostsf(Address position, int n) {

		Double longMin = position.getLongitude()
				- (DISTANCE_MAX / Math.abs(Math.cos(Math.toRadians(position.getLatitude() * 69))));
		Double longMax = position.getLongitude()
				+ (DISTANCE_MAX / Math.abs(Math.cos(Math.toRadians(position.getLatitude() * 69))));
		Double latMin = position.getLatitude()
				- (DISTANCE_MAX / Math.abs(Math.cos(Math.toRadians(position.getLongitude() * 69))));
		Double latMax = position.getLatitude()
				- (DISTANCE_MAX / Math.abs(Math.cos(Math.toRadians(position.getLongitude() * 69))));

		Query query = session.createQuery(
				"from Spot as s where s.address.longitude between :lgmin and :lgmax and s.address.latitude between :ltmin and :ltmax");

		query.setDouble("lgmin", longMin);
		query.setDouble("lgmax", longMax);
		query.setDouble("ltmin", latMin);
		query.setDouble("ltmax", latMax);
		
		return query.list();

	}

}
