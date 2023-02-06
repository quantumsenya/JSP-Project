package com.service.boardServices;

import java.util.List;

import com.dao.boardDao.AreaBoardDao;
import com.domain.BoardVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AreaBoardService {

	private AreaBoardDao aDao;
	
	// 글목록
	public List<BoardVO> areaBoardList() {
		return aDao.selectAreaAll();
	}
	
	// 글조회
	public BoardVO selectareaArticle(int bno) {
		return aDao.findAreaArticle(bno);
	}
	
	// 조회수
	public void upAreaViews(int bno) {
		aDao.upAreaViews(bno);
	}
	
	public void upAreaSuggest(int bno) {
		aDao.areaSuggest(bno);
	}

	// 글작성
	public int addAreaBoard(BoardVO vo) {
		return aDao.insertAreaBoard(vo);
	}

	// 글수정
	public void modAreaBoard(BoardVO vo) {
		aDao.updateAreaBoard(vo);
	}

	// 글삭제
	public void removeAreaBoard(int bno) {
		aDao.removeAreaBoard(bno);
	}
	
}
