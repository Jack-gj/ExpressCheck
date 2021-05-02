package com.gj.dao;

import java.sql.Connection;

import java.sql.SQLException;


public class BaseDao {
	
	static  private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static  private String URL = "jdbc:mysql://localhost:3306/extrace?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	//MySQL数据库账号和密码
	static private String USER = "root";
    static private String PASSWORD = "12345";
   	
   	static{
        try {
            //加载JDBC驱动
        	Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
    }
   	/*
   	 * param: void
   	 * return: conn Connection对象
   	 */
    public static Connection getConn(){
    	Connection conn = null;
    	try {
    		conn = java.sql.DriverManager.getConnection(URL,USER,PASSWORD);
    	}catch(SQLException e) {
    		System.out.println("数据库驱动加载失败");
    		e.printStackTrace();
    	}
        return conn;
    }
    
    public static void freeConn(Connection conn) {
    	try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭失败");
            e.printStackTrace();
        }      
        
    }   
}