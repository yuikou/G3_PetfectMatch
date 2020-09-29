package com.sitRep.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SitRepJNDIDAO implements SitRepDAO_interface{
	
	private static DataSource ds = null;
	static {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PSTMT);
			
			pstmt.setString(1, sitRepVO.getSitOrderNo());
			pstmt.setString(2, sitRepVO.getMemNo());
			pstmt.setString(3, sitRepVO.getEmpNo());
			pstmt.setString(4, sitRepVO.getSitRep());
			pstmt.setInt(5, sitRepVO.getSitRepStatus());
			pstmt.setInt(6, sitRepVO.getSitRepResult());
			
			pstmt.executeUpdate();
			
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PSTMT);
			
			pstmt.setString(1, sitRepVO.getSitOrderNo());
			pstmt.setString(2, sitRepVO.getMemNo());
			pstmt.setString(3, sitRepVO.getEmpNo());
			pstmt.setString(4, sitRepVO.getSitRep());
			pstmt.setInt(5, sitRepVO.getSitRepStatus());
			pstmt.setInt(6, sitRepVO.getSitRepResult());
			pstmt.setString(7, sitRepVO.getSitRepNo());


			pstmt.executeUpdate();			
			
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

			con = ds.getConnection();

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_BySitRepNo_PSTMT);
			pstmt.setString(1, sitRepNo);
			pstmt.executeUpdate();


			con.commit();

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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			
			con = ds.getConnection();
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

}
