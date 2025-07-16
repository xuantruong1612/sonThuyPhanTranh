package network;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class TestClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 14445);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            // Gửi 1 message giả đơn giản (command = 1, data = "Hello")
            byte command = 1;
            byte[] data = "Hello".getBytes();

            dos.writeByte(command);
            dos.writeShort(data.length);
            dos.write(data);
            dos.flush();

            // Đọc phản hồi (nếu server trả)
            byte respCmd = dis.readByte();
            int size = dis.readUnsignedShort();
            byte[] respData = new byte[size];
            dis.readFully(respData);
            System.out.println("Received from server: cmd=" + respCmd + ", data=" + new String(respData));

            dos.close();
            dis.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
