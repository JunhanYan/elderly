package com.elderly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elderly.service.UserServiceI;
import com.elderly.dao.UserMapper;
//import com.elderly.model.Role;
//import com.elderly.model.Team;
import com.elderly.model.User;

@Service("userService")
public class UserServiceImpl implements UserServiceI {
	
	public UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}
	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public User getUserById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int addUser(User user) {
		
		return userMapper.insertSelective(user);
	}
	@Override
	public int deleteUser(int userId) {
		return userMapper.deleteByPrimaryKey(userId);
	}
	@Override
	public List<User> getAllUsers() {
		return userMapper.getAllUsers();
	}
	
	@Override
	public User getUserAllInfo(int userId) { //暂时不写关联太多，性能太差可能没有用
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public int modifyUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}
	
	@Override
	public List<User> getEmergencyUsers() {
		
		return userMapper.getEmergencyUsers();
	}
	/*@Override
	public User login(String userAccount, String password) {
		
		return userMapper.login(userAccount, password);
	}
	@Override
	public User verifyAccountAvaliable(String userAccount) { 
		return userMapper.verifyAccountAvaliable(userAccount);
			
	}*/
}
