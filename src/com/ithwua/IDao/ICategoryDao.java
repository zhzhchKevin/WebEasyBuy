package com.ithwua.IDao;

import java.util.List;

import com.ithwua.bean.Category;

public interface ICategoryDao {
	Category queryCategorysById(Long id);

	List<Long> queryALLParentCategorys();

	List<Category> queryALLCategorys();

}
