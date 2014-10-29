<%@ include file="../common/include.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>
<%@ page import="com.bean.*"%>
<bean:define id="jobreqform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />

<%
String reqId =(String)request.getParameter("reqId");
String isapprovalinitiated =(String)request.getParameter("isapprovalinitiated");
String state =(String)request.getParameter("state");

jobreqform.setJobreqId(new Long(reqId).longValue());
jobreqform.setIsapprovalInitiated(new Integer(isapprovalinitiated).intValue());
jobreqform.setState(state);
User user1 = (User)session.getAttribute(Common.USER_DATA);
boolean isEditReqAfterPublishPermission = PermissionChecker.isPermissionApplied(Common.EDIT_REQUISITION_AFTER_PUBLISH, user1);
jobreqform.setSkillRatingList(Constant.skillsRatingsList);
BOFactory.getJobRequistionTXBO().setEvaluationTabData(user1,jobreqform);

%>

<BR>
	<fieldset><legend><b><%=Constant.getResourceStringValue("Requisition.Competency",user1.getLocale())%></b></legend>
	<div class="container">
<table border="0" width="90%">

    <tr>
	<td><b><%=Constant.getResourceStringValue("Requisition.Competency",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("Requisition.Minimum_rating_required",user1.getLocale())%></b></td><td></td>
	</tr>
    <tr>
	<td>
	<html:text property="competencyname" styleId="competencyname" maxlength="500" styleClass="text titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.Competency.help",user1.getLocale())%>"/>
    </td>

	<td>
	<input type="radio" name="iscompmandatory" class="titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.Competency.Is_mandatory.help",user1.getLocale())%>" value="on"><%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%> <input type="radio" name="iscompmandatory" value="off" checked><%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%>
	</td>
	<td>
	<html:select  property="minimumrating" styleClass="list titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.Competency.Minimum_rating_required.help",user1.getLocale())%>">
			<bean:define name="jobRequisitionForm" property="skillRatingList" id="skillRatingList" />

            <html:options collection="skillRatingList" property="key"  labelProperty="value"/>
			</html:select>
	</td>
	<td>
	<%=Constant.getResourceStringValue("Requisition.Is.visible.in.JobDetails",user1.getLocale())%>	<%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%><input type="radio" name="isVisible" maxlength="10" value="Y" class="titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.Competency.Is.visible.in.JobDetails.yes.help",user1.getLocale())%>">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%><input type="radio" name="isVisible"  value="N" checked class="titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.Competency.Is.visible.in.JobDetails.no.help",user1.getLocale())%>">
	
   <a class="button" href="#" onClick="addCompetency();return false" value="add"><%=Constant.getResourceStringValue("Requisition.add",user1.getLocale())%></a>

	<span class="textboxlabel" id="loading4" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>......</span>
	</td>
	</tr>

    
	</table>
</div>
<div class="div">
   <span id="competecydetails">
    <table border="0" width="800px">
<%
List competencyList = jobreqform.getComptetencyList();

