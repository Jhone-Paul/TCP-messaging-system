/*cliet.java*/

import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.*;
public class client
{
	public static void main(String[] args) throws IOException
	{
		Socket socket = new Socket();
    	socket.connect(new InetSocketAddress("127.0.0.1", 5001), 1000);
    	System.out.println("Connection Successful!");
		DataInputStream dataIn = new DataInputStream(socket.getInputStream());
    	DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
		
		Scanner scanner = new Scanner(System.in);   
		Thread userInputThread = new Thread(new UserInputTask(dataOut, scanner));
        userInputThread.start();

        // Create and start the server response thread
        Thread serverResponseThread = new Thread(new ServerResponseTask(dataIn));
        serverResponseThread.start();

        // Wait for both threads to complete
        try {
            userInputThread.join();
            serverResponseThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both threads have completed.");	
    	dataIn.close();
    	dataOut.close();
    	socket.close();
	}
	static class UserInputTask implements Runnable 
	{
        private DataOutputStream dataOut;
		Scanner scanner;
		public UserInputTask(DataOutputStream dataOut, Scanner scanner)
		{
			this.dataOut = dataOut;
			this.scanner = scanner;
		}
		@Override
        public void run()
		{	try{
            	System.out.println("Enter something:");
            	String userInput = scanner.nextLine();
            	dataOut.writeUTF(userInput);
				while(userInput!= "quit"){
					userInput = scanner.nextLine();
					dataOut.writeUTF(userInput);
				}

			}catch(IOException e) {
				e.printStackTrace();
			}
			
        }
    }

    static class ServerResponseTask implements Runnable 
	{
        private DataInputStream dataIn;

		public ServerResponseTask(DataInputStream dataIn) 
		{
			this.dataIn = dataIn;
		}
		@Override
        public void run()
		{
            try {
                // Simulate waiting for a server response
                Thread.sleep(2000); // Wait for 2 seconds
				String serverMessage = dataIn.readUTF();
                System.out.println("Server response received: "+serverMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
