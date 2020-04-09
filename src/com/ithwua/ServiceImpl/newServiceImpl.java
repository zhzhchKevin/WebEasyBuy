package com.ithwua.ServiceImpl;

import java.util.List;

import com.ithwua.DaoImpl.NewDaoImpl;
import com.ithwua.IDao.INewDao;
import com.ithwua.IService.INewService;
import com.ithwua.bean.New;

public class newServiceImpl implements INewService {
    INewDao newDao=new NewDaoImpl();
	
	@Override
	public List<New> queryAllNews() {
	
		return newDao.queryAllNews();
	}

}
