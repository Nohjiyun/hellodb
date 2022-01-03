package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import config.DBconfig;
import dto.NaverApiDto;

public class NaverInfoDaoImpl implements NaverInfoDao {
	private Connection conn; // DB 커넥션 연결 객체

	public NaverInfoDaoImpl() {
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

	public void insertNaverInfo() {
		// 쿼리문 준비
		String sql = "INSERT INTO naver_info (title, url, date) VALUES (?, ?, ?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "2제목이 들어간다");
			pstmt.setString(2, "2http://URL이 들어간다");
			pstmt.setString(3, "22021 여기는 날짜가 들어간");

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
	
	public void insertNaverInfo(String tit, String url, String date) {
		// 쿼리문 준비
		String sql = "INSERT INTO naver_info (title, url, date) VALUES (?, ?, ?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tit);
			pstmt.setString(2, url);
			pstmt.setString(3, date);

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
	
	public void insertNaverInfo(NaverApiDto dto) {
		// 쿼리문 준비
		String sql = "INSERT INTO naver_info (kidx, title, url, date) VALUES (?, ?, ?, ?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getKidx());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getLink());
			pstmt.setString(4, dto.getPubDate());

			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("데이터 삽입 성공!");

			}

		} catch (Exception e) {
			System.out.println("데이터 삽입 실패!");
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}

	}

	// 조건에 맞는 행을 db에서 1개 행만 가져오는 메서드
	public void selectOne(int id) {
		String sql = "SELECT * FROM naver_info WHERE num = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("num: " + rs.getInt("num"));
				System.out.println("title: " + rs.getString("title"));
				System.out.println("url: " + rs.getString("url"));
				System.out.println("date: " + rs.getString("date"));
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
	}
	
	public ArrayList<NaverApiDto> selectAll() {
		ArrayList<NaverApiDto> list = new ArrayList<NaverApiDto>();  // 데이터 담을 그릇
		String sql = "SELECT * FROM naver_info";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				NaverApiDto dto = new NaverApiDto();
				dto.setTitle(rs.getString("title"));
				dto.setLink(rs.getString("url"));
				dto.setPubDate(rs.getString("date"));
				list.add(dto);
//				System.out.println("num: " + rs.getInt("num"));
//				System.out.println("title: " + rs.getString("title"));
//				System.out.println("url: " + rs.getString("url"));
//				System.out.println("date: " + rs.getString("date"));
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
		return list;
	}

//조건에 맞는 행을 DB에서 수정(갱신)    하는 메서드
	public void updateNaverInfo(int id) {
		String sql = "UPDATE naver_info SET title=? WHERE  num=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "제목 수정");
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			System.out.println("수정된 id: " + id);

		} catch (Exception e) {
			System.out.println("update 예외 발생");
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}
	}
}