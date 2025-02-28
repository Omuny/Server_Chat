package chat.packet;

import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

public class PacketManager {

    private final static Map<Short, Class<? extends Packet>> packets = new HashMap<>();

    static {
        packets.put((short) 1, PacketAuthorize.class);
    }

    public static Packet getPacket(short id) {
        try {
            return packets.get(id).newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
            return  null;
        }
    }
}