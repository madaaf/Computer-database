<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="page" required="true" type="java.lang.Integer" description=""%>  
<%@ attribute name="body" required="true" type="java.lang.String" description=""%>  


 <a href="index?page=${page}">${body} </a>
 
