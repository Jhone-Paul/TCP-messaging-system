import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer 
{
	public static void main(String[] args)
	{
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			System.out.println("Server is listening on port 12345");
			
			Socket clientSocket = serverSocket.accept();
			System.out.println("Client connected");
				
			InputStream inputStream = clientSocket.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			String receivedFileName = reader.readLine();
			System.out.println(receivedFileName);
			String outputFileName = "sent_"+receivedFileName;

			try (FileOutputStream outputStream = new FileOutputStream(outputFileName)) {

				byte[] buffer = new byte[4096];
				int bytesRead;
				long totalBytesRead = 0;
				long fileSize = clientSocket.getInputStream().available();
			
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
					totalBytesRead += bytesRead;

					printProgressBar((int) (totalBytesRead * 100 / fileSize));
				}
			}

			inputStream.close();
			clientSocket.close();

			serverSocket.close();

			System.out.println("File received and saved as " + outputFileName);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void printProgressBar(int progress)
	{
		StringBuilder bar = new StringBuilder("[");
		for (int i = 0; i < 50; i++)
		{
			if (i < progress / 2)
			{
				bar.append("=");
			} else if (i == progress / 2)
			{
				bar.append(">");
			} else 
			{
				bar.append(" ");
			}
		}
		bar.append("] ").append(progress).append("%");
		System.out.print("\r" + bar.toString());
	}
}
