package com.calculator;

public class CalculatorImpl implements Calculator{
	
	// Basic operations
		public int add(int a, int b) {
			return a + b;
		}
		
		public int subtract(int a, int b) {
			return  a - b;
		}
		
		public int multiply(int a, int b) {
			return a*b;
		}
		
		public int divide(int a, int b) {
			return a/b;
		}
		
		// Advanced operations
		
		// Return x to the power e
		public int exponent(int x, int e) {
			int test = 1 ;
			for (int i = 1; i <= e ; i++) {
				test *= x ;
			}
			return test;
			
		}
		// Return fib(i) ith digit in sequence
		public int fibonacci(int i) {
			if (i == 0 || i == 1) {
				return i;
			}
			
			return fibonacci(i-1) + fibonacci(i-2);
		}
		
		// Parse a String into parameters and an operation
		// eg, given "5 + 2", return add(5, 2)
		// Look into String.parse()
		public int parse(String s) {
			String test[] = s.split(" ");
			int num1 = Integer.parseInt(test[0]);
			int num2 = Integer.parseInt(test[test.length-1]);
			
			switch (test[1]) {
			case "+":  {
				return add(num1,num2);
			}
			case "-": {
				return subtract(num1,num2);
			}
			case "*": {
				return multiply(num1,num2);
			}
			case "/": {
				return divide(num1,num2);
			}
			case "^": {
				return exponent(num1,num2);
			}
			default: {
				System.out.println("Error: Input not recogized");
				return 0;
			}
			}
		}
}
