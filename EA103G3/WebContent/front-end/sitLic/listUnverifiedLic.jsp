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

<TITLE>�f���ҷ�</TITLE>

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

<BODY style="background-color:#fff !important">

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

		<div class="backFromDB rwd-table">
		<table class="licTable vrfTable">
				<tr>
					<th style="min-width:auto;">NO.</th>
					<th>�ҷӽs��</th>
					<th>�ҷӦW��</th>
					<th>�ҷӨ����</th>
					<th>�ҷӪ��A</th>
					<th>�ҷӹϤ�</th>
					<th>�f�ֵ��G</th>
				</tr>
				<% 
					SitLicService slSvc = new SitLicService();
					List<SitLicVO> list = slSvc.getUnverifiedLic(0);
					pageContext.setAttribute("list", list);
				%>
				<c:forEach var="sitLic" items="${list}" varStatus="no">
					<tr>
						<td>${no.count}</td>
						<td>${sitLic.licNo}</td>
						<td>${sitLic.licName}</td>
						<td><c:out value="${sitLic.licEXP}" default="-"/></td>
						<td>${sitLic.licStatus}</td>
						<c:set var="licNo" value="${sitLic.licNo}"/>
						<td class="myLicPic"><img class="licPicImg" alt="" src="${pageContext.request.contextPath}/ShowImg?action=sitLic&licNo=${licNo}"></td>
						<td>
							<FORM method="post" action="sitLic.do">
									<input class="vrfBtn" type="submit" value="�q�L">
									<input type="hidden" name="licNo" value="${sitLic.licNo}">
									<input type="hidden" name="licStatus" value=1>
									<input type="hidden" name="action" value="verify">
							</FORM>
							<FORM method="post" action="sitLic.do">
									<input class="vrfBtn" type="submit" value="���q�L">
									<input type="hidden" name="licNo" value="${sitLic.licNo}">
									<input type="hidden" name="licStatus" value=2>
									<input type="hidden" name="action" value="verify">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>