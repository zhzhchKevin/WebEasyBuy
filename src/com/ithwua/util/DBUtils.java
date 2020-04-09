package com.ithwua.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	public static void close(ResultSet rs,Statement stmt,Connection conn){
		//����Ҫ�жϣ���Ϊ�������ʧ�ܣ�rs,stmt,conn����Ϊnull����ִ��close()����ʱ�ᱨ��ָ���쳣��
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt,Connection conn){
		close(null,stmt,conn);
	}
	
	public static void close(Statement stmt){
		close(null,stmt,null);
	}
	
	public static void close(Connection conn){
		close(null,null,conn);
	}
	
	public static void close(ResultSet rs,Statement stmt){
		close(rs,stmt,null);
	}
	
	public static void close(ResultSet rs,Connection conn){
		close(rs,null,conn);
	}
}
