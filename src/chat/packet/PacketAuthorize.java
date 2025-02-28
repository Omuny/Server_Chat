package chat.packet;

import chat.ServerLoader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketAuthorize extends Packet {

    private String nickname;

    public PacketAuthorize() {

    }

    public PacketAuthorize(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {

    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        nickname = dis.readUTF();
    }

    @Override
    public void handle() {
        ServerLoader.getHandler(getSocket()).setNickname(nickname);
        System.out.println("Our nickname is " + nickname);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {}
        ServerLoader.end();
    }
}
