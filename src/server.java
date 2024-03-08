import java.net.*;
import java.io.IOException;

public class server
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket sSocket = new ServerSocket(5001);
		System.out.println("Listening for clients...");
		Socket clientS = sSocket.accept();
		String clientSocketIP = clientS.getInetAddress().toString();
		int clientSocketPort = clientS.getPort();
 		System.out.println("[IP: " + clientSocketIP + " ,Port: " + clientSocketPort +"]  " + "Client Connection Successful!");
 
	}
}
