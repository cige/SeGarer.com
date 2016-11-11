package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DaoFactory;
import model.dao.SpotDao;
import model.entities.Address;
import model.entities.Spot;
import model.entities.User;

public class ReleaseSpotServlet extends HttpServlet {
	public static final String VIEW_SUCCESS = "/main.jsp";

	/**
	 * 
	 */
	private static final long serialVersionUID = -6887329651615332729L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (!ServletUtil.isLogged(req)) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		User user = (User) req.getSession(false).getAttribute("user");

		Double longitude;
		Double latitude;
		try {
			longitude = Double.valueOf(req.getParameter("longitude"));
			latitude = Double.valueOf(req.getParameter("latitude"));

		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		String formatedAddress = ServletUtil.getParam(req, "address");
		Address address = new Address(longitude, latitude, formatedAddress);

		SpotDao spotDao = DaoFactory.getInstance().getSpotDao();
		Spot spot = spotDao.findSpotByAddress(address);

		if (spot == null)
			spot = new Spot(address, user);
		spot.setFree(true);
		spotDao.save(spot);
		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
	}

}
