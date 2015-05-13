<!DOCTYPE jsp>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" pageEncoding="UTF-8"%>


<html>
<head>

<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="index"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1><spring:message code="label.add" /></h1>

	

					<form action="addComputer" method="POST" name="computerForm"
						onsubmit=" return checkForm()">
						<fieldset>
							<div class="form-group" id="divName">
								<label for="computerName"><spring:message code="label.name" /></label> <input
									type="text" class="form-control" id="name" name="name"
									placeholder="Computer name">
							</div>
							<div class="form-group" id="divIntroduced">
								<label for="introduced"><spring:message code="label.introduced" /></label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="dd-mm-yyyy">
							</div>
							<div class="form-group" id="divDiscontinued">
								<label for="discontinued"><spring:message code="label.discontinued" /></label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" placeholder="dd-mm-yyyy">
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="label.company" /></label> <select
									class="form-control" name="companies" id="companyId">
									<c:forEach items="${listCompanies}" var="company">
										<option value="${company.id}">${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary"
								id="addComputer"> or <a href="index"
								class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="js/jquery.min.js"></script>
	<!-- <script src="js/editComputer.js"></script> -->
</body>
</html>