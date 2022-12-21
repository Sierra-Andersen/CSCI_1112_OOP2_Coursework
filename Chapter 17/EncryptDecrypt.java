/*
 * Author: Sierra Andersen
 * Date: 20 Dec 2022
 * 
 * This program encyrpts a file and decrypts a file.
 */

import java.io.*;
import java.util.Scanner;

public class EncryptDecrypt {

	public static void main(String[] args) throws IOException {
		//Print menu of options
		System.out.println("Select an option:");
		System.out.println("1. Encrypt File");
		System.out.println("2. Decrypt File");
		System.out.println("3. Convert File");

		Scanner input = new Scanner(System.in);
		int option = input.nextInt();

		System.out.println("Input file name: ");
		File inFile =new File(input.next());

		System.out.println("Output file name: ");
		File outFile =new File(input.next());

		switch(option) {
		case 1:
			Encrypt(inFile, outFile);
			break;

		case 2:
			Decrypt(inFile, outFile);
			break;

		default:
			System.out.println("Not a valid menu option.");
			System.exit(1);
		}
	}

	//Encrypt a file
	public static void Encrypt(File inFile, File outFile) throws IOException {

		try( 
				BufferedInputStream input = new BufferedInputStream(new FileInputStream(inFile));
				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outFile));
				){
			int r = 0;
			while ((r = input.read()) != -1){
				output.write((byte)r+5);
			}
			System.out.println("File Encrypted!");
		}

	}


	//Decrypt a file
	public static void Decrypt(File inFile, File outFile) throws IOException {
		try( 
				BufferedInputStream input = new BufferedInputStream(new FileInputStream(inFile));
				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outFile));
				) {
			int r = 0;
			while ((r = input.read()) != -1){
				output.write((byte)r-5);
			}
			System.out.println("File Decrypted!");
		}

	}

}