%>
<% if(competencyList != null && competencyList.size()>0){%>
    <tr>
	
	<td><b><%=Constant.getResourceStringValue("Requisition.Competency",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("Requisition.Minimum_rating_required",user1.getLocale())%></b></td>
	<td><b>	<%=Constant.getResourceStringValue("Requisition.Is.visible.in.JobDetails",user1.getLocale())%></b></td>
	<td></td>
	</tr>



<%	
for(int i=0;i<competencyList.size();i++){
	JobTemplateCompetency jcomp = (JobTemplateCompetency)competencyList.get(i);
	String str="";
	if(jcomp.getImportance() == 6 ){
		str ="N/A";
	}else{
		str=String.valueOf(jcomp.getImportance()); 
	}
	String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
%>
<tr bgcolor=<%=bgcolor%>>

<td width="208px"><%=jcomp.getCharName()%></td>
<td width="97px"><%=(jcomp.getMandatory().equals("on"))?"Yes":"No"%></td>
<td width="162px"><%=str%></td>
<td><%=(jcomp.getIsVisible().equals("Y"))?"Yes":"No"%> </td>

<td width="50px"><a href="#" onClick="deletecompetency('<%=jcomp.getJbTmplcompId()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete competency" title="<%=Constant.getResourceStringValue("Requisition.delete_competency",user1.getLocale())%>" height="20"  width="19"/></a></td>
<%--
<%  if(jobreqform.getIsapprovalInitiated() == 1){ %>
<td width="50px"><a href="#" onClick="deletecompetency('<%=jcomp.getJbTmplcompId()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete competency" title="<%=Constant.getResourceStringValue("Requisition.delete_competency",user1.getLocale())%>" height="20"  width="19"/></a></td>

<% } else if(jobreqform.getState() != null && jobreqform.getState().equals(Common.ACTIVE)){ %>
		      <% if(isEditReqAfterPublishPermission){%>
<td width="50px"><a href="#" onClick="deletecompetency('<%=jcomp.getJbTmplcompId()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete competency" title="<%=Constant.getResourceStringValue("Requisition.delete_competency",user1.getLocale())%>" height="20"  width="19"/></a></td>
<%}}else{%>
<td width="50px"><a href="#" onClick="deletecompetency('<%=jcomp.getJbTmplcompId()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete competency" title="<%=Constant.getResourceStringValue("Requisition.delete_competency",user1.getLocale())%>" height="20"  width="19"/></a></td>
<%}%>

--%>
</tr>
<%
}
}else{%>
<!--Competency not added.-->
<%}%>

    </table>
	</span>
</div>
	</fieldset>

<BR>

<fieldset><legend><b><%=Constant.getResourceStringValue("Requisition.Accomplishments",user1.getLocale())%></b></legend>
<div class="container">
<table border="0" width="90%">

    <tr>
	<td><b><%=Constant.getResourceStringValue("Requisition.Accomplishments",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
	<!--  <td><b><%=Constant.getResourceStringValue("Requisition.Minimum_rating_required",user1.getLocale())%></b></td><td></td>-->
	</tr>
    <tr>
	<td>
	<html:text property="accomplishmentname" styleId="accomplishmentname" maxlength="500" styleClass="text titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.Accomplishments.help",user1.getLocale())%>"/>
    </td>
	</td>
	<td width="380px">
	<input type="radio" name="isaccommandatory" value="on" class="titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.Accomplishments.Is_mandatory.yes.help",user1.getLocale())%>"> <%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%> <input type="radio" name="isaccommandatory" value="off" checked class="titleHintBox" title="<%=Constant.getResourceStringValue("Requisition.Accomplishments.Is_mandatory.no.help",user1.getLocale())%>"> <%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%>
	</td>
	<!--  <td>
	<html:select  property="minimumratingaccom">
			<bean:define name="jobRequisitionForm" property="skillRatingList" id="skillRatingList" />

            <html:options collection="skillRatingList" property="key"  labelProperty="value"/>
			</html:select>
	</td>-->
	<td >
	 <a class="button" href="#" onClick="addAccomplishment();return false" value="add"><%=Constant.getResourceStringValue("Requisition.add",user1.getLocale())%></a>
	<span class="textboxlabel" id="loading5" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>......</span>
	</td>
	</tr>

    
	</table>
</div>
   <div class="div">

   <span id="accomplishmentdetails">
    <table border="0" width="800px">
<%
List accomplishmentList = jobreqform.getAccomplishmentList();

%>
<% if(accomplishmentList != null && accomplishmentList.size()>0){%>
    <tr>
	
	<td><b><%=Constant.getResourceStringValue("Requisition.Accomplishments",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
	<!--  <td><b><%=Constant.getResourceStringValue("Requisition.Minimum_rating_required",user1.getLocale())%></b></td><td></td>-->
	
	</tr>



<%	
for(int i=0;i<accomplishmentList.size();i++){
	JobTemplateAccomplishment jtacc = (JobTemplateAccomplishment)accomplishmentList.get(i);
	String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
%>
<tr bgcolor=<%=bgcolor%>>

<td width="132px" wrap><%=jtacc.getAccName()%></td><td width="180px"><%=(jtacc.getMandatory().equals("on"))?"Yes":"No"%></td><!--  <td width="250px"><%=jtacc.getImportance()%></td>-->
<td width="50px"><a href="#" onClick="deleteAccomplishment('<%=jtacc.getJbTmplAccId()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete competency" title="<%=Constant.getResourceStringValue("Requisition.delete_accomplishment",user1.getLocale())%>" height="20"  width="19"/></a></td>

</tr>
<%
}
}else{%>
<!--Competency not added.-->
<%}%>

    </table>
	</span>
	</div>
	</fieldset>

<br>
<fieldset><legend><b><%=Constant.getResourceStringValue("Requisition.applicant.scoring.keywords",user1.getLocale())%></b></legend>
 <div class="container">
 <table border="0" width="100%">
 <tr>
			<td><html:text property="scoringKeyword" size="80" maxlength="200" styleClass="text titleHintBox"  title="<%=Constant.getResourceStringValue("Requisition.applicant.scoring.keywords.help",user1.getLocale())%>"/>
			<span class="textboxlabel" id="scoringKeyword" STYLE="font-size: smaller;";>
						<%=Constant.getResourceStringValue("aquisition.applicant.keyword.score.message",user1.getLocale())%></span>
			</td>
		</tr>

 </table>
 </div>
</fieldset>
<br>
<%@ include file="assignExamQuestionaireToReq.jsp" %>
