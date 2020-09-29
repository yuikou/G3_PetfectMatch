package com.salon.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SalonServlet")
public class SalonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				String salName = req.getParameter("salName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";				
				if(salName == null ||salName.trim().length() == 0) {
					errorMsgs.add("美容店店名請勿空白");
				}else if (!salName.trim().matches(enameReg)) {
					errorMsgs.add("美容店店名只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String salOwner = req.getParameter("salOwner");
				String salOwnerReg = "^[(\u4e00-\u9fa5) {2,10}]$";				
				if(salOwner == null || salOwner.trim().length() ==0) {
					errorMsgs.add("負責人姓名請勿空白");
				}else if(!salOwner.trim().matches(salOwnerReg)){
					errorMsgs.add("負責人姓名: 只能是中文字母,且長度必需在2到10之間");
				}
				
				String salPh = req.getParameter("salPh");
				String salPhReg = "^[(0-9)] {6,10}$";
				
				if(salPh ==null ||salPh.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}else if(!salPh.trim().matches(salPhReg)) {
					errorMsgs.add("輸入正確電話");
				}
				
				String salMail =req.getParameter("salMail");
				
				if(salMail == null || salMail.trim().length() ==0) {
					errorMsgs.add("電子郵件請勿空白");
				}
				
				
				
				
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/addEmp.jsp");
				failureView.forward(req, res);		
				}
		
		}
		}
}

	
		
	

