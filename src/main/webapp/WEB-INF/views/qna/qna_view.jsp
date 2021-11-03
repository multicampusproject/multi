<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 글 보기화면</title>
<link href="/css/styles.css" rel="stylesheet" />
<script src="jquery-3.2.1.min.js"></script>
<script src="../resources/static/js/scripts.js"></script>
<script type="text/javascript">
var message = '${param.message}'

	if (message!="") {
		alert(message);
	}
	
$(document).ready(function(){
	commentList();
	
	//댓글 등록 버튼 클릭시
	$(document).on("click", "#replyInsertBtn", (function(){ 
		commentInsert();
		commentList();//commentInsert 함수 호출	
		})
	);
});

//댓글 목록
function commentList(){
	
	var email = $('#email').val(); //세션 이메일
	var code = $('#code').val();
	var html='';
	$.ajax({
		url: '/qna/replyList',
		type: 'post',
		data : {'code' : code},
		success : function(data) {
		 	$.each(data, function(key, value){ //data는 배열의 변수 이름 - 이름이 data 아니어도 됨. key=index. each=반복
				var memberEmail = value.member_email;
		 		if (memberEmail===email) {
					//수정 버튼 있는 버전
					html += '<div id="reply' + value.reply_number + '" class="mt-5 mb-5">';
					html += '<div style="float:right">' + value.reply_regdate + '</div>';
					html += '<div style="float:left">' + value.member_email + '</div>';
					html += '<br><hr>';
					html += '<div style="white-space:pre-wrap;" id="replyContent' + value.reply_number + '" class="mb-3">' + value.reply_content + '</div><br>';
					html += '<button class="btn btn-light btn-sm" id="updateBtn' + value.reply_number + '">수정</button>'; //수정버튼
					html += '<button class="btn btn-danger btn-sm" id="deleteBtn' + value.reply_number + '">삭제</button>'; //삭제버튼
					html += '<br>';
					html += '</div>';
				
					$("#test").empty();
					$("#test").append(html);
					
					//댓글 삭제
					$(document).on("click", "#deleteBtn" + value.reply_number, function(){
						var reply_number = value.reply_number;
						$.ajax({
							
							url:'/qna/replyDelete',
							type : 'post',
							data : {'reply_number' : reply_number, 'code': code},
							success : function(){
									commentList();
									//alert("댓글이 삭제되었습니다");
									//setTimeout(function(){ isAjaxing = false;}, 10000); //중복 방지
							}
						});
					});
					
					//댓글 수정버튼 클릭시 textarea로 변경
					$(document).on("click", "#updateBtn" + value.reply_number, function(){
						//String replyContentText = $("#replyContent" + value.reply_number).val();
						var html = "";
						html += '<div id="reply' + value.reply_number + '" class="mt-5 mb-5">';
						html += '<div style="float:right" >' + value.reply_regdate + '</div>';
						html += '<div style="float:left">' + value.member_email + '</div>';
						html += '<br><hr>';
						html += '<textarea class="form-control mb-3 h-25" rows="3" placeholder="댓글 수정" id="replyContentUpdate' + value.reply_number + '">' + value.reply_content + '</textarea>';
						html += '<button class="btn btn-light btn-sm" id="updateActionBtn' + value.reply_number + '">등록</button>'; //수정완료버튼
						html += '<button class="btn btn-danger btn-sm" id="cancelBtn' + value.reply_number + '">취소</button>'; //취소버튼
						html += '<br>';
						html += '</div>';
						$("#reply" + value.reply_number).replaceWith(html);
						
						$(document).on("click", "#updateActionBtn" + value.reply_number, function(){
							//등록버튼 누르면
							var reply_number = value.reply_number;
							var replyContent = $("#replyContentUpdate" + value.reply_number).val();
							
							//댓글 수정
							$.ajax({
								
								url:'/qna/replyUpdate',
								type : 'post',
								data : {'email' : email, 'reply_number' : reply_number, 'code': code, 'replyContent':replyContent},
								success : function(){
									commentList();
									//alert("댓글이 수정되었습니다.");	
								}
							}); //ajax end
						});
						$(document).on("click", "#cancelBtn" + value.reply_number, function(){
							//취소버튼 누르면
							commentList();
						});
					});

				} else {
					//수정 버튼 없는 버전
					html += '<div class="mt-5 mb-5">';
					html += '<div style="float:right">' + value.reply_regdate + '</div>';
					html += '<div style="float:left">' + value.member_email + '</div>';
					html += '<div><br><hr>';
					html += '<div style="white-space:pre-wrap;">' + value.reply_content + '</div>';
					html += '<br>';
					html += '</div>';
					$("#test").empty();
					$("#test").append(html);
				}
            });//each end
		}//success end
	}); //ajax end
} // function end

//댓글 등록
function commentInsert(){
	var code = $('#code').val();
	var replyContent = $('#replyContent').val();
	//alert(code + replyContent);
    $.ajax({
        url : '/qna/replyInsert',
        type : 'post',
        data : {'code':code, 'replyContent':replyContent },
      /*   error : function(error) {
        }, */
        success : function(data) {
        	if (data==-1) {
        	alert("로그인 정보가 없습니다.");
        	location.href="/index";
        	} else {
        	$("#replyContent").val("");
        	commentList();	
        	}
        }//success end
    });
}	
</script>

</head>
<body>
<div class="container">
	<%@ include file="../common/header.jsp" %>
	<br><br>
	<div class="container align-items-center">
		<div class="row">
			<div>
				<table class="table table-bordered" style="text-align:center; table-layout: fixed">
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
						<td class="align-middle" style="white-space:pre-wrap;">${qvo.qna_content}</td>
					</tr>
				</table>
				<input type="hidden" name="code" id="code" value="${qvo.qna_code}">
				<input type="hidden" value="${vo.member_email }" id="email">
			</div>
			<div id = "UpdateBtn">
				<c:if test="${vo.member_email.equals(qvo.member_email) }">
					<button class="btn btn-primary" id="qnaUpdateBtn" name="qnaUpdateBtn" onclick="location.href='/qnaupdate?num=${num}&code=${qvo.qna_code}'">글 수정하기</button>
					<button class="btn btn-danger" id="qnaDeleteBtn" name="qnaDeleteBtn" onclick="location.href='/qnadelete?num=${num}&code=${qvo.qna_code}'">글 삭제</button>
				</c:if>
				<button class="btn btn-outline-primary" id = "listBtn" onclick="location.href='qna?num=${num}'; return false;">목록으로 이동</button>
			</div>
		</div>
	</div>
	<div class="container mt-5">
		<div class="row">
			<h3>Comments</h3>
			<form class="form-control p-3" id="replyInsertForm" name="replyForm" method="post">
				<textarea class="form-control input-group input-group-text my-1 col-md" id="replyContent" name="replyContent" rows="3" placeholder="댓글을 입력하세요"></textarea> <br>
				<input class="btn btn-primary" id="replyInsertBtn" type="button" value="등록">
			</form>
		</div>
	</div>
	<!-- 댓글 리스트 ajax-->
	<div id="test">
	</div>
	<%@ include file="../common/footer.jsp" %>
</div>
</body>
</html>