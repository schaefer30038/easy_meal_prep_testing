import static org.junit.Assert.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.source.tree.AssertTree;

class ToolTest {

	private SQLConnection connect;
	private Tool testTool;
	
	@BeforeEach
	void setUp() throws Exception {
		connect = new SQLConnection();
		testTool = new Tool(connect.getConnection());
	}

	@AfterEach
	void tearDown() throws Exception {
		if (connect == null) {
			System.out.println("Print");
		}
		connect.closeConnection();
	}

	@Test
	void testCreateTool() {
		assertTrue(testTool.createTool("Test1"));
		assertFalse(testTool.createTool("Test1"));
		assertTrue(testTool.deleteTool());
	}
	
	@Test
	void testDeleteTool() {
		assertFalse(testTool.deleteTool());
		
		assertTrue(testTool.createTool("Test1"));
		assertTrue(testTool.deleteTool());

		assertTrue(testTool.createTool("Test1"));
		assertTrue(testTool.createToolFood(1, "Test1"));
		assertFalse(testTool.deleteTool());
		
		assertTrue(testTool.deleteToolFood(1, "Test1"));
		assertFalse(testTool.deleteTool());
	}
	@Test
	void testCreateToolFood() {
		assertTrue(testTool.createTool("Test1"));
		assertTrue(testTool.createTool("Test2"));
		assertTrue(testTool.createTool("Test3"));
		
		assertTrue(testTool.createToolFood(1, "Test1"));
		assertTrue(testTool.createToolFood(1, "Test2"));
		assertTrue(testTool.createToolFood(1, "Test3"));
		
		assertFalse(testTool.createToolFood(1, "Test1"));
		assertFalse(testTool.createToolFood(1, "Test2"));
		assertFalse(testTool.createToolFood(1, "Test3"));
		
		assertTrue(testTool.deleteToolFood(1, "Test1"));
		assertTrue(testTool.deleteToolFood(1, "Test2"));
		assertTrue(testTool.deleteToolFood(1, "Test3"));
		
	}
	@Test
	void testDeleteToolFood() {
		assertFalse(testTool.deleteToolFood(1, "Test1"));
		assertFalse(testTool.deleteToolFood(1, "Test2"));
		assertFalse(testTool.deleteToolFood(1, "Test3"));
		
	}

}
