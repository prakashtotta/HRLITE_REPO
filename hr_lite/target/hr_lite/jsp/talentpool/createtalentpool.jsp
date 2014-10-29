<%@ include file="../common/include.jsp" %>
	<%@ include file="../common/greybox.jsp" %>
	<%@ include file="../common/autosuggesttextarea.jsp" %>
	<%@ include file="../common/autocomplete.jsp" %>

	<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<%
	//response.setHeader("Cache-Control", "no-cache");
			//response.setHeader("Pragma", "no-cache");
			//response.setIntHeader("Expires", 0);
	%>
<html>
<bean:define id="tpform" name="talentPoolForm" type="com.form.TalentPoolForm" /> 

<style type="text/css">
	#myAutoComplete {
		width:5em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}
	#myAutoComplete1 {
		width:5em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}

  
	</style>
<style>
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



<script language="javascript">



function opensearchassignedto(){
		   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=talentpool");
  window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  //window.open("user.do?method=assignedtoselector&boxnumber=intschreassign", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
			 parent.parent.GB_hide();
	   } 
	}

function init(){
setTimeout ( "document.talentPoolForm.talentPoolName.focus(); ", 200 );
}

function savedata(){
	var alertstr = "";
	var showalert=false;	
	var name=document.talentPoolForm.talentPoolName.value.trim();
	var email=document.talentPoolForm.talentPoolemail.value.trim();	
	var assign=document.talentPoolForm.reviewername.value.trim();	
 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/; 
	 if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.talentpool.createpage.mandatorymessage.name",user1.getLocale())%><br>";
		showalert = true;
		}
	
     if(email == "" || email == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("refferal.common.Email_Id_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}else{

    if(reg.test(email) ==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Invalid_Email",user1.getLocale())%><BR>";
		showalert = true;
		}
		}
		if(assign == "" || assign == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.talentpool.createpage.mandatorymessage.assignto",user1.getLocale())%><br>";
		showalert = true;
		}
       if(document.talentPoolForm.talentPoolDesc.value.length > 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	   }

	 if (showalert){
     	alert(alertstr);
        return false;
          }
		 
	  document.talentPoolForm.action = "talentpool.do?method=saveTalentpool"+"&assignedtoid="+document.talentPoolForm.assignedtoid.value.trim()+"&reviewername="+assign;
      document.talentPoolForm.submit();

 
	}

function updatedata(){
	
	var alertstr = "";
	var showalert=false;	
	var name=document.talentPoolForm.talentPoolName.value.trim();
	var email=document.talentPoolForm.talentPoolemail.value.trim();	
	var assign=document.talentPoolForm.reviewername.value.trim();	
  //alert(document.talentPoolForm.assignedtoid.value.trim());

    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/; 
	 if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.talentpool.createpage.mandatorymessage.name",user1.getLocale())%><br>";
		showalert = true;
		}
	
     if(email == "" || email == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("refferal.common.Email_Id_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}else{

			 if(reg.test(email) ==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("hr.Validation.Invalid_Email",user1.getLocale())%><BR>";
		showalert = true;
		}
		}
		if(assign == "" || assign == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.talentpool.createpage.mandatorymessage.assignto",user1.getLocale())%><br>";
		showalert = true;
		}
		if(document.talentPoolForm.talentPoolDesc.value.length > 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	   }


	 if (showalert){
     	alert(alertstr);
        return false;
          }
		 //alert(document.talentPoolForm.assignedtoid.value.trim());
		// alert(document.talentPoolForm.isGroup.value.trim());

	 document.talentPoolForm.action = "talentpool.do?method=updateTalentpool&talentpoolid="+'<bean:write name="talentPoolForm" property="talentPoolId"/>'+"&assignedtoidnew="+document.talentPoolForm.assignedtoid.value.trim()+"&reviewername="+assign;
    document.talentPoolForm.submit();

 
	}
function deletedata(){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  document.talentPoolForm.action = "talentpool.do?method=deleteTalentpool&talentpoolid="+'<bean:write name="talentPoolForm" property="talentPoolId"/>';

		  document.talentPoolForm.submit();
	  }
	
}

function closewindow(){
	parent.parent.GB_hide();

}

