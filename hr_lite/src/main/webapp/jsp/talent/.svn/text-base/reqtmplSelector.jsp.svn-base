<%@ include file="../common/include.jsp" %>
<%@ taglib uri="http://paginationtag.miin.com" prefix="pagination-tag"%>
<%@ include file="../common/autocomplete.jsp" %>
<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<title><%=Constant.getResourceStringValue("Requisition.template.selecteor",user1.getLocale())%> </title>
<bean:define id="form" name="lovForm" type="com.form.LovForm" />

<%
String start = form.getStart();
String range = form.getRange();
String results = form.getResults();

%>

<script language="javascript">

var  PFormName= opener.document.forms[0].name;  


	function discard(){
       self.close();
		}

	function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";

var c_value = "";
var id_value = "";
var len = document.lovForm.templateId.length; 
if(len == undefined) len = 1; 

if (document.lovForm.templateId.length != undefined)
{

 for (var i=0; i < document.lovForm.templateId.length; i++)
   {
   if (document.lovForm.templateId[i].checked == true)
      {
	   
      c_value =document.lovForm.templateId[i].value + "\n";
	  id_value =  document.lovForm.templateId[i].id;
      }
   }
}else{
	if (document.lovForm.templateId.checked == true) {
	c_value = document.lovForm.templateId.value + "\n";
	  id_value = document.lovForm.templateId.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

//var tempurl = "<a href='#' onClick=window.open("+"'"+"jobtemplate.do?method=edittemplate&templateId="+id_value+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="templateId"></span>';

//window.opener.document.getElementById("templateIdt").innerHTML = dyvalue;
opener.document.jobRequisitionForm["templateId"].value=id_value;
self.close();
	   
		}

		function searchcri(){
       
	   document.lovForm.action = "lov.do?method=searchreqtemplates";
	   document.lovForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}
function resetData(){
  document.lovForm.criteria.value="";
  document.lovForm.orgId.value="";
  document.lovForm.departmentId.value="";
  document.lovForm.projectId.value="";
  document.lovForm.budgetId.value="";
  document.lovForm.status.value="";

		}

document.onkeypress=function(e){
var e=window.event || e
//alert("CharCode value: "+e.charCode)
//alert("Character: "+String.fromCharCode(e.charCode))
	if(e.keyCode==13)
	{
	setTimeout("document.lovForm.search.click();",200);
}
}


function retrieveURLOrg(url) {
	   //convert the url to a string
	    document.getElementById("loadingd").style.visibility = "visible";

		
		url=url+"&orgId="+document.lovForm.orgId.value;
		
	  	//Do the AJAX call
	  	if (window.XMLHttpRequest) { 
		    req = new XMLHttpRequest();    	// Non-IE browsers
	    	req.onreadystatechange = processStateChangeOrg;

		    try {
	    		req.open("GET", url, true);
				
		    } catch (e) {}
		    req.send(null);
	  	} else if (window.ActiveXObject) {
	       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
	    	if (req) {
		    	req.onreadystatechange=processStateChangeOrg;
		        req.open("GET", url, true);
			    req.send();
				
	    	}
	  	}
	}

	function processStateChangeOrg() {
		if (req.readyState == 4) { // Complete
		    if (req.status == 200) { // OK response
		       	//document.getElementById("states").innerHTML = req.responseText;
	    	    //Split the text response into Span elements
	    		spanElements = splitTextIntoSpanOrg(req.responseText);

	    		//Use these span elements to update the page
			    replaceExistingWithNewHtmlOrg(spanElements);
			    //onOtherStateSel();
	    	} 
	    	document.getElementById("loadingd").style.visibility = "hidden";	
	  	}
	}





	function replaceExistingWithNewHtmlOrg(newTextElements){
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






	function retrieveProjcodes(url) {
	   //convert the url to a string
	    document.getElementById("loadingp").style.visibility = "visible";

		
		url=url+"&departmentId="+document.lovForm.departmentId.value;
		
	  	//Do the AJAX call
	  	if (window.XMLHttpRequest) { 
		    req = new XMLHttpRequest();    	// Non-IE browsers
	    	req.onreadystatechange = processStateChangeProj;

		    try {
	    		req.open("GET", url, true);
				
		    } catch (e) {}
		    req.send(null);
	  	} else if (window.ActiveXObject) {
	       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
	    	if (req) {
		    	req.onreadystatechange=processStateChangeProj;
		        req.open("GET", url, true);
			    req.send();
				
	    	}

	  	}

	}
		 


	function processStateChangeProj() {
		if (req.readyState == 4) { // Complete
		    if (req.status == 200) { // OK response
		       	//document.getElementById("states").innerHTML = req.responseText;
	    	    //Split the text response into Span elements
	    		spanElements = splitTextIntoSpanOrg(req.responseText);

	    		//Use these span elements to update the page
			    replaceExistingWithNewHtmlProj(spanElements);
			    //onOtherStateSel();
	    	} 
	    	document.getElementById("loadingp").style.visibility = "hidden";	
	  	}
	}


	function replaceExistingWithNewHtmlProj(newTextElements){
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
					  
			    	  if(name="projectcodes")	document.getElementById(name).innerHTML = content;
				  }	   			
	   	 	}
		}
		retrieveBudgetCode();
	}




	function splitTextIntoSpanOrg(textToSplit){
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




	function retrieveBudgetCode() {
	   //convert the url to a string
	    document.getElementById("loadingp").style.visibility = "visible";

		
		url="lov.do?method=loadBudgetCode&departmentId=<%=form.getDepartmentId()%>&orgId="+document.lovForm.orgId.value;
		
	  	//Do the AJAX call
	  	if (window.XMLHttpRequest) { 
		    req = new XMLHttpRequest();    	// Non-IE browsers
	    	req.onreadystatechange = processStateChangeBudget;

		    try {
	    		req.open("GET", url, true);
				
		    } catch (e) {}
		    req.send(null);
	  	} else if (window.ActiveXObject) {
	       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
	    	if (req) {
		    	req.onreadystatechange=processStateChangeBudget;
		        req.open("GET", url, true);
			    req.send();
				
	    	}
	  	}

	}

	function processStateChangeBudget() {
		if (req.readyState == 4) { // Complete
		    if (req.status == 200) { // OK response
		       	//document.getElementById("states").innerHTML = req.responseText;
	    	    //Split the text response into Span elements
	    		spanElements = splitTextIntoSpanBudget(req.responseText);

	    		//Use these span elements to update the page
			    replaceExistingWithNewHtmlBudget(spanElements);
			    //onOtherStateSel();
	    	} 
	    	document.getElementById("loadingp").style.visibility = "hidden";	
	  	}
	}

	function replaceExistingWithNewHtmlBudget(newTextElements){
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
					  
			    	  if(name="budgetcodescodes")	document.getElementById(name).innerHTML = content;
				  }	   			
	   	 	}
		}
	}

	function splitTextIntoSpanBudget(textToSplit){
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
	function init(){
		document.lovForm.criteria.focus();

		}
</script>

<body class="yui-skin-sam" onLoad="init()">

<html:form action="/lov.do?method=searchreqtemplates">
<div class="div">
	<table border="0" width="100%">
	<tr><td colspan="4" bgcolor="gray">
	<b><%=Constant.getResourceStringValue("Requisition.searchreq",user1.getLocale())%>  </b>
	</td>
	</tr>
    <tr>
    			<td><%=Constant.getResourceStringValue("Requisition.tempname",user1.getLocale())%>:</td>
				<td><html:text property="criteria"/></td>
				<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%>:</td>
				<td>
			
					<html:select property="orgId" onchange="retrieveURLOrg('lov.do?method=loadDeptlistWithProjectcode');">
					
					<option value=""></option>
					<bean:define name="lovForm" property="orgnizationList" id="orgnizationList" />
            		<html:options collection="orgnizationList" property="orgId"  labelProperty="orgName"/>
			
					</html:select>&nbsp;
					<span class="textboxlabel" id="loadingd" STYLE="font-size: smaller;Visibility:hidden";>
					<br>	<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%> ......</br></span>
						
						
				</td>
	</tr>		
	<tr>		

				<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%>:</td>
				<td>
				<span id="departments">
				
				<html:select property="departmentId" onchange="retrieveProjcodes('lov.do?method=loadProjectCode');">
				<option value=""></option>
				<bean:define name="lovForm" property="departmentList" id="departmentList" />
            	<html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			
				</html:select>
				<span class="textboxlabel" id="loadingp" STYLE="font-size: smaller;Visibility:hidden";>
					<br>	<%=Constant.getResourceStringValue("Requisition.LoadPcode&Bcode",user1.getLocale())%>......</br></span>

			  
			</td>
			
				</td>
				<td><%=Constant.getResourceStringValue("admin.ProjectCode.PorjectCodes",user1.getLocale())%>:</td>
				<td>
					<span id="projectcodes">
					<html:select property="projectId">
					<option value=""></option>
					<bean:define name="lovForm" property="projectcodeList" id="projectcodeList" />
		
		            <html:options collection="projectcodeList" property="projectId"  labelProperty="projCode"/>
					</html:select>
					
					</span>
				</td>

	</tr>
	<tr>
				<td><%=Constant.getResourceStringValue("Requisition.budgetcode",user1.getLocale())%>:</td>
				<td>
                 <span id="budgetcodescodes">
				<html:select  property="budgetId">
				<option value=""></option>
				<bean:define name="lovForm" property="budgetCodeList" id="budgetCodeList" />
	            <html:options collection="budgetCodeList" property="budgetId"  labelProperty="budgetCode"/>
				</html:select>
				</span>


	</tr>
	<tr>

	            <td><%=Constant.getResourceStringValue("Requisition.status",user1.getLocale())%> :</td>
				<td><%=Constant.getResourceStringValue("Requisition.open",user1.getLocale())%><input type="radio" name="status" id="1"  <%=(form.getStatus()!= null && form.getStatus().equals("Open"))? "Checked=true" : "" %> value="Open">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.closed",user1.getLocale())%><input type="radio" name="status" id="0" <%=(form.getStatus()!= null && form.getStatus().equals("Closed"))? "Checked=true" : "" %> value="Closed"></td>
	</tr>
				
	
	<tr>
				<td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
                <input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button"></td>
	</tr>

			
				
</table>	
</div>
<br>
<div class="div">

<table border="0" width="100%">
<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("Requisition.jobreqtemp",user1.getLocale())%></b></td>
</tr>
</table>
<%
List reqtmplList = form.getReqtemplateList();
 if(reqtmplList != null && reqtmplList.size()>0){
%>
<pagination-tag:pager start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
<%

 if(reqtmplList != null && reqtmplList.size()>0){
%>
<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("Requisition.tempname",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetCode",user1.getLocale())%></td>
</tr>
 <%



	 for(int i=0;i<reqtmplList.size();i++){

		 JobRequisionTemplate jbtmpl = (JobRequisionTemplate)reqtmplList.get(i);
String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";

%>


<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="templateId" id="<%=jbtmpl.getTemplateId()%>" value="<%=jbtmpl.getTemplateName()%>">

<a href="#" onClick="window.open('jobtemplate.do?method=editjobtemplateselector&templateId=<%=jbtmpl.getTemplateId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=900,height=900')"><%=jbtmpl.getTemplateName()%></a>

	</script>
</td>
<td><%=(jbtmpl.getOrganization() == null)?"":jbtmpl.getOrganization().getOrgName()%></td>
<td><%=(jbtmpl.getDepartment() == null)?"":jbtmpl.getDepartment().getDepartmentName()%></td>
<td><%=(jbtmpl.getProjectcode() == null)?"":jbtmpl.getProjectcode().getProjCode()%></td>
<td><%=(jbtmpl.getBudgetcode() == null)?"":jbtmpl.getBudgetcode(). getBudgetCode()%></td>

</tr>


<%

	 }
 }

 %>
 </table>
<%  if(reqtmplList != null && reqtmplList.size()<1){ %>

<%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%>

<%}%>
	
	<table>	  
	<tr>
				<td>
				<%  if(reqtmplList != null && reqtmplList.size()>0){ %>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}%>
				</td>
				
			</tr>	

</table>
</div>
</html:form>