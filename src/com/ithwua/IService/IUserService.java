package com.ithwua.IService;

import com.ithwua.bean.User;

public interface IUserService {

	User doLogin(User user);

	boolean register(User user);

	boolean verifyName(String userName);

	boolean verifyEmail(String email);

}
