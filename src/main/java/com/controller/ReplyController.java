package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ReplyDao;
import com.domain.ReplyVO;
import com.google.gson.Gson;
import com.service.ReplyServices;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReplyServices service;
	private Gson gson;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		service = new ReplyServices(new ReplyDao());
		gson = new Gson();
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String pathInfo = request.getPathInfo();
		String contextPath = request.getContextPath();
		
		
		if(pathInfo.equals("/freeReplyList")) {
			String paramBno = request.getParameter("bno");
			int bno = Integer.parseInt(paramBno);
			List<ReplyVO> replyList = service.freeReplyList(bno);
			
			out.print(gson.toJson(replyList));
			
		}
		
		else if(pathInfo.equals("/freeReplyWrite")) {
			String paramBno = request.getParameter("bno");
			
			long currenTime = System.currentTimeMillis(); 
			HttpSession session = request.getSession(false);
			if(session.getAttribute("lastWriting")!=null) { // 마지막에 글을 쓸 시간이 있다면
				long lastWriting = (long) session.getAttribute("lastWriting");
				if(currenTime-lastWriting<10000) {
					out.print(gson.toJson("댓글 등록 10초 후 등록 가능"));
					return;
				}
			}
			// 마지막에 글을 쓸 시간이 없다면
			session.setAttribute("lastWriting", currenTime);
		
			ReplyVO vo = ReplyVO.builder()
					.bno(Integer.parseInt(paramBno))
					.reply(request.getParameter("reply"))
					.writer(request.getParameter("writer"))
					.writernick(request.getParameter("writernick"))
					.build();
			
			service.freeReplyWrite(vo);
			String result = gson.toJson("댓글 등록 성공");
			out.print(result);
			
		}
		
		// 댓글 삭제
		else if(pathInfo.equals("/freeReplyRemove")) {
			String paramRno = request.getParameter("rno");
			String paramBno = request.getParameter("bno");
			
			ReplyVO vo = ReplyVO.builder()
					.rno(Integer.parseInt(paramRno))
					.bno(Integer.parseInt(paramBno))
					.build();
			
			service.freeReplyRemove(vo);
			String result = gson.toJson("댓글 삭제 완료");
			out.print(result);
		}
		
		// 지역게시판
		else if(pathInfo.equals("/areaReplyList")) {
			String paramBno = request.getParameter("bno");
			int bno = Integer.parseInt(paramBno);
			List<ReplyVO> replyList = service.areaReplyList(bno);
			out.print(gson.toJson(replyList));
		}
		
		else if(pathInfo.equals("/areaReplyWrite")) {
			String paramBno = request.getParameter("bno");
			
			long currenTime = System.currentTimeMillis(); 
			HttpSession session = request.getSession(false);
			if(session.getAttribute("lastWriting")!=null) { // 마지막에 글을 쓸 시간이 있다면
				long lastWriting = (long) session.getAttribute("lastWriting");
				if(currenTime-lastWriting<10000) {
					out.print(gson.toJson("댓글 등록 10초 후 등록 가능"));
					return;
				}
			}
			// 마지막에 글을 쓸 시간이 없다면
			session.setAttribute("lastWriting", currenTime);
		
			ReplyVO vo = ReplyVO.builder()
					.bno(Integer.parseInt(paramBno))
					.reply(request.getParameter("reply"))
					.writer(request.getParameter("writer"))
					.writernick(request.getParameter("writernick"))
					.build();
			
			service.areaReplyWrite(vo);
			String result = gson.toJson("댓글 등록 성공");
			out.print(result);
			
		}
		
		// 댓글 삭제
		else if(pathInfo.equals("/areaReplyRemove")) {
			String paramRno = request.getParameter("rno");
			String paramBno = request.getParameter("bno");
			
			ReplyVO vo = ReplyVO.builder()
					.rno(Integer.parseInt(paramRno))
					.bno(Integer.parseInt(paramBno))
					.build();
			
			service.areaReplyRemove(vo);
			String result = gson.toJson("댓글 삭제 완료");
			out.print(result);
		}
		
		
		// 회원리뷰 게시판
		else if(pathInfo.equals("/reviewReplyList")) {
			String paramBno = request.getParameter("bno");
			int bno = Integer.parseInt(paramBno);
			List<ReplyVO> replyList = service.reviewReplyList(bno);
			out.print(gson.toJson(replyList));
		}
		
		else if(pathInfo.equals("/reviewReplyWrite")) {
			String paramBno = request.getParameter("bno");
			
			long currenTime = System.currentTimeMillis(); 
			HttpSession session = request.getSession(false);
			if(session.getAttribute("lastWriting")!=null) { // 마지막에 글을 쓸 시간이 있다면
				long lastWriting = (long) session.getAttribute("lastWriting");
				if(currenTime-lastWriting<10000) {
					out.print(gson.toJson("댓글 등록 10초 후 등록 가능"));
					return;
				}
			}
			// 마지막에 글을 쓴 시간이 없다면
			session.setAttribute("lastWriting", currenTime);
		
			ReplyVO vo = ReplyVO.builder()
					.bno(Integer.parseInt(paramBno))
					.reply(request.getParameter("reply"))
					.writer(request.getParameter("writer"))
					.writernick(request.getParameter("writernick"))
					.build();
			
			service.reviewReplyWrite(vo);
			String result = gson.toJson("댓글 등록 성공");
			out.print(result);
			
		}
		
		// 댓글 삭제
		else if(pathInfo.equals("/reviewReplyRemove")) {
			String paramRno = request.getParameter("rno");
			String paramBno = request.getParameter("bno");
			
			ReplyVO vo = ReplyVO.builder()
					.rno(Integer.parseInt(paramRno))
					.bno(Integer.parseInt(paramBno))
					.build();
			
			service.reviewReplyRemove(vo);
			String result = gson.toJson("댓글 삭제 완료");
			out.print(result);
		}
		
		
		// 뉴스룸 게시판
		else if(pathInfo.equals("/newsReplyList")) {
			String paramBno = request.getParameter("bno");
			int bno = Integer.parseInt(paramBno);
			List<ReplyVO> replyList = service.newsReplyList(bno);
			out.print(gson.toJson(replyList));
		}
		
		else if(pathInfo.equals("/newsReplyWrite")) {
			String paramBno = request.getParameter("bno");
			
			long currenTime = System.currentTimeMillis(); 
			HttpSession session = request.getSession(false);
			if(session.getAttribute("lastWriting")!=null) { // 마지막에 글을 쓸 시간이 있다면
				long lastWriting = (long) session.getAttribute("lastWriting");
				if(currenTime-lastWriting<10000) {
					out.print(gson.toJson("댓글 등록 10초 후 등록 가능"));
					return;
				}
			}
			// 마지막에 글을 쓴 시간이 없다면
			session.setAttribute("lastWriting", currenTime);
		
			ReplyVO vo = ReplyVO.builder()
					.bno(Integer.parseInt(paramBno))
					.reply(request.getParameter("reply"))
					.writer(request.getParameter("writer"))
					.writernick(request.getParameter("writernick"))
					.build();
			
			service.newsReplyWrite(vo);
			String result = gson.toJson("댓글 등록 성공");
			out.print(result);
			
		}
		
		// 댓글 삭제
		else if(pathInfo.equals("/newsReplyRemove")) {
			String paramRno = request.getParameter("rno");
			String paramBno = request.getParameter("bno");
			
			ReplyVO vo = ReplyVO.builder()
					.rno(Integer.parseInt(paramRno))
					.bno(Integer.parseInt(paramBno))
					.build();
			
			service.newsReplyRemove(vo);
			String result = gson.toJson("댓글 삭제 완료");
			out.print(result);
		}
		
		// 정보공유 게시판
		else if(pathInfo.equals("/shareReplyList")) {
			String paramBno = request.getParameter("bno");
			int bno = Integer.parseInt(paramBno);
			List<ReplyVO> replyList = service.shareReplyList(bno);
			out.print(gson.toJson(replyList));
		}
		
		else if(pathInfo.equals("/shareReplyWrite")) {
			String paramBno = request.getParameter("bno");
			
			long currenTime = System.currentTimeMillis(); 
			HttpSession session = request.getSession(false);
			if(session.getAttribute("lastWriting")!=null) { // 마지막에 글을 쓸 시간이 있다면
				long lastWriting = (long) session.getAttribute("lastWriting");
				if(currenTime-lastWriting<10000) {
					out.print(gson.toJson("댓글 등록 10초 후 등록 가능"));
					return;
				}
			}
			// 마지막에 글을 쓴 시간이 없다면
			session.setAttribute("lastWriting", currenTime);
		
			ReplyVO vo = ReplyVO.builder()
					.bno(Integer.parseInt(paramBno))
					.reply(request.getParameter("reply"))
					.writer(request.getParameter("writer"))
					.writernick(request.getParameter("writernick"))
					.build();
			
			service.shareReplyWrite(vo);
			String result = gson.toJson("댓글 등록 성공");
			out.print(result);
			
		}
		
		// 댓글 삭제
		else if(pathInfo.equals("/shareReplyRemove")) {
			String paramRno = request.getParameter("rno");
			String paramBno = request.getParameter("bno");
			
			ReplyVO vo = ReplyVO.builder()
					.rno(Integer.parseInt(paramRno))
					.bno(Integer.parseInt(paramBno))
					.build();
			
			service.shareReplyRemove(vo);
			String result = gson.toJson("댓글 삭제 완료");
			out.print(result);
		}
		
		// 질문답변 게시판
		else if(pathInfo.equals("/qnaReplyList")) {
			String paramBno = request.getParameter("bno");
			int bno = Integer.parseInt(paramBno);
			List<ReplyVO> replyList = service.qnaReplyList(bno);
			out.print(gson.toJson(replyList));
		}
		
		else if(pathInfo.equals("/qnaReplyWrite")) {
			String paramBno = request.getParameter("bno");
			
			long currenTime = System.currentTimeMillis(); 
			HttpSession session = request.getSession(false);
			if(session.getAttribute("lastWriting")!=null) { // 마지막에 글을 쓸 시간이 있다면
				long lastWriting = (long) session.getAttribute("lastWriting");
				if(currenTime-lastWriting<10000) {
					out.print(gson.toJson("댓글 등록 10초 후 등록 가능"));
					return;
				}
			}
			// 마지막에 글을 쓴 시간이 없다면
			session.setAttribute("lastWriting", currenTime);
		
			ReplyVO vo = ReplyVO.builder()
					.bno(Integer.parseInt(paramBno))
					.reply(request.getParameter("reply"))
					.writer(request.getParameter("writer"))
					.writernick(request.getParameter("writernick"))
					.build();
			
			service.qnaReplyWrite(vo);
			String result = gson.toJson("댓글 등록 성공");
			out.print(result);
			
		}
		
		// 댓글 삭제
		else if(pathInfo.equals("/qnaReplyRemove")) {
			String paramRno = request.getParameter("rno");
			String paramBno = request.getParameter("bno");
			
			ReplyVO vo = ReplyVO.builder()
					.rno(Integer.parseInt(paramRno))
					.bno(Integer.parseInt(paramBno))
					.build();
			
			service.qnaReplyRemove(vo);
			String result = gson.toJson("댓글 삭제 완료");
			out.print(result);
		}
		
		
		
	}
}
