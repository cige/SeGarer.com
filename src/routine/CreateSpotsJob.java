package routine;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.Address;
import model.entities.Spot;
import model.entities.User;

import java.util.Random;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CreateSpotsJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		UserDao userDao = DaoFactory.getInstance().getUserDao();

		User bot = userDao.getUserFromLogin("bot1");

		// TODO amine longitude,latitude inside PARIS

		Double maxLong = 48.902833;
		Double minLong = 48.815458;
		Double maxLat = 2.416780, minLat = 2.245080;
		Random rand = new Random();
		Double longitude = new Double(((maxLong - minLong) * rand.nextDouble()) + minLong);
		Double latitude = new Double(((maxLat - minLat) * rand.nextDouble()) + minLat);

		// TODO check address using google api

		String formated_address = "55 rue bot";

		Address address = new Address(longitude, latitude, formated_address);

		Spot spot = new Spot(address, bot);

		DaoFactory.getInstance().getSpotDao().persist(spot);

	}

}
