<%@ include file="../common/include.jsp" %>
 <bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<%

String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);



%>
<style type="text/css">

	body {
	margin:0;
	padding:0;
}
</style>
<style type="text/css">
.yui-navset button {
    position:absolute;
    top:0;
    right:0;
}
</style>
<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 97%;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
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

<script language="javascript">


function discard(){
	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		 parent.parent.GB_hide();
	  } 
}

function assignexam(){

	var alertstr = "";
	var showalert=false;
	var mockcatId = document.applicantForm.mockcatId.value;

			 
	if(mockcatId == "" || mockcatId == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.applicant.exams.required",user1.getLocale())%><br>";
		showalert = true;
		}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	   document.applicantForm.action = "applicant.do?method=assignExamSubmit&applicantId=<%=aform.getApplicantId()%>&uuid=<%=aform.getUuid()%>";
	   document.applicantForm.submit();
}


function assignQuestinnaire(){

	var alertstr = "";
	var showalert=false;
	var quetionnaireId = document.applicantForm.quetionnaireId.value;

			 
	if(quetionnaireId == "" || quetionnaireId == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.QuestionsGroup.questionnaire_required",user1.getLocale())%><br>";
		showalert = true;
		}

	 if (showalert){
     	alert(alertstr);
        return false;
          }


	
		document.applicantForm.action = "applicant.do?method=assignQuestionanireSubmit&applicantId=<%=aform.getApplicantId()%>&uuid=<%=aform.getUuid()%>";
		document.applicantForm.submit();
}

function deleteexam(url){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  document.applicantForm.action = url;

		  document.applicantForm.submit();
	  }	
}

function resendQuestinnaire(url){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.resend.msg",user1.getLocale())%>");
	  if (doyou == true){
		  document.applicantForm.action = url;

		  document.applicantForm.submit();
	  }	
}

function resendExam(url){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.applicant.exam.resend.msg",user1.getLocale())%>");
	  if (doyou == true){
		  document.applicantForm.action = url;

		  document.applicantForm.submit();
	  }	
}



</script>

<%

String assignExam = (String)request.getAttribute("assignExam");

if(assignExam != null && assignExam.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
		<tr>
			<td>&nbsp;&nbsp;<img src="jsp/images/whitetick.png"  height="20"  width="19"/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.assignExam.msg",user1.getLocale())%></font></td>
			<!-- <td> <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>-->
		</tr>
		
	</table>
</div>

<%}%>

<%

String resendQuestionnaire = (String)request.getAttribute("resendQuestionnaire");

if(resendQuestionnaire != null && resendQuestionnaire.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td>&nbsp;&nbsp;<img src="jsp/images/whitetick.png"  height="20"  width="19"/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.assignQuestionnaire.resend.msg",user1.getLocale())%></font></td>
			<!-- <td> <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>-->
		</tr>
		
	</table>
</div>

<%}%>

<%

String resendExam = (String)request.getAttribute("resendExam");

if(resendExam != null && resendExam.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td>&nbsp;&nbsp;<img src="jsp/images/whitetick.png"  height="20"  width="19"/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.assignExam.resend.msg",user1.getLocale())%></font></td>
			<!-- <td> <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>-->
		</tr>
		
	</table>
</div>

<%}%>



<%

String assignquestionanire = (String)request.getAttribute("assignquestionanire");

if(assignquestionanire != null && assignquestionanire.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td>&nbsp;&nbsp;<img src="jsp/images/whitetick.png"  height="20"  width="19"/>&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.assignQuestionnaire.msg",user1.getLocale())%></font></td>
			<!-- <td> <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>-->
		</tr>
		
	</table>
</div>

<%}%>

<%

String examalredyassigned = (String)request.getAttribute("examalredyassigned");

if(examalredyassigned != null && examalredyassigned.equals("yes")){
%>
<br>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red">&nbsp;&nbsp;<img src="jsp/images/small_info_icon.png"  height="20"  width="19"/>&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.assignExam.exist",user1.getLocale())%></font></td>
			<!-- <td> <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>-->
		</tr>
		
	</table>
</div>
<br>
<%}%>

<%

String questionanirealredyassigned = (String)request.getAttribute("questionanirealredyassigned");

if(questionanirealredyassigned != null && questionanirealredyassigned.equals("yes")){
%>
<br>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="red">&nbsp;&nbsp;<img src="jsp/images/small_info_icon.png"  height="20"  width="19"/>&nbsp;<%=Constant.getResourceStringValue("aquisition.applicant.assignQuestionnaire.exist",user1.getLocale())%></font></td>
			<!-- <td> <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a></td>-->
		</tr>
		
	</table>
</div>
<br>
<%}%>

<%
String tabselected2 = (String)request.getAttribute("tabselected2");


if(tabselected2 != null && tabselected2.equals("yes")){
	tabselected2="class=\"selected\"";
}
%>

