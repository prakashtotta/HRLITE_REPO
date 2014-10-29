<%@ include file="../common/include.jsp" %>

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





<%
 
 
 



  %>

<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
List activityList = (List)request.getAttribute("activityList");
%>
<span id="activitylog">
<fieldset><legend><%=Constant.getResourceStringValue("hr.req.activity",user1.getLocale())%></legend>

<table border="0" width="100%">
<tr bgcolor="#f3f3f3">
<td width="15%"><%=Constant.getResourceStringValue("hr.applicant.activity.on",user1.getLocale())%></td>
<td width="20%"><%=Constant.getResourceStringValue("hr.applicant.activity",user1.getLocale())%></td>
<td width="15%"><%=Constant.getResourceStringValue("hr.applicant.activity.by",user1.getLocale())%></td>
<td width="15%"><%=Constant.getResourceStringValue("hr.applicant.activity.currenowner",user1.getLocale())%></td>
<td width="30%"><%=Constant.getResourceStringValue("hr.applicant.activity.note",user1.getLocale())%></td>
</tr>
<% 
if(activityList != null){
for(int i=0;i<activityList.size();i++){
	RequisitionActivity activity = (RequisitionActivity)activityList.get(i);

	String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
%>
<tr bgcolor=<%=bgcolor%>>
<td width="15%"><%=DateUtil.convertSourceToTargetTimezone(activity.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%></td>
<td width="20%"><%=(activity.getActivityName() == null)?"":Constant.getResourceStringValue(activity.getActivityName(),user1.getLocale())%></td>
<td class="bodytext" width="15%">

<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=activity.getUserId()%>', 'EvaluationTemplate1','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=activity.getUserFullName()%></a> 

</td>

<td class="bodytext" width="15%">
<%if(!StringUtils.isNullOrEmpty(activity.getIsgroup()) && activity.getIsgroup().equals("Y")){%>
<img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=activity.getAssignedTouserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=(activity.getAssignedToUserName()==null)?"":activity.getAssignedToUserName()%></a>  
<%}else if(activity.getAssignedTouserId() != 0){%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=activity.getAssignedTouserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=activity.getAssignedToUserName()%></a> 
<%}%>


</td>


<td width="30%"><%=(activity.getComment() == null)?"":StringUtils.textAreaValuesDisplay(activity.getComment())%></td>

</tr>

<%}
}
%>
</table>
</fieldset>
</span>