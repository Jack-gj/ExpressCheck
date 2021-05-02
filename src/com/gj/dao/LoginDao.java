package com.gj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {
	private Connection conn = null;
	private Statement stmt = null;
	
	public  boolean loginCheck(String uid, String pwd) {
		String sql = "select * from UserInfo where UID = "+uid+" and PWD = '"+pwd+"'";
		try {
			conn = BaseDao.getConn();
			stmt = conn.createStatement();
			if(stmt.executeQuery(sql).next()) {
				return true;
			}
			BaseDao.freeConn(conn);
			stmt.close();
		}catch(SQLException e) {
			
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
