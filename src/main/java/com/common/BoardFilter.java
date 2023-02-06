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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domain.AuthVO;

@WebFilter(urlPatterns = {
		"/member/getHigher",
		"/member/modNicknameForm",
		"/member/memberModForm",
		"/board/freeboard/writeForm",
		"/board/areaboard/writeForm",
		"/board/userreview/writeForm"
})
public class BoardFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse reps = (HttpServletResponse) response;
		
		
		HttpSession session = req.getSession(false);
		AuthVO auth = (AuthVO) session.getAttribute("auth");
		if(auth==null) {
			String userURI = req.getRequestURI();
			String queryString = req.getQueryString();
			userURI+="?"+queryString;
			session.setAttribute("userURI", userURI);
			reps.sendRedirect(req.getContextPath()+"/member/loginForm");
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
