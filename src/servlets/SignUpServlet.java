package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.DaoFactory;
import model.entities.User;

public class SignUpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -98941247654222542L;

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(email == null || password == null){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		//TODO check if not already exists
		
		//Create the new user object
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		//Push it in DB

		DaoFactory factory =new DaoFactory();
		factory.getUserDao().persist(user);

		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		response.sendRedirect("main.jsp");
	}

}
