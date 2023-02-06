package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		String contextPath = request.getContextPath();
		final String PREFIX = "/WEB-INF/board/";
		final String SUFFIX = ".jsp";
		
		RequestDispatcher rd = null;
		String nextPage = null;
		
		if(pathInfo == null || pathInfo.equals("/") || pathInfo.equals("/main")) {
			request.getRequestDispatcher("/index").forward(request, response);
			return;
		}
		
		else if (pathInfo.equals("/freeboard")) {
			nextPage = "freeboard";
		}
		
		else if (pathInfo.equals("/areaboard")) {
			nextPage = "areaboard";
		}
		
		else if (pathInfo.equals("/userreview")) {
			nextPage = "userreview";
		}
		
		else if (pathInfo.equals("/newsroom")) {
			nextPage = "newsroom";
		}
		
		else if (pathInfo.equals("/shareinfo")) {
			nextPage = "newsroom";
		}
		
		else if (pathInfo.equals("/qna")) {
			nextPage = "qna";
		}
		
		
		else {
			request.getRequestDispatcher("/WEB-INF/_404.jsp").forward(request, response);
			return;
		}
		
		
		rd = request.getRequestDispatcher(PREFIX+nextPage+SUFFIX);
		rd.forward(request, response);
	}

}
