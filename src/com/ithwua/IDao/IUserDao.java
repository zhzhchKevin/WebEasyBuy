package com.ithwua.IDao;

import java.util.List;

import com.ithwua.bean.User; 


public interface IUserDao {
   /**
    * 用户注册:将用户数据保存到数据库
    */
    int register(User user);
	
	/**
	 * 用户登录：根据用户名，密码判断用户是否存在
	*/
	User login(User user);
	
	/**
	 * 根据用户的姓名查询用户的ID
	 * 
	 */
    long queryUserId(String userName);

	long queryUserIdByEmail(String email);
}
