package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -98941247654222542L;

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		// TODO register the user
		
		// TODO signup the user (or redirect to signup servlet)
		
		response.sendRedirect("main.jsp");
	}

}
