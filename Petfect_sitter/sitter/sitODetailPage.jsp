<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sitOrderDetail.model.*, java.util.*" %>
    
<% SitODetailVO sitODetailVO = (SitODetailVO) request.getAttribute("sitODetailVO"); %>    
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>訂單詳情頁面</title>
  <style>
    .sitter {
      border: 1px solid gray;
      background-color: lightblue;
      width: 400px;
      text-align: center;
    }
    table, th, td {
      border: 2px solid orange;
      border-collapse: collapse;
    }
    table {
      margin-top: 10px;
      width: 800px;
    }
  </style>
</head>
<body>
  <div class="sitter">
    <h2>PetSitter OrderDetail</h2>
  </div>
  
  <%-- 錯誤列表 --%>
  <c:if test="${not empty errorMsg}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
      <c:forEach var="message" items="${errorMsg}">
        <li style="color:red">${message}</li>
      </c:forEach>
    </ul>
  </c:if>
  
  <table>
    <tr>
      <th>訂單編號</th>
      <th>服務項目編號</th>
      <th>寵物編號</th>
      <th>服務項目價格</th>
      <th>服務項目(數目)</th>
      <th>服務項目(單位)</th>
    </tr>
      
    <tr>
      <td><%=sitODetailVO.getSitOrderNo()%></td>
      <td><%=sitODetailVO.getSitSrvNo()%></td>
      <td><%=sitODetailVO.getPetNo()%></td>
      <td><%=sitODetailVO.getSitOpPrice()%></td>
      <td><%=sitODetailVO.getSitSrvTimes()%></td>
      <td><%=sitODetailVO.getSitSrvUnit()%></td>
    </tr>  
  </table>
  
  
</body>
</html>