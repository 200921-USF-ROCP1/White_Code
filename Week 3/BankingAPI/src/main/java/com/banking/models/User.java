package com.banking.models;

public class User {
	//defines user attributes and methods
	
	private int userId; // primary key
	private String username; // not null, unique
	private String password; // not null
	private String firstName; // not null
	private String lastName; // not null
  	private String email; // not null
  	private Role role = new Role();
  	public int roleId;
  	
  	// Constructor
  	public User() {
  		
  		setUserId(-1);
  		setUsername("unknown");
  		setPassword("unknown");
  		setFirstName("unknown");
  		setLastName("unknown");
  		setEmail("unknown");
  		setRole(4); //standard user

  	}
  	public User(int userId, String username, String password, 
  				String firstName, String lastName, 
  				String email, int roleId) {
  		
  		setUserId(userId);
  		setUsername(username);
  		setPassword(password);
  		setFirstName(firstName);
  		setLastName(lastName);
  		setEmail(email);
  		setRole(roleId);
  		
  	}
  	
  	//get data
  	public int getUserId() {
  		return userId;
  	}
  	public String getUsername() {
  		return username; 		
  	}
  	public String getPassword() {
  		return password;		
  	}
  	public String getFirstName() {
  		return firstName;	
  	}
  	public String getLastName() {
  		return lastName;	
  	}
  	public String getEmail() {
  		return email;	
  	}
  	public Role getRole() {
  		return role;
  	}
  	
  	// set data
  	public void setUserId(int userId) {
  		//retrieve next userId from database
  		this.userId = userId;
  	}
  	public void setUsername(String username) {
  		this.username = username; 		
  	}
  	public void setPassword(String password) {
  		this.password = password;		
  	}
  	public void setFirstName(String firstName) {
  		this.firstName = firstName;	
  	}
  	public void setLastName(String lastName) {
  		this.lastName = lastName;	
  	}
  	public void setEmail(String email) {
  		this.email = email;	
  	}
  	public void setRole(int roleId) {
  		role.setRole(roleId);		
  	}
}
