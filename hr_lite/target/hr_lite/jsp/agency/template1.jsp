<%@ page errorPage="../common/error.jsp" %>
<%

  String pagetitle="";
  pagetitle=request.getParameter("title");
  if(pagetitle==null)
     pagetitle="Interview Management";
     
  String pageBody =  request.getParameter("htmlbody");  
 // out.println(pageBody);
  if(pageBody == null || "".equals(pageBody)){ 
  	pageBody = "LoginBody.jsp";
  }
  
  //String charSet = com.veritas.vrt.admin.util.AdminUtil.getCharSet(request);
%>

<html>
<head><meta http-equiv="content-type" content="text/html;charset=UTF-8">
<TITLE><%=pagetitle %></TITLE>
</head>


	
	
	
</head>
<BODY>

<LINK href="css/ctr.css" type=text/css rel=stylesheet>

<% 
	//String toDisplay = request.getParameter("diplayLeftNav");
%>

<table width="100%">
<tr>
<!--<td width="10%"></td>-->
<td width="80%">
<table border="0"  cellpadding="0" cellspacing="0" width="100%">

    <tr>
	<td ><%@ include file="../common/AdminHeader.jsp" %></td>
    </tr>
    <tr>
	<td ><%@ include file="../menu-for-applications/demos/menu_agency.jsp" %></td>
    </tr>
    
    <tr >
    <td>
	    <table  border="1" bordercolor="black" cellpadding="0" cellspacing="0" width="100%">
	     <tr valign="top">
	     
	      <td width="100%">
	      	<table border="0" cellpadding="0" cellspacing="0">
	      	<tr>
	      	 <td width="20"><img src="jsp/images/spacer.gif" border="0"></td>
	      	 <td>
		  <jsp:include page="<%= pageBody %>" flush="true"/>
		  	      
		  </td>
		</tr>
	      	<tr><td colspan="2"><img src="jsp/images/spacer.gif" border="0" height="20"></td></tr>
		</table>
	      </td>
	      
	     </tr>
	    </table>
    </td>
    </tr>
    
    <tr>
    
	<td ><%@ include file="../common/Footer.jsp" %></td>
    
    </tr>
    
    
</table>
</td>
<!--<td width="10%"></td>-->
</tr>
</table>

</body>

</html>

















