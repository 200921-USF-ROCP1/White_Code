package com.banking.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banking.interfaces.AccountService;
import com.banking.models.Account;
import com.banking.models.User;
import com.banking.services.AccountServiceImpl;
import com.banking.utils.Authentication;
import com.banking.utils.JsonInterp;
import com.banking.utils.SessionManager;
import com.banking.utils.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JsonInterp<Account> interp = new JsonInterp<Account>(Account.class);
	
	private static AccountService aServ = new AccountServiceImpl();
	private static SessionManager sm = new SessionManager();;
	
	private Account acc;
	private List<Account> accs;
	private String jsonString = null;
	private JsonNode node;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sm.createSession(request); //check if logged in
		if (sm.getUser() == null) {
			response.sendError(403, "Not logged in.");
			return;
		}

		String path = request.getRequestURI(); // "BankingAPI/accounts/withdraw
		String[] parts = path.split("/"); // ["","BankingAPI","accounts","withdraw"]
		
		if (parts.length >= 4) { //if extra input
			switch (parts[3].toLowerCase()) {
			
			case "withdraw": {
				node = interp.getNode(request);
				int accId = node.get("accountId").asInt();
				double amount = node.get("amount").asDouble();
				
				if (amount < 0) { //make sure amount is positive
					response.sendError(400,"Amount must be positive");
					return;
				}
				
				if (Authentication.canAccess("Admin") || ownsAccount(sm.getUser(),accId) ) {
					// complete withdrawal
					if (aServ.moveMoney(accId, -amount)) { //withdraw successful
						response.setStatus(200);
						//return message
						jsonString = String.format("$%.2f has been withdrawn from Account #%d",
								amount, accId);
					} else response.sendError(400, "Withdrawal failed.");
				} else response.sendError(401, "You do not have access to this account.");
				break;
			}
			
			case "deposit": {
				node = interp.getNode(request);
				int accId = node.get("accountId").asInt();
				double amount = node.get("amount").asDouble();
				
				if (amount < 0) { //make sure amount is positive
					response.sendError(400,"Amount must be positive");
					return;
				}
				
				if (Authentication.canAccess("Admin") || ownsAccount(sm.getUser(),accId) ) {
					// complete deposit
					if (aServ.moveMoney(accId, amount)) { //deposit successful
						response.setStatus(200);
						//return message
						jsonString = String.format("$%.2f has been deposited in Account #%d",
								amount, accId);
					} else response.sendError(400, "Deposit failed.");
				} else response.sendError(401, "You do not have access to this account.");
				break;
			}
			
			case "transfer": {
				node = interp.getNode(request);
				int accId = node.get("sourceAccountId").asInt();
				int transId = node.get("targetAccountId").asInt();
				double amount = node.get("amount").asDouble();
				
				if (amount < 0) { //make sure amount is positive
					response.sendError(400,"Amount must be positive");
					return;
				}
				
				if (Authentication.canAccess("Admin") || ownsAccount(sm.getUser(),accId) ) {
					// complete transfer
					if (aServ.moveMoney(accId, transId, amount)) { //transfer successful
						response.setStatus(200);
						//return message
						jsonString = String.format("$%.2f has been transferred from Account #%d to Account #%d",
								amount, accId, transId);
					} else response.sendError(400, "Transfer failed.");
				} else response.sendError(401, "You do not have access to this account.");
				break;
			}
			
			case "status": { //get all accounts by status
				if (Authentication.canAccess("Employee")) {
					if (StringUtils.isInteger(parts[4])) {
						int statusId = Integer.parseInt(parts[4]);
						// lookup accounts by status
						accs = aServ.getAccountsByStatus(statusId);
						
						if (accs != null) {
							//return account
							jsonString = interp.marshal(accs);
							
						} else response.sendError(401, "Accounts not found.");
					}
				} else response.sendError(401, "Employee access only.");
				break;
			}
			
			case "owner": { //get account by user id
				if (parts.length==5) { //if userid is in uri
					if (StringUtils.isInteger(parts[4])) {
						int userId = Integer.parseInt(parts[4]);
						
						if (Authentication.canAccess("Employee") || sm.getUser().getUserId() == userId ) {
							accs = aServ.getAccountsByUser(userId); //get accounts
							
							if (accs != null) {
								//return account
								jsonString = interp.marshal(accs);
								
							} else response.sendError(404, "Account not found");
						}  else response.sendError(401, "You do not have access to this account.");
					}
				} else { //user wants own accounts
					accs = aServ.getAccountsByUser(sm.getUser().getUserId()); //get accounts
					
					if (accs != null) {
						//return account
						jsonString = interp.marshal(accs);
						
					} else response.sendError(404, "You do not have any accounts.");
				}
				break;
			}
			
			default: {
				// -- find specific account --
				if (StringUtils.isInteger(parts[3])) {
					int accId = Integer.parseInt(parts[3]); //get accId for uri
					
					//authenticate
					if (Authentication.canAccess("Employee") || ownsAccount(sm.getUser(),accId) ) {
						//lookup account by id
						acc = aServ.getAccountById(accId);
						if (acc != null) {
							//return account
							jsonString = interp.marshal(acc);
							
						} else response.sendError(404, "Account not found.");
					} else response.sendError(401, "You must own this account to access it.");
				} else response.sendError(400);
				break;
			}
			}
		} else { 
			// -- find all accounts --
			if (Authentication.canAccess("Employee")) { //check access
				accs = aServ.getAllAccounts();
				
				if (accs != null) {
					//return account
					jsonString = interp.marshal(accs);
					
				} else response.sendError(404, "Accounts not found.");
			} else response.sendError(401, "Employee access only.");
		}
		
		//return output
		if (jsonString != null) {
			response.getWriter().println(jsonString); //send json string
			jsonString = null;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sm.createSession(request); //check if logged in
		if (sm.getUser() == null) {
			response.sendError(403, "Not logged in.");
			return;
		}
		
		String path = request.getRequestURI(); // "BankingAPI/accounts/1
		String[] parts = path.split("/"); // ["","BankingAPI","accounts","1"]
		
		
		
		if (parts.length == 4) { // -- user specified alongside new account -- 
			
			// use /accounts/:id to create an account for that user.
			if (StringUtils.isInteger(parts[3])) { //parse userId from uri
				int userId = Integer.parseInt(parts[3]); //get accId for uri
				
				// get account from JSON
				acc = interp.unmarshal(request);
				acc.setStatus(acc.statusId);
				acc.setType(acc.typeId);
				
				// authenticate based on userId and session user
				if (Authentication.canAccess("Employee") || sm.getUser().getUserId() == userId ) {
					acc = aServ.openAccount(acc);
					aServ.addUserToAccount(userId, acc.getId());
					jsonString = interp.marshal(acc);
				} else response.sendError(401);
				
			} else if(parts[3].equals("add"))  { // -- Add a user to account --
				node = interp.getNode(request);
				int userId = node.get("userId").asInt();
				int accId = node.get("accountId").asInt();
				
				if (Authentication.canAccess("Admin") || ownsAccount(sm.getUser(),accId) ) {
					if (Authentication.canAccess("Premium")) { //if you own the account, must be a premium user
						aServ.addUserToAccount(userId, accId);
						response.setStatus(202); //created
						jsonString = String.format("User %d added to Account #%d", userId,accId); //response
						
					} else response.sendError(401,"Must be a 'Premium' user to add others to your account.");
				} else response.sendError(401, "Only an Admin can change other people's accounts.");
			} else response.sendError(400,"Invalid address.");
			
		} else { // -- create an account without a user -- 
			
			// get account from JSON
			acc = interp.unmarshal(request);
			acc.setStatus(acc.statusId);
			acc.setType(acc.typeId);
			
			if (Authentication.canAccess("Employee")) {
				acc = aServ.openAccount(acc);
				jsonString = interp.marshal(acc);
			} response.sendError(401, "Employee access only");
			
		}
		
		//return account
		if (jsonString != null) {
			response.getWriter().println(jsonString); //send json string
			jsonString = null;
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sm.createSession(request); //check if logged in
		if (sm.getUser() == null) {
			response.sendError(403, "Not logged in.");
			return;
		}
		
		// -- Update account -- 
		acc = interp.unmarshal(request);
		acc.setStatus(acc.statusId);
		acc.setType(acc.typeId);
		
		if (Authentication.canAccess("Admin") || ownsAccount(sm.getUser(),acc.getId()) ) {
			//update account
			acc = aServ.updateAccount(acc);
			//return account
			jsonString = interp.marshal(acc);
		} else response.sendError(401, "Only an admin can update accounts.");
		
		
		
		//return account
		if (jsonString != null) {
			response.getWriter().println(jsonString); //send json string
			jsonString = null;
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sm.createSession(request); //check if logged in
		if (sm.getUser() == null) {
			response.sendError(403, "Not logged in.");
			return;
		}
		
		String path = request.getRequestURI(); // "BankingAPI/accounts/remove/1
		String[] parts = path.split("/"); // ["","BankingAPI","accounts","remove","1"]
		
		if (parts.length == 5) { //correct input length
			if(StringUtils.isInteger(parts[4])) { //parse uri
				int accId = Integer.parseInt(parts[4]);
				
				// authenticate logged-in account
				if (Authentication.canAccess("Admin")) {
					
					//check if account exists
					acc = aServ.getAccountById(accId);
					if (acc == null) {
						response.sendError(404, "Account not found.");
						return;
					}
					
					if (aServ.deleteAccount(accId)) { //account deleted successfully
						response.getWriter().println(String.format("Account #%d deleted successfully.", accId));
					} else response.sendError(400, "Deletion failed.");
					
				} else response.sendError(401,"Only an admin can delete accounts.");
			}
		} else response.sendError(400,"Please specify a account id.");
	}
	
	
	//check if a user owns the account specified
	private boolean ownsAccount(User user, int accId) {
		
		List<Account> accs = aServ.getAccountsByUser(user.getUserId());
		
		for (Account acc : accs) {
			if (acc.getId() == accId) {
				return true;
			}
		}
		return false;
	}
}
