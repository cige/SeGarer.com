package servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ServletUtil {

	public static boolean isLogged(HttpServletRequest req) {

		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			return true;
		}
		return false;
	}

	public static boolean isEmail(String email) {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			return false;
		}
		return true;
	}

	public static String getParam(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}
