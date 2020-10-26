package com.example.easymealprep;

import java.sql.*;
import java.util.ArrayList;

public class Recipe {
	private Connection conn;
	private Statement stmt;
	private CallableStatement cstmt;
	private String userAccount;
	
	public Recipe(Connection conn, String userAccount) {
		this.conn = conn;
		try {
			stmt = this.conn.createStatement();
			cstmt = null;
			this.userAccount = userAccount;
		} catch (SQLException e) {
			System.out.println("Error: Recipe " + e.getMessage());
		}
	}
	
	protected boolean createRecipe(int foodID, ArrayList<String> instruction) {
		try {
			int step = 1;
			int count = 0;
			
			if (instruction == null) {
				return false;
			}
			while (count < instruction.size()) {
				cstmt = conn.prepareCall("call createRecipe(?,?,?,?,?);");
				cstmt.setInt(1, foodID);
				cstmt.setInt(2, step);
				cstmt.setString(3, instruction.get(count));
				cstmt.setString(4, this.userAccount);
				cstmt.registerOutParameter(5, Types.VARCHAR);
				
				cstmt.executeUpdate();
				String status = cstmt.getString(5);
				if (!status.equals("Success")) {
					return false;
				}
				step++;
				count++;
			}
			return true;
			
		} catch (SQLException e) {
			System.out.println("Error: createRecipe " + e.getMessage());
		}
		return false;
	}
	protected boolean searchUserRecipe() {
		return false;
	}
	protected boolean searchAllRecipe() {
		return false;
	}
	protected boolean deleteRecipe(int foodID) {
		try {
			cstmt = conn.prepareCall("call deleteRecipe (?,?);");
			cstmt.setInt(1, foodID);
			cstmt.setString(2, this.userAccount);
			int result = cstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error: deleteRecipe " + e.getMessage());
		}
		return false;
	}
	protected boolean updateRecipe() {
		return false;
	}
	protected ResultSet searchOneRecipe(int foodID) {
		try {
			String sql = "select * from Recipe where foodID = " + foodID + " order by step ASC;";
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			System.out.println("Error: searchOneRecipe " + e.getMessage());
		}
		return null;
	}
}
