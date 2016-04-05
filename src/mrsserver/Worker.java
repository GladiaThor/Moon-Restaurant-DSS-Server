package mrsserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;

/**
 * Worker V2.2 Preforms delegation and answering duties on the server. Threaded
 * to allow multiple connections.
 *
 * @author (>^_^)> Claxxess<(^_^<)
 * @version 2.2
 *
 */
public class Worker extends Thread {

    private Socket outSocket = null;
    private String ip;
    private int port;
    private PrintWriter outWriter;
    private BufferedReader inReader = null;

    /**
     * Preforms the basic setup for the incoming connection.
     *
     * @param socket
     */
    public Worker(Socket socket) {
        super("Worker");
        outSocket = socket;
        MRSServer.add("Socket:" + outSocket);
    }

    /**
     * Runs the inPut method once initiated through the .start(); method. Also
     * adds a message to the main server frame confirming the incoming
     * connection.
     */
    public void run() {
        MRSServer.add("Connection accepted");
        inPut();
    }

    /**
     * Discovers the purpose of the incoming SQL command and invokes the correct
     * handler
     *
     */
    public ArrayList<String> inPut() {
        ArrayList<String> query = null;
        try {
            ip = outSocket.getInetAddress().toString();
            port = outSocket.getPort();
            inReader = new BufferedReader(new InputStreamReader(outSocket.getInputStream()));
            outWriter = new PrintWriter(outSocket.getOutputStream(), true);
            String inputLine;

            while ((inputLine = inReader.readLine()) != null) {
                if (inputLine.startsWith("%%%SELECT")) {
                    new Statistician(inputLine).start();
                    String[] reQuery = inputLine.split("%%%", 0);
                    inputLine = reQuery[1];
                    query = director(inputLine);
                    outPut(query);
                    query.clear();
                } else {
                    query = director(inputLine);
                    outPut(query);
                    query.clear();
                }
            }
        } catch (IOException exp) {
            MRSServer.add("IOException in inReader:" + exp + outSocket.getInetAddress());
        }
        MRSServer.add("input returened");
        return (query);
    }
    /*
     *Returns the result of the statement back to the client
     * 
     */

    public void outPut(ArrayList<String> outputAL) {
        String outputString = outputAL.toString();
        MRSServer.add("Writer Writes" + outputString);
        outWriter.println(outputString);
        MRSServer.add("communication complete");
    }

    /**
     * Can be used to induce a short wait.
     *
     * @param miliSec int
     */
    public static void waitSome(int miliSec) {
        try {
            Thread.sleep(miliSec);
        } catch (InterruptedException exp) {
            MRSServer.add("Method waitSome in Worker failed due to:" + exp);
        }
    }

    /**
     * Directs statements to the correct handler
     *
     * @param inputLine String
     */
    public static ArrayList<String> director(String inputLine) {
        ArrayList<String> query = null;
        if (inputLine.startsWith("SELECT")) {
            MRSServer.add("Query recieved:" + inputLine);
            query = QueryHandler.queryHandler(inputLine);
            return query;

        } else if (inputLine.startsWith("BAM")) {
            MRSServer.add("BAM");

            return query;

        } else if (inputLine.startsWith("UPDATE")) {
            MRSServer.add("Query recieved:" + inputLine);
            query = UpdateHandler.updateHandler(inputLine);
            return query;

        } else if (inputLine.startsWith("INSERT")) {
            MRSServer.add("Query recieved:" + inputLine);
            query = InsertHandler.insertHandler(inputLine);
            return query;

        } else if (inputLine.startsWith("DELETE")) {
            MRSServer.add("Query recieved:" + inputLine);
            query = DeleteHandler.deleteHandler(inputLine);
            return query;

        } else if (inputLine.startsWith("CHECK")) {
            MRSServer.add("Query recieved:" + inputLine);
            query = Authenticator.authenticate(inputLine);
            return query;


        } else if (inputLine.startsWith("STAT")) {
            String[] reQuery = inputLine.split(" ", 0);
            if (reQuery[1].equals("PRICE")) {
                MRSServer.add("Query recieved:" + inputLine);
                query = Statistician.priceRanger();
            } else if (reQuery[1].equals("FOODTYPETOTAL")) {
                MRSServer.add("Query recieved:" + inputLine);
                query = Statistician.foodType();
            } else if (reQuery[1].equals("REVSCORE")) {
                MRSServer.add("Query recieved:" + inputLine);
                query = Statistician.revScoreTotals();
            } else if (reQuery[1].equals("SCORE")) {
                MRSServer.add("Query recieved:" + inputLine);
                query = Statistician.restScoreStatsSingleRestaurant(reQuery[2]);
            } else if (reQuery[1].equals("STYLE")) {
                MRSServer.add("Query recieved:" + inputLine);
                query = Statistician.styleSearchChecker(reQuery[2]);
            } else if (reQuery[1].equals("STYLETOTAL")) {
                MRSServer.add("Query recieved:" + inputLine);
                query = Statistician.restStyleTotal();
            } else if (reQuery[1].equals("AFTERWORKTOTAL")) {
                MRSServer.add("Query recieved:" + inputLine);
                query = Statistician.afterWorkTotal();
            } else if (reQuery[1].equals("TAKEAWAYTOTAL")) {
                MRSServer.add("Query recieved:" + inputLine);
                query = Statistician.takeAwayTotal();
            } else if (reQuery[1].equals("STUDENTDISCOUNTTOTAL")) {
                MRSServer.add("Query recieved:" + inputLine);
                query = Statistician.studentDiscountTotal();
            }
            return query;

        }
        return null;

    }
}
