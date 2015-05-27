<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<%@ attribute name="pageCount" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="limit" required="true" type="java.lang.Integer"%>
<%@ attribute name="filter" required="false" type="java.lang.String"%>
<%@ attribute name="page" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="end" required="true" type="java.lang.Integer"
	description=""%>
<%@ attribute name="pageNum" required="false" type="java.lang.String"
	description=""%>



<div class="container text-center">


	<ul class="pagination">




		<!-- bouton suivant -->
		<li><c:if test="${page>0}">
				<tags:link body="<span aria-hidden='true'>&laquo;</span>"
					pageNum="0" limit="${limit}" />
				<li><tags:link body="<span aria-hidden='true'>&laquo;</span>"
						pageNum="${page-1}" limit="${limit}">
					</tags:link></li>

			</c:if></li>


		<!-- PAGINATION -->
		<c:choose>


			<c:when test="${page<3}">

				<c:forEach var="i" begin="${page}" end="${page+4}">
					<c:choose>
						<c:when test="${page==i}">
							<li class="active"><tags:link body="${i}" pageNum="${i}"
									limit="${limit}" filter="${filter}"></tags:link></li>
						</c:when>
						<c:otherwise>
							<li><tags:link body="${i}" pageNum="${i}"
									limit="${limit}" filter="${filter}"></tags:link></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</c:when>



			<c:when test="${end>pageCount}">
				<c:forEach var="i" begin="${page-5}" end="${page}">

					<c:choose>
						<c:when test="${page==i}">

							<li class="active"><tags:link body="${i}" pageNum="${i}"
									limit="${limit}" filter="${filter}"></tags:link></li>
						</c:when>
						<c:otherwise>

							<li><tags:link body="${i}" pageNum="${i}" limit="${limit}"
									filter="${filter}"></tags:link></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</c:when>

			<c:otherwise>
				<c:forEach var="i" begin="${page}" end="${end-1}">
					<c:choose>
						<c:when test="${page==i-2}">
							<li class="active"><tags:link body="${i-2}" pageNum="${i-2}"
									limit="${limit}" filter="${filter}"></tags:link></li>
						</c:when>
						<c:otherwise>
							<li><tags:link body="${i-2}" pageNum="${i-2}"
									limit="${limit}" filter="${filter}"></tags:link></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>

		<!-- bouton suivant -->
		<li><c:if test="${page<pageCount-1}">

				<tags:link body="<span aria-hidden='true'>&raquo;</span>"
					pageNum="${page+1}" limit="${limit}" />
				<li><tags:link body="<span aria-hidden='true'>&raquo;</span>"
						pageNum="${pageCount-1}" limit="${limit}">
					</tags:link></li>

			</c:if></li>



	</ul>


	<div class="btn-group btn-group-sm pull-right" role="group">

		<tags:link body="10" pageNum="0" limit="10" filter="${filter}"
			boostrapType="btn btn-default" />
		<tags:link body="50" pageNum="0" limit="50" filter="${filter}"
			boostrapType="btn btn-default" />
		<tags:link body="100" pageNum="0" limit="100" filter="${filter}"
			boostrapType="btn btn-default" />


	</div>

</div>