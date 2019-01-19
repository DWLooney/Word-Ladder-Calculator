/** 
 * Word Ladder Parser
 * Spring 2018
 * Daniel Looney
 */

import java.util.*;

public class LengthMap {
	private TreeMap<Integer, ArrayList<String>> lengthMap; 
	
	public LengthMap() {
		lengthMap = new TreeMap<Integer, ArrayList<String>>();
	}
	
	public void readFromFile(Scanner infile) {
		while(infile.hasNextLine()){
			//Assumes there is only one word on each line
			String parser = infile.nextLine();
			//Create new key and empty list if there is none associated for the length of the given word
			if(!this.lengthMap.containsKey(parser.length())) {
				this.lengthMap.put(parser.length(), new ArrayList<String>());
				
			}
			//Add word for the associated key to its list
			this.lengthMap.get(parser.length()).add(parser);
		}
	}
	
	public String toString() {
		String returnString = "";
		
		//Iterates between the lowest key and highest key
		for(int x = lengthMap.firstKey(); x < this.lengthMap.size() + lengthMap.firstKey(); x++) {
			returnString += "Size " + x + ": " + this.lengthMap.get(x).toString() + "\n"; 
		}
		return returnString;
	}
	
	public ArrayList<String> listCopy(int stringLength) {
		ArrayList<String> returnList = new ArrayList<String>();
		//Creates a deep copy of parameter list
		for (String x : this.lengthMap.get(stringLength)){
			returnList.add(x);
		}
		return returnList;
	}
	
	public boolean existsInMap(String testString) {
		//Check if key for the length exists, if it does iterates through all values in the associated list until it finds a match
		//If it doesn't returns false
		if(this.lengthMap.containsKey(testString.length())) {
			for (String x : lengthMap.get(testString.length())) {
				if(x.equals(testString)) {
					return true;
				}
			}
		}
		return false;
	}
}
