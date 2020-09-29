package com.petSitter.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetSitterJDBCDAO implements PetSitterDAO_interface {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "EA103G3";
	private static final String pwd = "123456";

	private static final String INSERT_PSTMT = "INSERT INTO petSitter(sitNo, memNo, sitInfo, "
			+ "srvSTime, srvETime, bankCode, bankAcc, sitAccStatus, totalComm, totalCus, repeatCus) VALUES ("
			+ "'S' || lpad(sitRep_SEQ.NEXTVAL, 3, '0'),?,?,?,?,?,?,0,0,0,0)";
	private static final String UPDATE_PSTMT = "UPDATE petSitter SET memNo=?, sitInfo=?, "
			+ "srvSTime=? , srvETime=?, bankCode=?, bankAcc=?, sitAccStatus=?, totalComm=?, totalCus=?, repeatCus=? WHERE sitNo=?";
	private static final String GETbyPK_PSTMT = "SELECT sitNo, memNo, sitInfo, bankCode, srvSTime, srvETime,"
			+ "bankAcc, sitAccStatus, totalComm, totalCus, repeatCus FROM petSitter WHERE sitNo=?";
	private static final String GETALL_PSTMT = "SELECT * FROM petSitter";

	@Override
	public void insert(PetSitterVO petSitterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(INSERT_PSTMT);

			pstmt.setString(1, petSitterVO.getMemNo());
			pstmt.setString(2, petSitterVO.getSitInfo());
			pstmt.setString(3, petSitterVO.getSrvSTime());
			pstmt.setString(4, petSitterVO.getSrvETime());
			pstmt.setString(5, petSitterVO.getBankCode());
			pstmt.setString(6, petSitterVO.getBankAcc());

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
	public void update(PetSitterVO petSitterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(UPDATE_PSTMT);

			pstmt.setString(1, petSitterVO.getMemNo());
			pstmt.setString(2, petSitterVO.getSitInfo());
			pstmt.setString(3, petSitterVO.getSrvSTime());
			pstmt.setString(4, petSitterVO.getSrvETime());
			pstmt.setString(5, petSitterVO.getBankCode());
			pstmt.setString(6, petSitterVO.getBankAcc());
			pstmt.setInt(7, petSitterVO.getSitAccStatus());
			pstmt.setInt(8, petSitterVO.getTotalComm());
			pstmt.setInt(9, petSitterVO.getTotalCus());
			pstmt.setInt(10, petSitterVO.getRepeatCus());
			pstmt.setString(11, petSitterVO.getSitNo());

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
	public PetSitterVO getByPK(String sitNo) {
		PetSitterVO petSitterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(GETbyPK_PSTMT);

			pstmt.setString(1, sitNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				petSitterVO = new PetSitterVO();
				petSitterVO.setSitNo(rs.getString("sitNo"));
				petSitterVO.setMemNo(rs.getString("memNo"));
				petSitterVO.setSitInfo(rs.getString("sitInfo"));
				petSitterVO.setSrvSTime(rs.getString("srvSTime"));
				petSitterVO.setSrvETime(rs.getString("srvETime"));
				petSitterVO.setBankCode(rs.getString("bankCode"));
				petSitterVO.setBankAcc(rs.getString("bankAcc"));
				petSitterVO.setSitAccStatus(rs.getInt("sitAccStatus"));
				petSitterVO.setTotalComm(rs.getInt("totalComm"));
				petSitterVO.setTotalCus(rs.getInt("totalCus"));
				petSitterVO.setRepeatCus(rs.getInt("repeatCus"));
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
		return petSitterVO;
	}

	@Override
	public List<PetSitterVO> getAll() {

		List<PetSitterVO> list = new ArrayList<PetSitterVO>();
		PetSitterVO petSitterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(GETALL_PSTMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				petSitterVO = new PetSitterVO();
				petSitterVO.setSitNo(rs.getString("sitNo"));
				petSitterVO.setMemNo(rs.getString("memNo"));
				petSitterVO.setSitInfo(rs.getString("sitInfo"));
				petSitterVO.setSrvSTime(rs.getString("srvSTime"));
				petSitterVO.setSrvETime(rs.getString("srvETime"));
				petSitterVO.setBankCode(rs.getString("bankCode"));
				petSitterVO.setBankAcc(rs.getString("bankAcc"));
				petSitterVO.setSitAccStatus(rs.getInt("sitAccStatus"));
				petSitterVO.setTotalComm(rs.getInt("totalComm"));
				petSitterVO.setTotalCus(rs.getInt("totalCus"));
				petSitterVO.setRepeatCus(rs.getInt("repeatCus"));
				list.add(petSitterVO);
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
//		PetSitterJDBCDAO dao = new PetSitterJDBCDAO();
//
//		// �s�W
//		PetSitterVO petSitterVO = new PetSitterVO();
//		petSitterVO.setMemNo("M002");
//		petSitterVO.setSitInfo("�ڬO�@����R�ʪ����H�A���i�L�ߤl�M�������g��C�ۤp�H�Ӯa���N���}�i�����A�]���絛�������U���״I���g��A�w�g���������U15�~�H�W���g��C�ثe�a������i�Ⱖ�����A�������Q���M�̮�|�C");
//		petSitterVO.setSrvSTime("0800");
//		petSitterVO.setSrvETime("1900");		
//		petSitterVO.setBankCode("811");
//		petSitterVO.setBankAcc("9999999999999999");
//		dao.insert(petSitterVO);
//
//		// �ק�
//		PetSitterVO petSitterVO = new PetSitterVO();
//		petSitterVO.setMemNo("M009");
//		petSitterVO.setSitInfo("�ڶW�ų��w�����߫}�A�]ı�o���U�o�̬O��ܶ}�ߪ��ơA�ڤ]�֦��D�`�h�d���������g���A�ڬ۫H�گ����z���d���_�����U���D�`�n�C");
//		petSitterVO.setSrvSTime("0800");
//		petSitterVO.setSrvETime("1900");		
//		petSitterVO.setBankCode("062");
//		petSitterVO.setBankAcc("4776577899099999");
//		petSitterVO.setSitAccStatus(0);
//		petSitterVO.setTotalComm(48);
//		petSitterVO.setTotalCus(10);
//		petSitterVO.setRepeatCus(3);
//		petSitterVO.setSitNo("S006");
//		dao.update(petSitterVO);
//
//		// �d��
//		PetSitterVO petSitterVO = dao.getByPK("S001");
//		System.out.println(petSitterVO.getMemNo() + ",");
//		System.out.println(petSitterVO.getSitInfo() + ",");
//		System.out.println(petSitterVO.getSrvSTime() + ",");
//		System.out.println(petSitterVO.getSrvETime() + ",");
//		System.out.println(petSitterVO.getBankCode() + ",");
//		System.out.println(petSitterVO.getBankAcc() + ",");
//		System.out.println(petSitterVO.getSitAccStatus() + ",");
//		System.out.println(petSitterVO.getTotalComm() + ",");
//		System.out.println(petSitterVO.getTotalCus() + ",");
//		System.out.println(petSitterVO.getRepeatCus());
//
//		// �d��
//		List<PetSitterVO> list = dao.getAll();
//		for (PetSitterVO petSit : list) {
//			System.out.print(petSit.getSitNo() + ",");
//			System.out.print(petSit.getMemNo() + ",");
//			System.out.print(petSit.getSitInfo() + ",");
//			System.out.print(petSit.getSrvSTime() + ",");
//			System.out.print(petSit.getSrvETime() + ",");
//			System.out.print(petSit.getBankCode() + ",");
//			System.out.print(petSit.getBankAcc() + ",");
//			System.out.print(petSit.getSitAccStatus() + ",");
//			System.out.print(petSit.getTotalComm() + ",");
//			System.out.print(petSit.getTotalCus() + ",");
//			System.out.print(petSit.getRepeatCus());
//			System.out.println();
//		}
//	}
}
