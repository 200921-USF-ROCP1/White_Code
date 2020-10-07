package com.banking.models;

class AccountType {
	//used to track what kind of account is being created. 
	//Type possibilities are Checking or Savings
	
	private int typeId; // primary key
	private String type; // not null, unique
	
	// Constructors
	public AccountType() {
		setAccountType(1);
	}
	public AccountType(int typeId) {
		setAccountType(typeId);
		
	}
	
	// Set account type
	public void setAccountType(int typeId) {
		// set type based on typeId key
		this.typeId = typeId;
		
		switch (typeId) {
		case 1:
			this.type = "Checking";
			break;
		case 2:
			this.type = "Savings";
			break;
		default:
			setAccountType(1);
			break;
		}
		
	}
	
	public String getAccountType() {
		return type;
	}
}
