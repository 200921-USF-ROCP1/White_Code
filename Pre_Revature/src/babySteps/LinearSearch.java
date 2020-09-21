package babySteps;

import java.util.Arrays;

public class LinearSearch {

	 

	public static void main(String[] args) {
	
		//create some dummy data for our method
	
	   char letter = 's';
	
	   char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
	
			   's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	
	
	   //call your methods here
	
	   LinearSearch ls = new LinearSearch();
	
	   System.out.println(ls.findIndex(letter, letters));
	   System.out.println(ls.findLetterBackwards(letter, letters));
	   System.out.println(ls.findMiddle(letter, letters));
	
	}

 

	public int findIndex(char target, char[] data) {
	
		
		//loop through the data
		
		for (int i = 0; i < data.length; i++) {

		
		//test current element against target
		
		if (data[i] == target) {
		
			return i;
		
		}

	
		}

		return -1;
	}

	
	public int findLetterBackwards(char target, char[] data ) {

		for (int i = data.length-1; i >= 0; i--) {

			
			//test current element against target
			
			if (data[i] == target) {
			
				return i;
			
			}

		
			}

			return -1;
	}
	
	
	public int findMiddle(char target, char[] data ) {
		
		char[] lowData = Arrays.copyOfRange(data, 0, data.length/2);
		char[] highData = Arrays.copyOfRange(data, data.length/2, data.length);
		
		int lowIdx = findLetterBackwards(target,lowData);
		int highIdx = findIndex(target,highData);
		
		if (lowIdx != -1) return lowIdx;
		else if (highIdx != -1) return highIdx + lowData.length;
		
		return -1;
		
	}
}
