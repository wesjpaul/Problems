
/*
 *  Author: Wesley Paul
 *  Date: July 22, 2014
 * 
 */

package Problems;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over 
 * five-thousand first names, begin by sorting it into alphabetical order. Then working out 
 * the alphabetical value for each name, multiply this value by its alphabetical position in 
 * the list to obtain a name score.
 *
 *	For example, when the list is sorted into alphabetical order, 
 *	COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. 
 *	So, COLIN would obtain a score of 938 × 53 = 49714.
 *	
 *	What is the total of all the name scores in the file?
 */

public class NameScores {
	
	// A list of the letters of the alphabet.
	private static final List<Character> alphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
	
	public static int RunNameScores(){
		int i = 1, NameScore = 0;
		 
		try {
			// Open the file and read it into a String.
			BufferedReader reader = new BufferedReader( new FileReader ("src/Misc/names.txt"));
			String file = reader.readLine();
			// Separate the names and place into a list.
			List<String> names = Arrays.asList(file.replace("\"", "").split(","));
			// Sort the names alphabetically.
			Collections.sort(names);
			
			// Cycle through the list find the score and multiply by the index.
			for (String name : names) {
				NameScore += getScore(name)*i;
				i++;	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		
		return NameScore;
	}
	
	/*
	 * Retrieves a string and determines its score. 
	 */
	private static int getScore(String name){
		int score = 0;
		// Cycle through each character and add the score to the total
		for (char c: name.toCharArray()) {
			score += alphabet.indexOf(c) + 1;	
		}
		return score;
	}

}
