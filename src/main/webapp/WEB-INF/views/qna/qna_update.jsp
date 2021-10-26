<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.NoticeVO"%>
<%@ page import="notice.NoticeDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 수정화면</title>
<script src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>

<form action="/qnaupdateaction" method="post">
<table border="1" style="width:1000px; text-align:center" >
	<tr>
		<th style="width:100px">글 번호</th>
		<th>${vo.qna_code}</th>
	</tr>
	<tr>
		<th style="width:100px">글 제목</th>
		<th><input type="text" id="subject" name="subject" value="${vo.qna_subject}" style="width:900px; text-align:center"/></th>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${vo.member_email}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td>${vo.qna_regdate}</td>
	</tr>
	<tr>
		<td style="height:400px">글 내용</td>
		<td><textarea id="content" name="content" style="height:400px; width:900px; text-align:center">${vo.qna_content}</textarea></td>
	</tr>
</table>
<input type="hidden" id="code" name="code" value="${vo.qna_code }">
<input type="submit" id ="updateBtn" value="수정하기">
<button id = "listBtn" onclick="location.href='qna'; return false;">목록으로 이동</button>
</form>
</body>
</html>