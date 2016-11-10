package api;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GoogleAPI {

	public static final String GEOCODE_API = "https://maps.googleapis.com/maps/api/geocode/json";
	public static final String GEOCIDE_API_KEY = "AIzaSyB1882m4wi4F2mmIZ3pHnvm3V86MpBYiOA";

	public static String getFormattedAddress(Double latitude, Double longitude) {
		String formattedAddr = "Inconnu";
		try {
			URL url = new URL(GEOCODE_API + "?latlng="+latitude + "," + longitude + "&key=" + GEOCIDE_API_KEY);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			JsonParser parser = new JsonParser();
			JsonElement jelement = parser.parse(isr);
			JsonObject obj = jelement.getAsJsonObject();
			JsonElement e1 = obj.get("status");
			String status = e1.getAsString();
			if (status.equals("OK")) {
				JsonArray results = obj.get("results").getAsJsonArray();
				formattedAddr = results.get(0).getAsJsonObject().get("formatted_address").getAsString();

			} else {
				System.out.println(status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formattedAddr;
	}

}
