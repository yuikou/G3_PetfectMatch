package com.sitOrderDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SitODetailJDBCDAO implements SitODetailDAO_interface {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "EA103G3";
	private static final String pwd = "123456";

	private static final String INSERT_PSTMT = "INSERT INTO sitOrderDetail(sitOrderNo,sitSrvNo,petNo,sitOpPrice,sitSrvTimes,sitSrvUnit) VALUES(?,?,?,?,?,?)";
	private static final String GETBYPKFK_PSTMT = "SELECT sitOrderNo,sitSrvNo,petNo,sitOpPrice,sitSrvTimes,sitSrvUnit FROM sitOrderDetail WHERE sitOrderNo=?";
	private static final String GETALL_PSTMT = "SELECT * FROM sitOrderDetail";

	@Override
	public void insert(SitODetailVO sitODetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(INSERT_PSTMT);

			pstmt.setString(1, sitODetailVO.getSitOrderNo());
			pstmt.setString(2, sitODetailVO.getSitSrvNo());
			pstmt.setString(3, sitODetailVO.getPetNo());
			pstmt.setInt(4, sitODetailVO.getSitOpPrice());
			pstmt.setInt(5, sitODetailVO.getSitSrvTimes());
			pstmt.setString(6, sitODetailVO.getSitSrvUnit());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public SitODetailVO getByPKFK(String sitOrderNo) {

		SitODetailVO sitODetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(GETBYPKFK_PSTMT);

			pstmt.setString(1, sitOrderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				sitODetailVO = new SitODetailVO();
				sitODetailVO.setSitOrderNo(rs.getString("sitOrderNo"));
				sitODetailVO.setSitSrvNo(rs.getString("sitSrvNo"));
				sitODetailVO.setPetNo(rs.getString("petNo"));
				sitODetailVO.setSitOpPrice(rs.getInt("sitOpPrice"));
				sitODetailVO.setSitSrvTimes(rs.getInt("sitSrvTimes"));
				sitODetailVO.setSitSrvUnit(rs.getString("sitSrvUnit"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return sitODetailVO;

	}

	@Override
	public List<SitODetailVO> getAll() {

		List<SitODetailVO> list = new ArrayList<SitODetailVO>();
		SitODetailVO sitODetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(GETALL_PSTMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sitODetailVO = new SitODetailVO();
				sitODetailVO.setSitOrderNo(rs.getString("sitOrderNo"));
				sitODetailVO.setSitSrvNo(rs.getString("sitSrvNo"));
				sitODetailVO.setPetNo(rs.getString("petNo"));
				sitODetailVO.setSitOpPrice(rs.getInt("sitOpPrice"));
				sitODetailVO.setSitSrvTimes(rs.getInt("sitSrvTimes"));
				sitODetailVO.setSitSrvUnit(rs.getString("sitSrvUnit"));
				list.add(sitODetailVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//		SitODetailJDBCDAO dao = new SitODetailJDBCDAO();
//
//		// �s�W
//		SitODetailVO sitODetailVO = new SitODetailVO();
//		sitODetailVO.setSitOrderNo("SO010");
//		sitODetailVO.setSitSrvNo("SS006");
//		sitODetailVO.setPetNo("P013");
//		sitODetailVO.setSitOpPrice(450);
//		sitODetailVO.setSitSrvTimes(10);
//		sitODetailVO.setSitSrvUnit("��");
//		dao.insert(sitODetailVO);
//		
//
//		// �d��
//		SitODetailVO sitODetailVO = dao.getByPKFK("SO011");
//		System.out.print(sitODetailVO.getSitOrderNo() + ",");
//		System.out.print(sitODetailVO.getSitSrvNo() + ",");
//		System.out.print(sitODetailVO.getPetNo() + ",");
//		System.out.print(sitODetailVO.getSitOpPrice() + ",");
//		System.out.print(sitODetailVO.getSitSrvTimes() + ",");
//		System.out.print(sitODetailVO.getSitSrvUnit());
//
//		System.out.println("---------------------");
//
//		// �d�߳���
//		List<SitODetailVO> list = dao.getAll();
//		for (SitODetailVO sitOD : list) {
//			System.out.print(sitOD.getSitOrderNo() + ",");
//			System.out.print(sitOD.getSitSrvNo() + ",");
//			System.out.print(sitOD.getPetNo() + ",");
//			System.out.print(sitOD.getSitOpPrice() + ",");
//			System.out.print(sitOD.getSitSrvTimes() + ",");
//			System.out.print(sitOD.getSitSrvUnit());
//			System.out.println();
//		}
//		
//	}

}
