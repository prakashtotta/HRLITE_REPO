<%@ include file="../common/include.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>
<%@ page import="com.form.*" %>
<%@ page import="com.bean.onboard.*" %>
<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="onBoardingTaskDefiForm" name="onBoardingTaskDefiForm" type="com.form.OnBoardingTaskDefiForm" />

<style>
span1{color:#ff0000;}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
</style>

<script language="javascript">

function userDetailsOrGroup(id,isgroup){
	var url = "";
	if(isgroup == 'Y'){
		url = "<%=request.getContextPath()%>/usergroup.do?method=editusergroup&readPreview=2&usergroupid="+id;
	}else{
			url = "<%=request.getContextPath()%>/user.do?method=edituser&readPreview=2&userId="+id;
		}
	
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	window.open(url, 'UserDetails','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600');
}


function addAttributes(){
    var alertstr = "";
	var taskdefid ="<%=onBoardingTaskDefiForm.getTaskdefid()%>";
	if(taskdefid=="0"){
		alert("First save On Boarding task defination");
		return false;
	}


	var Attribute_name = document.onBoardingTaskDefiForm.atrributes.value;
	var Is_mandatory = document.onBoardingTaskDefiForm.isMandatory.value;
	var showalert=false;

if(Attribute_name == "" || Attribute_name == null){
 	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.validation.Entertheattributename",user1.getLocale())%><BR>";
	showalert = true;
	}


 if (showalert){
 	alert(alertstr);
    return false;
      }


//var url="OnBoardingTaskDefi.do?method=saveAttributedetails&taskdefiId=<%=onBoardingTaskDefiForm.getTaskdefid()%>&Attribute_name="+Attribute_name+"&Is_mandatory="+Is_mandatory+";
}

function deleteAttributes(id){

	var url="OnBoardingTaskDefi.do?method=deleteAttribute&Attributeid="+id;


//url = encodeURIComponent(url);


      //Do the AJAX call
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
  	}
    


       setTimeout("retrieveAttributes()",200);


}
  	
function opensearchassignedtohiring(){
	   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=addprimaryowner");
	  window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

	 // window.open("user.do?method=assignedtoselector&boxnumber=addhighiringmanagerreq", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
	}

function opensearchwatchlist(){
	   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=watchlistselector");
window.open(url, "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

//window.open("user.do?method=watchlistselector", "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function validateUserhm(){

	
	//if(document.onBoardingTaskDefiForm.primaryownernamehidden.value == "" || document.onBoardingTaskDefiForm.primaryownernamehidden.value == null){
	//	document.onBoardingTaskDefiForm.primaryOwnerName.value = document.onBoardingTaskDefiForm.primaryOwnerName.value;
	//}else{
		document.onBoardingTaskDefiForm.primaryOwnerName.value=document.onBoardingTaskDefiForm.primaryownernamehidden.value;

	//}
}

function deletedatasure(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("refferal.common.delete",user1.getLocale())%>");
	  if (doyou == true){
		  //self.parent.location.reload();
			// parent.parent.GB_hide();
			deletedata();
	   } 
	}
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.button.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}
function discard1(){
	 //self.parent.location.reload();
	 parent.parent.GB_hide();
	}

function savedata(){
	var alertstr = "";
	var showalert=false;
    var numbers=/^[0-9]+$/;

	 var taskdefname=document.onBoardingTaskDefiForm.taskName.value.trim();
	 var desc=document.onBoardingTaskDefiForm.taskDesc.value.trim();
	 
		if(taskdefname == "" || taskdefname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.validation.OnBoardtaskDefinationNameisMandatory",user1.getLocale())%><br>";
		//document.forms[0].taskName.focus();  

		showalert = true;
		}
		var ownername=document.onBoardingTaskDefiForm.primaryOwnerName.value;
		if(ownername == null || ownername ==""){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.validation.owner_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}	
       	var days=document.onBoardingTaskDefiForm.noofdays.value.trim();
        if(numbers.test(days)==false){
        alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.validation.Pleaseenterdaysinnumber",user1.getLocale())%><br>";
        showalert = true;
        }
		if(desc.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
		}

      if (showalert){
     	alert(alertstr);
        return false;
          }

	document.onBoardingTaskDefiForm.action = "OnBoardingTaskDefi.do?method=saveOnBoardTaskDefi&readPreview=2";
	document.onBoardingTaskDefiForm.submit();
	//self.parent.location.reload();
	 
	}
