<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String readonlymode = (String)request.getAttribute("readonlymode");

%>
<%
	//response.setHeader("Cache-Control", "no-cache");
	//response.setHeader("Pragma", "no-cache");
	//response.setIntHeader("Expires", 0);
%>
<html>
<head>
<title><%=Constant.getResourceStringValue("aquisition.applicant.assignExam.details",user1.getLocale())%></title>
</head>
<bean:define id="mockquestionsetform" name="mockQuestionSetForm" type="com.form.MockQuestionSetForm" /> 

<style type="text/css">
	#myAutoComplete {
		width:5em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}
	#myAutoComplete1 {
		width:5em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}

  
	</style>
<style>
span1{color:#ff0000;}
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



<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
			 parent.parent.GB_hide();
	   } 
	}

function init(){
	
setTimeout ( "document.mockQuestionSetForm.name.focus(); ", 200 );

}

function savedata(){
	var alertstr = "";
	var showalert=false;
	var name=document.mockQuestionSetForm.name.value.trim();
	var displayname=document.mockQuestionSetForm.displayName.value.trim();
	var timeLimit=document.mockQuestionSetForm.timeLimit.value.trim();
	var attachmentData=document.mockQuestionSetForm.attachmentData.value.trim();
	var filetype=attachmentData.substring(attachmentData.length-5);
	var checkdecimal="no";

	for(i=0;i<timeLimit.length;i++){
	if(timeLimit[i]=="."){
     checkdecimal="yes";
	 break;

	}

}
	
	
	 if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.mandatorymessage.name",user1.getLocale())%><br>";
		showalert = true;
		}
	
     if(displayname == "" || displayname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.mandatorymessage.displayname",user1.getLocale())%><br>";
		showalert = true;
		}
   if(document.mockQuestionSetForm.description.value.length != "" && document.mockQuestionSetForm.description.value.length> 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
     if(timeLimit == "" || timeLimit == null || timeLimit=="0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.validation.timelimit_mand",user1.getLocale())%><br>";
		showalert = true;
		}else if(isNaN(timeLimit)){
         alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.validation.timelimit_not_number",user1.getLocale())%><BR>";

		showalert = true;
      
		}else if(checkdecimal=="yes"){
	

       alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.validation.timelimit_not_number",user1.getLocale())%>. <BR>";

		showalert = true;

		}

       if(attachmentData == "" || attachmentData == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.validation.attachment_mandatory",user1.getLocale())%> <br>";
		showalert = true;
		}else if(filetype!=".xlsx"){
	     alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.validation.attached_not_xlsx",user1.getLocale())%> <br>";
		 showalert = true;

       }
		
       
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.mockQuestionSetForm.action = "mockquestionset.do?method=saveMockQuestionSet";
      document.mockQuestionSetForm.submit();

 
	}

function updatedata(){

	var alertstr = "";
	var showalert=false;
		var name=document.mockQuestionSetForm.name.value.trim();
	var displayname=document.mockQuestionSetForm.displayName.value.trim();
	var timeLimit=document.mockQuestionSetForm.timeLimit.value.trim();
	var attachmentData=document.mockQuestionSetForm.attachmentData.value.trim();
	
	var filetype=attachmentData.substring(attachmentData.length-5);
	var checkdecimal="no";

	for(i=0;i<timeLimit.length;i++){
	if(timeLimit[i]=="."){
     checkdecimal="yes";
	 break;

	}

}
	
	
	 if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.mandatorymessage.name",user1.getLocale())%><br>";
		showalert = true;
		}
	
     if(displayname == "" || displayname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.mandatorymessage.displayname",user1.getLocale())%><br>";
		showalert = true;
		}
   if(document.mockQuestionSetForm.description.value.length != "" && document.mockQuestionSetForm.description.value.length> 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
     if(timeLimit == "" || timeLimit == null || timeLimit=="0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.validation.timelimit_mand",user1.getLocale())%><br>";
		showalert = true;
		}else if(isNaN(timeLimit)){
         alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.validation.timelimit_not_number",user1.getLocale())%><BR>";

		showalert = true;
      
		}else if(checkdecimal=="yes"){
	

       alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.validation.timelimit_not_number",user1.getLocale())%>. <BR>";

		showalert = true;

		}
		var uploadedFileName='<%=mockquestionsetform.getAttachmentName()%>';
		if(uploadedFileName == "" && uploadedFileName == null){
	       if(attachmentData == "" || attachmentData == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.validation.attachment_mandatory",user1.getLocale())%> <br>";
			showalert = true;
			}else if(filetype!=".xlsx"){
		     alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.mockquestionset.createpage.validation.attached_not_xlsx",user1.getLocale())%> <br>";
			 showalert = true;
	
	       }
		}
		
       
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	 document.mockQuestionSetForm.action = "mockquestionset.do?method=updateMockQuestionSet&catid="+'<bean:write name="mockQuestionSetForm" property="catId"/>';
    document.mockQuestionSetForm.submit();

 
	}
function deletedata(){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  document.mockQuestionSetForm.action = "mockquestionset.do?method=deleteMockQuestionSet&catid="+'<bean:write name="mockQuestionSetForm" property="catId"/>';

		  document.mockQuestionSetForm.submit();
	  }
	
}

