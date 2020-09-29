 package com.sitRep.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SitRepJDBCDAO implements SitRepDAO_interface {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "EA103G3";
	private static final String pwd = "123456";
	
	private static final String INSERT_PSTMT = "INSERT INTO sitRep(sitRepNo,sitOrderNo,memNo,empNo,sitRep,"
			+ "sitRepStatus,sitRepResult) VALUES('SR' || lpad(sitRep_SEQ.NEXTVAL, 3, '0'),?,?,?,?,?,?)";
	
	private static final String UPDATE_PSTMT = "UPDATE sitRep set sitOrderNo=?,memNo=?,"
			+ "empNo=?,sitRep=?,sitRepStatus=?,sitRepResult=? where sitRepNo=?";
	private static final String DELETE_BySitRepNo_PSTMT = "DELETE FROM sitRep where sitRepNo=?";
	
	private static final String GET_ONEbyPK_PSTMT = "SELECT sitRepNo, sitOrderNo, memNo, sitRep, empNo, sitRepStatus, sitRepResult FROM sitRep WHERE sitRepNo=?";
	private static final String GETbyFK_sitOrderNo_PSTMT = "SELECT sitRepNo, sitOrderNo, memNo, sitRep, empNo, sitRepStatus, sitRepResult FROM sitRep WHERE sitOrderNo=?";

	private static final String GET_ALL_PSTMT = "SELECT * FROM sitRep";
	
	@Override
	public void insert(SitRepVO sitRepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(INSERT_PSTMT);
			
			pstmt.setString(1, sitRepVO.getSitOrderNo());
			pstmt.setString(2, sitRepVO.getMemNo());
			pstmt.setString(3, sitRepVO.getEmpNo());
			pstmt.setString(4, sitRepVO.getSitRep());
			pstmt.setInt(5, sitRepVO.getSitRepStatus());
			pstmt.setInt(6, sitRepVO.getSitRepResult());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(SitRepVO sitRepVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(UPDATE_PSTMT);
			
			pstmt.setString(1, sitRepVO.getSitOrderNo());
			pstmt.setString(2, sitRepVO.getMemNo());
			pstmt.setString(3, sitRepVO.getEmpNo());
			pstmt.setString(4, sitRepVO.getSitRep());
			pstmt.setInt(5, sitRepVO.getSitRepStatus());
			pstmt.setInt(6, sitRepVO.getSitRepResult());
			pstmt.setString(7, sitRepVO.getSitRepNo());


			pstmt.executeUpdate();			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(String sitRepNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_BySitRepNo_PSTMT);
			pstmt.setString(1, sitRepNo);
			pstmt.executeUpdate();


			con.commit();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public SitRepVO getByPK(String sitRepNo) {
		SitRepVO sitRepVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(GET_ONEbyPK_PSTMT);
			pstmt.setString(1, sitRepNo);
			rs = pstmt.executeQuery();
			
			

			while (rs.next()) {
				sitRepVO = new SitRepVO();
				sitRepVO.setSitRepNo(rs.getString("sitRepNo"));
				sitRepVO.setSitOrderNo(rs.getString("sitOrderNo"));
				sitRepVO.setMemNo(rs.getString("memNo"));
				sitRepVO.setSitRep(rs.getString("sitRep"));
				sitRepVO.setEmpNo(rs.getString("empNo"));
				sitRepVO.setSitRepStatus(rs.getInt("sitRepStatus"));
				sitRepVO.setSitRepResult(rs.getInt("sitRepResult"));
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return sitRepVO;
	}
	
	@Override
	public SitRepVO getByFK(String sitOrderNo) {
		
		SitRepVO sitRepVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(GETbyFK_sitOrderNo_PSTMT);
			rs = pstmt.executeQuery();
			
			pstmt.setString(1, sitOrderNo);
			
			while (rs.next()) {
				sitRepVO = new SitRepVO();
				sitRepVO.setSitRepNo(rs.getString("sitRepNo"));
				sitRepVO.setMemNo(rs.getString("memNo"));
				sitRepVO.setSitRep(rs.getString("sitRep"));
				sitRepVO.setEmpNo(rs.getString("empNo"));
				sitRepVO.setSitRepStatus(rs.getInt("sitRepStatus"));
				sitRepVO.setSitRepResult(rs.getInt("sitRepResult"));
			}
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return sitRepVO;
	}


	@Override
	public List<SitRepVO> getAll() {
		List<SitRepVO> list = new ArrayList<SitRepVO>();
		SitRepVO sitRepVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,pwd);
			pstmt = con.prepareStatement(GET_ALL_PSTMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sitRepVO = new SitRepVO();
				sitRepVO.setSitRepNo(rs.getString("sitRepNo"));
				sitRepVO.setSitOrderNo(rs.getString("sitOrderNo"));
				sitRepVO.setMemNo(rs.getString("memNo"));
				sitRepVO.setSitRep(rs.getString("sitRep"));
				sitRepVO.setEmpNo(rs.getString("empNo"));
				sitRepVO.setSitRepStatus(rs.getInt("sitRepStatus"));
				sitRepVO.setSitRepResult(rs.getInt("sitRepResult"));
				list.add(sitRepVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
//	public static void main(String[] args) {
//
//		SitRepJDBCDAO dao = new SitRepJDBCDAO();
//
//		// 新增
//		SitRepVO sitRepVO = new SitRepVO();
//		sitRepVO.setSitOrderNo("SO002");
//		sitRepVO.setMemNo("M002");
//		sitRepVO.setEmpNo("EMP001");
//		sitRepVO.setSitRep("bad service");
//		sitRepVO.setSitRepStatus(0);
//		sitRepVO.setSitRepResult(2);
//		dao.insert(sitRepVO);
//
//
//		// 修改
//		SitRepVO sitRepVO = new SitRepVO();
//		sitRepVO.setSitOrderNo("SO002");
//		sitRepVO.setMemNo("M002");
//		sitRepVO.setEmpNo("EMP002");
//		sitRepVO.setSitRep("說好有提供接送服務，卻沒有赴約，致電過去才說臨時有事沒辦法接送");
//		sitRepVO.setSitRepStatus(0);
//		sitRepVO.setSitRepResult(3);
//		sitRepVO.setSitRepNo("SR001");
//		dao.update(sitRepVO);
//		
//		// 刪除
//		dao.delete("SR006");
//		
//		// 查詢 getByPK
//		SitRepVO sitRepVO = dao.getByPK("SR001");
//		System.out.println(sitRepVO.getSitRepNo() + ",");
//		System.out.println(sitRepVO.getSitOrderNo() + ",");
//		System.out.println(sitRepVO.getMemNo() + ",");
//		System.out.println(sitRepVO.getSitRep() + ",");
//		System.out.println(sitRepVO.getEmpNo() + ",");
//		System.out.println(sitRepVO.getSitRepStatus() + ",");
//		System.out.println(sitRepVO.getSitRepResult());
//		
//		// 查詢 getByFK_sitNo
//		SitRepVO sitRepVO = dao.getByPK("SO002");
//		System.out.println(sitRepVO.getSitRepNo() + ",");
//		System.out.println(sitRepVO.getSitOrderNo() + ",");
//		System.out.println(sitRepVO.getMemNo() + ",");
//		System.out.println(sitRepVO.getSitRep() + ",");
//		System.out.println(sitRepVO.getEmpNo() + ",");
//		System.out.println(sitRepVO.getSitRepStatus() + ",");
//		System.out.println(sitRepVO.getSitRepResult());
//		
//		
//		// 查詢
//		List<SitRepVO> list = dao.getAll();
//		for (SitRepVO sitRep : list) {
//			System.out.println(sitRep.getSitRepNo() + ",");
//			System.out.println(sitRep.getSitOrderNo() + ",");
//			System.out.println(sitRep.getMemNo() + ",");
//			System.out.println(sitRep.getSitRep() + ",");
//			System.out.println(sitRep.getEmpNo() + ",");
//			System.out.println(sitRep.getSitRepStatus() + ",");
//			System.out.println(sitRep.getSitRepResult());
//			System.out.println();
//		}
//	}
}
