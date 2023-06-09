import java.util.Arrays;

public class DMX {

    int[] data;
    int universe;

    public DMX() {
        data = new int[512];
    }

    public void processPacket(byte[] buffer) {
        universe = (buffer[113] & 0xFF) * 256 + (buffer[114] & 0xFF);
        byte[] dmxData = Arrays.copyOfRange(buffer, 126, buffer.length);

        for(int i = 0; i < dmxData.length; i++) {
            byte signedByte = dmxData[i];
            int unsignedByte = signedByte & 0xFF;
            data[i] = unsignedByte;
        }
    }
}
