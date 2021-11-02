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
</head>
<body id="page-top">
        <%@ include file="../common/header.jsp" %>
        
        
        <section class="page-section" id="services">
            <div class="container mt-5">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">${vo.member_id}님 히스토리</h2>
                </div>
                <div class="row text-center pt-3">
                <div class="container pt-5 pb-5">
                <table class="table table-hover">
			  
				<c:choose>
					<c:when test="${empty list}">
						<h3 class="section-subheading text-muted">아직 음악추천을 받지 않았습니다.</h3>
					</c:when>
					<c:otherwise>
					<thead>
						<tr scope="col">
							<th>No.</th>
							<th>나이</th>
							<th>성별</th>
							<th>감정</th>
							<th>추천음악</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="historyVO" varStatus="status">
							<tr >
								<td>${status.count }</td>
								<td>${historyVO.image_age }</td>
								<td>${historyVO.image_gender }</td>
								<td>${historyVO.music_emotion }</td>
								<td>
								<a href="https://www.youtube.com/embed/${historyVO.music_url}">
								${historyVO.music_artist} - ${historyVO.music_title }</a></td>
							</tr>
						</c:forEach>
					</tbody>
					</c:otherwise>
				</c:choose>			  
				</table>
                </div>
                </div>
            </div>
        </section>


<div class="b-example-divider"></div>



<%@ include file="../common/footer.jsp" %>

</body>
</html>