package tests;

import api.GoogleAPI;
import api.Metric;
import model.dao.DaoFactory;
import model.entities.Address;
import model.entities.Spot;
import servlets.ServletUtil;

public class TestMetric {

	public static void main(String[] args) {
		
		Spot spot = DaoFactory.getInstance().getSpotDao().get(Spot.class, 3);
		Address addr1 = spot.getAddress();
		Metric metric= GoogleAPI.distanceToSpot(addr1, spot.getAddress());
		
		System.out.println(metric);
		
		System.out.println(ServletUtil.opportunity(metric, spot));
	}

}
