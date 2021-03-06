package tests;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.Dao;
import model.dao.DaoFactory;
import model.entities.Vehicle;

/**
 * Servlet implementation class Hibernate
 */
@WebServlet({ "/hibernate" })
public class Hibernate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Vehicle v = new Vehicle("Mercedes de Merde!", Vehicle.MEDIUM_SIZE);

		DaoFactory factory = DaoFactory.getInstance();

		factory.getVehicleDao().save(v);
		request.getServletContext().getRequestDispatcher("/test.jsp").forward(request, response);
	}

}
