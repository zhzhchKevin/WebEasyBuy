package com.ithwua.IDao;

import java.sql.Connection;

import com.ithwua.bean.User;

public interface IOrderDao {

	Long queryOrderSeq(Connection conn, User userBack);

	int createOrder(Connection conn, User userBack);

	int updateOrder(Connection conn, Long orderSeq, double sum);

}
