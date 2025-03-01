package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerHandler extends Thread {

    private final ServerSocket server;

    ServerHandler(ServerSocket server) {
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {
            // Обработка подключающихся клиентов
            try {
                Socket client = server.accept();
                ClientHandler handler = new ClientHandler(client);
                handler.start();
                ServerLoader.handlers.put(client, handler);
            } catch (SocketException ex) {
                return;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }
}