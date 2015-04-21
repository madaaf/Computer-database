<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="page" required="true" type="java.lang.Integer" description=""%>  
<%@ attribute name="body" required="true" type="java.lang.String" description=""%> 
<%@ attribute name="limit" required="true" type="java.lang.Integer" %>  



 <a  class="btn btn-default" href="index?page=${page}&limit=${limit}">${body}</a>
 
