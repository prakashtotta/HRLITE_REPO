

<%@ page isErrorPage="true" %>
<%@ page import="java.io.*" %>

<html>
<head>
<title>
Job Requisition and Interview Management
</title>

</head>

<body bgcolor="white">
<font color="red"><b>We're sorry, but something went wrong : Please contact System Administrator.</b></font>
<br><br>
<% exception.printStackTrace(new PrintWriter(out)); %>
<% //out.println("Server Error : Please contact System Administrator."); %>
<% //out.println(exception.getMessage()); %>



</body>
</html>
