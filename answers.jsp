<% @ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<title> Exam Page </title>
</head>

<body>

<%
	
	HttpSession session = request.getSession(false);
	out.println("<center><b>")
	String question = (String) session.getAttribute("question");
	out.println(question)
	out.println("<center></b>")

%>

</body>
</html>