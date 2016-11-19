package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DaoFactory;
import model.entities.Spot;

@WebServlet("/isFree")
public class IsFreeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1851534878477365473L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (!ServletUtil.isLogged(req)) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		long id = 0;
		try {
			id = Long.valueOf(ServletUtil.getParam(req, "spotId"));
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		Spot spot = DaoFactory.getInstance().getSpotDao().getSpotFromId(id);

		if (spot != null && spot.isFree())
		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
		else
			resp.setStatus(HttpResponseCode.NOT_FREE_SPOT);
	}

}
