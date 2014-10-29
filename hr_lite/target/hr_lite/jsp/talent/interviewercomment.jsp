<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


<bean:define id="form" name="scheduleInterviewForm" type="com.form.ScheduleInterviewForm" />
<%
String ststusmsg="";
String status = form.getStatus();
long applicantId = form.getApplicantId();
int eventype = form.getEventType();

System.out.println("eventype : "+eventype);
System.out.println("status : "+status);


%>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 600px;
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

<script language="javascript">

function isIntFloatCheck(value){
if(isInteger(value) || isFloat(value) || value == '' ){

}else{
alert("<%=Constant.getResourceStringValue("aquisition.applicant.OnlyIntegerFloatalert",user1.getLocale())%>");
 return false;
}
}


function isFloat(value) {
            if (/\./.test(value)) {

                return true;

            } else {
                
                return false;

            }
          }

function isInteger (s) 
{ 
var i; 

if (isEmpty(s)) 
if (isInteger.arguments.length == 1) return 0; 
else return (isInteger.arguments[1] == true); 

for (i = 0; i < s.length; i++) 
{ 
var c = s.charAt(i); 

if (!isDigit(c)) return false; 
} 

return true; 
} 

function isEmpty(s) 
{ 
return ((s == null) || (s.length == 0)) 
} 

function isDigit (c) 
{ 
return ((c >= "0") && (c <= "9")) 
} 


function addotherroottext(){
	var rejectionreasonName = document.scheduleInterviewForm.rejectionreasonId.value;
	if(rejectionreasonName == '-1'){
	 var d=document.getElementById("savetext");
	 d.innerHTML="<input type='text' size='50' maxlength='500' name='otherrootcause'>";
	}else{
	var d=document.getElementById("savetext");
	 d.innerHTML="";
	}
}
function feedbackonchange(){
	var feedback = document.scheduleInterviewForm.feedback.value;
	var rejectionreasonName = document.scheduleInterviewForm.rejectionreasonId.value;
	if(feedback == "2"){
		document.getElementById("rrrotcause").style.display ="inline";
	document.getElementById("rrrotcausetabel").style.display ="inline";
	if(rejectionreasonName == '-1'){
	 var d=document.getElementById("savetext");
	 d.innerHTML="<input type='text' size='50' name='otherrootcause'>";
	}
	}else{
		document.getElementById("rrrotcause").style.display ="none";
	document.getElementById("rrrotcausetabel").style.display ="none";
	var d=document.getElementById("savetext");
	 d.innerHTML="";
	}
}

function init(){
	
	document.getElementById("rrrotcause").style.display ="none";
	document.getElementById("rrrotcausetabel").style.display ="none";
	
}

function closewindow(){
	  parent.parent.GB_hide();
}

	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		   window.top.hidePopWin();
		   } 
		}

	function savedata(){
    var alertstr = "";
	var showalert=false;
		
	var feedback = document.scheduleInterviewForm.feedback.value;
				
	if(feedback == "" || feedback == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Status_is_required",user1.getLocale())%><br>";
		showalert = true;
		}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
		 document.scheduleInterviewForm.action = "scheduleInterview.do?method=interviwercommment&applicantId=<%=applicantId%>&eventype=<%=eventype%>&feedback="+feedback;
	   document.scheduleInterviewForm.submit();
	
	   
		}
</script>
<!--
<script type="text/javascript" src="jsp/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript">
tinyMCE.init({
    mode : "textareas",
    theme : "advanced",
    theme_advanced_buttons1 : "mybutton,bold,italic,underline,separator,strikethrough,justifyleft,justifycenter,justifyright, justifyfull,bullist,numlist,undo,redo,link,unlink",
    theme_advanced_buttons2 : "",
    theme_advanced_buttons3 : "",
    theme_advanced_toolbar_location : "top",
    theme_advanced_toolbar_align : "left",
      plugins : 'inlinepopups',
    setup : function(ed) {
        // Display an alert onclick
        

        // Add a custom button
        ed.addButton('mybutton', {
            title : 'My button',
            image : 'jsp/jscripts/tiny_mce/example.gif',
            onclick : function() {
                ed.selection.setContent('<STRONG><%=Constant.getResourceStringValue("aquisition.applicant.Hello_world",user1.getLocale())%>!</STRONG>');
            }
        });
    }
});
</script>-->
<body onload="init()">

<%
String interviwercommmented = (String)request.getAttribute("interviwercommmented");
	
