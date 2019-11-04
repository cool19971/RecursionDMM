/**
 * @author Damon McClellan
 * BCDynamic
 * CS215 Data Structures  
 */

import java.util.*;
import java.io.*;

public class BCDynamic {
	public static void main(String[] args) throws IOException{
		Scanner scan = new Scanner(System.in);
		boolean keepRunning = true;
		while (keepRunning == true) {
			System.out.println("Enter your n:");
			int n = scan.nextInt();
			System.out.println("Enter your k:");
			int k = scan.nextInt();
			int nk = n - k;
			double timeStart = System.nanoTime();
			double factorialK = factorialk(k);
			double factorialNK = factorialNK(k, factorialK, nk);
			double factorialN = factorialN(nk, factorialNK, n);
			double answer = (factorialN / (factorialNK * factorialK));
			double duration = (System.nanoTime() - timeStart);
			System.out.println("There are " + answer + " ways to form " + n + " subsets from " + k + " items");
			writeFile(answer, duration, n, k);
			System.out.println("Would you like to enter another N and K? (Y/N)");
			String yesno = scan.next();
			if(yesno.equals("Y") == true) {
				keepRunning = true;
			}else if (yesno.equals("N")) {
				keepRunning = false;
			}else {System.out.println("Inoccrect fomat (Y/N)");}
		}//end while
		scan.close();
	}//end main
	
	public static double factorialk (double k) {
		double[] doubleArray = new double[(int)(k - 1)];
		double orgK = k;
		double finalK = 1;
		while (k > 1) {
			doubleArray[(int)k - 2] = (k-1);
			k = k-1;
		}//end while
		for (double i = (orgK - 2); i >= 0; i--) {
			finalK = doubleArray[(int)i] * finalK;
		}//end for
		return finalK * orgK;
	}// end factorial 
	
	public static double factorialNK (double k, double fK, double nk) {
		double[] doubleArray = new double[(int)(nk - k - 1)];
		double orgNK = nk;
		double finalNK = 1;
		while (nk > k + 1) {
			doubleArray[(int)nk - (int)k - 2] = (nk-1);
			nk = nk-1;
		}//end while 
		for (double i = (orgNK - k - 2); i >= 0; i--) {
			finalNK = doubleArray[(int)i] * finalNK;
		}//end for
		return finalNK * orgNK * fK;
	}//end factorialNK
	
	public static double factorialN (double nk, double fnk, double n) {
		double[] doubleArray = new double[(int)(n - nk - 1)];
		double orgN = n;
		double finalN = 1;
		while (n > nk + 1) {
			doubleArray[(int)n - (int)nk - 2] = (n-1);
			n = n-1;
		}//end while
		for (double i = (orgN - nk - 2); i >= 0; i--) {
			finalN = doubleArray[(int)i] * finalN;
		}//end for
		return finalN * orgN * fnk;
		}//end  factorialN
	
	public static void writeFile(double answer, double duration, double n, double k) throws IOException{
		String durationS = String.valueOf(Math.ceil(duration/1000000000.000));
		String output = String.valueOf(k) + ", " + String.valueOf(n) + ", " + String.valueOf(answer) + ", " + durationS + " Seconds";
		BufferedWriter writer = new BufferedWriter(new FileWriter("BCDynamic.txt",true));
		writer.newLine();
		writer.write(output);
		writer.close();
	}//end writeFile
}//end class	