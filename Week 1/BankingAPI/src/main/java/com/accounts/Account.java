package com.accounts;

public class Account {
	//defines basic account types
	
	private int accountId; // primary key
	private double balance;  // not null
	private AccountStatus status;
	private AccountType type;
	
	//Constructors
	public Account() {
		
		nextAccountId();
		setType(1); //Checking
		setStatus(1); //Pending
		
	}
	public Account(double balance) {
		nextAccountId();
		setBalance(balance);
		setType(1); //Checking
		setStatus(1); //Pending
	}
	public Account(double balance, int typeId) {
		nextAccountId();
		setBalance(balance);
		setType(typeId);
		setStatus(1); //Pending
	}
	public Account(double balance, int typeId, int statusId) {
		nextAccountId();
		setBalance(balance);
		setType(typeId);
		setStatus(statusId); 
	}
	
	
	//Set Data
	private void nextAccountId() {
		this.accountId = 1; //Retrieve next accountId from database
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
}
