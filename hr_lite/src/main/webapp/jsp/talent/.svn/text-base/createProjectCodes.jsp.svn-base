<%@ include file="../common/include.jsp" %>
<bean:define id="projectcodeform" name="projectCodesForm" type="com.form.ProjectCodesForm" />
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<html>
 <HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>
<style>
span1{color:#ff0000;}
</style>
  

<style type="text/css">
	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
</style>

<STYLE>
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation
			{
			background-color:#6677DD;
			text-align:center;
			vertical-align:center;
			text-decoration:none;
			color:#FFFFFF;
			font-weight:bold;
			}
	.TESTcpDayColumnHeader,
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation,
	.TESTcpCurrentMonthDate,
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDate,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDate,
	.TESTcpCurrentDateDisabled,
	.TESTcpTodayText,
	.TESTcpTodayTextDisabled,
	.TESTcpText
			{
			font-family:arial;
			font-size:8pt;
			}
	TD.TESTcpDayColumnHeader
			{
			text-align:right;
			border:solid thin #6677DD;
			border-width:0 0 1 0;
			}
	.TESTcpCurrentMonthDate,
	.TESTcpOtherMonthDate,
	.TESTcpCurrentDate
			{
			text-align:right;
			text-decoration:none;
			}
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDateDisabled
			{
			color:#D0D0D0;
			text-align:right;
			text-decoration:line-through;
			}
	.TESTcpCurrentMonthDate
			{
			color:#6677DD;
			font-weight:bold;
			}
	.TESTcpCurrentDate
			{
			color: #FFFFFF;
			font-weight:bold;
			}
	.TESTcpOtherMonthDate
			{
			color:#808080;
			}
	TD.TESTcpCurrentDate
			{
			color:#FFFFFF;
			background-color: #6677DD;
			border-width:1;
			border:solid thin #000000;
			}
	TD.TESTcpCurrentDateDisabled
			{
			border-width:1;
			border:solid thin #FFAAAA;
			}
	TD.TESTcpTodayText,
	TD.TESTcpTodayTextDisabled
			{
			border:solid thin #6677DD;
			border-width:1 0 0 0;
			}
	A.TESTcpTodayText,
	SPAN.TESTcpTodayTextDisabled
			{
			height:20px;
			}
	A.TESTcpTodayText
			{
			color:#6677DD;
			font-weight:bold;
			}
	SPAN.TESTcpTodayTextDisabled
			{
			color:#D0D0D0;
			}
	.TESTcpBorder
			{
			border:solid thin #6677DD;
			}
			legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
</STYLE>

 </HEAD>
 <SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
 <bean:define id="projectcodeform" name="projectCodesForm" type="com.form.ProjectCodesForm" />




<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		 
	  parent.parent.GB_hide();

	   } 
	}
	
