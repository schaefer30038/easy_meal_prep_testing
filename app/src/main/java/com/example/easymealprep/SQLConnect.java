package com.example.easymealprep;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class is an object that connects to a database server to access a
 * database 'spike_exercise'. It will be initialized when the program requires
 * access to the server
 *
 * @author Hyukjoon Yang
 *
 */
public class SQLConnect {

    // Strings that is required to access the database server
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://easymeal.cpahsyvhsld2.us-east-2.rds.amazonaws.com/" +
                                "easymeal?autoReconnect=true&useSSL=false";

    // Authorized account in the databsase server
    private final String USERNAME = "admin";
    private final String PASSWORD = "administrator";

    private Connection conn; // Variable that connects to database
    private Statement stmt; // Execute query statement
    private String sql; // String that stores SQL query
    private ResultSet rs; // Gets the data from a query
    private String status; // Gets output parameter of stored procedures in the database
    private CallableStatement cstmt; // Execute query statement

    /**
     * Constructor that initializes class variables and connects to database
     */
    public SQLConnect() {
        try {
            // Initializes class variables
            sql = null;
            rs = null;
            status = null;
            cstmt = null;

            // Connects to the database in the server
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();

            System.out.println("Database is successfully Connected");
            System.out.println();

        } catch (ClassNotFoundException e) {
            System.out.println("SQLConnection: " + e.getMessage());
        }
        catch (SQLException e) {
            System.out.println("SQLConnection: " + e.getMessage());
        }
    }

    /**
     * This method is called at the end of the app program to terminate connection
     * to the database
     */
    protected void closeConnection() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Close Connection: " + e.getMessage());
        }
    }

