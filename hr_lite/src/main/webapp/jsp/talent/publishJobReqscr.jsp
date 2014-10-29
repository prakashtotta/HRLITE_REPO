<%@ include file="../common/yahooincludes.jsp" %>
<%@ include file="../common/illegelredirection.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>
<%
User user1 = (User)session.getAttribute(Common.USER_DATA);
UserRegData userreg1 = BOFactory.getUserBO().getUserRegDataByid(String.valueOf(user1.getSuper_user_key()));
%>


<html>
<title><%=Constant.getResourceStringValue("Requisition.Publish.requistion",user1.getLocale())%> </title>
<bean:define id="jobreqform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<%
List vendorList = jobreqform.getPublishToVendorList();


%>
<script language="javascript"> 

window.name = 'myModal';
</script>

<script language="javascript">

function opensearchreferralscheme(k){

	var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("refferalscheme.do?method=referralscemeselector&tempvalue=1&orgid=<%=jobreqform.getParentOrgId()%>&deptid=<%=jobreqform.getDepartmentId()%>&type=E&boxnumber="+k);
 

 window.open(url, "SearchReferralSceme","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
}




function opensearchagencyreferralscheme(k){

	var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("refferalscheme.do?method=referralscemeselector&tempvalue=2&orgid=<%=jobreqform.getParentOrgId()%>&deptid=<%=jobreqform.getDepartmentId()%>&type=V&boxnumber="+k);
 
 window.open(url, "SearchReferralSceme","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
}


function discard(){

	
	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	 	window.close();
	  }
	}

function closewindow(){
	//window.opener.location.reload(true);
	//window.opener.document.location.href=window.opener.document.location.href;
	 window.close();
	}

/*function savedata(){
	var juju= document.jobRequisitionForm.juju.checked;
	var olx= document.jobRequisitionForm.olx.checked;
	var oodle= document.jobRequisitionForm.oodle.checked;
	var trovit= document.jobRequisitionForm.trovit.checked;
	var twitjobsearch= document.jobRequisitionForm.twitjobsearch.checked;
	var workhound= document.jobRequisitionForm.workhound.checked;
	var yakaz= document.jobRequisitionForm.yakaz.checked;
	var indeed= document.jobRequisitionForm.indeed.checked;
	var simplyhired= document.jobRequisitionForm.simplyhired.checked;
	var jooble= document.jobRequisitionForm.jooble.checked;
	var careervital =  document.jobRequisitionForm.careervital.checked;
	var jobdiesel= document.jobRequisitionForm.jobdiesel.checked;
	//alert("juju >>"+juju +" olx >>"+olx +" oodle >>"+oodle+" trovit >>"+trovit+" twitjobsearch >>"+twitjobsearch+" workhound >>"+workhound+" yakaz >>"+yakaz+" indeed >>"+indeed+" simplyhired >>"+simplyhired);
	

      var userids1=document.jobRequisitionForm.usernames.value;
	  var userids2=document.jobRequisitionForm.userids.value;
	// alert(userids2.length);
    // if(userids1.length>0){
    //   document.jobRequisitionForm.countvendor.value=(+document.jobRequisitionForm.countvendor.value+1);
	//  }
	 var countvendor1=document.jobRequisitionForm.countvendor.value;

     
	 	if (document.jobRequisitionForm.publishedComment.value.length > 800){
		alert("Comment size should not be greater than 800 chars");
		return false;
	}

	 document.jobRequisitionForm.action = "jobreq.do?method=publishJobReq&jobreqId=<%=jobreqform.getJobreqId()%>&countvendor="+countvendor1+"&juju="+juju+"&olx="+olx+"&oodle="+oodle+"&trovit="+trovit+"&twitjobsearch="+twitjobsearch+"&workhound="+workhound+"&yakaz="+yakaz+"&indeed="+indeed+"&simplyhired="+simplyhired+"&jooble="+jooble+"&careervital="+careervital+"&jobdiesel="+jobdiesel;
	 document.jobRequisitionForm.submit();
}
*/
function savedata(){
	
	

      var userids1=document.jobRequisitionForm.usernames.value;
	  var userids2=document.jobRequisitionForm.userids.value;
	// alert(userids2.length);
    // if(userids1.length>0){
    //   document.jobRequisitionForm.countvendor.value=(+document.jobRequisitionForm.countvendor.value+1);
	//  }
	 var countvendor1=document.jobRequisitionForm.countvendor.value;

     
	 	if (document.jobRequisitionForm.publishedComment.value.length > 800){
		alert("Comment size should not be greater than 800 chars");
		return false;
	}

	 document.jobRequisitionForm.action = "jobreq.do?method=publishJobReq&jobreqId=<%=jobreqform.getJobreqId()%>&countvendor="+countvendor1;
	 document.jobRequisitionForm.submit();
}

function deleteApprovers(id){

	/*var url="jobtemplate.do?method=deleteApprovers&id="+id;

  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeNo;

	    try {

    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChangeNo;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}*/

 //      setTimeout("retrieveApprovers()",200);
}

function processApprovers() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlApprovers(spanElements);
			
		    //onOtherStateSel();
    	} 
    	//document.getElementById("loading6").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlApprovers(newTextElements){
	//loop through newTextElements ffffffffff
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
				  
		    	  if(name="approverdetails")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

	function processStateChangeNo() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	
    	} 
    	
  	}
}

	function retrieveApprovers() {
		
    //document.getElementById("loading6").style.visibility = "visible";
	var url="jobtemplate.do?method=getApproversForInitiate&id=<%=jobreqform.getJobreqId()%>&type=job";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processApprovers;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processApprovers;
	        req.open("GET", url, true);
		    req.send();
			
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
function opensearchVendorselector(){
		var url = "user.do?method=vendorlistselectorpublishwindow&boxnumber=jobreq";
  window.open(url, "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
document.jobRequisitionForm.countvendor.value="<%=vendorList.size()%>";

}

 

function removeElement(divNum) {
var d = document.getElementById('myDiv');
d.removeChild(divNum);
document.jobRequisitionForm.countvendor.value=(+document.jobRequisitionForm.countvendor.value-1);

}
function opensearchreferralscheme(k){

	var orgidvalue = <%=jobreqform.getParentOrgId()%>;
	var deptvalue = <%=jobreqform.getDepartmentId() %>;

	var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("refferalscheme.do?method=referralscemeselector&tempvalue=1&orgid="+orgidvalue+"&deptid="+deptvalue+"&type=E&boxnumber="+k);
 // window.open("user.do?method=userselector&readPreview=3&boxnumber=-1", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

 window.open(url, "SearchReferralSceme","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
}




function opensearchagencyreferralscheme(k){

	var orgidvalue = <%=jobreqform.getParentOrgId()%>;
	var deptvalue = <%=jobreqform.getDepartmentId() %>;

	var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("refferalscheme.do?method=referralscemeselector&tempvalue=2&orgid="+orgidvalue+"&deptid="+deptvalue+"&type=V&boxnumber="+k);
 // window.open("user.do?method=userselector&readPreview=3&boxnumber=-1", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

 window.open(url, "SearchReferralSceme","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
}
</script>


<%
	List errorList = (List)request.getAttribute(Common.ERROR_LIST);
	if(errorList != null && errorList.size()>0){%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
<% for(int i=0;i<errorList.size();i++){
	String errorval = (String)errorList.get(i);
%>	
	        <tr>
			<td><font color="white"><li><%=errorval%></font></td>
			<td></td>
			</tr>
		
<%}%>
		
	</table>
</div>
<%}%>


<%
String publishJobReq = (String)request.getAttribute("publishJobReq");
	
if(publishJobReq != null && publishJobReq.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("Requisition.Publish.requistion.successfully",user1.getLocale())%></font>
			<!-- <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
			<td> </td>
		</tr>
		
	</table>
</div>
<%}else{%>

<html:form action="jobreq.do?method=publishJobReq" target='myModal'>
<div class="div">
<b><%=Constant.getResourceStringValue("Requisition.Publish.requistion",user1.getLocale())%></b><br>
<BR>
		<fieldset><legend><b><%=Constant.getResourceStringValue("Requisition.Agency",user1.getLocale())%></b></legend>

   <span id="approverdetails">
    <table border="0" bgcolor="#f3f3f3" width="100%">

	




<%if(vendorList != null){ %>
	<input type="hidden" value="<%=vendorList.size()%>" id="theValue" />
	<input type="hidden" name="countvendor" value="<%=vendorList.size()%>" />
	<%}else{ %>
	<input type="hidden" value="" id="theValue" />
    <input type="hidden" name="countvendor" value="0" />
	<%} %>

<% if(vendorList != null && vendorList.size()>0){%>
    <tr>
	
    <td width="50px"><b><%=Constant.getResourceStringValue("hr.Redemption.Agency_name",user1.getLocale())%></b></td>
	<td width="100px"><center><b><%=Constant.getResourceStringValue("hr.requistion.approver.system.defind",user1.getLocale())%></b></center></td>
	<td width="100px"></td>
	
	</tr>



<div id="myDiv">
<%
int k2=1;
if(vendorList != null){
for(int i=0;i<vendorList.size();i++){
	%>
	
<tr><td>
	<%
	PublishToVendor jcomp = (PublishToVendor)vendorList.get(i);
   
	 
	 String tdiv = "my"+k2+"Div";
	 String tspancomp = "publishrequisition_"+k2;
	 String tcompname = "publishToVendorId_"+k2;
	 String tcompname1= "userName_"+k2;
	 String tdval ="<td>";
	 String tempdivappcomp = "<div id='"+tdiv+"'"+">"+
		"<span id='"+tspancomp+"'"+">"+
		"<input type=\"hidden\"  size = \"20\" maxlength= \"100\" name="+tcompname+" value="+jcomp.getUserId()+">"+
        "<input type=\"hidden\"  size = \"20\" maxlength= \"100\" name="+tcompname1+" value="+jcomp.getUserName()+">"+
		"<a href=\'#\' onclick=window.open("+"'"+"user.do?method=editvendor&readPreview=2&userId="+jcomp.getUserId()+"'"+","+ "'"+"userPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=550"+"'"+")>"+jcomp.getUserName()+"</a>"
		+"</td><td><center>"
		+jcomp.getIsFromSystemRule()
		+"</center></td><td>"
		+"<a href=\'#\' onclick=\'removeElement("+tdiv+")\'><img src=\"jsp/images/delete.gif\" border=\"0\" alt=\"delete attributes\" title=\"delete attributes\" height=\"14\"  width=\"19\"/></a>"
		+"</td>"
		+"</div>";
	k2++;

%>

<%=tempdivappcomp%>
</td></tr>
<%}}%>


<!--Competency not added.-->

<%}%>
</div>


			<tr>

				<td><%=Constant.getResourceStringValue("hr.Redemption.Agency",user1.getLocale())%></td>
				<td>
				<span id="vendorlist"></span><a href="#" onClick="opensearchVendorselector()"><img src="jsp/images/selector.gif" border="0"/></a>
				<input type="hidden" name="userids" value=''/>
				<input type="hidden" name="usernames" value=''/>
				</td>
			</tr>
</table>
    </table>
	</span>

	</fieldset>



<br>
<fieldset><legend><b><%=Constant.getResourceStringValue("Requisition.Employee.Referral.Scheme",user1.getLocale())%></b></legend>
<%
Set<RefferalScheme> employeeReferralSchemeList = jobreqform.getEmployeeReferralSchemeList();
%>
<table>
	<tr>
		
		<td>
		<input type="hidden" name="idlistvalflt" value=""/>
		<input type="hidden" value="<%=employeeReferralSchemeList.size()%>" id="theemprefschemeValue2" />
<p><a href="javascript:;" onclick="addEmpRefSchemeElement2();"><img src="jsp/images/add.gif" border="0" alt="<%=Constant.getResourceStringValue("Requisition.Employee.Referral.Scheme",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("Requisition.Employee.Referral.Scheme",user1.getLocale())%>" height="20"  width="19"/></a></p>
		</td>
		<td>

		</td>
	</tr>
    <tr>
	<td>
	<div id="emprefschemediv2"> 

<%
int k=1;
Iterator itr = employeeReferralSchemeList.iterator();
	while(itr!=null && itr.hasNext()){
	RefferalScheme japp = (RefferalScheme)itr.next();
     String tdiv = "my2"+k+"Div";
	 String tspan = "emprefschemespan_"+k;
	 String tempschemename = "empschemename_"+k;
	 String tempschemeid ="empschemeid_"+k;
	 String leveltext1 = "Referral Scheme-"+k+"&nbsp;&nbsp";
	 String filterlink = "";

		filterlink="	<img src=\"jsp/images/referral-scheme.gif\" width='19' height='19'>"+
		"<a href=\"#\" onClick=\"window.open('refferalscheme.do?method=editRefferalScheme&readPreview=2&id="+japp.getRefferalScheme_Id()+"', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=400,height=300')\">"+japp.getRefferalScheme_Name()+"</a>";
	
	String tempdivapp = "<div id='"+tdiv+"'"+">"+
		"<span class='blue' id='"+tspan+"'"+">"+ leveltext1 +
		"<input type=\"hidden\" value='"+japp.getRefferalScheme_Name()+"' name='"+tempschemename+"'"+"/>"+" "+filterlink + "  "+
       "<input type=\"hidden\"   value="+japp.getRefferalScheme_Id()+" name='"+tempschemeid+"'"+"/>"+
	   	"</span>"+
		"<a href=\'#\' onclick=\'opensearchreferralscheme("+k+")\'><img src=\"jsp/images/selector.gif\" border=\"0\"/></a> ";
			 


String tempdivappdel =	" <a href=\'#\' onclick=\'removeEmpRefSchemeElement2("+tdiv+","+k+")\'><img src=\"jsp/images/delete.gif\" border=\"0\" height=\"14\"  width=\"19\"/></a>";

String tempdivapp1 =  "</div>";

	tempdivapp = tempdivapp + tempdivappdel + tempdivapp1;


	k++;
%>

<%=tempdivapp%>

<%}%>


</div>
	</td>
	<td>
	</td>

	

	</tr>

</table>

</fieldset>



<br>
<fieldset><legend><b><%=Constant.getResourceStringValue("Requisition.Agency.Referral.Scheme",user1.getLocale())%></b></legend>
<%
Set<RefferalScheme> agencyReferralSchemeList = jobreqform.getAgencyReferralSchemeList();
%>
<table>
	<tr>

		
		<td>
		<input type="hidden" name="idlistvalfltagencyref" value=""/>
		<input type="hidden" value="<%=agencyReferralSchemeList.size()%>" id="theagencyrefschemeValue2" />
<p><a href="javascript:;" onclick="addAgencyRefSchemeElement2();"><img src="jsp/images/add.gif" border="0" alt="<%=Constant.getResourceStringValue("Requisition.Agency.Referral.Scheme",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("Requisition.Agency.Referral.Scheme",user1.getLocale())%>" height="20"  width="19"/></a></p>
		</td>
		<td>

		</td>

	


	</tr>
    <tr>
	<td>
	<div id="agencyrefschemediv2"> 

<%
int k1=1;
Iterator itr1 = agencyReferralSchemeList.iterator();
	while(itr1 != null && itr1.hasNext()){
	RefferalScheme japp = (RefferalScheme)itr1.next();
     String tdiv = "my3"+k1+"Div";
	 String tspan = "agencyrefschemespan_"+k1;
	 String tempschemename = "agencyschemename_"+k1;
	 String tempschemeid ="agencyschemeid_"+k1;
	 String leveltext1 = "Referral Scheme-"+k1+"&nbsp;&nbsp";
	 String filterlink = "";

		filterlink="	<img src=\"jsp/images/referral-scheme.gif\" width='19' height='19'>"+
		"<a href=\"#\" onClick=\"window.open('refferalscheme.do?method=editRefferalScheme&readPreview=2&id="+japp.getRefferalScheme_Id()+"', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=400,height=300')\">"+japp.getRefferalScheme_Name()+"</a>";
	
	String tempdivapp = "<div id='"+tdiv+"'"+">"+
		"<span class='blue' id='"+tspan+"'"+">"+ leveltext1 +
		"<input type=\"hidden\" value='"+japp.getRefferalScheme_Name()+"' name='"+tempschemename+"'"+"/>"+" "+filterlink + "  "+
       "<input type=\"hidden\"   value="+japp.getRefferalScheme_Id()+" name='"+tempschemeid+"'"+"/>"+
	   	"</span>"+
		"<a href=\'#\' onclick=\'opensearchagencyreferralscheme("+k1+")\'><img src=\"jsp/images/selector.gif\" border=\"0\"/></a> ";



String tempdivappdel =	" <a href=\'#\' onclick=\'removeAgencyRefSchemeElement2("+tdiv+","+k1+")\'><img src=\"jsp/images/delete.gif\" border=\"0\" height=\"14\"  width=\"19\"/></a>";

String tempdivapp1 =  "</div>";

	tempdivapp = tempdivapp + tempdivappdel + tempdivapp1;


	k1++;
%>

<%=tempdivapp%>

<%}%>


</div>
	</td>
	<td>
	</td>
	</tr>

</table>

</fieldset>



<table width="700px" border="0">

<%
	if(!StringUtils.isNullOrEmpty(Constant.getValue("is.open.source")) && Constant.getValue("is.open.source").equals("yes")){

%>

	<tr bgcolor="#F0F0F0">
			<td>
			<%=Constant.getResourceStringValue("hr.system.rule.publish.to.external",user1.getLocale())%>
			</td>
				<td>
				<%
						if(jobreqform.getPublishToExternal()!=null && jobreqform.getPublishToExternal().equals("Y")){%>
				         <input type="checkbox" name="publishToExternal" id="publishToExternal" value="Y" checked="true"/>
						<%}else{%>
						 <input type="checkbox" name="publishToExternal" id="publishToExternal" value="Y" checked="true"/>
						<% }%>
						 
				
				</td>
	</tr>

<%}else{%>

<tr bgcolor="#F0F0F0">
			<td>
			<%=Constant.getResourceStringValue("hr.system.rule.publish.to.external",user1.getLocale())%>
			</td>
				<td>
				<%
						if(jobreqform.getPublishToExternal()!=null && jobreqform.getPublishToExternal().equals("Y")){%>
				         <input type="checkbox" name="publishToExternal" id="publishToExternal" value="Y" checked="true"/>
						<%}else{%>
						 <input type="checkbox" name="publishToExternal" id="publishToExternal" value="Y" />
						<% }%>
						 (This will post to http://www.<%=userreg1.getSubdomain()%>.hires360.com)
				
				</td>
	</tr>


<%}%>

<tr bgcolor="#F8F8F8"> 
<td>
<%=Constant.getResourceStringValue("hr.system.rule.publish.to.employee",user1.getLocale())%>
</td><td>
<%
		if(jobreqform.getPublishToEmpRef()!=null && jobreqform.getPublishToEmpRef().equals("Y")){%>
         <input type="checkbox" name="publishToEmpRef" id="publishToEmpRef"  value="Y" checked="true"/>
		<% }else{%>
		 <input type="checkbox" name="publishToEmpRef" id="publishToEmpRef"  value="Y" /></td>
		<% }%>

</tr>

<tr bgcolor="#F0F0F0">
<td>
<%=Constant.getResourceStringValue("hr.eeo.info.included",user1.getLocale())%>
</td><td>
<%
		if(jobreqform.getEeoInfoIncluded()!=null && jobreqform.getEeoInfoIncluded().equals("Y")){%>
         <input type="checkbox" name="eeoInfoIncluded" id="eeoInfoIncluded"  value="Y" checked="true"/>
		<% }else{%>
		 <input type="checkbox" name="eeoInfoIncluded" id="eeoInfoIncluded"  value="Y" /></td>
		<% }%>

</tr>

<tr>
	<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%></td>
	<td><html:textarea property="publishedComment" cols="60" rows="4"/></td>
</tr>

</table>
<%
	if(!StringUtils.isNullOrEmpty(Constant.getValue("is.open.source")) && Constant.getValue("is.open.source").equals("yes")){

}else{%>
<table>
<tr>
<td colspan="2">

	<table border="0" width="90%">
	<tr>
			<td width="26%">
				<input type="checkbox" align="bottom" name="yakaz" id="yakaz"  value="" class="titleHintBox"  title=""/>&nbsp;&nbsp;<img src="jsp/images/yakaz.GIF" align="top" height="30" width="70" border="0" title="Yakaz"></img>
			</td>
			<td>
				<input type="checkbox" align="bottom" name="jobdiesel" id="jobdiesel"  value="" class="titleHintBox"  title=""/>&nbsp;<img src="jsp/images/jobdiesel.png" align="top" height="40" width="130" border="0" title="Job Diesel"></img>
			</td>
			
	</tr>
</table>
<table>
<tr>
<td>
 
<font size="2"></font><b>Integration with below job boards coming soon.</b></font>
</td>

</tr>
</table>
<table>
<tr>
			<td>
			<input type="checkbox" align="bottom" disabled="true" name="juju" id="juju"  value="" class="titleHintBox"  title=""/>&nbsp;&nbsp;<img src="jsp/images/juju.GIF" align="top" height="30" width="70" border="0" title="Juju"></img>
			</td>
			
			<td >
			<input type="checkbox" align="bottom" disabled="true" name="olx" id="olx"  value="" class="titleHintBox"  title=""/>&nbsp;&nbsp;<img src="jsp/images/olxfeed_logo.gif" align="top" height="30" width="30" border="0" title="Olx"></img>
			</td>
			
			<td >
			<input type="checkbox" align="bottom" disabled="true" name="oodle" id="oodle"  value="" class="titleHintBox"  title=""/>&nbsp;&nbsp;<img src="jsp/images/oodle.GIF" align="top" height="30" width="70" border="0" title="Oodle"></img>
			</td>
			
			<td >	
			<input type="checkbox" align="bottom" disabled="true" name="trovit" id="trovit"  value="" class="titleHintBox"  title=""/>&nbsp;&nbsp;<img src="jsp/images/trovit.gif" align="top" height="30" width="70" border="0" title="Trovit"></img>	
			</td>
			
			<td >	
			<input type="checkbox" align="bottom" disabled="true" name="simplyhired" id="simplyhired"  value="" class="titleHintBox"  title=""/>&nbsp;&nbsp;<img src="jsp/images/simplyhired_logo.GIF" align="top" height="30" width="90" border="0" title="SimplyHired"></img>
			</td>


		</tr>
		
		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
		
		<tr>
			<td >	
			<input type="checkbox" align="bottom" disabled="true" name="twitjobsearch" id="twitjobsearch"  value="" class="titleHintBox"  title=""/>&nbsp;&nbsp;<img src="jsp/images/twitjobsearch.GIF" align="top" height="30" width="120" border="0" title="TwitJobSearch"></img>
			</td>
			<td >
			<input type="checkbox" align="bottom" disabled="true" name="workhound" id="workhound"  value="" class="titleHintBox"  title=""/>&nbsp;&nbsp;<img src="jsp/images/workhound.GIF" align="top" height="30" width="100" border="0" title="Workhound"></img>
			</td>
			<td >	
			<input type="checkbox" align="bottom" disabled="true" name="careervital" id="careervital"  value="" class="titleHintBox"  title=""/>&nbsp;&nbsp;<img src="jsp/images/careerVital.gif" align="top" height="30" width="90" border="0" title="careervitals"></img>
			</td>

			<td>
			<input type="checkbox" align="bottom" disabled="true" name="indeed" id="indeed"  value="" class="titleHintBox"  title=""/>&nbsp;&nbsp;<img src="jsp/images/indeed.GIF" align="top" height="30" width="70" border="0" title="Indeed"></img>
			</td>
			<td>
			<input type="checkbox" align="bottom" disabled="true" name="jooble" id="jooble"  value="" class="titleHintBox"  title=""/>&nbsp;&nbsp;<img src="jsp/images/jooble.gif" align="top" height="30" width="70" border="0" title="jooble"></img>
			</td>
			<td>
			</td>
		
		</tr>
	</table>

<%}%>
	</td>
</tr>
<tr>
<td colspan="2">
<input type="button" name="login" value="<%=Constant.getResourceStringValue("Requisition.Publish",user1.getLocale())%>" onClick="savedata()" class="button">
<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
</td>
<td></td>
</tr>
</table>
</div>
</html:form>

<%}%>


<script language="javascript">
	function addEmpRefSchemeElement2() {

  var ni = document.getElementById('emprefschemediv2');
  var numi = document.getElementById('theemprefschemeValue2');
  var num = (document.getElementById('theemprefschemeValue2').value -1)+ 2;
  if(num < 50){
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my2'+num+'Div';
  newdiv.setAttribute('id',divIdName);

  var spanstart = '<span class="blue" id="emprefschemespan_'+num+'">';
  var username1 = '<input type="hidden"  value="" name="empschemename_'+num+'"/>';
  var userid1 = '<input type="hidden"    name="empschemeid_'+num+'"/>';
   var levelid = '<input type="hidden"    name="empschemelevel_'+num+'"/>';
    var leveltext = 'Referral Scheme-'+num+'&nbsp;&nbsp';
    var spanend = '</span>'; 
  newdiv.innerHTML = ''+spanstart+''+leveltext+''+levelid+''+username1+''+userid1+ ''+spanend+''+'<a href=\'#\' onclick=\'opensearchreferralscheme('+num+')\'><img src=\"jsp/images/selector.gif\" border=\"0\"/></a>'+''+'  <a href=\'#\' onclick=\'removeEmpRefSchemeElement2('+divIdName+','+num+')\'><img src=\"jsp/images/delete.gif\" border=\"0\" height=\"14\"  width=\"19\"/></a>';
  ni.appendChild(newdiv);
  } else {
	  alert("maximum level exceed");
  }
}




function removeEmpRefSchemeElement2(divNum,num) {
	var approvenum = 'empschemeid_'+num;
	var idapprove=document.jobRequisitionForm[approvenum].value;
	var listidapprover=document.jobRequisitionForm.idlistvalflt.value;
var d = document.getElementById('emprefschemediv2');
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
document.jobRequisitionForm.idlistvalflt.value=array1new;
//alert(array1new);
}

</script>

<script language="javascript">
	function addAgencyRefSchemeElement2() {

  var ni = document.getElementById('agencyrefschemediv2');
  var numi = document.getElementById('theagencyrefschemeValue2');
  var num = (document.getElementById('theagencyrefschemeValue2').value -1)+ 2;
  if(num < 50){
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my3'+num+'Div';
  newdiv.setAttribute('id',divIdName);

  var spanstart = '<span class="blue" id="agencyrefschemespan_'+num+'">';
  var username1 = '<input type="hidden"  value="" name="agencyschemename_'+num+'"/>';
  var userid1 = '<input type="hidden"    name="agencyschemeid_'+num+'"/>';
   var levelid = '<input type="hidden"    name="agencyschemelevel_'+num+'"/>';
    var leveltext = 'Referral Scheme-'+num+'&nbsp;&nbsp';
    var spanend = '</span>'; 
  newdiv.innerHTML = ''+spanstart+''+leveltext+''+levelid+''+username1+''+userid1+ ''+spanend+''+'<a href=\'#\' onclick=\'opensearchagencyreferralscheme('+num+')\'><img src=\"jsp/images/selector.gif\" border=\"0\"/></a>'+''+'  <a href=\'#\' onclick=\'removeAgencyRefSchemeElement2('+divIdName+','+num+')\'><img src=\"jsp/images/delete.gif\" border=\"0\" height=\"14\"  width=\"19\"/></a>';
  ni.appendChild(newdiv);
  } else {
	  alert("maximum level exceed");
  }
}




function removeAgencyRefSchemeElement2(divNum,num) {
	var approvenum = 'agencyschemeid_'+num;
	var idapprove=document.jobRequisitionForm[approvenum].value;
	var listidapprover=document.jobRequisitionForm.idlistvalfltagencyref.value;
var d = document.getElementById('agencyrefschemediv2');
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
document.jobRequisitionForm.idlistvalfltagencyref.value=array1new;
//alert(array1new);
}

</script>

