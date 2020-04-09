package com.ithwua.ServiceImpl;

import java.util.List;

import com.ithwua.DaoImpl.ProductDaoImpl;
import com.ithwua.IDao.IProductDao;
import com.ithwua.IService.IProductService;
import com.ithwua.bean.Cart;
import com.ithwua.bean.Product;
import com.ithwua.util.Page_Util;

public class ProductServiceImpl implements IProductService {
    IProductDao productDao=new ProductDaoImpl();
	@Override
	public List<Product> queryAllProducts() {
		return productDao.queryAllProducts();
	}
	@Override
	public List<Product> queryProducts(int nowPage){
		return productDao.queryProducts(nowPage);
	}
	/**
	 * 根据数据的总条数以及pageSize求出总页数
	 * */
	@Override
	public int getPageCount(){
		int pageCount=0;
		int productCount=productDao.queryProductCount();//数据的总条数
		if(productCount%Page_Util.PAGE_SIZE==0){
			pageCount=productCount/Page_Util.PAGE_SIZE;
		}else{
			pageCount=productCount/Page_Util.PAGE_SIZE+1;
		}
		return pageCount;
	}
	@Override
	public Product queryProductById(String productId) {
		return productDao.queryProductById(productId);
	}
	
	@Override
	public List<Product> queryProductsByIds(String[] productIds) {
		String sql="select * from hwua_product where hp_id IN";
		for(int i=0;i<productIds.length;i++){
			if(i==0){
				sql+=" (?";
			}else{
				sql+=",?";
			}
		}
		sql+=")";
		System.out.println("sql语句为"+sql);
		
		return productDao.queryProductsByIds(sql,productIds);
	}
	
	
	
	@Override
	public List<Product> queryProductsByChildId(String categoryId) {
		String sql="select * from hwua_product where hpc_child_id=?";
		// TODO Auto-generated method stub
		return productDao.queryProductsByCateId(sql,categoryId);
	}

}
