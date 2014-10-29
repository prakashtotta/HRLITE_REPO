<%@ include file="../common/include.jsp" %>
<%@ include file="../common/autocompleteMultiple.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>
<style>
span1{color:#ff0000;}

</style>



<style type="text/css"> 
#elem528 { position:absolute; left:155px; top:164px; width:60px; height:20px; }  
#elem529 { position:absolute; left:212px; background-color: #f3f3f3; width:300px; height:200px; }
#elem530 { position:absolute;  left:547px; top:300px; width:40px; height:50px; }
#elem531 { position:absolute;  left:10px; top:450px; width:500px; height:50px; }
#elem532 { position:absolute;  left:2px; top:200px; width:40px; height:50px; }
#elem533 { position:absolute;  left:10px; top:250px; width:500px; height:50px; }
</style>

<bean:define id="systemform" name="systemRuleForm" type="com.form.SystemRuleForm" />

<script language="javascript">
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
		
 self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}
function closewindow(){
	 //self.parent.location.reload();
	 parent.parent.GB_hide();
}

 function savedata(){
	  var alertstr = "";
	var showalert=false;
		var userids ="";
	var rulname=document.systemRuleForm.ruleName.value.trim();
	var desc=document.systemRuleForm.ruleDesc.value.trim();
	
    
	
	if(rulname == "" || rulname == null){
     	alertstr = alertstr + "Rule Name is mandatory<br>";
		showalert = true;
		}
			
	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Rule_Desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }

var theSel = window.document['systemRuleForm'].users;

  if(theSel != null && theSel != 'undefind'){
    for(i=theSel.length-1; i>=0; i--)
    {

	userids = userids + theSel.options[i].value + ",";
	}
  }
	
var approveruserids="";
if($('input[name="users"]') != 'undefind'){
approveruserids = $('input[name="users"]').val();  
}

 document.systemRuleForm.action = "sysrule.do?method=saveSystemRule"+"&userids="+userids+"&approveruserids="+approveruserids;
 document.systemRuleForm.submit();

 

 
	}

