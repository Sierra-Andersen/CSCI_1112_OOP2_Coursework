/*
 * Author: Sierra Andersen
 * Date: 19 Dec 2022
 * 
 * This program creates a file and writes 100 random integers into the file.
 */
import java.io.*;
import java.util.Random;

public class SumIntegers {

	public static void main(String[] args) throws IOException {

		try(DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Exercise17_03.dat", true)))){
			for(int i = 0; i<100; i++) {
				int randInt = new Random().nextInt();
				output.writeInt(randInt);
				output.writeUTF(" ");
			}
		}

		try (DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream("Exercise17_03.dat")))){
			int value;
			int total = 0;
			while((value = input.read()) != -1) {
				total += value;
			}
			System.out.println("The sum is: " + total);
		
		}

	}


}