package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.entity.Board;

public class BoardDao {

	public Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/bbs");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public Board getBoard(int bid) {
		Connection conn = getConnection();
		String sql = "SELECT b.*, u.uname FROM board b"
				+ "	JOIN users u ON b.uid=u.uid"
				+ "	WHERE b.bid=?";
		Board board = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				board = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						LocalDateTime.parse(rs.getString(5).replace(" ", "T")), rs.getInt(6),
						rs.getInt(7), rs.getInt(8), rs.getString(9));
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	// field 값은 title, content, uid 등 attribute name
	// query 값은 검색어
	public List<Board> getBoardList(String field, String query, int num, int offset) {
//		String sql = "select * from board where isDeleted=0" 
//					+ " title ? content ? uid limit ? offset ?";
		Connection conn = getConnection();
		String sql = "SELECT b.*, u.uname FROM board b"
				+ "	JOIN users u ON b.uid=u.uid"
				+ "	WHERE b.isDeleted=0 AND " + field + " LIKE ?"
				+ "	ORDER BY bid DESC "
				+ "	LIMIT ? OFFSET ?";
		
		List<Board> list = new ArrayList<Board>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, query);
			pstmt.setInt(2, num);
			pstmt.setInt(3, offset);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						LocalDateTime.parse(rs.getString(5).replace(" ", "T")), rs.getInt(6),
						rs.getInt(7), rs.getInt(8), rs.getString(9));
				list.add(board);
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void insertBoard(Board board) {
		Connection conn = getConnection();
		String sql = "insert into board values (default, ?, ?, ?, default, default, default, default)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getUid());
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateBoard(Board board) {
		Connection conn = getConnection();
		String sql = "update board set title=?, content=?, modTime=now() where bid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getBid());
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteBoard(int bid) {
		Connection conn = getConnection();
		String sql = "update board set isDeleted=1 where bid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// field 값은 view 또는 reply
	public void increaseCount(String field, int bid) {
		Connection conn = getConnection();
		String sql = "UPDATE board SET " + field + "Count=" + field + "Count+1 WHERE bid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getBoardCount(String field, String query) {
		Connection conn = getConnection();
		query = "%" + query + "%";
		String sql = "SELECT COUNT(bid) FROM board"
					+ "	JOIN users ON board.uid=users.uid"
					+ "	WHERE board.isDeleted=0 and " + field + " LIKE ?";
		
//						SELECT COUNT(bid) FROM board
//						JOIN users ON board.uid=users.uid
//						WHERE isDelete=0 and uname LIKE '%제임스%';
		
		int count = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				count  = rs.getInt(1);
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return count;
	}
}
