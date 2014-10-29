<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ include file="../common/lightbox.jsp" %>
<%@ include file="../common/include.jsp" %>


  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<<< add User Salary Component >>");
%>

<bean:define id="cform" name="userSalaryForm" type="com.form.employee.UserSalaryForm" />


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
	
	var salarycomponent=document.userSalaryForm.salaryComonents.value.trim();
	var amount=document.userSalaryForm.amount.value.trim();
	var space=" ";
	var numbers=/^[0-9]+$/;
    if(salarycomponent == "" || salarycomponent == null ){
     	
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Salary_Component",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
     	showalert = true;
		}
    if(amount == "" || amount == null){
     	
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Amount",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
     	showalert = true;
		}else{
		    if(numbers.test(amount)==false){
		     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.amount_in_numbers",user1.getLocale())%><br>";
				showalert = true;
				}
		}


	if(document.userSalaryForm.isBankAcountDetails.checked){
		accountType="Y";
		var accNo=document.userSalaryForm.bankAcountNumber.value;
		var bankName=document.userSalaryForm.bankName.value;
		var bankAddress=document.userSalaryForm.bankAddress.value;
		var accType=document.userSalaryForm.accountType.value;

		var rountingNo=document.userSalaryForm.bankRoutingNumber.value;
		var ddamount=document.userSalaryForm.ddamount.value;
	
	    if(accNo == "" || accNo == null ){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Account_Number",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
	     	showalert = true;
		}
	    if(bankName == "" || bankName == null ){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Bank_name",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
	     	showalert = true;
		}
	    if(bankAddress == "" || bankAddress == null ){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Bank_address",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
	     	showalert = true;
		}
	    if(accType == "" || accType == null ){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Account_type",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
	     	showalert = true;
		}
	    if(rountingNo == "" || rountingNo == null ){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Routing_number",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
	     	showalert = true;
		}
	    if(ddamount == "" || ddamount == null ){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Amount",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
	     	showalert = true;
		}else{
		    if(numbers.test(ddamount)==false){
		     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.amount_in_numbers",user1.getLocale())%><br>";
				showalert = true;
				}
		}
		
		
	}else{
		accountType="N";
	}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userSalaryForm.action = "usersalary.do?method=updateUserSalaryComponent&isdirectdeposite="+accountType;
	  document.userSalaryForm.submit();



}


