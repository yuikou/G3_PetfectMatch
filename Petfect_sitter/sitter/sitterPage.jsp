<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>PetSitter</title>
  <style>
    .sitter {
      border: 1px solid gray;
      background-color: lightblue;
      width: 400px;
      text-align: center;
    }
    .sitterBody>img {
      width: 200px;
      height: 200px;
      border-radius: 50%;
      margin-top: 10px;
    }
  </style>
</head>
<body>
  <div class="sitter">
    <h2>PetSitter</h2>
  </div>
  <div class="sitterBody">
    <img src="images/pet.jpg">
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
  
  <jsp:useBean id="petSitSrv" scope="page" class="com.petSitter.model.PetSitterService"/>
  <ul>
    <li><a href="addPetSitter.jsp">加入保母行列</a></li>
    <li>
      <form method="post" action="<%=request.getContextPath()%>/sitter/sit.do">
        <b>選擇保母編號</b>
        <select size="1" name="sitNo">
          <c:forEach var="petSitterVO" items="${petSitSrv.all}">
            <option value="${petSitterVO.sitNo}">${petSitterVO.sitNo}</option>
          </c:forEach>
        </select>
        <input type="hidden" name="action" value="getOne">
        <input type="submit" value="送出">
      </form>
    </li>
  </ul>
</body>
</html>