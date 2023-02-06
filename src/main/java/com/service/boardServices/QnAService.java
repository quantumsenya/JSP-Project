package com.service.boardServices;

import java.util.List;

import com.dao.boardDao.QnABoardDao;
import com.domain.BoardVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QnAService {

	private QnABoardDao qDao;
	
	public List<BoardVO> QnAList() {
		return qDao.selectQnA();
	}

	public BoardVO selectQnA(int bno) {
		return qDao.findQnA(bno);
	}
	
	// 조회수
	public void upQnA(int bno) {
		qDao.upQnA(bno);
	}

	public int addQnA(BoardVO vo) {
		return qDao.insertQnA(vo);
	}

	public void modQnA(BoardVO vo) {
		qDao.updateQnA(vo);
	}

	public void removeQnA(int bno) {
		qDao.removeQnA(bno);
	}

	public void isEndQuestion(int bno) {
		qDao.isEndQuestion(bno);
	}

}
