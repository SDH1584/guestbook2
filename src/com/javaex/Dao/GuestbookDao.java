package com.javaex.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestbookDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public List<GuestbookVo> getList() {
		List<GuestbookVo> getList = new ArrayList<GuestbookVo>();
		getConnection();
		try {
			String query = "";
			query += " select no , ";
			query += " 		 name, ";
			query += " 		 password, ";
			query += "       content, ";
			query += " 		 to_char(reg_date,'yyyy-mm-dd hh:mi:ss') reg_date ";
			query += " from guestbook ";
			query += " order by reg_date desc ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String content = rs.getString("content");
				String password = rs.getString("password");
				String regDate = rs.getString("reg_date");

				GuestbookVo guestbookVo = new GuestbookVo(no, name, content, password,  regDate);
				getList.add(guestbookVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();

		return getList;
	}

	public int guestbookInsert(GuestbookVo vo) {
		int count = 0;
		this.getConnection();

		try {
			String query = "";
			query += "insert into guestbook ";
			query += " values(seq_guestbook_no.nextval, ?, ?, ?, sysdate)";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPassword());
			

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		this.close();
		return count;
	}
	
			public int delete(GuestbookVo vo ) {
				int count = 0;
				getConnection();

				try {
					String query = ""; 
					query += " delete from guestbook ";
					query += " where no= ?";
					query += "and password=?";
					pstmt = conn.prepareStatement(query); 

					pstmt.setInt(1, vo.getNo());
					pstmt.setString(2, vo.getPassword());
					
					count = pstmt.executeUpdate();

				} catch (SQLException e) {
					System.out.println("error:" + e);
				}

				close();
				return count;
			}
}