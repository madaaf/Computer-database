<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

    
<%@ attribute name="nbrOfPages" required="true" type="java.lang.Integer" description=""%>   
<%@ attribute name="limit" required="true" type="java.lang.Integer" %> 
<%@ attribute name="search" required="false" type="java.lang.String" %> 
 <%@ attribute name="start" required="true" type="java.lang.Integer" description=""%> 
<%@ attribute name="end" required="true" type="java.lang.Integer" description=""%>
<%@ attribute name="pageNum" required="false" type="java.lang.String" description=""%>



        <div class="container text-center">
         
            <ul class="pagination">
             <!-- bouton retour -->
                <li>
                   <c:if test="${start>0}">
                 	 <tags:link body="<span aria-hidden='true'>&laquo;</span>" pageNum="${start-1}"  limit="${limit}" > </tags:link>
                  </c:if>
              </li>
               <!-- bouton page -->
              <c:forEach var="i" begin="${start}" end="${end}">     
              	<c:if test="${i<nbrOfPages}" >	
             	 	<li> 
             	 	<tags:link body="${i}" pageNum="${i}"  limit="${limit}" search="${search}" > </tags:link>
             	 	</li> 
               	</c:if> 
              </c:forEach>
              <!-- bouton suivant -->          
              <li>
               <c:if test="${end<nbrOfPages}" >
              		<tags:link body="<span aria-hidden='true'>&raquo;</span>" pageNum="${end+1}"  limit="${limit}"/>
                </c:if>
            </li>
      		
        </ul>
 	
 	
        <div class="btn-group btn-group-sm pull-right" role="group" >

      	 	<tags:link body="10" pageNum="0" limit="10" search="${search}"   boostrapType="btn btn-default" />
      	 	<tags:link body="50" pageNum="0" limit="50" search="${search}" boostrapType="btn btn-default"/>
      	 	<tags:link body="100" pageNum="0" limit="100" search="${search}" boostrapType="btn btn-default"/>
      	 
        </div>
       </div>