<%@ include file="../common/include.jsp" %>
<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
%>
<title><%=Constant.getResourceStringValue("Requisition.jobreqselecteor",user1.getLocale())%></title>

<bean:define id="form" name="lovForm" type="com.form.LovForm" />

<%
String start = form.getStart();
String range = form.getRange();
String results = form.getResults();
String close = request.getParameter("close");
%>

<%
List checkedreqids1 = new ArrayList();
List checkedreqnames1 = new ArrayList();
if(session.getAttribute("checkedreqids") != null){
checkedreqids1 = (ArrayList)session.getAttribute("checkedreqids");

}
if(session.getAttribute("checkedreqnames") != null){
checkedreqnames1 = (ArrayList)session.getAttribute("checkedreqnames");

}

String reqidstr = "";
for(int i=0;i<checkedreqids1.size();i++){
	reqidstr = reqidstr + checkedreqids1.get(i)+",";
}
if(reqidstr != null && reqidstr.length()>0){
reqidstr = reqidstr.substring(0, reqidstr.length()-1);
}

String reqnamestr = "";
for(int i=0;i<checkedreqnames1.size();i++){
	System.out.println("checkedreqnames1.get(i)"+checkedreqnames1.get(i));
	reqnamestr = reqnamestr + checkedreqnames1.get(i)+",";
}
if(reqnamestr != null && reqnamestr.length()>0){
reqnamestr = reqnamestr.substring(0, reqnamestr.length()-1);
}

%>


<script language="javascript">

var  PFormName= opener.document.forms[0].name;  


var close1 = "<%=close%>";

var clickcountuserids = "";

	function discard(){
		window.location.href = "lov.do?method=removereqidsinsession";
       self.close();
		}



if(close1 == "yes"){
	var reqrr = '<%=reqidstr%>'.split(','); 
var reqnamearr = '<%=reqnamestr%>'.split(','); 

var reqlen=reqrr.length;
var reqnamelen=reqnamearr.length;

    // opener.element.getElementByID("tx1").value = "search result";
var c_value = "";
var id_value = "";
var d_value = "";
 


if (reqlen != undefined)
{

 for (var i=0; i < reqlen; i++)
   {
 
	  if (reqrr[i] != undefined) { 
      c_value = c_value + reqnamearr[i] + ",";
	  id_value = id_value + reqrr[i] +",";
	  var tempurl = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=jobreqdetailsid&approverstatus=yes&offerapplicant=yes&jobreqId="+reqrr[i]+"'"+","+ "'"+"userPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600"+"'"+")>"+reqnamearr[i]+"</a>&nbsp;&nbsp;";
      d_value = d_value + tempurl;
	  }
   }
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;







var dyvalue = '<span id="requitionId">'+d_value+'</span>';

window.opener.document.getElementById("requitionId").innerHTML = dyvalue;

window.opener.document[PFormName].reqid1.value=id_value.trim();
window.opener.document[PFormName].jobt1.value=c_value.trim();

window.location.href = "lov.do?method=removereqidsinsession";
self.close();
}


  function savedata(){
		document.lovForm.action = "lov.do?method=searchrequistionsmultiple&close=yes";
		document.lovForm.submit();
  }

		function searchcri(){
       
	   document.lovForm.action = "lov.do?method=searchrequistionsmultiple";
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


function clickcount(reqid,reqname){

var url="lov.do?method=setreqidsinsession&reqid="+reqid+"&reqname="+reqname;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	
	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}

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


<html:form action="/lov.do?method=searchrequistions">

<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("Requisition.search.requisition",user1.getLocale())%></td><td></td><td></td><td></td>
</tr>
	        
			 
			 <tr>
								
				<td><%=Constant.getResourceStringValue("Requisition.jobcode",user1.getLocale())%>:</td>
				<td><html:text  property="jobcode"/></td>
				<td><%=Constant.getResourceStringValue("Requisition.jobrequisition.name",user1.getLocale())%>:</td>
				<td><html:text  property="jobtitle"/></td>
			</tr>

			<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%>:</td>
			<td>
			
			<html:select property="orgId" onchange="retrieveURL('user.do?method=loadDepartments');">
			<option value=""></option>
			<bean:define name="lovForm" property="orgnizationList" id="orgnizationList" />

            <html:options collection="orgnizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%>......</span>
			
			</td>
			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%>:</td>
			<td>
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
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%>:</td>
			<td>
			<span id="location">
			<html:select  property="locationId">
			<option value=""></option>
			<bean:define name="lovForm" property="locationList" id="locationList" />

            <html:options collection="locationList" property="locationId"  labelProperty="locationName"/>
			</html:select>
			</span>
			</td>
            <td><%=Constant.getResourceStringValue("Requisition.status",user1.getLocale())%></td>
			<td><%=Constant.getResourceStringValue("Requisition.open",user1.getLocale())%><input type="radio" name="status" id="1" <%=(form.getStatus()!= null && form.getStatus().equals("Open"))? "Checked=true" : "" %> value="Open">&nbsp;&nbsp;Closed<input type="radio" name="status" id="0" <%=(form.getStatus()!= null && form.getStatus().equals("Closed"))? "Checked=true" : "" %> value="Closed"></td>
		</tr>	
	
				
	 <tr>
	   <td>
		<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()">
		<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()"></td>
		
		</td>
		<td>
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()">
		</td><td></td><td></td>
	 </tr>
			
			

</table>


<table border="0" width="100%">

<tr>
<td bgcolor="gray"><%=Constant.getResourceStringValue("Requisition.jobreqs",user1.getLocale())%></td>
</tr>
</table>
<%
List reqList = form.getRequisitionList();
 if(reqList != null && reqList.size()>0){
%>
<pagination-tag:pager start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("Requisition.jobrequisition.name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%></td>
</tr>
 <%

 
 if(reqList != null){

	 for(int i=0;i<reqList.size();i++){

		 JobRequisition jbtmpl = (JobRequisition)reqList.get(i);
String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
	String checked = "";
	if(checkedreqids1.contains(String.valueOf(jbtmpl.getJobreqId()))){
		checked = "checked=true";
	}

%>


<tr bgcolor=<%=bgcolor%>>
<td ><input type="checkbox" <%=checked%> name="requitionId" id="<%=jbtmpl.getJobreqId()%>" value="<%=jbtmpl.getJobreqName()%>" onClick="clickcount('<%=jbtmpl.getJobreqId()%>','<%=jbtmpl.getJobreqName()%>')">

<a href="#" onClick="window.open('jobreq.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId=<%=EncryptDecrypt.encrypt(String.valueOf(jbtmpl.getJobreqId()))%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=jbtmpl.getJobreqName()%></a>

	</script>
</td>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%></td>
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
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()">&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()">
			<%}%>
				</td>
				
			</tr>	 		     

</table>

</html:form>
