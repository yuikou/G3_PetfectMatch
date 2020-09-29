<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sitOrder.model.*, java.util.*" %>

<% Set<SitOrderVO> sitOrderSet = (Set<SitOrderVO>) request.getAttribute("sitOrderSet"); %>

<!DOCTYPE html>
<html> 
<head>
  <meta charset="UTF-8">
  <title>保母訂單</title>
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
      width: 1500px;
    }
  </style>
</head>
<body>
  <div class="sitter">
    <h2>PetSitter Order</h2>
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
      <th>會員編號</th>
      <th>保母編號</th>
      <th>服務開始日</th>
      <th>服務結束日</th>
      <th>總金額</th>
      <th>訂單狀態</th>
      <th>退費</th>
      <th>補償金額</th>
      <th>評價總數</th>
      <th>評價內容</th>
      <th>接送起點</th>
      <th>接送終點</th>
      <th>查看訂單明細</th>
    </tr>
      
    <% for(SitOrderVO sitOrderVO : sitOrderSet) { %>
    <tr>
      <td><%=sitOrderVO.getSitOrderNo()%></td>
      <td><%=sitOrderVO.getMemNo()%></td>
      <td><%=sitOrderVO.getSitNo()%></td>
      <td><%=sitOrderVO.getSitSDate()%></td>
      <td><%=sitOrderVO.getSitEDate()%></td>
      <td><%=sitOrderVO.getTotalPrice()%></td>
      <td><%=sitOrderVO.getOrderStatus()%></td>
      <td><%=sitOrderVO.getRefund()%></td>
      <td><%=sitOrderVO.getCoupon()%></td>
      <td><%=sitOrderVO.getCommStar()%></td>
      <td><%=sitOrderVO.getSitComm()%></td>
      <td><%=sitOrderVO.getPickupFrom()%></td>
      <td><%=sitOrderVO.getPickupTo()%></td>
      <td>
      <form>
        <input type="submit" value="查看訂單明細">
        <input type="hidden" name="sitOrderNo" value=<%=sitOrderVO.getSitOrderNo()%>>
        <input type="hidden" name="action" value="select_orderDetail">
      </form>  
      </td >
    </tr>  
    <% } %>
  </table>
</body>
</html>