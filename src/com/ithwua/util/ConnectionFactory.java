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
		//��ȡ�ļ���������Ҫ��
		Properties prop=new Properties();
		try {
			
			//��load()���ļ��е� ���ݼ��ص��ڴ��е�prop������
			//���ַ�ʽ�����÷��䣬������Ҫ�Լ�����
			//ͨ��getResourceAsStream����
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
	
    //��ȡ���ݿ�����
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
