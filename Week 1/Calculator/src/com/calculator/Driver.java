package com.calculator;

public class Driver {
	public static void main(String[] args) {
		int num1 = 10;
		int num2 = 2;
		
		CalculatorImpl c = new CalculatorImpl();
		
		System.out.println(c.add(num1,num2));
		System.out.println(c.subtract(num1,num2));
		System.out.println(c.multiply(num1,num2));
		System.out.println(c.divide(num1,num2));
		System.out.println(c.exponent(num1,num2));
		
		System.out.println(c.fibonacci(8));
		
		System.out.println(c.parse("fib 8"));
		
	}
}
