package com.example.easymealprep;

import java.sql.*;

public class Account {

	private Connection conn;
	private Statement stmt;
	private CallableStatement cstmt;

	private String accountName;
	private String userPassword;
	private String userName;
	private String userEmail;

	public Account(Connection connection) {
		conn = connection;
		try {
			stmt = conn.createStatement();
			cstmt = null;
			accountName = null;
			userPassword = null;
			userName = null;
			userEmail = null;
		} catch (SQLException e) {
			System.out.println("Error: Account " + e.getMessage());
		}
	}

	protected boolean loginAccount(String accountName, String password) {
		try {
			cstmt = conn.prepareCall("call loginAccount(?,?,?,?)");
			cstmt.setString(1, accountName);
			cstmt.setString(2, password);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.VARCHAR);

			cstmt.executeUpdate();

			this.userName = cstmt.getString(3);
			this.userEmail = cstmt.getString(4);

			if (!userName.equals("NULL")) {
				this.accountName = accountName;
				this.userPassword = password;
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error: loginAccount " + e.getMessage());
		}
		return false;
	}

	protected boolean createAccount(String accountName, String password, String name, String email) {
		try {
			cstmt = conn.prepareCall("call createAccount(?,?,?,?,?)");
			cstmt.setString(1, accountName);
			cstmt.setString(2, password);
			cstmt.setString(3, name);
			cstmt.setString(4, email);
			cstmt.registerOutParameter(5, Types.VARCHAR);

			cstmt.executeUpdate();

			String status = cstmt.getString(5);
			if (status.equals("Success")) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Error: createAccount " + e.getMessage());
		}
		return false;
	}

	protected boolean deleteAccount(String password) {
		if (this.accountName == null || !this.userPassword.equals(password)) {
			return false;
		}

		try {
			stmt.execute("delete from Account where userAccount = \"" + accountName + "\";");
			this.accountName = null;
			this.userPassword = null;

			return true;
		} catch (SQLException e) {
			System.out.println("Error: deleteAccount + " + e.getMessage());
		}
		return false;
	}

	protected boolean updateAccount(String password, String username, String email) {

		try {
			cstmt = conn.prepareCall("call updateAccount(?,?,?,?,?)");
			cstmt.setString(1, this.accountName);
			cstmt.setString(2, password);
			cstmt.setString(3, username);
			cstmt.setString(4, email);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.executeUpdate();
			
			String status = cstmt.getString(5);
			
			if (status.equals("Success")) {
				this.userPassword = password;
				this.userName = username;
				this.userEmail = email;
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Error: updateAccount " + e.getMessage());
		}
		return false;
	}
	
	protected ResultSet getFavorite() {
		String sql = "select f.foodID from Favorite f where f.userAccount = \"" + this.accountName + "\";";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			System.out.println("Error: getFavorite " + e.getMessage());
		}
		return null;
	}
	
	protected boolean setFavorite(int foodID) {
		try {
			cstmt = conn.prepareCall("call setFavorite(?,?,?);");
			cstmt.setString(1, this.accountName);
			cstmt.setInt(2, foodID);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			
			cstmt.executeUpdate();
			String status = cstmt.getString(3);
			
			if (status.equals("Success")) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error: setFavorite " + e.getMessage());
		}
		return false;
	}
	
	protected boolean deleteFavorite(int foodID) {
		String sql = "delete from Favorite where foodID = \"" + foodID + "\";";
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("Error: deleteFavorite " + e.getMessage());
		}
		return false;
	}
	
	protected String getUserAccount() {
		return this.accountName;
	}
	
	protected String getUserEmail() {
		return this.userEmail;
	}
	
	protected String getUserPassword() {
		return this.userPassword;
	}
	
	protected String getUserName() {
		 return this.userName;
	}
}
