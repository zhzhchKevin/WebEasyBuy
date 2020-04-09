package com.ithwua.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//模版类,作用：封装通用的六大步
public class JDBCTemplate {
	//通用的DML(增删改)操作
	public int update(String sql,PreparedStatementSetter setter){
		Connection conn=null;
		PreparedStatement pstmt=null;
		int rows=0;
		//1,2
		conn=ConnectionFactory.getConnection();
		
		try {
			//3
			pstmt=conn.prepareStatement(sql);
			//4
			if(setter!=null){
			setter.setValues(pstmt);
			}
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    DBUtils.close(pstmt, conn);
		}
		return rows;
	}
	
	//通用的查询操作
	public void query(String sql,PreparedStatementSetter setter,ResultSetHandler handler){
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//1,2
		conn=ConnectionFactory.getConnection();
		try {
			//3
			pstmt=conn.prepareStatement(sql);
			//4
			if(setter!=null){
				setter.setValues(pstmt);
			}
			rs=pstmt.executeQuery();
			//5
			if(handler!=null){
				handler.handleRS(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, conn);
		}
	}
	
	public void query(String sql,ResultSetHandler handler){
	    query(sql,null,handler);
	};

	
	
	//传conn的查询方法
	
    public void queryByConn(Connection conn,String sql,PreparedStatementSetter setter,ResultSetHandler handler){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
    	try {
			//3
			pstmt=conn.prepareStatement(sql);
			//4
			if(setter!=null){
				setter.setValues(pstmt);
			}
			rs=pstmt.executeQuery();
			//5
			if(handler!=null){
				handler.handleRS(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt);
		}
    }
    
  //传conn的修改方法
    public int updateByConn(Connection conn,String sql,PreparedStatementSetter setter){
		PreparedStatement pstmt=null;
		int rows=0;
		//1,2
		conn=ConnectionFactory.getConnection();
		
		try {
			//3
			pstmt=conn.prepareStatement(sql);
			//4
			if(setter!=null){
			setter.setValues(pstmt);
			}
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    DBUtils.close(pstmt);
		}
		return rows;
    }

}
