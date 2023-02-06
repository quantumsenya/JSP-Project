package com.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = {
		"/member/join",
		"/member/login",
		"/member/quit",
		"/member/doQuit",
		"/member/doHigher",
		"/member/modNickname"
})
public class EncryptFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("암호화 필터 동작");
		
		EncryptWrapper ew = new EncryptWrapper((HttpServletRequest)request);
		
		if(request.getParameter("pwd")!=null) {
			request.setAttribute("pwd", ew.getParameter("pwd"));
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

}
