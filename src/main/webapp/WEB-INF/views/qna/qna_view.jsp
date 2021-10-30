<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 글 보기화면</title>
<script src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
var message = '${param.message}'

	if (message!="") {
		alert(message);
	}
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
<table class="table table-bordered" style="text-align:center">
	<tr>
		<th style="width:100px">글 번호</th>
		<th>${qvo.qna_code}</th>
	</tr>
	<tr>
		<th style="width:100px">글 제목</th>
		<th>${qvo.qna_subject}</th>
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
		<td class="align-middle" style="height:400px">글 내용</td>
		<td class="align-middle">${qvo.qna_content}</td>
	</tr>
</table>
</div>
<div id = "UpdateBtn">
	<c:if test="${vo.member_email.equals(qvo.member_email) }">
	<button class="btn btn-primary" id="qnaUpdateBtn" name="qnaUpdateBtn" onclick="location.href='/qnaupdate?code=${qvo.qna_code}'">글 수정하기</button>
	<button class="btn btn-secondary" id="qnaDeleteBtn" name="qnaDeleteBtn" onclick="location.href='/qnadelete?code=${qvo.qna_code}'">글 삭제</button>
	</c:if>
	<button class="btn btn-outline-primary" id = "listBtn" onclick="location.href='qna'; return false;">목록으로 이동</button>
</div>
</div>
</div>
<div class="container mt-5">
<div class="row">
<div id="comments" class="my-3">
	<div>
		<h3>Comments</h3>
	</div>
	<form class="form-control p-3" id="commentForm" name="commentForm" method="post">
		<textarea class="form-control input-group input-group-text my-3" id="comment" name="comment" style="width: 1000px" rows="3" placeholder="댓글을 입력하세요"></textarea> <br>
		<input class="btn btn-primary" type="submit" value="등록" formaction="/qnaInsertReply?code=${qvo.qna_code}">
		<input class="btn btn-secondary" type="submit" value="취소" formaction="/qnaDeleteReply" >
	</form>
</div><br>
</div>
</div>

<!-- 댓글 리스트(작성자, 내용, 날짜, 수정하기) 보이게 구현하기 -->
<div class="container mt-5">
<div class="row">
<c:if test="${replyList != null }">
	<c:forEach items="${replyList}" var="ReplyVO">
		<c:choose>
			<c:when test="${ flag == 'u' && number == ReplyVO.reply_number }">
				<form class="mb-5" method="post" action="/qnaUpdateReplyAction">
				<div>
					<div>
						<div style="float:right">${ReplyVO.reply_regdate}</div>
						<div style="float:left"><h5>${ReplyVO.member_email}</h5></div>
					</div><br>
					<hr style="border:0px; background-color:gray">
					<textarea class="form-control mb-3" name="comment" placeholder="댓글 수정">${ReplyVO.reply_content}</textarea>
				</div>
				<input type="hidden" value="${qvo.qna_code}" name="qnaCode">
				<input type="hidden" value="${ReplyVO.reply_number}" name="replyNumber">
				<input class="btn btn-primary btn-sm" type="submit" value="등록">
				<input class="btn btn-light btn-sm" type="button" onclick="location.href='qnaview?code=${qvo.qna_code}'" value="취소">
				</form>
				<br><br>
			</c:when>
			<c:otherwise>
				<div>
					<div>
						<div style="float:right">${ReplyVO.reply_regdate}</div>
						<div style="float:left"><h5>${ReplyVO.member_email}</h5></div>
					</div><br>
					<hr>
					<div>${ReplyVO.reply_content}</div>
					<br>
				</div>
				<div class="mb-5">
				<c:if test="${vo.member_email == ReplyVO.member_email }">
					<button class="btn btn-light btn-sm" onclick="location.href='qnaUpdateReply?code=${qvo.qna_code}&number=${ReplyVO.reply_number}'">수정</button>
					<button class="btn btn-danger btn-sm" onclick="location.href='qnaDeleteReply?code=${qvo.qna_code}&number=${ReplyVO.reply_number}'">삭제</button>
				</c:if>
				</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</c:if>
</div>
</div>

<br><br><br>

<%@ include file="../common/footer.jsp" %>

</div>       


</body>
</html>