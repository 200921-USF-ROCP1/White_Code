package com.calculator;

import java.util.Scanner;

public class Driver {
	/*
	 * Using your Calculator implementation,
	 * add exception handling to it and complete
	 * the method calculate() below.
	 */
	public static void main(String[] args) {
		
		boolean running = true;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to the Calculator!");
		System.out.println("Enter your command below:");
		
		while (running) {
			running = calculate(sc);
		} 
		
		sc.close();
	}
	
	/*
	 * Calculate should:
	 * 1. Take input via the Scanner class
	 * 2. Parse the input (this is a part of the Calculator interface)
	 * 3. If it is valid input, output the result
	 * 4. If it is invalid, output an error
	 * 
	 * Because it is in an infinite loop, you only need 
	 * to do those four steps.
	 */
	public static boolean calculate(Scanner sc) {

		CalculatorImpl calc = new CalculatorImpl();
		
		System.out.print(":>> ");
		String input = sc.nextLine();
		
		if (input.equalsIgnoreCase("Exit") || input.length() == 0) {
			return false;
			
		} else if (input.contains(" ")) {
			System.out.println(" = " + calc.parse(input));
			
		} else {
			System.out.println("Spaces must be included between inputs");
		}	

		return true;
	}
}
