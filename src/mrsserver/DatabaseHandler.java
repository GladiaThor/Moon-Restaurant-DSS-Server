/**
 * 
 *
 * @author Claxxess <(^_^<)
 */
package mrsserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * Handles the connection information for the database connection.
 * @author (>^_^)> Claxxess<(^_^<)
 * @version 1.1
 */
public class DatabaseHandler {

    static File DBSettings = new File("Settings\\DBSettings.txt");
    static BufferedReader infoReader;

    /**
     * Gets the IP for the current DB server from the DBSettings.txt
     * file in the Settings folder.
     * @return IPadress
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String getIP() throws FileNotFoundException, IOException {
        infoReader = new BufferedReader(new FileReader(DBSettings));
        String IP = infoReader.readLine();
        infoReader.close();
        return IP;
    }

    /**
     * Gets the port for the current DB server from the DBSettings.txt
     * file in the Settings folder.
     * @return port number
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String getPort() throws FileNotFoundException, IOException {
        infoReader = new BufferedReader(new FileReader(DBSettings));
        String port = infoReader.readLine();
        port = infoReader.readLine();
        infoReader.close();
        return port;
    }

    /**
     * Gets the user name for the current DB server from the DBSettings.txt
     * file in the Settings folder.
     * @return userName
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String getUName() throws FileNotFoundException, IOException {
        infoReader = new BufferedReader(new FileReader(DBSettings));
        String uName = infoReader.readLine();
        uName = infoReader.readLine();
        uName = infoReader.readLine();
        infoReader.close();
        return uName;
    }

    /**
     * Gets the password for the current DB server from the DBSettings.txt
     * file in the Settings folder.
     * @return password
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String getPass() throws FileNotFoundException, IOException {
        infoReader = new BufferedReader(new FileReader(DBSettings));
        String pass = infoReader.readLine();
        pass = infoReader.readLine();
        pass = infoReader.readLine();
        pass = infoReader.readLine();
        infoReader.close();
        return pass;
    }
}