<%@ include file="../common/include.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>


<bean:define id="cform" name="userReportToForm" type="com.form.employee.UserReportToForm" />

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
System.out.println(" <<<< edit report to details >>");
%>



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
function savedata(){
	var alertstr="";
	var showalert=false;
	var repottoname=document.userReportToForm.reportToUserName.value.trim();

    if(repottoname == "" || repottoname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.reportto.Name.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
	if (showalert){
     	alert(alertstr);
        return false;
          }
	document.userReportToForm.action="userreportto.do?method=saveUserReportTodata";
	document.userReportToForm.submit();
	
}
function updatedata(){
	var alertstr="";
	var showalert=false;
	var repottoname=document.userReportToForm.reportToUserName.value.trim();

    if(repottoname == "" || repottoname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.user.reportto.Name.required",user1.getLocale())%> <BR>";
     	showalert = true;
		}
	if (showalert){
     	alert(alertstr);
        return false;
          }
	document.userReportToForm.action="userreportto.do?method=updateUserReportTodata";
	document.userReportToForm.submit();

	
}
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){

	   parent.parent.GB_hide(); 
	   } 
	}
function closewindow2(){
	parent.parent.GB_hide();

	 
}
function showHideOtherMethod(){

	var reportToMethod=document.userReportToForm.reportToMethod.value;
	if(reportToMethod == "Other"){

		document.getElementById('reportToMethodOther').style.display = 'block';
		var url="<%=request.getContextPath()%>/userreportto.do?method=showOtherMethodReportTo&showtextbox=YES";
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
		//alert(relationship);
	}else{
		document.getElementById('reportToMethodOther').style.display = 'none';
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
				  
		    	  if(name="reportToMethodOther")document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
  	
}
function validateUser(){
	

	//if(document.jobRequisitionTemplateForm.recruiterNamehidden.value == "" || document.jobRequisitionTemplateForm.recruiterNamehidden.value == null){
		//document.userReportToForm.reportToUserName.value = document.userReportToForm.reportToUserName.value;
	//}else{
		document.userReportToForm.reportToUserName.value =document.userReportToForm.reportToUserNamehidden.value; 
		
}
function init(){
	var reportToMethod=document.userReportToForm.reportToMethod.value;
	if(reportToMethod == "Other"){
		//document.getElementById('reportToMethodOther').style.display = 'block';
	}else{
		document.getElementById('reportToMethodOther').style.display = 'none';
	}


}
</script>
<script type="text/javascript">
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
String.prototype.ltrim = function() {
	return this.replace(/^\s+/,"");
}
String.prototype.rtrim = function() {
	return this.replace(/\s+$/,"");
}
function strStartsWith(str, prefix) { 
	return str.indexOf(prefix) === 0;
	}	
$(function() {

	$("#reportToUserName").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
			minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
		     
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		  
		document.userReportToForm.reportToUserId.value=item.data;

		document.userReportToForm.reportToUserNamehidden.value = item.value;

		}
		});
});

</script>
<%
String userreporttodatasaved = (String)request.getAttribute("userreporttodatasaved");
	
if(userreporttodatasaved != null && userreporttodatasaved.equals("yes")){
%>
<div align="left" class="msg">
	<table border="0" width="70%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.jobdetails.saved",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2();return false"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%
}
%>
<body onload="init();">

 <html:form action="/userreportto.do?method=logon">      
 
            	<table border="0" width="100%" cellspacing="8" cellpadding="4">
             <tr>
	            <td></td>
	            <td>
		            <input type="radio" name="reportToType" id="1"  <%=(cform.getReportToType()!= null && cform.getReportToType().equals("Supervisor"))? "Checked=true" : "" %> value="Supervisor">&nbsp;<%=Constant.getResourceStringValue("hr.user.reportto.Supervisor",user1.getLocale())%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="reportToType" id="0"  <%=(cform.getReportToType()!= null &&cform.getReportToType().equals("Subordinate"))? "Checked=true" : "" %> value="Subordinate">&nbsp;<%=Constant.getResourceStringValue("hr.user.reportto.Subordinate",user1.getLocale())%>
	           		<html:hidden property="userId" />
					<html:hidden property="reportToId" />
	            </td>
             </tr>
             <tr>
            	<td><%=Constant.getResourceStringValue("hr.user.reportto.Name",user1.getLocale())%><font color="red">*</font></td>
            	
            	<td>
            	<input type="hidden" name="reportToUserNamehidden">
				<input type="text"  id="reportToUserName" size="30" name="reportToUserName" autocomplete="off" value='<%=cform.getReportToUserName()  == null? "" :cform.getReportToUserName() %>' onblur="validateUser()"/>
				<html:hidden  property="reportToUserId"/>
				</td>            
			</tr>
            <tr>
            	<td><%=Constant.getResourceStringValue("hr.user.reportto.Reporting_Method",user1.getLocale())%></td>
				<td>
				<html:select  property="reportToMethod" onchange="showHideOtherMethod();">
				<bean:define name="userReportToForm" property="reportingMethodsList" id="reportingMethodsList" />
	            <html:options collection="reportingMethodsList" property="key"  labelProperty="value"/>
				</html:select>
				</td>
				
            </tr>
            <tr>
				<td width="25%" colspan="2"><span id="reportToMethodOther"><%=Constant.getResourceStringValue("hr.user.relationship.please_specify",user1.getLocale())%><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:text property="reportToMethodOther" size="25" maxlength="200"/></span>
			</tr>
            
            </table>
           <br><br>

		    <table border="0" width="80%">
			<tr>
				<td>
				<%if(cform.getReportToId() != 0){ %>
					<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
				<%}else{ %>
					<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
				<%}%>
					<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
				</td>
			</tr>
			</table>

		</html:form>
</body>