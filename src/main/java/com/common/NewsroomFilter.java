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
import com.domain.MemberVO.MemberGrade;

@WebFilter("/board/newsroom/writeForm")
public class NewsroomFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
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
		else if(auth.getGrade().toString().equals("") || auth.getGrade().toString().equals("준회원") ||
				auth.getGrade().toString().equals("정회원") || auth.getGrade().toString().equals("우수회원")) {
			reps.sendRedirect(req.getContextPath()+"/member/notAccessble");
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
