/*
 *  Author: Wesley Paul
 *  Date: August 20, 2014
 * 
 */


package Problems;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/*
 * In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom 
 * right, by moving left, right, up, and down, is indicated in bold red and is equal to 2297.
 *
 * 131	673	234	103	18
 * 201	96	342	965	150
 * 630	803	746	422	111
 * 537	699	497	121	956
 * 805	732	524	37	331
 *
 * Path: 131, 201, 96, 342, 234, 103, 18, 150, 111, 422, 121, 37, 331
 *
 * Find the minimal path sum, in matrix.txt (right click and 'Save Link/Target As...'), a 31K 
 * text file containing a 80 by 80 matrix, from the top left to the bottom right by moving left,
 * right, up, and down.
 * 
 * Source: https://projecteuler.net/
 * 
 */
public class FourWayPathSum {
	
	private static int [][] matrix = new int[80][80];
	private static int [][] minSum = new int[80][80];
	
	public static int runFourWayPathSum(){
		int i = 0, j = 0;
		String line;
		String [] split;
		boolean changed = true;
		
		
		// Read the text file into a 80x80 matrix of ints
		// Populate each index of the minSum matrix with the largest possible value;
		BufferedReader reader;
		try {
			reader = new BufferedReader( new FileReader ("src/Misc/matrix.txt"));
			while((line = reader.readLine()) != null){
				//System.out.println(line);
				split = line.split(",");
				for(j = 0; j < split.length; j++){
					matrix[i][j] = Integer.parseInt(split[j]);
					minSum[i][j] = Integer.MAX_VALUE;
				}
				i++;
			}
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

		// We know the smallest sum of the most top left index must be the value at that index.
		minSum[0][0] = matrix[0][0];
		
		// While the minSum matrix is still changing.
		while(changed){
			changed = false;
			for(i = 0; i < 80; i++){
				for(j = 0; j < 80; j++){
					// Check if the path coming from the left is smaller.
					if(j-1 >= 0 && (minSum[i][j] > minSum[i][j-1] + matrix[i][j])){
						minSum[i][j] = minSum[i][j-1] + matrix[i][j];
						changed = true;
					}
					// Check if the path coming from the above is smaller.
					if(i-1 >= 0 && (minSum[i][j] > minSum[i-1][j] + matrix[i][j])){
						minSum[i][j] = minSum[i-1][j] + matrix[i][j];
						changed = true;
					}
					// Check if the path coming from the the right is smaller.
					if(j+1 < 80 && (minSum[i][j] > minSum[i][j+1] + matrix[i][j]) && minSum[i][j+1] + matrix[i][j] > 0){
						minSum[i][j] = minSum[i][j+1] + matrix[i][j];
						changed = true;
					}
					// Check if the path coming from the below is smaller.
					if(i+1 < 80 && (minSum[i][j] > minSum[i+1][j] + matrix[i][j]) && minSum[i+1][j] + matrix[i][j] > 0){
						minSum[i][j] = minSum[i+1][j] + matrix[i][j];
						changed = true;
					}
				}
				
			}
		}
		
		// return the shortest path to the bottom right most cell
		return minSum[79][79];
		
	}
	
}
