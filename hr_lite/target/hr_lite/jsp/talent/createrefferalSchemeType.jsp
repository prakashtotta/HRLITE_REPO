<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

 <html>
  <HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>
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
</style>

<STYLE>
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation
			{
			background-color:#6677DD;
			text-align:center;
			vertical-align:center;
			text-decoration:none;
			color:#FFFFFF;
			font-weight:bold;
			}
	.TESTcpDayColumnHeader,
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation,
	.TESTcpCurrentMonthDate,
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDate,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDate,
	.TESTcpCurrentDateDisabled,
	.TESTcpTodayText,
	.TESTcpTodayTextDisabled,
	.TESTcpText
			{
			font-family:arial;
			font-size:8pt;
			}
	TD.TESTcpDayColumnHeader
			{
			text-align:right;
			border:solid thin #6677DD;
			border-width:0 0 1 0;
			}
	.TESTcpCurrentMonthDate,
	.TESTcpOtherMonthDate,
	.TESTcpCurrentDate
			{
			text-align:right;
			text-decoration:none;
			}
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDateDisabled
			{
			color:#D0D0D0;
			text-align:right;
			text-decoration:line-through;
			}
	.TESTcpCurrentMonthDate
			{
			color:#6677DD;
			font-weight:bold;
			}
	.TESTcpCurrentDate
			{
			color: #FFFFFF;
			font-weight:bold;
			}
	.TESTcpOtherMonthDate
			{
			color:#808080;
			}
	TD.TESTcpCurrentDate
			{
			color:#FFFFFF;
			background-color: #6677DD;
			border-width:1;
			border:solid thin #000000;
			}
	TD.TESTcpCurrentDateDisabled
			{
			border-width:1;
			border:solid thin #FFAAAA;
			}
	TD.TESTcpTodayText,
	TD.TESTcpTodayTextDisabled
			{
			border:solid thin #6677DD;
			border-width:1 0 0 0;
			}
	A.TESTcpTodayText,
	SPAN.TESTcpTodayTextDisabled
			{
			height:20px;
			}
	A.TESTcpTodayText
			{
			color:#6677DD;
			font-weight:bold;
			}
	SPAN.TESTcpTodayTextDisabled
			{
			color:#D0D0D0;
			}
	.TESTcpBorder
			{
			border:solid thin #6677DD;
			}
</STYLE>

 </HEAD>
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
<bean:define id="refferalSchemetypeform" name="refferalSchemeTypeForm" type="com.form.RefferalSchemeTypeForm" />


<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
	   parent.parent.GB_hide();
	   } 
	}

	function closewindow(){
	  parent.parent.GB_hide();
	 // window.top.hidePopWin();
}
function init(){
setTimeout ( "document.refferalSchemeTypeForm.refferalSchemeName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}
function savedata(){
	var alertstr = "";
	var showalert=false;
		
	var planname = document.refferalSchemeTypeForm.refferalSchemeName.value.trim();
	var desc=document.refferalSchemeTypeForm.refferalSchemeDesc.value.trim();
	if(planname == "" || planname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}	
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_required",user1.getLocale())%><br>";
		showalert = true;
		}	
	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}

	 if (showalert){
     	alert(alertstr);
        return false;
          }

	  document.refferalSchemeTypeForm.action = "refferalschemetype.do?method=saveRefferalSchemeType";

 document.refferalSchemeTypeForm.submit();
 

 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;
		
	var planname = document.refferalSchemeTypeForm.refferalSchemeName.value.trim();
	var desc=document.refferalSchemeTypeForm.refferalSchemeDesc.value.trim();
	if(planname == "" || planname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}		
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_required",user1.getLocale())%><br>";
		showalert = true;
		}	
	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	

document.refferalSchemeTypeForm.action = "refferalschemetype.do?method=updateRefferalSchemeType&id="+'<bean:write name="refferalSchemeTypeForm" property="refferalSchemeTypeId"/>';

 document.refferalSchemeTypeForm.submit();

 
	}
function deletedata(){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	document.refferalSchemeTypeForm.action = "refferalschemetype.do?method=deleteRefferalSchemeType&id="+'<bean:write name="refferalSchemeTypeForm" property="refferalSchemeTypeId"/>';

    document.refferalSchemeTypeForm.submit();
	  }
	
}
	

</script>


<%
String saveRefferalScheme = (String)request.getAttribute("saveRefferalScheme");
%>

<body onload="init()" >
<div align="center" class="msg">
<% if(saveRefferalScheme != null && saveRefferalScheme.equals("delete")){%>
<font color="white"><%=Constant.getResourceStringValue("admin.RefferalSchemeType.deletemsg",user1.getLocale())%></font> 
<!-- <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> -->
<%}else {%>
</div>
<html:form action="/refferalschemetype.do?method=saveRefferalScheme">


<div align="center" class="msg">
	<table border="0" width="100%">
	<font color="white"><html:errors /> </font>
	

<% if(saveRefferalScheme != null && saveRefferalScheme.equals("save")){%>
<font color="white"><%=Constant.getResourceStringValue("admin.RefferalSchemeType.savemsg",user1.getLocale())%></font> 
<!--<a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>-->
	
<%}else if(saveRefferalScheme != null && saveRefferalScheme.equals("update")){%>
<font color="white"><%=Constant.getResourceStringValue("admin.RefferalSchemeType.updatemsg",user1.getLocale())%></font> 
<!--<a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>-->
<%}%>

	
	

			
		<tr>
			<td></td>
			<td></td>
		</tr>
	 
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalSchemeType.name",user1.getLocale())%><span1>*</span1></td>
			<td><html:text property="refferalSchemeName" size="50" maxlength="300"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalSchemeType.uom",user1.getLocale())%><span1>*</span1></td>
			<td>
			<html:select property="uom">
			<bean:define name="refferalSchemeTypeForm" property="uomList" id="uomList" />

            <html:options collection="uomList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalSchemeType.desc",user1.getLocale())%><span1>*</span1></td>
			<td><html:textarea property="refferalSchemeDesc" cols="60" rows="5"/></td>
		</tr>
		</table>
	</div>
		<table border="0" width="100%">
		<tr>
			<td><% if(saveRefferalScheme != null && saveRefferalScheme.equals("edit") || saveRefferalScheme != null && saveRefferalScheme.equals("save") || saveRefferalScheme != null && saveRefferalScheme.equals("update")){%>
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
            <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
	
		
		</table>



</html:form>




</body>
<%}%>
</html>