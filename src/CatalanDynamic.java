/**
 * @author Damon McClellan
 * CatalanDynamic
 * CS215 Data Structures  
 */

import java.util.*;
import java.io.*;

public class CatalanDynamic {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		boolean keepRunning = true;
		while (keepRunning == true) {
		System.out.println("Enter your n: ");
		double n = scan.nextDouble();
		double timeStart = System.nanoTime();
		double rightDenominator = rightDenominator(n);
		double leftDenominator = leftDenominator(n, rightDenominator);
		double numerator = numerator(n, leftDenominator);
		double answer = (numerator / (rightDenominator * leftDenominator));
		System.out.println("C(" + n + ")" + "=" + answer);
		double duration = (System.nanoTime() - timeStart);
		writeFile(answer, duration, n);
		System.out.println("Would you like to enter another N? (Y/N)");
		String yesno = scan.next();
		if(yesno.equals("Y") == true) {
			keepRunning = true;
		}else if (yesno.equals("N")) {
			keepRunning = false;
		}else {System.out.println("Inoccrect fomat (Y/N)");}
		}//end while
		scan.close();
	}//end main
	
	public static double numerator(double n, double leftDenominator){
		n = (n*2);
		double[] doubleArray = new double[(int)(n - 1)];
		double orgN = n;
		double finalN = 1;
		while (n > 1) {
			doubleArray[(int)n - 2] = (n-1);
			n = n-1;
		}//end while
		for (double i = (orgN - 2); i >= 0; i--) {
			finalN = doubleArray[(int)i] * finalN;
		}//end for
		return finalN * orgN;
	}//end numerator
	
	public static double leftDenominator(double n , double rightDenominator) {
		return rightDenominator * (n + 1);
	}//end leftDenominator
	
	public static double rightDenominator(double n) {
		double[] doubleArray = new double[(int)(n - 1)];
		double orgN = n;
		double finalN = 1;
		while (n > 1) {
			doubleArray[(int)n - 2] = (n-1);
			n = n-1;
		}//end while
		for (double i = (orgN - 2); i >= 0; i--) {
			finalN = doubleArray[(int)i] * finalN;
		}//end for
		return finalN * orgN;
	}//end right Denominator
	
	public static void writeFile(double answer, double duration, double n) throws IOException{
		String durationS = String.valueOf(Math.ceil(duration/1000000000.000));
		String output = String.valueOf(n) + ", " + String.valueOf(answer) + ", " + durationS + " Seconds";;
		BufferedWriter writer = new BufferedWriter(new FileWriter("CatalanDynamic.txt",true));
		writer.newLine();
		writer.write(output);
		writer.close();
	}//end writeFile
}//end class