package model.dao;

import java.util.LinkedList;
import java.util.List;

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
		 
		//TODO  
		List<Spot> spots = new LinkedList<>();
		
		for(int i = 0; i<n ; i++){
			spots.add(new Spot(new Address(new Float(Math.random()), new Float(Math.random()), "address"), currentUser));
		}
		
		return spots;
	}

}
