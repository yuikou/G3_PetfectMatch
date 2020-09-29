package com.sitFollow.controller;

import java.io.*;
import java.sql.Date;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.sitLic.model.*;

public class SitFollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// �B�z����ШD
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

/* �Ӧ�xxx.jsp���ШD - �d�ݩҦ��l�ܪ��O�i  */
		if ("showAll".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				/* sitNo */
				String sitNo = (String) session.getAttribute("sitNo");
				
				/*************************** 2.�}�l�s�W��� ***************************************/
				SitLicService slSvc = new SitLicService();
				List<SitLicVO> list = slSvc.getSitAllLic(sitNo);
	
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("list", list);
				// ��e
				String url = "listOneSitAllLic.jsp";
				RequestDispatcher sucessView = req.getRequestDispatcher(url);
				sucessView.forward(req, res);
				
			/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("addSitLic.jsp");
				failureView.forward(req, res);
			}
		}
		
/* �Ӧ�xxx.jsp���ШD - �s�W�l�ܫO�i  */
		if ("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				/* 1-sitNo */
				String sitNo = (String) session.getAttribute("sitNo");
				
				/* 2-licName */
				String licName = req.getParameter("licName").trim();
				if (licName == null || licName.trim().length() == 0) {
					errorMsgs.add("�п�J�ҮѦW��");
				}
				// �p�G�S��J�N���^�ǿ��~��T
				if (! errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("addSitLic.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/* 3-licEXP */
				Date licEXP = null;
				try {
					String licEXPStr = req.getParameter("licEXP");
					licEXP = Date.valueOf(licEXPStr.trim());
					
				} catch (IllegalArgumentException e) {
					licEXP = null;
				}
				
				
				/* 4-licStatus */
				Integer licStatus = 0;// �s�W�ɹw�]���A0-�ݼf��
				
				
				/* 5-licPic */
				Collection<Part> parts = req.getParts();
				
				byte[] licPic = null;
				for (Part p : parts) {
					if (p.getContentType() != null) {
						InputStream is = p.getInputStream();
						licPic = new byte[is.available()];
						is.read(licPic);
						is.close();
					}
				}
				if ( licPic == null || licPic.length == 0) {
					errorMsgs.add("�ФW���ҷӹ���");
				}
				// �p�G�S�W�ǹϤ��N���^�ǿ��~��T
				if (! errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("addSitLic.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.�}�l�s�W��� ***************************************/
				SitLicService slSvc = new SitLicService();
				slSvc.add(sitNo, licName, licPic, licEXP, licStatus);
	
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "listOneSitAllLic.jsp";
				RequestDispatcher sucessView = req.getRequestDispatcher(url);
				sucessView.forward(req, res);
				
			/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("addSitLic.jsp");
				failureView.forward(req, res);
			}
		}
		
		
/* �Ӧ�xxx.jsp���ШD - �R���l�ܫO�i */
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				/* licNo */
				String licNo = req.getParameter("licNo");
				
				/*************************** 2.�}�l�s�W��� ***************************************/
				SitLicService slSvc = new SitLicService();
				SitLicVO sitLicVO = slSvc.getOneLicByPK(licNo);
		
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				req.setAttribute("sitLicVO", sitLicVO);
				
				String url = "updateSitLic.jsp";
				RequestDispatcher sucessView = req.getRequestDispatcher(url);
				sucessView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("listOneSitAllLic.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
