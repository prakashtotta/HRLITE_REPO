<%@ include file="../common/include.jsp" %>
  <%

String datepattern = Constant.getValue("defaultdateformat");
ApplicantUser user1 = (ApplicantUser)request.getSession().getAttribute(Common.APPLICANT_USER_DATA);

if(user1 != null){
datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}
JobApplicant applicant = user1.getApplicant();
String actionName = (String)request.getAttribute("action_name");
%>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 100%;
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


<bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />

<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		 parent.parent.GB_hide();
	 
	   } 
	}


function deleteAttachment(url){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		
		window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300");
	
	 
	   } 
}

function addPreviousOrg(){
        var alertstr = "";
		var applid = "<%=aform.getApplicantId()%>";
		if(applid == "0"){
			alert("<%=Constant.getResourceStringValue("aquisition.applicant.saveappfirstmsg",user1.getLocale())%>");
			return false;
		}


		var prevOrgName = document.applicantForm.prevOrgName.value;
		var role = document.applicantForm.role.value;
		var startDate = document.applicantForm.startDate.value;
		var endDate = document.applicantForm.endDate.value;
        var reasonforleave = document.applicantForm.reasonforleave.value;
		var reportingToName = document.applicantForm.reportingToName.value;

		var showalert=false;

	if(prevOrgName == "" || prevOrgName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.orgnamereqmsg",user1.getLocale())%><BR>";
		showalert = true;
		}



	 if (showalert){
     	alert(alertstr);
        return false;
          }


var url="editapplicant.do?method=savepreviousOrgdetails&applicantid=<%=aform.getApplicantId()%>&prevOrgName="+prevOrgName+"&role="+role+"&startDate="+startDate+"&endDate="+endDate+"&reasonforleave="+reasonforleave+"&reportingto="+reportingToName;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveOrganizations()",200);

}

function deletePreviousOrg(id){
var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	var url="editapplicant.do?method=deletePrevOg&prevorgid="+id;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveOrganizations()",200);
	  }

}

function retrieveOrganizations() {
	
    document.getElementById("loading3").style.visibility = "visible";
		document.applicantForm.prevOrgName.value="";
		document.applicantForm.role.value="";
		document.applicantForm.startDate.value="";
		document.applicantForm.endDate.value="";
        document.applicantForm.reasonforleave.value="";
	    document.applicantForm.reportingToName.value="";
	var url="editapplicant.do?method=getpreviousOrgdetails&applicantid=<%=aform.getApplicantId()%>";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processOrg;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processOrg;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}

function processStateChangeNo() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	
    	} 
    	
  	}
}

function processOrg() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlOrg(spanElements);
			
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading3").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlOrg(newTextElements){
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
				  
		    	  if(name="prevorgdetails")
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

function addattachmentscr(url){

	window.open(url, "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=500");
}


</script>

<html:form action="/editapplicant.do?method=saveapplicantData" enctype="multipart/form-data">

<div id="country2" class="tabcontent">
<div align="center">

<a href="#" onClick="javascript:window.location.reload()">refresh</a>

<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Work_experience",user1.getLocale())%></legend>
<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[addworkexp]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> add work experience  </div>
	<table border="0" width="100%">

    <tr>

	<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
		<td>	<html:text property="prevOrgName" size="20"/>	</td>
	</tr>
	<tr>
	<td><%=Constant.getResourceStringValue("aquisition.applicant.Last_role",user1.getLocale())%></td>
	<td><html:text property="role" size="20"/></td>
		</tr>
	<tr>
	<td><%=Constant.getResourceStringValue("aquisition.applicant.Reporting_Manager",user1.getLocale())%></td>
	<td><html:text property="reportingToName" size="20"/></td>
		</tr>
	<tr>
	<td><%=Constant.getResourceStringValue("aquisition.applicant.Joining_date",user1.getLocale())%></td>
	<td><html:text property="startDate" size="20"/></td>
		</tr>
	<tr>
	<td><%=Constant.getResourceStringValue("aquisition.applicant.Relieving_date",user1.getLocale())%></td>
	<td><html:text property="endDate" size="20"/></td>
		</tr>
	<tr>
	<td><%=Constant.getResourceStringValue("aquisition.applicant.Reason_for_leave",user1.getLocale())%></td>
	<td><html:text property="reasonforleave" size="30"/></td>
	
	</tr>
    <tr>

	
	
	
	
	
	<td><input type="button" value="add work experience" onClick="addPreviousOrg()">
	<span class="textboxlabel" id="loading3" STYLE="font-size: smaller;Visibility:hidden";>
	<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>......</span>
	</td>
	</tr>

    
	</table>

   <span id="prevorgdetails">
    
