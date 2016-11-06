package model.dao;

import model.entities.Spot;

public class SpotDao extends Dao<Spot> {
	
	private final static SpotDao SINGLETON = new SpotDao();

	private SpotDao(){
		super();
	}

	protected static SpotDao getInstance(){
		return SINGLETON;
	}

}
