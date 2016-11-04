package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestsJdbc {
	private static final Integer port = 3306;

	/**
	 * Pour communiquer avec MySQL
	 */
	private Connection connexion;

	/**
	 * Constructeur sans connexion
	 */
	public TestsJdbc() throws ClassNotFoundException {
		/* On commence par "charger" le pilote MySQL */
		Class.forName("com.mysql.jdbc.Driver");
	}

	public void connect(String server, String bd, String u, String p) throws SQLException {
		String url = "jdbc:mysql://" + server + ":" + port + "/" + bd;
		connexion = DriverManager.getConnection(url, u, p);
	}

}