/*
 *  Author: Wesley Paul
 *  Date: August 05, 2014
 * 
 */


package Problems;

/*
 *	Find the largest palindrome in a string.
 */

public class Palindrome {

	public static String RunPalindrome(String text){
		String palindrome = "";
		int largestSize = 0;	
		int i, j, size;
		
		
		// Go through each character in the string
		for(i=1; i < text.length(); i++){
			
			// Case 1:
			
			j = 1;
			size = 1;
			
			// Check if the previous and next characters are equal (ex. XYX)
			if((i+1 < text.length()) && text.charAt(i-1) == text.charAt(i+1)){
				
				// Continue to move outward to check if characters are equal until
				// the string ends or characters are no longer equal
				while((i-j >= 0 && i+j < text.length()) && (text.charAt(i-j) == text.charAt(i+j))){
					j++;
					size+=1;
				}
				
				// Check a new largest palindrome was found and place it in the return string
				if(size > largestSize){
					largestSize = size;
					palindrome = text.substring(i-size+1, i+size);
				}
			}
			

			// Case 2: 
			
			j = 1;
			size = 1;
			
			// check if the current and previous characters are equal (ex. XYYX)
			if(text.charAt(i - 1)==text.charAt(i)){
				
				// Check if previous and next characters are equal until
				// the string ends or characters are no longer equal
				while((i-j-1 >= 0 && i+j < text.length()) && (text.charAt(i-j-1) == text.charAt(i+j))){
					j++;
					size+=1;
				}
				
				// Check a new largest palindrome was found and place it in the return string
				if(size > largestSize){
					largestSize = size;
					palindrome = text.substring(i-size, i+size);
				}
				
			}
			
			
		}
		
		return palindrome;
	}
	
}
