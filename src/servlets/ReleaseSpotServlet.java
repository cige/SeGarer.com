package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		Address address = new Address(Double.valueOf(longitude), Double.valueOf(latitude), formatedAddress);

		SpotDao spotDao = DaoFactory.getInstance().getSpotDao();

		Spot spot = spotDao.findSpotByAddress(address);
		
		if (spot == null)
			spot = new Spot(address);
		spot.setOriginUser(user);
		spot.setFree(true);
		spotDao.persist(spot);

		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
	}

}
