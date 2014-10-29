<%@ include file="../common/include.jsp" %>

<bean:define id="eform" name="emailNotificationSettingForm" type="com.form.EmailNotificationSettingForm" />
 <%

User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());


List emailFunctionList = eform.getEmailFunctionNotificationList();
String user = (String)request.getAttribute("user");
String updatedWeid = (String)request.getAttribute("updatedWeid");

%>

<br>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">

<script language="javascript">

function enableChkBox(weid,user,status){
	//alert(weid+" > "+user+" > "+status);
	var url="emailnotificationsetting.do?method=updateEmailNotificationStatus&weid="+weid+"&user="+user+"&status="+status;;

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

    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtml(spanElements);
		    //onOtherStateSel();
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
				  
		    	  if(name="emailnotification")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}
</script>
</head>
<body class="yui-skin-sam">

<html:form action="/emailnotificationsetting.do?method=saveEmailNotification">
<span id="emailnotification">

<table border="0" width="100%" cellspacing="4" cellpadding="4" class="div">
<tr bgcolor="#f3f3f3">
	<td width=350><b><%=Constant.getResourceStringValue("admin.emailnotification.function_name",user1.getLocale())%></b></td>
	<td width=150 align="left"><b><%=Constant.getResourceStringValue("admin.emailnotification.is_hiring_mgr",user1.getLocale())%></b></td>
	<td width=150 align="left"><b><%=Constant.getResourceStringValue("admin.emailnotification.is_recruiter",user1.getLocale())%></b></td>
	<td width=150 align="left"><b><%=Constant.getResourceStringValue("admin.emailnotification.is_watcher",user1.getLocale())%></b></td>
	<td width=150 align="left"><b><%=Constant.getResourceStringValue("admin.emailnotification.is_current_owner",user1.getLocale())%></b></td>
	
</tr>

