/*
 *  Author: Wesley Paul
 *  Date: August 09, 2014
 * 
 */

package Problems;

/*
 * 
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 *
 *	a^2 + b^2 = c^2
 *	For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 *	
 *	There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 *	Find the product abc.
 *
 *	Source: https://projecteuler.net/
 */

public class PythagoreanTriplet {
	
	private static final int SUM = 1000;
	
	static int RunPythagoreanTriplet(){
		int a, b, c;
		
		// In order for a < b < c, a must be less than 1/3 the sum.
		for(a=1; a < SUM/3; a++){
			// In order for a < b < c, b must be less than 1/2 the sum.
			for(b=1; b < SUM/2; b++){
				c = SUM - a - b;
				if(a*a + b*b == c*c)
					return a*b*c;
			}
			
		}
		
		return 0;
	}
}
