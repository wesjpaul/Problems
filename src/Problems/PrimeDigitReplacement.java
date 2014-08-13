/*
 *  Author: Wesley Paul
 *  Date: August 11, 2014
 * 
 */

package Problems;

import java.util.Arrays;

/*
 * By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine 
 * possible values: 13, 23, 43, 53, 73, and 83, are all prime.
 *
 * By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number 
 * is the first example having seven primes among the ten generated numbers, yielding the 
 * family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003, being 
 * the first member of this family, is the smallest prime with this property.
 *
 * Find the smallest prime which, by replacing part of the number (not necessarily adjacent 
 * digits) with the same digit, is part of an eight prime value family.
 * 
 * Source: https://projecteuler.net/
 * 
 */

public class PrimeDigitReplacement {

	private static final int MAXVALUE = 1000000;
	private static final int MINFAMILYSIZE = 8; // must be less than 10
	private static final boolean [] primes = new boolean[MAXVALUE];
	
	public static int runPrimeDigitReplacement(){
		int i, j;
		int length;
		int prime, adder;
		int familySize, miss;
		
		// Populate the primes list
		getPrimes();
		
		// For each prime up to MAXVALUE 
		for(i= 11; i < MAXVALUE; i+=2){
			if(isPrime(i)){
				// Go through each possible pattern that this prime could be in a family for
				// (i.e. if i is 5 characters long go through:  10000, 10001, 10010 ... etc.)
				length = String.valueOf(i).length();
				for(j = 2; j < Math.pow(2, length); j++){
					// Convert the number to a binary string then back to an integer to get the adder.
					// (i.e. 5 turns into 101)
					adder = Integer.parseInt(Integer.toBinaryString(j));
					
					// Check that this adder is compatible with the given prime.
					if(!checkPosition(i, adder))
						continue;

					prime = i + adder;
					familySize = 1;
					miss = 0;

					// Using the adder check to see if a family of primes can be created that is as large as MINFAMILYSIZE
					while(miss <= 10 - MINFAMILYSIZE && familySize < MINFAMILYSIZE && String.valueOf(prime).length() == length ){
						if(isPrime(prime))
							familySize++;
						else
							miss++;
						prime+=adder;
					}
					// If a family was found return the smallest prime from that family (i.e. i) 
					if(familySize >= MINFAMILYSIZE){
						//System.out.println("Smallest prime: " + i + ", adder: " + adder);
						return i;
					}
				}	
			}
		}
		
		
		return 0;
	}
	
	// Take a prime and an adder and determines if they work together.
	// (i.e. does the adder maintain the same digit change required)
	private static boolean checkPosition(int primeIn, int adderIn){
		int i;
		
		String add = Integer.toString(adderIn);
		String prime = Integer.toString(primeIn);
		int j = add.length();
		char c = prime.charAt(prime.length() - j);
		
		i = 1;
		for(j = add.length(); j > 0; j--){
			if(add.charAt(j-1) == '1' && c != prime.charAt(prime.length()-i)){
				return false;
			}
			i++;
		}
		return true;
	}
	
	// Determine the all primes up to MAXVALUE
	private static void getPrimes(){
		int i, j;
		
		Arrays.fill(primes, true);
		primes[1] = primes[2] = false;
		
		for(i=2; i < MAXVALUE; i++){
	        
			if(primes[i]) {
	            for (j=2;i*j<MAXVALUE;j++) {
	                primes[i*j]=false;
	            }
	        }
		}
		
	}
	
	// Determines if an input number is prime.
	private static boolean isPrime(int num){
		return primes[num]; 
	}
}