<%
if(emailFunctionList != null){

	for(int i=0;i<emailFunctionList.size();i++){

		EmailNotificationSetting emailNotificationSetting = (EmailNotificationSetting)emailFunctionList.get(i);
    String bgcolor = "";
	if(i%2 == 1)bgcolor ="#B2D2FF";

%>




<tr bgcolor=<%=bgcolor%>>
	<%
	String resourcestr="admin.emailnotification."+emailNotificationSetting.getFunctionName();
	%>
	<td width=310><%=Constant.getResourceStringValue(resourcestr,user1.getLocale())%></td>
	<td width=150 align="left">
		<%if(emailNotificationSetting.getIsHiringMgr().equals("A")){ %>
			<input type="checkbox" id="isActive_<%=emailNotificationSetting.getWeid()%>" onClick="enableChkBox('<%=emailNotificationSetting.getWeid()%>','Is_Hiring_Manager','I');" name="isActive_<%=emailNotificationSetting.getWeid()%>" checked>
			<%if(! StringUtils.isNullOrEmpty(user) && user.equals("Is_Hiring_Manager") && updatedWeid.equals(String.valueOf(emailNotificationSetting.getWeid()))){ %><font color="green"><%=Constant.getResourceStringValue("admin.emailnotification.updated",user1.getLocale())%> </font><%}%>
			
		<%}else if(emailNotificationSetting.getIsHiringMgr().equals("I")){ %>
			<input type="checkbox" id="isActive_<%=emailNotificationSetting.getWeid()%>" onClick="enableChkBox('<%=emailNotificationSetting.getWeid()%>','Is_Hiring_Manager','A');" name="isActive_<%=emailNotificationSetting.getWeid()%>%>" >
			<%if(! StringUtils.isNullOrEmpty(user) && user.equals("Is_Hiring_Manager") && updatedWeid.equals(String.valueOf(emailNotificationSetting.getWeid()))){ %><font color="green"><%=Constant.getResourceStringValue("admin.emailnotification.updated",user1.getLocale())%> </font><%}%>
		<%} %>
	</td>

	<td width=150 align="left">
		<%if(emailNotificationSetting.getIsRecruiter().equals("A")){ %>
			<input type="checkbox" id="isActive_<%=emailNotificationSetting.getWeid()%>" onClick="enableChkBox('<%=emailNotificationSetting.getWeid()%>','Is_Recruiter','I');" name="isActive_<%=emailNotificationSetting.getWeid()%>" checked>
			<%if(! StringUtils.isNullOrEmpty(user) && user.equals("Is_Recruiter") && updatedWeid.equals(String.valueOf(emailNotificationSetting.getWeid()))){ %><font color="green"><%=Constant.getResourceStringValue("admin.emailnotification.updated",user1.getLocale())%> </font><%}%>
		<%}else if(emailNotificationSetting.getIsRecruiter().equals("I")){ %>
			<input type="checkbox" id="isActive_<%=emailNotificationSetting.getWeid()%>" onClick="enableChkBox('<%=emailNotificationSetting.getWeid()%>','Is_Recruiter','A');" name="isActive_<%=emailNotificationSetting.getWeid()%>%>">
			<%if(! StringUtils.isNullOrEmpty(user) && user.equals("Is_Recruiter") && updatedWeid.equals(String.valueOf(emailNotificationSetting.getWeid()))){ %><font color="green"><%=Constant.getResourceStringValue("admin.emailnotification.updated",user1.getLocale())%> </font><%}%>
		<%} %>
	</td>
		<td width=150 align="left">
		<%if(emailNotificationSetting.getIsWatcher().equals("A")){ %>
			<input type="checkbox" id="isActive_<%=emailNotificationSetting.getWeid()%>" onClick="enableChkBox('<%=emailNotificationSetting.getWeid()%>','Is_Watcher','I');" name="isActive_<%=emailNotificationSetting.getWeid()%>" checked>
			<%if(! StringUtils.isNullOrEmpty(user) && user.equals("Is_Watcher") && updatedWeid.equals(String.valueOf(emailNotificationSetting.getWeid()))){ %><font color="green"><%=Constant.getResourceStringValue("admin.emailnotification.updated",user1.getLocale())%> </font><%}%>
		<%}else if(emailNotificationSetting.getIsWatcher().equals("I")){ %>
			<input type="checkbox" id="isActive_<%=emailNotificationSetting.getWeid()%>" onClick="enableChkBox('<%=emailNotificationSetting.getWeid()%>','Is_Watcher','A');" name="isActive_<%=emailNotificationSetting.getWeid()%>%>">
			<%if(! StringUtils.isNullOrEmpty(user) && user.equals("Is_Watcher") && updatedWeid.equals(String.valueOf(emailNotificationSetting.getWeid()))){ %><font color="green"><%=Constant.getResourceStringValue("admin.emailnotification.updated",user1.getLocale())%> </font><%}%>
		<%} %>
	</td>
	<td width=150 align="left">
		<%if(emailNotificationSetting.getIsCurrentOwner().equals("A")){ %>
			<input type="checkbox" id="isActive_<%=emailNotificationSetting.getWeid()%>" onClick="enableChkBox('<%=emailNotificationSetting.getWeid()%>','Is_Current_Owner','I');" name="isActive_<%=emailNotificationSetting.getWeid()%>" checked>
			<%if(! StringUtils.isNullOrEmpty(user) && user.equals("Is_Current_Owner") && updatedWeid.equals(String.valueOf(emailNotificationSetting.getWeid()))){ %><font color="green"><%=Constant.getResourceStringValue("admin.emailnotification.updated",user1.getLocale())%> </font><%}%>
		<%}else if(emailNotificationSetting.getIsCurrentOwner().equals("I")){ %>
			<input type="checkbox" id="isActive_<%=emailNotificationSetting.getWeid()%>" onClick="enableChkBox('<%=emailNotificationSetting.getWeid()%>','Is_Current_Owner','A');" name="isActive_<%=emailNotificationSetting.getWeid()%>%>" >
			<%if(! StringUtils.isNullOrEmpty(user) && user.equals("Is_Current_Owner") && updatedWeid.equals(String.valueOf(emailNotificationSetting.getWeid()))){ %><font color="green"><%=Constant.getResourceStringValue("admin.emailnotification.updated",user1.getLocale())%> </font><%}%>
		<%} %>
	</td>
</tr>
<%}
}
%>

</table>
</span>


</html:form>
</body>


