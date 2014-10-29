
<SCRIPT LANGUAGE="JavaScript">
function addEducation(edu){
        var alertstr = "";
    	var len=0;
    	var educations;
    	if(edu == undefined){
    		len=0;

    	}else{
    		len=edu.length;
    		educations=edu.value;
    	}
    	
    	
		var eduadded="";
    	if(typeof document.applicantForm.educationName != 'undefined'){
			 eduadded = document.applicantForm.educationName.value.trim();
		}
    	//alert(eduadded +" "+educations );
    	if(len != 0 ){
    		if(educations != undefined && educations == eduadded){
    			alert(eduadded+" <%=Constant.getResourceStringValue("hr.Validation.alreadyAdded",locale)%>");
    			return;
    		}

    		for(var i=0;i<len;i++)
    		{
    			if(edu[i].value.trim() == eduadded)
    			{
    			alert(eduadded+" <%=Constant.getResourceStringValue("hr.Validation.alreadyAdded",locale)%>");
    			return;
    			}
    		}
    	}
		var applid = "<%=aform.getApplicantId()%>";
		if(applid == "0"){
			alert("<%=Constant.getResourceStringValue("aquisition.applicant.saveappfirstmsg",locale)%>");
			return false;
		}

        var educationName="";
		var specialization = "";
		var institute = "";
		var percentile ="";
		 var passingyear ="";
		 var startingYear = "";
    	if(typeof document.applicantForm.educationName != 'undefined'){
			 educationName = document.applicantForm.educationName.value;
		}
		if(typeof document.applicantForm.specialization != 'undefined'){
			 specialization = document.applicantForm.specialization.value;
		}
		if(typeof document.applicantForm.instituteName != 'undefined'){
			 institute = document.applicantForm.instituteName.value;
		}
		if(typeof document.applicantForm.percentile != 'undefined'){
			 percentile = document.applicantForm.percentile.value;
		}
		if(typeof document.applicantForm.passingYear != 'undefined'){
			 passingyear = document.applicantForm.passingYear.value;
		}
		if(typeof document.applicantForm.startingYear != 'undefined'){
			startingYear = document.applicantForm.startingYear.value;
		}
	
	
		var showalert=false;

	 if (showalert){
     	alert(alertstr);
        return false;
          }

document.getElementById("loading1").style.visibility = "visible";
var url="applicantoffer.do?method=saveeducationdetails&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>&secureid=<%=aform.getUuid()%>&educationName="+educationName+"&specialization="+specialization+"&institute="+institute+"&percentile="+percentile+"&passingyear="+passingyear+"&startingYear="+startingYear;


//url = encodeURIComponent(url);


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
  //	resetEducation();


       //setTimeout("retrieveEducations()",200);

	}
function resetEducation(){

	if(typeof document.applicantForm.educationName != 'undefined'){
			 document.applicantForm.educationName.value="";
		}
		if(typeof document.applicantForm.specialization != 'undefined'){
			 document.applicantForm.specialization.value="";
		}
		if(typeof document.applicantForm.instituteName != 'undefined'){
			 document.applicantForm.instituteName.value="";
		}
		if(typeof document.applicantForm.percentile != 'undefined'){
			 document.applicantForm.percentile.value="";
		}
		if(typeof document.applicantForm.passingYear != 'undefined'){
			 document.applicantForm.passingYear.value="";
		}
		if(typeof document.applicantForm.startingYear != 'undefined'){
			document.applicantForm.startingYear.value="";
		}
}

function retrieveEducations() {
    document.getElementById("loading1").style.visibility = "visible";
			
		    
	var url="applicantoffer.do?method=geteducationdetails&type=<%=Common.EDUCATION_COLLEGE%>&applicantid=<%=aform.getApplicantId()%>&uuid=<%=aform.getUuid()%>";

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

function processStateChangeEdu() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	
    	} 
    	
  	}
}

