<%@ include file="../../common/include.jsp" %>
<%@ include file="../../common/greybox.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

String orgIdv = (String)request.getParameter("orgId");
%>
<html>
<bean:define id="goalForm" name="goalForm" type="com.performance.form.GoalForm" />

<style>
span1{color:#ff0000;}
</style>

<style type="text/css"> 


<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 750px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}



	
	</style>



<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  parent.parent.GB_hide();
 
	   } 
	}
function closewindow(){
	  parent.parent.GB_hide();
}

function savedata(){
	 var alertstr = "";
	  
	 if (showalert){
     	alert(alertstr);
        return false;
          }

	 
	  document.goalForm.action = "goal.do?method=saveOrgGoal";
 document.goalForm.submit();
 

 
	}

function initiateGoals(){
		var departmentId= document.goalForm.departmentId.value;
	var designationId= document.goalForm.designationId.value;
	var timePeriodId= document.goalForm.timePeriodId.value;
		var url = "<%=request.getContextPath()%>/goal.do?method=initiateGoals&orgId=<%=orgIdv%>"+"&departmentId="+departmentId+"&designationId="+designationId+"&timePeriodId="+timePeriodId;
	location.href=url;

}

function addkra(goalId){
	var url = "<%=request.getContextPath()%>/kra.do?method=createKraWithGoal&goalId="+goalId+"&typekra=E";
		GB_showCenter('<%=Constant.getResourceStringValue("add.kra",user1.getLocale())%>',url,700,750, messageret1);
}

function addGoal(){
   /* var orgId = document.goalForm.orgId.value;
	var departmentId= document.goalForm.departmentId.value;
	var designationId= document.goalForm.designationId.value;
	var timePeriodId= document.goalForm.timePeriodId.value;
	var url = "<%=request.getContextPath()%>/goal.do?method=defineOrgGoal&orgId="+orgId+"&departmentId="+departmentId+"&designationId="+designationId+"&timePeriodId="+timePeriodId;
	GB_showCenter('<%=Constant.getResourceStringValue("define.organization.goal",user1.getLocale())%>',url,500,600, messageret);*/

	 document.goalForm.action = "goal.do?method=saveOrgGoal&orgId=<%=goalForm.getOrgId()%>";
 document.goalForm.submit();
}

function updatedata(){

	 var alertstr = "";
	 	
	  document.goalForm.action = "goal.do?method=updateOrgGoal&id="+'<bean:write name="goalForm" property="goalId"/>';
 document.goalForm.submit();

 
	}

function deletedata(){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  document.goalForm.action = "goal.do?method=DeleteBudgetCode&id="+'<bean:write name="goalForm" property="goalId"/>';

		  document.goalForm.submit();
	  }
	
}
function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&orgId="+document.goalForm.orgId.value.trim();
	
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChange;

	    try {
    		req.open("GET", url, true);
						
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChange;
	        req.open("GET", url, true);
			req.send();
			
    	}
  	}

		

}
	 


function processStateChange() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtml(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading").style.visibility = "hidden";	
  	}
}




function replaceExistingWithNewHtml(newTextElements){
	//loop through newTextElements
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="departments")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}


function splitTextIntoSpan(textToSplit){
	//Split the document
  	returnElements=textToSplit.split("</span>")
        
  	//Process each of the elements        
  	for(var i=returnElements.length-1;i>=0;--i){  //Remove everything before the 1st span
	    spanPos = returnElements[i].indexOf("<span");               
                
    	//if we find a match, take out everything before the span
    	if(spanPos>0){
        	  subString=returnElements[i].substring(spanPos);
	          returnElements[i]=subString;
    	} 
  	}
  	return returnElements;
}
   

function messageret(){
	window.location.reload();
}
function messageret1(){
	location.href="goal.do?method=createOrgGoal&orgId=<%=orgIdv%>";
}

</script>

<%
 String isReadOnly = (String)request.getAttribute("isReadOnly");

%>

<body class="yui-skin-sam" >

<html:form action="/goal.do?method=saveOrgGoal">

<div align="center">

<br>


<table border="0" width="100%">
<tr>
<td>
<b><%=Constant.getResourceStringValue("define.organization.goals",user1.getLocale())%></b>

