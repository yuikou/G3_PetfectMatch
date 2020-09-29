package com.sit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.petSitter.model.*;
import com.sitOrder.model.*;
import com.sitOrderDetail.model.*;

public class SitterServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
//		res.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = res.getWriter();

		String action = req.getParameter("action");

		if ("getOne".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				String sitNo = req.getParameter("sitNo");
				PetSitterService petSitSrv = new PetSitterService();
				PetSitterVO petSitterVO = petSitSrv.getByPK(sitNo);

				req.setAttribute("petSitterVO", petSitterVO);

				String url = "/sitter/listOneSitter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/sitter/sitterPage.jsp");
				failureView.forward(req, res);	
				
			}

		}

		if ("select_order".equals(action)) {

			String sitNo = req.getParameter("sitNo");
			SitOrderService sitOrderSrv = new SitOrderService();
			Set<SitOrderVO> sitOrderSet = sitOrderSrv.getByFK_sitNo(sitNo);

			req.setAttribute("sitOrderSet", sitOrderSet);

			String url = "/sitter/sitOrderPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("getOne_For_update".equals(action)) {

			String sitNo = req.getParameter("sitNo");
			PetSitterService petSitterSrv = new PetSitterService();
			PetSitterVO petSitterVO = petSitterSrv.getByPK(sitNo);

			req.setAttribute("petSitterVO", petSitterVO);
			String url = "/sitter/sitUpdatePage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		
		if ("update".equals(action)) {
			
			String sitNo = req.getParameter("sitNo").trim();
			String memNo = req.getParameter("memNo").trim();
			String sitInfo = req.getParameter("sitInfo").trim();
			String bankCode = req.getParameter("bankCode").trim();
			String bankAcc = req.getParameter("bankAcc").trim();
			String srvSTime = req.getParameter("srvSTime").trim();
			String srvETime = req.getParameter("srvETime").trim();
			
			Integer sitAccStatus = new Integer(req.getParameter("sitAccStatus").trim());
			Integer totalComm = new Integer(req.getParameter("totalComm").trim());
			Integer totalCus = new Integer(req.getParameter("totalCus").trim());
			Integer repeatCus = new Integer(req.getParameter("repeatCus").trim());
			
			PetSitterVO petSitterVO = new PetSitterVO();
			petSitterVO.setSitNo(sitNo);
			petSitterVO.setMemNo(memNo);
			petSitterVO.setSitInfo(sitInfo);
			petSitterVO.setSrvSTime(srvSTime);
			petSitterVO.setSrvETime(srvETime);
			petSitterVO.setBankCode(bankCode);
			petSitterVO.setBankAcc(bankAcc);
			petSitterVO.setSitAccStatus(sitAccStatus);
			petSitterVO.setTotalComm(totalComm);
			petSitterVO.setTotalCus(totalCus);
			petSitterVO.setRepeatCus(repeatCus);
			
			PetSitterService petSitterSrv = new PetSitterService();
			petSitterVO = petSitterSrv.update(sitNo,memNo,sitInfo,srvSTime,srvETime,bankCode,bankAcc,sitAccStatus,totalComm,totalCus,repeatCus);
			req.setAttribute("petSitterVO", petSitterVO);
			String url = "/sitter/listOneSitter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if ("select_orderDetail".equals(action)) {
			
			String sitOrderNo = req.getParameter("sitOrderNo");
			SitODetailService sitODetailSrv = new SitODetailService();
			SitODetailVO sitODetailVO = sitODetailSrv.getByPKFK(sitOrderNo);
			
			req.setAttribute("sitODetailVO", sitODetailVO);
			String url = "/sitter/sitODetailPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		
		if ("insert".equals(action)) {
			String memNo = req.getParameter("memNo").trim();
			String sitInfo = req.getParameter("sitInfo").trim();
			String bankCode = req.getParameter("bankCode").trim();
			String bankAcc = req.getParameter("bankAcc").trim();
			String srvSTime = req.getParameter("srvSTime").trim();
			String srvETime = req.getParameter("srvETime").trim();
			
			PetSitterVO petSitterVO = new PetSitterVO();
			petSitterVO.setMemNo(memNo);
			petSitterVO.setSitInfo(sitInfo);
			petSitterVO.setSrvSTime(srvSTime);
			petSitterVO.setSrvETime(srvETime);
			petSitterVO.setBankCode(bankCode);
			petSitterVO.setBankAcc(bankAcc);
			
			PetSitterService petSitterSrv = new PetSitterService();
			petSitterVO = petSitterSrv.insert(memNo,sitInfo,srvSTime,srvETime,bankCode,bankAcc);
			req.setAttribute("petSitterVO", petSitterVO);
			String url = "/sitter/listOneSitter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
			
		}
		
		
		
	}
}