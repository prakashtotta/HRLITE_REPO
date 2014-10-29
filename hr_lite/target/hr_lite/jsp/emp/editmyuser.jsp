<%@ include file="../common/include.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<link rel="stylesheet" href="jsp/dhtmlmodal/windowfiles/dhtmlwindow.css" type="text/css" />
<style>
span1{color:#ff0000;}
</style>
<script type="text/javascript" src="jsp/dhtmlmodal/windowfiles/dhtmlwindow.js">

/***********************************************
* DHTML Window Widget- © Dynamic Drive (www.dynamicdrive.com)
* This notice must stay intact for legal use.
* Visit http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>

<bean:define id="cform" name="createUserForm" type="com.form.CreateUserForm" />

<script type="text/javascript">
function openmypage(url){ //Define arbitrary function to run desired DHTML Window widget codes
ajaxwin=dhtmlwindow.open("ajaxbox", "ajax", url, "Organization", "width=450px,height=400px,left=200px,top=100px,resize=1,scrolling=1")
//ajaxwin.onclose=function(){return window.confirm("Close window 3?")} //Run custom code when window is about to be closed
}
</script>
<script language="javascript">


function closewindow(){
	 	   window.top.hidePopWin();

	  
	}

function closewindow2(){
	 parent.parent.GB_hide(); 

	  
	}	
function closewindow1(){
	 	   self.close();

	  
	}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		   parent.parent.GB_hide(); 
	   } 
	}


function deleteuser(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
	   document.createUserForm.action = "user.do?method=delete&userId=<%=cform.getUserId()%>";
   document.createUserForm.submit();

	   } 
	}

function suspend(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.suspendmsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
	   document.createUserForm.action = "user.do?method=suspend&userId=<%=cform.getUserId()%>";
   document.createUserForm.submit();

	   } 
	}	

function activate(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.activatemsg",user1.getLocale())%>");
	  if (doyou == true){
	  // window.top.hidePopWin();
	   document.createUserForm.action = "user.do?method=activate&userId=<%=cform.getUserId()%>";
   document.createUserForm.submit();

	   } 
	}	

function savedata(){
	  document.createUserForm.action = "user.do?method=save";
   document.createUserForm.submit();
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;
	var firstName = document.createUserForm.firstName.value.trim();
	var lastName = document.createUserForm.lastName.value.trim();
	if(firstName == "" || firstName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Fname_req",user1.getLocale())%><BR>";
		showalert = true;
		}
	if(lastName == "" || lastName == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.lname_req",user1.getLocale())%><BR>";
		showalert = true;
		}

	 if (showalert){
    	alert(alertstr);
       return false;
         }
	  document.createUserForm.action = "user.do?method=updatemydetails&userId=<%=cform.getUserId()%>";
   document.createUserForm.submit();
	}



	function opensearchorg(){
  window.open("org.do?method=orgselector&orgId=1", "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function opensearchdepartment(){
	var orgidvalue = document.createUserForm.orgId.value;
	if(orgidvalue == 0){
		alert("<%=Constant.getResourceStringValue("Requisition.selectorg.alert",user1.getLocale())%>");
		return false;
	}
	
  window.open("dept.do?method=departmentlocator&orgId="+orgidvalue, "SearchDeptMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function assignusernamepassword(){
  window.open("user.do?method=assignusernamescr&userId=<%=cform.getUserId()%>", "assignusername","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=400,height=400");
}


function opensearchprojectcode(){
	var orgidvalue = document.createUserForm.orgId.value;
	if(orgidvalue == 0){
		alert("<%=Constant.getResourceStringValue("Requisition.selectdept.alert",user1.getLocale())%>");
		return false;
	}
	
  window.open("lov.do?method=projectcodelocator&orgId="+orgidvalue, "SearchDeptMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	url=url+"&cid="+document.createUserForm.countryId.value;
	
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
				  
		    	  if(name="states")	document.getElementById(name).innerHTML = content;
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
String isuseradded = (String)request.getAttribute("isuseradded");
	
if(isuseradded != null && isuseradded.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.user.User_updated",user1.getLocale())%></td>
			<td><!--  <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>




<html:form action="/user.do?method=logon" >

<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>





		<tr></tr><tr></tr><tr></tr><tr></tr>
	 
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.First_Name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="firstName" size="20" maxlength="50"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.middle_Name",user1.getLocale())%></td>
			<td><html:text property="middleName"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Last_Name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="lastName"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Email",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="emailId" size="20" disabled="true" /></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Office_Phone",user1.getLocale())%></td>
			<td><html:text property="phoneOffice" size="20"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.SSNNumber",user1.getLocale())%></td>
			<td><html:text property="ssnNumber" size="20" maxlength="200"/></td>
		</tr>
			
			
		

				<%
		String orgurl =null;
		
        if(cform.getOrgId() != 0){
String orgname = (cform.getParentOrgName()==null)?"":cform.getParentOrgName();
   orgurl = "<a href='#'  onClick=window.open("+"'"+"org.do?method=editorg&readPreview=2&orgid="+cform.getOrgId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=330"+"'"+")>"+orgname+"</a>";

		}

		%>


				<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%> : </td>
			<td>
			<%=(orgurl == null)?"":orgurl%>
			
			</td>
		</tr>


		
		
			<%
		String depturl =null;
		System.out.println("cform.getDepartmentId()"+cform.getDepartmentId());
        if(cform.getDepartmentId() != 0){

   depturl = "<a href='#'  onClick=window.open("+"'"+"dept.do?method=editDepartmentDetails&readPreview=2&id="+cform.getDepartmentId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=230"+"'"+")>"+cform.getDepartment()+"</a>";

		}

		%>


				<tr>
			<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> : </td>
			<td>
			<%=(depturl == null)?"":depturl%>
			
			</td>

		</tr>

		
		
			<%
		String projectturl =null;
		
        if(cform.getProjectcodeId() != 0){

   projectturl = "<a href='#'  onClick=window.open("+"'"+"projectcodes.do?method=editProjectCodes&readPreview=2&projectCodeId="+cform.getProjectcodeId()+"'"+","+ "'"+"_blank"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=290"+"'"+")>"+cform.getProjectcode()+"</a>";

		}

		%>


				<tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%> : </td>
			<td>
			<%=(projectturl == null)?"":projectturl%>
			
			</td>

		</tr>
        <tr>
			<td><%=Constant.getResourceStringValue("hr.user.Designation",user1.getLocale())%></td>
			<td>
			<%=(cform.getDesignationName()==null)?"":cform.getDesignationName()%>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Role",user1.getLocale())%></td>
			<td>
			<%=(cform.getRoleName()==null)?"":cform.getRoleName()%>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Locale",user1.getLocale())%></td>
			<td>
			<html:select  property="localeId">
			<bean:define name="createUserForm" property="localeList" id="localeList" />

            <html:options collection="localeList" property="localeId"  labelProperty="localeName"/>
			</html:select>
			</td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Timezone",user1.getLocale())%></td>
			<td>
			<html:select  property="timezoneId">
			<bean:define name="createUserForm" property="timezoneList" id="timezoneList" />

            <html:options collection="timezoneList" property="timezoneId"  labelProperty="timezoneName"/>
			</html:select>
			</td>
		</tr>

		

		<tr>
			<td><%=Constant.getResourceStringValue("hr.user.Nationality",user1.getLocale())%></td>
			<td>
			<html:select  property="nationalityId">
			<bean:define name="createUserForm" property="countryList" id="countryList" />

            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
			</html:select>
			</td>
		</tr>

      	<tr>
			<td colspan="2">
	
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>

		</tr>

		
	</table>
</div>

</html:form>



