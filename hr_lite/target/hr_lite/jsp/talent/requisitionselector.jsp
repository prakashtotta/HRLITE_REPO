<%@ include file="../common/include.jsp" %>
<%@ taglib uri="http://paginationtag.miin.com" prefix="pagination-tag"%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<title><%=Constant.getResourceStringValue("Requisition.jobreqselecteor",user1.getLocale())%></title>

<bean:define id="form" name="lovForm" type="com.form.LovForm" />

<%
String start = form.getStart();
String range = form.getRange();
String results = form.getResults();

%>

<script language="javascript">

var  PFormName= opener.document.forms[0].name;  



String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}



	function discard(){
       self.close();
		}

	function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";
var frompage = '<%=form.getFrompage()%>';
var c_value = "";
var id_value = ""; 
var len = document.lovForm.requitionId.length; 
if(len == undefined) len = 1; 

if (document.lovForm.requitionId.length != undefined)
{

 for (var i=0; i < document.lovForm.requitionId.length; i++)
   {
   if (document.lovForm.requitionId[i].checked == true)
      {
	   
      c_value =document.lovForm.requitionId[i].value + "\n";
	  id_value =  document.lovForm.requitionId[i].id;
      }
   }

}else{
	if (document.lovForm.requitionId.checked == true) {
	c_value = document.lovForm.requitionId.value + "\n";
	  id_value = document.lovForm.requitionId.id;
	}
	
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

var reqnamearr = c_value.split('_*_*_'); 

var tempurl = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=jobreqdetailsid&approverstatus=yes&offerapplicant=yes&jobreqId="+id_value+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=930,height=800"+"'"+")>"+reqnamearr[0]+"</a>";
var dyvalue = '<span id="requitionId">'+tempurl+'</span>';

// this is an exception case, for others follow formname and frompage
if(frompage == "bulkuploadresume"){
		window.opener.document.getElementById("requitionId").innerHTML = dyvalue;
		opener.document.applicantForm.reqid1.value=id_value;
		self.close();
	}

if(PFormName == "applicantForm"){
	if(frompage == "createapppage"){
	opener.document[PFormName].requitionId.value=id_value;
	//window.opener.document.getElementById("currectctccurrencycodesp").innerHTML = '<span id="currectctccurrencycodesp">'+reqnamearr[1]+'</span>';
	//window.opener.document.getElementById("expectedctccurrencycodesp").innerHTML = '<span id="expectedctccurrencycodesp">'+reqnamearr[1]+'</span>';;
	}
    
	
	//window.opener.document.getElementById("requitionId").innerHTML = dyvalue;
	

	self.close();
}else{
	window.opener.document.getElementById("requitionId").innerHTML = dyvalue;
  // alert(id_value);
opener.document[PFormName].reqid1.value=id_value;
//opener.document[PFormName].jobt1.value=reqnamearr[0];
self.close();
}




	   
}

		function searchcri(){
       
	   document.lovForm.action = "lov.do?method=searchrequistions&frompage=<%=form.getFrompage()%>";
	   document.lovForm.submit();

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
  document.lovForm.jobcode.value="";
  document.lovForm.jobtitle.value="";
  document.lovForm.orgId.value="";
  document.lovForm.departmentId.value="";
  document.lovForm.locationId.value="";
  document.lovForm.status.value="";

  
  setTimeout("retrieveURL('user.do?method=loadDepartments')",200);
 
 }  

function init(){
document.lovForm.jobcode.focus();

}

</script>
<body class="yui-skin-sam" onLoad="init()">
<html:form action="/lov.do?method=searchrequistions&frompage=<%=form.getFrompage()%>">

<table border="0" width="100%">
<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("Requisition.search.requisition",user1.getLocale())%></b></td>

</tr>