function opensearchapprover(bn){
	//alert(boxnumber);
	var t = "user.do?method=userselector&boxnumber="+bn+"&idlistval="+"<%=systemform.getIdlistvaluser()%>";
	var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(t);
  
  window.open(t,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function deleteData(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	 if (doyou == true){
 document.systemRuleForm.action = "sysrule.do?method=deleteSystemRule&ruleid="+'<bean:write name="systemRuleForm" property="systemRuleId"/>';
 document.systemRuleForm.submit();
	 }
}
	
	

 function updatedata(){
	 var alertstr = "";
	var showalert=false;
		var userids ="";
	var rulname=document.systemRuleForm.ruleName.value.trim();
	var desc=document.systemRuleForm.ruleDesc.value.trim();
	
	if(rulname == "" || rulname == null){
     	alertstr = alertstr + "Rule Name is mandatory<br>";
		showalert = true;
		}

	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Rule_Desc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
   
   var theSel = window.document['systemRuleForm'].users;
 if(theSel != null && theSel != 'undefind'){
    for(i=theSel.length-1; i>=0; i--)
    {

	userids = userids + theSel.options[i].value + ",";
	}
  }

var approveruserids="";
if($('input[name="users"]') != 'undefind'){
approveruserids = $('input[name="users"]').val();  
}

document.systemRuleForm.action = "sysrule.do?method=updateSystemRule&ruleid="+'<bean:write name="systemRuleForm" property="systemRuleId"/>'+"&userids="+userids+"&approveruserids="+approveruserids;;
 document.systemRuleForm.submit();

 

 
	}

function init(){
//setTimeout ( "document.systemRuleForm.roleCode.focus(); ", 200 );
//document.systemRuleForm.roleCode.focus();
}
function opensearchUserselector(){
		var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=watchlistselector&boxnumber=systemrule");
  window.open(url, "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=650,height=600");
}


function opensearchVendorselector(){
		var url = "user.do?method=vendorlistselector&boxnumber=systemrule";
  window.open(url, "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=650,height=600");
}

function openvendorwindow(userid){

	window.open('user.do?method=editvendor&readPreview=2&userId='+userid, 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600');
}

function retrieveURL(url) {
	
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&orgId="+document.systemRuleForm.orgId.value.trim();
	
	
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


    function displaycheckbox(){
		 var ruleid=document.systemRuleForm.systemRuleId.value;	
		 var ruleTypeval = document.systemRuleForm.ruleType.value;
        var orgid=document.systemRuleForm.orgId.value;	
		  document.systemRuleForm.action ="sysrule.do?method=createPublishCheckBox&ruleId="+ruleid+"&orgid="+orgid+"&ruleTypeval="+ruleTypeval;
		  document.systemRuleForm.submit();

	}

  






  function displaycheckbox1234(){
		
	var ruleTypeval = document.systemRuleForm.ruleType.value;
      var ruleid=document.systemRuleForm.systemRuleId.value;	
	//if(ruleTypeval == "PUBLISH_REQUISTIONS" ){
		var url="sysrule.do?method=createPublishCheckBox&ruleId="+ruleid;	   
		if (window.XMLHttpRequest) { 
		    req = new XMLHttpRequest();    	// Non-IE browsers
	  	req.onreadystatechange = processStateChangecreatecheckbox;

		    try {

	  		req.open("GET", url, true);
				
		    } catch (e) {}
		    req.send(null);
		} else if (window.ActiveXObject) {
	     	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
	  	if (req) {
		    	req.onreadystatechange=processStateChangecreatecheckbox;
		        req.open("GET", url, true);
			    req.send();
				
	  	}
		}

		// document.getElementById("publish").style.visibility = "visible";
		// document.getElementById("ownerlis123t").style.visibility = "hidden";
	//}else{

		//document.getElementById("publish").style.visibility = "hidden";
	
	//}

}
function processStateChangecreatecheckbox() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
    		spanElements = splitTextIntoSpan(req.responseText);
		    replaceExistingWithNewHtmlcreatecheckbox(spanElements);
    	} 
    	
  	}
}


function replaceExistingWithNewHtmlcreatecheckbox(newTextElements){
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
	    	  if(document.getElementById(name)){
				  
		    	  if(name="publish")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}


function splitTextIntoSpan(textToSplit){
  	returnElements=textToSplit.split("</span>")
    
  	for(var i=returnElements.length-1;i>=0;--i){  
	    spanPos = returnElements[i].indexOf("<span");               
                
    	if(spanPos>0){
        	  subString=returnElements[i].substring(spanPos);
	          returnElements[i]=subString;
    	} 

  	}
  	return returnElements;

}


function removeUsersfromlist()
{
	var theSel = window.document['systemRuleForm'].users;
  var selIndex = theSel.selectedIndex;
  if (selIndex != -1) {
    for(i=theSel.length-1; i>=0; i--)
    {
      if(theSel.options[i].selected)
      {
        theSel.options[i] = null;
      }
    }
    if (theSel.length > 0) {
      theSel.selectedIndex = selIndex == 0 ? 0 : selIndex - 1;
    }
  }
}

function setUserValues(newText, newValue){
	 var theSel = window.document['systemRuleForm'].users;
	//alert(theSel+" "+theSel.length+" "+newText);
  if (theSel.length == 0) {
    var newOpt1 = new Option(newText, newValue);
    theSel.options[0] = newOpt1;
    theSel.selectedIndex = 0;
  } else if (theSel.selectedIndex != -1) {
    var selText = new Array();
    var selValues = new Array();
    var selIsSel = new Array();
    var newCount = -1;
    var newSelected = -1;
    var i;
    for(i=0; i<theSel.length; i++)
    {
      newCount++;
      selText[newCount] = theSel.options[i].text;
      selValues[newCount] = theSel.options[i].value;
      selIsSel[newCount] = theSel.options[i].selected;
      
      if (newCount == theSel.selectedIndex) {
        newCount++;
        selText[newCount] = newText;
        selValues[newCount] = newValue;
        selIsSel[newCount] = false;
        newSelected = newCount - 1;
      }
    }
    for(i=0; i<=newCount; i++)
    {
      var newOpt = new Option(selText[i], selValues[i]);
      theSel.options[i] = newOpt;
      theSel.options[i].selected = selIsSel[i];
    }
  }
}
</script>


<%

String systemruleexist = (String)request.getAttribute("systemruleexist");
String deleteSystemRule = (String)request.getAttribute("deleteSystemRule");
String saveSystemRule = (String)request.getAttribute("saveSystemRule");
String updateSystemRule = (String)request.getAttribute("updateSystemRule");
String PUBLISH_REQUISTIONS = (String)request.getAttribute("PUBLISH_REQUISTIONS");
if(PUBLISH_REQUISTIONS == null){
	PUBLISH_REQUISTIONS ="no";
}

%>

<html:form action="/sysrule.do?method=saveRole" >
<body onload="init()">
	<font color = "white" ><html:errors /> </font>
<% if(systemruleexist != null && systemruleexist.equals("yes")){%>
<div class="msg">
<font color="white"><%=Constant.getResourceStringValue("admin.systemrules.already.exist",user1.getLocale())%> </font> <!-- <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></div>
<%}%>
<% if(saveSystemRule != null && saveSystemRule.equals("yes")){%>
<div class="msg">
<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
<font color="white"><%=Constant.getResourceStringValue("admin.systemrules.savemessage",user1.getLocale())%> </font><!-- <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></div>
<%}%>
	<% if(updateSystemRule != null && updateSystemRule.equals("yes")){%>
<div class="msg">
<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
<font color="white"><%=Constant.getResourceStringValue("admin.systemrules.updatemessage",user1.getLocale())%></font> <!--<a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></div>
<%}%>
<% if(deleteSystemRule != null && deleteSystemRule.equals("yes")){%>
<div class="msg">
<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
<font color="white"><%=Constant.getResourceStringValue("admin.systemrules.deletemessage",user1.getLocale())%></font> <!--<a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></div>
	<br>
<%}else{%>
<div align="center" >
	<table border="0" width="100%">

	       <html:hidden property="systemRuleId"/>
		<tr>
			<td width="33%"><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Rule_Name",user1.getLocale())%><font color="red">*</font> </td>
			<td><html:text property="ruleName" size="43" maxlength="200" /></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Rule_Desc",user1.getLocale())%></td>
			<td><html:textarea property="ruleDesc" cols="40" rows="5" /></td>
		</tr>
		
		<tr>
          <td><%=Constant.getResourceStringValue("rule.type",user1.getLocale())%><font color="red">*</font></td>
			<td>
			<html:select property="ruleType" onchange="displaycheckbox();">
			<bean:define name="systemRuleForm" property="ruletypeList" id="ruletypeList" />

            <html:options collection="ruletypeList" property="key"  labelProperty="value"/>
			</html:select>

			</td>
		</tr>
				<tr>
					<td></td>
					<td>
					<span id="publish">
		
					</span>
					</td>
				</tr>
			
<tr>
<%if(systemform.getRuleType()!=null && systemform.getRuleType().equals("PUBLISH_REQUISTIONS") ){ %>


           <td><%=Constant.getResourceStringValue("Requisition.Publish",user1.getLocale())%></td>
		   <td>
		   <table> 
		   <tr>
		   <td>
			<%=Constant.getResourceStringValue("rule.publishToExternal",user1.getLocale())%>
	        <%
			if(systemform.getPublishToExternal()!=null && systemform.getPublishToExternal().equals("Y")){%>
	         <input type="checkbox" name="publishToExternal" id="publishToExternal" value="Y" checked="true"/>
			<%}else{%>
			 <input type="checkbox" name="publishToExternal" id="publishToExternal" value="Y" />
			<% }%>
			</td>
			</tr>
			<tr>
			<td>
		  	<%=Constant.getResourceStringValue("rule.publishToEmpRef",user1.getLocale())%>
			<%
		 	if(systemform.getPublishToEmpRef()!=null && systemform.getPublishToEmpRef().equals("Y")){%>
		 	<input type="checkbox" name="publishToEmpRef" id="publishToEmpRef"  value="Y" checked="true"/>
			<% }else{%>
		 	<input type="checkbox" name="publishToEmpRef" id="publishToEmpRef"  value="Y" />
			<% }%>
				</td>
			</tr>
			<tr>
				<td>
			<%=Constant.getResourceStringValue("hr.eeo.info.included",user1.getLocale())%>

			<%
			if(systemform.getEeoInfoIncluded()!=null && systemform.getEeoInfoIncluded().equals("Y")){%>
         	<input type="checkbox" name="eeoInfoIncluded" id="eeoInfoIncluded"  value="Y" checked="true"/>
			<% }else{%>
		 	<input type="checkbox" name="eeoInfoIncluded" id="eeoInfoIncluded"  value="Y" />
		 	
			<% }%>
			</td>
			</tr>
			</table>
			</td>

		<%}%>
</tr>

		
        <tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.orgname",user1.getLocale())%><font color="red">*</font></td>
			
			<td>
			
			<html:select property="orgId" onchange="retrieveURL('sysrule.do?method=loadDepartments');">
			<bean:define name="systemRuleForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%>	</span>
		
			</td>
			
		</tr>
        <tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.dept",user1.getLocale())%></td>
			<td>
			<span id="departments">
			<html:select property="deptId" onchange="setdepartmentvalue();">
			<option value="0"></option>
			<bean:define name="systemRuleForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>&nbsp;
	        </span>	
	
			</td>

		</tr>
	<tr>	
		
<% if(PUBLISH_REQUISTIONS != null && PUBLISH_REQUISTIONS.equals("yes")){%>

<%
String ownerlistvalue ="<span id=\"ownerlist\"></span>";
String ownerlisturl = "";
String ids ="";
String uname="";
List ownerlist = systemform.getRuleUsers();
%>
<td><%=Constant.getResourceStringValue("aquisition.applicant.Vendor",user1.getLocale())%></td>
<td>


<select id="elem529" name="users" size="10" multiple="multiple" ondblclick="openvendorwindow(this.options[this.selectedIndex].value)">
        
		<% 
			int ii=0;
			if(ownerlist != null){

			for(int i=0;i<ownerlist.size();i++){
				SystemRuleUser sysuser = (SystemRuleUser)ownerlist.get(i);
				User touser = sysuser.getUser();
				
				 String selectedtext = "";
				 ii++;
				 if(ii==ownerlist.size()){
				 selectedtext = "selected='selected'";
				 }
		%>
        <option value="<%=touser.getUserId()%>" <%=selectedtext%>><%=touser.getFirstName()%></option>
		<%}}%>
		</select>
			
			<span id="elem530">
		<a href="#" onClick="javascript:opensearchVendorselector();return false;"><img src="jsp/images/selector.gif" border="0"/></a>
		<a href="#" onClick="javascript:removeUsersfromlist();return false;"><img src="jsp/images/delete.gif" border="0"/></a>
		
        
			<input type="hidden" name="userids" value=''/>
				<input type="hidden" name="usernames" value=''/>
				
          </span>
         
</td>
</tr>
		

<%}else{%>

	<%
	List ownerlist = systemform.getRuleUsers();
	int totalsize = 0;
	if(ownerlist != null){
		totalsize= ownerlist.size();
	}
	%>
<tr>
 <td><p><%=Constant.getResourceStringValue("Requisition.ApproverName",user1.getLocale())%>
 <!--<a href="javascript:;" onclick="addElement2();"><img src="jsp/images/add.gif" border="0" alt="add approver" title="<%=Constant.getResourceStringValue("aquisition.applicant.Add_approvers",user1.getLocale())%>" height="20"  width="19"/></a></p>-->
 </td>
 </tr>
 <tr>
 	<td ></td>
 <td>

 <input type="hidden" value="<%=totalsize%>" id="theValue2" />
 <input type="hidden" name="idlistvaluser" value="<%=(systemform.getIdlistvaluser()==null)?"":systemform.getIdlistvaluser()%>"/>

<%
	String data="";
if(ownerlist != null){

for(int i=0;i<ownerlist.size();i++){
	 long idvalue = 0;
	 String idstr="";
	 String nameuserorgroup ="";
	 String userlink ="";
SystemRuleUser sysuser = (SystemRuleUser)ownerlist.get(i);

     if(sysuser != null && sysuser.getIsGroup().equals("Y")){
		 idvalue = sysuser.getUserGroup().getUsergrpId();
		 idstr = idvalue+"|"+"G";
		 nameuserorgroup = "<img src='jsp/images/User-Group-icon.png' />"+sysuser.getUserGroup().getUsergrpName();

       

	 }else{
         idvalue = sysuser.getUser().getUserId();
		 idstr = idvalue+"|"+"U";
		 nameuserorgroup = "<img src='jsp/images/user.gif' />"+sysuser.getUser().getFirstName()+ " "+sysuser.getUser().getLastName();
		
	 }

 data = data + "{ "+"\""+"id"+"\""+":"+"\""+idstr+"\","+"  "+"\""+"name"+"\""+":"+"\""+nameuserorgroup+"\""+"}"+",";

}

		if(!StringUtils.isNullOrEmpty(data)){
			data = data.substring(0,data.length()-1);
		}
}


%>


<input type="text" id="demo-input-pre-populated" name="users" />
		<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user1.getLocale())%></span>
        <script type="text/javascript">
        $(document).ready(function() {
            $("#demo-input-pre-populated").tokenInput("jsp/talent/getUserUserGroupJsonMulti.jsp", {
				 preventDuplicates: true,
			     hintText: "Type in a search user & groups",
                prePopulate: [
						<%=data%>
                ]
            });
        });
        </script>





          
</td>
</tr>
<%}%>
	<tr></tr><tr></tr><tr></tr>
	<% if(PUBLISH_REQUISTIONS != null && PUBLISH_REQUISTIONS.equals("yes")){%>
	<tr id="elem531">
	<%}else{%>	
	<!--  <tr id="elem533">-->
	<tr>
	<%}%>	
			<td colspan="2">
			<% if(systemform.getSystemRuleId()>0){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%> " onClick="updatedata()" class="button">
			<input type="button" name="delete" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%> " onClick="deleteData()" class="button">
            <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%> " onClick="discard()" class="button">
           <%}else if(updateSystemRule != null && updateSystemRule.equals("yes")){%>
            <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%> " onClick="updatedata()" class="button">
			<input type="button" name="delete" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%> " onClick="deleteData()" class="button">
            <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%> " onClick="discard()" class="button">
            <%}else{%>
             <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%> " onClick="savedata()" class="button">	
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%> " onClick="discard()" class="button">
			<%}%>
			</td>
			
			
		</tr>

		<%}%>

		
	</table>
</div>
</html:form>

<script language="javascript">
	function addElement2() {

  var ni = document.getElementById('satya');
  var numi = document.getElementById('theValue2');
  var num = (document.getElementById('theValue2').value -1)+ 2;
  if(isNaN(num))num=1;
  if(num < 50){
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my2'+num+'Div';
  newdiv.setAttribute('id',divIdName);

  var spanstart = '<span class="blue" id="approverspan_'+num+'">';
  var username1 = '<input type="hidden"  value="" name="username_'+num+'"/>';
  var userid1 = '<input type="hidden"    name="userid_'+num+'"/>';
   var levelid = '<input type="hidden"    name="level_'+num+'"/>';
   var isusergroup = '<input type="hidden"    name="isGroup_'+num+'"/>';
   var leveltext = 'Level-'+num+'&nbsp;&nbsp';
    var spanend = '</span>'; 
  newdiv.innerHTML = ''+spanstart+''+leveltext+''+levelid+''+username1+''+userid1+ ''+''+isusergroup+ ''+spanend+''+'<a href=\'#\' onclick=\'opensearchapprover('+num+')\'><img src=\"jsp/images/selector.gif\" border=\"0\"/></a>'+''+'  <a href=\'#\' onclick=\'removeElement2('+divIdName+','+num+')\'><img src=\"jsp/images/delete.gif\" border=\"0\"/></a>';
  ni.appendChild(newdiv);

  } else {
	  alert("maximum level exceed");
  }
}




function removeElement2(divNum,num) {
	
//var d = document.getElementById('myDiv2');
//d.removeChild(divNum);
//document.getElementById('theValue2').value=num-1;


var approvenum = 'userid_'+num;
var idapprove=document.systemRuleForm[approvenum].value;
var listidapprover=document.systemRuleForm.idlistvaluser.value;
var d = document.getElementById('myDiv2');
d.removeChild(divNum);
//document.getElementById('theValue2').value=num-1;
var array1=new Array();
var array1new=new Array();
//array1=listidapprover.split(/\.\d{2},?/);
array1=listidapprover;
for(var i=0;i<array1.length;i++){


if(idapprove!=array1[i]){
	
		
		
   array1new[i]=array1[i];
}

}
document.systemRuleForm.idlistvaluser.value=array1new;

}

</script>

