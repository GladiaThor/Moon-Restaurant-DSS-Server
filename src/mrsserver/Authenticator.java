
package mrsserver;

import java.util.ArrayList;
/**
 * Handles the authentication for the owner through the use of password and username
 *@author (>^_^)> Claxxess <(^_^<)
 *@version 1.2
 **/
public class Authenticator {

    /**
     * Makes sure that the password and username provided match and
     * if so returns the owner ID.
     *
     * @param input 
     * @return ownerID
     */
    public static ArrayList<String> authenticate(String input) {
        String[] inputArray = input.split("#");
        String pass = inputArray[2];
        String uname = inputArray[1];
        ArrayList<String> authen = new ArrayList();
        String passcheck = ClientServer.serverRespons("SELECT password FROM rdds_moon.owner WHERE userName='" + uname + "'");
        passcheck = passcheck.replace("$", "").replace("[", "").replace("]", "");
        MRSServer.add(passcheck);
        if (passcheck.equals(pass)) {
            authen.add(ClientServer.serverRespons("SELECT ownerID FROM rdds_moon.owner WHERE username ='"+uname+"'"));
            return authen;
        } else {
            authen.add("FAIL");
            MRSServer.add("AUTHENTICATION OF: "+uname+" WITH PASSWORD: "+pass+" HAS FAILED" );
            return authen;
        }



    }
    
    
    
}
