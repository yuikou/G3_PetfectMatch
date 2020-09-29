<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.petSitter.model.*" %>

<% PetSitterVO petSitterVO = (PetSitterVO) request.getAttribute("petSitterVO"); %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">  
  <title>保母資料</title>
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
    table, th, td {
      border: 2px solid orange;
      border-collapse: collapse;
    }
    table {
      margin-top: 10px;
      margin-bottom: 50px;
      width: 1500px;
    }
  </style>
</head>
<body>
  <div class="sitter">
    <h2>PetSitter</h2>
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
  
  
  <table>
    <tr>
      <th>托養保姆編號</th>
      <th>會員編號</th>
      <th>托養簡介</th>
      <th>可服務開始時間</th>
      <th>可服務結束時間</th>
      <th>銀行代碼</th>
      <th>匯款帳號</th>
      <th>帳號狀態</th>
      <th>總評價分數</th>
      <th>總服務人數</th>
      <th>回頭客人數</th>
      <th>訂單 </th>
      <th>更改保母資訊 </th>
    </tr>
    <tr>
      <td><%=petSitterVO.getSitNo()%></td>
      <td><%=petSitterVO.getMemNo()%></td>
      <td><%=petSitterVO.getSitInfo()%></td>
      <td><%=petSitterVO.getSrvSTime()%></td>
      <td><%=petSitterVO.getSrvETime()%></td>
      <td><%=petSitterVO.getBankCode()%></td>
      <td><%=petSitterVO.getBankAcc()%></td>
      <td><%=petSitterVO.getSitAccStatus()%></td>
      <td><%=petSitterVO.getTotalComm()%></td>
      <td><%=petSitterVO.getTotalCus()%></td>
      <td><%=petSitterVO.getRepeatCus()%></td>
      <td>
        <form method="post" action="<%=request.getContextPath()%>/sitter/sit.do">
          <input type="hidden" name="action" value="select_order">
          <input type="hidden" name="sitNo" value="${petSitterVO.sitNo}">
          <input type="submit" value="查詢訂單">
        </form>
      </td>
      <td>
        <form method="post" action="<%=request.getContextPath()%>/sitter/sit.do">
          <input type="hidden" name="action" value="getOne_For_update">
          <input type="hidden" name="sitNo" value="${petSitterVO.sitNo}">
          <input type="submit" value="更改">
        </form>
      </td>
    </tr>
  </table> 
</body>
</html>