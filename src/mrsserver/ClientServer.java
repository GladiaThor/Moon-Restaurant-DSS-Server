
package mrsserver;

import java.io.*;
import java.net.*;
/**
 * Handles the client side connection of the server.
 * Should be copied into the client. 
 * @author Emily
 * @version 1.3
 */

public class ClientServer {

    private static int localPort;
    private static String inputString;
/**
 * Returns server response to inputString.
 * @param inputString String to send to the server
 * @return The servers answer.
 */
    public static String serverRespons(String inputString) {
        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            //Obtain an address object of the server, establish a socket connetion

            clientSocket = new Socket("localhost", 9999);
            localPort = clientSocket.getLocalPort();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
            out.println(inputString);

            String userInput;
            userInput = in.readLine();

            out.close();
            in.close();
            clientSocket.close();
            return userInput;
        }
        catch (UnknownHostException e) {
            System.err.println("Cannot find information about host: localhost.");
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: localhost.");
            System.exit(1);
        }
        return null;
    }
}