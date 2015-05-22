<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<%@ attribute name="nbrOfPages" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="limit" required="true" type="java.lang.Integer"%>
<%@ attribute name="search" required="false" type="java.lang.String"%>
<%@ attribute name="start" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="end" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="pageNum" required="false" type="java.lang.String"
	description=""%>



<div class="container text-center">


	<ul class="pagination">




		<!-- bouton suivant -->
		<li><c:if test="${start>0}">
				<tags:link body="<span aria-hidden='true'>&laquo;</span>"
					pageNum="0" limit="${limit}" />
				<li><tags:link body="<span aria-hidden='true'>&laquo;</span>"
						pageNum="${start-1}" limit="${limit}">
					</tags:link></li>

			</c:if></li>


		<!-- PAGINATION -->
		<c:choose>


			<c:when test="${start<3}">
		
				<c:forEach var="i" begin="${start}" end="${start+4}">
					<c:choose>
						<c:when test="${start==i}">
							<li class="active"><tags:link body="${i}" pageNum="${i}"
									limit="${limit}" search="${search}"></tags:link></li>
						</c:when>
						<c:otherwise>
							<li><tags:link body="${i}" pageNum="${i}"
									limit="${limit}" search="${search}"></tags:link></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</c:when>



			<c:when test="${end>nbrOfPages}">
				<c:forEach var="i" begin="${start-5}" end="${start}">
				
					<c:choose>
						<c:when test="${start==i}">

							<li class="active"><tags:link body="${i}" pageNum="${i}"
									limit="${limit}" search="${search}"></tags:link></li>
						</c:when>
						<c:otherwise>

							<li><tags:link body="${i}" pageNum="${i}" limit="${limit}"
									search="${search}"></tags:link></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</c:when>

			<c:otherwise>
				<c:forEach var="i" begin="${start}" end="${end-1}">
					<c:choose>
						<c:when test="${start==i-2}">
							<li class="active"><tags:link body="${i-2}" pageNum="${i-2}"
									limit="${limit}" search="${search}"></tags:link></li>
						</c:when>
						<c:otherwise>
							<li><tags:link body="${i-2}" pageNum="${i-2}"
									limit="${limit}" search="${search}"></tags:link></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>

		<!-- bouton suivant -->
		<li><c:if test="${start<nbrOfPages-1}">

				<tags:link body="<span aria-hidden='true'>&raquo;</span>"
					pageNum="${start+1}" limit="${limit}" />
				<li><tags:link body="<span aria-hidden='true'>&raquo;</span>"
						pageNum="${nbrOfPages-1}" limit="${limit}">
					</tags:link></li>

			</c:if></li>



	</ul>


	<div class="btn-group btn-group-sm pull-right" role="group">

		<tags:link body="10" pageNum="0" limit="10" search="${search}"
			boostrapType="btn btn-default" />
		<tags:link body="50" pageNum="0" limit="50" search="${search}"
			boostrapType="btn btn-default" />
		<tags:link body="100" pageNum="0" limit="100" search="${search}"
			boostrapType="btn btn-default" />


	</div>

</div>