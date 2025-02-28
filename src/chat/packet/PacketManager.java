package chat.packet;

import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

public class PacketManager {

    private final static Map<Short, Class<? extends Packet>> packets = new HashMap<>();

    static {
        packets.put((short) 1, PacketAuthorize.class);
    }

    public static void read(short id, DataInputStream dis) {
        try {
            Packet packet = packets.get(id).newInstance();
            packet.read(dis);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}