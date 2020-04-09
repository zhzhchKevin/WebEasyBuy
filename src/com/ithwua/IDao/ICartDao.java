package com.ithwua.IDao;

import java.sql.Connection;
import java.util.List;

import com.ithwua.bean.Cart;

public interface ICartDao {
	public List<Cart> queryCartsByName(String userName);

	public int updateCarts(String cartId, String quantity);

	public int deleteCartById(String cartId);

	public int addCart(String productId, String userName);

	Cart queryCartsByCartId(Connection conn, String cartId);

	public int deleteCartById(Connection conn, String cartId);
}
