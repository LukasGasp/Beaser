import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.io.*;
import java.net.*;

import java.util.logging.Level;

public class Main{

    static Logger logger = Logger.getLogger("Logger for main");
    int[] dmx;
    int port = 1994;
    static Screen mainscreen;

    public Main() throws NumberFormatException, IOException{

        dmx  = new int[512];

        String message;

        ServerSocket server = new ServerSocket(port);
        logger.log(Level.INFO, "Java listening on Port " + String.valueOf(port));
 
        boolean run = true;
        while(run) {
            logger.log(Level.FINE, "Listening");
            Socket client = server.accept();
            logger.log(Level.FINER, "Got data");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
 
            message = in.readLine();
            logger.log(Level.FINE, "Got message: " + message);
            String[] parts = message.split(":");
            for(int i=1; i<parts.length; i++) {
                dmx[i - 1] = Integer.parseInt(parts[i]);
            }
            mainscreen.givedata(dmx);
        }
        System.exit(0);

    }

    public static void main(String[] args) throws IOException {
        mainscreen = new Screen();
        logger.log(Level.INFO, "Created Screen");
        logger.log(Level.INFO, "Starting Main Function");
        new Main();
        logger.log(Level.WARNING, "Main function finished");
     }
}