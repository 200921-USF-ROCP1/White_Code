package com.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTests {
	
	@Test
	public void test1() {
		//Setup
		Calculator calc = new CalculatorImpl();
		
		int a = 3, b = 2; //Initialize parameters
		int testAnswer = 5;
		
		int actualAnswer = calc.add(a, b);
		
		Assertions.assertEquals(testAnswer, actualAnswer);
	}
}
