package com.banking.models;

public class Account {
	//defines basic account types
	
	private int accountId; // primary key
	private double balance;  // not null
	private AccountStatus status = new AccountStatus();
	private AccountType type = new AccountType();
	public int statusId;
	public int typeId;
	
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
	public Account(int accountId, double balance, int statusId, int typeId) {
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
	public void setType(int typeId) {
		type.setAccountType(typeId);
	}
	
	//get data
	public double getBalance() {
		return balance;
	}
	public AccountStatus getStatus() {
		return status;
	}
	public AccountType getType() {
		return type;
	}
	public int getId() {
		return accountId;
	}
}
