package Devoir2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadedServer {
	

	public static void main(String[] args) {
		try {
			ServerSocket socket=new ServerSocket(5757);
			List<Thread> threads= new ArrayList<Thread>();
			for(int i=0;i<3;i++) {
			Thread t1 = new Thread(new ThreadTask(socket));
			t1.start();
			threads.add(t1);
			}
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		

	}
}
