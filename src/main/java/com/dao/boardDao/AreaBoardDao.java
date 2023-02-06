package com.dao.boardDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.common.ConnectionUtil;
import com.domain.BoardVO;

public class AreaBoardDao {
	
	private DataSource dataSource;
	
	public AreaBoardDao() {
		dataSource = ConnectionUtil.getDatasource();
	}

	// 지역게시판 글 목록 불러오기
	public List<BoardVO> selectAreaAll() {
		String query = "SELECT * FROM PROJ_AREABOARD ORDER BY BNO DESC";
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
	
	// 지역게시판 글 상세
	public BoardVO findAreaArticle(int bno) {
		BoardVO vo = null;
		String query = "SELECT * FROM PROJ_AREABOARD WHERE BNO=?";
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
	};
	
	// 조회수 올리기
	public void upAreaViews(int bno) {
		String query = "UPDATE PROJ_AREABOARD SET VIEWS=VIEWS+1 WHERE BNO=?";
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
	public void areaSuggest(int bno) {
		String query = "UPDATE PROJ_AREABOARD SET SUGGEST=SUGGEST+1 WHERE BNO=?";
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
	
	// 지역게시판 글 쓰기
	public int insertAreaBoard(BoardVO vo) {
		String query = "INSERT INTO PROJ_AREABOARD(BNO,CATEGORY,TITLE,CONTENT,WRITER,WRITERNICK,IMAGEFILE) VALUES (?,?,?,?,?,?,?)";
		int BoardNo = getAreaBno();
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
	
	// 지역게시판 글 번호 생성
	public int getAreaBno() {
		int areaBoardNo = 0;
		String query = "SELECT MAX(BNO)+1 as boardNo FROM PROJ_AREABOARD";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
		){
			if(rs.next()) {
				areaBoardNo = rs.getInt("boardNo");
				if(areaBoardNo == 0) {
					areaBoardNo = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return areaBoardNo;
	}
	
	// 지역게시판 글삭제
	public void removeAreaBoard(int bno) {
		String query = "DELETE FROM PROJ_AREABOARD WHERE BNO=?";
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
	
	// 지역게시판 글 수정
	public void updateAreaBoard(BoardVO vo) {
		String imageFile = vo.getImageFile();
		int bno = vo.getBno();
		
		String query = "UPDATE PROJ_AREABOARD SET TITLE=?,CONTENT=?";
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
	
}
