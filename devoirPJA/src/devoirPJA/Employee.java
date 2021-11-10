package devoirPJA;

import java.io.Serializable;


public class Employee implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
    public String address;
    public transient int SSN;
    public int number;

    public void mailCheck()
    {
        System.out.println("mail de verification de "+ name +" "+ address);
    }
}

