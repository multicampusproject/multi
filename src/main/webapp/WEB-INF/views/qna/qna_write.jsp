<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 글쓰기 화면</title>
<script src="../resources/static/js/scripts.js"></script>
</head>
<body>
<div class="container">
<%@ include file="../common/header.jsp" %>

<form action="/qnawriteaction?num=1" method="POST">
	<div>
		<h3 class="align-self-center pt-5 section-heading text-center" >QnA 글쓰기</h3>	
		<h6 class="text-center section-subheading text-muted fw-lighter">QnA Writing</h6>
		<br><br>
		<input type="text" class="form-control mb-3" placeholder="글 제목" name="qna_title" maxlength="50">
		<textarea class="form-control" placeholder="글 내용" name="qna_content" maxlength="2048" style="height:350px;"></textarea>
		<input type="submit" class="btn btn-primary mt-3" value="등록">
	</div>
</form>
</div>
</body>
</html>