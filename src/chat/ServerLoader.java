package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLoader {

    private static ServerSocket server;

    public static void main(String[] args) {
        start();
        handle();
        end();
    }

    private  static void handle() {
        while (true) {
            // Обработка подключающихся клиентов
            try {
                Socket client = server.accept();
                new ClientHandler(client);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }

    public static void start() {
        try {
            server = new ServerSocket(1446);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void end() {
        try {
            server.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}