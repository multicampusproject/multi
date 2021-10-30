<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>relogin 화면</title>
</head>
<body>



	<form id="relogin" action="/cfr2?image=${userProfile }" method="post">

	</form>

	<!-- db자동 저장 script -->
	<script type="text/javascript">
		this.document.getElementById("relogin").submit();
		//document.imageDB_form.submit();
	</script>

</body>
</html>