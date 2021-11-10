package devoirPJA;

import java.io.*;
import java.util.ArrayList;
public class Class2
{
	public static void main(String [] args)
	{
		ArrayList<Employee> emps = null;
	try
		{
		FileInputStream fileIn = new FileInputStream("emp.dat");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		emps = (ArrayList) in.readObject();
		in.close();
		fileIn.close();
		}catch(IOException i)
		{
		i.printStackTrace();
		return;
		}catch(ClassNotFoundException c)
		{
		System.out.println("classe Employee non trouvee");
		c.printStackTrace();
		return;
		}
		int j;
		for (int i=0;i<emps.size();i++) {
			j=i+1;
			System.out.println("Employee "+j+" deserialise...");
			System.out.println("Nom: " + emps.get(i).name);
			System.out.println("Adresse: " + emps.get(i).address);
			System.out.println("SSN: " + emps.get(i).SSN);
			System.out.println("Number: " + emps.get(i).number);
		}
		try {
			RandomAccessFile raf = new RandomAccessFile("empdirect.dat","rw");
			
			for (int i=0;i<emps.size();i++) {
				j=i+1;
				raf.writeUTF(emps.get(i).name+"@"+emps.get(i).address+"@"+emps.get(i).SSN+"@"+emps.get(i).number);
				System.out.println("Employee "+j+" enregistrer sur le fichier ...");
				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}