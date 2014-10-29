	<%@ include file="../common/include.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>

	<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>
	<%@ page import="com.bean.*"%>
	<%@ page import="com.bean.onboard.*"%>

  

	<bean:define id="sform" name="onBoardingForm" type="com.form.OnBoardingForm" />

	<%
	////response.setHeader("Cache-Control", "no-cache");
	//		//response.setHeader("Pragma", "no-cache");
		//	//response.setIntHeader("Expires", 0);
	%>

	
	  <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String jsscriptdata = (String)request.getAttribute("jsscriptdata");
%>

	
	<br>

<style>
span1{color:#ff0000;}
</style>

 <span id="onboardtaskdetails">

   



 
 <table align="left">
 <tr bgcolor=#EEF3E2>
		<td><b>Required</b></td><td><b>Task#</b></td><td><b>Task description</b></td>
		<td><b>Task owner</b></td><td><b>Criteria</b> </td>

	</tr>
<%
OnBoardingTemplate template = sform.getTemplate();
Set taskdefset = template.getTaskdefinitions();
Iterator itr = taskdefset.iterator();
%>
  
  <%
	  int i=1;
  String trcolor = "<tr>";
  while(itr.hasNext()){
	  OnBoardingTaskDefinitions taskdef = (OnBoardingTaskDefinitions)itr.next();
	  if((i%2)==0)trcolor="<tr bgcolor=#f3f3f3>";
  %>
  
      
      <tr bgcolor=#f3f3f3>
		<td><INPUT NAME="taskchk" TYPE="CHECKBOX" VALUE="<%=taskdef.getTaskdefid()%>" checked></td><td><b>#<%=i%></b>&nbsp;&nbsp;<b><%=taskdef.getTaskName()%></b></td><td><%=taskdef.getTaskDesc()%></td>
		<td>
		
		 <div id="taskassign_<%=taskdef.getTaskdefid()%>">
		<%if(!StringUtils.isNullOrEmpty(taskdef.getIsGroup()) && taskdef.getIsGroup().equals("Y")){%>
<img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=taskdef.getPrimaryOwnerId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=taskdef.getPrimaryOwnerName()%></a> 
<%}else{%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=taskdef.getPrimaryOwnerId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=taskdef.getPrimaryOwnerName()%></a> 
<%}%>
<input type="hidden" name="isGroup_<%=taskdef.getTaskdefid()%>" value="<%=taskdef.getIsGroup()%>"/>
		 <input type="hidden" name="taskownername_<%=taskdef.getTaskdefid()%>" value="<%=taskdef.getPrimaryOwnerName()%>"/>
		 <input type="hidden" name="taskownerid_<%=taskdef.getTaskdefid()%>" value="<%=taskdef.getPrimaryOwnerId()%>"/>

<a href="#" onClick="opensearchtaskassign('<%=taskdef.getTaskdefid()%>');return false;"><img src="jsp/images/selector.gif" border="0"/></a>
</div>
		


		 </td>
		 
		</td><td><input type="text" size="3" name="tasknofodays_<%=taskdef.getTaskdefid()%>" value="<%=taskdef.getNoofdays()%>" maxlength="3" onblur="if(isIntCheck(this.value)==false)this.value=0;"/>days &nbsp; <i><%=(taskdef.getEventType()==null)?"":taskdef.getEventType().toLowerCase()%> </i>&nbsp;<b>On boarding date</b></td>
     </tr>
	
     
     <%
      List attributes = taskdef.getAtrributes();
	 if(attributes != null && attributes.size()>0){

	  %>
      <tr><td></td><td>
	  <table>
	  <tr bgcolor=#f3f3f3><td>Attribute</td></tr>
	  <% for(int p=0;p<attributes.size();p++){
		  OnBoardingTaskAttributes attr = (OnBoardingTaskAttributes)attributes.get(p);
	   %>
	  <tr><td><%=attr.getAttribute()%><%=(attr.getIsMandatory() != null && attr.getIsMandatory().equals("Y"))?"<font color=red>*</red>":""%></td></tr>
      
	  <%
		  } // end for loop
	  %>
	  </table></td><td></td><td></td><td></td></tr>
	 <%}//end of null
	 %>
	

<%
		i++;
  }
%>
</table>

</span>
