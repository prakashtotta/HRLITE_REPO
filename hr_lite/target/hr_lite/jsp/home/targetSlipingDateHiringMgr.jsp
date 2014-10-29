<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
List myActiveReqList = BOFactory.getJobRequistionBO().getMyActiveAndNotFilledWithTargetDateSlipingJobRequistionsHiringMgr(user1.getUserId());

%>
<span id="targetdateslip">
<table width="100%">
		 <% if(myActiveReqList != null && myActiveReqList.size()>0){%>
		 <tr>
		 <td><%=Constant.getResourceStringValue("hr.name",user1.getLocale())%></td>
		  <td><%=Constant.getResourceStringValue("hr.target.date",user1.getLocale())%></td>
		 </tr>
          
          <% for(int i=0;i<myActiveReqList.size();i++){
			JobRequisition jobreq = (JobRequisition)myActiveReqList.get(i);  
			String urlreq = "jobreq.do?method=requistionapplicantlist&state=0&requistionId="+jobreq.getJobreqId()+"&secureid="+jobreq.getUuid();
			String bgcolor = "";
			if(i%2 == 0)bgcolor ="#B2D2FF";
			
		   %>

		 <tr bgcolor="<%=bgcolor%>">
		 <td><a href="#" onClick="editJobReq('<%=jobreq.getJobreqId()%>');return false;"><%=jobreq.getJobreqName()%></a></td>
		 
		  <td><%=DateUtil.convertDateToStringDate(jobreq.getTargetfinishdate(), DateUtil.defaultdateformatforSQL)%></td>
		 </tr>

		  <%}%>
		  
		 <%}else{%>
		  <div class="product_title"><%=Constant.getResourceStringValue("requisitions.not.found",user1.getLocale())%></div>
		 <%}%>
		 </table>

</span>