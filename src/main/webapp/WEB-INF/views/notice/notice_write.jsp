<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기 화면</title>
<link href="/resources/static/css/styles.css" rel="stylesheet" />
<script src="jquery-3.2.1.min.js"></script>
</head>
<body>
<div class="container">
<div class="mb-5">
<%@ include file="../common/header.jsp" %>
<br><br>
</div>
<div class="container">
<div class="row">
<form action="/noticewriteaction" method="POST">
	
	<div class="mt-3">
		<h3 class="align-self-center pt-5 section-heading text-center" >공지사항 글쓰기</h3>	
		<h6 class="text-center section-subheading text-muted fw-lighter">Notice Writing</h6>
		<br>
		<input type="text" class="form-control mb-3" placeholder="글 제목" name="notice_title" maxlength="50">
		<textarea class="form-control" placeholder="글 내용" name="notice_content" maxlength="2048" style="height:350px;"></textarea>
		<input type="submit" class="btn btn-primary mt-3" value="등록">
	</div>
</form>
</div>
</div>
</div>
</body>
</html>