package chat;

import chat.packet.Packet;

import javax.swing.event.InternalFrameListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerLoader {

    private static ServerSocket server;
    private static ServerHandler handler;
    public static Map<Socket, ClientHandler> handlers = new HashMap<>();

    public static void main(String[] args) {
        start();
        handle();
        end();
    }

    private static void handle() {
        handler = new ServerHandler(server);
        handler.start();
        readChat();
    }

    public static void sendPacket(Socket receiver, Packet packet) {
        try {
            DataOutputStream dos = new DataOutputStream(receiver.getOutputStream());
            dos.writeShort(packet.getId());
            packet.write(dos);
            dos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void readChat() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            if (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.equals("/end")) {
                    end();
                } else {
                    System.out.println("Unknown command!");
                }
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {}
            }
        }
    }

    public static ServerHandler getSServerHandler() {
        return handler;
    }

    private static void start() {
        try {
            server = new ServerSocket(8888);
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
        System.exit(0);
    }

    public static  ClientHandler getHandler(Socket socket) {
        return handlers.get(socket);
    }

    public static void invalidate(Socket socket) {
        handlers.remove(socket);
    }

}