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
 * jdbc������
 * @author APPle
 *
 */
public class JdbcUtil {
	private static String url = null;
	private static String user = null;
	private static String password = null;
	private static String driverClass = null;
	
	
	//�������ӳض���
		private static DataSource ds = new ComboPooledDataSource(); 
		
		/**
		 * ��ȡ���ӳض���
		 */
		public static DataSource getDataSource(){
			
			return ds;
		}
	
	
	static{
		try {
			//��ȡjdbc.properties�ļ�
			Properties prop = new Properties();
			//ʹ����·����ʽ��ȡ�����ļ�
			InputStream in = JdbcUtil.class.getResourceAsStream("/jdbc.properties"); 
			//�����ļ�
			prop.load(in);
			//��ȡ�����ļ�������
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			driverClass = prop.getProperty("driverClass");
			
			//ע����������
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ��ȡ���ӷ���
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
	 * �ͷ���Դ����
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
	 * �ͷ���Դ����
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
