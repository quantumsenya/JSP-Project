package com.service.boardServices;

import java.util.List;

import com.dao.boardDao.ShareBoardDao;
import com.domain.BoardVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShareBoardService {

	private ShareBoardDao sDao;
	
	public List<BoardVO> shareBoardList() {
		return sDao.selectShareBoard();
	}

	public BoardVO selectShareBoard(int bno) {
		return sDao.findShareBoard(bno);
	}
	
	// 조회수
	public void upShareBoardViews(int bno) {
		sDao.upShareViews(bno);
	}
	
	// 추천
	public void upShareBoardSuggest(int bno) {
		sDao.shareSuggest(bno);
	}

	public int addShareBoard(BoardVO vo) {
		return sDao.insertShareBoard(vo);
	}

	public void modShareBoard(BoardVO vo) {
		sDao.updateShareBoard(vo);
	}

	public void removeShareBoard(int bno) {
		sDao.removeShareBoard(bno);
	}
	
	public void isEndInfo(int bno) {
		sDao.isEndInfo(bno);
	}

}
