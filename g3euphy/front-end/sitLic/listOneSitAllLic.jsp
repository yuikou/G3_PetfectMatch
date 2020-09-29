<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.sitLic.model.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- �s���������ۮe�� -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<TITLE>�O�i���Ҧ��ҷ�</TITLE>

<!-- �פJ�~��CSS -->
<c:set var="path" value="/EA103G3/front-end/sitLic" />

<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css">   
<link rel="stylesheet" type="text/css" href="${path}/css/Main.css">
<link rel="stylesheet" type="text/css" href="${path}/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${path}/css/animate.css">
<link rel="stylesheet" type="text/css" href="${path}/css/animsition.min.css">
<link rel="stylesheet" type="text/css" href="${path}/css/util.css">
<link rel="stylesheet" type="text/css" href="${path}/css/Petfect.css">
<link rel="stylesheet" type="text/css" href="${path}/css/pitSitterForm.css">
<link rel="stylesheet" type="text/css" href="${path}/css/sitLicAll.css">
<link rel="Shortcut Icon" type="image/x-icon" href="https://dzmg8959fhe1k.cloudfront.net/all/favicon.ico">

</head>

<BODY>

<!-- �H�U������navigation bar -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light myNav">
        <div class="container-fluid">
            <!-- logo (click to home page)-->
            <a class="navbar-brand" href="#" style="padding-top: 0px;padding-bottom: 0px"><img class="myLogo"
                    src="https://dzmg8959fhe1k.cloudfront.net/all/logo.jpg"><img class="myLogoWord"
                    src="https://dzmg8959fhe1k.cloudfront.net/all/logoWord.jpg"></a>
            <!-- logo -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation"
                style="margin-right: 15px">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="myNavDiv collapse navbar-collapse" id="navbarText">
                <!-- �W�s���s��U�A�ȷj�M���� -->
                <ul class="navbar-nav ml-auto">
	                <div class="myUndeLine">
	                    <li class="nav-item">
                            <a class="nav-link accordion myFirst" href="#"><span class="ch-word">�d���A��</span><span
                                    class="en-word">Services</span></a>
                            <a class="nav-link morelink" href="#">���i</a>
                            <a class="nav-link morelink" href="#">���e</a>
                            <a class="nav-link morelink" href="#">��i</a>
                    	</li>
        	        </div>
                   	<div class="myUndeLine">
                    	<li class="nav-item">
                            <a class="nav-link accordion myFirst" href="#"><span class="ch-word">�d�ͤ���</span><span
                                    class="en-word">Interaction</span></a>
                            <a class="nav-link morelink" href="#">�Q�װ�</a>
                            <a class="nav-link morelink" href="#">����</a>
                    	</li>
                    </div>
                    <div class="myUndeLine">
                    	<li class="nav-item">
                            <a class="nav-link accordion" href="#"><span class="ch-word">���e�~�̱M��</span><span
                                    class="en-word">Salon store</span></a>
                    	</li>
                    </div>
                </ul>
                <!-- �H�W�W�s���s��U�A�ȷj�M���� -->
                <!-- �n�J���s -->
                <input type="button" class="myBtn" value="sign in"></input>
                <!-- �H�W�n�Jbtn -->
            </div>
        </div>
    </nav>
    <!-- ����navigation bar end-->
    
    
<!-- ����body -->
    <div class="container">
		
		<!-- ���~�C�� -->
	   	<div class="errorList"> 
			<c:if test="${not empty errorMsgs}" >
				<font style="color:red;">�o�ͥH�U���~�G</font>
				<ul>
				<c:forEach var="msg" items="${errorMsgs}">
					<li style="color:red;">${msg}</li>
				</c:forEach>
				</ul>
			</c:if>
		</div>
	
		<!-- ���ե� -->
		<div class="test" style="margin:40px;left:400px;">
			<a href="addSitLic.jsp">�s�W�Ү�</a>
		</div>
		<!-- ���ե� -->
		
		<div class="backFromDB rwd-table">
			<table class="licTable">
				<tr>
					<th style="min-width:auto;">NO.</th>
					<th>�ҷӦW��</th>
					<th>�ҷӨ����</th>
					<th>�ҷӪ��A</th>
					<th>�ҷӹϤ�</th>
					<th>�ק�<span class="warrning">*</span></th>
				</tr>
				<% 
					String sitNo = (String) session.getAttribute("sitNo");
					SitLicService slSvc = new SitLicService();
					List<SitLicVO> list = slSvc.getSitAllLic(sitNo);
					pageContext.setAttribute("list", list);
				%>
				<c:forEach var="sitLic" items="${list}" varStatus="no">
					<tr>
						<td data-th="no">${no.count}</td>
						<td data-th="�ҷӦW��">${sitLic.licName}</td>
						<td data-th="�ҷӨ����"><c:out value="${sitLic.licEXP}" default="-"/></td>
						<c:set var="licStatus" value="${sitLic.licStatus}"/>
						<% 
							String[] statusArr = {"�f�֤�", "�f�ֳq�L", "�f�֥��q�L", "�L���ҷ�"};
							String status = statusArr[(Integer)pageContext.getAttribute("licStatus")];
							pageContext.setAttribute("status", status);
						%>
						<td data-th="�ҷӪ��A">${status}</td>
						<td data-th="�ҷӹϤ�" class="myLicPic"><img class="licPicImg" alt="" src="${pageContext.request.contextPath}/ShowImg?action=sitLic&licNo=${sitLic.licNo}"></td>
						<td data-th="�ק�">
							<FORM method="post" action="sitLic.do">
								<input class="updBtn" type="submit" value="�ק�">
								<input type="hidden" name="licNo" value="${sitLic.licNo}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<!-- ����end -->
    <!-- �ȪA�p���s -->
    <div class="qaicon">
        <a href="#"><img src="https://dzmg8959fhe1k.cloudfront.net/all/unicorn.svg" /></a>
    </div>
    <!-- footer -->
    <div class="container-fluid main-footer text-center">
        <div class="row myFooterContainer">
            <div class="col-12 col-sm">
                <ul>About us
                    <li><a href="#" class="myFooterLink">�A�ȱ���</a></li>
                    <li><a href="#" class="myFooterLink">���p�v�F��</a></li>
                </ul>
            </div>
            <div class="col-12 col-sm">
                <ul>FAQ
                    <li><a href="#" class="myFooterLink">�`�����D</a></li>
                    <li><a href="#" class="myFooterLink">�N���^�X</a></li>
                </ul>
            </div>
            <div class="col-12 col-sm">
                <ul>Contact us
                    <li><a href="#" class="myFooterLink">�o�e�q�l�l��</a></li>
                    <li><a href="#" class="myFooterLink">�l�ܧڭ�</a></li>
                </ul>
            </div>
        </div>
        <p class="copyright">&copy; copyright by <i>NiHaiZaiMa</i>
    </div>
</body>
</html>