</table>
	<div class="div">
     <table border="0" width="100%">
 
			 <tr>
								
				<td width="20%"><%=Constant.getResourceStringValue("Requisition.jobcode",user1.getLocale())%>:</td>
				<td width="30%"><html:text  property="jobcode"/></td>
				<td width="20%"><%=Constant.getResourceStringValue("Requisition.jobrequisition.name",user1.getLocale())%>:</td>
				<td width="30%"><html:text  property="jobtitle"/></td>
			</tr>

			<tr>
			<td width="20%"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%>:</td>
			<td width="30%">
			
			<html:select property="orgId" onchange="retrieveURL('user.do?method=loadDepartments');">
			<option value=""></option>
			<bean:define name="lovForm" property="orgnizationList" id="orgnizationList" />

            <html:options collection="orgnizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept.only",user1.getLocale())%>......</span>
			
			</td>
			<td width="20%"><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%>:</td>
			<td width="30%">
			<span id="departments">
			<html:select property="departmentId" >
			<option value=""></option>
			<bean:define name="lovForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</span>
			</td>
		</tr>

			<tr>
			<td width="20%"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%>:</td>
			<td width="30%">
			<span id="location">
			<html:select  property="locationId">
			<option value=""></option>
			<bean:define name="lovForm" property="locationList" id="locationList" />

            <html:options collection="locationList" property="locationId"  labelProperty="locationName"/>
			</html:select>
			</span>
			</td>
            <td width="20%"><%=Constant.getResourceStringValue("Requisition.status",user1.getLocale())%> :</td>
			<td width="30%"><%=Constant.getResourceStringValue("Requisition.open",user1.getLocale())%><input type="radio" name="status" id="1" <%=(form.getStatus()!= null && form.getStatus().equals("Open"))? "Checked=true" : "" %> value="Open">&nbsp;&nbsp;<%=Constant.getResourceStringValue("Requisition.closed",user1.getLocale())%><input type="radio" name="status" id="0" <%=(form.getStatus()!= null && form.getStatus().equals("Closed"))? "Checked=true" : "" %> value="Closed"></td>
		</tr>	

	</table>




<table border="0" width="100%">	

				
	 <tr>
	   <td>
		<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
		 <input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button">
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
		</td><td></td><td></td>
	 </tr>
			
			

</table>
</div>
<br>
<table border="0" width="100%">

<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("Requisition.jobreqs",user1.getLocale())%></b></td>
</tr>
</table>
<%
List reqList = form.getRequisitionList();
 if(reqList != null && reqList.size()>0){
%>
<pagination-tag:pager action="lov" start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
<%

 if(reqList != null && reqList.size()>0){
%>
<table border="0" width="100%" class="div">
<tr>
<td><%=Constant.getResourceStringValue("Requisition.jobrequisition.name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%></td>
</tr>
 <%

 


	 for(int i=0;i<reqList.size();i++){

		 JobRequisition jbtmpl = (JobRequisition)reqList.get(i);
String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
	

%>


<tr bgcolor=<%=bgcolor%>>
<td >
<input type="radio" name="requitionId" id="<%=jbtmpl.getJobreqId()%>" value="<%=jbtmpl.getJobreqName()+"_*_*_"+jbtmpl.getOrganization().getCurrencyCode()%>">

<a href="#" onClick="window.open('jobreq.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId=<%=EncryptDecrypt.encrypt(String.valueOf(jbtmpl.getJobreqId()))%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=930,height=800')"><%=jbtmpl.getJobreqName()%></a>

	</script>
</td>
<td><%=(jbtmpl.getOrganization() == null)?"":jbtmpl.getOrganization().getOrgName()%></td>
<td><%=(jbtmpl.getDepartment() == null)?"":jbtmpl.getDepartment().getDepartmentName()%></td>
<td><%=(jbtmpl.getLocation() == null)?"":jbtmpl.getLocation().getLocationName()%></td>
</tr>


<%

	 }
 }

 %>
<%  if(reqList != null && reqList.size()<1){ %>
<tr>
				<td>
<font color="red"><b><%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%></b></font>
</td>
				
			</tr>
<%}%>
		  
	<tr>
				<td>
				<%  if(reqList != null && reqList.size()>0){ %>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}%>
				</td>
				
			</tr>	 		     

</table>

</html:form>
