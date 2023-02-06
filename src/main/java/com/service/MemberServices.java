package com.service;

import java.util.List;

import com.dao.MemberDao;
import com.domain.MemberVO;
import com.domain.MemberVO.MemberGrade;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberServices {
	
	private MemberDao dao;

	public void joinMember(MemberVO vo) {
		dao.joinMember(vo);
	}

	public void modMember(MemberVO vo) {
		dao.modMember(vo);
	}
	
	public boolean loginService(MemberVO vo) {
		return dao.loginCheck(vo);
	}
	
	public MemberGrade getGrade(String id) {
		return dao.getGrade(id);
	}
	
	public int quitMember(String id, String password) {
		return dao.quitMember(id, password);
	}
	
	public String getNickname (String id) {
		return dao.getNickname(id);
	}

	public int getHigherMember(String id, String pwd) {
		return dao.getHigher(id, pwd);
	}
	
	public List<MemberVO> selectAllMember() {
		return dao.selectAllMember();
	}
	
	public void kickMember(String id) {
		dao.kickMember(id);
	}

	public int modNickname(MemberVO vo, String pwd) {
		return dao.modNickname (vo, pwd);
		
	}
	
}
