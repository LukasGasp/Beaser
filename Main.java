import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import java.util.logging.Level;

public class Main{

    static Logger logger = Logger.getLogger("Logger for main");
    int[] dmx;
    boolean running = true;
    static Screen mainscreen;

    public Main(){

        String s = null;

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
                if (s.equals("stop")){
                    taskcheck();
                } else {
                    String[] parts = s.split(":");
                    if(parts.length!=2){throw new IllegalArgumentException();}
                    int index = Integer.parseInt(parts[0]);
                    int data = Integer.parseInt(parts[1]);
                    dmx[index] = data;
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

    public void taskcheck(){
        logger.log(Level.FINEST, "Taskforce 1");
        System.out.println(dmx[0]);
        if(dmx[0] == 0){
            System.out.println("Mode: Off");
            mainscreen.setmode(0);
        }
    }

    public static void main(String[] args) {
        mainscreen = new Screen();
        logger.log(Level.INFO, "Created Screen");
        logger.log(Level.INFO, "Starting Main Function");
        new Main();
        logger.log(Level.WARNING, "Main function finished");
     }
}