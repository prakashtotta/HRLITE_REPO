<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<bean:define id="form" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<html>
<head>
<title><%=Constant.getResourceStringValue("hr.applicant.assign.exam",user1.getLocale())%></title>

<script language="javascript">


function assignExam(){

		var alertstr = "";
		var showalert=false;
		//var numbers=/^[0-9]+$/;
		//{1,2} 2 for 2 digits ,change both the places if u wanna reduce digits
		var numbers=/^[0-9]{1,2}([,.]{1}[0-9]{1,2})?$/;

		var mockexamsetId=document.jobRequisitionForm.mockexamsetId.value.trim();
		var score=document.jobRequisitionForm.passPercentage.value.trim();
		if(mockexamsetId == "" || mockexamsetId == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.requisition.exam.required",user1.getLocale())%><br>";
			showalert = true;
			}
		if(numbers.test(score)==false){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.requisition.exam",user1.getLocale())%><br>";
			showalert = true;
			}

		if (showalert){
		     alert(alertstr);
		     return false;
		}

	  document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionForm.action = "jobreq.do?method=assignExamToReq&jobreqid="+'<bean:write name="jobRequisitionForm" property="jobreqId"/>';
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
<html:form action="/jobreq.do?method=assignExamToReq">
<br>

<span id='progressbartable1'>
<img src="jsp/images/indicator.gif" height="20"  width="40"/>
</span>
<b><%=Constant.getResourceStringValue("hr.applicant.assign.exam",user1.getLocale())%></b>
<br><br>

<%

String assignExam = (String)request.getAttribute("assignExam");

if(assignExam != null && assignExam.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("Requisition.assign.exam.success.msg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </a> --></td>
			</tr>
		
	</table>
</div>

<%}%>

 <div class="div">
 <table border="0" width="100%">

<tr>
			<td><%=Constant.getResourceStringValue("hr.applicant.exam",user1.getLocale())%><font color="red">*</font></td>
			<td width="70%">
			<html:select  property="mockexamsetId" styleClass="list titleHintBox"  title="<%=Constant.getResourceStringValue("Requisition.exam.help",user1.getLocale())%>">
			<option value=""></option>	
			<bean:define name="jobRequisitionForm" property="mockexamsList" id="mockexamsList" />

            <html:options collection="mockexamsList" property="catId"  labelProperty="name"/>
			</html:select>
			</td>
		</tr>

<tr>
<td>

<%=Constant.getResourceStringValue("hr.applicant.exam.passpercentage",user1.getLocale())%>
</td>
<td width="70%">

<html:text property="passPercentage" size="20" styleClass="text titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.exam.passpercentage.help",user1.getLocale())%>"/>

</td>
</tr>
<tr>
<td>

<%=Constant.getResourceStringValue("aquisition.applicant.your_comments",user1.getLocale())%>
</td>
<td  width="70%">

<html:textarea property="mcomckexamsetcomment" cols="50" rows="4" styleClass="text titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.exam.your_comments.help",user1.getLocale())%>"/>

</td>
</tr>

<tr>
<td> </td>
<td width="70%">
	
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.applicant.assign.exam",user1.getLocale())%>" onClick="assignExam()" class="button">
 	
  </td>
</tr>
</table>
</div>
</html:form>
</body>
</html>