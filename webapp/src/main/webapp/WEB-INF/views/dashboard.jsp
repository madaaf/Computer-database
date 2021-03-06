<!DOCTYPE jsp>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" pageEncoding="UTF-8"%>


<html>
<head>
<title>Computer Database</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">


</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		
		<div id="logout" class="navbar-right">
			<a type="button" class="btn btn-default" href="logout">
				<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
			</a>
		</div>
		
		<div class="container">

			<a class="navbar-brand" href="index?page=1"><spring:message
					code="label.title" /> </a> <a href="?locale=fr" class="pull-right">
				<img id="flag" src="img/flagFr.png" />
			</a> <a href="?locale=en" class="pull-right"> <img id="flag"
				src="img/flagEN.png" />
			</a>


		</div>
	</header>


	<section id="main">
		<div class="container">


			<spring:message code="label.find" var="springFind" />
			<h1 id="homeTitle">${pageS.totalComputer} ${springFind}</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="index" method="GET"
						class="form-inline">
						<spring:message code="label.search" var="springfilter" />
						<input type="search" id="searchbox" name="filter"
							class="form-control" placeholder="${springfilter}" />
						<spring:message code="label.filter" var="springFilter" />
						<input type="submit" id="searchsubmit" value="${springFilter}"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message
							code="label.add" /></a> <a class="btn btn-default" id="editComputer"
						href="#" onclick="$.fn.toggleEditMode();"><spring:message
							code="label.edit" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="deleteComputer" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><spring:message code="label.name" var="springName" /> <spring:message
								code="label.introduced" var="springIntroduced" /> <spring:message
								code="label.discontinued" var="springDiscontinued" /> <spring:message
								code="label.company" var="springCompany" /> <tags:link
								body="${springName}" pageNumber="1" pageSize="${pageS.pageSize}"
								filter="${pageS.filter}" field="computer.name"
								sort="${pageS.sort}">
							</tags:link></th>
						<th><tags:link body="${springIntroduced}" pageNumber="1"
								pageSize="${pageS.pageSize}" filter="${pageS.filter}"
								field="computer.introduced" sort="${pageS.sort}">
							</tags:link></th>
						<!-- Table header for Discontinued Date -->
						<th><tags:link body="${springDiscontinued}" pageNumber="1"
								pageSize="${pageS.pageSize}" filter="${pageS.filter}"
								field="computer.discontinued" sort="${pageS.sort}">
							</tags:link></th>
						<!-- Table header for Company -->
						<th><tags:link body="${springCompany}" pageNumber="1"
								pageSize="${pageS.pageSize}" filter="${pageS.filter}"
								field="company.name" sort="${pageS.sort}">
							</tags:link></th>

					</tr>
				</thead>

				<!-- Browse attribute computers -->
				<c:forEach items="${pageS.listComputers}" var="computer">

					<tbody id="results">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="editComputer?id=${computer.id} " onclick="">${computer.id}
									${computer.name}</a></td>
							<td><c:if test="${computer.introduced!=null}"> ${computer.introduced}</c:if></td>
							<td><c:if test="${computer.discontinued!=null}"> ${computer.discontinued}</c:if></td>
							<td><c:if test="${computer.companyName!=null}">${computer.companyName}</c:if></td>

						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<tags:page totalPage="${pageS.totalPage}" start="${pageS.pageNumber}"
			end="${pageS.pageNumber+5}" pageSize="${pageS.pageSize}" filter="${pageS.filter}"></tags:page>
	</footer>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>