<%@ include file="../common/include.jsp" %>
<html>
<bean:define id="designationForm" name="designationForm" type="com.form.DesignationForm" />
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

String designationsaved = (String)request.getAttribute("designationsaved");
String designationupdated = (String)request.getAttribute("designationupdated");
String designationdeleted = (String)request.getAttribute("designationdeleted");
%>
<style>
span1{color:#ff0000;}
</style>
<style type="text/css">
	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
/* Clear calendar's float, using dialog inbuilt form element */
    #container .bd form {
        clear:left;
    }

    /* Have calendar squeeze upto bd bounding box */
    #container .bd {
        padding:0;
    }

    #container .hd {
        text-align:left;
    }

    /* Center buttons in the footer */
    #container .ft .button-group {
        text-align:center;  
    }

    /* Prevent border-collapse:collapse from bleeding through in IE6, IE7 */
    #container_c.yui-overlay-hidden table {
        *display:none;
    }

    /* Remove calendar's border and set padding in ems instead of px, so we can specify an width in ems for the container */
    #cal {
        border:none;
        padding:1em;
    }
legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
    /* Datefield look/feel */
    .datefield {
        position:relative;
        top:10px;
        left:10px;
        white-space:nowrap;
        border:1px solid black;
        background-color:#eee;
        width:25em;
        padding:5px;
    }

    .datefield input,
    .datefield button,
    .datefield label  {
        vertical-align:middle;
    }

    .datefield label  {
        font-weight:bold;
    }

    .datefield input  {
        width:15em;
    }

    .datefield button  {
        padding:0 5px 0 5px;
        margin-left:2px;
    }

    .datefield button img {
        padding:0;
        margin:0;
        vertical-align:middle;
    }

    /* Example box */
    .box {
        position:relative;
        height:30em;
    }
	</style>
	<script language="javascript"> 

window.name = 'myModal';
</script>
<script language="javascript">

function savedata(){
	var alertstr = "";
		var showalert=false;
		var code = document.designationForm.designationCode.value.trim();	
		var name = document.designationForm.designationName.value.trim();
		var desc = document.designationForm.designationDesc.value.trim();
				 
		if(code == "" || code == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Code_required",user1.getLocale())%><br>";
			showalert = true;
			}
			if(name == "" || name == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
			showalert = true;
			}
			if(desc.length > 800){

		     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
				showalert = true;
			}
		 if (showalert){
	     	alert(alertstr);
	        return false;
	          }
		
		  document.designationForm.action = "designation.do?method=saveDesignation";
	 document.designationForm.submit();
	  
		}
function updatedata(){
	var alertstr = "";
	var showalert=false;
	var code = document.designationForm.designationCode.value.trim();	
	var name = document.designationForm.designationName.value.trim();
	var desc = document.designationForm.designationDesc.value.trim();
	//alert(desc.length);
			 
	if(code == "" || code == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Code_required",user1.getLocale())%><br>";
		showalert = true;
		}
		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		if(desc.length > 800){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
		}
	 if (showalert){
     	alert(alertstr);
        return false;
          }

	  document.designationForm.action = "designation.do?method=updateDesignation&id="+'<bean:write name="designationForm" property="designationId"/>';
 document.designationForm.submit();
	
}
function deleteDesignation(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	document.designationForm.action = "designation.do?method=deleteDesignation&id="+'<bean:write name="designationForm" property="designationId"/>';
	  document.designationForm.submit();
	  }
}
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}

function closewindow(){
	 //self.parent.location.reload();

	/// self.parent.location.reload();
	 parent.parent.GB_hide();
}

function init(){
	setTimeout ( "document.designationForm.designationCode.focus(); ", 200 );
	//document.roleForm.roleCode.focus();
	}
</script>




<%
	
if(designationsaved != null && designationsaved.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>

			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Designation.savemsg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>

		</tr>
		
	</table>
</div>

<%}%>
<%
	
if(designationupdated != null && designationupdated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>

			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Designation.updatemsg",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>

		</tr>
		
	</table>
</div>

<%}%>
<%

if(designationdeleted != null && designationdeleted.equals("yes")){

%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Designation.deletemsg",user1.getLocale())%> </font></td>

			<td><!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>

		</tr>
		
	</table>
</div>
<%}else{%>
<br>
<body class="yui-skin-sam" onload="init()" >
<html:form action="/designation.do?method=saveDesignation" target='myModal'>


	
	<font color = red ><html:errors /> </font>
	<div class="div">
	<table border="0" width="100%">

		
		<tr></tr><tr></tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Designation.DesignationCode",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="designationCode" maxlength="100" size="50"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Designation.DesignationName",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="designationName" maxlength="200" size="50"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%></td>
			<td><html:textarea property="designationDesc" cols="50" rows="4"/></td>
		</tr>
		<tr>
		<td colspan="2"><%if (designationForm.getDesignationId() > 0){%>
			<%
					if(designationForm.getStatus() != null && !designationForm.getStatus().equals("D")){
					%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="delete" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deleteDesignation()" class="button">
					<%}%>
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			</td>
			<td></td>
		</tr>


		</table>
</div>

</html:form>
<%}%>

</body>

