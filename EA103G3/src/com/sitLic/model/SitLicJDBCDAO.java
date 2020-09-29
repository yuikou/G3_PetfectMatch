package com.sitLic.model;

import java.io.*;
import java.sql.*;
import java.util.*;

public class SitLicJDBCDAO implements SitLicDAO_interface {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "EA103G3";
	private static String passwd = "123456";

	private static final String ADD_PSTMT = "INSERT INTO sitLic VALUES ('SL' || lpad(licNo_seq.NEXTVAL, 3, '0'), ?, ?, ?, ?, ?)";
	private static final String UPDATE_PSTMT = "UPDATE sitLic SET LICNAME=?, LICPIC=?, LICEXP=?, LICSTATUS=? WHERE LICNO=?";
	private static final String GET_ALL_LIC = "SELECT * FROM sitLic WHERE SITNO=? ORDER BY LICNO";
	private static final String GET_ONE_LIC = "SELECT * FROM sitLic WHERE LICNO=?";
	private static final String GET_UNVERIFIED_LIC = "SELECT * FROM sitLic WHERE LICSTATUS=?";
	
	@Override
	public void add(SitLicVO sitLic) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(ADD_PSTMT);
			pstmt.setString(1, sitLic.getSitNo());
			pstmt.setString(2, sitLic.getLicName());
			pstmt.setBytes(3, sitLic.getLicPic());
			pstmt.setDate(4, sitLic.getLicEXP());
			pstmt.setInt(5, sitLic.getLicStatus());

