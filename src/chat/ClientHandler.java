package chat;

import chat.packet.Packet;
import chat.packet.PacketAuthorize;

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
            Packet packet;
            switch(id) {
                case 1: {
                    packet = new PacketAuthorize();
                    break;
                }
            }
            packet.read(dis);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}