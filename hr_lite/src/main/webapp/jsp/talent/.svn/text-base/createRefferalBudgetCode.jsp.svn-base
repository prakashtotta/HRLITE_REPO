<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<html>
<bean:define id="refferalbudgetcodeform" name="refferalBudgetCodeForm" type="com.form.RefferalBudgetCodeForm" />

<style>
span1{color:#ff0000;}
</style>

<style type="text/css">
	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
/* Clear calendar's float, using dialog inbuilt form element */
    #container .bd form {
        clear:left;
    }

    /* Have calendar squeeze upto bd bounding box */
    #container .bd {
        padding:0;
    }

    #container .hd {
        text-align:left;
    }

    /* Center buttons in the footer */
    #container .ft .button-group {
        text-align:center;
    }

    /* Prevent border-collapse:collapse from bleeding through in IE6, IE7 */
    #container_c.yui-overlay-hidden table {
        *display:none;
    }

    /* Remove calendar's border and set padding in ems instead of px, so we can specify an width in ems for the container */
    #cal {
        border:none;
        padding:1em;
    }

    /* Datefield look/feel */
    .datefield {
        position:relative;
        top:10px;
        left:10px;
        white-space:nowrap;
        border:1px solid black;
        background-color:#eee;
        width:25em;
        padding:5px;
    }

    .datefield input,
    .datefield button,
    .datefield label  {
        vertical-align:middle;
    }

    .datefield label  {
        font-weight:bold;
    }

    .datefield input  {
        width:15em;
    }

    .datefield button  {
        padding:0 5px 0 5px;
        margin-left:2px;
    }

    .datefield button img {
        padding:0;
        margin:0;
        vertical-align:middle;
    }

    /* Example box */
    .box {
        position:relative;
        height:30em;
    }
	</style>

<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  parent.parent.GB_hide();
 
	   } 
	}
