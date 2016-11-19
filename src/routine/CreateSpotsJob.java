package routine;

import java.util.Random;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import model.dao.DaoFactory;
import model.entities.Address;
import model.entities.Spot;
import model.entities.User;

public class CreateSpotsJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		Double maxLat = 48.902833;
		Double minLat = 48.815458;
		Double maxLong = 2.416780;
		Double minLong = 2.245080;
		Random rand = new Random();
		Double longitude = new Double(((maxLong - minLong) * rand.nextDouble()) + minLong);
		Double latitude = new Double(((maxLat - minLat) * rand.nextDouble()) + minLat);
		
		System.out.println(longitude+" "+latitude);

		Address address = new Address(longitude, latitude);

		DaoFactory factory = DaoFactory.getInstance();
		//User usr = factory.getUserDao().makeRandomUser();
		Spot spot = new Spot(address, null);
		factory.getSpotDao().save(spot);
	}
}
