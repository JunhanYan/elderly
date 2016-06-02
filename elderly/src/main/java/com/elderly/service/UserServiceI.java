package com.elderly.service;

import java.util.List;


import com.elderly.model.User;

public interface UserServiceI {

	public User getUserById(int userId);
	public List<User> getAllUsers();
	public int addUser(User user);
	public int deleteUser(int userId);
	public int modifyUser(User user);
	public User getUserAllInfo(int userId);//尽量少用，表关联太多
	public List<User> getEmergencyUsers();
	//public User login(String userAccount, String password);
	//public User verifyAccountAvaliable(String userAccount);
}
