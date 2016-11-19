package servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import api.GoogleAPI;
import api.Metric;
import model.entities.Address;
import model.entities.Spot;

public class ServletUtil {
	public static final float OPPORTUNITY_MAX = 95;
	public static final float OPPORTUNITY_MIN = 20;
	public static final float THRESHOLD_TIME = 15;

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

	public static float opportunity(Metric metric, Spot spot) {
		float opportunity;
		System.out.println();
		float tmp = metric.getDuration() + ((System.currentTimeMillis() - spot.getReleaseTime().getTime()) / 60000);

		System.out.println(tmp);
		if (tmp < 1)
			opportunity = OPPORTUNITY_MAX;
		else if (tmp > THRESHOLD_TIME)
			opportunity = OPPORTUNITY_MIN;
		else
			opportunity = Math.round(100 - tmp * (OPPORTUNITY_MAX - OPPORTUNITY_MIN) / THRESHOLD_TIME);
		return opportunity;
	}
}
