package service;

import java.util.Scanner;

import utils.ConnectionManager;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input;
		
		ConnectionManager driver = new ConnectionManager();
		driver.startup();
		
		
		
		/*
		while (true) {
			
			input = sc.nextLine();
			
			if (input.toLowerCase() == "exit") {
				System.out.println("Stopping program....");
				return;
			}
			
			
		}
		*/
			
	}
	
}
