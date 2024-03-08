/*cliet.java*/

import java.io.IOException;
import java.net.*;

public class client
{
	public static void main(String[] args) throws IOException
	{
		Socket socket = new Socket();
    	socket.connect(new InetSocketAddress("127.0.0.1", 5001), 1000);
    	System.out.println("Connection Successful!");
	}
}
