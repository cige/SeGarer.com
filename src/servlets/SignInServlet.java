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

public class SignInServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -98941247654222542L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login").trim();
		String password = request.getParameter("password").trim();
		if (login == null || password == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		UserDao userDao = DaoFactory.getInstance().getUserDao();

		// check if user exists
		User user;
		if (ServletUtil.isEmail(login)) {
			user = userDao.getUserFromEmail(login);
		} else {
			user = userDao.getUserFromPseudo(login);
		}

		if (user == null) {
			response.sendError(HttpResponseCode.INCORRECT_LOGIN);
			return;
		}

		// check password
		if (!user.getPassword().equals(password)) {
			response.sendError(HttpResponseCode.INCORRECT_PASSWORD);
			return;
		}

		// sign in
		user.setStatus(true);
		userDao.save(user);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		response.setStatus(HttpServletResponse.SC_ACCEPTED);
	}

}
