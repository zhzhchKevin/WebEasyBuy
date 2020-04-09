package com.ithwua.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	public static void close(ResultSet rs,Statement stmt,Connection conn){
		//必须要判断，因为如果创建失败，rs,stmt,conn可能为null，当执行close()方法时会报空指针异常。
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
