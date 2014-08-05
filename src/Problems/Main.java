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
	}

}
