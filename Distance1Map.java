/** 
 * Creates a map linking words to their lengths.
 * Spring 2018
 * Daniel Looney
 */

import java.util.*;

public class Distance1Map {
	
	private HashMap<String, ArrayList<String>> distMap;
	
	public Distance1Map() {
		distMap = new HashMap<String, ArrayList<String>>();
	}
	
	public void listToMap(ArrayList<String> listToConvert) {
		//Nested for loops to iterate through all possible combinations of words
		for(String key : listToConvert) {
			this.distMap.put(key, new ArrayList<String>());
			
			for(String val : listToConvert) {
				//Only adds to arraylist for the key if they are only different by one character
				if (similarChars(key, val) == val.length() - 1) {
					this.distMap.get(key).add(val);
					
				}
			}
		}
	}
	
	public boolean containsWord(String word) {
		
		if (this.distMap.containsKey(word)) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String returnString = "";
		// Creates a set of all keys in the map
		Set<String> keyList = distMap.keySet();
		
		//Iterates through all keys in the set, and prints the key and its associated values, if any
		for(String key : keyList) {
			if (distMap.get(key).toString().equals("[]"))
				//String to append if there is no associated value
				returnString += "Key: " + key + "\nValues: None" + "\n";
			else {
				//String to append if there are associated values
				returnString += "Key: " + key + "\nValues: " + distMap.get(key).toString() + "\n";
			}
		}
		return returnString;
	}
	
	// Helper method to determine how many characters two words share
	public int similarChars(String s1, String s2) {
		int similarChars = 0;
		for (int x = 0; x < s1.length(); x++) {
			if (s1.charAt(x) == s2.charAt(x)) {
				similarChars++;
			}
		}
		return similarChars;
	}
	
	//Helper class to store the current level the node is on as well as the word and a list of the previous words in the ladder
	private class BFSNode{
		String word;
		int level;
		ArrayList<String> traversedLadder;
		public BFSNode(String newWord, int newLevel, ArrayList<String> prevLadder){
			this.word = newWord;
			this.level = newLevel;
			
			traversedLadder = new ArrayList<String>();
			//Create a deep copy of the list of words from the previous nodes
			for (String str : prevLadder) {
				traversedLadder.add(str);
			}
			//add this node's word to the list
			traversedLadder.add(word);
		}
	}
	
	public int minDistance(String s1, String s2) {
		HashSet<String> processedWords = new HashSet<String>();
		
		//Create a queue consisting of the Breadth First Search data type as outlined above
		Queue<BFSNode> distQueue = new LinkedList<BFSNode>();
		
		//Enter the initial value into the queue
		distQueue.offer(new BFSNode(s1, 0, new ArrayList<String>()));
		
		while(!distQueue.isEmpty()) {
			BFSNode compareTo = distQueue.remove();
			
			//returns if compareTo is only one letter away as opposed to equal to solve first-past-the-post issue
			if (similarChars(compareTo.word, s2) == s1.length() - 1) {
				//Add the final word to the arraylist of the answer since it doesn't do that in the algorithm
				compareTo.traversedLadder.add(s2);
				System.out.println(compareTo.traversedLadder.toString());
				return compareTo.level + 1;
			}
			
			else {
				//Load all similar words into queue as part of a node
				for (String str : distMap.get(compareTo.word)) {
					if (!processedWords.contains(str)) {
						distQueue.offer(new BFSNode(str, compareTo.level + 1, compareTo.traversedLadder));
						processedWords.add(str);
					}
				}
			}
		}
		return -1;
	}
	
	
}
