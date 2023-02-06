package com.service;

import java.util.List;

import com.dao.ReplyDao;
import com.domain.ReplyVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReplyServices {
	
	private ReplyDao dao;
		
	public List<ReplyVO> freeReplyList(int bno) {
		return dao.freeReplyList(bno);
	}
	
	public void freeReplyWrite(ReplyVO vo) {
		dao.freeReplyWrite(vo);
	}
	
	public void freeReplyRemove(ReplyVO vo) {
		dao.freeReplyRemove(vo);
	}
	
	
	public List<ReplyVO> areaReplyList(int bno) {
		return dao.areaReplyList(bno);
	}
	
	public void areaReplyWrite(ReplyVO vo) {
		dao.areaReplyWrite(vo);
	}
	
	public void areaReplyRemove(ReplyVO vo) {
		dao.areaReplyRemove(vo);
	}
	
	
	public List<ReplyVO> reviewReplyList(int bno) {
		return dao.userReviewReplyList(bno);
	}
	
	public void reviewReplyWrite(ReplyVO vo) {
		dao.userReviewReplyWrite(vo);
	}
	
	public void reviewReplyRemove(ReplyVO vo) {
		dao.userReviewReplyRemove(vo);
	}
	
	
	public List<ReplyVO> newsReplyList(int bno) {
		return dao.newsroomReplyList(bno);
	}
	
	public void newsReplyWrite(ReplyVO vo) {
		dao.newsroomReplyWrite(vo);
	}
	
	public void newsReplyRemove(ReplyVO vo) {
		dao.newsroomReplyRemove(vo);
	}
	
	
	public List<ReplyVO> shareReplyList(int bno) {
		return dao.shareReplyList(bno);
	}
	
	public void shareReplyWrite(ReplyVO vo) {
		dao.shareReplyWrite(vo);
	}
	
	public void shareReplyRemove(ReplyVO vo) {
		dao.shareReplyRemove(vo);
	}
	
	
	public List<ReplyVO> qnaReplyList(int bno) {
		return dao.qnaReplyList(bno);
	}
	
	public void qnaReplyWrite(ReplyVO vo) {
		dao.qnaReplyWrite(vo);
	}
	
	public void qnaReplyRemove(ReplyVO vo) {
		dao.qnaReplyRemove(vo);
	}
}
