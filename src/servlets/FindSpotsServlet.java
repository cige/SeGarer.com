package servlets;

import java.io.IOException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.DaoFactory;
import model.entities.Address;
import model.entities.Spot;
import model.entities.User;

public class FindSpotsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7643486180166520978L;

	private final static int RESULTS_NUMBER = 4;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		if (session == null) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		User user = (User) session.getAttribute("user");

		if (user == null) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		String longitude = req.getParameter("longitude");
		String latitude = req.getParameter("latitude");
		String formatedAddress = req.getParameter("address");

		if (longitude == null || latitude == null) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		Address currentPos = new Address(new Double(longitude), new Double(latitude), formatedAddress);

		resp.setContentType("application/json");

		JsonObjectBuilder json = Json.createObjectBuilder();
		JsonArrayBuilder spots = Json.createArrayBuilder();

		List<Spot> list = DaoFactory.getInstance().getSpotDao().findClosestSposts(currentPos, RESULTS_NUMBER);

		for (int i = 0; i < RESULTS_NUMBER && i < list.size(); i++) {
			spots.add(list.get(i).toJson());
		}

		json.add("results", spots);

		JsonWriter jsonWriter = Json.createWriter(resp.getOutputStream());
		jsonWriter.writeObject(json.build());
		jsonWriter.close();
		
		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
	}

}
