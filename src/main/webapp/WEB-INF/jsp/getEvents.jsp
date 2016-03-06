<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Goals Report</title>
</head>
<body>


	<table>
	
		<tr>
			<th>ID</th><th>Minutes</th>
		</tr>
	
		<c:forEach items="${events}" var="event">
		
			<tr>
				
				<td>${event.id}</td><td>${event.name}</td>
				
				<td>
					<table>
						<tr>
							<th>Attendee ID</th><th>Attendee name</th><th>Attendee mail</th><th>Attendee phone</th>
						</tr>
						<c:forEach items="${event.attendee}" var="attendee">
							<tr>
								<td>${attendee.id}</td><td>${attendee.name}</td><td>${attendee.emailAddress}</td><td>${attendee.phone}</td>
							</tr>
						</c:forEach>
						
					</table>
				</td>
				
			</tr>
		
		</c:forEach>
	
	</table>

</body>
</html>