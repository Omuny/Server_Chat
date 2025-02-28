package chat;

import chat.packet.Packet;
import chat.packet.PacketAuthorize;
import chat.packet.PacketManager;

import java.io.DataInputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
        start();
    }

    @Override
    public void run() {
        while (true) {
            readData();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }

    private void readData() {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            if (dis.available() <= 0) {
                return;
            }
            short id = dis.readShort();
            // чтение пакета
            Packet packet = PacketManager.getPacket(id);
            packet.setSocket(client);
            packet.read(dis);
            packet.handle();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}