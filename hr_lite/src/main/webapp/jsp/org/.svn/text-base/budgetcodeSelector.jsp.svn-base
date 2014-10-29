<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<title><%=Constant.getResourceStringValue("admin.BudgetCodeSelector",user1.getLocale())%></title>


<bean:define id="form" name="lovForm" type="com.form.LovForm" />

<%
String start = form.getStart(); 
String range = form.getRange();
String results = form.getResults();
String hideval = "inline";
List budgetCodeList = form.getBudgetCodeList();
 if(budgetCodeList != null && budgetCodeList.size()>0){
	 hideval = "none";
 }
%>

<script language="javascript">

var  PFormName= opener.document.forms[0].name;  
document.getElementById('progressbartable').style.display = 'none';
var hidevalue1 = "<%=hideval%>";
if(hidevalue1 == "none"){
	document.getElementById('progressbartable').style.display = 'none'; 
}

function setFocus(){
document.lovForm.criteria.focus();
// document.getElementById('roleCode').focus();
}

	function discard(){
       self.close();
		}

	function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";

var c_value = "";
var id_value = "";
var len = document.lovForm.budgetcodeId.length; 
if(len == undefined) len = 1; 

if (document.lovForm.budgetcodeId.length != undefined)
{

 for (var i=0; i < document.lovForm.budgetcodeId.length; i++)
   {
   if (document.lovForm.budgetcodeId[i].checked == true)
      {
	   
      c_value =document.lovForm.budgetcodeId[i].value + "\n";
	  id_value =  document.lovForm.budgetcodeId[i].id;
      }
   }
}else{
	if (document.lovForm.budgetcodeId.checked == true) {
	c_value = document.lovForm.budgetcodeId.value + "\n";
	  id_value = document.lovForm.budgetcodeId.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

var tempurl = "<a href='#' onClick=window.open("+"'"+"budgetcode.do?method=editbudgetCode&readPreview=2&id="+id_value+"'"+","+ "'"+"BudgetPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=260"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="budgetcodeId">'+tempurl+'</span>';
//window.opener.document.getElementById('budgetcodeId').innerHTML = dyvalue;
if(PFormName == 'jobRequisitionTemplateForm'){

opener.document[PFormName].budgetcodeId.value=id_value;


}

 if(PFormName == 'jobRequisitionForm'){

opener.document[PFormName].budgetcodeId.value=id_value;


}

//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;

//opener.document[PFormName].budgetcodeId.value=id_value;
//opener.document[PFormName].evaluationcriteria.value=c_value;




self.close();
	   
		}

		function searchcri(){
       document.getElementById('progressbartable').style.display = 'inline'; 
	   document.lovForm.action = "lov.do?method=searchbudgetcode&orgId=<%=form.getOrgId()%>&deptId=<%=form.getDepartmentId()%>";
	   document.lovForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}

	function init(){
       
	  document.getElementById('progressbartable').style.display = 'none'; 
		document.lovForm.criteria.focus();
	
	   
		}



function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&orgId="+document.lovForm.orgId.value;
	
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
  
   function resetData(){
  document.lovForm.criteria.value="";
  document.lovForm.budgetYear.value="";
  document.lovForm.orgId.value="";
  document.lovForm.departmentId.value="";
 
    
 }  

</script>

<body class="yui-skin-sam" onLoad="init()" >


<html:form action="/lov.do?method=searchbudgetcode">
<div class="div">
	<table border="0" width="100%" >
	<tr><td colspan="4" bgcolor="gray"> <b><%=Constant.getResourceStringValue("admin.SearchBudgetCodes",user1.getLocale())%></b> </td>
    <tr>
				<td width="50%">
				<%=Constant.getResourceStringValue("admin.BudgetCode.BudgetCode",user1.getLocale())%> : <html:text  property="criteria"/></td>
				<td width="50%">
	            <%=Constant.getResourceStringValue("admin.BudgetCode.BudgetYear",user1.getLocale())%> :	<html:select  property="budgetYear" >
				<option value=""></option>
			<bean:define name="lovForm" property="yearsList" id="yearsList" />

            <html:options collection="yearsList" property="key"  labelProperty="value"/>
			</html:select>
			</td>
	</tr>
	<tr>
			<td>
			<%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%> : 
			<% if(form.getOrgId() != 0){%>
			<html:select property="orgId" disabled="true">
			<bean:define name="lovForm" property="orgnizationList" id="orgnizationList" />

            <html:options collection="orgnizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.BudgetCode.LoadingDepartments",user1.getLocale())%>......</span>
			<%}else{%>
						<html:select property="orgId" onchange="retrieveURL('user.do?method=loadDepartments');">
			<bean:define name="lovForm" property="orgnizationList" id="orgnizationList" />

            <html:options collection="orgnizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.BudgetCode.LoadingDepartments",user1.getLocale())%>......</span>
			<%}%>
			</td>
			<td>
			<%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> : 
			 <% if(form.getDepartmentId() != 0){%>
			<span id="departments">
			<html:select property="departmentId">
		<option value=""></option>
			<bean:define name="lovForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
				<%}else{%>

				<span id="departments">
			<html:select property="departmentId">
			<option value=""></option>
			<bean:define name="lovForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>

					<%}%>
			</span>
			</td>
				
				
			</tr>
			<tr>
			<td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
			 <input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button"></td>
			</tr>

			
				
</table>	
</div>
<br><br>
<div class="div">
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetCodes",user1.getLocale())%></b></td>
</tr>
</table>

<%


 if(budgetCodeList != null && budgetCodeList.size()>0){
%>
<pagination-tag:pager start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
 <%
 if(budgetCodeList != null){
	if(budgetCodeList.size()>0){
 %>
<table border="0" width="100%">

<tr>
<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetCode",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.BudgetCode.Budegetname",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetYear",user1.getLocale())%></td>
</tr>

<%
	 for(int i=0;i<budgetCodeList.size();i++){

		BudgetCode bdcode = (BudgetCode)budgetCodeList.get(i);
		String bgcolor = "";
	   if(i%2 == 0)bgcolor ="#B2D2FF";

%>


<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="budgetcodeId" id="<%=bdcode.getBudgetId()%>" value="<%=bdcode.getBudgetCode()%>">

<a href="#" onClick="window.open('budgetcode.do?method=editbudgetCode&readPreview=2&id=<%=bdcode.getBudgetId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=260')"><%=bdcode.getBudgetCode()%></a>

	</script>
</td>
<td>
<%=bdcode.getBudgetCentreName()%>
</td>
<td>
<%=bdcode.getOrgName()%>
</td>
<td>
<%=bdcode.getDeptName()%>
</td>
<td>
<%=bdcode.getBudgetYear()%>
</td>
</tr>


<%

	 }
 }else{%>

<%=Constant.getResourceStringValue("admin.budgetcode.notfound",user1.getLocale())%>
 <%}}%>
	<%if(budgetCodeList != null && budgetCodeList.size()>0){%>	  
		      <tr>
				<td>
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
				</td>
				
			</tr>

	<%}%>


</table>
<table id="progressbartable" class="dialogBackground" width="100%" height="100%">

<tr>
    <td></td>
    <td style="vertical-align:middle"><img src="jsp/images/loading.gif"></td>
    <td></td>
</tr>


</table>
</div>
</html:form>
</body>