package com.service.boardServices;

import java.util.List;

import com.dao.boardDao.FreeBoardDao;
import com.domain.BoardVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FreeBoardService {

	private FreeBoardDao fDao;

	// 글목록
	public List<BoardVO> freeBoardList() {
		return fDao.selectFreeAll();
	}
	
	// 글조회
	public BoardVO selectFreeArticle(int bno) {
		return fDao.findFreeArticle(bno);
	}
	
	// 조회수
	public void upFreeViews(int bno) {
		fDao.upFreeViews(bno);
	}
	
	// 추천
	public void upFreeSuggest(int bno) {
		fDao.freeSuggest(bno);
	}
	
	// 글작성
	public int addFreeBoard(BoardVO vo) {
		return fDao.insertFreeBoard(vo);
	}
	
	// 글수정
	public void modFreeBoard(BoardVO vo) {
		fDao.updateFreeBoard(vo);
	}
	
	// 글삭제
	public void removeFreeBoard(int bno) {
		fDao.deleteFreeBoard(bno);
	}
	
}