//    /**
//     * This method is called when the user creates an account. It calls
//     * createAccount stored procedure from the database to create the account.
//     * @param username String for the user account
//     * @param password String for the password
//     * @param picture  Blob that refers to the picture uploaded by the user
//     * @param apiary   String for the initial apiary address
//     * @param email    String for the user's email
//     * @param phone    String for the user's phone
//     * @return String that stores the result of this method
//     */
    protected boolean loginAccount(String accountName, String password) {
        try {
            String userName;
            System.out.println("login start2");
            cstmt = conn.prepareCall("call loginAccount(?,?,?,?)");
            cstmt.setString(1, accountName);
            cstmt.setString(2, password);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.VARCHAR);

            cstmt.executeUpdate();

            userName = cstmt.getString(3);
//            this.userEmail = cstmt.getString(4);
//
            if (!userName.equals("NULL")) {
//                this.accountName = accountName;
//                this.userPassword = password;
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: loginAccount " + e.getMessage());
        }
        return false;
    }

    protected String createAccount(String username, String password, Blob picture,
                                   String apiary, String email, String phone) {

        try {
            // Query to initialize createAccount stored procedure in the database
            cstmt = conn.prepareCall("call createAccount(?,?,?,?,?,?,?);");
            cstmt.setString(1, username);
            cstmt.setString(2, password);
            cstmt.setBlob(3, picture);
            cstmt.setString(4, apiary);
            cstmt.setString(5, email);
            cstmt.setString(6, phone);
            cstmt.registerOutParameter(7, Types.VARCHAR);
            cstmt.executeUpdate();

            // Get output parameter from the procedure
            status = cstmt.getString(7);

            // Checks if the query is executed successfully by output parameter
            if (status.equals("Success")) {
                System.out.println("Successfully created account");
            } else {
                System.out.println("The acount already exists");
            }
        } catch (SQLException e) {
            System.out.println("createAccount: " + e.getMessage());
        }
        return status;

    }

    /**
     * This method is called when the user tries to log in in the app. It calls
     * searchAccount stored procedure from the database to check if the user account
     * with the password exists.
     * @param username String variable for the user account
     * @param password String variable for the password
     * @return String that stores the result of this method
     */
    protected String searchAccount(String username, String password) {
        try {
            // Execute query to call searchAccount procedure
            cstmt = conn.prepareCall("call searchAccount(?,?,?)");
            cstmt.setString(1, username);
            cstmt.setString(2, password);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.executeUpdate();

            // Gets output parameter of the procedure
            status = cstmt.getString(3);

            // Checks if the query is executed successfully by output parameter
            if (status.equals("Username")) {
                System.out.println("The account does not exist");
            } else if (status.equals("Password")) {
                System.out.println("The password is wrong");
            } else {
                System.out.println("Successfully logged in");
            }
        } catch (SQLException e) {
            System.out.println("searchAccount: " + e.getMessage());
        }
        return status;
    }

    /**
     * This method is called when the user updates the user's profile. It will
     * execute updateProfile stored procedure from the database to update the
     * profile of the user's account
     * @param username String for the user's account
     * @param picture  Blob format for the picture uploaded by the user
     * @param email    String for the user's email
     * @param phone    String for the user's phone
     * @return String that stores the result of this method
     */
    protected String updateProfile(String username, Blob picture, String email, String phone) {

        String res = "fail";
        try {
            // Execute query to call updateProfile procedure
            cstmt = conn.prepareCall("call updateProfile(?, ?, ?, ?);");
            cstmt.setString(1, username);
            cstmt.setBlob(2, picture);
            cstmt.setString(3, email);
            cstmt.setString(4, phone);
            cstmt.execute();
            System.out.println("Successfully updated profile");
            res = "success";
        } catch (SQLException e) {
            System.out.println("updateProfile: " + e.getMessage());
        }
        return res;
    }

    /**
     * This method is called to display information about the user's profile by
     * executing displayProfile stored procedure from the database
     *
     * @param username String for the user's account
     * @return rs ResultSet that stores the data from the query.
     */
    protected ResultSet displayProfile(String username) {
        try {
            // Execute query to call displayProfile stored procedure
            sql = "call displayProfile(\"" + username + "\");";
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println("displayProfile: " + e.getMessage());
        }
        return null;
    }

    /**
     * This method is called when the user creates a hive into the database. It
     * calls createHive stored procedure to store the new information of new hive
     * into the database
     * @param username        String for the user account
     * @param apiary          String for the apiary address of hive
     * @param hive            String for the name of hive
     * @param inspection      String for inspection result
     * @param health          String for health
     * @param honey           String for honey stores
     * @param queenproduction String for queen production
     * @param equiphive       String for equipment on the hive
     * @param equipinven      String for equipment in inventory
     * @param loss            int for losses
     * @param gain            int for gains
     * @return                String that stores the result of this method
     */
    protected String createHive(String username, String apiary, String hive, String inspection, 
                                String health, String honey, String queenproduction, 
                                String equiphive, String equipinven, int loss, int gain) {

        // Execute query to call createHive stored procedure
        try {
            cstmt = conn.prepareCall("call createHive(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            cstmt.setString(1, username);
            cstmt.setString(2, apiary);
            cstmt.setString(3, hive);
            cstmt.setString(4, inspection);
            cstmt.setString(5, health);
            cstmt.setString(6, honey);
            cstmt.setString(7, queenproduction);
            cstmt.setString(8, equiphive);
            cstmt.setString(9, equipinven);
            cstmt.setInt(10, loss);
            cstmt.setInt(11, gain);
            cstmt.registerOutParameter(12, Types.VARCHAR);
            cstmt.executeUpdate();

            // Get output parameter from the stored procedure
            status = cstmt.getString(12);

            // Checks if the query is executed successfully by output parameter
            if (status.equals("Exists")) {
                System.out.println("The hive already exists");
            } else if (status.equals("DNEApiary")) {
                System.out.println("No user with the apiary adress exists");
            } else {
                System.out.println("The hive is successfully created");
            }
        } catch (SQLException e) {
            System.out.println("createHive: " + e.getMessage());
        }
        return status;

    }

    /**
     * This method is called to display information of hives owned by the user. It
     * executes displayHive stored procedure from the database.
     *
     * @param username String for the user's account
     *
     * @return rs ResultSet that stores the data from the database
     */
    protected ResultSet displayHive(String username) {
        try {
            sql = "call displayHive(\"" + username + "\");";
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println("displayHive: " + e.getMessage());
        }
        return null;
    }

    /**
     * This method is called to update a hive that is specified by the user account,
     * the apiary address and the name of the hive. It executes updateHive stored
     * procedure. It restricts the user to change the apiary adress and the name of
     * hive.
     * @param username        String for the user account
     * @param apiary          String for the apiary address of hive
     * @param oldhive            String for the name of hive
     * @param newhive            String for the name of hive
     * @param inspection      String for inspection result
     * @param health          String for health
     * @param honey           String for honey stores
     * @param queenproduction String for queen production
     * @param equiphive       String for equipment on the hive
     * @param equipinven      String for equipment in inventory
     * @param loss            int for losses
     * @param gain            int for gains
     * @return String         String that stores the result of this method
     */
    protected String updateHive(String username, String apiary, String oldhive, String newhive,
                                String inspection, String health, String honey, 
                                String queenproduction, String equiphive, String equipinven, 
                                int loss, int gain) {

        // Execute query to call updateHive stored procedure
        try {
            cstmt = conn.prepareCall("call updateHive(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            cstmt.setString(1, username);
            cstmt.setString(2, apiary);
            cstmt.setString(3, oldhive);
            cstmt.setString(4, newhive);

            cstmt.setString(5, inspection);
            cstmt.setString(6, health);
            cstmt.setString(7, honey);
            cstmt.setString(8, queenproduction);
            cstmt.setString(9, equiphive);
            cstmt.setString(10, equipinven);
            cstmt.setInt(11, loss);
            cstmt.setInt(12, gain);
            cstmt.registerOutParameter(13, Types.VARCHAR);
            cstmt.executeUpdate();

            String status = cstmt.getString(13);
            if (status.equals("Exist")){
                System.out.println("Hive with the new name already exists");
            } else {
                System.out.println("Successfully updated Hive");
            }


        } catch (SQLException e) {
            System.out.println("updateHive: " + e.getMessage());
        }
        return status;

    }

    /**
     * This method is called to delete a hive specified by the username, apiary
     * address, and the name of hive by executing a delteHive stored procedures in
     * the database.
     * @param  username String for the user's account
     * @param  apiary   String for the user's apiary address
     * @param  hive     String for the name of hive
     * @return String   that stores the result of this method
     */
    protected String deleteHive(String username, String apiary, String hive) {

        try {
            // Execute query to call deleteHive stored procedure
            cstmt = conn.prepareCall("call deleteHive(?, ?, ?, ?);");
            cstmt.setString(1, username);
            cstmt.setString(2, apiary);
            cstmt.setString(3, hive);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.executeUpdate();

            status = cstmt.getString(4);

            // Checks if the query is executed successfully by the output parameter
            if (status.equals("DNE")) {
                System.out.println("Such hive does not exist");
            } else {
                System.out.println("Successfully deleted the hive");
            }
        } catch (SQLException e) {
            System.out.println("Error in deleteHive: " + e.getMessage());
        }
        return status;
    }

    /**
     * This method is called when the user creates a new apiary by calling
     * createApiary stored procedure. If there is a apiary with the same name, it
     * does not create the new apiary. If the user creates a apiary that is not
     * belong to the user, it does not create the new apiary.
     * @param username String for the user's account
     * @param apiary   String for the new apiary address
     * @return String  that stores the result of this method
     */
    protected String createApiary(String username, String apiary) {
        try {
            // Execute createApiary stored procedure
            cstmt = conn.prepareCall("call createApiary(?,?,?);");
            cstmt.setString(1, username);
            cstmt.setString(2, apiary);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.executeUpdate();
            status = cstmt.getString(3);

            // Check if the query executed successfully by output parameter
            if (status.equals("Exist")) {
                System.out.println("The appiary already exists");
            } else if (status.equals("NoUser")) {
                System.out.println("Such account does not exist");
            } else {
                System.out.println("Successfully created apiary");
            }
        } catch (SQLException e) {
            System.out.println("createApiary: " + e.getMessage());
        }
        return status;
    }

    /**
     * This method is called when the user edits the name of the appiary. It
     * executes updateApiary stored procedure. It does not allow the user to change
     * non-existing apiary and to update a apiary to another apiary's name that
     * already exists.
     * @param username  String for the user account
     * @param oldpiary  String for the original name of apiary
     * @param newapiary String for the new name of apiary
     * @return String   that stores the result of this method
     */
    protected String updateApiary(String username, String oldpiary, String newapiary) {
        try {
            // Execute updateApiary stored procedure
            cstmt = conn.prepareCall("call updateApiary(?, ?, ?, ?);");

            cstmt.setString(1, username);
            cstmt.setString(2, oldpiary);
            cstmt.setString(3, newapiary);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.executeUpdate();
            status = cstmt.getString(4);

            // Check if the query executed successfully by output parameter
            if (status.equals("DNE")) {
                System.out.println("No appiary exist");
            } else if (status.equals("Exist")) {
                System.out.println("Appiary exist with the name");
            } else {
                System.out.println("Successfully updated apiary");
            }
        } catch (SQLException e) {
            System.out.println("updateApiary: " + e.getMessage());
        }
        return status;
    }

    /**
     * This method is called when the user deletes the user's apiary.
     * @param username String for the user account
     * @param apiary   String for the user apiary
     * @return String  that stores the result of this method
     */
    protected String deleteApiary(String username, String apiary) {
        try {
            // Execute delteApiary stored procedure
            cstmt = conn.prepareCall("call deleteApiary(?, ?, ?);");

            cstmt.setString(1, username);
            cstmt.setString(2, apiary);
            cstmt.registerOutParameter(3, Types.VARCHAR);

            cstmt.executeUpdate();
            status = cstmt.getString(3);

            // Check if the query executed successfully by output parameter
            if (status.equals("DNE")) {
                System.out.println("Such apiary does not exist");
            } else {
                System.out.println("Successfully deleted apiary");
            }

        } catch (SQLException e) {
            System.out.println("deleteApiary: " + e.getMessage());
        }
        return status;

    }

    protected ResultSet displayApiary(String username) {
        try {
            cstmt = conn.prepareCall("call displayApiary(?);");
            cstmt.setString(1, username);
            rs = cstmt.executeQuery();

            return rs;
        } catch (SQLException e) {
            System.out.println("displayApiuary: " + e.getMessage());
            return null;
        }
    }
    /**
     * This method is to display selected information of hives owned by the user
     *
     * @param username        String for the user account
     * @param inspection      boolean to display the data for inspection
     * @param health          boolean to display the data for health
     * @param honey           boolean to display the data for honey
     * @param queenproduction boolean to display queen production
     * @param equiphive       boolean to display equipment on hive
     * @param equipinven      boolean to display euipmentin inventory
     * @param loss            boolean to display loss
     * @param gain            boolean to display gain
     */
    protected ResultSet hiveList(String username, boolean inspection, boolean health, boolean honey, boolean queenproduction,
                                 boolean equiphive, boolean equipinven, boolean loss, boolean gain) {

        // Create sql statement
        sql = "select uh.username, uh.apiary, uh.hive";

        // Continue writing sql statement based on the boolean values
        if (inspection == true) {
            sql = sql.concat(", uh.inspection");
        }
        if (health == true) {
            sql = sql.concat(", uh.health");
        }
        if (honey == true) {
            sql = sql.concat(", uh.honey");
        }
        if (queenproduction == true) {
            sql = sql.concat(", uh.queenproduction");
        }
        if (equiphive == true) {
            sql = sql.concat(", uh.equiphive");
        }
        if (equipinven == true) {
            sql = sql.concat(", uh.equipinven");
        }
        if (loss == true) {
            sql = sql.concat(", uh.loss");
        }
        if (gain == true) {
            sql = sql.concat(", uh.gain");
        }

        // Finalize the sql statement
        sql = sql.concat(" from userhive uh where uh.username = \"" + username + "\";");

        try {
            // Execute the query
            rs = stmt.executeQuery(sql);
            System.out.println();

            return rs;
            // Print data received from the query

        } catch (SQLException e) {
            System.out.println("listHive: " + e.getMessage());
            return null;
        }
    }

    /**
     * This method is called to display data of hives that are selected by user to be displayed.
     * It calls getPreference stored procedures to get which information of hive should be displayed
     * form the database. Then it calls hiveList to return data of hives.
     *
     * @param username String for the user account
     * @return rs ResultSet that stores the data of hives
     */
    protected ResultSet getPreference(String username) {
        try {
            // Default boolean value for each column of data set
            boolean inspection = true;
            boolean health = true;
            boolean honey = true;
            boolean queenproduction = true;
            boolean equiphive = true;
            boolean equipinven = true;
            boolean loss = true;
            boolean gain = true;

            // Execute getPreference stored procedure from the database
            cstmt = conn.prepareCall("call getPreference(?, ?, ?, ?, ?, ?, ?, ?, ?);");
            cstmt.setString(1, username);
            cstmt.registerOutParameter(2, Types.BIGINT);
            cstmt.registerOutParameter(3, Types.BIGINT);
            cstmt.registerOutParameter(4, Types.BIGINT);
            cstmt.registerOutParameter(5, Types.BIGINT);
            cstmt.registerOutParameter(6, Types.BIGINT);
            cstmt.registerOutParameter(7, Types.BIGINT);
            cstmt.registerOutParameter(8, Types.BIGINT);
            cstmt.registerOutParameter(9, Types.BIGINT);
            cstmt.executeUpdate();

            // Translate int values from the data into boolean values
            if (cstmt.getInt(2) == 0) {
                inspection = false;
            }
            if (cstmt.getInt(3) == 0) {
                health = false;
            }
            if (cstmt.getInt(4) == 0) {
                honey = false;
            }
            if (cstmt.getInt(5) == 0) {
                queenproduction = false;
            }
            if (cstmt.getInt(6) == 0) {
                equiphive = false;
            }
            if (cstmt.getInt(7) == 0) {
                equipinven = false;
            }
            if (cstmt.getInt(8) == 0) {
                loss = false;
            }
            if (cstmt.getInt(9) == 0) {
                gain = false;
            }

            // Call hiveList to get data with selected information
            return hiveList(username, inspection, health, honey, queenproduction, equiphive, equipinven, loss, gain);
        } catch (SQLException e) {
            System.out.println("getPreference: " + e.getMessage());
        }
        return null;
    }


    /**
     * This method is called to edit the preference for information to be displayed by the user.
     * It calls editPreference stored procedure to update the preference in the database.
     *
     * @param username String for the user account
     * @param inspection True to be displayed. Otherwise, not displayed.
     * @param health True to be displayed. Otherwise, not displayed.
     * @param honey True to be displayed. Otherwise, not displayed.
     * @param queenproduction True to be displayed. Otherwise, not displayed.
     * @param equiphive True to be displayed. Otherwise, not displayed.
     * @param equipinven True to be displayed. Otherwise, not displayed.
     * @param loss True to be displayed. Otherwise, not displayed.
     * @param gain True to be displayed. Otherwise, not displayed.
     * @return String to check if the method is successfully executed
     */
    protected String editPreference(String username, boolean inspection, boolean health, boolean honey,
                                    boolean queenproduction, boolean equiphive, boolean equipinven, boolean loss, boolean gain) {
        try {
            // Default values for each column of data in the database
            int intinspection =0;
            int inthealth = 0;
            int inthoney = 0;
            int intqueenproduction=0;
            int intequiphive =0;
            int intequipinven=0;
            int intloss=0;
            int intgain=0;

            // Translate boolean values of parameters into integer value
            if (inspection == true) intinspection = 1;

            if (health == true) inthealth = 1;

            if (honey == true) inthoney = 1;

            if (queenproduction == true) intqueenproduction = 1;

            if (equiphive == true) intequiphive = 1;

            if (equipinven == true) intequipinven = 1;

            if (loss == true) intloss = 1;

            if (gain == true) intgain = 1;

            // Execute editPreference stored procedure
            cstmt = conn.prepareCall("call editPreference(?, ?, ?, ?, ?, ?, ?, ?, ?);");
            cstmt.setString(1, username);
            cstmt.setInt(2, intinspection);
            cstmt.setInt(3, inthealth);
            cstmt.setInt(4, inthoney);
            cstmt.setInt(5, intqueenproduction);
            cstmt.setInt(6, intequiphive);
            cstmt.setInt(7, intequipinven);
            cstmt.setInt(8, intloss);
            cstmt.setInt(9, intgain);
            cstmt.executeUpdate();

            return "Successfully updated preference";

        } catch (SQLException e) {
            return "editPreference: " + e.getMessage();

        }
    }

    /**
	 * This method is executed to display full information of a specific hive chosen
	 * by the user.
	 * 
	 * @param username String for the user account
	 * @param apiary   String for the apiary address
	 * @param hive     String for the name of the hive
	 * @return ResultSet that stores data of the hive
	 */
    protected ResultSet displaySpecificHive(String username, String apiary, String hive) {
        try {
            cstmt = conn.prepareCall("call displaySpecificHive(?, ?, ?, ?);");
            cstmt.setString(1, username);
            cstmt.setString(2, apiary);
            cstmt.setString(3, hive);
            cstmt.registerOutParameter(4, Types.VARCHAR);

            ResultSet rs = cstmt.executeQuery();

            String status = cstmt.getString(4);
            if (status.equals("Success")) {
                return rs;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("displaySpecificHive: " + e.getMessage());
        }
        return null;
    }
    
    /**
	 * This method is called to convert ResultSet into an array of arrayLists.
	 * The first row will be filled with the column names of the ResultSet.
	 *
	 * @param rs ResultSet that stores the data
	 * @return Array of Arraylist that stores the data of ResultSet
	 */
    static ArrayList<String>[] hiveSelectedInfo(ResultSet rs) {

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCol = rsmd.getColumnCount();

            ArrayList<String>[] result = new ArrayList[numCol];
            
            // Initialize array of arraylists
            for (int i = 0; i < numCol; i++) {
                result[i] = new ArrayList<String>();
                // Fill first row by column names
                result[i].add(rsmd.getColumnName(i + 1));
            }
            
            // Write data in the array
            while (rs.next()) {
                for (int i = 1; i <= numCol; i++) {
                    result[i - 1].add(rs.getString(i));
                }
            }

            return result;
        } catch (SQLException e) {
            System.out.println("hiveSelectedInfo: " + e.getMessage());
            return null;
        }

    }
}
