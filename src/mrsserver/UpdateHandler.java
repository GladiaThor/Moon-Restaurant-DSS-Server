
package mrsserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * Handles UPDATE statements received by the server.
 * @author (>^_^)> Claxxess<(^_^<)
 * @version 1.1
 */
public class UpdateHandler {

    private static String ipString;
    private static String JDBCportString;
    private static String userString;
    private static String passString;
    private static ArrayList<String> updateStatusString = new ArrayList<String>();

    /**
     * Resolves the incoming update and returns an Array List reporting whether
     * or not the update was successful.
     *
     * @param incommingUpdate
     * @return an arrayList of the server responses.
     */
    public static ArrayList<String> updateHandler(String incommingUpdate) {
        DBConnection();
        try {

            //setup connection
            Class.forName("com.mysql.jdbc.Driver");
            String connectard = "jdbc:mysql://" + ipString + ":" + JDBCportString + "/mysql?"
                    + "user=" + userString + "&password=" + passString;
            MRSServer.add("Database connection to:" + ipString + ":" + JDBCportString + "established.");
            Connection serverConnection = DriverManager.getConnection(connectard);
            //establish create query
            String update = incommingUpdate.replace("'null'", "null").replace("''","null");

            //create statement
            Statement statement = serverConnection.createStatement();
            MRSServer.add("statement Created:");

            //execute statement
            statement.executeUpdate(update);

            //Add sucess of the string update to ArrayList
            updateStatusString.add("Update Successful");


        } catch (ClassNotFoundException classExceptionString) {
            MRSServer.add("Class not found:" + classExceptionString);
            //Add failiure of update and relevant reason to ArrayList
            updateStatusString.add("Update Failed:" + classExceptionString);
        } catch (java.sql.SQLException sqlExceptionString) {
            MRSServer.add("SQL Exception:" + sqlExceptionString);
            //Add failiure of update and relevant reason to ArrayList
            updateStatusString.add("Update Failed:" + sqlExceptionString);
        }
        //Retun Update status
        return (updateStatusString);
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