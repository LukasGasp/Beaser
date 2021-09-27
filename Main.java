import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import javax.xml.crypto.Data;

import java.util.logging.Level;

public class Main{

    public static void main(String[] args){

        String s = null;
        int current = 0;
        // Logger
        Logger logger;

        int[] dmx;
        boolean running;

        dmx  = new int[512];
        logger  = Logger.getLogger("Logger for main");
        running = true;

        try {
        
            // Run Python to get sacn
            Process p = Runtime.getRuntime().exec("python3 getsacn.py");
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            // read the output from the command (Success) and put it in Array DMX
            while ((s = stdInput.readLine()) != null) {
                if (s.equals("start")){
                    current = 0;
                    logger.log(Level.FINEST, "New Data");
                } else {
                    int i=Integer.parseInt(s); 
                    dmx[current] = i;
                    current = current + 1;
                }
            }

            // read any errors from the attempted command (Errors)
            while ((s = stdError.readLine()) != null) {
                logger.log(Level.WARNING, "Error from Command Line");
                logger.log(Level.WARNING, s);
            }
            
            System.exit(0);
        }
        catch (IOException e) {
            logger.log(Level.WARNING, "IO-Exception");
            e.printStackTrace();
            System.exit(-1);
        }

    }
}