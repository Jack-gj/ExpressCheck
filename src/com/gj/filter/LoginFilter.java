package com.gj.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="LoginFilter", urlPatterns="/*")
public class LoginFilter implements Filter {

	String passUrl = "";


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest=(HttpServletRequest)request;
        HttpServletResponse httpResponse=(HttpServletResponse)response;
        HttpSession session=httpRequest.getSession();
        
        String[] strArray = passUrl.split(";"); //�±�ѭ�����������μ����ʵ�url���Ƿ����passurl�еĹؼ��֣���������򲻽�������
      
        for (String str : strArray) {
            if (str.equals(""))//������ϱ����������õ�urlΪ�գ���˵������Ҫ�������أ�������ѭ��
                continue;
            if (httpRequest.getRequestURL().indexOf(str) >= 0) { //���������õĵ�ַ
                chain.doFilter(httpRequest, httpResponse);
                return;
            }
        }
        
        //������ajax����
        if (httpRequest.getHeader("X-Requested-With") != null  
        	    && "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString())) { 
        	 chain.doFilter(httpRequest, httpResponse);
        	 return;
        } 
        //���session����������
        if(session.getAttribute("uid")!=null){
            chain.doFilter(httpRequest, httpResponse);
        }
        else{
            httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.html");
        }
	}

	public void init(FilterConfig fConfig) throws ServletException {
		passUrl = fConfig.getInitParameter("passUrl");
	}

	@Override
	public void destroy() {
		
		
	}

}
