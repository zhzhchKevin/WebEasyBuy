package com.ithwua.IDao;

import java.sql.Connection;

import com.ithwua.bean.Cart;

public interface IOrderDetialDao {

	int createOrderDetail(Connection conn, Cart cart, double cost, Long orderSeq);

}
