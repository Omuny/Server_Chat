package chat;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
            try {
                DataInputStream dis = new DataInputStream(client.getInputStream());
                if(dis.available() > 0) {
                    // Чтение и обработка
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }

}