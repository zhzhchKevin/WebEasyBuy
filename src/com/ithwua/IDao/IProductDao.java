package com.ithwua.IDao;

import java.util.List;

import com.ithwua.bean.Product;

public interface IProductDao {
    
	public List<Product> queryAllProducts();

	/**
	 * 根据页面数，获取相应的数据条数
	 * @param nowPage
	 * @return
	 */
	public List<Product> queryProducts(int nowPage);
	
	public int queryProductCount();

	public Product queryProductById(String productId);

	public List<Product> queryProductsByIds(String sql, String[] productIds);

	
	/**
	 * 根据小类型或者大类型来搜索所有的上商品
	 * @param sql
	 * @param categoryId
	 * @return
	 */
	public List<Product> queryProductsByCateId(String sql, String categoryId);
}