function updatedata(){
     var alertstr = "";
	var showalert=false;
    var numbers=/^[0-9]+$/;

	 var taskdefname=document.onBoardingTaskDefiForm.taskName.value.trim();
	 var desc=document.onBoardingTaskDefiForm.taskDesc.value.trim();
		if(taskdefname == "" || taskdefname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.validation.OnBoardtaskDefinationNameisMandatory",user1.getLocale())%><br>";
		showalert = true;
		
		
		}
		var ownername=document.onBoardingTaskDefiForm.primaryOwnerId.value;
	
		if(ownername == 0){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.validation.PrimaryownernameisMandatory",user1.getLocale())%><br>";
		showalert = true;
		}	
		
		var ownername=document.onBoardingTaskDefiForm.primaryOwnerName.value;
		if(ownername == null || ownername ==""){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.validation.owner_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}	
       var days=document.onBoardingTaskDefiForm.noofdays.value.trim();
        if(numbers.test(days)==false){
        alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.validation.Pleaseenterdaysinnumber",user1.getLocale())%><br>";
        showalert = true;
        }
		if(desc.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
		}

      if (showalert){
     	alert(alertstr);
        return false;
          }

	document.onBoardingTaskDefiForm.action = "OnBoardingTaskDefi.do?method=updateOnBoardingTaskDefi&readPreview=2&id="+'<bean:write name="onBoardingTaskDefiForm" property="taskdefid"/>';
	document.onBoardingTaskDefiForm.submit();
	
	}

function deletedata(){
	document.onBoardingTaskDefiForm.action = "OnBoardingTaskDefi.do?method=deleteOnBoardingTaskDefi&readPreview=1&id="+'<bean:write name="onBoardingTaskDefiForm" property="taskdefid"/>';
    document.onBoardingTaskDefiForm.submit();
	
}
function retrieveAttributes() {
    document.getElementById("loading3").style.visibility = "visible";
	var url="OnBoardingTaskDefi.do?method=getAttributedetails&attid=<%=onBoardingTaskDefiForm.getTaskdefid()%>";

  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processOrg;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processOrg;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
	
}
function processOrg() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlAttribute(spanElements);
			
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading3").style.visibility = "hidden";	
  	}
}
function replaceExistingWithNewHtmlAttribute(newTextElements){
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
				  
		    	  if(name="attributeDetails")
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

function addElement() {
 
  var ni = document.getElementById('myDiv');
  var numi = document.getElementById('theValue');
  var num = (document.getElementById('theValue').value -1)+ 2;
  if(num < 50){
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my'+num+'Div';
  newdiv.setAttribute('id',divIdName);
  var spanstart = '<span id="compspan_'+num+'">';
  var attributes1 = '<input type="text" size = "50" maxlength= "100" name="attributes_'+num+'"/>';
  var attribteid1 = '<input type="hidden"    name="taskattid_'+num+'"/>';
  var mandatory1 = '<input type="checkbox"  checked   name="mandatory_'+num+'"/>';
  var spanend = '</span>'; 
  var spaces = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
  newdiv.innerHTML = ''+spanstart+''+attributes1+''+attribteid1+''+spaces+''+spaces+''+mandatory1+''+spanend+''+spaces+''+spaces+'<a href=\'#\' onclick=\'removeElement('+divIdName+')\'><img src="jsp/images/delete.gif" border="0" alt="delete attributes" title="delete attributes" height="14"  width="19"/></a>';
  ni.appendChild(newdiv);
  } else {
	  alert("maximum level exceed");
  }
}

function removeElement(divNum) {
var d = document.getElementById('myDiv');
d.removeChild(divNum);
}

function validateattribute(){
alert("dd");
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


    $("#primaryOwnerName").autocomplete({
		url: 'jsp/talent/getUserUserGroupData.jsp',
	     minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
	
			onItemSelect: function(item) {
		    
		  
		  var itemvaluedata = ""+item.data;
		  var itemvaluedataname = ""+item.value;
		  
		 
         if(strStartsWith(itemvaluedata,"g")){
			 document.onBoardingTaskDefiForm.primaryOwnerName.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 var idval=itemvaluedata;
			 document.onBoardingTaskDefiForm.primaryOwnerId.value=idval.substring(1,idval.length);
			 document.onBoardingTaskDefiForm.isGroup.value="Y";
			 document.onBoardingTaskDefiForm.primaryownernamehidden.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 			 			 
		 }else{
		document.onBoardingTaskDefiForm.primaryOwnerId.value=item.data;
		 document.onBoardingTaskDefiForm.isGroup.value="N";
		 document.onBoardingTaskDefiForm.primaryOwnerName.value=itemvaluedataname.substring(33,itemvaluedataname.length);
         document.onBoardingTaskDefiForm.primaryownernamehidden.value=itemvaluedataname.substring(33,itemvaluedataname.length);
		 }
		
		//alert(item.data);
		}
		});


     

});
function init(){
	setTimeout ( "document.onBoardingTaskDefiForm.taskName.focus(); ", 200 );
	//document.roleForm.roleCode.focus();
	}
	</script>

