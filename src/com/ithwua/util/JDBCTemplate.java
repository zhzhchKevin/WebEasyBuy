package com.ithwua.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//ģ����,���ã���װͨ�õ�����
public class JDBCTemplate {
	//ͨ�õ�DML(��ɾ��)����
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
	
	//ͨ�õĲ�ѯ����
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

	
	
	//��conn�Ĳ�ѯ����
	
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
    
  //��conn���޸ķ���
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