function validateUser(){
	//alert(document.scheduleInterviewForm.reviewerid.value);
	document.talentPoolForm.reviewername.value=document.talentPoolForm.reviewernamehidden.value;
}
var ccids="";
var ccnames="";


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
	
	$("#reviewername").autocomplete({
		url: 'jsp/talent/getUserUserGroupData.jsp',
	     minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
	
			onItemSelect: function(item) {
		    
		  
		  var itemvaluedata = ""+item.data;
		  var itemvaluedataname = ""+item.value;
		  
		 
         if(strStartsWith(itemvaluedata,"g")){
			 document.talentPoolForm.reviewername.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 var idval=itemvaluedata;
			 document.talentPoolForm.assignedtoid.value=idval.substring(1,idval.length);
			 document.talentPoolForm.isGroup.value="Y";
			 document.talentPoolForm.reviewernamehidden.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 			 			 
		 }else{
		document.talentPoolForm.assignedtoid.value=item.data;
		 document.talentPoolForm.isGroup.value="N";
		 document.talentPoolForm.reviewername.value=itemvaluedataname.substring(33,itemvaluedataname.length);
         document.talentPoolForm.reviewernamehidden.value=itemvaluedataname.substring(33,itemvaluedataname.length);
		 }
		
		//alert(item.data);
		}
		});
		});

</script>

<%
String saveTalentpool = (String)request.getAttribute("saveTalentpool");
	
if(saveTalentpool != null && saveTalentpool.equals("yes")){
%>
<div class="msg" align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td ><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.talentpool.savemessage",user1.getLocale())%></font>
			 <!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updateTalentpool = (String)request.getAttribute("updateTalentpool");
	
if(updateTalentpool != null && updateTalentpool.equals("yes")){
%>
<div class="msg" align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.talentpool.updatemessage",user1.getLocale())%></font>
			  <!--<a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String deleteTalentpool = (String)request.getAttribute("deleteTalentpool");
	
if(deleteTalentpool != null && deleteTalentpool.equals("yes")){
%>
<div class="msg" align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.talentpool.deletemessage",user1.getLocale())%></font>
			 <!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>
<br><br>

<%}else{%>	
<body class="yui-skin-sam" onload="init()">

<html:form action="/talentpool.do?method=saveTalentpool">


<div align="center" class="div">

	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.talentpool.talentpoolbody.talentpoolname",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="talentPoolName" size="30" maxlength="200"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.variable.variable_description",user1.getLocale())%></td>
			<td><html:textarea property="talentPoolDesc" rows="4" cols="27"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.configutaion.email",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="talentPoolemail" size="30" maxlength="200"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.talentpool.assignto",user1.getLocale())%><font color="red">*</font></td>
			

		
				<td>

                <input type="hidden" name="reviewernamehidden" value="<%=(tpform.getAssigntoName()== null)?"":tpform.getAssigntoName()%>">
                 <input type="text" id="reviewername" name="reviewername" autocomplete="off"   value="<%=(tpform.getAssigntoName()== null)?"":tpform.getAssigntoName()%>" onblur="validateUser()">

				<span id="assignedto"></span>
<a href="#" onClick="opensearchassignedto();return false;"><img src="jsp/images/selector.gif" border="0"/></a>
<input type="hidden" name="assignedtoid" value="<%=tpform.getAssignedtoid()%>"/>
<input type="hidden" name="isGroup" value="<%=tpform.getIsGroup()%>"/>




				</td>
		</tr>
<tr>
			<td><%=Constant.getResourceStringValue("admin.ProjectCode.orgname",user1.getLocale())%></td>
			
			<td>
			<% if(tpform.getTalentPoolId() ==  Common.DEFAULT_TALENT_POOL_ID){  %>
			<html:select disabled="true" property="orgId">
			<bean:define name="talentPoolForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
			<%}else{%>
			<html:select property="orgId">
			<bean:define name="talentPoolForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
			<%}%>

		
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.talentpool.smtpserver",user1.getLocale())%></td>
			<td><html:text property="smtpserver" size="30" maxlength="500"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.talentpool.smtpport",user1.getLocale())%></td>
			<td><html:text property="smptoport" size="30" maxlength="200"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.talentpool.smtpuser",user1.getLocale())%></td>
			<td><html:text property="smtpuser" size="30" maxlength="500"/></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.talentpool.smtppassword",user1.getLocale())%></td>
			<td><html:text property="smtppassword" size="30" maxlength="200"/></td>
		</tr>

		</table>
	<br>
	  <table border="0" width="100%">

	
		<tr>
			<td><%if (tpform.getTalentPoolId()!=0){%>

			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
		
		</table>
		
	</div>	


</html:form>
	
</body>

		<%}%>