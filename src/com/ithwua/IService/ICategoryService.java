package com.ithwua.IService;

import java.util.List;

import com.ithwua.bean.Category;

public interface ICategoryService {
	//搜索所有的类别对象
	public List queryALLCategorys();
	
	
	//搜索所有的大类别的对象
	public List<Category> queryALLParentCategorys();
	
	
	
	//根据父级的类别ID搜索对应的子集，将自己排除在外
	
	
	
}
