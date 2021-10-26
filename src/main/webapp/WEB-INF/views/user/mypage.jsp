<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${vo.member_name}님<br>
<a href="${pageContext.request.contextPath}/myinfo">내 정보확인</a>
<a href="${pageContext.request.contextPath}/myhistory">내 히스토리</a>
</body>
</html>