function closewindow(){
	  parent.parent.GB_hide();
}
function init(){
setTimeout ( "document.refferalBudgetCodeForm.ref_budgetCode.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}
function savedata(){
  var alertstr = "";
  var numbers=/^[0-9]+$/;
    var budgetCode = document.refferalBudgetCodeForm.ref_budgetCode.value.trim();
	var budgetCentername = document.refferalBudgetCodeForm.ref_budgetCentreName.value.trim();
	var desc = document.refferalBudgetCodeForm.ref_budgetCentreDesc.value.trim();
	var budgetamt = document.refferalBudgetCodeForm.ref_budgetamount.value.trim();
	 
	var showalert=false;

	if(budgetCode == "" || budgetCode == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.ref_Budgetcode_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(budgetCentername == "" || budgetCentername == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.ref_budget_Centername_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_required",user1.getLocale())%><BR>";
		showalert = true;
		}
		if(numbers.test(budgetamt)==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ref_BudgetAmt_required",user1.getLocale())%><br>";
		showalert = true;
		}

	//if(budgetamt == "" || budgetamt == null || budgetamt == 0){
     //	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ref_BudgetAmt_required",user1.getLocale())%><BR>";
	//	showalert = true;
	//	}

	 if(document.refferalBudgetCodeForm.ref_budgetCentreDesc.value.length> 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
 
	 
	  document.refferalBudgetCodeForm.action = "refferalBudgetcode.do?method=saveRefferalBudgetCode&readPreview=3";
 document.refferalBudgetCodeForm.submit();
 

 
	}

function updatedata(){

	 var alertstr = "";
	 var numbers=/^[0-9]+$/;
    var budgetCode = document.refferalBudgetCodeForm.ref_budgetCode.value.trim();
	var budgetCentername = document.refferalBudgetCodeForm.ref_budgetCentreName.value.trim();
	var desc = document.refferalBudgetCodeForm.ref_budgetCentreDesc.value.trim();
	var budgetamt = document.refferalBudgetCodeForm.ref_budgetamount.value.trim();
	 
	var showalert=false;

	if(budgetCode == "" || budgetCode == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.ref_Budgetcode_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(budgetCentername == "" || budgetCentername == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.ref_budget_Centername_required",user1.getLocale())%><BR>";
		showalert = true;
		}

	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_required",user1.getLocale())%><BR>";
		showalert = true;
		}

		if(numbers.test(budgetamt)==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ref_BudgetAmt_required",user1.getLocale())%><br>";
		showalert = true;
		}
	
	//if(budgetamt == "" || budgetamt == null || budgetamt == 0){
     //	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ref_BudgetAmt_required",user1.getLocale())%><BR>";
		//showalert = true;
		//}

	 if(document.refferalBudgetCodeForm.ref_budgetCentreDesc.value.length> 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }

	  document.refferalBudgetCodeForm.action = "refferalBudgetcode.do?method=updateRefferalBudgetCode&readPreview=1&id="+'<bean:write name="refferalBudgetCodeForm" property="ref_budgetId"/>';
 document.refferalBudgetCodeForm.submit();

 
	}
function deletedata(){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  document.refferalBudgetCodeForm.action = "refferalBudgetcode.do?method=DeleteRefferelBudgetCode&id="+'<bean:write name="refferalBudgetCodeForm" property="ref_budgetId"/>';

		  document.refferalBudgetCodeForm.submit();
	  }
	
}
function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&orgId="+document.refferalBudgetCodeForm.orgId.value.trim();
	
	
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

	setTimeout("getCurrencyCode()",300);

	

}
	 

function getCurrencyCode(){

	var urlnew = "org.do?method=getcurrencycodebyorg&orgId="+document.refferalBudgetCodeForm.orgId.value.trim();

	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNew;

	    try {
    		req.open("GET", urlnew, true);
						
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNew;
	        req.open("GET", urlnew, true);
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

function processStateChangeNew() {
	
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
			
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlNew(spanElements);
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

function replaceExistingWithNewHtmlNew(newTextElements){
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
				  
		    	  if(name="currencycode")	document.getElementById(name).innerHTML = content;
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
//String saveRefferalBudgetCode = (String)request.getAttribute("saveRefferalBudgetCode");
//System.out.println("saveRefferalBudgetCode"+saveRefferalBudgetCode);
%>
<%
String saveReffBudgetCode = (String)request.getAttribute("saveReffBudgetCode");
	
if(saveReffBudgetCode != null && saveReffBudgetCode.equals("yes")){
%>
<div class="msg">
<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.savemsg",user1.getLocale())%> </font>
			<!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </font></a> --></div>

<%}%>
<%
String updateReffBudgetCode = (String)request.getAttribute("updateReffBudgetCode");
	
if(updateReffBudgetCode != null && updateReffBudgetCode.equals("yes")){
%>

	<div class="msg">
	<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.update",user1.getLocale())%></font>
			<!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </font></a> --></div>



<%}%>
<%
String deleteReffBudgetCode = (String)request.getAttribute("deleteReffBudgetCode");
	
if(deleteReffBudgetCode != null && deleteReffBudgetCode.equals("yes")){
%>

	<div class="msg">
	<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.delete",user1.getLocale())%></font>
		<!--<a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></div>

	<%}else{%>
<br>
<body class="yui-skin-sam" onload="init()" >

<html:form action="/refferalBudgetcode.do?method=saveRefferalBudgetCode" method="POST">

<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

<%

if ((refferalbudgetcodeform.getReadPreview()).equals("2")){

%>
	
	<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.create",user1.getLocale())%></td>
			<td></td>
		</tr>	
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.rbcoe",user1.getLocale())%></td>
			<td><html:text property="ref_budgetCode" maxlength="200" readonly="true"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.name",user1.getLocale())%></td>
			<td><html:text property="ref_budgetCentreName" maxlength="500" readonly="true"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.desc",user1.getLocale())%></td>
			<td><html:textarea property="ref_budgetCentreDesc" readonly="true" cols="50" rows="5"/></td>
		</tr>
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.year",user1.getLocale())%></td>
			<td>
			<html:select  property="ref_budgetYear" >
			<bean:define name="refferalBudgetCodeForm" property="yearsList" id="yearsList" />

            <html:options collection="yearsList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>

				<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			
			<td>
			
			<html:select property="orgId" >
			<bean:define name="refferalBudgetCodeForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
			<%=Constant.getResourceStringValue("admin.Deparment.loadingdept.only",user1.getLocale())%>......</span>
			
			</td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			
			<td>
			<span id="departments">
			<html:select property="departmentId" >
			<bean:define name="refferalBudgetCodeForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</span>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.amt",user1.getLocale())%></td>
			<td><html:text property="ref_budgetamount" readonly="true"/></td>
		</tr>
<%}else{%>


<tr>
			<td></td>
			<td></td>
		</tr>	
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.rbcoe",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text   property="ref_budgetCode" size="57" maxlength="200"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="ref_budgetCentreName" size="57" maxlength="500"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.desc",user1.getLocale())%><font color="red">*</font></td>
			<td><html:textarea property="ref_budgetCentreDesc" cols="60" rows="5"/></td>
		</tr>
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.year",user1.getLocale())%></td>
			<td>
			<html:select  property="ref_budgetYear">
			<bean:define name="refferalBudgetCodeForm" property="yearsList" id="yearsList" />

            <html:options collection="yearsList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			
			<td>
			
			<html:select property="orgId" onchange="retrieveURL('user.do?method=loadDepartments');">
			<bean:define name="refferalBudgetCodeForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept.only",user1.getLocale())%>......</span>
			
			</td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
			
			<td>
			<span id="departments">
			<html:select property="departmentId" >
			<option value=""></option>
			<bean:define name="refferalBudgetCodeForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</span>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.amt",user1.getLocale())%></td>
			<td><html:text property="ref_budgetamount" /> 
			<span id="currencycode"><%=refferalbudgetcodeform.getRef_budgetCurrency()%>	
			<html:hidden property="ref_budgetCurrency" value="<%=refferalbudgetcodeform.getRef_budgetCurrency()%>"/>
			</span>
			
</td>
		</tr>
	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>

		<tr>
			<td colspan="2"><% if ((refferalbudgetcodeform.getReadPreview()).equals("1") || saveReffBudgetCode != null && saveReffBudgetCode.equals("yes")){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<%}%>
			<td></td>
		</tr>
<%}%>
		</table>
		

		
</div>
</html:form>
</body>
		<%} %>
