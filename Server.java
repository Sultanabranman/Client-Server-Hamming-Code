package model;

import java.io.*;
import java.net.*;
import java.util.*;



public class Server {

	public static void main(String[] args) {
		new Server();		
	}
	
	public Server()
	{
		/** Open server socket **/
		try
		{
			ServerSocket serverSocket = new ServerSocket(8000);
			
			while(true)
			{
				/** Wait for client connection **/
				Socket socket = serverSocket.accept();
				
				HandelAClient task = new HandelAClient(socket);	
				
				new Thread(task).start();				
			}
		}
		catch(IOException ex) {
			System.err.println(ex);
			System.exit(1);
		}		
		
		/** Assign new thread to client **/	
	}
	
	public class HandelAClient implements Runnable {
		private Socket socket;
		
		public HandelAClient(Socket socket) {
			this.socket = socket;
		}
		
		public void run() {
			try 
			{		
				/** Open object input stream from client **/
				ObjectOutputStream outputToClient = new ObjectOutputStream(
						socket.getOutputStream());
				outputToClient.flush();
				
				/** Open object output stream to client **/
				ObjectInputStream inputFromClient = new ObjectInputStream(
						socket.getInputStream());
				
				while(true)
				{
					int[] stream = new int[11];
					int d7, d6, d5,d4, d3, d2, d1, r4, r3, r2, r1;
					
					/** Wait to receive array from client **/
					stream = (int[]) inputFromClient.readObject();
					
					System.out.println("received\n");
					
					/** Assign array elements to different bits **/
					d7 = stream[0];
					d6 = stream[1];
					d5 = stream[2];
					r4 = stream[3];
					d4 = stream[4];
					d3 = stream[5];
					d2 = stream[6];
					r3 = stream[7];
					d1 = stream[8];
					r2 = stream[9];
					r1 = stream[10];
					
					/** Perform hamming code check on array **/
					
						/** check r1 parity (r1 = r1, d1, d2, d4, d5, d7) **/
						check_r1_Parity(r1, d1, d2, d4, d5, d7);
					
						/** check r2 parity (r2 = r2, d1, d3, d4, d6, d7) **/
						check_r2_Parity(r2, d1, d3, d4, d6, d7);
						
						/** check r3 parity (r3 = r3, d2, d3, d4) **/
						check_r3_Parity(r3, d2, d3, d4);
						
						/** check r4 parity (r4 = r4, d5, d6 , d7 **/
						check_r4_Parity(r4, d5, d6, d7);
						
					/** If no errors detected **/
					
						/** Remove parity bits and assign to new array **/
					
						/** Output message with parity bits removed **/
					
					/** If an error is detected **/
					
						/** Attempt to fix data **/
					
							/** If data was fixed **/
					
								/** Remove parity bits and assign to new array **/
					
								/** Output message with parity bits removed **/
					
							/** If data can't be fixed **/
					
								/** Message saying error detected and ask for resend **/
				}				
				
				
				
									
				
			}
			catch(IOException e) 
			{
				System.err.println(e);
			}
			catch (ClassNotFoundException e)
			{
				System.err.println(e);
			}
		}
	}
	
	public int check_r1_Parity(int r1, int d1, int d2, int d4, int d5, int d7)
	{
		int parity = 0; 
		int count = 0;
		int[] r1_checkbits = {r1, d1, d2, d4, d5, d7};
		
		/** count number of 1's in array **/
		for(int i = 0; i < r1_checkbits.length; i++)
		{
			if(r1_checkbits[i] == 1)
			{
				count++;
			}
		}
		
		/** if odd number of 1's **/
		if(count % 2 == 1)
		{
			parity = 1;
		}
		/** if even number of 1's **/
		else if(count % 2 == 0)
		{
			parity = 0;
		}
		
		return parity;
	}
	
	public int check_r2_Parity(int r2, int d1, int d3, int d4, int d6, int d7)
	{
		int parity = 0;
		int count = 0;
		int[] r2_checkbits = {r2, d1, d3, d4, d6, d7};
		
		/** count number of 1's in array **/
		for(int i = 0; i < r2_checkbits.length; i++)
		{
			if(r2_checkbits[i] == 1)
			{
				count++;
			}
		}
		
		/** if odd number of 1's **/
		if(count % 2 == 1)
		{
			parity = 1;
		}
		/** if even number of 1's **/
		else if(count % 2 == 0)
		{
			parity = 0;
		}
		
		return parity;		
	}
	
	public int check_r3_Parity(int r3, int d2, int d3, int d4)
	{
		int parity = 0;
		int count = 0;
		int[] r3_checkbits = {r3, d2, d3, d4};
		
		/** count number of 1's in array **/
		for(int i = 0; i < r3_checkbits.length; i++)
		{
			if(r3_checkbits[i] == 1)
			{
				count++;
			}
		}
		
		/** if odd number of 1's **/
		if(count % 2 == 1)
		{
			parity = 1;
		}
		/** if even number of 1's **/
		else if(count % 2 == 0)
		{
			parity = 0;
		}
		
		return parity;
	}
	
	public int check_r4_Parity(int r4,int d5, int d6, int d7)
	{
		int parity = 0;
		int count = 0;
		int[] r4_checkbits = {r4, d5, d6, d7};
		
		/** count number of 1's in array **/
		for(int i = 0; i < r4_checkbits.length; i++)
		{
			if(r4_checkbits[i] == 1)
			{
				count++;
			}
		}
		
		/** if odd number of 1's **/
		if(count % 2 == 1)
		{
			parity = 1;
		}
		/** if even number of 1's **/
		else if(count % 2 == 0)
		{
			parity = 0;
		}
		
		return parity;
	}

}
