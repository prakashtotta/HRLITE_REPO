<%
String path = (String)request.getAttribute("filePath");
path = request.getContextPath()+"/download/file?filename="+path;
response.sendRedirect(path);
%>