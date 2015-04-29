<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

    
<%@ attribute name="nbrOfPages" required="true" type="java.lang.Integer" description=""%>   
<%@ attribute name="limit" required="true" type="java.lang.Integer" %> 
<%@ attribute name="search" required="false" type="java.lang.String" %> 
 <%@ attribute name="start" required="true" type="java.lang.Integer" description=""%> 
<%@ attribute name="fin" required="true" type="java.lang.Integer" description=""%>



        <div class="container text-center">
         
            <ul class="pagination">
             <!-- bouton retour -->
                <li>
                   <c:if test="${start>0}">
                 	 <tags:link body="<span aria-hidden='true'>&laquo;</span>" page="${start-1}"  limit="${limit}" > </tags:link>
                  </c:if>
              </li>
               <!-- bouton page -->
              <c:forEach var="i" begin="${start}" end="${fin}">     
              	<c:if test="${i<nbrOfPages}" >	
             	 	<li> 
             	 	<tags:link body="${i}" page="${i}"  limit="${limit}" search="${search}" > </tags:link>
             	 	</li> 
               	</c:if> 
              </c:forEach>
              <!-- bouton suivant -->          
              <li>
               <c:if test="${fin<nbrOfPages}" >
              		<tags:link body="<span aria-hidden='true'>&raquo;</span>" page="${fin+1}"  limit="${limit}"/>
                </c:if>
            </li>
      		
        </ul>
 	
 	
        <div class="btn-group btn-group-sm pull-right" role="group" >

      	 	<tags:link body="10" page="0" limit="10" search="${search}"   boostrapType="btn btn-default" />
      	 	<tags:link body="50" page="0" limit="50" search="${search}" boostrapType="btn btn-default"/>
      	 	<tags:link body="100" page="0" limit="100" search="${search}" boostrapType="btn btn-default"/>
      	 
        </div>
       </div>