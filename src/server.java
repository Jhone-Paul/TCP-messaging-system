import java.net.*;
import java.io.IOException;
import java.io.*;

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
	   	DataInputStream dataIn = new DataInputStream(clientS.getInputStream());
    	DataOutputStream dataOut = new DataOutputStream(clientS.getOutputStream());

    	String clientMessage = dataIn.readUTF();
		String prev = clientMessage;
		System.out.println(clientMessage);
		while (clientMessage != "quit") {
			clientMessage = dataIn.readUTF();
			if(clientMessage != prev) {
				System.out.println(clientMessage);
				prev = clientMessage;
				dataOut.writeUTF("recieved message :)");
			}
		}
    	System.out.println(clientMessage);
    	String serverMessage = "Hi this is coming from Server!";
    	dataOut.writeUTF(serverMessage);

    	dataIn.close();
    	dataOut.close();
    	sSocket.close();
    	clientS.close(); 
	}
}
