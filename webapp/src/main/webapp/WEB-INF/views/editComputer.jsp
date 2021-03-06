<!DOCTYPE jsp>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
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
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>
						<spring:message code="label.editComputer" />
					</h1>


					<sf:form method="POST" modelAttribute="computerDTO">
						<fieldset>

							<input type="hidden" value="${computer.id}" id="id" name="id" />
							<div class="form-group" id="divName">
								<label for="computerName"><spring:message
										code="label.name" /></label>
								<sf:input type="text" class="form-control" id="name" name="name"
									placeholder="Computer name" path="name"
									value="${computer.name}" />
							</div>
							<div class="text-center form-group">
								<sf:errors class="alert alert-danger" path="name" />
							</div>
							<div class="form-group" id="divIntroduced">
								<label for="introduced"><spring:message
										code="label.introduced" /></label>
								<sf:input type="date" class="form-control" id="introduced"
									name="introduced" placeholder="dd-mm-yyyy" path="introduced"
									value="${computer.introduced}" />
							</div>
							<div class="text-center form-group">
								<sf:errors class="alert alert-danger" path="introduced" />
							</div>
							<div class="form-group" id="divDiscontinued">
								<label for="discontinued"><spring:message
										code="label.discontinued" /></label>
								<sf:input type="date" class="form-control" id="discontinued"
									value="${computer.discontinued}" name="discontinued"
									placeholder="dd-mm-yyyy" path="discontinued" />
							</div>
							<div class="text-center form-group">
								<sf:errors class="alert alert-danger" path="discontinued" />
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message
										code="label.company" /></label>
								<sf:select class="form-control" name="companies" id="companyId"
									path="companyId">
									<!-- Actual value -->
									<c:if test="${computer.companyName != null}">
										<option value="${computer.companyId}">${computer.companyName}</option>
									</c:if>
									<option value="0">--</option>
									<c:forEach items="${listCompanies}" var="company">
										<option value="${company.id}">${company.name}</option>
									</c:forEach>
								</sf:select>
							</div>
							<div class="actions pull-right">
								<input type="submit" value="Add" class="btn btn-primary"
									id="addComputer"> or <a href="index"
									class="btn btn-default">Cancel</a>
							</div>
						<div class="text-center form-group"><sf:errors class="alert alert-danger" path="companyId"/></div>
						</fieldset>
					</sf:form>
				</div>
			</div>
		</div>
	</section>
	<script src="js/jquery.min.js"></script>
	<!-- <script src="js/editComputer.js"></script>-->
</body>
</html>