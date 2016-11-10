package routine;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.Address;
import model.entities.Spot;
import model.entities.User;

public class CreateSpotsJob implements Job {
	public static final String GEOCODE_API = "https://maps.googleapis.com/maps/api/geocode/json?";

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		Double maxLat = 48.902833;
		Double minLat = 48.815458;
		Double maxLong = 2.416780;
		Double minLong = 2.245080;
		Random rand = new Random();
		Double longitude = new Double(((maxLong - minLong) * rand.nextDouble()) + minLong);
		Double latitude = new Double(((maxLat - minLat) * rand.nextDouble()) + minLat);
		String formattedAddr = "";
		try {

			URL url = new URL(GEOCODE_API + "latlng=" + latitude + "," + longitude);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			JsonParser parser = new JsonParser();
			JsonObject jelement = parser.parse(isr).getAsJsonObject();
			JsonArray results = jelement.getAsJsonArray("results");
			JsonObject o = results.get(1).getAsJsonObject();
			formattedAddr = o.getAsString();
			System.out.println(formattedAddr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Address address = new Address(longitude, latitude, formattedAddr);

		//Spot spot = new Spot(address, bot);

		//DaoFactory.getInstance().getSpotDao().persist(spot);
	}

}
