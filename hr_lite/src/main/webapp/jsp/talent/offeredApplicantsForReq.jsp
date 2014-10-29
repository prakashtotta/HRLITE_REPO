<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.form.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>
<%@ include file="../common/include.jsp" %>

<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
List applicantList = (List)request.getAttribute("offeredApplicantList");
%>
<span id="offeredapplicants">
<b><%=Constant.getResourceStringValue("Requisition.offered.applicants",user1.getLocale())%></b><br><br>
<div class="div">

<table border="0" width="100%">
<tr>
<td><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("aquisition.applicant.status",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("aquisition.applicant.qualification",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Primaryskill",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("aquisition.applicant.email",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("aquisition.applicant.Experience",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("aquisition.applicant.CurrentOrganization",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("aquisition.applicant.DATE_OF_BIRTH",user1.getLocale())%></b></td>
</tr>
 <%

 
 if(applicantList != null){

	 for(int i=0;i<applicantList.size();i++){

		 JobApplicant applicant = (JobApplicant)applicantList.get(i);
String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";

	//if(applicant.getInterviewState()!=null && applicant.getInterviewState().equals(Common.OFFER_ACCEPTED)){
	//	bgcolor ="green";
	//}
	

%>


<tr bgcolor=<%=bgcolor%>>
<td >
<a href="applicant.do?method=applicantDetails&applicantId=<%=applicant.getApplicantId()%>&secureid=<%=applicant.getUuid()%>" target="new"><%=applicant.getFullName()%></a>

</td>
<td><%=applicant.getInterviewState()%></td>
<td><%=(applicant.getHeighestQualification()==null)?"":applicant.getHeighestQualification()%></td>
<td><%=(applicant.getPrimarySkill()==null)?"":applicant.getPrimarySkill()%></td>
<td><%=applicant.getEmail()%></td>
<td><%=applicant.getNoofyearsexp()%></td>
<td><%=(applicant.getPreviousOrganization()==null)?"":applicant.getPreviousOrganization()%></td>
<td><%=DateUtil.convertDateToStringDate(applicant.getDateofbirth(),datepattern)%></td>

</tr>


<%

	 }
 }

 %>
<%  if(applicantList != null && applicantList.size()<1){ %>
<tr>
				<td>
<font color="red"><b><%=Constant.getResourceStringValue("aquisition.applicant.Not.yet.offered",user1.getLocale())%>  </b></font>
</td>
				
			</tr>
<%}%>
		  
	     

</table>
</div>
</span>
