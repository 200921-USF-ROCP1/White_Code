package com.banking.interfaces;

import com.banking.models.User;
import java.util.List;

public interface UserService {
	
	public User login(String username, String password);
	
	public User register(User user);
	
	public List<User> getAllUsers();
	
	public User getUserById(int userId);
	
	public User getUserByUsername(String username);
	
	public User updateUser(User user);
	
	
	
}
