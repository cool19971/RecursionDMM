/**
 * @author Damon McClellan
 * BCRecursive
 * CS215 Data Structures  
 */

import java.util.*;
import java.io.*;

public class BCRecursive {
	
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
		if (k == 1) {
			return k;
		}//end if
		else {
			return k * factorialk(k-1);
		}//end else	
	}// end factorial 
	
	public static double factorialNK (double k, double fK, double nk) {
		if (nk == (k + 1)) {
			return (nk * fK);
		}//end if
		else {
			return nk * factorialNK(k, fK, nk-1);
		}//end else	
	}//end factorialNK
	
	public static double factorialN (double nk, double fnk, double n) {
		if (n == (nk + 1)) {
			return (n * fnk);
		}//end if
		else {
			return n * factorialN(nk, fnk, n-1);
		}//end else
	}//end factorialN
	
	public static void writeFile(double answer, double duration, double n, double k) throws IOException{
		String durationS = String.valueOf(Math.ceil(duration/1000000000.000));
		String output = String.valueOf(k) + ", " + String.valueOf(n) + ", " + String.valueOf(answer) + ", " + durationS + " Seconds" ;
		BufferedWriter writer = new BufferedWriter(new FileWriter("BCRecursive.txt",true));
		writer.newLine();
		writer.write(output);
		writer.close();
	}//end writeFile
}//end class