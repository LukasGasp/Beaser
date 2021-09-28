import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main{

    static Logger logger = Logger.getLogger("Logger for main");

    public Main(){

        String s = null;
        int current = 0;

        int[] dmx;

        dmx  = new int[512];

        try {
        
            // Run Python to get sacn
            Process p = Runtime.getRuntime().exec("python3 getsacn.py");
            logger.log(Level.INFO, "Created python listener");
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            // read the output from the command (Success) and put it in Array DMX
            while ((s = stdInput.readLine()) != null) {
                if (s.equals("start")){
                    current = 0;
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

    public static void main(String[] args) {
        new Screen();
        logger.log(Level.INFO, "Created Screen");
        logger.log(Level.INFO, "Starting Main Function");
        new Main();
        logger.log(Level.WARNING, "Main function finished");
     }
}