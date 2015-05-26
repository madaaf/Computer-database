<!DOCTYPE jsp>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false" pageEncoding="UTF-8"%>

<html>


<html>
<head>
<title>Computer Database</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<link href="css/signin.css" rel="stylesheet" media="screen">


</head>
<body>

	<div id="login-box">
		<form class="form-signin" name="f"
			action="/webapp/j_spring_security_check" method="POST">

			<spring:message code="password" var="password" />
			<spring:message code="username" var="username" />

			<h2 class="form-signin-heading">
				<spring:message code="login" />
			</h2>

			<label for="inputName" class="sr-only">${username}</label> <input
				type="text" name="username" id="username" class="form-control"
				placeholder="${username}" required autofocus> <label
				for="inputPassword" class="sr-only">${password}</label> <input
				type="password" name="password" id="password" class="form-control"
				placeholder="${password}" required> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
			<c:if test="${auth == false}">
				<div>
					<font color="red"><spring:message code="error.auth.fail" /></font>
				</div>
			</c:if>
			<button id="login" class="btn btn-lg btn-primary btn-block"
				type="submit">
				<spring:message code="label.login" />
			</button>
		</form>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>