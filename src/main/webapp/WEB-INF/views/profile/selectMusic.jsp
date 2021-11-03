<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../resources/static/css/styles.css" rel="stylesheet" /><script src="../resources/static/js/scripts.js"></script>
</head>
<body>





	<c:forEach items="${list }" var="musicVO">

		<form id="historyDB_form" action=rec_music method=post>
			<input type='hidden' name="image_name"
				value='<%=(String) session.getAttribute("userProfile")%>'> <input
				type='hidden' name="music_num" value='${musicVO.music_num }'>
			<input type='hidden' name="member_email"
				value='<%=(String) session.getAttribute("userId")%>'>

		</form>
		<!-- db자동 저장 script -->
		<script type="text/javascript">
			this.document.getElementById("historyDB_form").submit();
		</script>



	</c:forEach>


</body>
</html>







