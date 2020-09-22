package com.matthew;

public class Driver {
	public static void main(String[] args) {
		
		int test = 15;
		
		FizzBuzz(test);
	}
	
	public static void FizzBuzz(int a) {
		/* Print all values up to input
		 * If divisible by 3, print 'Fizz'
		 * If divisible by 5, print 'Buzz'
		 * If divisible by both, print 'FizzBuzz'
		 */

		if (a <= 0) {
			System.out.println("Input must be greater than 0.");
			return;
		}
		
		for (int i = 1; i <= a; i++) {
			
			if (i%3 == 0 && i%5 == 0 ) {
				System.out.println("FizzBuzz");
			} else if (i%3 == 0) {
				System.out.println("Fizz");
				
			} else if (i%5 == 0) {
				System.out.println("Buzz");
			} else {
				System.out.println(i);
			}
		}
	}
}
