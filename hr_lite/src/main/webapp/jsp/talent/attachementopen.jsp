<%
String path = (String)request.getParameter("filePath");
response.sendRedirect(path);
%>