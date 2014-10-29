<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String noofdaysoffset = Constant.getValue("dashboard.noofdays.offset.config");
List appList = BOFactory.getApplicantBO().getApplicantsByInterviewStateAndJoiningDateRecruiter(user1,Common.ON_BOARD,new Integer(noofdaysoffset).intValue());

%>
<span id="onboardapplicants">
<table width="100%">
		 <% if(appList != null && appList.size()>0){%>
		 <tr>
		 <td><%=Constant.getResourceStringValue("hr.name",user1.getLocale())%></td>
		  <td><%=Constant.getResourceStringValue("hr.number",user1.getLocale())%></td>
		  <td><%=Constant.getResourceStringValue("hr.onboard.date",user1.getLocale())%></td>
		 </tr>
          
          <% for(int i=0;i<appList.size();i++){
			JobApplicant appl = (JobApplicant)appList.get(i);  
			String urlreq = "applicant.do?method=applicantDetails&applicantId="+appl.getApplicantId()+"&secureid="+appl.getUuid();
			String bgcolor = "";
			if(i%2 == 0)bgcolor ="#B2D2FF";
			
		   %>

		 <tr bgcolor="<%=bgcolor%>">
		 <td><a href="<%=urlreq%>" target="new"><%=appl.getFullName()%></a></td>
		  <td><%=appl.getApplicant_number()%></td>
		  <td><%=DateUtil.convertDateToStringDate(appl.getJoineddate(), DateUtil.defaultdateformatforSQL)%></td>
		 </tr>

		  <%}%>
		  
		 <%}else{%>
		  <div class="product_title"><%=Constant.getResourceStringValue("applicants.not.found",user1.getLocale())%></div>
		 <%}%>
		 </table>

</span>