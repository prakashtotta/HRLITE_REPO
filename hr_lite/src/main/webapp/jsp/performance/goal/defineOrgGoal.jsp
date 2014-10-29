<%@ include file="../../common/include.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>
<bean:define id="goalForm" name="goalForm" type="com.performance.form.GoalForm" />



<script language="javascript">

var goalidval = "<%=goalForm.getGoalId()%>";

if(goalidval>0){
	parent.parent.GB_hide();
}


function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  parent.parent.GB_hide();
 
	   } 
	}
function closewindow(){
	  parent.parent.GB_hide();
}

function addGoal(){
	var orgId = document.goalForm.orgId.value;
	var departmentId= document.goalForm.departmentId.value;
	var designationId= document.goalForm.designationId.value;
	var timePeriodId= document.goalForm.timePeriodId.value;
	 document.goalForm.action = "goal.do?method=saveOrgGoal&orgId="+orgId+"&departmentId="+departmentId+"&designationId="+designationId+"&timePeriodId="+timePeriodId;
 document.goalForm.submit();
 

 
	}

</script>

<body class="yui-skin-sam" >

<html:form action="/goal.do?method=saveOrgGoal">

<div align="center">

<br>


<table border="0" width="100%">
<tr>
<td>
<b><%=Constant.getResourceStringValue("define.organization.goal",user1.getLocale())%></b>

</td>
</tr>
</table>
<br>
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

	
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("goal.name",user1.getLocale())%><span1>*</span1></td>
			
			<td>
		<html:text property="goalName" size="50" maxlength="200"/>
			
			</td>

		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("goal.description",user1.getLocale())%><span1>*</span1></td>
			
			<td>
		<html:textarea property="goalDesc" cols="60" rows="5"/>
			
			</td>

		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("goal.modifiable",user1.getLocale())%><span1>*</span1></td>
			
			<td>
		<html:checkbox property="modifiable" />
			
			</td>

		</tr>

		<html:hidden property="orgId" />
		<html:hidden property="departmentId" />
		<html:hidden property="designationId" />
		<html:hidden property="timePeriodId" />

        
		<tr>
		<td>
			<input type="button" name="addgoal" value="<%=Constant.getResourceStringValue("add.goal",user1.getLocale())%>" onClick="addGoal()">
		</td>
		<td>
		</td>
	
		</tr>


		
</table>


	
</div>

</html:form>


</body>