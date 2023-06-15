import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static Logger logger = Logger.getLogger("Beaser logger");
    static Screen mainscreen;
    static Interface interface1;

    static int panelamount = 5;

    public static void main(String[] args) throws Exception {
        interface1 = new Interface();
        run();
    }

    public static void run() throws Exception{
        mainscreen = new Screen(panelamount);
        interface1.setpanels(panelamount);
        interface1.setdmxaddress(1);

        DatagramSocket socket = new DatagramSocket(5568);
        byte[] buffer = new byte[638];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        DMX dmx;
        dmx = new DMX();

        logger.log(Level.INFO, "Listening...");
        socket.receive(packet);
        dmx.processPacket(buffer);
        logger.log(Level.INFO, "Universe: " + dmx.universe);

        while (true) {
            socket.receive(packet);
            dmx.processPacket(buffer);
            //TODO: Only do something if data has changed!
            mainscreen.givedata(dmx.data);
            interface1.setUniverse(dmx.universe);
        }
    }
}
