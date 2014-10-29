	<%@ include file="../common/include.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>

	<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>
	<%@ page import="com.bean.*"%>
	<%@ page import="com.bean.onboard.*"%>

  <meta http-equiv="expires" content="Sun, 01 Dec 2001 12:00:00 EST" />

	<bean:define id="sform" name="onBoardingForm" type="com.form.OnBoardingForm" />

	<%
	////response.setHeader("Cache-Control", "no-cache");
	//		//response.setHeader("Pragma", "no-cache");
	//		//response.setIntHeader("Expires", 0);
	%>

	
	  <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String jsscriptdata = (String)request.getAttribute("jsscriptdata");
%>


<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 1200px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 1200px;
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
<style>
span1{color:#ff0000;}
</style>
  

	<script language="javascript">

function userDetailsOrGroup(id,isgroup){
	var url = "";
	if(isgroup == 'Y'){
		url = "<%=request.getContextPath()%>/usergroup.do?method=editusergroup&readPreview=2&usergroupid="+id;
	}else{
			url = "<%=request.getContextPath()%>/user.do?method=edituser&readPreview=2&userId="+id;
		}
	
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	window.open(url, 'UserDetails','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600');
}

	function closewindow(){
	  parent.parent.GB_hide();
}



	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		   parent.parent.GB_hide();
		   } 
		}

	function savedata(){

		 document.onBoardingForm.action = "onboarding.do?method=saveOnBoarding";
         document.onBoardingForm.submit();
	   
		}



function validateUser(id){
	var hidd = 'taskownerhiddenname_'+id;
	var inname = 'taskowner_'+id;
	//document.onBoardingForm[inname].value=document.onBoardingForm[hidd].value;

	if(document.onBoardingForm[hidd].value == "" || document.onBoardingForm[hidd].value == null){
	
		document.onBoardingForm[inname].value=document.onBoardingForm[inname].value;
		}else{
	
	document.onBoardingForm[inname].value=document.onBoardingForm[hidd].value;
	}

}

function isIntCheck(value){
	if(isInteger(value)){
	}else{
		alert("Only Integer is allowed");
		return false;
	}
}

function isInteger (s) 
{ 
var i; 

if (isEmpty(s)) 
if (isInteger.arguments.length == 1) return 0; 
else return (isInteger.arguments[1] == true); 

for (i = 0; i < s.length; i++) 
{ 
var c = s.charAt(i); 

if (!isDigit(c)) return false; 
} 

return true; 
} 

function isEmpty(s) 
{ 
return ((s == null) || (s.length == 0)) 
} 

function isDigit (c) 
{ 
return ((c >= "0") && (c <= "9")) 
} 


function opensearchtaskassign(bn){
	//alert(boxnumber);
	var t = "user.do?method=onboardTaskUserSelector&stringboxval=approver&boxnumber="+bn;
	var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(t);
  
  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}


function retrieveOnBoardTaskDetails() {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	var urlnew="onboarding.do?method=loadingOnboardTaskDetails"+"&templateId="+document.onBoardingForm.onboardingTemplateId.value;

	//alert(urlnew);
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeResumeSource;

	    try {
    		req.open("GET", urlnew, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
			alert(urlnew);
	    	req.onreadystatechange=processStateChangeResumeSource;
	        req.open("GET", urlnew, true);
		    req.send();
			
    	}
  	}
}

function processStateChangeResumeSource() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlResumeSource(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlResumeSource(newTextElements){
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
				  
		    	  if(name="onboardtaskdetails")
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
	</script>



<%
String initiatedOBoarding = (String)request.getAttribute("initiatedOBoarding");
	
if(initiatedOBoarding != null && initiatedOBoarding.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.Initiate_onboarding.started",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}else {%>

	<body class="yui-skin-sam">
	<html:form action="/onboarding.do?method=scheduleInterview">

	<div align="center" class="div">
		<table border="0" width="100%">
		<font color = red ><html:errors /> </font>

<tr>
					<td><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Applicant",user1.getLocale())%></td>

		
				<td>
				<%
                    List applicantList = sform.getApplicantList();
				session.setAttribute("applicantList",applicantList);
				String initiateonboardlist = "";
				String alreadyinitiateonboardlist = "";
				    for(int i=0;i<applicantList.size();i++){
						JobApplicant applicant = (JobApplicant)applicantList.get(i);
						if(applicant.getInitiateJoiningProcess().equals(Common.ON_BOARDING_NOT_STARTED)){
						initiateonboardlist = initiateonboardlist + "# "+applicant.getFullName() + "&nbsp;&nbsp;";
						}else{
							alreadyinitiateonboardlist = alreadyinitiateonboardlist + "# "+applicant.getFullName() + "&nbsp;&nbsp;";
						}
                 %>
                 

				 <%}%>
				 <font color="green"><b><%=initiateonboardlist%> </b></font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
				 <% if(alreadyinitiateonboardlist.length()>0){%> On boarding already initiated for <font color="red"><b><%=alreadyinitiateonboardlist%> </b></font> <%}%>
   
				</td>
			</tr>
<tr>
					<td>Template:</td>

		
				<td>
				<html:select  property="onboardingTemplateId" onchange="retrieveOnBoardTaskDetails();">
			<bean:define name="onBoardingForm" property="onboardingTemplateList" id="onboardingTemplateList" />

            <html:options collection="onboardingTemplateList" property="templateid"  labelProperty="templateName"/>
			</html:select>
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("admin.loading",user1.getLocale())%>......</span>

				</td>
			</tr>
</table>
 <fieldset><legend>Task definitions</legend>
  <span id="onboardtaskdetails">

   

 <table align="left">
 <tr bgcolor=#EEF3E2>
		<td><b>Required</b></td><td><b>Task</b></td><td><b>Description</b></td>
		<td><b>Owner</b></td><td><b>Criteria</b> </td>

	</tr>
<%
OnBoardingTemplate template = sform.getTemplate();
System.out.println("template >> "+template);
if(template != null){
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
		<td><INPUT NAME="taskchk" TYPE="CHECKBOX" VALUE="<%=taskdef.getTaskdefid()%>" checked></td><td><b><%=taskdef.getTaskName()%></b></td><td><%=taskdef.getTaskDesc()%></td>
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
		

  

		</td><td><input type="text" size="3" name="tasknofodays_<%=taskdef.getTaskdefid()%>" value="<%=taskdef.getNoofdays()%>" maxlength="3" onblur="if(isIntCheck(this.value)==false)this.value=0;"/>days &nbsp; <i><%=(taskdef.getEventType()==null)?"":taskdef.getEventType().toLowerCase()%> </i>&nbsp;<b>On boarding date</b></td>
     </tr>
	
     
     <%
      List attributes = taskdef.getAtrributes();
	 if(attributes != null && attributes.size()>0){

	  %>
      <tr><td></td><td>
	  <table>
	  <tr bgcolor=#f3f3f3><td width="300px" >Attributes</td></tr>
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
}
%>
</span>
</fieldset>


			<tr>
				<td>
				
				
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">
			
				</td>
				<td><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			</tr>

		</table>
	</div>

	</html:form>


	<%}%>




	</body>