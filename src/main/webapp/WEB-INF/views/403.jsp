<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
    <br/><br/><br/><br/><br/>
<center>
	<h4>HTTP Status 403 - Access is denied</h4>

	<c:choose>
		<c:when test="${empty username}">
			<h4>You do not have permission to access this page!</h4>
		</c:when>
		<c:otherwise>
			<h4>Username : ${username} <br/>You do not have permission to access this page!</h4>
		</c:otherwise>
	</c:choose>
</center>
</body>
</html>