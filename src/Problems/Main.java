/*
 *  Author: Wesley Paul
 *  Date: July 22, 2014
 * 
 */


package Problems;

/*
 * Runs each problem and outputs their responses
 * 
 */

public class Main {

	public static void main(String[] args) {
		System.out.println("Sum of even fibonnaci numbers: " + Fibonacci.RunFibonacci());
		System.out.println("Largest palindrome in string: " + Palindrome.RunPalindrome("vsgfsgqwertyyuiiuyytrewqdvsgdfgydydbv"));
		System.out.println("Largest palindome in 3 digit products: " + PalindromicNumbers.RunPalindromicNumbers());
		System.out.println("Largest product in grid: " + GridProduct.RunGridProduct());
		System.out.println("Pythagorean Triplet with a sum of 1000: " + PythagoreanTriplet.RunPythagoreanTriplet());
		System.out.println("Total NameScore: " + NameScores.RunNameScores());
		System.out.println("Difference of pentegonal numbers D: " + PentegonalNumbers.runPentegonalNumbers());
		System.out.println("Smallest Prime Number of 8 prime family: " + PrimeDigitReplacement.runPrimeDigitReplacement());
		System.out.println("Sum of decrypted ASCII text: " + XORdecryption.runXORdecryption());
	}

}
