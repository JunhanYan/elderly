package com.elderly.dao;


import com.elderly.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uId);


    int insertSelective(User user);

    User selectByPrimaryKey(int uId);

    int updateByPrimaryKeySelective(User record);

    
  //  User login(@Param("userAccount")String userAccount, @Param("password")String password);
   
  //  User verifyAccountAvaliable(String userAccount);
    
   // User getUserAndTeams(int userId);
   // User getUserAndRoles(int userId);
    List<User> getAllUsers();
    
    List<User> getEmergencyUsers();
}