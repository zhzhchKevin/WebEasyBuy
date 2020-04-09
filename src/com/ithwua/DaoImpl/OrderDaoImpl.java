package com.ithwua.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ithwua.IDao.IOrderDao;
import com.ithwua.bean.User;
import com.ithwua.util.JDBCTemplate;
import com.ithwua.util.PreparedStatementSetter;
import com.ithwua.util.ResultSetHandler;

public class OrderDaoImpl extends JDBCTemplate implements IOrderDao {

	@Override
	public Long queryOrderSeq(Connection conn,final User userBack) {
		String sql="select ho_id from (select * from hwua_order where ho_user_name=? order by ho_id) where rownum=1";
		final Long[] seq=new Long[1];
		this.queryByConn(conn,sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				System.out.println("获得userback的名称"+userBack.getName());
				pstmt.setString(1, userBack.getName());  
			}
		}, new ResultSetHandler() {
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
				if(rs.next()){
					System.out.println("获得userback的名称"+rs.getLong(1));
					seq[0]=rs.getLong(1);
				}
			}
		});
		return seq[0];
	}

	@Override
	public int createOrder(Connection conn,final User userBack) {
		String sql="insert into hwua_order values(seq_order.nextval,?,?,?,sysdate,0,0,0)";
		return this.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, userBack.getId());
				pstmt.setString(2, userBack.getName());
				pstmt.setString(3, userBack.getAddress());
			}
		});
	}

	@Override
	public int updateOrder(Connection conn,final Long orderSeq, final double sum) {
		String sql="update hwua_order set ho_cost=? where ho_id=?";
		return this.updateByConn(conn, sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setDouble(1, sum);
				pstmt.setLong(2, orderSeq);
			}
		});
	}

}
