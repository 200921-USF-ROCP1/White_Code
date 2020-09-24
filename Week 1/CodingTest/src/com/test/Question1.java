package com.test;

public class Question1 {

	public static void main(String[] args) {
		// INPUT [uncomment & modify if required]
		String input = "invigi liati on";

		// sampleInput = // int.Parse(Console.ReadLine());

		// write your Logic here:
		String letters = String.valueOf(input.charAt(0));
		System.out.println(letters);

		for (int i = 1; i < input.length(); i++) {
			if (!letters.contains(String.valueOf(input.charAt(i)))) {
				letters += String.valueOf(input.charAt(i));
			}

			// OUTPUT [uncomment & modify if required]
			System.out.println(letters);
			
			String[] words = input.split(" ");
	        
			String[] rewords = new String[words.length];
			
			String str = "Hello World!";
			
			String str1 = "Apple";
			
			String str2 = "Bannana";
			
			String str3 = "Apple";
			
			String str4 = new String("Apple");
			
		}
		
	}

}