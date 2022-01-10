package fr.eni.encheres.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {

	private static String url;
	private static String user;
	private static String pwd;
	
	static {
		url = Settings.getProperty("url");
		user = Settings.getProperty("user");
		pwd = Settings.getProperty("password");
	}
	
	
	public static Connection getConnection() throws SQLException {		
		return DriverManager.getConnection(url, user, pwd);
	}
}
