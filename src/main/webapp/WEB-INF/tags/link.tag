<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="pageNum" required="false" type="java.lang.Integer" description=""%>  
<%@ attribute name="body" required="true" type="java.lang.String" description=""%> 
<%@ attribute name="limit" required="true" type="java.lang.Integer" %>  
<%@ attribute name="search" required="false" type="java.lang.String" %>
<%@ attribute name="colomn" required="false" type="java.lang.String" %>
<%@ attribute name="way" required="false"  %>
<%@ attribute name="boostrapType" required="false" %>






<c:choose>
  <c:when test="${colomn!=null}">
   <a href="index?pageNum=${pageNum}&limit=${limit}&search=${search}&colomn=${colomn}&way=${way}" class="${boostrapType}" >${body}</a>
  </c:when>
  <c:when test="${search==null}">
   <a href="index?pageNum=${pageNum}&limit=${limit}&colomn=${colomn}&way=${way}" class="${boostrapType}" >${body}</a>
  </c:when>
  <c:otherwise>
     <a href="index?pageNum=${pageNum}&limit=${limit}&search=${search}" class="${boostrapType}" >${body}</a>
  </c:otherwise>
</c:choose>
 
