<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <style>
    div.container { width: 900px; margin: 50px auto; }
    thead th { background-color: #eee; }
    table{ border-collapse: collapse; width: 100%; }
    td, th { padding: 4px; border: 1px solid lightgray; }
    td:nth-child(4) { text-align: center; }
  </style>
</head>
<body>
<div class="container">
  <h1>장비목록</h1>
  
  <table class="table table-bordered table-condensed">
    <thead>
      <tr>
        <th>순번</th>
        <th>장비명</th>
        <th>타입</th>
        <th>도입일자</th>
        <th>사용자</th>
        <th>스트레스지수</th>
        <th>폐기일자</th>
        <th>등록일자</th>
        <th>등록자</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="eqlist" items="${ EQ }">
        <tr>
          <td>${eqlist.EQ_SEQ}</td>
          <td>${eqlist.EQ_NAME }</td>
          <td>${eqlist.EQ_TYPE }</td>
          <td>${eqlist.EQ_INPUT_DATE }</td>
          <td>${eqlist.EQ_USER }</td>
          <td>${eqlist.EQ_STRESS_NUM }</td>
          <td>${eqlist.EQ_DISUSE }</td>
          <td>${eqlist.REG_DATE }</td>
          <td>${eqlist.REG_USER }</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

</div>
</body>
</html>
