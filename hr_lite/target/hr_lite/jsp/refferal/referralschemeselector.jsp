<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<title><%=Constant.getResourceStringValue("admin.RefferalScheme.Referralschemeselector",user1.getLocale())%> </title>
<%
String orgredonly = (String)request.getAttribute("orgredonly");
String tempvalue = (String)request.getAttribute("tempvalue");
String boxnumber = (String)request.getAttribute("boxnumber");
System.out.println("boxnumber : "+boxnumber);
%>

<bean:define id="form" name="refferalSchemeForm" type="com.form.RefferalSchemeForm" />

<%
String start = form.getStart();
String range = form.getRange();
String results = form.getResults();

%>

<script language="javascript">
var tempvaluesc = "<%=tempvalue%>";
var boxnu = "<%=boxnumber%>";
var  PFormName= opener.document.forms[0].name;  

String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}



function discard(){
       self.close();
}

function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";

	var c_value = "";
	var id_value = "";
	var len = document.refferalSchemeForm.referralschemeid.length; 
	var refschemevalue = document.refferalSchemeForm.referralschemeid.value; 
	
	if(len == undefined) len = 1; 
	
	var radios = document.getElementsByTagName('input');
	var flag="notchecked"
	for (var i = 0; i < radios.length; i++) {
	    if (radios[i].type == 'radio' && radios[i].checked) {
	        // get value, set checked flag or do whatever you need to
	        flag="checked";    
	    }
	}
	//alert(flag);
	if(flag == "checked"){
	
	
	
			if (document.refferalSchemeForm.referralschemeid.length != undefined)
			{
			
			 for (var i=0; i < document.refferalSchemeForm.referralschemeid.length; i++)
			   {
			   if (document.refferalSchemeForm.referralschemeid[i].checked == true)
			      {
				   
			      c_value =document.refferalSchemeForm.referralschemeid[i].value + "\n";
				  id_value =  document.refferalSchemeForm.referralschemeid[i].id;
			      }
			   }
			}else{
				if (document.refferalSchemeForm.referralschemeid.checked == true) {
				c_value = document.refferalSchemeForm.referralschemeid.value + "\n";
				  id_value = document.refferalSchemeForm.referralschemeid.id;
				}
			}
			//opener.getElementByID("evaluationsheet").innerHTML = c_value;
	
			var reqnamearr = c_value.split('_*_*_'); 
			
			var tempurl = "<a href='#' onClick=window.open("+"'"+"refferalscheme.do?method=editRefferalScheme&readPreview=2&id="+id_value+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600"+"'"+")>"+reqnamearr[0]+"</a>";
			var dyvalue = '<span id="referralId">'+tempurl+'</span>';
	
	
	
	       
			if(tempvaluesc == 1){
				if(boxnu == 'requisitionApprovalreffScheme'){
					opener.document[PFormName].refferalSchemeId.value=id_value;
				}else{
				
			
					  var spanstart = '<span  id="emprefschemespan_'+boxnu+'">' + 'Referral Scheme-'+boxnu+'&nbsp;&nbsp';
					  var username1 = '<input type="hidden" value="'+c_value+'" name="empschemename_'+boxnu+'"/>';
					  var userid1 = '<input type="hidden"  value='+id_value+'  name="empschemeid_'+boxnu+'"/>';
					  var isusergroup ="";
					  var userlink ="";
					
						userlink = "<img src='jsp/images/referral-scheme.gif' width='19' height='19'><a href='#' onClick=window.open("+"'"+"refferalscheme.do?method=editRefferalScheme&readPreview=2&id="+id_value+"'"+","+ "'"+"filterPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550"+"'"+")>"+c_value+"</a>";
					
					  var spanend = '</span>'; 
					
					  var newspanvalue = spanstart+username1+userlink+userid1+spanend;
					  var tmp = "emprefschemespan_"+boxnu;
					
					  window.opener.document.getElementById(tmp).innerHTML = newspanvalue;
				}
			
			}else{
				if(boxnu == 'requisitionApprovalAgencyScheme'){
					opener.document[PFormName].agencyRefferalSchemeId.value=id_value;
				}else{
						
			
					  var spanstart = '<span  id="agencyrefschemespan_'+boxnu+'">' + 'Agency Refferal Scheme-'+boxnu+'&nbsp;&nbsp';
					  var username1 = '<input type="hidden" value="'+c_value+'" name="agencyschemename_'+boxnu+'"/>';
					  var userid1 = '<input type="hidden"  value='+id_value+'  name="agencyschemeid_'+boxnu+'"/>';
					  var isusergroup ="";
					  var userlink ="";
					
						userlink = "<img src='jsp/images/referral-scheme.gif' width='19' height='19'><a href='#' onClick=window.open("+"'"+"refferalscheme.do?method=editRefferalScheme&readPreview=2&id="+id_value+"'"+","+ "'"+"filterPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550"+"'"+")>"+c_value+"</a>";
					
					  var spanend = '</span>'; 
					
					  var newspanvalue = spanstart+username1+userlink+userid1+spanend;
					  var tmp = "agencyrefschemespan_"+boxnu;
					
					  window.opener.document.getElementById(tmp).innerHTML = newspanvalue;
				}
			
			}
	
			self.close();
	}else{
		
		alert("<%=Constant.getResourceStringValue("admin.RefferalScheme.Referralscheme.select",user1.getLocale())%>");
	}

 
}

function searchcri(){
       
	   document.refferalSchemeForm.action = "refferalscheme.do?method=searchreferralscheme&tempvalue=<%=tempvalue%>&orgredonly=<%=orgredonly%>&orgid=<%=form.getOrgId()%>&deptid=<%=form.getDeptId()%>&type=<%=form.getSchemeType()%>&boxnumber=<%=boxnumber%>";
	   document.refferalSchemeForm.submit();

}


