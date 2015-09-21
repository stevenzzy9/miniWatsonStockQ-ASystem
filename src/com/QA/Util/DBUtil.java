package com.QA.Util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class DBUtil {
	private static Connection conn;
	
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	
	static {
		Properties pro = new Properties();
		try {
//			pro.load(DBUtil.class.getResourceAsStream("DBConfig.properties"));
//			driver = pro.getProperty("driver");
//			url = pro.getProperty("url");
//			username = pro.getProperty("username");
//			password = pro.getProperty("password");
			
			driver="com.mysql.jdbc.Driver";
			url="jdbc:mysql://localhost:3306/solidqa?useUnicode=true&characterEncoding=utf-8";
			username="root";
			password="li";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 获得当前连接的预准备的Statement
	 * 其中SQL不带参数[问号]
	 * @param conn
	 * @param sql
	 * @return PreparedStatement pstmt
	 */
	public static PreparedStatement getPreparedStatemnt(Connection conn, String sql) {
		PreparedStatement pstmt=null;
		try {
			pstmt = conn.prepareStatement(sql);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}


	/**
	 * 获得当前连接的预处理的Statement
	 * 其中 SQL 带条件的查询 ，问号的参数按顺序一次存�?数组 params
	 * @param conn
	 * @param sql
	 * @param params
	 * @return PreparedStatement pstmt
	 */
	public static PreparedStatement getPreparedStatemnt(Connection conn , String sql,ArrayList<Object>params) {
		PreparedStatement pstmt=null;
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++){
				pstmt.setObject(i + 1, params.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	/**
	 * 重载方法 关闭 连接
	 * @param conn
	 */
	public static void CloseResources(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())				
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 重载方法 关闭 称述对象
	 * @param stmt
	 */
	public static void CloseResources(Statement stmt) {
		try {
			if (stmt != null)				
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void CloseResources(ResultSet rs) {
		try {
			if (rs != null)				
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void CloseResources(ResultSet rs , Statement stmt) {
		CloseResources(rs);
		CloseResources(stmt);
	}

	
	public static void CloseResources(Connection conn, Statement stmt) {
		CloseResources(stmt);
		CloseResources(conn);
	}
	
	
	public static void CloseResources(Connection conn, ResultSet rs) {
		CloseResources(rs);
		CloseResources(conn);
	}


	public static void CloseResources(Connection conn, Statement stmt,
			ResultSet rs) {
		CloseResources(rs);
		CloseResources(conn ,stmt);
	}
	
	/**
	 * 执行数据库插入操作
	 * 
	 * @param sql
	 * @return int=1说明操作正确
	 * @throws ClassNotFoundException
	 */
	public static int update(String sql,ArrayList<Object> params) throws ClassNotFoundException {
		
		PreparedStatement ps = DBUtil.getPreparedStatemnt(conn, sql,params);
		int i = 0;
		try {
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;
	}

	public static ResultSet query(String sql,ArrayList<Object>params) throws ClassNotFoundException {
		try {
			PreparedStatement ps = DBUtil.getPreparedStatemnt(conn, sql,params);
			ResultSet result = ps.executeQuery();
			return result;
			
		} catch (SQLException e) {
			System.out.println("#########查询错误#########");
			return null;
		}
	}
}
