/*
 *  Author: Wesley Paul
 *  Date: August 06, 2014
 * 
 */

package Problems;

/*
 *	A palindromic number reads the same both ways. The largest palindrome made 
 *	from the product of two 2-digit numbers is 9009 = 91 × 99.
 *	Find the largest palindrome made from the product of two 3-digit numbers.
 *
 *	Source: https://projecteuler.net/
 */


public class PalindromicNumbers {
	
	private static final int MAXVALUE = 999;
	private static final int MINVALUE = 99;
	
	private static boolean palindrome(int num){
		String number = String.valueOf(num);
		String reverse = new StringBuilder(number).reverse().toString();
		
		return number.equals(reverse);
		
	}
	
	public static int RunPalindromicNumbers(){
		int i = 0, j = 0;
		int largest = 0;
		
		// Multiply all 3 digit values together starting from MAXVALUE 
		// and iterating down
		
		for(i=MAXVALUE; i > MINVALUE; i--){
			// if the current iteration multiplied by MAXVALUE is less than the largest found 
			// palindrome then we are done.
			if(i*MAXVALUE <= largest)
				break;
			
			for(j=MAXVALUE; j > MINVALUE; j--){
				// if the current product iteration is less than the largest known palindrome
				// then we are done with this value of i
				if(i*j <= largest)
					break;
				// if a palindrome is found it must be the largest so far.
				if(palindrome(i*j))
					largest = i*j;
				
			}
		}
		
		return largest;
	}
	
	
}
