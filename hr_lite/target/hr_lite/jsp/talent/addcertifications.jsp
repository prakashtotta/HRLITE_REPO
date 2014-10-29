<SCRIPT LANGUAGE="JavaScript">

function addCertifications(){
        var alertstr = "";
		var applid = "<%=aform.getApplicantId()%>";
		if(applid == "0"){
			alert("<%=Constant.getResourceStringValue("aquisition.applicant.saveappfirstmsg",locale)%>");
			return false;
		}

        var certName ="";
		var specialization = "";
		var certorgName ="";
		var certpercentile="";
		var certpassingYear="";
        if(typeof document.applicantForm.certName != 'undefined'){
			certName = document.applicantForm.certName.value;
		}
		if(typeof document.applicantForm.certorgName != 'undefined'){
			certorgName = document.applicantForm.certorgName.value;
		}
		if(typeof document.applicantForm.certpercentile != 'undefined'){
			certpercentile = document.applicantForm.certpercentile.value;
		}
		if(typeof document.applicantForm.certpassingYear != 'undefined'){
			certpassingYear = document.applicantForm.certpassingYear.value;
		}
	
		var showalert=false;

	
	 if (showalert){
     	alert(alertstr);
        return false;
          }

document.getElementById("loading2").style.visibility = "visible";
var url="applicantoffer.do?method=saveeducationdetails&type=<%=Common.EDUCATION_CERTIFICATION%>&applicantid=<%=aform.getApplicantId()%>&secureid=<%=aform.getUuid()%>&educationName="+certName+"&specialization="+specialization+"&institute="+certorgName+"&percentile="+certpercentile+"&passingyear="+certpassingYear;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processCert;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processCert;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    

  	//resetcertification();
       //setTimeout("retrieveCertifications()",200);

	}


function processStateChangeCert() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	
    	} 
    	
  	}
}

function deletecertification(id){

	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",locale)%>");
	  if (doyou == true){


	var url="applicantoffer.do?method=deleteEducation&eduid="+id;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeCert;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeCert;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveCertifications()",200);

	  }
}
function resetcertification(){
	if(typeof document.applicantForm.certName != 'undefined'){
			document.applicantForm.certName.value="";
		}
		if(typeof document.applicantForm.certorgName != 'undefined'){
			document.applicantForm.certorgName.value="";
		}
		if(typeof document.applicantForm.certpercentile != 'undefined'){
			document.applicantForm.certpercentile.value="";
		}
		if(typeof document.applicantForm.certpassingYear != 'undefined'){
			document.applicantForm.certpassingYear.value="";
		}
}
function retrieveCertifications() {
    document.getElementById("loading2").style.visibility = "visible";
		//document.applicantForm.certName.value="";
		//document.applicantForm.certorgName.value="";
		//document.applicantForm.certpercentile.value="";
		//document.applicantForm.certpassingYear.value="";
	var url="applicantoffer.do?method=geteducationdetails&type=<%=Common.EDUCATION_CERTIFICATION%>&applicantid=<%=aform.getApplicantId()%>&uuid=<%=aform.getUuid()%>";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processCert;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processCert;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}

function processCert() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlCert(spanElements);
			
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading2").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlCert(newTextElements){
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
				  
		    	  if(name="certifications")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}


</script>

<%if(CERTIFICATION_List.size()>0){%>

<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Certifications_details",locale)%></legend>
	
	<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[addcertification]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("aquisition.applicant.add.certification",locale)%></div>
<div id="addcertification" style="width  100%;">
	<table border="0" width="100%">
<%

 for(int i=0;i<CERTIFICATION_List.size();i++){
	ScreenFields scf = (ScreenFields)CERTIFICATION_List.get(i);
     
%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.CERTIFICATION_NAME.toString())){%>
    <tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.name",locale)%>
		<% if(mandatoryFieldsEducationTab.contains(FieldCodes.ApplicantScreen.CERTIFICATION_NAME.toString())){%>
			<span1>*</span1>
		<%}%>
		</td>
		<td><html:text property="certName" size="40"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.CERTIFIED_ORGANIZATION.toString())){%>
	<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Certified_Organization",locale)%>
				<% if(mandatoryFieldsEducationTab.contains(FieldCodes.ApplicantScreen.CERTIFIED_ORGANIZATION.toString())){%>
			<span1>*</span1>
		<%}%>
		</td>
		<td><html:text property="certorgName" size="40"/></td>
	</tr>
<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.CERTIFICATION_PERCENT_GRADE.toString())){%>
	<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Percentage_Grade",locale)%>
				<% if(mandatoryFieldsEducationTab.contains(FieldCodes.ApplicantScreen.CERTIFICATION_PERCENT_GRADE.toString())){%>
			<span1>*</span1>
		<%}%>
		</td>
		<td><html:text property="certpercentile" size="5"/></td>
	</tr>
	<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.CERIFICATION_PASS_MONTH_YEAR.toString())){%>
	<tr>
		<td><%=Constant.getResourceStringValue("aquisition.applicant.Passing_year",locale)%>
				<% if(mandatoryFieldsEducationTab.contains(FieldCodes.ApplicantScreen.CERIFICATION_PASS_MONTH_YEAR.toString())){%>
			<span1>*</span1>
		<%}%>
		</td>
		<td><html:text property="certpassingYear" size="5"/></td>
	</tr>
<%}%>
<%}%>
	<tr>
	<td>
	<input type="button" name="add certification" value="<%=Constant.getResourceStringValue("aquisition.applicant.add.certification",locale)%>" onClick="addCertifications();return false" class="button"/>
	<input type="button" name="reset" value="reset" onClick="resetcertification();return false" class="button"/>
	<span class="textboxlabel" id="loading2" STYLE="font-size: smaller;Visibility:hidden" class="button";>
	<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",locale)%>......</span>
	</td>
	</tr>

    
	</table>
</div>
	<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[certificationdetails]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> <%=Constant.getResourceStringValue("aquisition.applicant.add.certification.added",locale)%></div>
<div id="certificationdetails" style="width  100%;" class="div">
<!--Certifications details start-->
<%@ include file="../talent/certificationdetails.jsp" %>
<!--Certifications details end-->
</div>
	</fieldset>

<%}%>