package com.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * jdbc工具类
 * @author APPle
 *
 */
public class JdbcUtil {
	private static String url = null;
	private static String user = null;
	private static String password = null;
	private static String driverClass = null;
	
	
	//创建连接池对象
		private static DataSource ds = new ComboPooledDataSource(); 
		
		/**
		 * 获取连接池对象
		 */
		public static DataSource getDataSource(){
			
			return ds;
		}
	
	
	static{
		try {
			//读取jdbc.properties文件
			Properties prop = new Properties();
			//使用类路径方式读取配置文件
			InputStream in = JdbcUtil.class.getResourceAsStream("/jdbc.properties"); 
			//加载文件
			prop.load(in);
			//读取配置文件的内容
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			driverClass = prop.getProperty("driverClass");
			
			//注册驱动程序
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取连接方法
	 */
	public static Connection getConnection(){
		try {
			Connection cn = DriverManager.getConnection(url, user, password);
			return cn;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 释放资源方法
	 */
	public static void close(ResultSet rs,Statement stm,Connection cn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if(stm!=null){
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if(cn!=null){
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 释放资源方法
	 */
	public static void close(Statement stm,Connection cn){
		if(stm!=null){
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		if(cn!=null){
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	
}
