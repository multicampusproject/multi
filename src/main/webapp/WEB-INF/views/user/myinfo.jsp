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
    $(document).ready(function(){
	    	$("#joinBtn").click(function(){
	    		
	    		//alert(1)
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
                    <h2 class="section-heading text-uppercase">${vo.member_id}님 회원 정보</h2>
                </div>
                <div class="row text-center pt-3">
                <div class="container pt-5 pb-5">
					<form action="${pageContext.request.contextPath}/updatemyinfo" method="post">
						<table class="table table-hover">
						  <thead>
						    <tr>
						      <th scope="col" colspan="2" >${dbvo.member_name}님 회원 정보</th>
						    </tr>
						  </thead>
						  <tbody>
						  	<tr>
						      <th scope="row">이메일</th>
						      <td>
						      	<input type="text" class="form-control" value="${dbvo.member_email}" disabled="disabled">
						      	<input type="hidden" value="${dbvo.member_email}" name="member_email">
						      </td>
						    </tr>
						    <tr>
						      <th scope="row">아이디</th>
						      <td><input type="text" class="form-control" id="userid" name="member_id" value="${dbvo.member_id}" disabled="disabled"></td>
						    </tr>
						    <tr>
						      <th scope="row">이름</th>
						      <td><input type="text" class="form-control" id="name" name="member_name" value="${dbvo.member_name}"></td>
						    </tr>
						     <tr>
						      <td  colspan="2">
						      <input type="submit" class="btn btn-warning" id="joinBtn" value="이름 수정">
						      <a class="btn btn-outline-warning" href="${pageContext.request.contextPath}/index">메인으로 가기</a></td>
						    </tr>
						  </tbody>
						</table>
					</form>
				</div>
                </div>
            </div>
        </section>
<div class="text-center container mt-5">
<main class="form-signin">
  
	
</main>
</div>

<div class="b-example-divider"></div>




<%@ include file="../common/footer.jsp" %>
</body>
</html>