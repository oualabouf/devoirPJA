package Devoir2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try {
			Integer port =5757;
			Socket soc=new Socket("127.0.0.1",port);
			
			ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
			String command="";
			
					
					Scanner scan = new Scanner(System.in);
					
					System.out.println("Server Listening on Port _numéro de port "+port);
					
					
					while(!command.equals("QUIT")){
						command = scan.nextLine();
						out.writeObject(command);
						String response = (String)in.readObject();
						while(!response.equals("<!--STOP-->")) {
							System.out.println(response);
							response = (String)in.readObject();
						}
					}
				
			
			
		}catch(Exception e) {
			System.out.println("Client Exception: " + e.getMessage());
		}
	}

}
