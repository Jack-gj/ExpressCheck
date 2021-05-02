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
        
        String[] strArray = passUrl.split(";"); //下边循环作用是依次检查访问的url中是否包含passurl中的关键字，如果包含则不进行拦截
      
        for (String str : strArray) {
            if (str.equals(""))//如果在上边配置中设置的url为空，则说明都需要进行拦截，跳过此循环
                continue;
            if (httpRequest.getRequestURL().indexOf(str) >= 0) { //不拦截设置的地址
                chain.doFilter(httpRequest, httpResponse);
                return;
            }
        }
        
        //不拦截ajax请求
        if (httpRequest.getHeader("X-Requested-With") != null  
        	    && "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString())) { 
        	 chain.doFilter(httpRequest, httpResponse);
        	 return;
        } 
        //如果session存在则不拦截
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
