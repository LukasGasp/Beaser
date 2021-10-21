import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.net.*;

import java.util.logging.Level;

public class Main{

    static Logger logger = Logger.getLogger("Logger for main");
    int[] dmx;
    int port = 1994;
    static Screen mainscreen;
    ServerSocket server;
    static boolean run = true;

    static Process python;

    public Main() throws NumberFormatException, IOException{

        dmx  = new int[512];

        String message;

        server = new ServerSocket(port);
        logger.log(Level.INFO, "Java listening on Port " + String.valueOf(port));

        while(run) {
            logger.log(Level.FINE, "Listening");
            Socket client = server.accept();
            logger.log(Level.FINER, "Got data");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
 
            message = in.readLine();
            logger.log(Level.FINEST, "Got message: " + message);
            String[] parts = message.split(":");
            for(int i=1; i<parts.length; i++) {
                dmx[i - 1] = Integer.parseInt(parts[i]);
            }
            //for(int i=1; i<dmx.length; i++){  //Prints received Data
            //    System.out.println(dmx[i]);
            //}
            mainscreen.givedata(dmx);
        }
        server.close();
        logger.log(Level.WARNING, "Closed Server");
    }

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run(){
                python.destroy(); // Stops python Process #clean
                run = false;
                System.out.println("Shutdown");
            }
        }, "Shutdown-thread"));
        logger.log(Level.INFO, "Creating Screen");
        mainscreen = new Screen();
        logger.log(Level.INFO, "Starting Python listerner");
        python = Runtime.getRuntime().exec("python3 getsacn.py");
        logger.log(Level.INFO, "Starting Main Function");
        new Main();
        logger.log(Level.WARNING, "Main function finished");
     }
}