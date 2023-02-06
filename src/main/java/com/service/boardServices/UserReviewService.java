package com.service.boardServices;

import java.util.List;

import com.dao.boardDao.UserReviewDao;
import com.domain.BoardVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserReviewService {

	private UserReviewDao uDao;
	
	// 글목록
	public List<BoardVO> userReviewList() {
		return uDao.selectUserReviewAll();
	}

	// 글조회
	public BoardVO selectUserReview(int bno) {
		return uDao.findUserReview(bno);
	}
	
	// 조회수
	public void upUserReviewViews(int bno) {
		uDao.upUserViews(bno);
	}
	
	// 추천
	public void upUserReviewSuggest(int bno) {
		uDao.userSuggest(bno);
	}

	// 글작성
	public int addUserReview(BoardVO vo) {
		return uDao.insertUserReview(vo);
	}

	// 글수정
	public void modUserReview(BoardVO vo) {
		uDao.updateUserReview(vo);
	}

	// 글삭제
	public void removeUserReview(int bno) {
		uDao.removeUserReview(bno);
	}
	
}
