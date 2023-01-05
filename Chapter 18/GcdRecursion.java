/*
 * Author: Sierra Andersen
 * Date: 4 Jan 2023
 * 
 * This program finds the GCD of two integers using recursion.
 */
import java.util.Scanner;

public class GcdRecursion {
	public static int GCD(int m, int n) {
		if(m%n == 0)
			return n;
		else
			return GCD(n, m%n);
	}
	public static void main(String[] args) {
		//Create a Scanner object
		Scanner input = new Scanner(System.in);
		
		//Prompt user for integers
		System.out.println("Type two integers: ");
		int i1 = input.nextInt();
		int i2 = input.nextInt();
		
		System.out.println("The GCD of " + i1 + " and " +i2 + " is: " + GCD(i1, i2));
		
	}

}
