package com.banking.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.interfaces.AccountService;
import com.banking.models.Account;
import com.banking.services.AccountServiceImpl;

public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AccountService aServ = new AccountServiceImpl();
	private Account acc;
	private List<Account> accs;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getRequestURI(); // "/accounts/withdraw
		String[] parts = path.split("/"); // ["","accounts","withdraw"]
		
		if (parts.length >= 3) { //if extra input
			switch (parts[2].toLowerCase()) {
			case "withdraw": {
				
				int amount = 0; //--placeholder--
				acc = aServ.moveMoney(acc, -amount);
				break;
			}
			case "deposit": {
				
				int amount = 0; //--placeholder--
				acc = aServ.moveMoney(acc, amount);
				break;
			}
			case "transfer": {
				
				int amount = 0; //--placeholder--
				Account transId = null; //--placeholder--
				acc = aServ.moveMoney(acc, transId , -amount);
				break;
			}
			case "status": {
				try {
					int statusId = Integer.parseInt(parts[3]);
					// lookup accounts by status
					accs = aServ.getAccountsByStatus(statusId);
					
				} catch (NumberFormatException e){
					//invalid input
					
				}
				break;
			}
			case "owner": {
				try {
					int userId = Integer.parseInt(parts[3]);
					//lookup accounts by user
					accs = aServ.getAccountsByUser(userId);
					
				} catch (NumberFormatException e){
					//invalid input
					
				}
				break;
			}
			default: {
				// Get specific acc id

				try {
					int accId = Integer.parseInt(parts[2]);
					//lookup account by id
					acc = aServ.getAccountById(accId);
					
				} catch (NumberFormatException e){
					//invalid input
						
					break;
				}
			}
			}
		} else { 
			//lookup all accounts
			accs = aServ.getAllAccounts();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get account from JSON
		
		//submit account
		acc = aServ.openAccount(acc);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get account from JSON
		
		//update account
		acc = aServ.updateAccount(acc);
	}

}