function savedata(){
	var alertstr="";
	var showalert=false;
	
	var salarycomponent=document.userSalaryForm.salaryComonents.value.trim();
	var amount=document.userSalaryForm.amount.value.trim();
	var space=" ";
	var numbers=/^[0-9]+$/;
    if(salarycomponent == "" || salarycomponent == null ){
     	
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Salary_Component",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
     	showalert = true;
		}

    if(amount == "" || amount == null){
     	
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Amount",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
     	showalert = true;
	}else{
	    if(numbers.test(amount)==false){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.amount_in_numbers",user1.getLocale())%><br>";
			showalert = true;
			}
	}


	if(document.userSalaryForm.isBankAcountDetails.checked){
		accountType="Y";
		var accNo=document.userSalaryForm.bankAcountNumber.value;
		var bankName=document.userSalaryForm.bankName.value;
		var bankAddress=document.userSalaryForm.bankAddress.value;
		var accType=document.userSalaryForm.accountType.value;

		var rountingNo=document.userSalaryForm.bankRoutingNumber.value;
		var ddamount=document.userSalaryForm.ddamount.value;
	
	    if(accNo == "" || accNo == null ){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Account_Number",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
	     	showalert = true;
		}
	    if(bankName == "" || bankName == null ){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Bank_name",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
	     	showalert = true;
		}
	    if(bankAddress == "" || bankAddress == null ){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Bank_address",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
	     	showalert = true;
		}
	    if(accType == "" || accType == null ){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Account_type",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
	     	showalert = true;
		}
	    if(rountingNo == "" || rountingNo == null ){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Routing_number",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
	     	showalert = true;
		}
	    if(ddamount == "" || ddamount == null ){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.Amount",user1.getLocale())%>"+space+"<%=Constant.getResourceStringValue("hr.user.salary.isMandatory",user1.getLocale())%> <BR>";
	     	showalert = true;
		}else{
		    if(numbers.test(ddamount)==false){
		     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.salary.amount_in_numbers",user1.getLocale())%><br>";
				showalert = true;
				}
		}
		
		
	}else{
		accountType="N";
	}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.userSalaryForm.action = "usersalary.do?method=saveUserSalaryComponent&isdirectdeposite="+accountType;
	  document.userSalaryForm.submit();


}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
 	if (doyou == true){
		document.userSalaryForm.action = "usersalary.do?method=deleteUserSalaryComponent";
		document.userSalaryForm.submit();
 	}
	
}
function isBankDetails(){
	if(document.userSalaryForm.isBankAcountDetails.checked){
	
		var url="<%=request.getContextPath()%>/usersalary.do?method=showBankDetails&showbankdetails=YES";
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
		
	}else{
		
		var url="<%=request.getContextPath()%>/usersalary.do?method=showBankDetails&showbankdetails=NO";
		
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
    	//document.getElementById("loading").style.visibility = "hidden";	
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
				  
		    	  if(name="isDirectDepositeData")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
  	
}
function showOthertextbox(){
	var accType=document.userSalaryForm.accountType.value;
	if(accType == 'Other'){
		document.getElementById('accounttypeother').style.display = 'block';
		var url="<%=request.getContextPath()%>/usersalary.do?method=showOtherAccountTypeTextbox&showtextbox=YES";
	  	//Do the AJAX call
	  	if (window.XMLHttpRequest) { 
		    req = new XMLHttpRequest();    	// Non-IE browsers
	    	req.onreadystatechange = processStateChangeTextbox;

		    try {
	    		req.open("GET", url, true);
				
		    } catch (e) {}
		    req.send(null);
	  	} else if (window.ActiveXObject) {
	       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
	    	if (req) {
		    	req.onreadystatechange=processStateChangeTextbox;
		        req.open("GET", url, true);
			    req.send();
				
	    	}
	  	}
		//alert(accType);
	}else{
		var url="<%=request.getContextPath()%>/usersalary.do?method=showOtherAccountTypeTextbox&showtextbox=NO";
	  	//Do the AJAX call
	  	if (window.XMLHttpRequest) { 
		    req = new XMLHttpRequest();    	// Non-IE browsers
	    	req.onreadystatechange = processStateChangeTextbox;

		    try {
	    		req.open("GET", url, true);
				
		    } catch (e) {}
		    req.send(null);
	  	} else if (window.ActiveXObject) {
	       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
	    	if (req) {
		    	req.onreadystatechange=processStateChangeTextbox;
		        req.open("GET", url, true);
			    req.send();
				
	    	}
	  	}
	}
}

function processStateChangeTextbox() {

	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlTextbox(spanElements);
		    //onOtherStateSel();
    	} 
    	//document.getElementById("loading").style.visibility = "hidden";	
  	}
	
}

