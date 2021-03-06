<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="pageNumber" required="false" type="java.lang.Integer" description=""%>  
<%@ attribute name="body" required="true" type="java.lang.String" description=""%> 
<%@ attribute name="pageSize" required="true" type="java.lang.Integer" %>  
<%@ attribute name="filter" required="false" type="java.lang.String" %>
<%@ attribute name="field" required="false" type="java.lang.String" %>
<%@ attribute name="sort" required="false"  %>
<%@ attribute name="boostrapType" required="false" %>


<c:choose>
  <c:when test="${field!=null}">
   <a href="index?pageNumber=${pageNumber}&pageSize=${pageSize}&filter=${filter}&field=${field}&sort=${sort}" class="${boostrapType}" >${body}</a>
  </c:when>
  <c:when test="${filter==null}">
   <a href="index?pageNumber=${pageNumber}&pageSize=${pageSize}&field=${field}&sort=${sort}" class="${boostrapType}" >${body}</a>
  </c:when>
  <c:otherwise>
     <a href="index?pageNumber=${pageNumber}&pageSize=${pageSize}&filter=${filter}" class="${boostrapType}" >${body}</a>
  </c:otherwise>
</c:choose>
 
