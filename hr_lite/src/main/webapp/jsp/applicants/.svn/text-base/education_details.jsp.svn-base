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

function addEducation(){
        var alertstr = "";
		var applid = "<%=aform.getApplicantId()%>";
		if(applid == "0"){
			alert("<%=Constant.getResourceStringValue("aquisition.applicant.saveappfirstmsg",user1.getLocale())%>");
			return false;
		}


		var educationName = document.applicantForm.educationName.value;
		var specialization = document.applicantForm.specialization.value;
		var institute = document.applicantForm.instituteName.value;
		var percentile = document.applicantForm.percentile.value;
		var passingyear = document.applicantForm.passingYear.value;
		var startingYear = document.applicantForm.startingYear.value;


		var showalert=false;

	if(educationName == "" || educationName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Education_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(institute == "" || institute == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.College_University_is_required",user1.getLocale())%><BR>";
		showalert = true;
		}


	 if (showalert){
     	alert(alertstr);
        return false;
          }


var url="editapplicant.do?method=saveeducationdetails&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>&educationName="+educationName+"&specialization="+specialization+"&institute="+institute+"&percentile="+percentile+"&passingyear="+passingyear+"&startingYear="+startingYear;


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
    
       setTimeout("retrieveEducations()",200);

	}


function deleteeducation(id){
var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	var url="editapplicant.do?method=deleteEducation&eduid="+id;

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

       setTimeout("retrieveEducations()",200);
	  }

}

function retrieveEducations() {
    document.getElementById("loading1").style.visibility = "visible";
			document.applicantForm.educationName.value="";
		    document.applicantForm.specialization.value="";
		    document.applicantForm.instituteName.value="";
		    document.applicantForm.percentile.value="";
				document.applicantForm.startingYear.value="";
		    document.applicantForm.passingYear.value="";
	var url="editapplicant.do?method=geteducationdetails&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processEdu;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processEdu;
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

function processEdu() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmledu(spanElements);
			
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading1").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmledu(newTextElements){
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
				  
		    	  if(name="educations")
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


<a href="#" onClick="javascript:window.location.reload()">refresh</a>
<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Education_details",user1.getLocale())%></legend>
	<table border="0" width="100%">

    <tr>
	<td><%=Constant.getResourceStringValue("aquisition.applicant.Education",user1.getLocale())%></td>
		<td><html:select  property="educationName">
			<option value=""></option>
			<bean:define name="applicantForm" property="educationNamesList" id="educationNamesList" />

            <html:options collection="educationNamesList" property="key"  labelProperty="value"/>
			</html:select>
	</td>
	</tr><tr>
	<td><%=Constant.getResourceStringValue("aquisition.applicant.Specialization",user1.getLocale())%></td>
	<td><html:text property="specialization" size="40"/></td>
	</tr><tr>
	<td><%=Constant.getResourceStringValue("aquisition.applicant.College_University",user1.getLocale())%></td>
		<td><html:text property="instituteName" size="40"/></td>
	</tr><tr>
	<td><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></td>
	<td><html:text property="percentile" size="5"/></td>
	</tr>
	<tr>
	<td><%=Constant.getResourceStringValue("aquisition.applicant.Starting_year",user1.getLocale())%></td>
	<td><html:text property="startingYear" size="10"/></td>
	</tr>
	<tr>
	<td><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></td>
	<td><html:text property="passingYear" size="10"/></td>
	</tr>
    <tr>

	

	
	
	
	<td><input type="button" value="add education" onClick="addEducation()">
	<span class="textboxlabel" id="loading1" STYLE="font-size: smaller;Visibility:hidden";>
	<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",user1.getLocale())%>......</span>
	</td>
	</tr>
</table>
   <span id="educations">
    
<%
List eduList = aform.getEducationsList();

%>
<% if(eduList != null && eduList.size()>0){%>


<table  border="0" width="100%">

<%	
for(int i=0;i<eduList.size();i++){
	EducationDetails edu = (EducationDetails)eduList.get(i);
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
	
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Education",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getEducationName()%></td>
    </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Specialization",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getSpecialization()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.College_University",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getInstituteName()%></td>
	</tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getPercentile()%></td>
    </tr>
		<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Starting_year",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getStartingYear()%></td>
   </tr>
	<tr>
	<td width="40%"><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",user1.getLocale())%></td><td width="60%" align="left"> <%=edu.getPassingYear()%></td>
   </tr>
	

 </table>
 <!-- org details -->
</td>
<td>
<!-- attachements -->
<table valign="top" border="0" width="30%">
<%
String attachurl = "applicantuserops.do?method=actionattachmentaddscr&idvalue="+edu.getEducationId()+"&action="+actionName+"&applicantId="+applicant.getApplicantId()+"&secureid="+applicant.getUuid();
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
		if(actionattach.getAction() != null && actionattach.getAction().equals("education_details_proof_attachment")){

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
<td><a href="#" onClick="deleteeducation('<%=edu.getEducationId()%>')"><img src="jsp/images/delete.gif" border="0" alt="delete education" 
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

%>
<%=Constant.getResourceStringValue("aquisition.applicant.Educations_not_added",user1.getLocale())%>
<%}%>

   
	</span>

	</fieldset>

</html:form>