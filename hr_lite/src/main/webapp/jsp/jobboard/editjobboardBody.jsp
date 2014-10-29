<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
 <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>
<bean:define id="form" name="userRegForm" type="com.form.UserRegForm" />
<script language="javascript">

function openURLNew(url){
	window.open(url);
}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}




function updatedata(){
		var content = nicEditors.findEditor('companyInfo').getContent();
		
	document.userRegForm.cinfo.value=content;
	
	  document.userRegForm.action = "jobboard.do?method=updatejobboard";
	  document.userRegForm.submit();

	}

function chnageImage(url){
	window.open(url, "profileimage","location=0,status=0,scrollbars=1,menubar=0,resizable=1,left=10, top=10,width=400,height=200");
}

</script>

 <script type="text/javascript" src="jsp/js/nicEdit.js"></script>
 <script type="text/javascript">
 //<![CDATA[
 bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
 //]]>  
 </script>

<%
String updatejobboard = (String)session.getAttribute("updatejobboard");
	
if(updatejobboard != null && updatejobboard.equals("yes")){
	session.removeAttribute("updatejobboard");
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white">Updated successfully.</font></td>
			<td> </a></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
if(updatejobboard != null && updatejobboard.equals("subdomaintaken")){
	session.removeAttribute("updatejobboard");
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white">Subdomain name already taken, Use other name.</font></td>
			<td> </a></td>
		</tr>
		
	</table>
</div>

<%}%>
	

<body >

<html:form action="/jobboard.do?method=updatejobboard">
<br>
	<font color = red ><html:errors /> </font>


<table border="0" width="100%">
<tr>
<td valign="top">
<table>
	<tr>
	<td>Change logo: <br>
	<%
		    String changeimageurl = "jobboard.do?method=uploadLogoPhotoscr";
			if(form.getLogoPhotoId() == 0){
			%>
			
		<a class="button" href="#" onClick="chnageImage('<%=changeimageurl%>')" rel="lightbox" title="change logo image" rev="<%=changeimageurl%>"><img src="jsp/jobboard/images/logo_green.png" width="165" height="53" alt="" border="0"/></a>
			<%}else{
				String imgurl = "jsp/emp/profilePhoto.jsp?id="+form.getLogoPhotoId();
			%>

			<a class="button" href="#" onClick="chnageImage('<%=changeimageurl%>')" rel="lightbox" title="change logo image" rev="<%=changeimageurl%>"><img src="<%=imgurl%>" border="0" width="165" height="53" alt="" /></a>

			<%}%>
	</td>
    </tr>
	<tr>
	<td>
	Subdomain name :<br>
	<html:text property="subdomain" size="20" maxlength="450"/>.hires360.com
	</td>
    </tr>
</table>

</td>
<td valign="top">

<table>
			<tr>
				
				<td>
				<input type="hidden" name="cinfo"/>
				Company Information:<br>
				<html:textarea property="companyInfo" styleId="companyInfo" cols="70" rows="20"/>
				</td>
			</tr>
</table>

</td>
</tr>
</table>


		<%
			String domainurl = "http://"+form.getSubdomain()+"."+Constant.getValue("domain.name");
	    %>
<br><br>
<table border="0" width="100%">
		<tr>
			<td>
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()">
			
			</td>
			<td>
			<a href="#" onClick="openURLNew('<%=domainurl%>')"><%=form.getSubdomain()+"."+Constant.getValue("domain.name")%></a>
			</td>
		</tr>
		</table>

</html:form>

</body>

