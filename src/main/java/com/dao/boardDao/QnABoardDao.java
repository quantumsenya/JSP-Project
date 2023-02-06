package com.dao.boardDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.common.ConnectionUtil;
import com.domain.BoardVO;

public class QnABoardDao {

	private DataSource dataSource;
	
	public QnABoardDao() {
		dataSource = ConnectionUtil.getDatasource();
	}

	public List<BoardVO> selectQnA() {
		String query = "SELECT * FROM PROJ_QNA ORDER BY BNO DESC";
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
						.views(rs.getInt("views"))
						.isend(rs.getString("isend"))
						.writeDate(rs.getDate("writedate"))
						.build();
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 질문 상세
	public BoardVO findQnA(int bno) {
		BoardVO vo = null;
		String query = "SELECT * FROM PROJ_QNA WHERE BNO=?";
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
							.views(rs.getInt("views"))
							.isend(rs.getString("isend"))
							.build();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	// 조회수 올리기
	public void upQnA(int bno) {
		String query = "UPDATE PROJ_QNA SET VIEWS=VIEWS+1 WHERE BNO=?";
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

	// 질문게시판 등록
	public int insertQnA(BoardVO vo) {
		String query = "INSERT INTO PROJ_QNA(BNO,CATEGORY,TITLE,CONTENT,WRITER,WRITERNICK,IMAGEFILE) VALUES (?,?,?,?,?,?,?)";
		int BoardNo = getQnABno();
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

	// 질문게시판 글번호 불러오기
	private int getQnABno() {
		int QnA_No = 0;
		String query = "SELECT MAX(BNO)+1 as boardNo FROM PROJ_QNA";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
		){
			if(rs.next()) {
				QnA_No = rs.getInt("boardNo");
				if(QnA_No == 0) {
					QnA_No = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return QnA_No;
	}

	// 질문 수정
	public void updateQnA(BoardVO vo) {
		String imageFile = vo.getImageFile();
		int bno = vo.getBno();
		
		String query = "UPDATE PROJ_QNA SET TITLE=?,CONTENT=?";
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

	// 질문 삭제
	public void removeQnA(int bno) {
		String query = "DELETE FROM PROJ_QNA WHERE BNO=?";
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

	// 질문 종료
	public void isEndQuestion(int bno) {
		String query = "UPDATE PROJ_QNA SET ISEND=? WHERE BNO=?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
		) {
			pstmt.setString(1, "종료");
			pstmt.setInt(2, bno);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
