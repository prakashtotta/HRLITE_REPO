<%
String path = (String)request.getAttribute("filePath");
if(path != null){
path = request.getContextPath()+"/download/file?filename="+path;
response.sendRedirect(path);
}else{
	response.sendRedirect(null);
}
%> 

