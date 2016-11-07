package routine;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.Address;
import model.entities.Spot;
import model.entities.User;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CreateSpotsJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		
		User bot = userDao.getUserFromLogin("bot1");
		
		//TODO amine longitude,latitude inside PARIS
		
		float maxLong=3, minLong= 1;
		float maxLat = 3, minLat = 1;
		
		Float longitude = new Float(((maxLong-minLong)*Math.random())+minLong);
		Float latitude = new Float(((maxLat-minLat)*Math.random())+minLat);
		
		//TODO check address using google api
		
		String formated_address = "55 rue bot";
		
		Address address = new Address(longitude,latitude,formated_address);
		
		Spot spot = new Spot(address, bot);
		
		DaoFactory.getInstance().getSpotDao().persist(spot);
		

	}

}