function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&orgId="+document.refferalSchemeForm.orgId.value;
	
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
function resetdata(){
	//alert("");
	document.refferalSchemeForm.refferalScheme_Name.value="";
	document.refferalSchemeForm.ref_budgetId.value="";
	
}
function init(){
	document.refferalSchemeForm.refferalScheme_Name.focus();
	}
	</script>
<body class="yui-skin-sam" onLoad="init()" >

<html:form action="/refferalscheme.do?method=searchreferralscheme&tempvalue=<%=tempvalue%>&orgredonly=<%=orgredonly%>&orgid=<%=form.getOrgId()%>&deptid=<%=form.getDeptId()%>">
<table border="0" width="100%">
<tr>
<td bgcolor="gray" colspan="4"><b><%=Constant.getResourceStringValue("admin.RefferalScheme.Searchreferralscheme",user1.getLocale())%></b></td>
</tr>
</table>
<div class="div">
<table border="0" width="100%">

	        
			 
		<tr>
								
				<td><%=Constant.getResourceStringValue("admin.RefferalScheme.name",user1.getLocale())%> :</td>
				<td><html:text  property="refferalScheme_Name"/></td>
				<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.rbcoe",user1.getLocale())%> :</td>
			<td>
			
			<html:select property="ref_budgetId">
			<option value=""></option>
			<bean:define name="refferalSchemeForm" property="refferalBudgetCodeList" id="refferalBudgetCodeList" />

            <html:options collection="refferalBudgetCodeList" property="ref_budgetId"  labelProperty="ref_budgetCentreName"/>
			</html:select>
			</td>
			</tr>

            
			<tr>
			 <% if(orgredonly != null){%>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%> :</td>
			<td>
			
			<html:select property="orgId" disabled="true">
			<bean:define name="refferalSchemeForm" property="orgnizationList" id="orgnizationList" />

            <html:options collection="orgnizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			
			
			</td>
             <%}else{%>
			 		<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%> :</td>
			<td>
			
			<html:select property="orgId" onchange="retrieveURL('user.do?method=loadDepartments');">
			<option value=""></option>
			<bean:define name="refferalSchemeForm" property="orgnizationList" id="orgnizationList" />

            <html:options collection="orgnizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept",user1.getLocale())%>......</span>
			
			</td>

			 <%}%>

             <% if(orgredonly != null){%>
			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> :</td>
			<td>
		
			<html:select property="deptId" disabled="true">
			<option value=""></option>
			<bean:define name="refferalSchemeForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			
			</td>

			 <%}else{%>

			 			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> :</td>
			<td>
			<span id="departments">
			<html:select property="deptId" >
			<option value=""></option>
			<bean:define name="refferalSchemeForm" property="departmentList" id="departmentList" />

            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			</html:select>
			</span>
			</td>
			 <%}%>
		</tr>
	

		
	
				
	 <tr>
	   <td>
		<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()" class="button">
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
		</td><td></td><td></td>
	 </tr>
			
			

</table>
</div>
<br><br>
 <div class="div">
<table border="0" width="100%">

<tr>
<td bgcolor="gray"><%=Constant.getResourceStringValue("admin.RefferalScheme.Referralschemes",user1.getLocale())%></td>
</tr>
</table>
<%
List refschemeList = form.getReferralSchemeList();
 if(refschemeList != null && refschemeList.size()>0){
%>
<pagination-tag:pager start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
<table border="0" width="100%">
 <%
 if(refschemeList != null && refschemeList.size()>0){
%>
<tr>
<td><%=Constant.getResourceStringValue("admin.RefferalScheme.name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.RefferalSchemeType.reward.type",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.rbcoe",user1.getLocale())%></td>
</tr>
<%
	 for(int i=0;i<refschemeList.size();i++){

		 RefferalScheme refscheme = (RefferalScheme)refschemeList.get(i);
String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
	

%>


<tr bgcolor=<%=bgcolor%>>
<td >
<input type="radio" name="referralschemeid" id="<%=refscheme.getRefferalScheme_Id()%>" value="<%=refscheme.getRefferalScheme_Name()%>">

<a href="#" onClick="window.open('refferalscheme.do?method=editRefferalScheme&readPreview=2&id=<%=refscheme.getRefferalScheme_Id()%>', '_blank','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=refscheme.getRefferalScheme_Name()%></a>

	</script>
</td>
<td>

<% 
String orgname = "";

if(refscheme.getRefferalBudgetCode()!= null){
     if(refscheme.getRefferalBudgetCode().getOrg() != null){
orgname = refscheme.getRefferalBudgetCode().getOrg().getOrgName();
	 }
}
%>
<%=orgname%>
</td>
<td>
<% 
String deptname = "";

if(refscheme.getRefferalBudgetCode()!= null){
     if(refscheme.getRefferalBudgetCode().getDepartment() != null){
deptname = refscheme.getRefferalBudgetCode().getDepartment().getDepartmentName();
	 }
}
%>
<%=deptname%>
</td>
<td><%=refscheme.getRefferalSchemeType().getRefferalSchemeName()%></td>
<td>
<% 
String budgetcode = "";

if(refscheme.getRefferalBudgetCode()!= null){
     
budgetcode = refscheme.getRefferalBudgetCode().getRef_budgetCode();
	
}
%>
<%=budgetcode%>
</td>
</tr>


<%

	 }
 }

 %>
 </table>
<%  if(refschemeList != null && refschemeList.size()<1){ %>

 <%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%>  

<%}%>
		<table>  
	<tr>
				<td>
				<%  if(refschemeList != null && refschemeList.size()>0){ %>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}%>
				</td>
				
			</tr>	 		     

</table>
</div>
</html:form>
