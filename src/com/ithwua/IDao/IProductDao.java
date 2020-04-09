package com.ithwua.IDao;

import java.util.List;

import com.ithwua.bean.Product;

public interface IProductDao {
    
	public List<Product> queryAllProducts();

	/**
	 * ����ҳ��������ȡ��Ӧ����������
	 * @param nowPage
	 * @return
	 */
	public List<Product> queryProducts(int nowPage);
	
	public int queryProductCount();

	public Product queryProductById(String productId);

	public List<Product> queryProductsByIds(String sql, String[] productIds);

	
	/**
	 * ����С���ͻ��ߴ��������������е�����Ʒ
	 * @param sql
	 * @param categoryId
	 * @return
	 */
	public List<Product> queryProductsByCateId(String sql, String categoryId);
}
