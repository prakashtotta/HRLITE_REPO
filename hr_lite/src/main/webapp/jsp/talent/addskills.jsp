<SCRIPT LANGUAGE="JavaScript">
function addSkills(skill){
        var alertstr = "";

    	var len=0;
    	var skills;
    	if(skill == undefined){
    		len=0;

    	}else{
    		len=skill.length;
    		skills=skill.value;
    	}
		var skilladded="";
    	if(typeof document.applicantForm.skillname != 'undefined'){
			 skilladded = document.applicantForm.skillname.value.trim();
		}
    	
    	///alert(skilladded +" "+skills );
    	if(len != 0 ){
    		if(skills != undefined && skills == skilladded){
    			alert("<%=Constant.getResourceStringValue("hr.user.Qualifications.Skill.added",locale)%>");
    			return;
    		}

    		for(var i=0;i<len;i++)
    		{
    			if(skill[i].value.trim() == skilladded)
    			{
    			alert("<%=Constant.getResourceStringValue("hr.user.Qualifications.Skill.added",locale)%>");
    			return;
    			}
    		}
    	}
        
		var applid = "<%=aform.getApplicantId()%>";
		if(applid == "0"){
			alert("<%=Constant.getResourceStringValue("aquisition.applicant.saveappfirstmsg",locale)%>");
			return false;
		}

    var skillname = "";
	var yearsofexpskill = "";
	var ratingskill = "";
	if(typeof document.applicantForm.skillname != 'undefined'){
		skillname = document.applicantForm.skillname.value;
		}
	if(typeof document.applicantForm.yearsofexpskill != 'undefined'){
		yearsofexpskill = document.applicantForm.yearsofexpskill.value;
		}

	if(typeof document.applicantForm.ratingskill != 'undefined'){
		ratingskill = document.applicantForm.ratingskill.value;
		}

	
		

var url="applicantoffer.do?method=saveapplicantSkills&applicantid=<%=aform.getApplicantId()%>&skillname="+skillname+"&yearsofexpskill="+yearsofexpskill+"&ratingskill="+ratingskill;


//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeSkill;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeSkill;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveSkills()",200);

	}

function retrieveSkills() {
    document.getElementById("loading4").style.visibility = "visible";
		//document.applicantForm.skillname.value="";
		//document.applicantForm.yearsofexpskill.value="";
		//document.applicantForm.ratingskill.value="";
	var url="applicantoffer.do?method=getApplicantSkills&applicantid=<%=aform.getApplicantId()%>";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processSkills;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processSkills;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}


function processStateChangeSkill() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	
    	} 
    	
  	}
}

function processSkills() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlSkills(spanElements);
			
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading4").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlSkills(newTextElements){
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
				  
		    	  if(name="skilldetails")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function deleteskill(id){
var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",locale)%>");
	  if (doyou == true){
	var url="applicantoffer.do?method=deleteSkill&skillid="+id;

     
//url = encodeURIComponent(url);


      //Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeSkill;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeSkill;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
    


       setTimeout("retrieveSkills()",200);

	  }
}
</script>
<% 

if(SKIL_List.size()>0){%>

<fieldset><legend><b><%=Constant.getResourceStringValue("aquisition.applicant.Skills",locale)%></b></legend>

<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[addskills]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("aquisition.applicant.addskills",locale)%></div>
<div id="addskills" style="width  100%;">
<table border="0" width="100%">
<%

 for(int i=0;i<SKIL_List.size();i++){
	ScreenFields scf = (ScreenFields)SKIL_List.get(i);
     
%>

<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.SKILL.toString())){%>
    <tr>
	<td width="300px"><%=Constant.getResourceStringValue("aquisition.applicant.Skill",locale)%>
	<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.SKILL.toString())){%>
			<span1>*</span1>
			<%}%>
	</td>
		<td>
	<html:select  property="skillname">
			<bean:define name="applicantForm" property="skillList" id="skillList" />

            <html:options collection="skillList" property="key"  labelProperty="value"/>
			</html:select>
	</td>
	
	</tr>

<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.SKILL_YEARS_OF_EXPERIENCE.toString())){%>

    <tr>

	<td><%=Constant.getResourceStringValue("aquisition.applicant.Years_of_experience",locale)%>
		<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.SKILL_YEARS_OF_EXPERIENCE.toString())){%>
			<span1>*</span1>
			<%}%>
	</td>
	<td>
		<html:select  property="yearsofexpskill">
			<bean:define name="applicantForm" property="skillYearsList" id="skillYearsList" />

            <html:options collection="skillYearsList" property="key"  labelProperty="value"/>
			</html:select>
	</td>

	</tr>

<%}%>
<% if(scf.getFieldcode().equals(FieldCodes.ApplicantScreen.SKILL_RATING.toString())){%>
	<tr>
	<td><%=Constant.getResourceStringValue("aquisition.applicant.Rating",locale)%>
			<% if(mandatoryFieldsWorkExp.contains(FieldCodes.ApplicantScreen.SKILL_RATING.toString())){%>
			<span1>*</span1>
			<%}%>
	</td>
		<td>
	<html:select  property="ratingskill">
			<bean:define name="applicantForm" property="skillRatingList" id="skillRatingList" />

            <html:options collection="skillRatingList" property="key"  labelProperty="value"/>
			</html:select>
	</td>
	
	</tr>

<%}%>

<%} // end of for loop %>
	</table>
	
	<table border="0" width="100%">
	<tr>
	<td><br><a href="#" onClick="addSkills(document.applicantForm.skilName);return false" class="button">Add</a><br>
	<span class="textboxlabel" id="loading4" STYLE="font-size: smaller;Visibility:hidden";>
	<%=Constant.getResourceStringValue("aquisition.applicant.Please_wait",locale)%>......</span>
	</td>
	</tr>

    
	</table>
</div>


<div style="background: #f3f3f3;  text-align:left"><a href="#" rel="toggle[skilldetails]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a><%=Constant.getResourceStringValue("aquisition.applicant.addedskills",locale)%></div>
<div id="skilldetails" style="width  100%;" class="div">
<!--Skills details start-->
<%@ include file="../talent/skilldetails.jsp" %>
<!--Skills details end-->
</div>
   

	</fieldset>

<%}%>