<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%
String date = (String)request.getParameter("date");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
List applicanteventList = BOFactory.getApplicantBO().getAllInterviewsByDateAdmin(user1,date);

%>
<span id="todaysinterviews">
<table width="100%">
		 <% if(applicanteventList != null && applicanteventList.size()>0){%>
		 <tr>
		 <td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></td>
		  <td><%=Constant.getResourceStringValue("hr.interview.date.round",user1.getLocale())%></td>
		  <td><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Reviewer",user1.getLocale())%></td>
		 
		 </tr>
          
          <% for(int i=0;i<applicanteventList.size();i++){

			JobApplicationEvent event = (JobApplicationEvent)applicanteventList.get(i);
				String eventheader = Common.INTERVIEW_ROUND;
				if(event.getEventType() == 0){
				   eventheader = Constant.getResourceStringValue("Resume_Screening",user1.getLocale());
				}else{
					eventheader = Common.INTERVIEW_ROUND + "-"+event.getEventType();
				}

				String applicantname1 = event.getApplicant().getFullName();
				

				String applicanturl = "applicant.do?method=applicantDetails&applicantId="+event.getApplicant().getApplicantId()+"&secureid="+event.getApplicant().getUuid();

			String ownerurl = "";
			if(!StringUtils.isNullOrEmpty(event.getIsGroup()) && event.getIsGroup().equals("Y")){
				ownerurl =  "<img src='jsp/images/User-Group-icon.png'><a href='#'  onClick=window.open("+"'"+"usergroup.do?method=editusergroup&readPreview=2&usergroupid="+event.getOwnerGroup().getUsergrpId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=630"+"'"+")>"+event.getOwnerGroup().getUsergrpName()+"</a>";
			}else{
			 ownerurl =  "<img src='jsp/images/user.gif'><a href='#'  onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+event.getOwner().getUserId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=630"+"'"+")>"+event.getOwner().getFirstName()+" " +event.getOwner().getLastName()+"</a>";
			}

				


			String bgcolor = "";
			if(i%2 == 0)bgcolor ="#B2D2FF";

			String intdate = DateUtil.convertSourceToTargetTimezone(event.getInterviewDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale()) +"<br>"+"("+eventheader+")";
			if(event.getEventType() == 0){
				intdate = eventheader;
			}
		   %>

		 <tr bgcolor="<%=bgcolor%>">
		 <td><a href="<%=applicanturl%>" target="new"><%=applicantname1%></a></td>
		  <td><%=intdate%></td>
		  <td><%=ownerurl%></td>
		  
		 </tr>

		  <%}%>
		  
		 <%}else{%>
		  <div class="product_title"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.No.interviews.todays",user1.getLocale())%></div>
		 <%}%>
		 </table>

</span>