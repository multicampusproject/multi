<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.NoticeVO"%>
<%@ page import="notice.NoticeDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 수정화면</title>
<link href="/resources/static/css/styles.css" rel="stylesheet" />
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
<br>
<div class="container align-items-center">
<div class="row">
<div>
<form action="/qnaupdateaction" method="post">
<table class="table table-bordered" style="text-align:center" style="text-align:center" >
	
	<tr>
		<th style="width:100px">글 번호</th>
		<th>${qvo.qna_code}</th>
	</tr>
	<tr>
		<th style="width:100px">글 제목</th>
		<th><input class="col-sm" type="text" id="subject" style="width:100%; height:100%; text-align:center" name="subject" value="${qvo.qna_subject}" style="text-align:center"/></th>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${qvo.member_email}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td>${qvo.qna_regdate}</td>
	</tr>
	<tr>
		<td style="height:400px; vertical-align:middle">글 내용</td>
		<td><textarea class="col-sm" id="content" name="content" style="width:100%; height:400px; text-align:center; vertical-align:middle">${qvo.qna_content}</textarea></td>
	</tr>
</table>

<input type="hidden" id="code" name="code" value="${qvo.qna_code }">
<input class="btn btn-primary" type="submit" id ="updateBtn" value="수정하기">
<button class="btn btn-light" id = "cancelBtn" onclick="location.href=''qnaview?code=${qvo.qna_code }">취소</button>
<button class="btn btn-light" id = "listBtn" onclick="location.href='qna'; return false;">목록으로 이동</button>
</form>
<br><br><br>
</div>
</div>
</div>
<%@ include file="../common/footer.jsp" %>
</div>
</body>

</html>