<body class="yui-skin-sam" onLoad="init()" >

<%
String saveOnBoardtaskDefination = (String)request.getAttribute("saveOnBoardtaskDefination");
	
if(saveOnBoardtaskDefination != null && saveOnBoardtaskDefination.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td>
			<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
			<font color="white"><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.savemessage",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="discard1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updateOnBoardtaskDefination = (String)request.getAttribute("updateOnBoardtaskDefination");
	
if(updateOnBoardtaskDefination != null && updateOnBoardtaskDefination.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td>
			<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
			<font color="white"><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.updatemessage",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="discard1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String deleteOnBoardtaskDefination = (String)request.getAttribute("deleteOnBoardtaskDefination");
	
if(deleteOnBoardtaskDefination != null && deleteOnBoardtaskDefination.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td>
			<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
			<font color="white"><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.createpage.deletemessage",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="discard1()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}else{%>

<br>
<html:form action="/OnBoardingTaskDefi.do?method=saveOnBoardTaskDefi">

<% if(onBoardingTaskDefiForm.getReadPreview().equals("3")){%>

	<div align="center" class="div">
	
	
	
	<br>
	
			<fieldset><legend><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.defidetailslist",user1.getLocale())%></legend>
		
		
		<tr>
			<br>
			<tr>
				<td></td>
				<td></td>
			</tr>
		<fieldset><legend><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.selectorpage.view.TaskDefinationDetails",user1.getLocale())%></legend>
		<table border="0" width="100%">
		
			<td WIDTH=400><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.selectorpage.OnBoardtaskDefinationName",user1.getLocale())%><font color="red">*</font></td>
			<td><%=onBoardingTaskDefiForm.getTaskName()%></td>
		</tr>		
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.desc",user1.getLocale())%></td>
			<td><%=onBoardingTaskDefiForm.getTaskDesc()%></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.PrimaryOwnerName",user1.getLocale())%><font color="red">*</font></td>
		

       <%if(!StringUtils.isNullOrEmpty(onBoardingTaskDefiForm.getIsGroup()) && onBoardingTaskDefiForm.getIsGroup().equals("Y")){%>
<td ><img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=onBoardingTaskDefiForm.getPrimaryOwnerId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=onBoardingTaskDefiForm.primaryOwnername%></a> </td> 
<%}else{%>
<td ><img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=onBoardingTaskDefiForm.getPrimaryOwnerId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=onBoardingTaskDefiForm.primaryOwnername%></a> </td>
<%}%>

		
	</tr>
	 <tr>
			<td><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.listpage.Eventtype",user1.getLocale())%><font color="red">*</font> </td>
			<td><%=onBoardingTaskDefiForm.getEventType()%></td>
     </tr>
	 <tr>
            <td><%=Constant.getResourceStringValue("aquisition.applicant.days",user1.getLocale())%></td>
            <td><%=onBoardingTaskDefiForm.getNoofdays()%></td>
	 </tr>
			      
		
		<tr><td></td><td></td></tr>
   </table>
</fieldset><br>
	<fieldset><legend><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.selectorpage.AttributesDetails",user1.getLocale())%></legend>
<table border="0" width="100%">

			<tr>
				<td></td>
				<td></td>
			</tr>
<tr>
<td>
<%
List attributeList = onBoardingTaskDefiForm.getAttributeList();
%>	
<%if(attributeList != null){ %>
	<input type="hidden" value="<%=attributeList.size()%>" id="theValue" />
	<%}else{ %>
	<input type="hidden" value="" id="theValue" />
	<%} %>


 
</td>

</tr>

<tr>
<td width="900"><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.selectorpage.Attributesname",user1.getLocale())%></td>
<td ><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.selectorpage.Ismandatory",user1.getLocale())%></td>
</tr>



<td>
	
<%
int k2=1;
if(attributeList != null){
for(int i=0;i<attributeList.size();i++){
	OnBoardingTaskAttributes jcomp = (OnBoardingTaskAttributes)attributeList.get(i);
     String tdiv = "my"+k2+"Div";
	 String tspancomp = "compspan_"+k2;
	 String tcompname = "attributes_"+k2;
      String tcompmandatory = "mandatory_"+k2;
	  String checkedmancomp = (jcomp.getIsMandatory().equals("Y"))?"checked":"";
      String valismandatory="No";
	  if(jcomp.getIsMandatory().equals("Y")){
       valismandatory="Yes";
	  }
	  //String spaces = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
	//String tcompimportance = "accimportance_"+k2;
    String valattribute=jcomp.getAttribute();
	String tempdivappcomp = "<div id='"+tdiv+"'"+">"+
		"<span id='"+tspancomp+"'"+">"+"<tr>"+"<td>"+valattribute+"</td>"+"<td width=800>"+valismandatory+"</td>"+"</tr>"
		
		+
        
       	
	"</div>";
	k2++;

%>

<%=tempdivappcomp%>

<%}}%>

</div>
</td>
</tr>

		</table>
   </fieldset>
   </fieldset>
</fieldset>

<%}else {%>


<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
	
	<tr>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.selectorpage.OnBoardtaskDefinationName",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="taskName" size="40" maxlength="500"/></td>
		</tr>
		
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.Desc",user1.getLocale())%></td>
			<td><html:textarea property="taskDesc" cols="40" rows="5" /></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.PrimaryOwnerName",user1.getLocale())%><font color="red">*</font></td>
		<td>	<input type="hidden" name="primaryownernamehidden">
                
                 <input type="text" id="primaryOwnerName" name="primaryOwnername" autocomplete="off"
				 value="<%=(onBoardingTaskDefiForm.getPrimaryOwnername()==null)?"":onBoardingTaskDefiForm.getPrimaryOwnername()%>" onblur="validateUserhm()">
				
				<span id="assignedto"></span>
<a href="#" onClick="opensearchassignedtohiring()"><img src="jsp/images/selector.gif" border="0"/></a>

<input type="hidden" name="primaryOwnerId" value="<%=onBoardingTaskDefiForm.getPrimaryOwnerId()%>">
<input type="hidden" name="isGroup" value="<%=onBoardingTaskDefiForm.getIsGroup()%>">



	</td>
	</tr>
	 <tr>
			<td><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.listpage.Eventtype",user1.getLocale())%><font color="red">*</font> </td>
			<td>
			<select name="eventType">
			<%System.out.println("onBoardingTaskDefiForm.getAppliedcri() : "+onBoardingTaskDefiForm.getAppliedcri()); 
			if(!StringUtils.isNullOrEmpty(onBoardingTaskDefiForm.getAppliedcri()) && onBoardingTaskDefiForm.getAppliedcri().equals("BEFORE")){%>
  			<option value="BEFORE" selected="yes" ><%=Constant.getResourceStringValue("Requisition.brfore",user1.getLocale())%></option>
  			<%}else {%>
			<option value="BEFORE"><%=Constant.getResourceStringValue("Requisition.brfore",user1.getLocale())%></option>
			 <%}%>
  		  	<% if(!StringUtils.isNullOrEmpty(onBoardingTaskDefiForm.getAppliedcri()) && onBoardingTaskDefiForm.getAppliedcri().equals("AFTER")){%>
 			 <option value="AFTER" selected="yes" ><%=Constant.getResourceStringValue("Requisition.after",user1.getLocale())%></option>
  			<%}else {%>
			<option value="AFTER"><%=Constant.getResourceStringValue("Requisition.after",user1.getLocale())%></option>
		  <%}%>
 
		</select>        
		<html:text property="noofdays" maxlength="2"/><%=Constant.getResourceStringValue("aquisition.applicant.days",user1.getLocale())%>
		</td>
		
		</tr> 
		<tr><td></td><td></td></tr>

<tr>
<td>
<%
List attributeList = onBoardingTaskDefiForm.getAttributeList();
%>	
<%if(attributeList != null){ %>
	<input type="hidden" value="<%=attributeList.size()%>" id="theValue" />
	<%}else{ %>
	<input type="hidden" value="" id="theValue" />
	<%} %>
<a href="javascript:;"onclick="addElement();"><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.selectorpage.AddAttributes",user1.getLocale())%>
</a>

 
</td>
<td></td>
</tr>
<tr><td><th><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.selectorpage.Attributesname",user1.getLocale())%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Constant.getResourceStringValue("admin.OnBoardTaskDef.selectorpage.Ismandatory",user1.getLocale())%></th></td><td></td></tr>
<tr>
<td>

</td>
<td>
	<div id="myDiv">
<%
int k2=1;
if(attributeList != null){
for(int i=0;i<attributeList.size();i++){
	OnBoardingTaskAttributes jcomp = (OnBoardingTaskAttributes)attributeList.get(i);

     String tdiv = "my"+k2+"Div";
	 String tspancomp = "compspan_"+k2;
	 String tcompname = "attributes_"+k2;
      String tcompmandatory = "mandatory_"+k2;
	  String checkedmancomp = (jcomp.getIsMandatory().equals("Y"))?"checked":"";
	  //String spaces = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
	//String tcompimportance = "accimportance_"+k2;
	String tempdivappcomp = "<div id='"+tdiv+"'"+">"+
		"<span id='"+tspancomp+"'"+">"+
		"<input type=\"text\"  size = \"50\" maxlength= \"100\" value='"+jcomp.getAttribute()+"'  name='"+tcompname+"'"+"/>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
		"<input type=\"checkbox\"" +checkedmancomp+ " name='"+tcompmandatory+"'"+"/>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
        
       	"<a href=\'#\' onclick=\'removeElement("+tdiv+")\'><img src=\"jsp/images/delete.gif\" border=\"0\" alt=\"delete attributes\" title=\"delete attributes\" height=\"14\"  width=\"19\"/></a>"+  
	"</div>";
	k2++;

%>

<%=tempdivappcomp%>

<%}}%>

</div>
</td>
</tr>

<tr><td></td><td></td></tr>
<tr><td></td><td></td></tr>
<tr><td></td><td></td></tr>
	<tr><td><% if(onBoardingTaskDefiForm.getReadPreview().equals("2")){%>
	        <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedatasure()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
	      <%} else {%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			
			
		<%}%>
			
			<td></td>
		</tr>

		</table>
</div>
<%}%>
</html:form>

	<%}%>	
	
