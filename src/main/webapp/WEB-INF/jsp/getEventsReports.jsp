<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Events Report</title>
</head>
<body>

	<table>
		<tr>
			<th>Event</th><th>Attendee name</th><th>Attendee email</th><th>Attendee phone</th>
		</tr>
		<c:forEach items="${eventReports}" var="eventReport">
			<tr>
				<td>${eventReport.eventName}</td><td>${eventReport.attendeeName}</td>
				<td>${eventReport.attendeeEmail}</td><td>${eventReport.attendeePhone}</td>
			</tr>
			
		</c:forEach>
			
	</table>

</body>
</html>