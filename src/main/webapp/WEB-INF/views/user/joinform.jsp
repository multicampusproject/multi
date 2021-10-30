<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/sandstone/bootstrap.min.css" integrity="sha384-zEpdAL7W11eTKeoBJK1g79kgl9qjP7g84KfK3AZsuonx38n8ad+f5ZgXtoSDxPOh" crossorigin="anonymous">
<style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      
      .id_ok{color:DodgerBlue; display: none;}
      .id_already{color:tomato; display: none;}
      
      .nickname_ok{ display: none;}
      .nickname_already{color:#6A82FB; display: none;}
    
</style>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    function checkId(){
        var userid = $('#userid').val(); 
        //alert(userid)
        if(userid != ""){
	        $.ajax({
	            url:'${pageContext.request.contextPath}/idCheck', 
	            type:'post', //POST 방식으로 전달
	            data:{userid:userid},
	            success:function(cnt){
	                //console.log("처리 성공 시 변경되는 내용");
	            	if(cnt != 1){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
	                    $('.id_ok').css("display","inline-block"); 
	                    $('.id_already').css("display", "none");
	                } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
	                    $('.id_already').css("display","inline-block");
	                    $('.id_ok').css("display", "none");
	                }
	            },
	            error:function(){
	                alert("에러입니다");
	            }
	        });//ajax끝
        }//if
        else{
        	alert("아이디를 입력하세요.");
        	$("#userid").focus();
        }
    };//checkId
    
    
    $(document).ready(function(){
	    	$("#joinBtn").click(function(){
	    		
	    		//alert(1)
	    		if($("#userid").val().length == 0){
	    			alert("아이디를 입력하세요");
	    			$("#userid").focus();
	    			return false;
	    		}
	    		if($("#name").val().length == 0){
	    			alert("이름을 입력하세요");
	    			$("#name").focus();
	    			return false;
	    		}
	    		
	    	});
    });//ready
    
    
</script>

</head>
<body id="page-top">
        <%@ include file="../common/header.jsp" %>

<section class="page-section" id="services">
            <div class="container mt-5">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">회원 가입</h2>
                </div>
                <div class="row text-center pt-3">
                <div class="container pt-5 pb-5">
				<form action="${pageContext.request.contextPath}/userjoin" method="post">
					<table class="table table-hover">
					  <thead>
					    <tr>
					      <th scope="col" colspan="4">회원 가입 양식</th>
					    </tr>
					  </thead>
					  <tbody>
					  	<tr>
					      <th scope="row">이메일</th>
					      <td colspan="3">
					      	<input type="text" class="form-control" value="${member_email}" disabled="disabled">
					      	<input type="hidden" value="${member_email}" name="email">
					      </td>
					    </tr>
					    <tr>
					      <th scope="row">아이디</th>
					      <td><input type="text" class="form-control" id="userid" name="userid"></td>
					      <td>
							<span id="ok" class="id_ok">사용 가능한 아이디입니다.</span>
							<span class="id_already">이미 사용 중인 아이디입니다. 다시 작성해주세요.</span>
					      </td>
					      <td>
					      <button type="button" class="btn btn-secondary" onclick="checkId();">아이디 중복체크</button>
					      </td>
					    </tr>
					    <tr>
					      <th scope="row">이름</th>
					      <td colspan="3"><input type="text" class="form-control" id="name" name="name"></td>
					    </tr>
					     <tr>
					      <td colspan="4" >
					      <input type="submit" class="btn btn-secondary" id="joinBtn" value="회원가입"></td>
					    </tr>
					  </tbody>
					</table>
				</form>
				</div>
				</div>
			</div>
</section>

<div class="b-example-divider"></div>



<%@ include file="../common/footer.jsp" %>


</body>
</html>