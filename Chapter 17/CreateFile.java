/*
 * Author: Sierra Andersen
 * Date: 19 Dec 2022
 * 
 * This program creates a file and writes 100 random integers into the file.
 */

import java.io.*;
import java.util.Random;

public class CreateFile {

	public static void main(String[] args) throws IOException {
		
		try(DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Exercise17_01.txt", true)))){
			for(int i = 0; i<100; i++) {
				int randInt = new Random().nextInt();
				output.writeInt(randInt);
			}
		}
		
		try (DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream("Exercise17_01.txt")))){
			int value;
			while((value = input.read()) != -1)
				System.out.print(value + " ");
		}

	}

}
