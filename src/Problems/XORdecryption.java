/*
 *  Author: Wesley Paul
 *  Date: August 12, 2014
 * 
 */

package Problems;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Each character on a computer is assigned a unique code and the preferred standard is ASCII
 * (American Standard Code for Information Interchange). For example, uppercase A = 65, asterisk 
 * (*) = 42, and lowercase k = 107.
 *
 * A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each
 * byte with a given value, taken from a secret key. The advantage with the XOR function is that 
 * using the same encryption key on the cipher text, restores the plain text; for example, 65 XOR 
 * 42 = 107, then 107 XOR 42 = 65.
 *
 * For unbreakable encryption, the key is the same length as the plain text message, and the key 
 * is made up of random bytes. The user would keep the encrypted message and the encryption key 
 * in different locations, and without both "halves", it is impossible to decrypt the message.
 *
 * Unfortunately, this method is impractical for most users, so the modified method is to use a
 * password as a key. If the password is shorter than the message, which is likely, the key is 
 * repeated cyclically throughout the message. The balance for this method is using a sufficiently 
 * long password key for security, but short enough to be memorable.
 *
 * Your task has been made easy, as the encryption key consists of three lower case characters. 
 * Using cipher1.txt (right click and 'Save Link/Target As...'), a file containing the encrypted 
 * ASCII codes, and the knowledge that the plain text must contain common English words, decrypt 
 * the message and find the sum of the ASCII values in the original text.
 * 
 * Source: https://projecteuler.net/
 * 
 */

public class XORdecryption {

	// This array holds the most common 7 letters in the English language plus the Space character.
	private static final int [] commonletters = {32,191,116,97,111,105,110,115}; // Space, e, t, a, o, i, n, s
	private static final int MAXASCII = 500;
	
	/*
	 * The algorithm used for solving this problem is as follows:
	 * Since we know the key is 3 characters long we must split the cipher text into 3 groups
	 * which hold the characters which map to each character of the key. (i.e. abcabcabcabc, 
	 * group a's b's and c's).  Using these groups and the known most common letters in the
	 * English language we can find which key is most likely by XORing each common letter
	 * to each ciphertext. The key that is most common likely to be the correct key. Using the 
	 * new found key the cipher text can be decoded and the sum found.
	 */
	public static int runXORdecryption(){
		int total = 0;
		
		try {
			int key1, key2, key3;
			
			// Read the ciphertext into a string and then split it into a list.
			String cipher = new String(Files.readAllBytes(Paths.get("src/Misc/cipher1.txt")), StandardCharsets.UTF_8);
			List<String> cipherLetters = Arrays.asList(cipher.split(","));
			List<Integer> row1 = new ArrayList<Integer>(), row2 = new ArrayList<Integer>(), row3 = new ArrayList<Integer>();
			
			// Split the list of characters into 3 groups
			for(int i = 0; i < cipherLetters.size(); i+=1){
				row1.add(Integer.parseInt(cipherLetters.get(i)));
				i++;
				if(i < cipherLetters.size())
					row2.add(Integer.parseInt(cipherLetters.get(i)));
				i++;
				if(i < cipherLetters.size())
					row3.add(Integer.parseInt(cipherLetters.get(i)));
			}
 
			// Find the key for each group.
			key1 = getProbableKey(row1);
			key2 = getProbableKey(row2);
			key3 = getProbableKey(row3);
			
			// Decode the ciphertext and find the sum.
			for(int i = 0; i < cipherLetters.size(); i+=1){
				total += key1^Integer.parseInt(cipherLetters.get(i));
				i++;
				if(i < cipherLetters.size())
					total += key2^Integer.parseInt(cipherLetters.get(i));
				i++;
				if(i < cipherLetters.size())
					total += key3^Integer.parseInt(cipherLetters.get(i));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			

		return total;
	}
	
	// Uses the above described algorithm to determine the most probable key given
	// a group of cipher text from the english language.
	private static int getProbableKey(List<Integer> row){
		
		int [][] countCipher = new int [MAXASCII][2];
		int [][] countKey = new int [MAXASCII][2];
		int j;
		
		// Populate the countCipher 2D array by determining how often each character occurs
		for(j = 0; j < row.size(); j++){
			countCipher[row.get(j)][0] = row.get(j); 
			countCipher[row.get(j)][1]++; 
		}
		
		// Sort the array from most common to least common.
		Arrays.sort(countCipher, new java.util.Comparator<int[]>() {
		    public int compare(int[] a, int[] b) {
		    	return Integer.compare(b[1], a[1]);
		    }
		});
		
		// For every character that occurs more than 15 times XOR each common
		// character, place it in the keylist and increment that key's count.
		for(j = 0; j < countCipher.length; j++){
			if(countCipher[j][1] < 15)
				break;
			for(int letter: commonletters){
				countKey[countCipher[j][0]^letter][0] = countCipher[j][0]^letter;
				countKey[countCipher[j][0]^letter][1]++;
			}
		}
		
		// Sort the keylist from most common to least common.
		// The key with the highest count is most likely the correct key.
		Arrays.sort(countKey, new java.util.Comparator<int[]>() {
		    public int compare(int[] a, int[] b) {
		    	return Integer.compare(b[1], a[1]);
		    }
		});
			
		return countKey[0][0];
	}
	
}
