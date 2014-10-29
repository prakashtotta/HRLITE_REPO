<%@ include file="../common/include.jsp" %>
<%

User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>

<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />


<html:form action="/applicant.do?method=savequestionfilters" >
<span id="questionsfilters">
<html:hidden property="searchuuid"/>
<%
List<SearchApplicantQuestions> qnscriList = aform.getQuestionCriList();
%>


<% if(qnscriList != null) {
for(int i=0;i<qnscriList.size();i++){
	SearchApplicantQuestions saq = qnscriList.get(i);
%>
<li><b><%=saq.getQuestion().getQuestionName()%></b> (<%=saq.getQuestion().getTypeVal()%>)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<% if(saq.getQuestion().getTypeVal()!=null && saq.getQuestion().getTypeVal().equals("dropdown")){%>
EQUALS &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=saq.getAnswerOption()%>
<%}else{%>

<%=saq.getFiltercri()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<%=saq.getFilterValue1()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<% if(saq.getFilterValue2() != null){%>

<%=saq.getFilterValue2()%>

<%}%>
<%}%>
<a href="#" onClick="deletequestionsfilters('<%=saq.getSearchuuid()%>','<%=saq.getUuid()%>');return false"><img src="jsp/images/delete.gif" border="0" alt="delete" title="<%=Constant.getResourceStringValue("aquisition.applicant.delete.qns.filter",user1.getLocale())%>" height="20"  width="19"/></a>
<br>
<%}}%>
	
</span>
</html:form>
<SCRIPT LANGUAGE="JavaScript">
document.applicantForm["csrfcode"].value=document.applicantForm["csrfcode"].value;
</script>