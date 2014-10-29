<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<html>




<table border="0" cellpadding="0" cellspacing="0">
 <tr><td colspan="5"><img src="../images/spacer.gif" width="20"></td></tr>
 <tr> <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
      <td width="100" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="240" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="240" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
 </tr>

 <tr><td colspan="5" class="BodyToolboxHeader">&nbsp;&nbsp;<%=Constant.getResourceStringValue("LOV.LOV_List",user1.getLocale())%></td></tr>

 <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"></td>
 </tr>

 <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/role.do?method=rolelist">
 <%=Constant.getResourceStringValue("admin.role.rolelist",user1.getLocale())%></a></td>
 </tr>
 <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/user.do?method=userList">
 <%=Constant.getResourceStringValue("LOV.UserList_manual_pg",user1.getLocale())%></a></td>
 </tr>
 <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/user.do?method=userListwithPag">
 <%=Constant.getResourceStringValue("LOV.UserList_yahoo_pg",user1.getLocale())%></a></td>
 </tr>
 <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/selection.do?method=selectionslist">
 <%=Constant.getResourceStringValue("LOV.selection_list",user1.getLocale())%></a></td>
 </tr>

  <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/round.do?method=roundlist">
 <%=Constant.getResourceStringValue("LOV.Round_List",user1.getLocale())%></a></td>
 </tr>
 <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/lov.do?method=callist">
 <%=Constant.getResourceStringValue("LOV.Call_List",user1.getLocale())%></a></td>
 </tr>
  <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/char.do?method=charlist">
 <%=Constant.getResourceStringValue("LOV.Char_List",user1.getLocale())%></a></td>
 </tr>
 <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/groupchar.do?method=groupCharlist">
 <%=Constant.getResourceStringValue("LOV.Group_Char_List",user1.getLocale())%></a></td>
 </tr>
  <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/evtmpl.do?method=evtmpllist">
 <%=Constant.getResourceStringValue("LOV.Evaluation_Templates_List",user1.getLocale())%></a></td>
 </tr>

<tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/salaryplan.do?method=salarylist">
 <%=Constant.getResourceStringValue("LOV.Salary_PlanList",user1.getLocale())%></a></td>
 </tr>

<tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/location.do?method=locationlist">
 <%=Constant.getResourceStringValue("LOV.Locations",user1.getLocale())%></a></td>
 </tr>

<tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/budgetcode.do?method=budgetCodelist">
 <%=Constant.getResourceStringValue("LOV.Budget_Codes",user1.getLocale())%></a></td>
 </tr>

 <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/jobcode.do?method=jobCodelist">
 <%=Constant.getResourceStringValue("LOV.Job_Codes",user1.getLocale())%></a></td>
 </tr>

 <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/jobgrade.do?method=jobGradelist">
 <%=Constant.getResourceStringValue("LOV.Jobgrades",user1.getLocale())%></a></td>
 </tr>
 <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"> <a  href="<%=request.getContextPath()%>/emailtemplate.do?method=emailtemplatelist">
 <%=Constant.getResourceStringValue("LOV.Email_template_list",user1.getLocale())%></a></td>
 </tr>

<tr><td colspan="5" class="ModuleText"><b><%=Constant.getResourceStringValue("LOV.Note",user1.getLocale())%></b></td></tr>

<tr><td colspan="5">&nbsp;</td></tr>

<tr> <td colspan="5" bgcolor="#999999"><img src="jsp/images/spacer.gif"  height="1"></td></tr>


</table>

    	
