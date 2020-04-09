package com.ithwua.IService;

import java.util.List;

import com.ithwua.bean.Cart;
import com.ithwua.bean.Product;

public interface ICartService {


	public List<Cart> queryCartsByName(String userName);

	public boolean updateCarts(String cartId, String quantity);

	public boolean deleteCartById(String cartId);

	public boolean addCart(String productId, String userName);

}
