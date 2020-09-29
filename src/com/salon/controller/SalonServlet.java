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
					errorMsgs.add("���e�����W�ФŪť�");
				}else if (!salName.trim().matches(enameReg)) {
					errorMsgs.add("���e�����W�u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				String salOwner = req.getParameter("salOwner");
				String salOwnerReg = "^[(\u4e00-\u9fa5) {2,10}]$";				
				if(salOwner == null || salOwner.trim().length() ==0) {
					errorMsgs.add("�t�d�H�m�W�ФŪť�");
				}else if(!salOwner.trim().matches(salOwnerReg)){
					errorMsgs.add("�t�d�H�m�W: �u��O����r��,�B���ץ��ݦb2��10����");
				}
				
				String salPh = req.getParameter("salPh");
				String salPhReg = "^[(0-9)] {6,10}$";
				
				if(salPh ==null ||salPh.trim().length() == 0) {
					errorMsgs.add("�q�ܽФŪť�");
				}else if(!salPh.trim().matches(salPhReg)) {
					errorMsgs.add("��J���T�q��");
				}
				
				String salMail =req.getParameter("salMail");
				
				if(salMail == null || salMail.trim().length() ==0) {
					errorMsgs.add("�q�l�l��ФŪť�");
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

	
		
	

