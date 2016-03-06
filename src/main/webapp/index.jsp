<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
</head>
<body>

	<h1>Welcome <sec:authentication property="name"/> !</h1>
	
	<h1>Hello ${event.name}!</h1>
	
	<a href="event.html"> Add Event</a>
	<br>
	<a href="attendee.html"> Add Attendee</a>
	<br>
	<a href="getEvents.html"> Events</a>
	<br>
	<sec:authorize ifAnyGranted="ROLE_ADMIN">
		<a href="getEventReports.html"> Event Reports</a>
	</sec:authorize>
	
	
		
		<br><br><br><br><br><br><br><br><br><br><br><br>
		<c:url var="logoutUrl" value="/logout"/>
    	<form class="form-inline" action="${logoutUrl}" method="post">		
		    <input type="submit" value="Log out" />
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	
		
	
</body>
</html>