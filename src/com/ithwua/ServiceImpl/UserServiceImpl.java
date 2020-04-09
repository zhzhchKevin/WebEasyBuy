package com.ithwua.ServiceImpl;

import java.util.List; 


import com.ithwua.DaoImpl.UserDaoImpl;
import com.ithwua.IDao.IUserDao;
import com.ithwua.IService.IUserService;
import com.ithwua.bean.User;

public class UserServiceImpl implements IUserService {
    IUserDao userDao=new UserDaoImpl();
    
	@Override
	public boolean register(User user) {
		if(userDao.register(user)==1){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public User doLogin(User user) {
		User userBack=userDao.login(user);
		
		if(userBack!=null&&userBack.getPassword().equals(user.getPassword())){
			//无该用户
			return userBack;
		}else {
			return null;
		}
			
	}

	@Override
	public boolean verifyName(String userName) {
		if(userDao.queryUserId(userName)==-1){
			//名字不存在
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public boolean verifyEmail(String email) {
		if(userDao.queryUserIdByEmail(email)==-1){
			//名字不存在
			return true;
		}else{
			return false;
		}
	}
}