			pstmt.executeUpdate();
			con.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			// console�L�X�ҥ~��]��m�A�ӤH����ndebug
			e.printStackTrace();
			try {
				con.rollback();
				throw new RuntimeException("�s�W���ѡG " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
					// �̫᪺�ҥ~��Exception�����A�קK�o��Driver���������~
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(SitLicVO sitLic) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, passwd);
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(UPDATE_PSTMT);
			pstmt.setString(1, sitLic.getLicName());
			pstmt.setBytes(2, sitLic.getLicPic());
			pstmt.setDate(3, sitLic.getLicEXP());
			pstmt.setInt(4, sitLic.getLicStatus());
			pstmt.setString(5, sitLic.getLicNo());

			if (pstmt.executeUpdate() == 1) {
				System.out.println("�W�Ǧ��\");
			} 
			con.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
				throw new RuntimeException("�ק異�ѡG " + e.getMessage());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
	}

	@Override
	public SitLicVO getOneLicByPK(String licNo) {
		SitLicVO sitLic = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(GET_ONE_LIC);
			pstmt.setString(1, licNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sitLic = new SitLicVO();
				sitLic.setLicNo(rs.getString("LICNO"));
				sitLic.setSitNo(rs.getString("SITNO"));
				sitLic.setLicName(rs.getString("LICNAME"));
				sitLic.setLicPic(rs.getBytes("LICPIC"));
				sitLic.setLicEXP(rs.getDate("LICEXP"));
				sitLic.setLicStatus(rs.getInt("LICSTATUS"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("�d�ߥ��ѡG " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return sitLic;
	}

	@Override
	public List<SitLicVO> getSitAllLic(String sitNo) {
		List<SitLicVO> list = new ArrayList<SitLicVO>();
		SitLicVO sitLic = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(GET_ALL_LIC);
			pstmt.setString(1, sitNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sitLic = new SitLicVO();
				sitLic.setLicNo(rs.getString("LICNO"));
				sitLic.setSitNo(rs.getString("SITNO"));
				sitLic.setLicName(rs.getString("LICNAME"));
				sitLic.setLicPic(rs.getBytes("LICPIC"));
				sitLic.setLicEXP(rs.getDate("LICEXP"));
				sitLic.setLicStatus(rs.getInt("LICSTATUS"));
				list.add(sitLic);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("�d�ߥ��ѡG " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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

	@Override
	public List<SitLicVO> getAllForEmp(Integer licStatus) {
		List<SitLicVO> list = new ArrayList<SitLicVO>();
		SitLicVO sitLic = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(GET_UNVERIFIED_LIC);
			pstmt.setInt(1, licStatus);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sitLic = new SitLicVO();
				sitLic.setLicNo(rs.getString("LICNO"));
				sitLic.setSitNo(rs.getString("SITNO"));
				sitLic.setLicName(rs.getString("LICNAME"));
				sitLic.setLicPic(rs.getBytes("LICPIC"));
				sitLic.setLicEXP(rs.getDate("LICEXP"));
				sitLic.setLicStatus(rs.getInt("LICSTATUS"));
				list.add(sitLic);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("�d�ߥ��ѡG " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
	
	public static void main(String[] args) {
		SitLicJDBCDAO jdbcdao = new SitLicJDBCDAO();

		// �s�W
//		SitLicVO VO1 = new SitLicVO();
//		VO1.setSitNo("S003");
//		VO1.setLicName("�S�w�d���~�\�i��");
//		VO1.setLicPic(null);
//		VO1.setLicEXP(Date.valueOf("2022-09-22"));
//		VO1.setLicStatus(0);
//		jdbcdao.add(VO1);

		// �ק�
//		SitLicVO VO2 = new SitLicVO();
//		VO2.setLicName("�S�w�d���~�\�i��");
//		// �qfile���X�s�JDB
//		byte[] pic;
//		try {
//			pic = getPictureByteArray("E:/�M�D/�M�D�m��/DAO_practice/�ҷ�/�S�w�d���~�\�i��(�ӤH).jpg");
//		} catch (IOException e) {
//			e.printStackTrace();
//			throw new RuntimeException("�Ϥ��S����G " + e.getMessage());
//		}
//		VO2.setLicPic(pic);
//		VO2.setLicEXP(null);
//		VO2.setLicStatus(0);
//		VO2.setLicNo("SL004");
//		jdbcdao.update(VO2);

		// �ק�
//		byte[] pic = null;
//		try {
//			pic = getPictureByteArray("E:/�M�D/�M�D�m��/DAO_practice/�ҷ�/�S�w�d���~�\�i��(�ӤH2).jpg");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		SitLicVO VO4 = new SitLicVO();
//		VO4.setLicPic(pic);
//		VO4.setLicNo("SL004");
//		jdbcdao.update2(VO4);
		
		// �d��one
		SitLicVO VO3 = jdbcdao.getOneLicByPK("SL003");
		System.out.println(VO3.getLicNo());
		System.out.println(VO3.getLicName());
		System.out.println(VO3.getSitNo());
		System.out.println(VO3.getLicEXP() != null ? VO3.getLicEXP() : "�L����");
		System.out.println(VO3.getLicStatus());

		// �d��SitAll
		List<SitLicVO> list = new ArrayList<SitLicVO>();
		list = jdbcdao.getSitAllLic("S001");
		System.out.println("-------------------------------");
		if (list.size() < 1) {
			System.out.println("�d�L���");
		}
		for (SitLicVO VO : list) {
			System.out.println(VO.getSitNo() + "��LicNo =  " + VO.getLicNo());
			System.out.println(VO.getSitNo() + "��LicName = " + VO.getLicName());
			System.out.println(VO.getSitNo() + "��LicEXP = " + (VO.getLicEXP() != null ? VO.getLicEXP() : "�L����"));
			System.out.println(VO.getSitNo() + "��LicStatus = " + VO.getLicStatus());
		}
		System.out.println("-------------------------------");
		
		// �d�߭n�f�֪�
		List<SitLicVO> list2 = new ArrayList<SitLicVO>();
		list2 = jdbcdao.getAllForEmp(0);
		System.out.println("-------------------------------");
		if (list.size() < 1) {
			System.out.println("�d�L���");
		}
		for (SitLicVO VO : list2) {
			System.out.println(VO.getSitNo() + "��LicNo =  " + VO.getLicNo());
			System.out.println(VO.getSitNo() + "��sitNo =  " + VO.getSitNo());
			System.out.println(VO.getSitNo() + "��LicName = " + VO.getLicName());
			System.out.println(VO.getSitNo() + "��LicEXP = " + (VO.getLicEXP() != null ? VO.getLicEXP() : "�L����"));
			System.out.println(VO.getSitNo() + "��LicStatus = " + VO.getLicStatus());
		}
		System.out.println("-------------------------------");
		
	}

	
	// �ϥ�file�W��byte[]�覡
	public static byte[] getPictureByteArray(String path) throws IOException {
		File f = new File(path);
		FileInputStream fis = new FileInputStream(f);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[fis.available()];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
			baos.flush();
		}
		baos.close();
		fis.close();
		
		return baos.toByteArray();
	}

}
