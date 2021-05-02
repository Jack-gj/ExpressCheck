package com.gj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandleEva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER ="root";
	private static final String PSW = "12345";
	
    public HandleEva() {
        super();
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		Connection con = null;
		Statement stmt = null;
		String eva = "";
		
		if(request.getParameter("eva")!=null) {
			eva = request.getParameter("eva").trim();
		}
		if(eva != "") {
			String sql = "insert into ue (eva) values('"+eva+"')";
			String uri = "jdbc:mysql://localhost:3306/stu?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
			try {
				con = DriverManager.getConnection(uri,USER,PSW);
				
				stmt = con.createStatement();
				if(stmt.executeUpdate(sql)>0) {
					out.println("提交成功，感谢你的反馈！");
				}else {
					out.println("提交失败！");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					con.close();
					stmt.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
