package com.controller.board;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.FileUpload;
import com.dao.boardDao.AreaBoardDao;
import com.domain.BoardVO;
import com.service.boardServices.AreaBoardService;

@WebServlet("/board/areaboard/*")
public class AreaBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AreaBoardService service;
	private FileUpload multiReq;
	
	@Override
	public void init() throws ServletException {
		AreaBoardDao dao = new AreaBoardDao();
		service = new AreaBoardService(dao);
		multiReq = new FileUpload("board/areaboard/");
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
		final String PREFIX = "/WEB-INF/board/2_areaBoard/";
		final String SUFFIX = ".jsp";
		
		RequestDispatcher rd = null;
		String nextPage = null;
		
		// 글 목록
		if(pathInfo==null || pathInfo.equals("/") ||pathInfo.equals("/list")) {
			List<BoardVO> boardList = service.areaBoardList();
			request.setAttribute("list", boardList);
			nextPage = "list";
		}
		
		// 글 상세
		else if (pathInfo.equals("/detail")){
			String paramBno = request.getParameter("bno");
			int bno = Integer.parseInt(paramBno);
			service.upAreaViews(bno);
			BoardVO board = service.selectareaArticle(bno);
			request.setAttribute("board", board);
			nextPage = "detail";
		}
		
		// 추천하기
		else if (pathInfo.equals("/suggest")) {
			String paramBno = request.getParameter("bno");
			int bno = Integer.parseInt(paramBno);
			service.upAreaSuggest(bno);
			response.sendRedirect(contextPath+"/board/areaboard/detail?bno="+bno);
			return;
		}
		
		// 글쓰기 폼
		else if (pathInfo.equals("/writeForm")) {
			nextPage = "writeForm";
		}
		
		// 글쓰기 처리
		else if (pathInfo.equals("/write")) {
			Map<String, String> req = multiReq.getMultipartRequest(request);
			String imageFile = req.get("imageFile");
			BoardVO vo = BoardVO.builder()
					.title(req.get("title"))
					.category(req.get("category"))
					.content(req.get("content"))
					.writer(req.get("writer"))
					.writernick(req.get("writernick"))
					.imageFile(req.get("imageFile"))
					.build();
			int boardNo = service.addAreaBoard(vo);
			if(imageFile!=null && imageFile.length()>0) {
				multiReq.uploadImage(boardNo, imageFile);
			}
			response.sendRedirect(contextPath+"/board/areaboard/");
			return;
		}
		
		
		// 글수정 처리
		else if (pathInfo.equals("/modareaBoard")) {
			Map<String, String> req = multiReq.getMultipartRequest(request);
			String paramBno = req.get("bno");
			int bno = Integer.parseInt(paramBno);
			String title = req.get("title");
			String category = req.get("category");
			String content = req.get("content");
			String imageFile = req.get("imageFile");
			
			BoardVO vo = BoardVO.builder()
					.bno(bno)
					.title(title)
					.category(category)
					.content(content)
					.imageFile(imageFile)
					.build();
			service.modAreaBoard(vo);
			
			if(imageFile!=null) {
				String origImg = req.get("origFileName");
				multiReq.uploadImage(bno, imageFile);
				if (origImg!=null) {
					multiReq.deleteOrgImage(bno, imageFile);
				}
			}
			response.sendRedirect(contextPath+"/board/areaboard/");
			return;
		}
		
		
		// 글삭제
		else if (pathInfo.equals("/removeAreaBoard")) {
			Map<String, String> req = multiReq.getMultipartRequest(request);
			String paramBno = req.get("bno");
			int bno = Integer.parseInt(paramBno);
			service.removeAreaBoard(bno);
			multiReq.delAllImage(bno);
			response.sendRedirect(contextPath+"/board/areaboard");
			return;
		}
		else {
			request.getRequestDispatcher("/WEB-INF/_404.jsp").forward(request, response);
			return;
		}
		
		rd = request.getRequestDispatcher(PREFIX+nextPage+SUFFIX);
		rd.forward(request, response);
		
	}

	
	
}
