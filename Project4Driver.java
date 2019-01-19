/** 
 * Word Ladder Driver
 * Spring 2018
 * Daniel Looney
 */

import java.io.*;
import java.util.*;

public class Project4Driver {
	public static void main(String[] args) throws FileNotFoundException {
		LengthMap m1 = new LengthMap();
		
		//Populates LengthMap from given dictionary file
		m1.readFromFile(new Scanner(new File("Files/dict.txt")));
		
		//Prints all words to output file sorted by length
		PrintStream outfile = new PrintStream(new File("Files/myoutput.txt"));
		outfile.println(m1.toString());
		
		//User interaction block
		System.out.println("This program uses a dictionary to compute distances between pairs of words.\n");
		Scanner input = new Scanner(System.in);
		boolean notValid = true;
		String startWord = "";
		String targetWord = "";
		while(notValid) {
			System.out.println("Enter two words to be compared.");
			System.out.print("Enter the first word: ");
			startWord = input.next();
			System.out.print("Enter the second word: ");
			targetWord = input.next();
			
			// Tests if the provided words are inequal lengths or not in the dictionary
			if(startWord.length() != targetWord.length() || !m1.existsInMap(startWord) || !m1.existsInMap(targetWord)) {
				System.out.println("The provided words are of uneven length, or does not exist in the dictionary. Please try again.\n");
				notValid = true;
			}
			else {
				notValid = false;
			};
		}
		input.close();
		
		
		ArrayList<String> toCompare = m1.listCopy(startWord.length());
		Distance1Map d1 = new Distance1Map();
		d1.listToMap(toCompare);
		
		int editDistance = d1.minDistance(startWord, targetWord);
		//If the distance is not found
		if (editDistance == -1) {
			System.out.println("Could not find the distance based on the provided dictionary.");
		}
		//If the distance is found
		else {
			System.out.println("Minimum edit distance from " + startWord + " to " + targetWord + " is " + editDistance + " edits.");
		}
		
		//Prints the key-value mappings of the given length to the output file
		outfile.println();
		outfile.println(d1.toString());
		//Close the file after program exit
		outfile.close();
	}
	
	
}