<body class="yui-skin-sam">
<html:form action="/applicant.do?method=assignExamSubmit">

<div id="demo" class="yui-navset">
    <ul class="yui-nav">
        <li class="selected"><a href="#tab1"><em><%=Constant.getResourceStringValue("hr.applicant.exams",user1.getLocale())%></em></a></li>
		<li <%=tabselected2%>><a href="#tab2"><em><%=Constant.getResourceStringValue("hr.applicant.Questionnaires",user1.getLocale())%></em></a></li>
  </ul>  
	
<div class="yui-content">
        <div id="tab1"> <br>
<fieldset><legend><b><%=Constant.getResourceStringValue("hr.applicant.assign.exam",user1.getLocale())%> </legend></b>



<table  border="0" width="100%">

<tr>
			<td><%=Constant.getResourceStringValue("hr.applicant.exam",user1.getLocale())%><font color="red">*</font></td>
			<td>
			<html:select  property="mockcatId">
			<html:option value=""></html:option>
			<bean:define name="applicantForm" property="mockCatList" id="mockCatList" />

            <html:options collection="mockCatList" property="catId"  labelProperty="name"/>
			</html:select>
			</td>
		</tr>

<tr>
<td>

<%=Constant.getResourceStringValue("hr.applicant.exam.passpercentage",user1.getLocale())%>
</td>
<td>

<html:text property="passPercentage" size="20" value=""/>

</td>
</tr>
<tr>
<td>

<%=Constant.getResourceStringValue("aquisition.applicant.your_comments",user1.getLocale())%>
</td>
<td>

<html:textarea property="comment" cols="60" rows="4"/>

</td>
</tr>

<tr>
<td> </td>
<td>
	
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.applicant.assign.exam",user1.getLocale())%>" onClick="assignexam()" class="button">
 	

 	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
 </td>
</tr>
</table>

</fieldset>

<br><br>
<fieldset><legend><b><%=Constant.getResourceStringValue("hr.applicant.assign.exams",user1.getLocale())%></legend></b>

