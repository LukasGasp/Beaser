import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static Logger logger = Logger.getLogger("Beaser logger");
    static Screen mainscreen;

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws Exception{
        mainscreen = new Screen();

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
        }
    }
}
