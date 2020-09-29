<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.petSitter.model.*" %>

<% PetSitterVO petSitterVO = (PetSitterVO) request.getAttribute("petSitterVO"); %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>申請保母</title>
  <style>
    .sitter {
      border: 1px solid gray;
      background-color: lightblue;
      width: 400px;
      text-align: center;
    }
    .back_img {
      width: 60px;
      height: 60px;
    }
    table{
      border: 2px solid orange;
      margin-top: 10px;
      width: 400px;
    }
  </style>
</head>
<body>
  <div class="sitter">
    <h2>Add PetSitter</h2>
    <a href="<%=request.getContextPath()%>/sitter/sitterPage.jsp"><img src="images/back.png" class="back_img"></a>
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
  
  <form method="post" action="<%=request.getContextPath()%>/sitter/sit.do">
    <table>
      <tr>
        <td><b>托養簡介 :</b></td>
        <td>
          <textarea rows="10" name="sitInfo" cols="30"><%= (petSitterVO==null)? "關於你對毛小孩的想法": petSitterVO.getSitInfo() %></textarea>
        </td>
      </tr>
      <tr>
        <td><b>可服務開始時間 :</b></td>
        <td>
          <select size="1" name="srvSTime">
            <option value="${petSitterVO.srvSTime}"><%= (petSitterVO==null)? "請選擇":petSitterVO.getSrvSTime() %></option>
            <option value="0530">05:30</option>
            <option value="0600">06:00</option>
            <option value="0630">06:30</option>
            <option value="0700">07:00</option>
            <option value="0730">07:30</option>
            <option value="0800">08:00</option>
            <option value="0830">08:30</option>
            <option value="0900">09:00</option>
            <option value="0930">09:30</option>
            <option value="1000">10:00</option>
            <option value="1030">10:30</option>
            <option value="1100">11:00</option>
            <option value="1130">11:30</option>
            <option value="1200">12:00</option>
          </select>        
        </td>
      </tr>
      
      <tr>
        <td><b>可服務結束時間 :</b></td>
        <td>
          <select size="1" name="srvETime">
            <option value="${petSitterVO.srvETime}"><%= (petSitterVO==null)? "請選擇":petSitterVO.getSrvETime() %></option>
            <option value="1530">15:30</option>
            <option value="1600">16:00</option>
            <option value="1630">16:30</option>
            <option value="1700">17:00</option>
            <option value="1730">17:30</option>
            <option value="1800">18:00</option>
            <option value="1830">18:30</option>
            <option value="1900">19:00</option>
            <option value="1930">19:30</option>
            <option value="2000">20:00</option>
            <option value="2030">20:30</option>
            <option value="2100">21:00</option>
            <option value="2130">21:30</option>
            <option value="2200">22:00</option>
          </select>        
        </td>
      </tr>
      <tr>
        <td><b>銀行代碼及帳號 :</b></td>
        <td>
          <input type="text" name="bankCode" size="2" value="<%= (petSitterVO==null)? "": petSitterVO.getBankCode() %>" />-
          <input type="text" name="bankAcc" size="20" value="<%= (petSitterVO==null)? "": petSitterVO.getBankAcc() %>" />
        </td>
      </tr>
    </table>
    <input type="hidden" name="memNo" value="M004">
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="申請保母">
  </form>

</body>
</html>