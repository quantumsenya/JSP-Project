package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.common.ConnectionUtil;
import com.domain.MemberVO;
import com.domain.MemberVO.MemberGrade;

public class MemberDao {

	private DataSource dataSource;
	
	public MemberDao() {
		dataSource = ConnectionUtil.getDatasource();
	}
	
	// 회원가입
	public void joinMember(MemberVO vo) {
		String query = "INSERT INTO PROJ_MEMBER (MNO, ID, PWD, NAME, NICKNAME, PHONE, EMAIL) VALUES (PJMNO_SEQ.NEXTVAL,?,?,?,?,?,?)";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		) {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getNickname());
			pstmt.setString(5, vo.getPhone());
			pstmt.setString(6, vo.getEmail());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 로그인 확인
	public boolean loginCheck(MemberVO vo) {
		boolean result = false;
		String query = "SELECT DECODE(COUNT(*),1,'TRUE','FALSE') AS RESULT FROM PROJ_MEMBER WHERE ID=? AND PWD=?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					result = Boolean.parseBoolean(rs.getString("RESULT"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 회원등급 조회
	public MemberGrade getGrade(String id) {
		MemberGrade grade = null;
		String query = "SELECT GRADE FROM PROJ_MEMBER WHERE ID =?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery();){
				if(rs.next())
					grade = MemberGrade.valueOf(rs.getString("GRADE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grade;
	}
	
	// 회원정보 수정
	public void modMember(MemberVO vo) {
		String query1 = "SELECT * FROM PROJ_MEMBER WHERE ID=?";
		String query2 = "UPDATE PROJ_MEMBER SET PWD=?, NAME=?, NICKNAME=?, EMAIL=?, PHONE=? WHERE ID=?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt1 = conn.prepareStatement(query1);
				){
				pstmt1.setString(1, vo.getId());
				pstmt1.executeQuery();
				try (ResultSet rs = pstmt1.executeQuery();) {
					if (rs.next()) {
						String origPwd = rs.getString("pwd");
						try (
							Connection conn2 = dataSource.getConnection();
							PreparedStatement pstmt2 = conn.prepareStatement(query2);		
						){
							if (origPwd.equals(vo.getOrigPwd())) {
								pstmt2.setString(1, vo.getPwd());
								pstmt2.setString(2, vo.getName());
								pstmt2.setString(3, vo.getNickname());
								pstmt2.setString(4, vo.getEmail());
								pstmt2.setString(5, vo.getPhone());
								pstmt2.setString(6, vo.getId());
								pstmt2.executeUpdate();
							} else {
								System.out.println("비번틀림");
								System.out.println("기존비번 : " + origPwd);
								System.out.println("입력한비번 : " + vo.getPwd());
							}
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 닉네임 변경
	public int modNickname(MemberVO vo, String pwd) {
		int result = 0;
		String pwdQuery = "SELECT PWD FROM PROJ_MEMBER WHERE ID=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(pwdQuery);
			){
				pstmt.setString(1, vo.getId());
				pstmt.executeUpdate();
				try (ResultSet rs = pstmt.executeQuery();) {
					if(rs.next()) {
						String origPassword = rs.getString("PWD");
						String query = "UPDATE PROJ_MEMBER SET NICKNAME=? WHERE ID=?";
						if(origPassword.equals(pwd)) {
							try (
								Connection conn2 = dataSource.getConnection();
								PreparedStatement pstmt2 = conn2.prepareStatement(query);
								) {
								pstmt2.setString(1, vo.getNickname());
								pstmt2.setString(2, vo.getId());
								pstmt2.executeUpdate();
								result = 1;
							}
						}
					}
					else {
						result=2;
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 닉네임정보
	public String getNickname(String id) {
		String nickname = null;
		String query = "SELECT NICKNAME FROM PROJ_MEMBER WHERE ID=?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			){
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			try (ResultSet rs = pstmt.executeQuery();) {
				if(rs.next()) {
					nickname = rs.getString("nickname");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nickname;
	}
	
	// 강퇴
	public void kickMember(String id) {
		String query = "DELETE FROM PROJ_MEMBER WHERE ID=?";
		try(
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setString(1, id);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 탈퇴
	public int quitMember(String id, String password) {
		int result=0;
		String query = "SELECT PWD FROM PROJ_MEMBER WHERE ID=?";
		
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			try (ResultSet rs = pstmt.executeQuery();) {
				if(rs.next()) {
					String origPassword = rs.getString("PWD");
					String query2 = "DELETE FROM PROJ_MEMBER WHERE ID=?";
					if(origPassword.equals(password)) {
						try(
							Connection conn2 = dataSource.getConnection();
							PreparedStatement pstmt2 = conn.prepareStatement(query2);
							) {
							pstmt2.setString(1, id);
							pstmt2.executeQuery();
							result = 1;
							System.out.println("회원삭제 성공");
						}
					}else {
						result = 2;
						System.out.println("회원삭제 실패 - 비밀번호 불일치");
						System.out.println("원본PW : " + origPassword);
						System.out.println("입력PW : " + password);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 등업하기
	public int getHigher(String id, String pwd) {
		int result = 1;
		String query = "SELECT PWD FROM PROJ_MEMBER WHERE ID=?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			try (ResultSet rs = pstmt.executeQuery();) {
				if(rs.next()) {
					String origPassword = rs.getString("PWD");
					String query2 = "UPDATE PROJ_MEMBER SET GRADE = '정회원' WHERE ID =?";
					if(origPassword.equals(pwd)) {
						try(
							Connection conn2 = dataSource.getConnection();
							PreparedStatement pstmt2 = conn.prepareStatement(query2);
							) {
							pstmt2.setString(1, id);
							pstmt2.executeQuery();
							result = 1;
							System.out.println("등업완료");
						}
					}else {
						System.out.println("등업 실패 - 비밀번호 불일치");
						System.out.println("원본PW : " + origPassword);
						System.out.println("입력PW : " + pwd);
						result = 2;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 전체회원
	public List<MemberVO> selectAllMember() {
		String query = "SELECT * FROM PROJ_MEMBER ORDER BY MNO DESC";
		List<MemberVO> list = new ArrayList<MemberVO>();
		try(
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {
				MemberVO vo = MemberVO.builder()
						.mno(rs.getInt("mno"))
						.grade(MemberGrade.valueOf(rs.getString("GRADE")))
						.id(rs.getString("id"))
						.nickname(rs.getString("nickname"))
						.name(rs.getString("name"))
						.phone(rs.getString("phone"))
						.email(rs.getString("email"))
						.joinDate(rs.getDate("joindate"))
						.build();
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	
}
