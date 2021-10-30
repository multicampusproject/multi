<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
관리자메인
<a href="${pageContext.request.contextPath}/aduserlist">회원리스트</a><br>
<a href="${pageContext.request.contextPath}/musiclist">음악리스트</a><br>
<a href="${pageContext.request.contextPath}/analysisemotion">감정분석통계</a><br>
<a href="${pageContext.request.contextPath}/analysismusic">추천음악통계</a><br>
</body>
</html>