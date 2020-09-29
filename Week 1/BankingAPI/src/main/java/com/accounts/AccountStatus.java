package com.accounts;

class AccountStatus {
	//used to track the status of accounts. 
	//Status possibilities are Pending, Open, Closed, or Denied
	
	private int statusId; // primary key
	private String status; // not null, unique
	
	// Constructors
	public AccountStatus() {
		setAccountStatus(1);
	}
	public AccountStatus(int statusId) {
		setAccountStatus(statusId);
		
	}
	
	// Set account status
	public void setAccountStatus(int statusId) {
		// set status based on statusId key
		this.statusId = statusId;
		
		switch (statusId) {
		case 1:
			this.status = "Pending";
			break;
		case 2:
			this.status = "Open";
			break;
			
		case 3:
			this.status = "Closed";
			break;
			
		case 4:
			this.status = "Denied";
			break;

		default:
			setAccountStatus(1);
			break;
		}
		
	}
	
	public String getAccountStatus() {
		return status;
	}
}

