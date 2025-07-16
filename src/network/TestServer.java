package network;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class TestServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(14445);
        System.out.println("Server started on port 14445...");
        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("Client connected: " + client.getInetAddress());

            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());

            // Đọc dữ liệu client gửi
            byte command = dis.readByte();
            int length = dis.readUnsignedShort();
            byte[] data = new byte[length];
            dis.readFully(data);
            System.out.println("Received from client: cmd=" + command + ", data=" + new String(data));

            // Gửi phản hồi lại (giả sử trả lại command + data client gửi)
            dos.writeByte(command);     // gửi lại command
            dos.writeShort(length);     // gửi lại độ dài data
            dos.write(data);            // gửi lại data
            dos.flush();

            dis.close();
            dos.close();
            client.close();
        }
    }
}
