<%@ include file="../../common/include.jsp" %>
<%@ include file="../../common/greybox.jsp" %>
<%@ include file="../../common/autocomplete.jsp" %>
<script type="text/javascript" src="jsp/js/jquery.blockUI.js"></script>


<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String 	userId = (String)request.getAttribute("userId");
String 	empName = (String)request.getAttribute("empName");
if(empName == null){
	empName = "";
}
Long timeLongv = (Long)session.getAttribute("TIMEPERIOD_ID");
List goalList =  BOFactory.getGoalBO().userGoalsList(userId,timeLongv);
%>
<span id="goaldata">

<br>
<table border="0" width="100%">
<tr>
<td>
<b><%=Constant.getResourceStringValue("goals.for",user1.getLocale())%> : <%=empName%></b>
</td>
</tr>
</table>
<br>
<table width="100%" border="1">
<tr bgcolor="#f3f3f3">
<td><%=Constant.getResourceStringValue("goal.name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("goal.KRAs",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("kra.weight",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("kra.kpis",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("goal.actions",user1.getLocale())%></td>
<tr>

<%
if(goalList != null && goalList.size()>0){
	for(int i=0;i<goalList.size();i++){
		UserGoals goal = (UserGoals)goalList.get(i);
	if(StringUtils.isNullOrEmpty(goal.getGoal().getGoalName()))continue;
		
%>

<tr valign="top" height="100">
<td width="10%"> <b><%=goal.getGoal().getGoalName()%> </b>
</td>
<td width="20%"> <%=(goal.getKraName()==null)?"":goal.getKraName()%> <br>
<i><font color="33CCCC"><%=(goal.getKradesc()==null)?"":goal.getKradesc()%></i>
</td>
<td width="10%"> <b><%=(goal.getKraWeight()==0.0)?"":goal.getKraWeight()%> </b>
</td>
<td width="50%">

<%
List<UserGoalsKpi> kpiList = goal.getKpiList();
if(kpiList != null && kpiList.size()>0){
%>

<table align="center" width="80%" border="1">
<tr bgcolor="#f3f3f3">
<td width="50%"><%=Constant.getResourceStringValue("kra.tasks",user1.getLocale())%></td>
<td width="50%"><%=Constant.getResourceStringValue("kra.measures",user1.getLocale())%></td>
</tr>
<%
for(int j=0;j<kpiList.size();j++){
				UserGoalsKpi gkpi = (UserGoalsKpi)kpiList.get(j);
%>
<tr>
<td>
<%=gkpi.getKpiName()%>
</td>
<td>
<%=gkpi.getKraMeasure()%>
</td>

</tr>

<%}%>
</table>

<%}else{%>
<table align="center" width="80%" border="1">
</table>
<%}%>

</td>
<td width="15%">
<% if(goal.getModifiable()!= null && goal.getModifiable().equals("Y")){%>
<a href="#" onClick="addkra('<%=goal.getUserGoalId()%>');return false;"><%=Constant.getResourceStringValue("add.kra",user1.getLocale())%></a>
<%}%>
<% if(goal.getKramodifiable()!= null && goal.getKramodifiable().equals("Y")){%>
<br><a href="#" onClick="modifykra('<%=goal.getUserGoalId()%>');return false;"><%=Constant.getResourceStringValue("modify.kra",user1.getLocale())%></a>
<%}%>
</td>
<tr>

<%}}%>
</table>
<%
if(!StringUtils.isNullOrEmpty(userId)){
%>
<br>
<table border="0" width="100%">
<tr>
<td>
 <input type="button" name="sendforreview" value="<%=Constant.getResourceStringValue("send.for.review.goals.to.user",user1.getLocale())%>" onClick="sendForReview('<%=userId%>')">
</td>
</tr>
</table>
<%}%>
<input type="hidden" name="useridhidden" value="<%=userId%>">

</span>