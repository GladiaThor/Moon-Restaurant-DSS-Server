
package mrsserver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
/**
 * Handles the database setup.
 * @author (>^_^)> Claxxess<(^_^<)& Emlan
 * @version 2.0
 */
public class InfoFrame {
/**
 * Initilization for the InfoFrame.
 * P.s God help you if you want to use it.
 */
    static class start {

        String returnMsg = "Attempting to Reconfigure Database Connection";
        JFrame infoFrame = new JFrame();
        JTextField userField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField ipField = new JTextField();
        JTextField portField = new JTextField();
        JLabel Frame = new JLabel();
        JLabel user = new JLabel("User name");
        JLabel password = new JLabel("Password");
        JLabel ip = new JLabel("Server Ip");
        JLabel port = new JLabel("Server port");
        JButton okBut = new JButton("OK");

        start(boolean visible) {
            infoFrame.setDefaultCloseOperation(hide());

            infoFrame.setSize(180, 130);
            frame();
            infoFrame.setVisible(visible);
        }

        public int hide() {
            infoFrame.setVisible(false);
           
            MRSServer.add(returnMsg);
           
            return 1;
        }
    /**
     * Creates the Database setup frame
     *
     * @param port
     * @author Claxxess & Emlan
     */
        public void frame() {
            infoFrame.setLayout(new GridLayout(0, 2));
            infoFrame.add(user);
            infoFrame.add(userField);
            infoFrame.add(password);
            infoFrame.add(passwordField);
            infoFrame.add(ip);
            infoFrame.add(ipField);
            infoFrame.add(port);
            infoFrame.add(portField);
            infoFrame.add(okBut);
            
            okBut.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    boolean ipCheckBol = ipValid(ipField.getText());
                    boolean portCheckBol = portCheck(portField.getText());
                    if (ipCheckBol == false) {
                        JOptionPane.showMessageDialog(null, null, "Invalid IP adress", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (portCheckBol == false) {
                        JOptionPane.showMessageDialog(null, null, "Invalid portNumber", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        PrintWriter DBSave = null;
                        File DBSettingsFile = new File("Settings\\DBSettings.txt");
                        try {

                            DBSave = new PrintWriter(DBSettingsFile);
                        } catch (FileNotFoundException exp) {
                            try {
                                DBSettingsFile.createNewFile();
                                String lineSep = System.getProperty("line.separator");
                                String DBInfoString = ipField.getText()
                                        + lineSep + portField.getText()
                                        + lineSep + userField.getText()
                                        + lineSep + passwordField.getText();
                                DBSave.print(DBInfoString);
                                returnMsg = "New Server Configurations Saved";

                                hide();
                                DBSave.close();
                                MRSServer.showDBInfo();
                            } catch (IOException exp2) {
                                MRSServer.add("Failed to create settings file for the database due to:\n"
                                        + "exp2");
                            }
                        }
                        String lineSep = System.getProperty("line.separator");
                        String DBInfoString = ipField.getText()
                                + lineSep + portField.getText()
                                + lineSep + userField.getText()
                                + lineSep + passwordField.getText();
                        DBSave.print(DBInfoString);
                        returnMsg = "New Server Configurations Saved";
                        try {

                            hide();
                            DBSave.close();
                            MRSServer.showDBInfo();
                        } catch (FileNotFoundException ex) {
                        } catch (IOException ex) {
                        }

                    }
                }
            });


        }
    /**
     * Makes sure that the IP address in the IP address field matches the standards
     * for valid IP address.
     *
     * @param ip
     * @return validity
     * @author Claxxess
     */
        public boolean ipValid(String ip) {
            String ipPatternString =
                    "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
            Pattern IPPattern = Pattern.compile(ipPatternString);
            Matcher IPMatcher = IPPattern.matcher(ip);
            return IPMatcher.matches();

        }
    /**
     * Makes sure that the port number in the port field matches the standards
     * for valid port numbers
     *
     * @param port
     * @return validity
     * @author Claxxess
     */
        public boolean portCheck(String port) { 
            String portPatternString = "\\b[1-9][0-9]{2,4}\\b";
            Pattern portPattern = Pattern.compile(portPatternString);
            Matcher portMatcher = portPattern.matcher(port);
            return portMatcher.matches();
        }
    }
}
