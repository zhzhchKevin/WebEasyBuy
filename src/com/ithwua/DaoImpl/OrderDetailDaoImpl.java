package com.ithwua.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ithwua.IDao.IOrderDetialDao;
import com.ithwua.bean.Cart;
import com.ithwua.util.JDBCTemplate;
import com.ithwua.util.PreparedStatementSetter;

public class OrderDetailDaoImpl extends JDBCTemplate implements IOrderDetialDao {

	@Override
	public int createOrderDetail(Connection conn,final Cart cart, final double cost, final Long orderSeq) {
		String sql="insert into hwua_order_detail values(seq_detail.nextval,?,?,?,?)";
		return this.updateByConn(conn,sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1,orderSeq);
				pstmt.setLong(2,cart.getProductId());
				pstmt.setLong(3,cart.getQuantity());
				pstmt.setDouble(4,cost);
			}
		});
	}

}
