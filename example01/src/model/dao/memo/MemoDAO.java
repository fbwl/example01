package model.dao.memo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.dto.memo.MemoDTO;

public class MemoDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public Connection dbConn() {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String dbId = "example01";
			String dbPasswd = "1234";

			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbId, dbPasswd);
			System.out.println("--오라클 접속 성공--");

		} catch (Exception e) {
			System.out.println("--오라클 접속 실패--");
			e.printStackTrace();
		}
		return conn;
	}

	public void dbConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int setInsert(MemoDTO dto) {
		conn = dbConn();
		int result = 0;
		try {
			String sql = "insert into memo values(seq_memo.nextval,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<MemoDTO> getSelectAll() {
		conn = dbConn();
		ArrayList<MemoDTO> list = new ArrayList<>();
		try {
			String sql = "select * from memo order by no asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemoDTO dto = new MemoDTO();
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegi_date(rs.getDate("regi_date"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public int getTotalRecord() {
		conn = dbConn();
		int count = 0;
		try {
			String sql = "select count(*) from memo";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<MemoDTO> getSelectMemo(int startRecord, int lastRecord) {
		conn = dbConn();
		ArrayList<MemoDTO> list = new ArrayList<>();
		try {
			String basicSql = "";
			basicSql += "select * from memo where no > 0 order by no desc";
			String sql = "";
			sql += "select * from (select A.*, rownum rnum from (" + basicSql + ") A) where rnum >=? and rnum<=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRecord);
			pstmt.setInt(2, lastRecord);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemoDTO dto = new MemoDTO();
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegi_date(rs.getDate("regi_date"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}
