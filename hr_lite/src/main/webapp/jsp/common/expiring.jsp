
<%
String isexp = "";
if (!session.isNew()) {   // Session is valid
isexp ="no";
session.setMaxInactiveInterval(7200);

} else { 
	isexp ="yes";
	//response.sendRedirect("/login.do?method=login");
	} 

%>

<script>    
var isexp = '<%=isexp%>';
if(isexp == 'yes'){
	alert("Oops !!! Your session has already expired !!!");
	opener.location.reload();
}
self.close();
</script> 