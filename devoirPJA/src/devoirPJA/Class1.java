package devoirPJA;
import java.io.*;
import java.util.*;

public class Class1 {
	private static Scanner scanner;

	public static void main(String [] args)
    {
    	scanner = new Scanner(System.in);
    	System.out.println("donnes le nombre d'employees :");
    	int nbr_emp = scanner.nextInt();
    	ArrayList<Employee> emps = new ArrayList<Employee>();
    	int j;
    	
    	for (int i = 0; i < nbr_emp; i++) {
    		j = i+1;
    		Employee e =new Employee();
    		scanner.nextLine();
    		System.out.println("entrer le nom de l'employee numero "+j);
    		e.name = scanner.nextLine();
    		System.out.println("entrer l'address de l'employee numero "+j);
    		e.address = scanner.nextLine();
    		System.out.println("entrer l'SSN de l'employee numero "+j);
    		e.SSN = scanner.nextInt();
    		System.out.println("entrer le nomero de l'employee numero "+j);
    		e.number = scanner.nextInt();
    		emps.add(e);
    		}

    try
    {
        FileOutputStream fileOut = new FileOutputStream("emp.dat");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        
        	out.writeObject(emps);
        
        out.close();
        fileOut.close();
        System.out.printf("donnees serialisees sauvegardees dans emp.dat");
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }
}
