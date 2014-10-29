<%@ include file="../common/include.jsp" %>
<%@ include file="../common/tooltip.jsp" %>


<bean:define id="refferalSchemeform" name="refferalSchemeForm" type="com.form.RefferalSchemeForm" />
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
 <html>
  <HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>

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
			legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
</STYLE>

 </HEAD>
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>



<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
	   parent.parent.GB_hide();
	   } 
	}

	function closewindow(){
	  parent.parent.GB_hide();
	 // window.top.hidePopWin();
}
function init(){
setTimeout ( "document.refferalSchemeForm.refferalScheme_Name.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}
function savedata(){
	var alertstr = "";
	var showalert=false;
	var name = document.refferalSchemeForm.refferalScheme_Name.value.trim();	
	var Desc = document.refferalSchemeForm.refferalScheme_Desc.value.trim();
	var amt=document.refferalSchemeForm.refferalScheme_Amount.value.trim();
	var Reffbc = document.refferalSchemeForm.ref_budgetId.value.trim();
	var creditAfterdays = document.refferalSchemeForm.creditAfterdays.value.trim();

		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		if(Desc == "" || Desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_required",user1.getLocale())%><br>";
		showalert = true;
		}
		if(Reffbc == "" || Reffbc == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.RefferalBudgetCode.Code_mandatory",user1.getLocale())%><br>";
			showalert = true;
		}
		if(amt == "" || amt == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Amount_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		if(isNaN(amt)){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.amountnumber.required",user1.getLocale())%><br>";
			showalert = true;
		}
		 

		if(isNaN(creditAfterdays)){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.amountnumber.creditafterdays.required",user1.getLocale())%><br>";
			showalert = true;
		}

		if(document.refferalSchemeForm.refferalScheme_Desc.value.length> 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ref_scheme_exceeded_800_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.refferalSchemeForm.action = "refferalscheme.do?method=saveRefferalScheme&readPreview=3";
 document.refferalSchemeForm.submit();
 

 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;
	var name = document.refferalSchemeForm.refferalScheme_Name.value.trim();	
	var Desc = document.refferalSchemeForm.refferalScheme_Desc.value.trim();
	var amt=document.refferalSchemeForm.refferalScheme_Amount.value.trim();
	var Reffbc = document.refferalSchemeForm.ref_budgetId.value.trim();
	var creditAfterdays = document.refferalSchemeForm.creditAfterdays.value.trim();
	
	
		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		if(Desc == "" || Desc == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_required",user1.getLocale())%><br>";
			showalert = true;
			}
		if(Reffbc == "" || Reffbc == null){
		    alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.RefferalBudgetCode.Code_mandatory",user1.getLocale())%><br>";
			showalert = true;
		}
		if(amt == "" || amt == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Amount_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		if(isNaN(amt)){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.amountnumber.required",user1.getLocale())%><br>";
			showalert = true;
		}
		if(isNaN(creditAfterdays)){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.amountnumber.creditafterdays.required",user1.getLocale())%><br>";
			showalert = true;
		}

		if(document.refferalSchemeForm.refferalScheme_Desc.value.length> 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ref_scheme_exceeded_800_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	if (showalert){
     	alert(alertstr);
        return false;
          }
document.refferalSchemeForm.action = "refferalscheme.do?method=updateRefferalScheme&readPreview=1&id="+'<bean:write name="refferalSchemeForm" property="refferalScheme_Id"/>';
 document.refferalSchemeForm.submit();

 
	}
function deletedata(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	document.refferalSchemeForm.action = "refferalscheme.do?method=deleteRefferalScheme&id="+'<bean:write name="refferalSchemeForm" property="refferalScheme_Id"/>';
    document.refferalSchemeForm.submit();
	  }
	
}
	
function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&rerreralbudgetcodeid="+document.refferalSchemeForm.ref_budgetId.value.trim()+"&uomscheme="+document.refferalSchemeForm.uomscheme.value.trim();
	
	
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

	//setTimeout("getCurrencyCode()",300);

	

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
//String saveRefferalScheme = (String)request.getAttribute("saveRefferalScheme");
%>
<%
String saveReffScheme = (String)request.getAttribute("saveReffScheme");
	
if(saveReffScheme != null && saveReffScheme.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td align="left">
			<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
			<font color="white"><%=Constant.getResourceStringValue("admin.RefferalScheme.savemsg",user1.getLocale())%> </font>
			<!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updateReffScheme = (String)request.getAttribute("updateReffScheme");
	
if(updateReffScheme != null && updateReffScheme.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td align="left">
			<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
			<font color="white"><%=Constant.getResourceStringValue("admin.RefferalScheme.updatemsg",user1.getLocale())%></font>
			<!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String deleteReffScheme = (String)request.getAttribute("deleteReffScheme");
	
if(deleteReffScheme != null && deleteReffScheme.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td align="left">
			<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
			<font color="white"><%=Constant.getResourceStringValue("admin.RefferalScheme.deletemsg",user1.getLocale())%></font>
			<!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </a> --></td>
		</tr>
		
	</table>
</div>
	<%}else{%>
<body onload="init()">

<html:form action="/refferalscheme.do?method=saveRefferalScheme">

<div align="center" class="div">
<%

if ((refferalSchemeform.getReadPreview()).equals("2")){

%>
     <br>
	 <fieldset><legend><%=Constant.getResourceStringValue("admin.RefferalScheme.details",user1.getLocale())%></legend>
	 <table border="0" width="100%">
	<tr>
			<td></td>
			<td></td>
		</tr>
	<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalScheme.name",user1.getLocale())%></td>
			<td><%=(refferalSchemeform.getRefferalScheme_Name()==null)?"":refferalSchemeform.getRefferalScheme_Name()%></td>
		</tr>
	<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalScheme.type",user1.getLocale())%></td>
			<td>
			<%if(refferalSchemeform.getSchemeType()!= null && refferalSchemeform.getSchemeType().equals("E")){%>
			<%=Constant.getResourceStringValue("admin.RefferalScheme.type.employee.referral",user1.getLocale())%>
			<%}else{%>
			<%=Constant.getResourceStringValue("admin.RefferalScheme.type.agency.referral",user1.getLocale())%>
			<%}%>
			</td>
		</tr>
		

		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalScheme.desc",user1.getLocale())%></td>
			<td><%=(refferalSchemeform.getRefferalScheme_Desc()==null)?"":refferalSchemeform.getRefferalScheme_Desc()%></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.rbcoe",user1.getLocale())%></td>
			<td><%=(refferalSchemeform.getRefferalbudgetcode()==null)?"":refferalSchemeform.getRefferalbudgetcode().getRef_budgetCode()%></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalSchemeType.reward.type",user1.getLocale())%></td>
			<td><%=(refferalSchemeform.getRefferalSchemeType()==null)?"":refferalSchemeform.getRefferalSchemeType().getRefferalSchemeName()%></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferalScheme.bonus",user1.getLocale())%></td>
			<td><%=refferalSchemeform.getRefferalScheme_Amount()%>&nbsp;&nbsp;<%=(refferalSchemeform.getUom()==null)?"":refferalSchemeform.getUom()%></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Execution.criteria",user1.getLocale())%></td>
			<td><%=(refferalSchemeform.getRule()==null)?"":refferalSchemeform.getRule().getRuleDesc()%></td>
		</tr>
</table>
		</fieldset>
 
<%}else{%>

	
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>



	
		<tr>
			<td><a href="#"  onMouseover="ddrivetip('<%=Constant.getResourceStringValue("admin.RefferalScheme.name.help",user1.getLocale())%>','#DFDFFF', 300)";
onMouseout="hideddrivetip()"><%=Constant.getResourceStringValue("admin.RefferalScheme.name",user1.getLocale())%></a><font color="red">*</font></td>
			<td><html:text property="refferalScheme_Name" maxlength="300" size="60"/></td>
		</tr>
		
		<tr>
			<td><a href="#"  onMouseover="ddrivetip('<%=Constant.getResourceStringValue("admin.RefferalScheme.type.help",user1.getLocale())%>','#DFDFFF', 300)";
onMouseout="hideddrivetip()"><%=Constant.getResourceStringValue("admin.RefferalScheme.type",user1.getLocale())%></a><font color="red">*</font></td>
			<td><%=Constant.getResourceStringValue("admin.RefferalScheme.type.employee.referral",user1.getLocale())%><input type="radio" name="schemeType" maxlength="10" id="1" <%=(refferalSchemeform.getSchemeType()!= null && refferalSchemeform.getSchemeType().equals("E"))? "Checked=true" : "" %> value="E">&nbsp;&nbsp;<%=Constant.getResourceStringValue("admin.RefferalScheme.type.agency.referral",user1.getLocale())%><input type="radio" name="schemeType" id="0" <%=(refferalSchemeform.getSchemeType()!= null && refferalSchemeform.getSchemeType().equals("V"))? "Checked=true" : "" %> value="V"></td>
		</tr>

		<tr>
			<td><a href="#"  onMouseover="ddrivetip('<%=Constant.getResourceStringValue("admin.RefferalScheme.desc.help",user1.getLocale())%>','#DFDFFF', 300)";
onMouseout="hideddrivetip()"><%=Constant.getResourceStringValue("admin.RefferalScheme.desc",user1.getLocale())%></a><font color="red">*</font></td>
			<td><html:textarea property="refferalScheme_Desc" cols="60" rows="5"/></td>
		</tr>

		<tr>
			<td><a href="#"  onMouseover="ddrivetip('<%=Constant.getResourceStringValue("admin.RefferalSchemeType.reward.type.help",user1.getLocale())%>','#DFDFFF', 300)";
onMouseout="hideddrivetip()"><%=Constant.getResourceStringValue("admin.RefferalSchemeType.reward.type",user1.getLocale())%></a><font color="red">*</font></td>
			<td>
			<html:select property="uomscheme">
			<bean:define name="refferalSchemeForm" property="uomList" id="uomList" />

            <html:options collection="uomList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
		</tr>

       <tr>
			<td><a href="#"  onMouseover="ddrivetip('<%=Constant.getResourceStringValue("admin.RefferalBudgetCode.rbcoe.help",user1.getLocale())%>','#DFDFFF', 300)";
onMouseout="hideddrivetip()"><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.rbcoe",user1.getLocale())%></a><font color="red">*</font></td>
			<td>
			
			<html:select property="ref_budgetId" onchange="retrieveURL('refferalscheme.do?method=loadUom');">
			<option value=""></option>
			<bean:define name="refferalSchemeForm" property="refferalBudgetCodeList" id="refferalBudgetCodeList" />

            <html:options collection="refferalBudgetCodeList" property="ref_budgetId"  labelProperty="ref_budgetCode"/>
			</html:select>
			
			
			</td>
		</tr>




      <tr>
			<td><a href="#"  onMouseover="ddrivetip('<%=Constant.getResourceStringValue("admin.RefferalScheme.bonus.help",user1.getLocale())%>','#DFDFFF', 300)";
onMouseout="hideddrivetip()"><%=Constant.getResourceStringValue("admin.RefferalScheme.bonus",user1.getLocale())%></a></td>
			<td><html:text property="refferalScheme_Amount" maxlength="500"/>
			<span id="currencycode"><%=(refferalSchemeform.getUom()==null)?"":refferalSchemeform.getUom()%>
			<html:hidden property="uom" value="<%=refferalSchemeform.getUom()%>"/>
			</span>
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						Loading ......</span>
			<!--<span class="textboxlabel"  STYLE="font-size: smaller;">
						(UOM will be taken implicitly from budget code AND Scheme Type)</span>-->
			
			</td>
		</tr>
 </table>
 
<fieldset><legend><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Execution.criteria",user1.getLocale())%></legend>
<table align="left">

		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Credit_After_Days",user1.getLocale())%><font color="red">*</font>
		<html:text property="creditAfterdays" maxlength="800" size="5"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Constant.getResourceStringValue("admin.RefferelRedumptionRule.Credit_After_Days.days",user1.getLocale())%>&nbsp;<%=Constant.getResourceStringValue("admin.RefferalScheme.type.off",user1.getLocale())%>
		&nbsp;
		<html:select property="criteria" >

			<bean:define name="refferalSchemeForm" property="criteriaList" id="criteriaList" />
            <html:options collection="criteriaList"  property="key"  labelProperty="value"/>
			</html:select>
		
		</td>
		</tr>

</table>
</fieldset>


<table border="0" width="100%">
		<%
      String updatebutton = (String)request.getAttribute("updatebutton");
    
       %>
		
		<tr>
			<td><% if (refferalSchemeform.getRefferalScheme_Id()>0){%>
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
            <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
	<%}%>

</table>

</div>


</html:form>
</body>
<%}%>