if(interviwercommmented != null && interviwercommmented.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>

			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.Feedback_added_successfully",user1.getLocale())%></font>

		<!-- <a href="#" onClick="closewindow();return false;"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> -->
</td>

			<td> </td>
		</tr>
		
	</table>
</div>
<br>
<%}else {%>
<html:form action="/scheduleInterview.do?method=interviwercommment" enctype="multipart/form-data">

	<font color = red ><html:errors /> </font>
	<!-- <h3><%=Constant.getResourceStringValue("aquisition.applicant.add_feedback",user1.getLocale())%>  </h3> -->
	<br>
	<div class="div">
	<table border="0" width="100%">


		<tr>
				<td width="30%"><%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%></td>
				<td width="70%">
				<select name="feedback" onchange="feedbackonchange();">
				  <option value=""></option>
				  <%if(eventype == 0){ %>
				  <option value="1"><%=Constant.getResourceStringValue("aquisition.applicant.Cleared_review",user1.getLocale())%></option>
				  <%}else{ %>
				  <option value="1"><%=Constant.getResourceStringValue("aquisition.applicant.Cleared_interview",user1.getLocale())%></option>
				  <%} %>
				  <option value="2"><%=Constant.getResourceStringValue("aquisition.applicant.Rejected",user1.getLocale())%></option>
				  <option value="3"><%=Constant.getResourceStringValue("aquisition.applicant.On_hold",user1.getLocale())%></option>
				</select>
				
				</td>
		</tr>


	<tr>
				<td width="30%"> <span id="rrrotcausetabel">
				<%=Constant.getResourceStringValue("aquisition.applicant.Rejection_root_cause",user1.getLocale())%></span></td>
				<td width="70%">
            <span id="rrrotcause">
			<html:select  property="rejectionreasonId" onchange="addotherroottext();">
			<bean:define name="scheduleInterviewForm" property="rejectionreasonList" id="rejectionreasonList" />
            <html:options collection="rejectionreasonList" property="offerdeclinedreasonId"  labelProperty="offerdecliedreasonName"/>
			<option value="-1"><%=Constant.getResourceStringValue("aquisition.applicant.Other",user1.getLocale())%></option>
			</html:select>
			</span>
			<div id="savetext"></div>

	</td>
			</tr>


<!--
<% if(form.getEvtmplId() != 0){%>
<tr>
				<td width="30%">Evaluation sheet</td>
				<td width="70%">
			Ratings: 5-excellent 4-very good 3-good 2-average 1-poor 0-unaware
				<html:textarea property="evTmplfeedback" cols="90" rows="20"/>
			
				
</td>
			</tr>
<%}%>
-->
<%
List comptetencyList = form.getComptetencyList();
if(comptetencyList != null && comptetencyList.size()>0){
%>
				<tr>
				<td width="30%"></td>
				<td width="70%">
				<fieldset><legend><%=Constant.getResourceStringValue("admin.Competencies",user1.getLocale())%></legend>
				<table>
<tr>
<td width="30%"><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
<td width="33%"><b><%=Constant.getResourceStringValue("aquisition.applicant.Minimum_score_required",user1.getLocale())%></b></td>
<td width="19%"><b><%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%> </b></td>
<td width="20%"><b> <%=Constant.getResourceStringValue("aquisition.applicant.your_rating",user1.getLocale())%></b></td>
</tr>
				
<%
 for(int i=0;i<comptetencyList.size();i++){
	JobTemplateCompetency comp = (JobTemplateCompetency)comptetencyList.get(i);
	String textboxname = "comp_"+comp.getJbTmplcompId();
%>
<tr>
<td><%=comp.getCharName()%></td><td><%=comp.getImportance()%></td><td><%=(comp.getMandatory() != null && comp.getMandatory().equals("on"))?"Yes":"No"%></td>
<td><input type="text" name="<%=textboxname%>" size="5"  maxlength="3" onblur="if(isIntFloatCheck(this.value)==false)this.value='';"></td>
</tr>

<%}%>
</table>
</fieldset>
				</td>
			</tr>
<%}%>


<%
	if(eventype == 0){
List accList = form.getAccomplishmentList();
if(accList != null && accList.size()>0){
%>
				<tr>
				<td width="30%"></td>
				<td width="70%">
				<fieldset><legend><%=Constant.getResourceStringValue("Requisition.Accomplishments",user1.getLocale())%></legend>
				<table>
<tr>
<td width="30%"><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
<td width="33%"><b><%=Constant.getResourceStringValue("aquisition.applicant.Minimum_score_required",user1.getLocale())%></b></td>
<td width="19%"><b><%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%> </b></td>
<td width="20%"><b> <%=Constant.getResourceStringValue("aquisition.applicant.your_rating",user1.getLocale())%></b></td>
</tr>
				
<%
   for(int i=0;i<accList.size();i++){
	JobTemplateAccomplishment acc = (JobTemplateAccomplishment)accList.get(i);
	String textboxname = "accom_"+acc.getJbTmplAccId();
%>
<tr>
<td><%=acc.getAccName()%></td><td><%=acc.getImportance()%></td><td><%=(acc.getMandatory() != null && acc.getMandatory().equals("on"))?"Yes":"No"%></td>
<td><input type="text" name="<%=textboxname%>" size="5"  maxlength="3" onblur="if(isIntFloatCheck(this.value)==false)this.value='';"></td>
</tr>

<%}%>
</table>
</fieldset>
				</td>
			</tr>
<%}
}
%>

<tr>
				<td width="30%"> <%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%></td>
				<td width="70%">
				<html:textarea property="interviewercomments" cols="60" rows="5"/>
				
</td>
			</tr>
			<tr>
			
			<td width="30%"><%=Constant.getResourceStringValue("aquisition.applicant.Evaluation_Template_File",user1.getLocale())%></td>
				<td width="70%">
			<html:file property="evtmplFile"/> </td>
		</tr>

			<tr>
				<td>
				
				
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">
			
				
				 <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			</tr>

</table>
</div>
</html:form>
<%}%>
</body>