<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글 보기화면</title>
<script src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
/*$(document).ready(function() {
	//댓글 수정
	$("#commentUpdateBtn").on("click", function(){
		updateReply();
	});

	
});//function end

function updateReply(replyNumber, noticeCode){
	//textarea로 변경
	var data={
			replyNumber : replyNumber
			noticeCode:noticeCode
			content:
	}
	$('#commentText' + number).html('<textarea>' + comment + '</textarea>');
	//ajax로 요청
	.then(function(){
		
	})*/
	/*$.ajax({
		url: '/noticeUpdateReply',
		type:'post',
		data:{'code': code, 'comment':comment, 'number':number},
		dataType :'json',
		success : function(){
			alert("댓글 수정 완료");
		}
	}) //ajax end
} //updatereply end*/


//function noticeUpdate(){
	 
	 //String email = null;
	 //세션에 값이 담겨있는지 체크
	 //if (session.getAttribute("email")!=null){
		 //email = (String)session.getAttribute("email");
	 //}
	 //if (email == null){ %>
		 //alert("로그인을 확인해 주세요.");
	 
	 //로그인 페이지로 이동
	 	//response.sendRedirect("login.jsp");
	 //} else {
		 //if (email == request.getParameter("vo.member_email")){
			 //글 수정 페이지로 이동
			 //location.href="/noticeupdate?code="+${vo.notice_code}; //요기 수정하기
		 //}
	 //}
//}
	
</script>

</head>
<body>

<table border="1"  style="width:1000px; text-align:center; border-collapse:collapse;">
	<tr>
		<th style="width:100px">글 번호</th>
		<th>${vo.notice_code}</th>
	</tr>
	<tr>
		<th style="width:100px">글 제목</th>
		<th>${vo.notice_subject}</th>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${vo.member_email}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td>${vo.notice_regdate}</td>
	</tr>
	<tr>
		<td style="height:400px">글 내용</td>
		<td>${vo.notice_content}</td>
	</tr>
</table><br>

<div id = "UpdateBtn" style="display:block">
	<button id="noticeUpdateBtn" name="noticeUpdateBtn" onclick="location.href='/noticeupdate?code=${vo.notice_code}'">글 수정하기</button>
	<button id="noticeDeleteBtn" name="noticeDeleteBtn" onclick="location.href='/noticedelete?code=${vo.notice_code}'">글 삭제</button>
</div>
<br>
<button id = "listBtn" onclick="location.href='notice'; return false;">목록으로 이동</button>
<br>

<br>
<div id="comments">
	<div id="comment=head" style="height:30px">
		Comments
	</div>
	<form id="commentForm" name="commentForm" method="post">
		<textarea id="comment" name="comment" style="width: 1000px" rows="3" placeholder="댓글을 입력하세요"></textarea> <br>
		<input type="submit" value="등록" formaction="/noticeInsertReply?code=${vo.notice_code}">
		<input type="submit" value="취소" formaction="/noticeDeleteReply" >
	</form>
</div><br>

<!-- 댓글 리스트(작성자, 내용, 날짜, 수정하기) 보이게 구현하기 -->

<c:if test="${replyList != null }">
	<c:forEach items="${replyList}" var="ReplyVO">
		<c:choose>
			<c:when test="${ flag == 'u' && number == ReplyVO.reply_number }">
				<form method="post" action="/noticeUpdateReplyAction">
				<div style="width:600px; padding: 5px 5px 5px 5px;">
					<div style="width:600px">
						<div style="float:right">${ReplyVO.reply_regdate}</div>
						<div style="float:left">${ReplyVO.member_email}</div>
					</div><br>
					<hr style="border:0px; height:0.5px; background-color:gray">
					<textarea name="comment" placeholder="댓글 수정">${ReplyVO.reply_content}</textarea>
				</div>
				<input type="hidden" value="${vo.notice_code}" name="noticeCode">
				<input type="hidden" value="${ReplyVO.reply_number}" name="replyNumber">
				<input type="submit" value="등록">
				<input type="button" onclick="location.href='noticeview?code=${vo.notice_code}'" value="취소">
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
				<button onclick="location.href='noticeDeleteReply?code=${vo.notice_code}&number=${ReplyVO.reply_number}'">삭제</button>
				<button onclick="location.href='noticeUpdateReply?code=${vo.notice_code}&number=${ReplyVO.reply_number}'">수정</button>
				<br><br>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</c:if>

</body>
</html>