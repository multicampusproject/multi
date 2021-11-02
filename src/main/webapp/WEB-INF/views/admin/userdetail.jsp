<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/static/js/scripts.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/sandstone/bootstrap.min.css" integrity="sha384-zEpdAL7W11eTKeoBJK1g79kgl9qjP7g84KfK3AZsuonx38n8ad+f5ZgXtoSDxPOh" crossorigin="anonymous">
</head>
<body id="page-top">
        <%@ include file="../common/header.jsp" %>

<div class="text-center container mt-5">
<main class="form-signin">
  
	<section class="page-section" id="services">
            <div class="container mt-5">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">회원 상세정보</h2>
                </div>
                <div class="row text-center pt-3">
                <div class="container pt-5 pb-5">
				<table class="table table-hover">
				  <thead>
				    <tr>
				      <th scope="col" colspan="5" >회원 상세 정보</th>
				    </tr>
				  </thead>
				  <tbody>
					<tr>
						<th>Email</th>
						<td>${user.member_email}</td>
					</tr>
					<tr>
						<th>ID</th>
						<td>${user.member_id}</td>
					</tr>
					<tr>
						<th>Name</th>
						<td>${user.member_name}</td>
					</tr>
					<tr>
						<th>State</th>
						<td>
						<c:if test="${user.member_state == 0}">
							일반 회원
							<a href="${pageContext.request.contextPath}/updatestate/${user.member_id}/1" class="btn btn-outline-danger">관리자로 변경</a>
						</c:if>
						<c:if test="${user.member_state == 1}">
							관리자
							<a href="${pageContext.request.contextPath}/updatestate/${user.member_id}/0" class="btn btn-outline-success">일반 회원으로 변경</a>
						</c:if>
						</td>
					</tr>
				  </tbody>
				</table>
				</div>
				</div>
			</div>
			</section>
</main>
</div>

<div class="b-example-divider"></div>


<%@ include file="../common/footer.jsp" %>


</body>
</html>