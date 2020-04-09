package com.ithwua.DaoImpl;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ithwua.IDao.IUserDao;
import com.ithwua.bean.User;
import com.ithwua.util.ConnectionFactory;
import com.ithwua.util.DBUtils;
import com.ithwua.util.JDBCTemplate;
import com.ithwua.util.PreparedStatementSetter;
import com.ithwua.util.ResultSetHandler;

public class UserDaoImpl extends JDBCTemplate implements IUserDao{
    private Connection conn;
    private ResultSet rs;
    PreparedStatement pst;
	//注册添加用户
	@Override
	public int register(final User user) {
		
		String sql="insert into hwua_user values(seq_user.nextval,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,1)";
			return update(sql,new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setString(1, user.getName());
					pstmt.setString(2, user.getPassword());
					pstmt.setString(3, user.getSex());
					pstmt.setString(4,user.getBirthday());
					pstmt.setString(5, user.getIdentityCode());
					pstmt.setString(6, user.getEmail());
					pstmt.setString(7, user.getMobile());
					pstmt.setString(8, user.getAddress());	
				}
			});
	}

	
	//用户登录   (返回User对象)
	@Override
	public User login(final User user) {
		final User[] userThis=new User[1];
		String queryPass=null;
		conn=ConnectionFactory.getConnection();
		String sql="Select * from hwua_user where hu_user_name=?";
		this.query(sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1,user.getName());
			}
		}, new ResultSetHandler() {
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
			     if(rs.next()){
                     User user=new User();
                     user.setId(rs.getLong(1));
                     user.setName(rs.getString(2));
                     user.setPassword(rs.getString(3));
                     user.setSex(rs.getString(4));
                     user.setBirthday(rs.getString(5));
                     user.setIdentityCode(rs.getString(6));
                     user.setEmail(rs.getString(7));
                     user.setMobile(rs.getString(8));
                     user.setAddress(rs.getString(9));
                     user.setStatus(rs.getInt(10));
                     userThis[0]=user;
                     System.out.println(user.toString());
				}
			}
		});
		return userThis[0];
	}

    //
	@Override
	public long queryUserId(final String userName) {
		final long[] userId=new long [1];
		String sql3="select hu_user_id from hwua_user where hu_user_name=?";
		this.query(sql3,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1,userName);
			}
		}, new ResultSetHandler() {
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
			     if(rs.next()){
                     userId[0]=rs.getLong(1);
				}else{
					//无该用户名
					userId[0]=-1;
				}
			}
		});
		return userId[0];
	}


	@Override
	public long queryUserIdByEmail(final String email) {
		final long[] userId=new long [1];
		String sql3="select hu_user_id from hwua_user where hu_email=?";
		this.query(sql3,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1,email);
			}
		}, new ResultSetHandler() {
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
			     if(rs.next()){
                     userId[0]=rs.getLong(1);
				}else{
					//无该用户名
					userId[0]=-1;
				}
			}
		});
		return userId[0];
	}
	
	//根据用户的名称查询用户的ID
	

}
