import java.sql.*;
import java.util.ArrayList;

public class Tool {

	private Connection conn;
	private Statement stmt;
	private CallableStatement cstmt;

	public Tool(Connection conn) {
		this.conn = conn;
		try {
			stmt = this.conn.createStatement();
			cstmt = null;
		} catch (SQLException e) {
			System.out.println("Error: CookingTool " + e.getMessage());
		}
	}

	protected boolean createTool(String toolName) {
		try {
			cstmt = conn.prepareCall("call createTool(?);");
			cstmt.setString(1, toolName);

			int result = cstmt.executeUpdate();

			if (result == 1) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Error: createTool " + e.getMessage());
		}
		return false;
	}

	protected boolean createToolFood(int foodID, String toolName) {
		try {
			cstmt = conn.prepareCall("call createToolFood(?,?);");
			cstmt.setString(1, toolName);
			cstmt.setInt(2, foodID);
			int result = cstmt.executeUpdate();
			if (result == 1) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Error: createToolFood " + e.getMessage());
		}
		return false;
	}

	protected ResultSet listTool() {
		ResultSet rs = null;
		return rs;
	}

	protected ResultSet listToolFood(int foodID) {
		ResultSet rs = null;
		return rs;
	}

	protected boolean updateToolFood(int foodID, ArrayList<String> tools) {
		return false;
	}

	protected boolean deleteToolFood(int foodID, String toolName) {
		String sql = "delete from FoodTool where foodID = " + foodID + " and toolName = \"" + toolName + "\";";
		try {
			int result = stmt.executeUpdate(sql);
			if (result == 1) {
				this.deleteTool();
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error: deleteToolFood " + e.getMessage());
		}
		return false;
	}

	protected boolean deleteTool() {
		try {
			String sql = "delete from Tool t where t.toolName <> all(select ft.toolName from FoodTool ft group by ft.toolName);";
			int result = stmt.executeUpdate(sql);
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error: deleteTool " + e.getMessage());
		}
		return false;
	}
}
