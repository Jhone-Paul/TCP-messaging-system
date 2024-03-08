import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    public static void main(String[] args) {
        try {
            // Create a server socket listening on port 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is listening on port 12345");

            // Wait for a client to connect
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            // Get the input stream of the client socket
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Read data from the client
            String receivedData = in.readLine();
            System.out.println("Received data: " + receivedData);

            // Close the input stream and the client socket
            in.close();
            clientSocket.close();

            // Close the server socket
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

