import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            String msg = "Hello Server";
            byte[] buffer = msg.getBytes();

            InetAddress host = InetAddress.getByName("127.0.0.1");
            int serverSocket = 6788;

            DatagramPacket request = new DatagramPacket(buffer, buffer.length, host, serverSocket);
            socket.send(request);

            byte[] responseBuffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(responseBuffer, responseBuffer.length);
            socket.receive(reply);

            System.out.println("Client received: " + new String(reply.getData(), 0, reply.getLength()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
