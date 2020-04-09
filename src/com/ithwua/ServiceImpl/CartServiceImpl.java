package com.ithwua.ServiceImpl;

import java.util.List;

import com.ithwua.DaoImpl.CartDaoImpl;
import com.ithwua.IDao.ICartDao;
import com.ithwua.IService.ICartService;
import com.ithwua.bean.Cart;
import com.ithwua.bean.Product;

public class CartServiceImpl implements ICartService {
	ICartDao cartDao=new CartDaoImpl();

	@Override
	public List<Cart> queryCartsByName(String userName) {
		
		return cartDao.queryCartsByName(userName);
	}
 
	//修改商品购买数量
	@Override
	public boolean updateCarts(String cartId, String quantity) {
		if(cartDao.updateCarts(cartId,quantity)==1){
			return true;
		}else{
			return false;
		}

	}

	@Override
	public boolean deleteCartById(String cartId) {
		if(cartDao.deleteCartById(cartId)==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean addCart(String productId, String userName) {
		if(cartDao.addCart(productId,userName)==1){
			return true;
		}else{
			return false;
		}
	}

}