function deleteeducation(id){

	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",locale)%>");
	  if (doyou == true){


	var url="applicantoffer.do?method=deleteEducation&eduid="+id;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeEdu;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeEdu;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveEducations()",200);

	  }
}
</script>



 
<%if(EDUCATION_DETAILS_List.size()>0){%>

 <fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Education_details",locale)%></legend>
	
	<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[addeducation]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("aquisition.applicant.add.education",locale)%></div>
<div id="addeducation" style="width  100%;" class="div">
	<table border="0" width="100%">

<%

 for(int i=0;i<EDUCATION_DETAILS_List.size();i++){
	ScreenFields scf = (ScreenFields)EDUCATION_DETAILS_List.get(i);
     
%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EDUCATION_NAME.toString())){%>
    <tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Education",locale)%>
		<% if(mandatoryFieldsEducationTab.contains(FieldCodes.ApplicantScreen.EDUCATION_NAME.toString())){%>
			<span1>*</span1>
		<%}%>
		</td>
			<td><html:select  property="educationName">
				<option value=""></option>
				<bean:define name="applicantForm" property="educationNamesList" id="educationNamesList" />
	
	            <html:options collection="educationNamesList" property="key"  labelProperty="value"/>
				</html:select>
		</td>
	</tr>
	<tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.SPECIALIZATION.toString())){%>

	<td><%=Constant.getResourceStringValue("aquisition.applicant.Specialization",locale)%>
			<% if(mandatoryFieldsEducationTab.contains(FieldCodes.ApplicantScreen.SPECIALIZATION.toString())){%>
			<span1>*</span1>
		<%}%>
	</td>
		
		<td><html:text property="specialization" size="40"/></td>
		</tr>
	<tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EDUCATION_COLL_UNIVERSITY.toString())){%>	
		<td><%=Constant.getResourceStringValue("aquisition.applicant.College_University",locale)%>
		<% if(mandatoryFieldsEducationTab.contains(FieldCodes.ApplicantScreen.EDUCATION_COLL_UNIVERSITY.toString())){%>
			<span1>*</span1>
		<%}%>
		</td>
		<td><html:text property="instituteName" size="40"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EDUCATION_PERCENT_GRADE.toString())){%>
		<tr><td><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",locale)%>
		<% if(mandatoryFieldsEducationTab.contains(FieldCodes.ApplicantScreen.EDUCATION_PERCENT_GRADE.toString())){%>
			<span1>*</span1>
		<%}%>
		</td>
		<td><html:text property="percentile" size="5"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EDUCATION_START_MONTH_YEAR.toString())){%>
	<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Starting_year",locale)%>
		<% if(mandatoryFieldsEducationTab.contains(FieldCodes.ApplicantScreen.EDUCATION_START_MONTH_YEAR.toString())){%>
			<span1>*</span1>
		<%}%>
		</td>
		<td><html:text property="startingYear" size="10"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.EDUCATION_PASSING_MONTH_YEAR.toString())){%>
	<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",locale)%>
		<% if(mandatoryFieldsEducationTab.contains(FieldCodes.ApplicantScreen.EDUCATION_PASSING_MONTH_YEAR.toString())){%>
			<span1>*</span1>
		<%}%>
		</td>
		<td><html:text property="passingYear" size="10"/></td>
	</tr>
    <tr>
<%}%>
	<%} // end of for loop %>
	
	
	
	
	</tr>
	<tr>
	<td>
	<input type="button" name="add education" value="<%=Constant.getResourceStringValue("aquisition.applicant.add.education",locale)%>" onClick="addEducation(document.applicantForm.eduName);return false" class="button"/>
	<input type="button" name="reset" value="reset" onClick="resetEducation();return false" class="button"/>
	<span class="textboxlabel" id="loading1" STYLE="font-size: smaller;Visibility:hidden";>
	<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",locale)%>......</span>
	</td>
	</tr>

    
	</table>
</div>
	<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[educationdetails]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("aquisition.applicant.add.education.added",locale)%></div>
<div id="educationdetails" style="width  100%;" class="div">
<!--education details start-->
<%@ include file="../talent/educationdetails.jsp" %>
<!--education details end-->
</div>

	</fieldset>

<%}// check size of EDUCATION_DETAILS_List%>