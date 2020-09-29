package com.sitOrder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SitOrderDAO implements SitOrderDAO_interface{
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
	
	private static final String INSERT_PSTMT = "INSERT INTO sitOrder(sitOrderNo, memNo, sitNo, sitSDate, sitEDate, "
			+ "totalPrice, orderStatus, refund, coupon, commStar, sitComm, pickupFrom, pickupTo) "
			+ "VALUES('SO' || lpad(sitOrder_SEQ.NEXTVAL, 3, '0'),?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_PSTMT = "UPDATE sitOrder SET memNo=?, sitNo=?, sitSDate=?, sitEDate=?, "
			+ "totalPrice=?,orderStatus=?,refund=?,coupon=?,commStar=?,sitComm=?,pickupFrom=?,pickupTo=? WHERE sitOrderNo=?";
	
	private static final String GET_BY_PK_PSTMT = "SELECT sitOrderNo, memNo, sitNo, sitSDate, sitEDate, "
			+ "totalPrice, orderStatus, refund, coupon, commStar, sitComm, pickupFrom, pickupTo FROM sitOrder WHERE sitOrderNo=?";
	private static final String GET_BY_MEMNO_PSTMT = "SELECT sitOrderNo, memNo, sitNo, sitSDate, sitEDate, "
			+ "totalPrice, orderStatus, refund, coupon, commStar, sitComm, pickupFrom, pickupTo FROM sitOrder WHERE memNo=?";
	private static final String GET_BY_SITNO_PSTMT = "SELECT sitOrderNo, memNo, sitNo, sitSDate, sitEDate, "
			+ "totalPrice, orderStatus, refund, coupon, commStar, sitComm, pickupFrom, pickupTo FROM sitOrder WHERE sitNo=?";
	
	private static final String GETALL_PSTMT = "SELECT * FROM sitOrder";

	
	@Override
	public void insert(SitOrderVO sitOrderVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PSTMT);
			
			pstmt.setString(1,sitOrderVO.getMemNo());
			pstmt.setString(2,sitOrderVO.getSitNo());
			pstmt.setDate(3,sitOrderVO.getSitSDate());
			pstmt.setDate(4,sitOrderVO.getSitEDate());
			pstmt.setInt(5,sitOrderVO.getTotalPrice());
			pstmt.setInt(6,sitOrderVO.getOrderStatus());
			pstmt.setInt(7,sitOrderVO.getRefund());
			pstmt.setInt(8,sitOrderVO.getCoupon());
			pstmt.setInt(9,sitOrderVO.getCommStar());
			pstmt.setString(10,sitOrderVO.getSitComm());
			pstmt.setString(11,sitOrderVO.getPickupFrom());
			pstmt.setString(12,sitOrderVO.getPickupTo());
			
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
	public void update(SitOrderVO sitOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PSTMT);
			
			pstmt.setString(1,sitOrderVO.getMemNo());
			pstmt.setString(2,sitOrderVO.getSitNo());
			pstmt.setDate(3,sitOrderVO.getSitSDate());
			pstmt.setDate(4,sitOrderVO.getSitEDate());
			pstmt.setInt(5,sitOrderVO.getTotalPrice());
			pstmt.setInt(6,sitOrderVO.getOrderStatus());
			pstmt.setInt(7,sitOrderVO.getRefund());
			pstmt.setInt(8,sitOrderVO.getCoupon());
			pstmt.setInt(9,sitOrderVO.getCommStar());
			pstmt.setString(10,sitOrderVO.getSitComm());
			pstmt.setString(11,sitOrderVO.getPickupFrom());
			pstmt.setString(12,sitOrderVO.getPickupTo());
			pstmt.setString(13, sitOrderVO.getSitOrderNo());

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
	public SitOrderVO getByPK(String sitOrderNo) {
		
		SitOrderVO sitOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_PK_PSTMT);

			pstmt.setString(1, sitOrderNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sitOrderVO = new SitOrderVO();
				sitOrderVO.setSitOrderNo(rs.getString("sitOrderNo"));
				sitOrderVO.setMemNo(rs.getString("memNo"));
				sitOrderVO.setSitNo(rs.getString("sitNo"));
				sitOrderVO.setSitSDate(rs.getDate("sitSDate"));
				sitOrderVO.setSitEDate(rs.getDate("sitEDate"));
				sitOrderVO.setTotalPrice(rs.getInt("totalPrice"));
				sitOrderVO.setOrderStatus(rs.getInt("orderStatus"));
				sitOrderVO.setRefund(rs.getInt("refund"));
				sitOrderVO.setCoupon(rs.getInt("coupon"));
				sitOrderVO.setCommStar(rs.getInt("commStar"));
				sitOrderVO.setSitComm(rs.getString("sitComm"));
				sitOrderVO.setPickupFrom(rs.getString("pickupFrom"));
				sitOrderVO.setPickupTo(rs.getString("pickupTo"));

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
		return sitOrderVO;
	}

	public Set<SitOrderVO> getByFK_memNo(String memNo){
		Set<SitOrderVO> set = new LinkedHashSet<SitOrderVO>();
		SitOrderVO sitOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEMNO_PSTMT);		
			pstmt.setString(1, memNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sitOrderVO = new SitOrderVO();
				sitOrderVO.setSitOrderNo(rs.getString("sitOrderNo"));
				sitOrderVO.setMemNo(rs.getString("memNo"));
				sitOrderVO.setSitNo(rs.getString("sitNo"));
				sitOrderVO.setSitSDate(rs.getDate("sitSDate"));
				sitOrderVO.setSitEDate(rs.getDate("sitEDate"));
				sitOrderVO.setTotalPrice(rs.getInt("totalPrice"));
				sitOrderVO.setOrderStatus(rs.getInt("orderStatus"));
				sitOrderVO.setRefund(rs.getInt("refund"));
				sitOrderVO.setCoupon(rs.getInt("coupon"));
				sitOrderVO.setCommStar(rs.getInt("commStar"));
				sitOrderVO.setSitComm(rs.getString("sitComm"));
				sitOrderVO.setPickupFrom(rs.getString("pickupFrom"));
				sitOrderVO.setPickupTo(rs.getString("pickupTo"));
				set.add(sitOrderVO);
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
		return set;
	}
	
	@Override
	public Set<SitOrderVO> getByFK_sitNo(String sitNo) {
		Set<SitOrderVO> set = new LinkedHashSet<SitOrderVO>();
		SitOrderVO sitOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_SITNO_PSTMT);		
			pstmt.setString(1, sitNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sitOrderVO = new SitOrderVO();
				sitOrderVO.setSitOrderNo(rs.getString("sitOrderNo"));
				sitOrderVO.setMemNo(rs.getString("memNo"));
				sitOrderVO.setSitNo(rs.getString("sitNo"));
				sitOrderVO.setSitSDate(rs.getDate("sitSDate"));
				sitOrderVO.setSitEDate(rs.getDate("sitEDate"));
				sitOrderVO.setTotalPrice(rs.getInt("totalPrice"));
				sitOrderVO.setOrderStatus(rs.getInt("orderStatus"));
				sitOrderVO.setRefund(rs.getInt("refund"));
				sitOrderVO.setCoupon(rs.getInt("coupon"));
				sitOrderVO.setCommStar(rs.getInt("commStar"));
				sitOrderVO.setSitComm(rs.getString("sitComm"));
				sitOrderVO.setPickupFrom(rs.getString("pickupFrom"));
				sitOrderVO.setPickupTo(rs.getString("pickupTo"));
				set.add(sitOrderVO);
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
		return set;
	}

	@Override
	public List<SitOrderVO> getAll() {


		List<SitOrderVO> list = new ArrayList<SitOrderVO>();
		SitOrderVO sitOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL_PSTMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sitOrderVO = new SitOrderVO();
				sitOrderVO.setSitOrderNo(rs.getString("sitOrderNo"));
				sitOrderVO.setMemNo(rs.getString("memNo"));
				sitOrderVO.setSitNo(rs.getString("sitNo"));
				sitOrderVO.setSitSDate(rs.getDate("sitSDate"));
				sitOrderVO.setSitEDate(rs.getDate("sitEDate"));
				sitOrderVO.setTotalPrice(rs.getInt("totalPrice"));
				sitOrderVO.setOrderStatus(rs.getInt("orderStatus"));
				sitOrderVO.setRefund(rs.getInt("refund"));
				sitOrderVO.setCoupon(rs.getInt("coupon"));
				sitOrderVO.setCommStar(rs.getInt("commStar"));
				sitOrderVO.setSitComm(rs.getString("sitComm"));
				sitOrderVO.setPickupFrom(rs.getString("pickupFrom"));
				sitOrderVO.setPickupTo(rs.getString("pickupTo"));
				list.add(sitOrderVO);
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
