import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class Food {

	private Connection conn;
	private Statement stmt;
	private CallableStatement cstmt;

	private String userAccount;

	public Food(Connection conn, String userAccount) {
		this.conn = conn;
		try {
			stmt = this.conn.createStatement();
			cstmt = null;
			this.userAccount = userAccount;

		} catch (SQLException e) {
			System.out.println("Error: Food " + e.getMessage());
		}
	}

	protected boolean createFood(String foodName, String foodDescription, String picture) {
		int maxID = 0;
		try {
			cstmt = conn.prepareCall("call getFoodID(?);");
			cstmt.setInt(1, Types.BIGINT);
			cstmt.executeUpdate();
			maxID = cstmt.getInt(1);
			maxID++; // possible problem: No limit
			byte[] image = imageToByte(picture);

			cstmt = conn.prepareCall("call createFood(?,?,?,?,?,?);");
			cstmt.setInt(1, maxID);
			cstmt.setString(2, this.userAccount);
			cstmt.setString(3, foodName);
			cstmt.setString(4, foodDescription);
			cstmt.setBytes(5, image);
			cstmt.registerOutParameter(6, Types.VARCHAR);

			cstmt.executeUpdate();
			String status = cstmt.getString(6);

			if (status.equals("Success")) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error: createFood " + e.getMessage());
		}
		return false;
	}

	private byte[] imageToByte(String filepath) {
		byte[] byteArray = null;
		if (filepath == null) {
			return byteArray;
		}
		ByteArrayOutputStream baos = null;
		FileInputStream fis = null;
		try {
			baos = new ByteArrayOutputStream();
			fis = new FileInputStream(filepath);
			byte[] buffer = new byte[1024];
			int read = 0;

			while ((read = fis.read(buffer, 0, buffer.length)) != -1) {
				baos.write(buffer, 0, read);
			}
			byteArray = baos.toByteArray();
		} catch (IOException e) {
			System.out.println("Error: imageToByte " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: imageToByte " + e.getMessage());
		} finally {

			try {
				if (baos != null) {
					baos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				System.out.println("Error: imageToByte " + e.getMessage());
			}
		}
		return byteArray;
	}

	protected boolean deleteFood(String foodName) {
		String sql = "delete from Food where foodName = \"" + foodName + "\" and userAccount = \"" + this.userAccount
				+ "\";";
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("Error: deleteFood " + e.getMessage());
		}
		return false;
	}

	protected ResultSet searchFood(String foodName) {
		String sql = "select * from Food where foodName like \'%" + foodName + "%\'; ";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			System.out.println("Error: searchFood " + e.getMessage());
		}
		return rs;
	}

	protected boolean updateFood(int foodID, String foodName, String foodDescription, String picture) {

		byte[] image = imageToByte(picture);

		try {
			cstmt = conn.prepareCall("call updateFood (?, ?, ?, ?, ?, ?);");
			cstmt.setInt(1, foodID);
			cstmt.setString(2, this.userAccount);
			cstmt.setString(3, foodName);
			cstmt.setString(4, foodDescription);
			cstmt.setBytes(5, image);
			cstmt.registerOutParameter(6, Types.VARCHAR);

			int test = cstmt.executeUpdate();

			System.out.println(test);

			String status = cstmt.getString(6);

			if (status.equals("Success")) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Error: updateFood " + e.getMessage());
		}
		return false;
	}

	protected ResultSet listAllFood() {
		ResultSet rs = null;
		try {
			String sql = "select * from Food;";
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Error: listFood " + e.getMessage());
		}
		return rs;
	}

	protected ResultSet listUserFood() {
		ResultSet rs = null;
		try {
			String sql = "select * from Food where userAccount = \"" + this.userAccount + "\";";
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Error: listFood " + e.getMessage());
		}
		return rs;
	}

	protected int getFoodID(String foodName) {
		String sql = "select foodID from Food where foodName = \"" + foodName + "\" and userAccount = \"" + this.userAccount
				+ "\";";
		int id = -1;
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt("foodID");
			}
		} catch (SQLException e) {
			System.out.println("Error: getFoodID " + e.getMessage());
		}
		return id;
	}
	
	protected ResultSet searchOneFood(int foodID) {
		String sql = "select * from Food where foodName foodID = " + foodID + "; ";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			System.out.println("Error: searchFood " + e.getMessage());
		}
		return rs;
	}
}
