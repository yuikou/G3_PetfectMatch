<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.petSitter.model.*" %>

<% PetSitterVO petSitterVO = (PetSitterVO) request.getAttribute("petSitterVO"); %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>保母資料更新</title>
  <style>
    .sitter {
      border: 1px solid gray;
      background-color: lightblue;
      width: 400px;
      text-align: center;
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
    <h2>PetSitter Info Update</h2>
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
  
  <h3>保母資料修改 :</h3>
    <form method="post" action="<%=request.getContextPath()%>/sitter/sit.do" >
    <table>
      <tr>
        <td><b>托養保姆編號 :</b></td>
        <td><%=petSitterVO.getSitNo()%></td>
      </tr>
      <tr>
        <td><b>會員編號 :</b></td>
        <td><%=petSitterVO.getMemNo()%></td>
      </tr> 
      <tr>
        <td><b>托養簡介 :</b></td>
        <td>
          <textarea rows="10" name="sitInfo" cols="30"><%=petSitterVO.getSitInfo()%></textarea>
        </td>
      </tr>
      <tr>
        <td><b>可服務開始時間 :</b></td>
        <td>
          <select size="1" name="srvSTime">
            <option value="${petSitterVO.srvSTime}">${petSitterVO.srvSTime}</option>
            <option value="0530">0530</option>
            <option value="0600">0600</option>
            <option value="0630">0630</option>
            <option value="0700">0700</option>
            <option value="0730">0730</option>
            <option value="0800">0800</option>
            <option value="0830">0830</option>
            <option value="0900">0900</option>
            <option value="0930">0930</option>
            <option value="1000">1000</option>
            <option value="1030">1030</option>
            <option value="1100">1100</option>
            <option value="1130">1130</option>
            <option value="1200">1200</option>
          </select>        
        </td>
      </tr>
      
      <tr>
        <td><b>可服務結束時間 :</b></td>
        <td>
          <select size="1" name="srvETime">
            <option value="${petSitterVO.srvETime}">${petSitterVO.srvETime}</option>
            <option value="1530">1530</option>
            <option value="1600">1600</option>
            <option value="1630">1630</option>
            <option value="1700">1700</option>
            <option value="1730">1730</option>
            <option value="1800">1800</option>
            <option value="1830">1830</option>
            <option value="1900">1900</option>
            <option value="1930">1930</option>
            <option value="2000">2000</option>
            <option value="2030">2030</option>
            <option value="2100">2100</option>
            <option value="2130">2130</option>
            <option value="2200">2200</option>
          </select>        
        </td>
      </tr>
      <tr>
        <td><b>銀行代碼及帳號 :</b></td>
        <td>
          <input type="text" name="bankCode" size="2" value="<%=petSitterVO.getBankCode()%>" />-
          <input type="text" name="bankAcc" size="20" value="<%=petSitterVO.getBankAcc()%>" />
        </td>
      </tr>
    </table>
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="sitNo" value="<%=petSitterVO.getSitNo()%>">
    <input type="hidden" name="memNo" value="<%=petSitterVO.getMemNo()%>">
    <input type="hidden" name="sitAccStatus" value="<%=petSitterVO.getSitAccStatus()%>">
    <input type="hidden" name="totalComm" value="<%=petSitterVO.getTotalComm()%>">
    <input type="hidden" name="totalCus" value="<%=petSitterVO.getTotalCus()%>">
    <input type="hidden" name="repeatCus" value="<%=petSitterVO.getRepeatCus()%>">
    <input type="submit" value="送出修改">
    </form>
</body>
</html>