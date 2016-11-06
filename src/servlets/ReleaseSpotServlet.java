package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.DaoFactory;
import model.entities.Address;
import model.entities.Spot;
import model.entities.User;

public class ReleaseSpotServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6887329651615332729L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		if(session == null){
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		User user = (User) session.getAttribute("user");

		if(user == null){
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		String longitude = req.getParameter("longitude");
		String latitude = req.getParameter("latitude");
		String formatedAddress = req.getParameter("address");

		Address address = new Address(Float.valueOf(longitude), Float.valueOf(latitude), formatedAddress);
		
		Spot spot = new Spot(address,user);
		
		DaoFactory.getInstance().getSpotDao().persist(spot);
		
		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
	}

}
