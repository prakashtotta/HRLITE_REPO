<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ taglib uri="http://paginationtag.miin.com" prefix="pagination-tag"%>
<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.onboard.*" %>
<%@ page import="com.form.*" %>

<bean:define id="createorgemailtempform" name="organizationEmailTemplateForm" type="com.form.OrganizationEmailTemplateForm" />
<%

String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String mappingsave = (String)request.getAttribute("mappingsave");
System.out.println("<<< organizationemailfunctionmappingNew.jsp >>>");

String fn_name = (String)request.getAttribute("fn_name");
System.out.println("<<< fn_name >>>"+fn_name);
String emailtemplateid = (String)request.getAttribute("emailtemplateid");
System.out.println("<<< emailtemplateid >>>"+emailtemplateid);
String shoeEmailTemplate = (String)request.getAttribute("shoeEmailTemplate");


%>
<style>
span1{color:#ff0000;}
</style>
<html>

 <script type="text/javascript" src="jsp/js/nicEdit.js"></script>
 <script type="text/javascript">
 //<![CDATA[
 bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
 //]]>  
 </script>


<script language="javascript">
function opensearchorganizationemailtemplate(i){
  window.open("orgemailtemplate.do?method=orgEmaillistselector&emailvalue=emailtemplate_"+i+"&emailtemplateidval=emailtemplateId_"+i+"&emailtemplatenameval=emailtemplatenameval_"+i, "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
  //alert(i);
}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		//  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}


function savedata(){
	
 document.organizationEmailTemplateForm.action = "orgemailtemplate.do?method=saveOrganizationEmailTemplateNew&readPreview=2";
 document.organizationEmailTemplateForm.submit();

 
	}

function updatedata(){
	
	  
	  document.organizationEmailTemplateForm.action = "orgemailtemplate.do?method=updateOrganizationEmailTemplate&readPreview=2&id="+'<bean:write name="organizationEmailTemplateForm" property="orgemailtemplid"/>';
 document.organizationEmailTemplateForm.submit();



 
	}


	function closewindow(){
		// self.parent.location.reload();
		// parent.parent.GB_hide();
}

function loadEmailtemplate(){
	document.getElementById('showemailtemplate').style.display = 'block';
	  var url = "<%=request.getContextPath()%>";
	  var eventid =document.organizationEmailTemplateForm.emailEventId.value;
	  var orgid =document.organizationEmailTemplateForm.orgId.value;

	if(eventid != ""){
		

	    document.organizationEmailTemplateForm.action ="orgemailtemplate.do?method=showOrgEmailTemplate&emailEventId="+document.organizationEmailTemplateForm.emailEventId.value+"&orgid="+orgid;
	    document.organizationEmailTemplateForm.submit();
	}else{
		document.getElementById('showemailtemplate').style.display = 'none';
	}

	
	//document.getElementById('showemailtemplate').style.display = 'none';
    //   document.organizationEmailTemplateForm.action ="orgemailtemplate.do?method=loadEmailtemplateNew&readPreview=1";
	//    document.organizationEmailTemplateForm.submit();

}
function showOrgEmailTemplate(){
	
	document.getElementById('showemailtemplate').style.display = 'block';
	  var url = "<%=request.getContextPath()%>";
	  var eventid =document.organizationEmailTemplateForm.emailEventId.value;
	  var orgid =document.organizationEmailTemplateForm.orgId.value;

	if(eventid != ""){
		

	    document.organizationEmailTemplateForm.action ="orgemailtemplate.do?method=showOrgEmailTemplate&emailEventId="+document.organizationEmailTemplateForm.emailEventId.value+"&orgid="+orgid;
	    document.organizationEmailTemplateForm.submit();
	}else{
		document.getElementById('showemailtemplate').style.display = 'none';
	}
	
}
function completeajx(){
	  $.unblockUI();
}
function PreviewMessage(emailtemplateid){

	var url ="emailtemplate.do?method=previewMessage&emailtemplateid="+emailtemplateid;
	window.open(url, "PreviewMessage","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
	
}
function messageret1(){
	
}
function saveemailtemplate(){
	var content = nicEditors.findEditor('emailmessage').getContent();
	document.organizationEmailTemplateForm.emailmessage.value=content;
	var emailtemplateId="<%=createorgemailtempform.getEmailtemplateId()%>";
	var orgId=document.organizationEmailTemplateForm.orgId.value;
	var emailEventId=document.organizationEmailTemplateForm.emailEventId.value;
	var orgemailtemplid =document.organizationEmailTemplateForm.orgemailtemplid.value;
	
	if(emailtemplateId == 0){
			
		    document.organizationEmailTemplateForm.action ="orgemailtemplate.do?method=saveOrgemailtemplateNew&orgId="+orgId+"&emailEventId="+emailEventId;
		    document.organizationEmailTemplateForm.submit();
	}else{

		    document.organizationEmailTemplateForm.action ="orgemailtemplate.do?method=updateOrgemailtemplateNew&orgemailtemplid="+orgemailtemplid+"&orgId="+orgId+"&emailEventId="+emailEventId+"&emailtemplateId="+emailtemplateId;
		    document.organizationEmailTemplateForm.submit();
	}


	
/*
	  var url = "emailtemplate.do?method=saveemailtemplateNew";
	  
	  dataString = $("#showtemplate").serialize();
	$.ajax({
		type: 'POST',
	  url: url,
	  data: dataString,
	  success: function(data){
		
	  $('#showemailtemplate').html(data);
		//completeajx();
	  }
	});
	*/
}




</script>





<body class="yui-skin-sam">

<html:form action="/orgemailtemplate.do?method=saveOrganizationEmailTemplate" styleId="showtemplate">

		
		<% if(! StringUtils.isNullOrEmpty(mappingsave) && mappingsave.equals("yes")){
		%>
		<table>
			<tr>
				<td align="left">
				<font color="green">Email function mapping save successfully</font>
				</td>			
			</tr>
		</table><br><br>
		<%} %>
<div class="div">
	<table border="0" width="1000">
	<font color = red ><html:errors /> </font>
	

	



	<tr>
			<td width="100"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			
			<td>
			
			<html:select property="orgId"  onchange="loadEmailtemplate()">
			<bean:define name="organizationEmailTemplateForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			
		
			</td>
			<!--  <td ><input type="button" name="save" value="Save" onClick="savedata()" /></td>-->
		</tr>
		<tr></tr><tr></tr><tr></tr><tr></tr>
		<tr>
			<td ><%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.function",user1.getLocale())%></td>
			<td >

		
				<html:select property="emailEventId" onchange="showOrgEmailTemplate()">
				<option value=""></option>
				<bean:define name="organizationEmailTemplateForm" property="emailEventCodeList" id="emailEventCodeList" />
	
	            <html:options collection="emailEventCodeList" property="emailEventId"  labelProperty="eventName"/>
				</html:select>&nbsp;
			
		
			</td>
		</tr>
		<tr>
       <td></td><td></td>
        
		</tr>

	   		
		</table>
	

	


<span id="showemailtemplate">
<%if(shoeEmailTemplate.equals("yes")){ %>
 <table border="0" width="1000">
	<tr>
	<td>	<font color = red ><html:errors /> </font>
			<table border="0" width="700">
			
			<tr>
					<!-- <td><%=Constant.getResourceStringValue("admin.EmailTemplate.Emailtemplate",user1.getLocale())%></td> -->
					<td></td>
				</tr>
			 
				<tr>
					<td width="100"><%=Constant.getResourceStringValue("admin.EmailTemplate.Templatename",user1.getLocale())%><font color="red">*</font></td>
					<td>
					<html:text property="emailtemplatename" size="80" maxlength="500"/>
					<html:hidden property="orgemailtemplid"/>
					</td>
				</tr>
				<tr></tr><tr></tr><tr></tr><tr></tr>
				<tr>
					<td><%=Constant.getResourceStringValue("admin.EmailTemplate.Template_Subject",user1.getLocale())%><font color="red">*</font></td>
					<td>
					<html:text property="emailSubject" size="80" maxlength="500"/></td>
				</tr>
				<tr></tr><tr></tr><tr></tr><tr></tr>
				<tr>
					<td><%=Constant.getResourceStringValue("admin.EmailTemplate.Template",user1.getLocale())%></td>
					<td >
					<html:textarea property="emailmessage" styleId="emailmessage" rows="20" cols="90" value="<%=createorgemailtempform.getEmailtemplateData()%>"> </html:textarea>
		
			  
					
					</td>
				</tr>
		
			</table>
</td>
<% if(! StringUtils.isNullOrEmpty(fn_name) ){%>
<td width="200">
<table >
		<tr>
			<td valign="top">
			<b><%=Constant.getResourceStringValue("admin.EmailTemplate.available.tags",user1.getLocale())%></b>
			</td>
		</tr>
		<tr>
			<td >
			<%=com.util.MergeUtil.getAvailableTags(fn_name)%>
			</td>
		</tr>

		
</table>
</td>

<%}%>
</tr>
</table>

	
<table>

		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

			<%if(!StringUtils.isNullOrEmpty(emailtemplateid) ){ %>
			<td><input type="Button" title="<%=Constant.getResourceStringValue("user.massemail.preview",user1.getLocale())%>" value="<%=Constant.getResourceStringValue("user.massemail.preview",user1.getLocale())%>" onclick="PreviewMessage('<%=emailtemplateid%>')"  class="button"></td>
			<%} %>
			<td>
			<button type="button" onclick="saveemailtemplate()" class="button"><%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%></button>&nbsp;
			</td>
			<td></td>
		</tr>
</table>

<%} %>
</span>



</html:form>





