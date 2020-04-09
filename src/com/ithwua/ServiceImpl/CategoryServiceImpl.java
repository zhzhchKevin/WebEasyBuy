package com.ithwua.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import com.ithwua.DaoImpl.CategoryDaoImpl;
import com.ithwua.IDao.ICategoryDao;
import com.ithwua.IService.ICategoryService;
import com.ithwua.bean.Category;

public class CategoryServiceImpl implements ICategoryService {
    ICategoryDao categoryDao=new CategoryDaoImpl();
    
    //查询所有大类型对象
	@Override
	public List<Category> queryALLParentCategorys() {
		List<Long> listId=categoryDao.queryALLParentCategorys();
		List<Category> categorys=new ArrayList<Category>();
		for(Long id:listId){
			categorys.add(categoryDao.queryCategorysById(id));
		}
		return categorys;
	}
   //搜索所有的
	@Override
	public List<Category> queryALLCategorys() {
		return categoryDao.queryALLCategorys();
	}
	
	
	
}
