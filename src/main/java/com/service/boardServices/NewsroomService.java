package com.service.boardServices;

import java.util.List;

import com.dao.boardDao.NewsroomDao;
import com.domain.BoardVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewsroomService {

	private NewsroomDao nDao;
	
	public List<BoardVO> newsroomList() {
		return nDao.selectNewsroom();
	}

	public BoardVO selectNewsroom(int bno) {
		return nDao.findNewsroom(bno);
	}
	
	// 조회수
	public void upNewsroomView(int bno) {
		nDao.upNewsViews(bno);
	}
	
	// 추천
	public void upNewsroomSuggest(int bno) {
		nDao.newsSuggest(bno);
	}

	public int addNewsroom(BoardVO vo) {
		return nDao.insertNewsroom(vo);
	}

	public void modNewsroom(BoardVO vo) {
		nDao.updateNewsroom(vo);
	}

	public void removeNewsroom(int bno) {
		nDao.removeNewsroom(bno);
	}
}
