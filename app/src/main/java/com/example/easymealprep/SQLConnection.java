package com.example.easymealprep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://easymeal.cpahsyvhsld2.us-east-2.rds.amazonaws.com/easymeal?autoReconnect=true&useSSL=false"; 
	private final String USERNAME = "admin";
	private final String PASSWORD = "administrator";
	private Connection conn;
	
	protected SQLConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e){
			System.out.println("Error: SQLConnection " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error: SQLConnection " + e.getMessage());
		}
	}
	
	protected void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Error: closeConnection " + e.getMessage());
		}
	}
	
	protected Connection getConnection() {
		return conn;
	}
}