<%
List orgList = aform.getPreviousOrgList();

%>
<% if(orgList != null && orgList.size()>0){%>


<table  border="0" width="100%">

<%	
for(int i=0;i<orgList.size();i++){
	PreviousOrgDetails edu = (PreviousOrgDetails)orgList.get(i);
	String bgcolor="";
	if(i%2==0){
		bgcolor="bgcolor=#f3f3f3";
	}else{
		bgcolor="bgcolor=#DCD9D1";
		
	}
%>

<tr <%=bgcolor%> >
<td>
<!-- org details -->
<table valign="top" border="0" width="60%">
    <tr>
	
	<td width="40%"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getPrevOrgName()%></td>
    </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Last_role",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getRole()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Reporting_Manager",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getReportingToName()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Joining_date",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getStartdate()%></td>
    </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Relieving_date",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getEnddate()%></td>
   </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Reason_for_leave",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getReasonforleave()%></td>

	</tr>

 </table>
 <!-- org details -->
</td>
<td>
<!-- attachements -->
<table valign="top" border="0" width="30%">
<%
String attachurl = "applicantuserops.do?method=actionattachmentaddscr&idvalue="+edu.getPrevOrgDetailsId()+"&action="+actionName+"&applicantId="+applicant.getApplicantId()+"&secureid="+applicant.getUuid();
%>
<tr>

<td width="125px"><a href="#" onClick="addattachmentscr('<%=attachurl%>')">add proofs</a></td>
<td></td>
<td></td>
</tr>


<%
List appattachmentList = edu.getAttachmentList();

if(appattachmentList != null && appattachmentList.size()>0){
	%>
	<table cellspacing="5">

<%
		for(int j=0;j<appattachmentList.size();j++){
		ActionsAttachment actionattach = (ActionsAttachment)appattachmentList.get(j);
		if(actionattach.getAction() != null && actionattach.getAction().equals("experience_details_proof_attachment")){
		String dleteurl = request.getContextPath()+"/applicantuserops.do?method=deleteattachment&actionname="+actionName+"&applicantId="+applicant.getApplicantId()+"&secureid="+applicant.getUuid()+"&uuid="+actionattach.getUuid();

%>
<tr>
<td width="125px"></td>
<td><%=(actionattach.getAttahmentdetails()==null)?"":actionattach.getAttahmentdetails()%></td>
<td>
<a href="applicantuserops.do?method=attachmentdetails&uuid=<%=actionattach.getUuid()%>"><%=actionattach.getAttahmentname()%></a>
<a href="#" onClick="deleteAttachment('<%=dleteurl%>')"><img src="jsp/images/delete.gif" border="0" alt="delete attachment" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_organization",user1.getLocale())%>" height="10"  width="9"/></a>
</td>
</tr>
<%
		}
	}%>

<%}%>

 </table>


 <!-- attachements -->
 <td>
<!-- delete -->
<table valign="top" border="0" width="10%">
<tr>
<td><a href="#" onClick="deletePreviousOrg('<%=edu.getPrevOrgDetailsId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete organization" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_organization",user1.getLocale())%>" height="20"  width="19"/></a></td>
 </td>
</tr>
 </table>
<!-- delete end -->
 </td>
 </tr>
<%
}
 %>

 </table>
<%
}else{
System.out.println("orgList : "+orgList);
%>
<%=Constant.getResourceStringValue("aquisition.applicant.ExpDetailsNotAddMsg",user1.getLocale())%>
<%}%>

   
	</span>

	</fieldset>

</html:form>