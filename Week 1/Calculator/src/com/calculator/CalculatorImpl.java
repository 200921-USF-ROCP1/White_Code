package com.calculator;

public class CalculatorImpl implements Calculator{
	
	// Basic operations
		@Override
		public int add(int a, int b) {
			return a + b;
		}
		
		@Override
		public int subtract(int a, int b) {
			return  a - b;
		}
		
		@Override
		public int multiply(int a, int b) {
			return a*b;
		}
		
		@Override
		public int divide(int a, int b) {
			return a/b;
		}
		
		// Advanced operations
		
		// Return x to the power e
		@Override
		public int exponent(int x, int e) {
			int test = 1 ;
			for (int i = 1; i <= e ; i++) {
				test *= x ;
			}
			return test;
			
		}
		// Return fib(i) ith digit in sequence
		@Override
		public int fibonacci(int i) {
			if (i == 0 || i == 1) {
				return i;
			}
			
			return fibonacci(i-1) + fibonacci(i-2);
		}
		
		// Parse a String into parameters and an operation
		// eg, given "5 + 2", return add(5, 2)
		// Look into String.parse()
		@Override
		public int parse(String s) {
			
			int num1 = 0;
			int num2 = 0;
			
			String test[] = s.split(" ");
			
			if (Character.isDigit(test[0].charAt(0))) {
				
				try {
					num1 = Integer.parseInt(test[0]);
					num2 = Integer.parseInt(test[test.length-1]);
				} catch (Exception e) {
					System.out.println("Invalid Operands");
					return 0;
				}
				
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
					
					// Test for fibonacci sequence
					if (test[0].equalsIgnoreCase("fib")) {
						return fibonacci(num2);
					} else {
						System.out.println("Operation not recognized");
						return 0;
					}
				}
				}
			} else {
				
				switch (test[0]) {
				case "fib": {
					return fibonacci(Integer.parseInt(test[1]));
				} 
				case "exp": {
					return exponent(Integer.parseInt(test[1]),Integer.parseInt(test[2]));
				}
				default: {
					System.out.println("Operation not recognized");
					return 0;
				}
				}
				
			}
			
		}
}
