package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class SignOutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -98941247654222542L;

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		HttpSession session = request.getSession(false);

		if(session != null){
			User user = (User) session.getAttribute("user");
			if(user!= null){
				UserDao userDao = DaoFactory.getInstance().getUserDao();
				user.setStatus(false);
				userDao.persist(user);
			}
			session.invalidate();
		}
		response.sendRedirect("connection.jsp");
	}

}
