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

public class SignUpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -98941247654222542L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = ServletUtil.getParam(request, "email");
		String pseudo = ServletUtil.getParam(request, "pseudo");
		String password = ServletUtil.getParam(request, "password");
		String confirmation = ServletUtil.getParam(request, "password2");

		if (email == null || password == null || pseudo == null || confirmation == null || !ServletUtil.isEmail(email)
				|| !password.equals(confirmation)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		// TODO check if the email is well formed

		UserDao userDao = DaoFactory.getInstance().getUserDao();

		if (userDao.getUserFromEmail(email) != null) {
			response.sendError(HttpResponseCode.EMAIL_ALREADY_USED);
			return;
		}

		if (userDao.getUserFromPseudo(pseudo) != null) {
			response.sendError(HttpResponseCode.PSEUDO_ALREADY_USED);
			return;
		}

		// Create the new user object

		User user = new User(email, pseudo, password);

		// Push it in DB

		userDao.save(user);

		// sign the user in
		user.setStatus(true);
		userDao.save(user);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
	}

}