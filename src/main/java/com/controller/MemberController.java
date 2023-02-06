package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
import com.domain.AuthVO;
import com.domain.MemberVO;
import com.domain.MemberVO.MemberGrade;
import com.service.MemberServices;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberServices service;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		service= new MemberServices(new MemberDao());
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		String contextPath = request.getContextPath();
		final String PREFIX = "/WEB-INF/member/";
		final String SUFFIX = ".jsp";
		
		RequestDispatcher rd = null;
		String nextPage = null;
		
		// 마이페이지 이동
		if(pathInfo == null || pathInfo.equals("/") || pathInfo.equals("/myPage")) {
			nextPage = "myPage";
		}
		
		// 회원가입 폼 이동
		else if (pathInfo.equals("/joinMember")) {
			nextPage = "memberJoinForm";
		}
		
		// 회원가입 처리
		else if (pathInfo.equals("/join")) {
			String pwd = (String) request.getAttribute("pwd");
			String pwd1 = request.getParameter("pwd");
			String pwd2 = request.getParameter("conf_pwd");
			System.out.println(pwd1);
			System.out.println(pwd2);
			if(pwd1.equals(pwd2)) {
				MemberVO vo = MemberVO.builder()
						.id(request.getParameter("id"))
						.pwd(pwd)
						.name(request.getParameter("name"))
						.nickname(request.getParameter("nickname"))
						.phone(request.getParameter("phone"))
						.email(request.getParameter("email"))
						.build();
				service.joinMember(vo);
				
				// 가입과 동시에 로그인
				MemberVO vo2 = MemberVO.builder().id(request.getParameter("id")).pwd(pwd).build();
				if (service.loginService(vo2)) {
					HttpSession session = request.getSession();
					MemberGrade grade = service.getGrade(vo.getId());
					String nickname = service.getNickname(vo.getId());
					AuthVO authVO = new AuthVO();
					authVO.setId(vo.getId());
					authVO.setGrade(grade);
					authVO.setNickname(nickname);
					session.setAttribute("auth", authVO);
					response.sendRedirect(contextPath+"/index");
					return;
				} 
			} else {
				PrintWriter out = response.getWriter();
				out.print("<script> alert('입력한 비밀번호가 일치하지 않습니다.');history.go(-1);</script>");
				out.flush();
				return;
			}
		}
		
		
		// 로그인페이지 이동
		else if (pathInfo.equals("/loginForm")) {
			nextPage = "memberLoginForm";
		}
		
		// 로그인 처리
		else if (pathInfo.equals("/login")) {
			String id = request.getParameter("id");
			String pwd = (String) request.getAttribute("pwd");
			MemberVO vo = MemberVO.builder()
					.id(id).pwd(pwd).build();
			
			if(service.loginService(vo)) {
				HttpSession session = request.getSession();
				MemberGrade grade = service.getGrade(vo.getId());
				String nickname = service.getNickname(vo.getId());
				AuthVO authVO = new AuthVO();
				authVO.setId(vo.getId());
				authVO.setGrade(grade);
				authVO.setNickname(nickname);
				session.setAttribute("auth", authVO);
				
				String userURI = (String) session.getAttribute("userURI");
				if(userURI != null) {
					session.removeAttribute("userURI");
					response.sendRedirect(userURI);
					return;
				}
				response.sendRedirect(contextPath+"/index");
				return;
			}else {
				PrintWriter out = response.getWriter();
				out.print("<script> alert('아이디 혹은 비밀번호 입력 오류입니다.');history.go(-1);</script>");
				out.flush();
				return;
			}
		}
		
		// 회원정보 수정
		else if (pathInfo.equals("/memberModForm")) {
			nextPage = "memberModForm";
		}
		
		// 회원정보 수정 처리
		else if (pathInfo.equals("/modMember")) {
			if (request.getParameter("pwd1").equals(request.getParameter("pwd2"))) {
				MemberVO vo = MemberVO.builder()
						.id(request.getParameter("id"))
						.pwd((String) request.getAttribute("pwd"))
						.name(request.getParameter("name"))
						.nickname(request.getParameter("nickname"))
						.email(request.getParameter("email"))
						.phone(request.getParameter("phone"))
						.build();
				service.modMember(vo);
			} else {
				PrintWriter out = response.getWriter();
				out.print("<script> alert('변경할 비밀번호가 일치하지 않습니다.');history.go(-1);</script>");
				out.flush();
				return;
			}
			response.sendRedirect(contextPath+"/myPage");
			return;
		}
		
		// 닉네임 변경 페이지
		else if (pathInfo.equals("/modNicknameForm")) {
			nextPage = "memberModNickname";
		}
		
		// 닉네임 변경 처리
		else if (pathInfo.equals("/modNickname")) {
			String nickname = request.getParameter("new_nickname");
			String id = request.getParameter("id");
			String pwd = (String) request.getAttribute("pwd");
			MemberVO vo = MemberVO.builder().id(id).pwd(pwd).nickname(nickname).build();
			int result = service.modNickname(vo, pwd);
			if(result==1) {
				HttpSession session = request.getSession();
				MemberGrade grade = service.getGrade(vo.getId());
				AuthVO authVO = new AuthVO();
				authVO.setId(vo.getId());
				authVO.setGrade(grade);
				authVO.setNickname(nickname);
				session.setAttribute("auth", authVO);
				response.sendRedirect(contextPath+"/member/myPage");
				return;
			}
			else if (result==2) {
				PrintWriter out = response.getWriter();
				out.print("<script> alert('비밀번호 입력 오류입니다.');history.go(-1);</script>");
				out.flush();
			}
			System.out.println("무언가 잘못되었습니다");
			return;
		}
		
		// 등업페이지가기
		else if (pathInfo.equals("/getHigher")) {
			nextPage = "getHigher";
		}
		
		// 등업 작동
		else if (pathInfo.equals("/doHigher")) {
			String id = request.getParameter("id");
			String pwd = (String) request.getAttribute("pwd");
			int result = service.getHigherMember(id, pwd);
			MemberVO vo = MemberVO.builder().id(id).pwd(pwd).build();
			if(result==1) {
				response.sendRedirect(contextPath+"/member/doHighComplete");
				HttpSession session = request.getSession();
				MemberGrade grade = service.getGrade(vo.getId());
				String nickname = service.getNickname(vo.getId());
				AuthVO authVO = new AuthVO();
				authVO.setId(vo.getId());
				authVO.setGrade(grade);
				authVO.setNickname(nickname);
				session.setAttribute("auth", authVO);
				return;
			} else if (result==2) {
				PrintWriter out = response.getWriter();
				out.print("<script> alert('비밀번호 입력 오류입니다.');history.go(-1);</script>");
				out.flush();
				return;
			} else {
				System.out.println("잘못된 명령");
				return;
			}
		}
		
		// 등업
		else if (pathInfo.equals("/doHighComplete")) {
			nextPage = "doHighComplete";
		}
		
		
		// 로그아웃
		else if (pathInfo.equals("/logout")) {
			HttpSession session = request.getSession(false);
			session.removeAttribute("auth");
			PrintWriter out = response.getWriter();
			out.print("<script> alert('로그아웃 되었습니다.');</script>");
			response.sendRedirect(contextPath+"/index");
			out.flush();
			return;
		}
		
		// 탈퇴하러 가기
		else if (pathInfo.equals("/quit")) {
			nextPage = "memberQuit";
		}
		
		// 탈퇴하기
		else if (pathInfo.equals("/doQuit")) {
			HttpSession session = request.getSession(false);
			String id = request.getParameter("id");
			String password = (String) request.getAttribute("pwd");
			int quitMember = service.quitMember(id,password);
			if (quitMember==1) {
				session.removeAttribute("auth");
				response.sendRedirect(contextPath+"/member/goodbye");
				return;
			} else if (quitMember==2) {
				PrintWriter out = response.getWriter();
				out.print("<script> alert('비밀번호 입력 오류입니다.');history.go(-1);</script>");
				out.flush();
				return;
			}
		}
		
		// 강퇴하기
		else if (pathInfo.equals("/kick")) {
			String id = request.getParameter("id");
			service.kickMember(id);
			System.out.println(id+"님 강퇴완료");
			response.sendRedirect(contextPath+"/member/memberlist");
			return;
		}
		
		
		// 회원정보 관리
		else if (pathInfo.equals("/memberlist")) {
			List<MemberVO> memberList = service.selectAllMember();
			request.setAttribute("member", memberList);
			nextPage = "memberList";
		}
		
		
		
		// 탈퇴완료 페이지
		else if (pathInfo.equals("/goodbye")) {
			nextPage = "bye";
		}
		
		// 관리자 외 접근권한이 없음
		else if (pathInfo.equals("/notAccessble")) {
			nextPage = "notAccessble";
		}
		
		// 정회원 이상 접근가능
		else if (pathInfo.equals("/notAccessbleGrade")) {
			nextPage = "notAccessble2";
		}
		

		// 에러페이지
		else {
			request.getRequestDispatcher("/WEB-INF/_404.jsp").forward(request, response);
			return;
		}
		
		rd = request.getRequestDispatcher(PREFIX+nextPage+SUFFIX);
		rd.forward(request, response);
	}
}
