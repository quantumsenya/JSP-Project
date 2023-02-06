package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.common.ConnectionUtil;
import com.domain.ReplyVO;

public class ReplyDao {

	private DataSource dataSource;

	public ReplyDao() {
		dataSource = ConnectionUtil.getDatasource();
	}
	
	// 자유게시판 댓글목록 불러오기
	public List<ReplyVO> freeReplyList(int bno) {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		String query = "SELECT * FROM PROJ_FREE_REPLY WHERE BNO=? ORDER BY BNO ASC";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setInt(1, bno);
			try (ResultSet rs = pstmt.executeQuery();) {
				while(rs.next()) {
					ReplyVO vo = ReplyVO.builder()
							.rno(rs.getInt("rno"))
							.bno(rs.getInt("bno"))
							.reply(rs.getString("reply"))
							.writer(rs.getString("writer"))
							.writernick(rs.getString("writernick"))
							.replyDate(rs.getDate("replyDate"))
							.build();
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// 자유게시판 댓글 작성
	public void freeReplyWrite(ReplyVO vo) {
		String query = "INSERT INTO PROJ_FREE_REPLY (RNO,BNO,REPLY,WRITER,WRITERNICK) VALUES (SEQ_FREEREPLY.NEXTVAL,?,?,?,?)";
		String query2 = "UPDATE PROJ_FREEBOARD SET REPLYCOUNT=REPLYCOUNT+1 WHERE BNO=?";
		try(Connection conn = dataSource.getConnection();){
			try (
				PreparedStatement pstmt = conn.prepareStatement(query);
				PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
				conn.setAutoCommit(false);
				pstmt.setInt(1, vo.getBno());
				pstmt.setString(2, vo.getReply());
				pstmt.setString(3, vo.getWriter());
				pstmt.setString(4, vo.getWriternick());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getBno());
				pstmt2.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 자유게시판 댓글 삭제
	
	public void freeReplyRemove(ReplyVO vo) {
		String query = "DELETE FROM PROJ_FREE_REPLY WHERE RNO=?";
		String query2 = "UPDATE PROJ_FREEBOARD SET REPLYCOUNT=REPLYCOUNT-1 WHERE BNO=?";
		try (Connection conn = dataSource.getConnection();){
			try (
					PreparedStatement pstmt = conn.prepareStatement(query);
					PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
				conn.setAutoCommit(false);
				
				pstmt.setInt(1, vo.getRno());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getBno());
				pstmt2.executeUpdate();
				
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	// 지역게시판 댓글목록 불러오기
	public List<ReplyVO> areaReplyList(int bno) {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		String query = "SELECT * FROM PROJ_AREA_REPLY WHERE BNO=? ORDER BY BNO ASC";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setInt(1, bno);
			try (ResultSet rs = pstmt.executeQuery();) {
				while(rs.next()) {
					ReplyVO vo = ReplyVO.builder()
							.rno(rs.getInt("rno"))
							.bno(rs.getInt("bno"))
							.reply(rs.getString("reply"))
							.writer(rs.getString("writer"))
							.writernick(rs.getString("writernick"))
							.replyDate(rs.getDate("replyDate"))
							.build();
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 지역게시판 댓글 작성
	public void areaReplyWrite(ReplyVO vo) {
		String query = "INSERT INTO PROJ_AREA_REPLY (RNO,BNO,REPLY,WRITER,WRITERNICK) VALUES (SEQ_AREAREPLY.NEXTVAL,?,?,?,?)";
		String query2 = "UPDATE PROJ_AREABOARD SET REPLYCOUNT=REPLYCOUNT+1 WHERE BNO=?";
		try(Connection conn = dataSource.getConnection();){
			try (
				PreparedStatement pstmt = conn.prepareStatement(query);
				PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
				conn.setAutoCommit(false);
				pstmt.setInt(1, vo.getBno());
				pstmt.setString(2, vo.getReply());
				pstmt.setString(3, vo.getWriter());
				pstmt.setString(4, vo.getWriternick());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getBno());
				pstmt2.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 지역게시판 댓글 삭제
	public void areaReplyRemove(ReplyVO vo) {
		String query = "DELETE FROM PROJ_AREA_REPLY WHERE RNO=?";
		String query2 = "UPDATE PROJ_AREABOARD SET REPLYCOUNT=REPLYCOUNT-1 WHERE BNO=?";
		try (Connection conn = dataSource.getConnection();){
			try (
					PreparedStatement pstmt = conn.prepareStatement(query);
					PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
				conn.setAutoCommit(false);
				
				pstmt.setInt(1, vo.getRno());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getBno());
				pstmt2.executeUpdate();
				
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	// 회원리뷰 게시판 댓글목록 불러오기
	public List<ReplyVO> userReviewReplyList(int bno) {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		String query = "SELECT * FROM PROJ_USERREVIEW_REPLY WHERE BNO=? ORDER BY BNO ASC";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setInt(1, bno);
			try (ResultSet rs = pstmt.executeQuery();) {
				while(rs.next()) {
					ReplyVO vo = ReplyVO.builder()
							.rno(rs.getInt("rno"))
							.bno(rs.getInt("bno"))
							.reply(rs.getString("reply"))
							.writer(rs.getString("writer"))
							.writernick(rs.getString("writernick"))
							.replyDate(rs.getDate("replyDate"))
							.build();
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 회원리뷰 게시판 댓글 작성
	public void userReviewReplyWrite(ReplyVO vo) {
		String query = "INSERT INTO PROJ_USERREVIEW_REPLY (RNO,BNO,REPLY,WRITER,WRITERNICK) VALUES (SEQ_USERREVIEW_REPLY.NEXTVAL,?,?,?,?)";
		String query2 = "UPDATE PROJ_USERREVIEW SET REPLYCOUNT=REPLYCOUNT+1 WHERE BNO=?";
		try(Connection conn = dataSource.getConnection();){
			try (
				PreparedStatement pstmt = conn.prepareStatement(query);
				PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
				conn.setAutoCommit(false);
				pstmt.setInt(1, vo.getBno());
				pstmt.setString(2, vo.getReply());
				pstmt.setString(3, vo.getWriter());
				pstmt.setString(4, vo.getWriternick());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getBno());
				pstmt2.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 회원리뷰게시판 댓글 삭제
	public void userReviewReplyRemove(ReplyVO vo) {
		String query = "DELETE FROM PROJ_USERREVIEW_REPLY WHERE RNO=?";
		String query2 = "UPDATE PROJ_USERREVIEW SET REPLYCOUNT=REPLYCOUNT-1 WHERE BNO=?";
		try (Connection conn = dataSource.getConnection();){
			try (
					PreparedStatement pstmt = conn.prepareStatement(query);
					PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
				conn.setAutoCommit(false);
				
				pstmt.setInt(1, vo.getRno());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getBno());
				pstmt2.executeUpdate();
				
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 뉴스룸 댓글목록 불러오기
	public List<ReplyVO> newsroomReplyList(int bno) {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		String query = "SELECT * FROM PROJ_NEWSROOM_REPLY WHERE BNO=? ORDER BY BNO ASC";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setInt(1, bno);
			try (ResultSet rs = pstmt.executeQuery();) {
				while(rs.next()) {
					ReplyVO vo = ReplyVO.builder()
							.rno(rs.getInt("rno"))
							.bno(rs.getInt("bno"))
							.reply(rs.getString("reply"))
							.writer(rs.getString("writer"))
							.writernick(rs.getString("writernick"))
							.replyDate(rs.getDate("replyDate"))
							.build();
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 뉴스룸 댓글 작성
	public void newsroomReplyWrite(ReplyVO vo) {
		String query = "INSERT INTO PROJ_NEWSROOM_REPLY (RNO,BNO,REPLY,WRITER,WRITERNICK) VALUES (SEQ_NEWSROOM_REPLY.NEXTVAL,?,?,?,?)";
		String query2 = "UPDATE PROJ_NEWSROOM SET REPLYCOUNT=REPLYCOUNT+1 WHERE BNO=?";
		try(Connection conn = dataSource.getConnection();){
			try (
				PreparedStatement pstmt = conn.prepareStatement(query);
				PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
				conn.setAutoCommit(false);
				pstmt.setInt(1, vo.getBno());
				pstmt.setString(2, vo.getReply());
				pstmt.setString(3, vo.getWriter());
				pstmt.setString(4, vo.getWriternick());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getBno());
				pstmt2.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 뉴스룸 댓글 삭제
	public void newsroomReplyRemove(ReplyVO vo) {
		String query = "DELETE FROM PROJ_NEWSROOM_REPLY WHERE RNO=?";
		String query2 = "UPDATE PROJ_NEWSROOM SET REPLYCOUNT=REPLYCOUNT-1 WHERE BNO=?";
		try (Connection conn = dataSource.getConnection();){
			try (
					PreparedStatement pstmt = conn.prepareStatement(query);
					PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
				conn.setAutoCommit(false);
				
				pstmt.setInt(1, vo.getRno());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getBno());
				pstmt2.executeUpdate();
				
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 정보공유 댓글목록 불러오기
	public List<ReplyVO> shareReplyList(int bno) {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		String query = "SELECT * FROM PROJ_SHARE_REPLY WHERE BNO=? ORDER BY BNO ASC";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setInt(1, bno);
			try (ResultSet rs = pstmt.executeQuery();) {
				while(rs.next()) {
					ReplyVO vo = ReplyVO.builder()
							.rno(rs.getInt("rno"))
							.bno(rs.getInt("bno"))
							.reply(rs.getString("reply"))
							.writer(rs.getString("writer"))
							.writernick(rs.getString("writernick"))
							.replyDate(rs.getDate("replyDate"))
							.build();
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 정보공유 댓글 작성
	public void shareReplyWrite(ReplyVO vo) {
		String query = "INSERT INTO PROJ_SHARE_REPLY (RNO,BNO,REPLY,WRITER,WRITERNICK) VALUES (SEQ_SHARE_REPLY.NEXTVAL,?,?,?,?)";
		String query2 = "UPDATE PROJ_SHAREBOARD SET REPLYCOUNT=REPLYCOUNT+1 WHERE BNO=?";
		try(Connection conn = dataSource.getConnection();){
			try (
				PreparedStatement pstmt = conn.prepareStatement(query);
				PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
				conn.setAutoCommit(false);
				pstmt.setInt(1, vo.getBno());
				pstmt.setString(2, vo.getReply());
				pstmt.setString(3, vo.getWriter());
				pstmt.setString(4, vo.getWriternick());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getBno());
				pstmt2.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 정보공유 댓글 삭제
	public void shareReplyRemove(ReplyVO vo) {
		String query = "DELETE FROM PROJ_SHARE_REPLY WHERE RNO=?";
		String query2 = "UPDATE PROJ_SHAREBOARD SET REPLYCOUNT=REPLYCOUNT-1 WHERE BNO=?";
		try (Connection conn = dataSource.getConnection();){
			try (
					PreparedStatement pstmt = conn.prepareStatement(query);
					PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
				conn.setAutoCommit(false);
				
				pstmt.setInt(1, vo.getRno());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getBno());
				pstmt2.executeUpdate();
				
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 질문답변 댓글목록 불러오기
	public List<ReplyVO> qnaReplyList(int bno) {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		String query = "SELECT * FROM PROJ_QNA_REPLY WHERE BNO=? ORDER BY BNO ASC";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setInt(1, bno);
			try (ResultSet rs = pstmt.executeQuery();) {
				while(rs.next()) {
					ReplyVO vo = ReplyVO.builder()
							.rno(rs.getInt("rno"))
							.bno(rs.getInt("bno"))
							.reply(rs.getString("reply"))
							.writer(rs.getString("writer"))
							.writernick(rs.getString("writernick"))
							.replyDate(rs.getDate("replyDate"))
							.build();
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 질문답변 댓글 작성
	public void qnaReplyWrite(ReplyVO vo) {
		String query = "INSERT INTO PROJ_QNA_REPLY (RNO,BNO,REPLY,WRITER,WRITERNICK) VALUES (SEQ_QNA_REPLY.NEXTVAL,?,?,?,?)";
		String query2 = "UPDATE PROJ_QNA SET REPLYCOUNT=REPLYCOUNT+1 WHERE BNO=?";
		try(Connection conn = dataSource.getConnection();){
			try (
				PreparedStatement pstmt = conn.prepareStatement(query);
				PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
				conn.setAutoCommit(false);
				pstmt.setInt(1, vo.getBno());
				pstmt.setString(2, vo.getReply());
				pstmt.setString(3, vo.getWriter());
				pstmt.setString(4, vo.getWriternick());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getBno());
				pstmt2.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 질문답변 댓글 삭제
	public void qnaReplyRemove(ReplyVO vo) {
		String query = "DELETE FROM PROJ_QNA_REPLY WHERE RNO=?";
		String query2 = "UPDATE PROJ_QNA SET REPLYCOUNT=REPLYCOUNT-1 WHERE BNO=?";
		try (Connection conn = dataSource.getConnection();){
			try (
					PreparedStatement pstmt = conn.prepareStatement(query);
					PreparedStatement pstmt2 = conn.prepareStatement(query2);
				){
				conn.setAutoCommit(false);
				
				pstmt.setInt(1, vo.getRno());
				pstmt.executeUpdate();
				
				pstmt2.setInt(1, vo.getBno());
				pstmt2.executeUpdate();
				
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
