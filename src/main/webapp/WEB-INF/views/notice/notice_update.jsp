<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.NoticeVO"%>
<%@ page import="notice.NoticeDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정화면</title>
<script src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div class="container">
<div class="mb-5">
<%@ include file="../common/header.jsp" %>
<br><br>
</div>
<form action="/noticeupdateaction" method="post">
<table border="1" style="width:1000px; text-align:center" >
	<tr>
		<th style="width:100px">글 번호</th>
		<th>${nvo.notice_code}</th>
	</tr>
	<tr>
		<th style="width:100px">글 제목</th>
		<th><input type="text" id="subject" name="subject" value="${nvo.notice_subject}" style="width:900px; text-align:center"/></th>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${nvo.member_email}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td>${nvo.notice_regdate}</td>
	</tr>
	<tr>
		<td style="height:400px">글 내용</td>
		<td><textarea id="content" name="content" style="height:400px; width:900px; text-align:center">${nvo.notice_content}</textarea></td>
	</tr>
</table>
<input type="hidden" id="code" name="code" value="${nvo.notice_code }">
<c:if test="${vo.member_state == 1 }">
	<input type="submit" id ="updateBtn" value="수정하기">
</c:if>
<button id = "cancelBtn" onclick="location.href=''noticeview?code=${nvo.notice_code }">취소</button>
<button id = "listBtn" onclick="location.href='notice'; return false;">목록으로 이동</button>
</form>
<br><br><br>
</div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>