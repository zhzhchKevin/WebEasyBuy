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
	
	//根据大类型ID搜索相应的商品
	
	
	
	
	
	//根据小类型ID搜索相应的商品
	
	public List<Product> queryProductsByChildId(String categoryId);

    
}
