package com.gj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gj.dao.LoginDao;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   
    public LoginServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("json");
		
		PrintWriter out = response.getWriter();
		JSONObject jo = null;
		
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		
		LoginDao ld = new LoginDao();
		HashMap<String, String> hm = new HashMap<String, String>();
		
		if(ld.loginCheck(uid, pwd)) {
			try {
				//设置session
				HttpSession session = request.getSession();
				session.setAttribute("uid",uid);
				
				hm.put("code", "1");
				hm.put("msg","登录成功！");
				hm.put("value", uid);
				
				jo = JSONObject.parseObject(JSON.toJSONString(hm));
			}catch(Exception e) {
				e.printStackTrace();
			}	
		}else {
			
			try {
				hm.put("code", "0");
				hm.put("msg","用户名或密码错误！");
				hm.put("value", "");
				jo = JSONObject.parseObject(JSON.toJSONString(hm));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		out.println(jo);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		
	}

}
