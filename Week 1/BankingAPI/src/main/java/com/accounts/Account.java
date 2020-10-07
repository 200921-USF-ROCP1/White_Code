package com.accounts;

public class Account {
	//defines basic account types
	
	private int accountId; // primary key
	private double balance;  // not null
	private AccountStatus status = new AccountStatus();
	private AccountType type = new AccountType();
	
	//Constructors
	public Account() {
		
		setAccountId(-1);
		setType(1); //Checking
		setStatus(1); //Pending
		
	}
	public Account(int accountId, double balance) {
		setAccountId(accountId);
		setBalance(balance);
		setType(1); //Checking
		setStatus(1); //Pending
	}
	public Account(int accountId, double balance, int typeId) {
		setAccountId(accountId);
		setBalance(balance);
		setType(typeId);
		setStatus(1); //Pending
	}
	public Account(int accountId, double balance, int typeId, int statusId) {
		setAccountId(accountId);
		setBalance(balance);
		setType(typeId);
		setStatus(statusId); 
	}
	
	
	//Set Data
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setStatus(int statusId) {
		status.setAccountStatus(statusId);
	}
	private void setType(int typeId) {
		type.setAccountType(typeId);
	}
	
	//get data
	public double getBalance() {
		return balance;
	}
	public String getStatus() {
		return status.getAccountStatus();
	}
	public String getType() {
		return type.getAccountType();
	}
	public int getId() {
		return accountId;
	}
	public int getTypeId() {
		switch (type.getAccountType()) {
		case "Checking":
			return 1;
		case "Savings":
			return 2;
		default:
			return 1;
		}
	}
	public int getStatusId() {
		switch (status.getAccountStatus()) {
		case "Pending":
			return 1;
		case "Open":
			return 2;
		case "Closed":
			return 2;
		case "Denied":
			return 2;
		default:
			return 2;
		}
	}
}
