<%@ include file="../header.jsp" %>

<bean:define id="aform" name="fbUserCertificationsForm" type="network.form.FbUserCertificationsForm" />

<%

FaceBookUser ownuser = freader.getOwnUserDataForUI(fuser.getFacebookid(),fuser.getSessionKey());

List certificationList = BOFactory.getFbUserBO().getFBUserCertificationsListByUserId(fuser.getUserId());
FbUserCertifications fbUserCertifications1=null;
FbUserCertifications fbUserCertifications2=null;
FbUserCertifications fbUserCertifications3=null;
FbUserCertifications fbUserCertifications4=null;
FbUserCertifications fbUserCertifications5=null;
if(certificationList.size() > 0){
	fbUserCertifications1 = (FbUserCertifications)certificationList.get(0);
	fbUserCertifications2 = (FbUserCertifications)certificationList.get(1);
	fbUserCertifications3 = (FbUserCertifications)certificationList.get(2);
	fbUserCertifications4 = (FbUserCertifications)certificationList.get(3);
	fbUserCertifications5 = (FbUserCertifications)certificationList.get(4);
}

String savecertifications=(String)request.getAttribute("savecertifications");

%>
<html>
<head>
	<title>Update Certifications</title>

<script language="javascript">
function savedata(){
	var cert1 = document.fbUserCertificationsForm.certification1.value;
	var cert2 = document.fbUserCertificationsForm.certification2.value;
	var cert3 = document.fbUserCertificationsForm.certification3.value;
	var cert4 = document.fbUserCertificationsForm.certification4.value;
	var cert5 = document.fbUserCertificationsForm.certification5.value;
	
	var org1 = document.fbUserCertificationsForm.org1.value;
	var org2 = document.fbUserCertificationsForm.org2.value;
	var org3 = document.fbUserCertificationsForm.org3.value;
	var org4 = document.fbUserCertificationsForm.org4.value;
	var org5 = document.fbUserCertificationsForm.org5.value;

	var year1 = document.fbUserCertificationsForm.year1.value;
	var year2 = document.fbUserCertificationsForm.year2.value;
	var year3 = document.fbUserCertificationsForm.year3.value;
	var year4 = document.fbUserCertificationsForm.year4.value;
	var year5 = document.fbUserCertificationsForm.year5.value;
	
//alert(document.fbUserCertificationsForm.certification1.value+" >> "+document.fbUserCertificationsForm.org1.value+" >> "+document.fbUserCertificationsForm.year1.value)
	  var url = "fbuserfertifications.do?method=updateFBUserCertifications&certification1="+cert1+"&certification2="+cert2+"&certification3="+cert3+"&certification4="+cert4+"&certification5="+cert5+"&org1="+org1+"&org2="+org2+"&org3="+org3+"&org4="+org4+"&org5="+org5+"&year1="+year1+"&year2="+year2+"&year3="+year3+"&year4="+year4+"&year5="+year5;
	  document.fbUserCertificationsForm.action = url;
	  document.fbUserCertificationsForm.submit();
}
function closewindow(){
	parent.parent.GB_hide(); 
	
}
function discard(){
	  var doyou = confirm("Are you sure you would like to discard these changes ( OK = yes Cancel = No)");
	  if (doyou == true){
		//self.parent.location.reload();
	    //window.top.hidePopWin();
	   parent.parent.GB_hide(); 
	   } 
	}

</script>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width:95%;
	border: 1px solid #999;
	padding: 10px;
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;
}

</style>
<link rel="stylesheet" type="text/css" href="jsp/css/button.css" />

</head>

<body class="yui-skin-sam" >

<%
	
if(savecertifications != null && savecertifications.equals("yes")){
%>
<fieldset>
	<table border="0" width="100%">
	
	
		<tr>
			<td><img src='jsp/images/approve2.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="green">Certifications saved successfully</font></td>

		</tr>
		
	</table>
</fieldset>

<%}%>
<html:form action="/fbuserfertifications.do?method=updateFBUserCertifications">
<fieldset>
	<table border="0" width="100%" cellspacing="2" cellpadding="2">


			<tr>
				<td></td>
				<td>Certifications Name</td><td>Organization</td><td>Year</td>
				
			</tr>
			<tr>
				<td>1:</td>
				<td width="25%"><input type="text" name="certification1" size="35" value="<%=fbUserCertifications1 == null?"":fbUserCertifications1.getCertName() %>"></input></td><td width="25%"> <input type="text" name="org1" size="35" value="<%=fbUserCertifications1 == null?"":fbUserCertifications1.getCertOrg() %>"></input></td><td> <input type="text" name="year1" size="5" value="<%=fbUserCertifications1 == null?"":fbUserCertifications1.getYear() %>"></input></td>
				
			</tr>
			<tr>
				<td>2:</td>
				<td><input type="text" name="certification2" size="35" value="<%=fbUserCertifications2 == null?"":fbUserCertifications2.getCertName() %>"></input></td><td> <input type="text" name="org2" size="35" value="<%=fbUserCertifications2 == null?"":fbUserCertifications2.getCertOrg() %>"></input></td><td> <input type="text" name="year2" size="5" value="<%=fbUserCertifications2 == null?"":fbUserCertifications2.getYear() %>"></input></td>
				
			</tr>
			<tr>
				<td>3:</td>
				<td><input type="text" name="certification3" size="35" value="<%=fbUserCertifications3 == null?"":fbUserCertifications3.getCertName() %>"></input></td><td> <input type="text" name="org3" size="35" value="<%=fbUserCertifications3 == null?"":fbUserCertifications3.getCertOrg() %>"></input></td><td> <input type="text" name="year3" size="5" value="<%=fbUserCertifications3 == null?"":fbUserCertifications3.getYear() %>"></input></td>
				
			</tr>
			<tr>
				<td>4:</td>
				<td><input type="text" name="certification4" size="35" value="<%=fbUserCertifications4 == null?"":fbUserCertifications4.getCertName() %>"></input></td><td> <input type="text" name="org4" size="35" value="<%=fbUserCertifications4 == null?"":fbUserCertifications4.getCertOrg() %>"></input></td><td> <input type="text" name="year4" size="5" value="<%=fbUserCertifications4 == null?"":fbUserCertifications4.getYear() %>"></input></td>
				
			</tr>
			<tr>
				<td>5:</td>
				<td><input type="text" name="certification5" size="35" value="<%=fbUserCertifications5 == null?"":fbUserCertifications5.getCertName() %>"></input></td><td> <input type="text" name="org5" size="35" value="<%=fbUserCertifications5 == null?"":fbUserCertifications5.getCertOrg() %>"></input></td><td> <input type="text" name="year5" size="5" value="<%=fbUserCertifications5 == null?"":fbUserCertifications5.getYear() %>"></input></td>
			</tr>

					
	</table>
	<br>
	<table>
		<tr>
			<td><input type="button" class="button" name="save" value="Submit" onClick="savedata()"></td>
			<td><input type="button" class="button" name="cancel" value="Cancel" onClick="discard()"></td>
		</tr>
	</table>
	</fieldset>
</html:form>
</body>
</html>