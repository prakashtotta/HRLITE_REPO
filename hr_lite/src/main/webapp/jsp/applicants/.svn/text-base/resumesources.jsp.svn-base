<%@ include file="../common/include.jsp" %>
<%
String path = request.getContextPath()+request.getAttribute("filePath");
ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>

<%
String typeid = (String)request.getAttribute("soucetypeid");
System.out.println("typeid"+typeid);
%>

<html:form action="/applicant.do?method=loadResumeSources" >


<span id ="subsource">


<%if(typeid != null && new Integer(typeid).intValue()==Common.RESUME_SOURCE_AGENCY_ID){%>
	<html:select  property="vendorId">
	<option value=""></option>
			<bean:define name="applicantForm" property="vendorsList" id="vendorsList" />

            <html:options collection="vendorsList" property="userId"  labelProperty="lastName"/>
			</html:select>
<%} else if(typeid != null && new Integer(typeid).intValue()==Common.RESUME_SOURCE_FERERRAL_ID){%>
    <%=Constant.getResourceStringValue("aquisition.applicant.Employee_code",user1.getLocale())%> : <html:text property="employeecode" size="30"/> <br>
	<%=Constant.getResourceStringValue("aquisition.applicant.refferername",user1.getLocale())%> : <html:text property="referrerName" size="30"/> <br>
	<%=Constant.getResourceStringValue("aquisition.applicant.Referrer_Email",user1.getLocale())%> : <html:text property="referrerEmail" size="30"/> 
<%} else {%>


	<html:select  property="sourceId">
	<option value=""></option>
			<bean:define name="applicantForm" property="sourceList" id="sourceList" />

            <html:options collection="sourceList" property="resumeSourcesId"  labelProperty="resumeSourcesName"/>
			</html:select>
<%}%>

</span>
</html:form>
</html:html>