function replaceExistingWithNewHtmlTextbox(newTextElements){
	
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
				  
		    	  if(name="accounttypeother")document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
  	
}
function init(){
	var accType=document.userSalaryForm.accountType.value;
	if(accType == "Other" && accType!= null){
		//showOthertextbox();
	}else{
		document.getElementById('accounttypeother').style.display = 'none';
	}
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
String usersalarycomponentupdated = (String)request.getAttribute("usersalarycomponentupdated");
	
if(usersalarycomponentupdated != null && usersalarycomponentupdated.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.salary.SalaryComponent.updated",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<%
String usersalarycomponentsaved = (String)request.getAttribute("usersalarycomponentsaved");
	
if(usersalarycomponentsaved != null && usersalarycomponentsaved.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.salary.SalaryComponent.saved",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>

<%
String usersalarycomponentdeleted = (String)request.getAttribute("usersalarycomponentdeleted");
	
if(usersalarycomponentdeleted != null && usersalarycomponentdeleted.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.salary.SalaryComponent.deleted",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}else{
%>
<body onload="init();">
<html:form action="/usersalary.do?method=saveUserSalaryComponent" enctype="multipart/form-data">
<table border="0" width="100%">

		<tr>
			<td width="30%"><%=Constant.getResourceStringValue("hr.user.salary.Pay_Grade",user1.getLocale())%></td>
			<td>	
				<html:select  property="salaryplanId">
				<option value=""></option>		
				<bean:define name="userSalaryForm" property="salaryplanList" id="salaryplanList" />
	            <html:options collection="salaryplanList" property="salaryplanId"  labelProperty="salaryPlanName"/>
				</html:select>
				
				<html:hidden property="userId" />
				<html:hidden property="userSalaryId" />
			</td>
		
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.salary.Salary_Component",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="salaryComonents" size="35" maxlength="200"/></td>
			
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.salary.Pay_Frequency",user1.getLocale())%></td>
			<td>
				<html:select property="compfrequencyId">
				<option value=""></option>	
				<bean:define name="userSalaryForm" property="compfrequencyList" id="compfrequencyList" />
	            <html:options collection="compfrequencyList" property="compFrequencyId"  labelProperty="compFrequencyName"/>
				</html:select>
			</td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.salary.Amount",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="amount" size="35" maxlength="200"/></td>
		</tr>
		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>

		

</table>
<span id="isDirectDepositeData">
<table border="0" width="100%">
		<tr>
		<td>
		<% if(cform.getIsBankAcountDetails() != null && cform.getIsBankAcountDetails().equals("Y")){%>
		<input type="checkbox" id="isBankAcountDetails" onClick="isBankDetails()" name="isBankAcountDetails" Value="Y" checked>
		<%}else{ %>
		<input type="checkbox" id="isBankAcountDetails" onClick="isBankDetails()"  name="isBankAcountDetails" Value="N">
		<%} %>
		
		<%=Constant.getResourceStringValue("hr.user.salary.Add_direct_deposite_details",user1.getLocale())%></td>
			
		</tr>
		</table>
		<br><br>

	<% if(cform.getIsBankAcountDetails() != null && cform.getIsBankAcountDetails().equals("Y")){%>

		<table border="0" width="100%">
		<tr>
		<td><%=Constant.getResourceStringValue("hr.user.salary.Account_Number",user1.getLocale())%><font color="red">*</font></td>

			<td><html:text property="bankAcountNumber" size="35" maxlength="200"/></td>
		</tr>
		<tr>
		<td><%=Constant.getResourceStringValue("hr.user.salary.Bank_name",user1.getLocale())%><font color="red">*</font></td>

			<td><html:text property="bankName" size="35" maxlength="200"/></td>
		</tr>
		<tr>
		<td><%=Constant.getResourceStringValue("hr.user.salary.Bank_address",user1.getLocale())%><font color="red">*</font></td>

			<td><html:text property="bankAddress" size="35" maxlength="200"/></td>
		</tr>

		<tr>
		<td><%=Constant.getResourceStringValue("hr.user.salary.Routing_number",user1.getLocale())%><font color="red">*</font></td>
	
			<td><html:text property="bankRoutingNumber" size="35" maxlength="200"/></td>
		</tr>
		<tr>
		<td><%=Constant.getResourceStringValue("hr.user.salary.Amount",user1.getLocale())%><font color="red">*</font></td>
		
			<td><html:text property="ddamount" size="35" maxlength="200"/></td>
		</tr>
	
				<tr>
			<td><%=Constant.getResourceStringValue("hr.user.salary.Account_type",user1.getLocale())%><font color="red">*</font></td>

			<td>
			<html:select  property="accountType" onchange="showOthertextbox();">
			<bean:define name="userSalaryForm" property="accountTypeList" id="accountTypeList" />
            <html:options collection="accountTypeList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
			
			
		</tr>
		<tr>
		<td colspan="2">
		<span id="accounttypeother" ><%=Constant.getResourceStringValue("hr.user.salary.please_specify",user1.getLocale())%><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<html:text property="accountTypeOther" size="35" maxlength="200"/></span>
		</td>
		
		</tr>
			</table>
		<%} %>
</span>
	
<br>
		<table border="0" width="100%">
		<tr>
			<td>
			<%
			if(cform.getUserSalaryId() != 0){
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