</td>
</tr>
</table>
<br>
	<table border="0" width="100%" bgcolor="#f3f3f3">
	<font color = red ><html:errors /> </font>

	
		
		
		<tr>
			<td valign="left"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			
			<td valign="left">

			<% if(!StringUtils.isNullOrEmpty(isReadOnly) && isReadOnly.equals("yes")){%>
			<html:select property="orgId" onchange="retrieveURL('user.do?method=loadDepartments');" disabled="true">
			<bean:define name="goalForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
			<%}else{%>

			<html:select property="orgId" onchange="retrieveURL('user.do?method=loadDepartments');" disabled="true">
			<bean:define name="goalForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("admin.BudgetCode.LoadingDepartments",user1.getLocale())%>	</span>

			<%}%>
			
	
			
			</td>

			<td valign="left"><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			
			<td valign="left">

            <% if(!StringUtils.isNullOrEmpty(isReadOnly) && isReadOnly.equals("yes")){%>
			<html:select property="departmentId" disabled="true">
			<option value=""></option>
			<bean:define name="goalForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			<%}else{%>
			<span id="departments">
			<html:select property="departmentId" >
			<option value=""></option>
			<bean:define name="goalForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</span>
			<%}%>


			</td>

		<td valign="left"><%=Constant.getResourceStringValue("hr.user.Designation",user1.getLocale())%></td>
			<td valign="left">


            <% if(!StringUtils.isNullOrEmpty(isReadOnly) && isReadOnly.equals("yes")){%>
			<html:select  property="designationId" disabled="true">
			<option value="0"></option>
			<bean:define name="goalForm" property="designationList" id="designationList" />

            <html:options collection="designationList" property="designationId"  labelProperty="designationName"/>
			</html:select>
			<%}else{%>
			<html:select  property="designationId">
			<option value="0"></option>
			<bean:define name="goalForm" property="designationList" id="designationList" />

            <html:options collection="designationList" property="designationId"  labelProperty="designationName"/>
			</html:select>
			<%}%>


			</td>


			<td valign="left"><%=Constant.getResourceStringValue("goal.for.timeperiod",user1.getLocale())%></td>
			<td valign="left">

			
            <% if(!StringUtils.isNullOrEmpty(isReadOnly) && isReadOnly.equals("yes")){%>
			<html:select  property="timePeriodId" disabled="true">
			<bean:define name="goalForm" property="timePeriodList" id="timePeriodList" />

            <html:options collection="timePeriodList" property="timePeriodId"  labelProperty="startenddate"/>
			</html:select>
			<%}else{%>
			<html:select  property="timePeriodId">
			<bean:define name="goalForm" property="timePeriodList" id="timePeriodList" />

            <html:options collection="timePeriodList" property="timePeriodId"  labelProperty="startenddate"/>
			</html:select>
			<%}%>

			</td>


		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("goal.name",user1.getLocale())%><span1>*</span1></td>
			
			<td>
		<html:text property="goalName" size="50" maxlength="200"/>
			
			</td>

			<td><%=Constant.getResourceStringValue("goal.description",user1.getLocale())%><span1>*</span1></td>
			
			<td>
		<html:textarea property="goalDesc" cols="60" rows="5"/>
			
			</td>
			<td><%=Constant.getResourceStringValue("goal.modifiable",user1.getLocale())%><span1>*</span1></td>
			
			<td>
		<html:checkbox property="modifiable" />
			
			</td>

			<td valign="left">
			<input type="button" name="addgoal" value="<%=Constant.getResourceStringValue("add.goal",user1.getLocale())%>" onClick="addGoal()">
			</td>
	

		</tr>

</table>

<br>
<table border="0" width="100%">
<tr>
<td>
<b><%=Constant.getResourceStringValue("goals",user1.getLocale())%></b>
&nbsp;&nbsp;
<input type="button" name="initiategoal" value="<%=Constant.getResourceStringValue("initiate.goals",user1.getLocale())%>" onClick="initiateGoals();return false;">

</td>
</tr>
</table>

<%

List goalList =  BOFactory.getGoalBO().goalListsByOrgDeptTimePeriodDesignation(goalForm.getOrgId(),goalForm.getDepartmentId(),goalForm.getDesignationId(),goalForm.getTimePeriodId());
%>
<br>
<table width="100%" border="1">
<tr bgcolor="#f3f3f3">
<td><%=Constant.getResourceStringValue("goal.name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("goal.KRAs",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("goal.actions",user1.getLocale())%></td>
<tr>

<%
if(goalList != null && goalList.size()>0){
	for(int i=0;i<goalList.size();i++){
		Goal goal = (Goal)goalList.get(i);
%>

<tr valign="top" height="100">
<td width="30%"> <b><%=goal.getGoalName()%> </b><br>
<i> <%=goal.getGoalDesc()%><i>
</td>
<td width="50%">
<a href="#" onClick="addkra('<%=goal.getGoalId()%>');return false;"><%=Constant.getResourceStringValue("add.kra",user1.getLocale())%></a><br>
<%
List<GoalKra> kraList = BOFactory.getGoalBO().getGoalKraListByGoal(goal.getGoalId());
if(kraList != null && kraList.size()>0){
%>

<table border="1">
<tr bgcolor="#f3f3f3">
<td><%=Constant.getResourceStringValue("kra.name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("kra.description",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("kra.weight",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("kra.modifiable",user1.getLocale())%></td>
</tr>
<%
for(int j=0;j<kraList.size();j++){
				GoalKra gkra = (GoalKra)kraList.get(j);
%>
<tr>
<td>
<a href="#" onClick="editKra('<%=gkra.getGoalKraId()%>');return false;"><%=gkra.getKraName()%></a>
</td>
<td>
<%=gkra.getKradesc()%>
</td>
<td>
<%=gkra.getKraWeight()%>
</td>
<td>
<%=(gkra.getModifiable()==null || gkra.getModifiable().equals("N"))?"N":"Y"%>
</td>
</tr>

<%}%>
</table>

<%}%>

</td>
<td width="15%">actions</td>
<tr>

<%}}%>
</table>

	
</div>

</html:form>


</body>

