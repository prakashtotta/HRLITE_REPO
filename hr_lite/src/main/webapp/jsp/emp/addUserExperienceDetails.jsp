<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/lightbox.jsp" %>
 <%@ include file="../common/include.jsp" %>


  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<<< addUserExperienceDetails.jsp >>");
%>

<bean:define id="cform" name="userExperienceForm" type="com.form.employee.UserExperienceForm" />


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
span1{color:#ff0000;}
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
</STYLE>
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

<script language="javascript">



function updatedata(){
	var alertstr="";
	var showalert=false;
	var prevOrgName=document.userExperienceForm.prevOrgName.value.trim();

	
    if(prevOrgName == "" || prevOrgName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.WorkExperience.Employer.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userExperienceForm.action="userexperience.do?method=updateUserExperienceDetails";
	  document.userExperienceForm.submit();


}

function savedata(){


	var alertstr="";
	var showalert=false;
	var prevOrgName=document.userExperienceForm.prevOrgName.value.trim();

	
    if(prevOrgName == "" || prevOrgName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.WorkExperience.Employer.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userExperienceForm.action="userexperience.do?method=saveUserExperienceDetails";
	  document.userExperienceForm.submit();



}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
 	if (doyou == true){
		document.userExperienceForm.action="userexperience.do?method=deleteUserExperienceDetails";
	  	document.userExperienceForm.submit();
 	}
	
}

function retrieveURLPrevOrg(url) {

	   //convert the url to a string
	    document.getElementById("loadingprev").style.visibility = "visible";

		url=url+"&cid="+document.userExperienceForm.countryId.value;

	  	//Do the AJAX call
	  	if (window.XMLHttpRequest) { 
		    req = new XMLHttpRequest();    	// Non-IE browsers
	    	req.onreadystatechange = processStateChangePrev;

		    try {
	    		req.open("GET", url, true);
				
		    } catch (e) {}
		    req.send(null);
	  	} else if (window.ActiveXObject) {
	       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
	    	if (req) {
		    	req.onreadystatechange=processStateChangePrev;
		        req.open("GET", url, true);
			    req.send();
				
	    	}
	  	}
	}

function processStateChangePrev() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlPrevCountry(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loadingprev").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlPrevCountry(newTextElements){
	//loop through newTextElements ffffffffff
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
				  
		    	  if(name="states")
   					document.getElementById(name).innerHTML = content;
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
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){

	   parent.parent.GB_hide(); 
	   } 
	}
function closewindow2(){
	parent.parent.GB_hide();
	 // document.userEmergencyContactForm.action = "useremergencycontact.do?method=emergencyContactdetails";
	 // document.userEmergencyContactForm.submit();
	 
}
</script>
<%
String userExperienceDetailsUpdated = (String)request.getAttribute("userExperienceDetailsUpdated");
	
if(userExperienceDetailsUpdated != null && userExperienceDetailsUpdated.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.Work_Experience.updated",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<%
String userExperienceDetailsSaved = (String)request.getAttribute("userExperienceDetailsSaved");
	
if(userExperienceDetailsSaved != null && userExperienceDetailsSaved.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("Work Experience Details Saved Successfully",user1.getLocale())%></font></td>
			<td><!--   <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>

<%
String userExperienceDetailsDeleted = (String)request.getAttribute("userExperienceDetailsDeleted");
	
if(userExperienceDetailsDeleted != null && userExperienceDetailsDeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.Qualifications.Work_Experience.deleted",user1.getLocale())%></font></td>
			<td><!--   <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>--></td>
		</tr>
		
	</table>
</div>
<%
}else{
%>
<body >
<html:form action="/userexperience.do?method=saveUserExperienceDetails" >
<table border="0" width="100%">


		<tr>
			<td width="30%"><%=Constant.getResourceStringValue("hr.user.WorkExperience.Employer",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="prevOrgName" size="40"/>
			<html:hidden property="userId" />
			<html:hidden property="orgDetailsId" />
			</td>
		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.Job_Title",user1.getLocale())%></td>
			<td><html:text property="role" size="40"/></td>
			
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.Start_date",user1.getLocale())%></td>
			<td>
			<SCRIPT LANGUAGE="JavaScript" ID="jscal4xx">
			var cal4xx = new CalendarPopup("testdiv4");
			cal4xx.showNavigationDropdowns();

			</SCRIPT>
			<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
			

			<html:text property="startdate" readonly="true" size="25"/>
			<A HREF="#" onClick="cal4xx.select(document.userExperienceForm.startdate,'anchor4xx','MMM d, yyyy'); return false;" TITLE="select date" NAME="anchor4xx" ID="anchor4xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
			
			<DIV ID="testdiv4" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
				</td>		
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.End_date",user1.getLocale())%></td>
			<td>
			<SCRIPT LANGUAGE="JavaScript" ID="jscal3xx">
			var cal3xx = new CalendarPopup("testdiv3");
			cal3xx.showNavigationDropdowns();
			
			</SCRIPT>
			<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
			
			
			<html:text property="enddate" readonly="true" size="25"/>
			<A HREF="#" onClick="cal3xx.select(document.userExperienceForm.enddate,'anchor3xx','MMM d, yyyy'); return false;" TITLE="select date" NAME="anchor3xx" ID="anchor3xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
			
			<DIV ID="testdiv3" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
				</td>		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.Reason_For_Leaving",user1.getLocale())%></td>
			<td><html:text property="reasonforleave" size="40"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.Reporting_Manager",user1.getLocale())%></td>
			<td><html:text property="reportingToName" size="40"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.City",user1.getLocale())%></td>
			<td><html:text property="city" size="40"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.Country",user1.getLocale())%></td>
						
			<td>
			<html:select  property="countryId" onchange="retrieveURLPrevOrg('user.do?method=loadState');">
			<option value=""></option>
			<bean:define name="userExperienceForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loadingprev" STYLE="font-size: smaller;Visibility:hidden";>
			loading......</span>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.State",user1.getLocale())%></td>
			<td>
				<span id="states">
					<html:select  property="stateId">
					<option value=""></option>
					<bean:define name="userExperienceForm" property="stateList" id="stateList" />
		            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
					</html:select>
				</span>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.Last_Salary_Drawn",user1.getLocale())%></td>
			<td><html:text property="lastSalary" size="20"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.Bonus",user1.getLocale())%></td>
			<td><html:text property="bonus" size="20"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.Currency_Type",user1.getLocale())%></td>
			<td>
				<html:select  property="currencyId">
				<bean:define name="userExperienceForm" property="currencyList" id="currencyList" />
	            <html:options collection="currencyList" property="currencyId"  labelProperty="currencyName"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.Responsibilities",user1.getLocale())%></td>
			<td><html:textarea property="responsibilities" cols="37" rows="5"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.Employer_Contact_Name",user1.getLocale())%></td>
			<td><html:text property="employercontactName" size="40"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.WorkExperience.Employer_Contact_Telephone",user1.getLocale())%></td>
			<td><html:text property="employercontactPhone" size="40"/></td>
		</tr>
		


</table>

	
<br>
		<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(cform.getOrgDetailsId() != 0){
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%
			}else{
			%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%
			}
			%>		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			</td>
		</tr>
		</table>
</html:form>
</body>

<%
}
%>
