import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient
{
	public static void main(String[] args) 
	{
		try 
		{
			Socket socket = new Socket("localhost", 12345);
			OutputStream outputStream = socket.getOutputStream();
			String filePath = "file.txt";
			try
			{
				filePath = args[0];
			} catch (Exception e) {
				System.err.println("error "+ e);
			}
			outputStream.write((filePath+"\n").getBytes());
			try (FileInputStream fileInputStream = new FileInputStream(filePath))
			{
				byte[] buffer = new byte[4096];
				int bytesRead;

				while ((bytesRead = fileInputStream.read(buffer)) != -1)
				{
					outputStream.write(buffer, 0, bytesRead);
				}
			}
			catch (FileNotFoundException e) 
			{
				System.err.println("Could not find the file sorry");
			}

			outputStream.close();
			socket.close();
			System.out.println("File sent to server.");
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

