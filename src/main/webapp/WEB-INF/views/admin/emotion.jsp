<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/sandstone/bootstrap.min.css" integrity="sha384-zEpdAL7W11eTKeoBJK1g79kgl9qjP7g84KfK3AZsuonx38n8ad+f5ZgXtoSDxPOh" crossorigin="anonymous">
<script type="text/javascript">
	var ctx = document.getElementById('myChart');

</script>
</head>
<body id="page-top">
        <%@ include file="../common/header.jsp" %>

<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">  -->
<!-- 차트 링크 --> 
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script> 
	<section class="page-section" id="services">
            <div class="container mt-5">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">감정 분석 통계</h2>
                </div>
                <div class="row text-center pt-3">
                <div class="container pt-5 pb-5">
 					<canvas id="myChart"></canvas>
 				</div>
 				</div>
			</div> 
	</section>
<!-- 부트스트랩 --> 
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script> 
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 
<script src="../resources/static/js/scripts.js"></script>
<script>
var lb = [];
var datalist = [];
var bgcolorlist = [
	'rgba(255, 99, 132, 0.2)', 
	'rgba(54, 162, 235, 0.2)', 
	'rgba(255, 206, 86, 0.2)', 
	'rgba(75, 192, 192, 0.2)', 
	'rgba(153, 102, 255, 0.2)', 
	'rgba(255, 159, 64, 0.2)',
	'rgba(197, 255, 97, 0.2)',
	'rgba(255, 97, 221, 0.2)'
];
var bordercolorlist = [
	'rgba(255, 99, 132, 1)', 
	'rgba(54, 162, 235, 1)', 
	'rgba(255, 206, 86, 1)', 
	'rgba(75, 192, 192, 1)', 
	'rgba(153, 102, 255, 1)', 
	'rgba(255, 159, 64, 1)',
	'rgba(197, 255, 97, 1)',
	'rgba(255, 97, 221, 1)'
];	

</script>

<c:forEach items="${list }" var="item">
<script>
lb.push("${item.music_emotion}");
datalist.push(${item.cnt});
</script>
</c:forEach>
<script>
//alert(lb)
//alert(datalist)

</script>


<!-- 차트 --> 
<script>

	var ctx = document.getElementById('myChart'); 
	var myChart = new Chart(ctx, { 
		type: 'bar', 
		data: { 
			//labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'], 
			//labels: ['angry', 'fear', 'laugh', 'neutral', 'sad', 'surprise', 'smile', 'talking'], 
			labels: lb,
			datasets: [{ 
				label: '분석 감정 결과', 
				data: datalist, 
				
				
				backgroundColor:  bgcolorlist,
				borderColor: bordercolorlist,
				borderWidth: 1 
				}] 
			}, 
			options: { 
				scales: { 
					yAxes: [{ 
						ticks: { 
							beginAtZero: true 
						} 
					}] 
				} 
			} 
		}); 
</script> 

<%@ include file="../common/footer.jsp" %>
</body>
</html>