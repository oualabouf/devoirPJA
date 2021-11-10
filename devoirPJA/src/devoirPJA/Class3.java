package devoirPJA;

import java.io.*;
import java.util.Scanner;

public class Class3 {
	public static void main(String [] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("entrer le numero de l'employee");
		int num =scanner.nextInt();
		 int num_test;
		 Boolean exist;
		 exist = false;
		try {
			RandomAccessFile raf = new RandomAccessFile("empdirect.dat","rw");
			raf.seek(0);
			while (raf.getFilePointer() < raf.length()) {
				
				String[] infos = raf.readUTF().split("@", 4);
				num_test = Integer.parseInt(infos[3]);
				if( num_test == num) {
					System.out.println("Nom :"+infos[0]);
					System.out.println("Address :"+infos[1]);
					System.out.println("SSN :"+infos[2]);
					System.out.println("Numero :"+infos[3]);
					exist =true;
				}
				 
			}
			if(exist==false) {
				System.out.println("l'employee "+num+" n'exist pas !!!");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	

}
