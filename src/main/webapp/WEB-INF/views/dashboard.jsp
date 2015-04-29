<!DOCTYPE jsp>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
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
        <div class="container">
            <a class="navbar-brand" href="index?page=0"> Application - Computer Database </a>
        </div>
    </header>
    
 
    <section id="main">
        <div class="container">
        <!-- when computers are deleted -->
        <c:if test="${deletedSuccess!=null}">
       	 <div class="alert alert-success" id="alertDeletedComputer"> You successfully delete your computers. </div>     
        </c:if>
            
            <h1 id="homeTitle">
                ${nbrComputers} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="index" method="GET" class="form-inline">
                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
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

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                        <tags:link body="Computer Name" page="0" limit="${limit}" search="${search}" colomn="computer.name" way="${way}" > </tags:link>                       
                        </th>
                        <th>
                         <tags:link body="Introduced date" page="0" limit="${limit}" search="${search}" colomn="computer.introduced" way="${way}" > </tags:link>                          
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <tags:link body="Discontinued date" page="0" limit="${limit}" search="${search}" colomn="computer.discontinued" way="${way}" > </tags:link> 
                           
                        </th>
                        <!-- Table header for Company -->
                        <th>
                        	<tags:link body="Company" page="0" limit="${limit}" search="${search}" colomn="company.name" way="${way}" > </tags:link> 
                            
                        </th>

                    </tr>
                </thead>

                <!-- Browse attribute computers -->
                <c:forEach  items="${listComputers}" var="computer">
       
                <tbody id="results">
                    <tr>
                        <td class="editMode"> <input type="checkbox" name="cb" class="cb" value="${computer.id}"> </td>
                        <td> <a href="editComputer?id=${computer.id} " onclick="">${computer.id}  ${computer.name}</a> </td>
                		<td> <c:if test="${computer.introduced!=null}"> ${computer.introduced}</c:if></td>                	
              		 	<td> <c:if test="${computer.discontinued!=null}"> ${computer.discontinued}</c:if></td>
              		  <td> <c:if test="${computer.companyName!=null}">${computer.companyName}</c:if></td>
          
                    </tr>                    
                </tbody>
             </c:forEach>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">

		<tags:page nbrOfPages="${nbrOfPages}" start="${start}" fin="${fin}" limit="${limit}" search="${search}"></tags:page>

    </footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

</body>
</html>