<%
List mocktests = aform.getMocktestList();
if(mocktests != null && mocktests.size()>0){
%>

<table  border="1" width="100%">

<tr>
<td>Exam name</td><td>By</td><td>On</td><td>Started on</td><td>Finished on</td><td>% got </td>
<td>Passing score</td><td>Result</td><td></td>
</tr>

<% for(int i=0;i<mocktests.size();i++){
MockTest  mocktest = (MockTest)	mocktests.get(i);
String deleteurl = "applicant.do?method=deleteExam&applicantId="+mocktest.getApplicantId()+"&uuid="+mocktest.getUuid()+"&testid="+mocktest.getTestId();

String examresendurl = "applicant.do?method=resendExam&applicantId="+mocktest.getApplicantId()+"&uuid="+mocktest.getUuid()+"&testid="+mocktest.getTestId();
%>

<tr>
<td>
<% if(mocktest.getEndTime() == null){ %>
<a href="#" onClick="deleteexam('<%=deleteurl%>');return false"><img src="jsp/images/delete.gif" border="0" alt="<%=Constant.getResourceStringValue("Requisition.delete_exam",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("Requisition.delete_exam",user1.getLocale())%>" height="20"  width="19"/></a>
<%}%>



	
<a href="#" onClick=window.open('<%=request.getContextPath()%>/jsp/testengine/pmpans.jsp?testid=<%=mocktest.getTestId()%>&uuid=<%=mocktest.getUuid()%>&applicantid=<%=mocktest.getApplicantId()%>'); ><%=mocktest.getCat().getName()%></a>


</td><td><%=mocktest.getCreatedBy()%></td><td><%=DateUtil.convertSourceToTargetTimezone(mocktest.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td><td><%=DateUtil.convertSourceToTargetTimezone(mocktest.getStartTime(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td><td><%=DateUtil.convertSourceToTargetTimezone(mocktest.getEndTime(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
<td>
<% if(mocktest.getEndTime() != null){ %>
<%=mocktest.getPercentage()%>
<%}%>
</td>
<td><%=mocktest.getPassPercentage()%></td><td><%=mocktest.getResult()%></td>
<td>
<%if(! StringUtils.isNullOrEmpty(mocktest.getResult()) && mocktest.getResult().equals("ASSIGNED")){ %>
<a href="#" onClick="resendExam('<%=examresendurl%>');return false"><%=Constant.getResourceStringValue("Requisition.resend",user1.getLocale())%>
<%}%>
</td>
</tr>

<%}%>

</table>
<%}else{%>
<%=Constant.getResourceStringValue("hr.applicant.not.assigned.exam",user1.getLocale())%>
<%}%>

</fieldset>

</div>

        <div id="tab2">
		<br>

<fieldset><legend><b><%=Constant.getResourceStringValue("hr.applicant.assign.Questionnaire",user1.getLocale())%></legend></b>




<table  border="0" width="100%">

<tr>
			<td><%=Constant.getResourceStringValue("hr.applicant.Questionnaire",user1.getLocale())%><font color="red">*</font></td>
			<td>
			<html:select  property="quetionnaireId">
			<html:option value=""></html:option>
			<bean:define name="applicantForm" property="questionGroupList" id="questionGroupList" />

            <html:options collection="questionGroupList" property="questiongroupId"  labelProperty="questiongroupName"/>
			</html:select>
			</td>
		</tr>


<tr>
<td>

<%=Constant.getResourceStringValue("hr.applicant.Questionnaire.heading",user1.getLocale())%>
</td>
<td>

<html:textarea property="headingComment" cols="60" rows="4"/>

</td>
</tr>

<tr>
<td> </td>
<td>
	
	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.applicant.assign.Questionnaire",user1.getLocale())%>" onClick="assignQuestinnaire()" class="button">
 	

 	<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
 </td>
</tr>
</table>

</fieldset>

<br><br>
<fieldset><legend><b><%=Constant.getResourceStringValue("hr.applicant.assign.Questionnaires",user1.getLocale())%></legend></b>

<%
List quetionnaires = aform.getQuetionnaireList();
if(quetionnaires != null && quetionnaires.size()>0){
%>

<table  border="0" width="100%">

<tr>
<td><%=Constant.getResourceStringValue("hr.applicant.Questionnaire",user1.getLocale())%></td><td><%=Constant.getResourceStringValue("hr.applicant.Questionnaire.assigned.by",user1.getLocale())%></td><td><%=Constant.getResourceStringValue("hr.applicant.Questionnaire.assigned.on",user1.getLocale())%></td><td><%=Constant.getResourceStringValue("hr.applicant.assign.Questionnaires.Answered.on",user1.getLocale())%></td> <td></td>
</tr>

<% for(int i=0;i<quetionnaires.size();i++){
QuestionGroupApplicants  qnsgroup = (QuestionGroupApplicants)	quetionnaires.get(i);
String deleteurl = "applicant.do?method=deleteQuestionnaire&applicantId="+qnsgroup.getApplicantId()+"&uuid="+qnsgroup.getUuid()+"&testid="+qnsgroup.getQnsGrpAppId();

String questionaireurl = "applicant.do?method=resendQuestionnaire&applicantId="+qnsgroup.getApplicantId()+"&uuid="+qnsgroup.getUuid()+"&gpid="+qnsgroup.getQnsGrpAppId();

%>

<tr>
<td>
<% if(qnsgroup.getEndTime() == null){ %>
<a href="#" onClick="deleteexam('<%=deleteurl%>');return false"><img src="jsp/images/delete.gif" border="0" alt="<%=Constant.getResourceStringValue("Requisition.delete_exam",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("Requisition.delete_exam",user1.getLocale())%>" height="20"  width="19"/></a>
<%}%>

	
<a href="#" onClick=window.open('<%=request.getContextPath()%>/questiongroup.do?method=questionnaire&gpid=<%=qnsgroup.getQnsGrpAppId()%>&uuid=<%=qnsgroup.getUuid()%>&applicantid=<%=qnsgroup.getApplicantId()%>')><%=qnsgroup.getQuestiongroup().getQuestiongroupName()%></a>


</td><td>

<%=qnsgroup.getCreatedBy()%></td><td><%=DateUtil.convertSourceToTargetTimezone(qnsgroup.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td><td><%=DateUtil.convertSourceToTargetTimezone(qnsgroup.getEndTime(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td><td>
<%//if(! StringUtils.isNullOrEmpty(qnsgroup.getResult()) && qnsgroup.getResult().equals("ASSIGNED")){ %>
<a href="#" onClick="resendQuestinnaire('<%=questionaireurl%>');return false"><%=Constant.getResourceStringValue("Requisition.resend",user1.getLocale())%>

<%//}%>
</td>
<td>

</tr>

<%}%>

</table>
<%}else{%>
<%=Constant.getResourceStringValue("hr.applicant.not.assigned.Questionnaires",user1.getLocale())%>
<%}%>

</fieldset>



		</div>
    </div>
</div>

</html:form>

</body>

<script>
(function() {
    var tabView = new YAHOO.widget.TabView('demo');
    
    var addTab = function() {
        var labelText = window.prompt('<%=Constant.getResourceStringValue("hr.applicant.assign.exam",user1.getLocale())%>');
        var content = window.prompt('<%=Constant.getResourceStringValue("hr.applicant.assign.exam",user1.getLocale())%>');
        if (labelText && content) {
            tabView.addTab( new YAHOO.widget.Tab({ label: labelText, content: content }) );
        }
    };

    //var button = document.createElement('button');
    //button.innerHTML = 'add tab';

    //YAHOO.util.Event.on(button, 'click', addTab);
    //tabView.appendChild(button);

    //YAHOO.log("The example has finished loading; as you interact with it, you'll see log messages appearing here.", "info", "example");
})();
</script>