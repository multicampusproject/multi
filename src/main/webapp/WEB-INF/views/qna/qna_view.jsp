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

</script>

</head>
<body>

<table border="1"  style="width:1000px; text-align:center; border-collapse:collapse;">
	<tr>
		<th style="width:100px">글 번호</th>
		<th>${vo.qna_code}</th>
	</tr>
	<tr>
		<th style="width:100px">글 제목</th>
		<th>${vo.qna_subject}</th>
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
		<td>${vo.qna_content}</td>
	</tr>
</table><br>

<div id = "UpdateBtn" style="display:block">
	<button id="qnaUpdateBtn" name="qnaUpdateBtn" onclick="location.href='/qnaupdate?code=${vo.qna_code}'">글 수정하기</button>
	<button id="qnaDeleteBtn" name="qnaDeleteBtn" onclick="location.href='/qnadelete?code=${vo.qna_code}'">글 삭제</button>
</div>
<br>
<button id = "listBtn" onclick="location.href='qna'; return false;">목록으로 이동</button>
<br>

<br>
<div id="comments">
	<div id="comment=head" style="height:30px">
		Comments
	</div>
	<form id="commentForm" name="commentForm" method="post">
		<textarea id="comment" name="comment" style="width: 1000px" rows="3" placeholder="댓글을 입력하세요"></textarea> <br>
		<input type="submit" value="등록" formaction="/qnaInsertReply?code=${vo.qna_code}">
		<input type="submit" value="취소" formaction="/qnaDeleteReply" >
	</form>
</div><br>

<!-- 댓글 리스트(작성자, 내용, 날짜, 수정하기) 보이게 구현하기 -->

<c:if test="${replyList != null }">
	<c:forEach items="${replyList}" var="ReplyVO">
		<c:choose>
			<c:when test="${ flag == 'u' && number == ReplyVO.reply_number }">
				<form method="post" action="/qnaUpdateReplyAction">
				<div style="width:600px; padding: 5px 5px 5px 5px;">
					<div style="width:600px">
						<div style="float:right">${ReplyVO.reply_regdate}</div>
						<div style="float:left">${ReplyVO.member_email}</div>
					</div><br>
					<hr style="border:0px; height:0.5px; background-color:gray">
					<textarea name="comment" placeholder="댓글 수정">${ReplyVO.reply_content}</textarea>
				</div>
				<input type="hidden" value="${vo.qna_code}" name="qnaCode">
				<input type="hidden" value="${ReplyVO.reply_number}" name="replyNumber">
				<input type="submit" value="등록">
				<input type="button" onclick="location.href='qnaview?code=${vo.qna_code}'" value="취소">
				</form>
				<br><br>
			</c:when>
			<c:otherwise>
				<div style="width:600px; padding: 5px 5px 5px 5px;">
					<div style="width:600px">
						<div style="float:right">${ReplyVO.reply_regdate}</div>
						<div style="float:left">${ReplyVO.member_email}</div>
					</div><br>
					<hr style="border:0px; height:0.5px; background-color:gray">
					<div style="white-space:pre">${ReplyVO.reply_content}</div>
				</div>
				<button onclick="location.href='qnaDeleteReply?code=${vo.qna_code}&number=${ReplyVO.reply_number}'">삭제</button>
				<button onclick="location.href='qnaUpdateReply?code=${vo.qna_code}&number=${ReplyVO.reply_number}'">수정</button>
				<br><br>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</c:if>

</body>
</html>