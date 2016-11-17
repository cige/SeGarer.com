package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonValue.ValueType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.GoogleAPI;
import api.Metric;
import model.dao.DaoFactory;
import model.entities.Address;
import model.entities.Spot;

public class FindSpotsServlet extends HttpServlet {
	public static final float DISTANCE_MAX = 1.609f * 2; // 3km

	/**
	 * 
	 */
	private static final long serialVersionUID = -7643486180166520978L;

	private final static int RESULTS_NUMBER = 4;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (!ServletUtil.isLogged(req)) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}

		double longitude;
		double latitude;

		try {
			longitude = Double.valueOf(ServletUtil.getParam(req, "longitude"));
			latitude = Double.valueOf(ServletUtil.getParam(req, "latitude"));
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		resp.setContentType("application/json");

		JsonObjectBuilder json = Json.createObjectBuilder();
		JsonArrayBuilder spots = Json.createArrayBuilder();

		List<Spot> list = DaoFactory.getInstance().getSpotDao().findClosestSposts((Double)latitude,(Double)longitude, DISTANCE_MAX,  RESULTS_NUMBER);

		for (int i = 0; i < RESULTS_NUMBER && i < list.size(); i++) {
			Spot spot = list.get(i);
			Metric metric = GoogleAPI.distanceToSpot(new Address(longitude, latitude, ""), spot.getAddress());
			float purcentage = ServletUtil.opportunity(metric, spot);
			spots.add(spot.toJson(metric, purcentage));
		}

		json.add("results", spots);

		JsonWriter jsonWriter = Json.createWriter(resp.getOutputStream());
		jsonWriter.writeObject(json.build());
		jsonWriter.close();

		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
	}

}
