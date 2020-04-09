package com.ithwua.IService;

import java.util.List;

import com.ithwua.bean.Cart;
import com.ithwua.bean.Product;

public interface IProductService {
    public List<Product> queryAllProducts();
    
    public List<Product> queryProducts(int nowPage);
    
    public int getPageCount();

	public Product queryProductById(String productId);

	public List<Product> queryProductsByIds(String[] productIds);
	
	//���ݴ�����ID������Ӧ����Ʒ
	
	
	
	
	
	//����С����ID������Ӧ����Ʒ
	
	public List<Product> queryProductsByChildId(String categoryId);

    
}
