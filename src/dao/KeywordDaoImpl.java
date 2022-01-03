package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.DBconfig;


public class KeywordDaoImpl implements KeywordDao {
	private Connection conn; // DB 커넥션 연결 객체
	
	public KeywordDaoImpl() {
		try {
			System.out.println("생성자");
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DBconfig.URL, DBconfig.USERNAME, DBconfig.PASSWORD);
			System.out.println("드라이버 로딩 성공");
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패 ");
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
			}
		}
	}

	@Override
	public void insert(String k) {
		// 쿼리문 준비
		String sql = "INSERT INTO keyword (keyword) VALUES (?) "
				+ "ON DUPLICATE KEY UPDATE "
				+ "cnt = (SELECT (cnt+1) FROM keyword AS kw WHERE keyword = ?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, k);
			pstmt.setString(2, k);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("데이터 삽입 성공!");
			}

		} catch (Exception e) {
			System.out.println("데이터 삽입 실패!");
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}
		
	}

	@Override
	public int selectOne(String k) {
		int ret = 0;
		String sql = "SELECT idx FROM keyword WHERE keyword = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, k);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				ret = rs.getInt("idx");
			}

		} catch (Exception e) {
			System.out.println("select 메서드 예외발생");
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}
		return ret;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}



}