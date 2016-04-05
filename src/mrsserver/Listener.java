/*
 * 
 *@author
 * 
 */
package mrsserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/** 
 * When initiated awaits instructions on port 9999.
 * listens for connections and starts instances of worker when needed.
 * @author (>^_^)> Claxxess<(^_^<) 
 * @version 1.0
 */
public class Listener extends Thread {
    //Initiate universal values
    ServerSocket connectionSocket;
    Socket outSocket = null;

    /**
     * When connection established, initiates an instance of the worker class
     * to handle the incoming request. 
     */
    static class ear extends Listener {
        public ear() {
            int portNr = 9999;

            try {
                //Start the listener
                connectionSocket = new ServerSocket(portNr);
                MRSServer.add("Listener ready and waiting");
            }
            catch (IOException exp) {
                MRSServer.add("IOException in connectionSocket:\n" + exp);
            }

            try {
                //On accepted conection start a new instance of the worker class
                while (true) {
                    new Worker(connectionSocket.accept()).start();
                }
            }
            catch (IOException exp) {
                MRSServer.add("IOException in inSOcket:" + exp);
            }
        }
    }
}