function init(){
setTimeout ( "document.projectCodesForm.projCode.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){
	var alertstr = "";
	var showalert=false;
		
	var code = document.projectCodesForm.projCode.value.trim();
	var name=document.projectCodesForm.projName.value.trim();
	var departmentId = document.projectCodesForm.departmentId.value.trim();

	
	if(code == "" || code == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Pcode_required",user1.getLocale())%><br>";
		showalert = true;
		}
		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Projectname_required",user1.getLocale())%><br>";
		showalert = true;
		}	
		if(departmentId == "" || departmentId == null || departmentId=="0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Department_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}
	if(document.projectCodesForm.projDesc.value.length != "" && document.projectCodesForm.projDesc.value.length> 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	if(document.projectCodesForm.notes.value.length != "" && document.projectCodesForm.notes.value.length> 800 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Notes_exceeded_800_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.projectCodesForm.action = "projectcodes.do?method=saveProjectCodes&readPreview=1";
 document.projectCodesForm.submit();


 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;
		
	var code = document.projectCodesForm.projCode.value.trim();
	var name=document.projectCodesForm.projName.value.trim();
    var departmentId = document.projectCodesForm.departmentId.value.trim();
	
	if(code == "" || code == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Pcode_required",user1.getLocale())%><br>";
		showalert = true;
		}
		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Projectname_required",user1.getLocale())%><br>";
		showalert = true;
		}	
		if(departmentId == "" || departmentId == null || departmentId=="0"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Department_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}
	if(document.projectCodesForm.projDesc.value.length != "" && document.projectCodesForm.projDesc.value.length> 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	if(document.projectCodesForm.notes.value.length != "" && document.projectCodesForm.notes.value.length> 800 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Notes_exceeded_800_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.projectCodesForm.action = "projectcodes.do?method=updateProjectCodes&readPreview=1&id="+'<bean:write name="projectCodesForm" property="projectId"/>';
 document.projectCodesForm.submit();
 
	}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){
	document.projectCodesForm.action = "projectcodes.do?method=projectCodedelete&readPreview=1&projectCodeId="+'<bean:write name="projectCodesForm" property="projectId"/>';
    document.projectCodesForm.submit();
	 }
}
	


 
	

	function closewindow(){
	  parent.parent.GB_hide();
}


function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&orgId="+document.projectCodesForm.orgId.value.trim();
	
	
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
   
</script>

<%
String savedprojectcode = (String)request.getAttribute("savedprojectcode");
String Updateprojectcode = (String)request.getAttribute("Updateprojectcode");
String Deleteprojectcode = (String)request.getAttribute("Deleteprojectcode");

%>

<body onload="init()" >

<% if(savedprojectcode != null && savedprojectcode.equals("yes")){%>
<div class="msg">
<font color="white"><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<%=Constant.getResourceStringValue("admin.ProjectCode.savemsg",user1.getLocale())%></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!-- <a class="closelink" href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> -->
	</div>
<%}%>

<% if(Updateprojectcode != null && Updateprojectcode.equals("yes")){%>
<div class="msg">
<font color="white"><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<%=Constant.getResourceStringValue("admin.ProjectCode.updatemsg",user1.getLocale())%></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
<!-- <a class="closelink" href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> -->
	</div>
<%}%>


<html:form action="/projectcodes.do?method=saveProjectCodes">
<% if(Deleteprojectcode != null && Deleteprojectcode.equals("yes")){%>
<div class="msg">
<font color="white"><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<%=Constant.getResourceStringValue("admin.ProjectCode.deletemsg",user1.getLocale())%></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
<!-- <a class="closelink" href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> -->
	</div>
<%}else{%>
<br>
<div class="div">
	


<%

if (projectcodeform.getReadPreview() != null && projectcodeform.getReadPreview().equals("2")){

%>	
     <br>
	 <fieldset><legend><%=Constant.getResourceStringValue("admin.ProjectCode.details",user1.getLocale())%></legend>
	 <table border="0" width="100%">
	<tr>
			<td></td>
			<td></td>
		</tr>	
		

		<tr >
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%> </td>
			<td >
			 <%=(projectcodeform.getProjCode()== null)?"":projectcodeform.getProjCode()%>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.name",user1.getLocale())%> </td>
			<td> <%=(projectcodeform.getProjName()== null)?"":projectcodeform.getProjName()%></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.desc",user1.getLocale())%></td>
			<td> <%=(projectcodeform.getProjDesc()== null)?"":projectcodeform.getProjDesc()%></td>
		</tr>	
		<tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.notes",user1.getLocale())%></td>
			<td><%=(projectcodeform.getNotes()== null)?"":projectcodeform.getNotes()%></td>
		</tr>
		<%
		String orgurl =null;
		System.out.println("projectcodeform.getOrgId()"+projectcodeform.getOrgId());
        if(projectcodeform.getOrgId() != 0){

   orgurl = "<a href='#'  onClick=window.open("+"'"+"org.do?method=editorg&readPreview=2&orgid="+projectcodeform.getOrgId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=330"+"'"+")>"+projectcodeform.getOrgName()+"</a>";

		}

		%>

				<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%> </td>
			
			<td>
			<%=(orgurl == null)?"":orgurl%>
			
			</td>
		</tr>
		<%
		String deptpurl =null;
        if(projectcodeform.getDepartmentId() != 0){

    deptpurl = "<a href='#' onClick=window.open("+"'"+"dept.do?method=editDepartmentDetails&readPreview=2&id="+projectcodeform.getDepartmentId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=230"+"'"+")>"+projectcodeform.getDepartmentName()+"</a>";

		}

		%>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			
			<td>
		<%=(deptpurl == null)?"":deptpurl%>
			</td>
		</tr>
		
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.startdate",user1.getLocale())%></td>
			<td>	
	     <%=(projectcodeform.getEffectiveStartDate()==null)?"":projectcodeform.getEffectiveStartDate()%>
			</td>
		</tr>
   <tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.enddate",user1.getLocale())%></td>
			<td>	
		     <%=(projectcodeform.getEffectiveEndDate()==null)?"":projectcodeform.getEffectiveEndDate()%>
			
			</td>
		</tr>
		</table>
		</fieldset>
  <%}else{%>   
		
<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

  <tr >
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%><font color="red">*</font></td>
			<td >
			<html:text property="projCode" maxlength="200" size="50"/>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text   property="projName" maxlength="500" size="50"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.desc",user1.getLocale())%></td>
			<td><html:textarea property="projDesc"  cols="60" rows="5"/></td>
		</tr>	
		<tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.notes",user1.getLocale())%></td>
			<td><html:textarea property="notes" cols="60" rows="5"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.orgname",user1.getLocale())%><font color="red">*</font></td>
			
			<td>
			
			<html:select property="orgId" onchange="retrieveURL('projectcodes.do?method=loadDepartments');">
			<bean:define name="projectCodesForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("admin.Deparment.loadingdept.only",user1.getLocale())%>....</span>
		
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.dept",user1.getLocale())%><font color="red">*</font></td>
			
			<td>
			<span id="departments">
			<html:select property="departmentId" >
			<bean:define name="projectCodesForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>&nbsp;
			</span>
		
			</td>
		</tr>
		
		
		</table>

		<table width="100%">		
		<tr>
			<td>
			<%if ((projectcodeform.getReadPreview()).equals("1")){ %>

			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="delete" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
		
		</tr>
	
		</table>

<%}%>
<%}%>
</div>


</html:form>

</body>

