<%@ include file="../common/include.jsp" %>
<%
String path = request.getContextPath()+request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
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
	<html:select  styleClass="list" property="sourceId">
	<option value=""></option>
			<bean:define name="applicantForm" property="vendorsList" id="vendorsList" />

            <html:options collection="vendorsList" property="userId"  labelProperty="firstName"/>
			</html:select>
<%} else if(typeid != null && new Integer(typeid).intValue()==Common.RESUME_SOURCE_FERERRAL_ID){%>

<table>

 <tr><td><%=Constant.getResourceStringValue("aquisition.applicant.Employee_code",user1.getLocale())%></td> 
 <td><html:text styleClass="textdynamic" property="employeecode" size="30"/> </td>
 </tr>
  <tr><td><%=Constant.getResourceStringValue("aquisition.applicant.refferername",user1.getLocale())%></td> 
  <td><html:text styleClass="textdynamic" property="referrerName" size="30"/> </td>
  </tr>
  <tr><td><%=Constant.getResourceStringValue("aquisition.applicant.Referrer_Email",user1.getLocale())%></td> 
  <td> <html:text styleClass="textdynamic" property="referrerEmail" size="30"/> </td>
  </tr>
  </table>

<%} else {%>


	<html:select  styleClass="list" property="sourceId">
	<option value=""></option>
			<bean:define name="applicantForm" property="sourceList" id="sourceList" />

            <html:options collection="sourceList" property="resumeSourcesId"  labelProperty="resumeSourcesName"/>
			</html:select>
<%}%>

</span>
</html:form>
</html:html>


