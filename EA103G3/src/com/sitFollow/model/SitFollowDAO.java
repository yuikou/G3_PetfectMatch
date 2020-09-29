package com.sitFollow.model;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

public class SitFollowDAO implements SitFollowDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String ADD_PSTMT = "INSERT INTO sitFollow VALUES (?, ?)";
	private static final String DEL_PSTMT = "DELETE FROM sitFollow WHERE MEMNO=? AND SITNO=?";
	private static final String GET_ALL_BY_MEMNO = "SELECT rownum, memNo From member_table "
			+ "WHERE memNo IN (Select petSitter.memNo FROM petSitter "
			+ "WHERE sitNO IN (Select sitNO FROM sitFollow WHERE MEMNO=? )) " + "Order By rownum DESC";

	@Override
	public Boolean add(String memNo, String sitNo) {
		Boolean addOK = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ADD_PSTMT);
			pstmt.setString(1, memNo);
			pstmt.setString(2, sitNo);
			// �p�G�s�W�@�����\�A�^��true��Controller�A��K����U�@�q�{���X
			if (pstmt.executeUpdate() == 1) {
				addOK = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("�X�{DB���D" + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return addOK;
	}

	@Override
	public Boolean del(String memNo, String sitNo) {
		Boolean delOK = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DEL_PSTMT);
			pstmt.setString(1, memNo);
			pstmt.setString(2, sitNo);
			// �p�G�R���@�����\�A�^��true��Controller�A��K����U�@�q�{���X
			if (pstmt.executeUpdate() == 1) {
				delOK = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("�X�{DB���DGG" + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return delOK;
	}

	@Override
	public List<String> getAllByMemNo(String memNo) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		List<Object> list = new ArrayList<Object>();

		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEMNO);
			pstmt.setString(1, memNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
//				map.put("memNickname", rs.getString(2));
//				map.put("memPhoto", rs.getBytes(3));
//				list.add(map);
//				memVO = new MemberVO();
//				memVO.setMemNickname(rs.getString(2));
//				memVO.setMemPhoto(rs.getBytes(3));
				list.add(rs.getString("memNO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("�X�{DB���D�F OMG" + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
