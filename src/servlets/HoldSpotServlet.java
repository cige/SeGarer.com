package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DaoFactory;
import model.entities.Spot;

@WebServlet("/holdSpot")
public class HoldSpotServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (!ServletUtil.isLogged(req)) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		Double longitude, latitude;

		try {
			longitude = Double.valueOf(req.getParameter("longitude"));
			latitude = Double.valueOf(req.getParameter("latitude"));
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		DaoFactory factory = DaoFactory.getInstance();
		Spot spot = factory.getSpotDao().getSpotByCoordonates(longitude, latitude);

		if (spot != null && spot.isFree()) {
			factory.getSpotDao().delete(spot);
			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
