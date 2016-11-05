package model.dao;

import model.entities.Horodator;
import model.entities.Spot;
import model.entities.User;
import model.entities.Vehicle;

public class DaoFactory {
	
	private static DaoFactory SINGLETON = new DaoFactory();
	
	private DaoFactory(){
		super();
	}
	
	public static DaoFactory getInstance(){
		return SINGLETON;
	}

	public UserDao getUserDao() {
		return UserDao.getInstance();
	}

	public Dao<Spot> getSpotDao() {
		return new Dao<Spot>();
	}

	public Dao<Vehicle> getVehicleDao() {
		return new Dao<Vehicle>();
	}

	public Dao<Horodator> getHorodatorDao() {
		return new Dao<Horodator>();
	}
}
