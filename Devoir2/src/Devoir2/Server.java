package Devoir2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.omg.CORBA.portable.InputStream;

import java.util.ArrayList;

public class Server {
	public static ServerSocket soc;
	public static void main(String[] args) {
		ObjectInputStream in=null;
		ObjectOutputStream out=null;
		
			try {
				while(true) {
				if (soc == null || !soc.isBound()) {
                    soc = new ServerSocket(5757);
                    Socket s =soc.accept();
                    out = new ObjectOutputStream(s.getOutputStream());
    				in = new ObjectInputStream(s.getInputStream());
                }
				
				if (in==null || out==null) {
					throw new Exception("faild init");
				}
				
				String path =(String) in.readObject();
				
				
				
				
				String[] result = path.split(" ");
				
				
				
				if (result[0].equals("List")) {
					
					
					File file = new File( result[1] );
					
					if ( file.exists() )
					{
						
						if ( file.isDirectory() )
						{
							String[ ] directory = file.list();
							out.writeObject( "Liste des fichiers contenus :" );
							for ( String directoryName : directory )
								out.writeObject( directoryName );
						}
					}else {
						out.writeObject("ERROR : "+result[1]+" does not exist");
					}
					
					
				}else {
					if (result[0].equals("Get")) {
						
						String strFileDirectoryPath = result[1];
						try {
							FileInputStream fstream = new FileInputStream(strFileDirectoryPath);
							DataInputStream i = new DataInputStream(fstream);
							BufferedReader br = new BufferedReader(new InputStreamReader(i));
							String strLine;
							// Read File Line By Line
							
							while ((strLine = br.readLine()) != null) {
								out.writeObject(strLine);
							}
							
							i.close();
						} catch (Exception e) {// Catch exception if any
							out.writeObject("ERROR : "+result[1]+" does not exist");
						}
						
						
					}else {
						if(result[0].equals("QUIT")) {
							out.writeObject("END");
						}else {
						out.writeObject("ERROR : "+path+" is not a valable request");
						}
					}
				}
				
				out.writeObject("<!--STOP-->");
			
				}
		}catch(Exception e) {System.out.println("Server Exception :"+e.getMessage());}
			
	}
	
}
	
}
