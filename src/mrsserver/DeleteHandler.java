
package mrsserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
/**
 * Handles DELETE statements received by the server.
 * @author Emlan
 */
public class DeleteHandler {

    private static String ipString;
    private static String JDBCportString;
    private static String userString;
    private static String passString;
    private static ArrayList<String> deleteStatus = new ArrayList<String>();
/**
 * Sends the incoming query to the database.
 * @param incommingDelete incoming query
 * @return [delete success/fail]
 */
    public static ArrayList<String> deleteHandler(String incommingDelete) {
        DBConnection();
        try {

            //setup connection
            Class.forName("com.mysql.jdbc.Driver");
            String connection = "jdbc:mysql://" + ipString + ":" + JDBCportString + "/mysql?"
                    + "user=" + userString + "&password=" + passString;
           MRSServer.add("Database connection to:" + ipString + ":" + JDBCportString + "established.");
           Connection serverConnection = DriverManager.getConnection(connection);

            // create query
            String delete = incommingDelete;

            //create statement
            Statement statement = serverConnection.createStatement();
            MRSServer.add("Statement created: ");

            //execute statement
            statement.execute(delete);
            deleteStatus.add("Delete Successful");
        } catch (ClassNotFoundException classExceptionString) {
            MRSServer.add("Class not found: " + classExceptionString);
            deleteStatus.add("Delete Failed:" + classExceptionString);
        } catch (SQLException sqlException) {
            //Do nothing
            deleteStatus.add("Delete Failed:" + sqlException);
        }

        return (deleteStatus);
    }
    /**
     * Establishes a connection with the database using the databaseHandler
     * class.
     */
    public static void DBConnection() {
        try {
            ipString = DatabaseHandler.getIP();
            JDBCportString = DatabaseHandler.getPort();
            userString = DatabaseHandler.getUName();
            passString = DatabaseHandler.getPass();
        } catch (FileNotFoundException exp) {
        } catch (IOException exp) {
        }
    }
}