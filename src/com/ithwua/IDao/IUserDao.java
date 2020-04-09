package com.ithwua.IDao;

import java.util.List;

import com.ithwua.bean.User; 


public interface IUserDao {
   /**
    * �û�ע��:���û����ݱ��浽���ݿ�
    */
    int register(User user);
	
	/**
	 * �û���¼�������û����������ж��û��Ƿ����
	*/
	User login(User user);
	
	/**
	 * �����û���������ѯ�û���ID
	 * 
	 */
    long queryUserId(String userName);

	long queryUserIdByEmail(String email);
}
