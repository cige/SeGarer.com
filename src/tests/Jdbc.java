package tests;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Jdbc
 */
@WebServlet({ "/Jdbc", "/jdbc" })
public class Jdbc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SERVER = "localhost", BD = "webscope", LOGIN = "orm", PASSWORD = "orm",
			VUES = "/vues/jdbc/";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Jdbc() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
