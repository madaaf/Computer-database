<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<%@ attribute name="totalPage" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="pageSize" required="true" type="java.lang.Integer"%>
<%@ attribute name="filter" required="false" type="java.lang.String"%>
<%@ attribute name="start" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="end" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="pageNumber" required="false" type="java.lang.String"
	description=""%>



<div class="container text-center">


	<ul class="pagination">




		<!-- bouton suivant -->
		<li><c:if test="${start>1}">
				<tags:link body="<span aria-hidden='true'>&laquo;</span>"
					pageNumber="1" pageSize="${pageSize}"/>                                                                                           }" />
				<li><tags:link body="<span aria-hidden='true'>&laquo;</span>"
						pageNumber="${start-1}" pageSize="${pageSize}">
					</tags:link></li>

			</c:if></li>


		<!-- PAGINATION -->
		<c:choose>


			<c:when test="${start<3}">
		
				<c:forEach var="i" begin="${start}" end="${start+4}">
					<c:choose>
						<c:when test="${start==i}">
							<li class="active"><tags:link body="${i}" pageNumber="${i}"
									pageSize="${pageSize}" filter="${filter}"></tags:link></li>
						</c:when>
						<c:otherwise>
							<li><tags:link body="${i}" pageNumber="${i}"
									pageSize="${pageSize}" filter="${filter}"></tags:link></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</c:when>



			<c:when test="${end>totalPage}">
				<c:forEach var="i" begin="${start-5}" end="${start}">
				
					<c:choose>
						<c:when test="${start==i}">

							<li class="active"><tags:link body="${i}" pageNumber="${i}"
									pageSize="${pageSize}" filter="${filter}"></tags:link></li>
						</c:when>
						<c:otherwise>

							<li><tags:link body="${i}" pageNumber="${i}" pageSize="${pageSize}"
									filter="${filter}"></tags:link></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</c:when>

			<c:otherwise>
				<c:forEach var="i" begin="${start}" end="${end-1}">
					<c:choose>
						<c:when test="${start==i-2}">
							<li class="active"><tags:link body="${i-2}" pageNumber="${i-2}"
									pageSize="${pageSize}" filter="${filter}"></tags:link></li>
						</c:when>
						<c:otherwise>
							<li><tags:link body="${i-2}" pageNumber="${i-2}"
									pageSize="${pageSize}" filter="${filter}"></tags:link></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>

		<!-- bouton suivant -->
		<li><c:if test="${start<totalPage-1}">

				<tags:link body="<span aria-hidden='true'>&raquo;</span>"
					pageNumber="${start+1}" pageSize="${pageSize}" />
				<li><tags:link body="<span aria-hidden='true'>&raquo;</span>"
						pageNumber="${totalPage-1}" pageSize="${pageSize}">
					</tags:link></li>

			</c:if></li>



	</ul>


	<div class="btn-group btn-group-sm pull-right" role="group">

		<tags:link body="10" pageNumber="1" pageSize="10" filter="${filter}"
			boostrapType="btn btn-default" />
		<tags:link body="50" pageNumber="1" pageSize="50" filter="${filter}"
			boostrapType="btn btn-default" />
		<tags:link body="100" pageNumber="1" pageSize="100" filter="${filter}"
			boostrapType="btn btn-default" />


	</div>

</div>