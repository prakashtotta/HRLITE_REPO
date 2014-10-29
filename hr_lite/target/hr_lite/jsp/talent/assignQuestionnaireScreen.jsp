<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="form" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<html>
<head>
<title><%=Constant.getResourceStringValue("hr.applicant.assign.Questionnaire",user1.getLocale())%></title>

<script language="javascript">


function assignQuestionnaire(){

	var alertstr = "";
	var showalert=false;


	var questiongroupId=document.jobRequisitionForm.questiongroupId.value.trim();

	if(questiongroupId == "" || questiongroupId == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.requisition.questionnaire.required",user1.getLocale())%><br>";
		showalert = true;
		}
	if (showalert){
	     alert(alertstr);
	     return false;
	}
	  document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionForm.action = "jobreq.do?method=assignQuestionnaire&jobreqid="+'<bean:write name="jobRequisitionForm" property="jobreqId"/>';
 	  document.jobRequisitionForm.submit();
 
}
function closewindow()
{
	window.close();
}
function init(){
    
	  document.getElementById('progressbartable1').style.display = 'none';  

	  
		}
</script>

</head>
<body onload="init()" class="yui-skin-sam">
<html:form action="/jobreq.do?method=assignQuestionnaire">
<br>

<span id='progressbartable1'>
<img src="jsp/images/indicator.gif" height="20"  width="40"/>
</span>
<b><%=Constant.getResourceStringValue("hr.applicant.assign.Questionnaire",user1.getLocale())%></b>
<br><br>
<%

String assignQuestionnaire = (String)request.getAttribute("assignQuestionnaire");

if(assignQuestionnaire != null && assignQuestionnaire.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
			<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("Requisition.assign.Questionnaire.success.msg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </a> --></td>
			</tr>
		
	</table>
</div>

<%}%>
 <div class="div">
 <table border="0" width="100%" valign="left">

<tr>
			<td><%=Constant.getResourceStringValue("hr.applicant.Questionnaire",user1.getLocale())%><font color="red">*</font></td>
			<td width="70%">
			<html:select  property="questiongroupId" styleClass="list titleHintBox"  title="<%=Constant.getResourceStringValue("Requisition.Questionnaire.help",user1.getLocale())%>">
			<option value=""></option>	
			<bean:define name="jobRequisitionForm" property="questionGroupList" id="questionGroupList" />

            <html:options collection="questionGroupList" property="questiongroupId"  labelProperty="questiongroupName"/>
			</html:select>
			</td>
		</tr>


<tr>
<td>

<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.heading",user1.getLocale())%>
</td>
<td width="70%">

<html:textarea property="questiongroupcomment" cols="50" rows="4" styleClass="text titleHintBox"  title="<%=Constant.getResourceStringValue("Requisition.Questionnaire.your_comment.help",user1.getLocale())%>"/>

</td>
</tr>

<tr>
<td> </td>
<td width="70%">
	
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.applicant.assign.Questionnaire",user1.getLocale())%>" onClick="assignQuestionnaire()" class="button">
 	

 	
 </td>
</tr>
</table>
</div>
</html:form>
</body>
</html>