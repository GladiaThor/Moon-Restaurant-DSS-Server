
package mrsserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
/** 
 * Handles SELECT statements received by the server.
 * @author (>^_^)> Claxxess<(^_^<)
 * @version 1.1
 */
public class QueryHandler {

    private static String ipString;
    private static String JDBCportString;
    private static String userString;
    private static String passString;

    /**
     * Resolves the incoming query and returns an Array List with the results.
     *
     * @param incommingQuery
     * @return Query Results
     */
    public static ArrayList<String> queryHandler(String incommingQuery) {


        DBConnection();
        ArrayList<String> results = new ArrayList<String>();
        try {
            //setup connection
            Class.forName("com.mysql.jdbc.Driver");
            String connectard = "jdbc:mysql://" + ipString + ":" + JDBCportString + "/mysql?"
                    + "user=" + userString + "&password=" + passString;
            MRSServer.add("Database connection to:" + ipString + ":" + JDBCportString + "established.");
            Connection serverConnection = DriverManager.getConnection(connectard);
            //establish create query
            String query = incommingQuery;
            

            //create statement
            Statement statement = serverConnection.createStatement();

            //execute statement
            ResultSet statementResult = statement.executeQuery(query);

            int columnInt = 1;
            ResultSetMetaData resultsMeta = statementResult.getMetaData();
            int metaCountInt = resultsMeta.getColumnCount();
            //Build results into an ArrayList
            while (statementResult.next()) {
                while (columnInt <= metaCountInt) {
                    results.add("$" + statementResult.getString(columnInt) + "$");
                    columnInt++;
                }
                columnInt = 1;
            }

            statementResult.close();
            statement.close();

        } catch (ClassNotFoundException classExceptionString) {
            MRSServer.add("Class not found:" + classExceptionString);
            results.add("Query Failed:" + classExceptionString);
        } catch (java.sql.SQLException sqlExceptionString) {
            MRSServer.add("SQL Exception:" + sqlExceptionString);
            results.add("Query Failed:" + sqlExceptionString);
        }

        MRSServer.add("resuslts returned");
        //Return Query Results
        return (results);
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
