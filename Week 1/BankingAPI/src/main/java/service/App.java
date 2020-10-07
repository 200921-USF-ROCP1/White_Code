package service;
import java.util.Scanner;
import com.dao.*;
import com.users.User;

import utils.ConnectionManager;


public class App {

	public static Scanner sc  = new Scanner(System.in);
	private static AccountService accS= new AccountService();
	private static UserService userS = new UserService();
	
	public static void main(String[] args) {
		User user = null;
		System.out.println("Welcome to the only bank!");
		System.out.println("\tPlease log in below");
		while (true) {
			//login
			
			System.out.print("Username: ");
			String username = sc.nextLine();
			if (username.equalsIgnoreCase("exit")) break;
			
			System.out.print("Password: ");
			String password = sc.nextLine();
			if (password.equalsIgnoreCase("exit")) break;
			
			user = userS.login(username,password);
			if (user != null) {
				System.out.println("\n");
				accS.listUserAccounts(user);
			}
			
			while (user != null) { //continue until exit
				System.out.print("=:> ");
				String input = sc.nextLine();
				
				if (input.equalsIgnoreCase("exit")) {
					System.out.println("Stopping program....");
					return;
				} else if (input.trim() == "") //only spaces
					continue;
				
				
				switch (input.toLowerCase()) {
				case "logout": {
					// logout procedure
					user = userS.logout();
					break;
				}
				case "register": {
					//register new user
					if (user.getRole().toLowerCase().equals("admin")) {
						userS.register();
					} else 
						System.out.println("You do not have permission to register a new user.\n");
					break;
				}
				case "accounts": {
					//list user accounts
					if (user.getRole().toLowerCase().equals("admin") || user.getRole().toLowerCase().equals("employee")) {
						//enter user id
						System.out.println("User Id:");
						int userId = sc.nextInt();
						accS.listUserAccounts(userId);
					} else 
						//get users own accounts
						accS.listUserAccounts(user);
					break;
				}
				case "open": {
					//open a new account
					if (user.getRole().toLowerCase().equals("admin") || user.getRole().toLowerCase().equals("employee")) {
						//enter user id
						System.out.println("User Id:");
						int userId = sc.nextInt();
						accS.openAccount(userId);
					} else 
						//get users own accounts
						accS.openAccount(user);
					break;
				}
				case "withdraw": {
					//withdraw money
					if (user.getRole().toLowerCase().equals("admin") || user.getRole().toLowerCase().equals("employee")) {
						//enter user id
						System.out.println("User Id:");
						int userId = sc.nextInt();
						accS.openAccount(userId);
					} else 
						//get users own accounts
						accS.openAccount(user);
					break;
				}
				default: {
					System.out.println("\tUnknown command.\n");
				}
				
				}
				
			}
		}
	}
	public void finalize() {
		ConnectionManager.closeConnection();
		sc.close();
	}
	
}
