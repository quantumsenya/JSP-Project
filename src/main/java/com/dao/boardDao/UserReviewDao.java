package com.dao.boardDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.common.ConnectionUtil;
import com.domain.BoardVO;

public class UserReviewDao {

	private DataSource dataSource;
	
	public UserReviewDao() {
		dataSource = ConnectionUtil.getDatasource();
	}
	
	// 회원리뷰 글 목록
	public List<BoardVO> selectUserReviewAll() {
		String query = "SELECT * FROM PROJ_USERREVIEW ORDER BY BNO DESC";
		List<BoardVO> list = new ArrayList<BoardVO>();
		try(
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {
				BoardVO vo = BoardVO.builder()
						.bno(rs.getInt("bno"))
						.category(rs.getString("category"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.writer(rs.getString("writer"))
						.writernick(rs.getString("writernick"))
						.imageFile(rs.getString("imageFile"))
						.replyCount(rs.getInt("replyCount"))
						.suggest(rs.getInt("suggest"))
						.views(rs.getInt("views"))
						.writeDate(rs.getDate("writedate"))
						.build();
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 회원리뷰 상세글
	public BoardVO findUserReview(int bno) {
		BoardVO vo = null;
		String query = "SELECT * FROM PROJ_USERREVIEW WHERE BNO=?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setInt(1, bno);
			try (ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					vo = BoardVO.builder()
							.bno(rs.getInt("bno"))
							.category(rs.getString("category"))
							.title(rs.getString("title"))
							.content(rs.getString("content"))
							.writer(rs.getString("writer"))
							.writernick(rs.getString("writernick"))
							.writeDate(rs.getDate("writeDate"))
							.imageFile(rs.getString("imageFile"))
							.suggest(rs.getInt("suggest"))
							.views(rs.getInt("views"))
							.build();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	// 조회수 올리기
	public void upUserViews(int bno) {
		String query = "UPDATE PROJ_USERREVIEW SET VIEWS=VIEWS+1 WHERE BNO=?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);	
		){
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 추천
	public void userSuggest(int bno) {
		String query = "UPDATE PROJ_USERREVIEW SET SUGGEST=SUGGEST+1 WHERE BNO=?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);	
		){
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원리뷰 글 작성
	public int insertUserReview(BoardVO vo) {
		String query = "INSERT INTO PROJ_USERREVIEW(BNO,CATEGORY,TITLE,CONTENT,WRITER,WRITERNICK,IMAGEFILE) VALUES (?,?,?,?,?,?,?)";
		int BoardNo = getUserReviewBno();
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			){
			pstmt.setInt(1, BoardNo);
			pstmt.setString(2, vo.getCategory());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getWriter());
			pstmt.setString(6, vo.getWriternick());
			pstmt.setString(7, vo.getImageFile());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BoardNo;
	}
	
	// 회원리뷰 글번호 불러오기
	public int getUserReviewBno() {
		int userReviewNo = 0;
		String query = "SELECT MAX(BNO)+1 as boardNo FROM PROJ_USERREVIEW";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
		){
			if(rs.next()) {
				userReviewNo = rs.getInt("boardNo");
				if(userReviewNo == 0) {
					userReviewNo = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userReviewNo;
	}

	// 회원리뷰 글 수정
	public void updateUserReview(BoardVO vo) {
		String imageFile = vo.getImageFile();
		int bno = vo.getBno();
		
		String query = "UPDATE PROJ_USERREVIEW SET TITLE=?,CONTENT=?";
		if (imageFile!=null) {
			query+= ",IMAGEFILE=? WHERE BNO=?";
		} else {
			query+= " WHERE BNO=?";
		}
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		){
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			if(imageFile!=null) {
				pstmt.setString(3, imageFile);
				pstmt.setInt(4, bno);
			} else {
				pstmt.setInt(3, bno);
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원리뷰 글 삭제
	public void removeUserReview(int bno) {
		String query = "DELETE FROM PROJ_USERREVIEW WHERE BNO=?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			){
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
