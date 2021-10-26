<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 게시판 메인</title>
</head>
<body>
<h3>공지사항 게시판</h3>
<table border="1" style="text-align:center; border-collapse:collapse;">
	<tr>
		<th>글번호</th>
		<th style="width:600px">제목</th>
		<th>작성자</th>
		<th>작성일</th>
	</tr>
<c:forEach items="${noticelist}" var="NoticeVO">
	<tr>
		<td>${NoticeVO.notice_code}</td>
		<td><a href="/noticeview?code=${NoticeVO.notice_code}">${NoticeVO.notice_subject}</a></td>
		<td>${NoticeVO.member_email}</td>
		<td>${NoticeVO.notice_regdate}</td>
	</tr>
</c:forEach>
</table>
<br>
<button onclick="location.href='/noticewrite'">글 작성하기</button>
</body>
</html>