function closewindow(){
	parent.parent.GB_hide();

}


function deleteattachment(){
	//alert("hi ..");
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  document.mockQuestionSetForm.action = "mockquestionset.do?method=deleteAttachment&catid="+<%=mockquestionsetform.getCatId()%>+"&attachment=delete";
		  document.mockQuestionSetForm.submit();
	  }
	}

</script>

<%
String saveMockQuestionSet = (String)request.getAttribute("saveMockQuestionSet");
	
if(saveMockQuestionSet != null && saveMockQuestionSet.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.mockquestionset.savemessage",user1.getLocale())%></font>
			 <!-- <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%if(saveMockQuestionSet != null && saveMockQuestionSet.equals("error")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.mockquestionset.fileformatwrong",user1.getLocale())%></font>
			 </td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updateMockQuestionSet = (String)request.getAttribute("updateMockQuestionSet");
	
if(updateMockQuestionSet != null && updateMockQuestionSet.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.mockquestionset.updatemessage",user1.getLocale())%></font>
			<!-- <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String deleteMockQuestionSet = (String)request.getAttribute("deleteMockQuestionSet");
	
if(deleteMockQuestionSet != null && deleteMockQuestionSet.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.mockquestionset.deletemessage",user1.getLocale())%></font>
			<!-- <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>--></td>
		</tr>
		
	</table>
</div>
<%}else{%>	
<body class="yui-skin-sam" onload="init()">
<html:form action="/mockquestionset.do?method=saveMockQuestionSet" enctype="multipart/form-data">
	<%if(! StringUtils.isNullOrEmpty(readonlymode) && readonlymode.equals("yes")){ %>
	<br>
	<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.assignExam.details",user1.getLocale())%></legend>
	<table border="0" width="100%">
		<tr>
			<td><%=Constant.getResourceStringValue("admin.businessRule.name",user1.getLocale())%></td>
			
			<td><bean:write name="mockQuestionSetForm" property="name"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.mockquestionset.displayname",user1.getLocale())%></td>
			<td><bean:write name="mockQuestionSetForm" property="displayName"/></td>
		</tr>

		
		<tr>
			<td><%=Constant.getResourceStringValue("onboarding.taskdef.page.table.configuration.taskDesc",user1.getLocale())%></td>

			<td><bean:write name="mockQuestionSetForm" property="description"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.mockquestionset.time_limit",user1.getLocale())%></td>
			<td><bean:write name="mockQuestionSetForm" property="timeLimit"/> <%=Constant.getResourceStringValue("admin.mockquestionset.in_minute",user1.getLocale())%></td>
			
		</tr>

         <tr>
			<td><%=Constant.getResourceStringValue("admin.mockquestionset.attachment",user1.getLocale())%></td>
			<td><bean:write name="mockQuestionSetForm" property="attachmentName"/></td>
			
		</tr>
	</table>
	</fieldset>
	<%}else{ %>
		




<div align="center" class="div">

	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td></td>
			<td></td>
		</tr>
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.businessRule.name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="name" size="60" maxlength="400"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.mockquestionset.displayname",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="displayName" size="60" maxlength="400"/></td>
		</tr>

		
		<tr>
			<td><%=Constant.getResourceStringValue("onboarding.taskdef.page.table.configuration.taskDesc",user1.getLocale())%></td>
			<td><html:textarea property="description" rows="3" cols="57"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.mockquestionset.time_limit",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="timeLimit" size="10" maxlength="400"/> <%=Constant.getResourceStringValue("admin.mockquestionset.in_minute",user1.getLocale())%></td>
		</tr>

         <tr>
			<td><%=Constant.getResourceStringValue("admin.mockquestionset.attachment",user1.getLocale())%><font color="red">*</font></td>
			<td>
			
			<html:file property="attachmentData"/> 
		

			<input type="hidden" name="existfile" value="<%=(mockquestionsetform.getAttachmentName()==null)?"":mockquestionsetform.getAttachmentName()%>"/>
			<% if(!StringUtils.isNullOrEmpty( mockquestionsetform.getAttachmentName())){  %>
			
			<a href="<%=request.getContextPath()%>/download/file?filename=<%=mockquestionsetform.getCreatedBy()+File.separator+mockquestionsetform.getAttachmentName()%>" ><bean:write name="mockQuestionSetForm" property="attachmentName"/></a>
			&nbsp;&nbsp;<a href="#" onClick="deleteattachment();return false;"><img src="jsp/images/delete.gif" border="0" alt="delete resume" title="delete attachment" height="20"  width="19"/></a>
			<%}%> 
			
			
			
			</td>
			
			</tr>

		 <tr>
			<td><%=Constant.getResourceStringValue("admin.sample.exam.xml.file",user1.getLocale())%></td>
			<td><a href="<%=request.getContextPath()%>/download/file?isfromjsp=yes&filename=questionset.xlsx">questionset.xlsx</a></td>
		</tr>
		  		</table>
		
	  <br>

	<table border="0" width="100%">
		<tr>
			<td><%if (mockquestionsetform.getCatId()!=0){%>

			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
		
		</table>
		</div>

		<%}%>

</html:form>
	
</body>

		<%}%>