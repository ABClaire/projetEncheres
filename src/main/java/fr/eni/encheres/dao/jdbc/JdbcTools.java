package fr.eni.encheres.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {

	private static String url;
	private static String user;
	private static String pwd;
	private static String driver;
	
	static {
		url = Settings.getProperty("url");
		user = Settings.getProperty("user");
		pwd = Settings.getProperty("password");
		driver = Settings.getProperty("driver");
	}
	
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(url, user, pwd);
	}
}
