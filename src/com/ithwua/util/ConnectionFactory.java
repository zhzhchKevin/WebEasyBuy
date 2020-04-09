package com.ithwua.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
	private static String DRIVER;
	private static String URL;
	private static String UNAME;
	private static String UPASS;
	
	static {
		//读取文件的连接四要素
		Properties prop=new Properties();
		try {
			
			//用load()把文件中的 数据加载到内存中的prop对象中
			//这种方式，利用反射，流不需要自己创建
			//通过getResourceAsStream返回
			prop.load(ConnectionFactory.class.getResourceAsStream("jdbcinfo.properties"));
			//prop.load(new FileInputStream("src/work13/jdbcinfo.properties"));
			
			DRIVER=prop.getProperty("driver");
			URL=prop.getProperty("url");
			UNAME=prop.getProperty("uname");
			UPASS=prop.getProperty("upass");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    //获取数据库连接
	public static Connection getConnection(){
		Connection conn =null;
		try {
			Class.forName(DRIVER);
		    conn=DriverManager.getConnection(URL,UNAME,UPASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String [] args){
		System.out.println(ConnectionFactory.getConnection());
	}
}
