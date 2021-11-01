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
                    <h2 class="section-heading text-uppercase">회원 목록</h2>
                </div>
                <div class="row text-center pt-3">
                <div class="container pt-5 pb-5">
				<table class="table table-hover">
				  <thead>
				    <tr>
		      			<th>No.</th>
						<th>Email</th>
						<th>ID</th>
						<th>Name</th>
						<th>State</th>
				    </tr>
				  </thead>
				  <tbody>
					<c:choose>
						<c:when test="${empty list}">
							<tr>
							<th colspan="5">
							회원이 없습니다.
							</th>
							</tr>
						</c:when>
						<c:otherwise>
							
							<c:forEach items="${list}" var="userVO" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td>
									<a href="${pageContext.request.contextPath}/aduserdetail/${userVO.member_id}">
									${userVO.member_email }</td></a>
									<td>${userVO.member_id }</td>
									<td>${userVO.member_name }</td>
									<td>
									<c:if test="${userVO.member_state == 0}">
										일반 회원
									</c:if>
									<c:if test="${userVO.member_state == 1}">
										관리자
									</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>			  
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