import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class client {

    public static void main(String[] args) {
        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 12345);

            // Get the output stream of the socket
            OutputStream outputStream = socket.getOutputStream();

            // Send data to the server
            String data = "Hello, Server!";
            outputStream.write(data.getBytes());

            // Close the output stream and the socket
            outputStream.close();
            socket.close();

            System.out.println("Data sent to server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
