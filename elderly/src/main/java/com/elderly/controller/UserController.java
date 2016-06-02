package com.elderly.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.elderly.service.UserServiceI;

import com.elderly.model.User;



@Controller
@RequestMapping("/userController")
public class UserController {
	
	private UserServiceI userService;
	//private FolderServiceI folderService;
	
	/*public FolderServiceI getFolderService() {
		return folderService;
	}
	@Autowired
	public void setFolderService(FolderServiceI folderService) {
		this.folderService = folderService;
	}

	public UserServiceI getUserService() {
		return userService;
	}*/

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public @ResponseBody User register(@RequestBody User user) {
		
		if(userService.addUser(user)>0)
		{
			
			return user;
		}else
			return null;
	}
	
	/*@RequestMapping("/verifyUserAccount/{userAccount}")
	public @ResponseBody Map<String, String> verifyUserAccount(@PathVariable String userAccount) {
		Map<String,String> map = new HashMap<String,String >();
		if(userService.verifyAccountAvaliable(userAccount)!=null)
			{
				map.put("result", "avaliable");
			}else{
				map.put("result", "unavaliable");
			}
		return map;
	}
	*/
	@RequestMapping("/getUserById/{userId}")
	public @ResponseBody User getUserById(@PathVariable int userId) {
		User user = userService.getUserById(userId);
		return user;
	}
	
	
	@RequestMapping("/getAllUsers")
	public @ResponseBody List<User> getAllUsers() {
		return userService.getAllUsers();		
	}
	@RequestMapping("/getEmergencyUsers")
	public @ResponseBody List<User> getEmergencyUsers() {
		return userService.getEmergencyUsers();		
	}
	
	
	@RequestMapping(value="/modifyUser",method=RequestMethod.PUT)
	public @ResponseBody User modifyUser(@RequestBody User user) {

		if(userService.modifyUser(user)>0)
			return user;
		else
			return null;
	}
	
	@RequestMapping(value="/deleteUser/{userId}",method=RequestMethod.DELETE)
	public @ResponseBody Map<String, String> deleteUser( int userId) {
		Map<String,String> map = new HashMap<String,String >();
		if(userService.deleteUser(userId)>0)
			{
				map.put("result", "success");
			}else{
				map.put("result", "failure");
			}
		return map;
	}
}