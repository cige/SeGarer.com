package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DaoFactory;
import model.entities.Spot;

@WebServlet("/targetSpot")
public class TargetSpotServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!ServletUtil.isLogged(req)) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		Long idSpot;

		try {
			idSpot = Long.valueOf(req.getParameter("idSpot"));
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		DaoFactory factory = DaoFactory.getInstance();
		Spot spot = factory.getSpotDao().getSpotFromId(idSpot);

		if (spot != null && spot.isFree()) {
			spot.incremenInterestUsers();
			factory.getSpotDao().save(spot);
			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
