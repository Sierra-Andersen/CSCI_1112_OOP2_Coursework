/*
 * Author: Sierra Andersen
 * Date: 4 Jan 2023
 * 
 * This program reverses a string using recursion.
 */
public class RecursiveReverse {
	
	public static void reverseDisplay(String value) {
		reverseDisplay(value, value.length()-1);
	}
	
	private static void reverseDisplay(String value, int high) {
		//Print out the last digit of the string
		if (high >= 0){
			System.out.print(value.charAt(high));
			reverseDisplay(value, high-1);
		}
	}

	public static void main(String[] args) {
		
		//Examples of reversed strings
		reverseDisplay("racecar");
		System.out.println();
		reverseDisplay("Cat");
		System.out.println();
		reverseDisplay("Hello World!");

	}
}

