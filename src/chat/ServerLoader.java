package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerLoader {

    private static ServerSocket server;
    private static Map<Socket, ClientHandler> handlers = new HashMap<>();

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
                ClientHandler handler = new ClientHandler(client);
                handler.start();
                handlers.put(client, handler);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }
    }

    private static void start() {
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

    public static  ClientHandler getHandler(Socket socket) {
        return handlers.get(socket);
    }

    public static void invalidate(Socket socket) {
        handlers.remove(socket);
    }

}