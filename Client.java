package model;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	
	private static ObjectOutputStream toServer;
	private static ObjectInputStream fromServer;

	public static void main(String[] args) {
		new Client();		
	}	
	
	public Client()
	{
		Scanner input = new Scanner(System.in);
		try 
		{
			/** Open a socket on the server **/
			Socket socket = new Socket("localhost", 8000);
			
			/** Create object output stream **/
			toServer = new ObjectOutputStream(socket.getOutputStream());
			
			/** Create object input stream **/
			fromServer = new ObjectInputStream(socket.getInputStream());
		}
		catch (IOException ex)
		{
			System.err.println(ex.toString() + "\n");
			System.exit(1);
		}
		
		while(true)
		{
			boolean error = false;
			/** Get user input for stream to be sent (must be combination of 
			 * 	11 1's and 0's)
			**/
			System.out.println("Please enter data to be sent "
					+ "(must be 11 1's and 0's): ");
			
			String data = input.nextLine();
			
			/** validate that input was 11 int's long **/
			if(data.length() != 11)
			{
				error = true;
				System.err.println("Input was not valid length\n");
				
				continue;
			}
			
			/** validate that only 1's and 0's entered **/
			for(int i = 0; i < data.length(); i++)
			{
				int current = Character.getNumericValue(data.charAt(i));
				if(current < 0 || current > 1)
				{
					error = true;
					System.err.println("Input must only contain 1's and 0's");
					break;
					
				}
			}
			
			/** check if error occurred when getting input */
			if(error == true)
			{
				continue;
			}
					
			/** assign user input to array **/
			int[] stream = new int[11];
			
			for(int i = 0; i < data.length(); i++)
			{
				stream[i] = Character.getNumericValue(data.charAt(i));
			}			
			
			/** Send array to server **/
			try 
			{
				toServer.writeObject(stream);
			}
			catch (SocketException ex)
			{
				System.err.println("Server Disconnected, closing...\n");
				System.exit(1);
			}
			catch (IOException ex) 
			{
				System.err.println(ex.toString() + "\n");				
			}
			

		}
	}
		

}
