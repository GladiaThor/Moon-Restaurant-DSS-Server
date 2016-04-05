
package mrsserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
/**
 * Handles INSERT statements received by the server.
 * @author Emlan
 */
public class InsertHandler {

    private static String ipString;
    private static String JDBCportString;
    private static String userString;
    private static String passString;
    private static ArrayList<String> insertStatus = new ArrayList<String>();
/**
 * Sends the incoming query to the database.
 * @param incommingInsert the incoming query
 * @return [insert success/fail]
 */
    public static ArrayList<String> insertHandler(String incommingInsert) {
        DBConnection();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connection = "jdbc:mysql://" + ipString + ":" + JDBCportString + "/mysql?"
                    + "user=" + userString + "&password=" + passString;
            MRSServer.add("Database connection to:" + ipString + ":" + JDBCportString + "established.");
            Connection serverConnection = DriverManager.getConnection(connection);

            //establish create query
            String insert = incommingInsert.replace("'null'", "null").replace("''","null");

            //creat statement
            Statement statement = serverConnection.createStatement();
            MRSServer.add("Statement created: ");

            //execute statement
            statement.executeUpdate(insert);
            insertStatus.add("Insert Successful");
        } catch (ClassNotFoundException classExceptionString) {
            MRSServer.add("Class not found: " + classExceptionString);
            insertStatus.add("Insert Failed:" + classExceptionString);
        } catch (SQLException exp) {
            //Do nothing
            insertStatus.add("Insert Failed:" + exp);
        }
        return (insertStatus);
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
