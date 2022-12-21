/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rmazorow
 */
import java.io.*;

public class Exercise17_07 {
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
		Loan loan1 = new Loan();
		Loan loan2 = new Loan(1.8, 10, 10000);
		
		File file = new File("Exercise17_07.dat");

		try (
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
				) {
			output.writeObject(loan1);
			output.writeObject(loan2);
		} 
		catch (IOException ex) {
			System.out.println("File could not be opened");
		}

		outputData(file);
	}

		public static void outputData(File file) throws ClassNotFoundException {
			double loanTotal = 0;
			try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));){

				while (true) {
					Loan loan = (Loan)(input.readObject());
					loanTotal =+ loan.getLoanAmount();
				}
			}

			catch (EOFException ex) {
				System.out.println("All loans were considered.");
			}

			catch (IOException ex) {
				ex.printStackTrace();
			}
			System.out.println("The total loan amount is: " + loanTotal);	
		}
}
