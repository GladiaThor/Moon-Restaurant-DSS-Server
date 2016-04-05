
package mrsserver;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 * Main server window. Handles the extraction of server logs and 
 * database configuration.
 * @author Claxxess & Emlan
 * @version 2.6R
 */
public class MRSServer {

    static JTextPane TF = new JTextPane();
    static JFrameClose serverFrame = new JFrameClose();
    static JButton printButton = new JButton();
    static JButton serverCon = new JButton();
    static Writer logSaver;

    /**
     * Main Method in the MRSServer Class in charge of creating and maintaining
     * window functions.
     *
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     * @author Claxxess
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        JScrollPane cPanel = new JScrollPane(TF);
        serverFrame.setLayout(new BorderLayout());
        serverFrame.add(cPanel, BorderLayout.CENTER);
        serverFrame.setSize(765, 430);
        serverFrame.setResizable(false);
        TF.setEditable(false);

        JPanel southPanel = new JPanel();
        serverFrame.add(southPanel, BorderLayout.SOUTH);
        southPanel.add(printButton);
        southPanel.add(serverCon);
        serverFrame.setTitle("MRS Server V 2.6 (BETA)");
        printButton.setText("Save Log");
        serverCon.setText("Reconfigure Database Connection");
        serverCon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                InfoFrame.start infoFrame = new InfoFrame.start(true);
            }
        });
        serverCon.setVisible(true);
        printButton.setVisible(true);

        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    String timeStamp = new SimpleDateFormat("yyyyMMdd HHmmss").format(Calendar.getInstance().getTime());
                    String logName = "Logs\\[" + timeStamp + "]" + "ServerLog.txt";
                    File serverFile = new File(logName);
                    logSaver = new PrintWriter(serverFile);
                    String callLine = System.getProperty("line.separator");
                    String TFText = TF.getText().replace("|", callLine);
                    JOptionPane.showMessageDialog(null, "A log file was created with the name:" + logName
                            + "\n at location" + serverFile.getCanonicalPath(), "LogFile", JOptionPane.INFORMATION_MESSAGE);
                    logSaver.write(TFText);
                    logSaver.close();

                } catch (IOException exp) {
                    System.out.println(exp);
                }
            }
        });


        serverFrame.setVisible(true);

        add("Checking Database Connection Settings");
        File dBSetFile = new File("Settings\\DBSettings.txt");
        try {
            FileReader servCheck = new FileReader(dBSetFile);
        } catch (FileNotFoundException ex) {
            add("Database Connection Settings not found");
            add("Runing First Startup Database Settings Manager");

            InfoFrame.start infoFrame = new InfoFrame.start(true);

            while (true) {
                Listener Server = new Listener.ear();
            }

        }
        add("Settings Found");
        showDBInfo();

        while (true) {
            Listener Server = new Listener.ear();
        }

    }

    /**
     * Adds information to the MRSServer window.
     *
     * @param add
     * @author Claxxess
     */
    public static void add(String add) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        TF.setText(TF.getText() + "\n|" + "[" + timeStamp + "]" + add);

    }

    /**
     * Shows the current Database Information in the server window.
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @author Claxxess
     */
    public static void showDBInfo() throws FileNotFoundException, IOException {
        add("Database Server IP: " + DatabaseHandler.getIP());
        add("Database Server Port: " + DatabaseHandler.getPort());
        add("Connected to Databse as User: " + DatabaseHandler.getUName());

    }
}
