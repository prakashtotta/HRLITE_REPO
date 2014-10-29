<%@ include file="../common/include.jsp" %>
<%@ page import="com.dao.*"%>
<%@ page import="com.bean.Locale;"%>
  <%

String datepattern = Constant.getValue("defaultdateformat");
 String refuserid = (String)request.getAttribute("refuserid");
 String uuid = (String)request.getAttribute("uuid");
 System.out.println("*** uuid : "+uuid);
 String applicantId = (String)request.getAttribute("applicantId");
 System.out.println("refuserid : "+refuserid);
 Locale locale=null;
 Timezone timezone = null;



if(refuserid != null && !refuserid.equals("null")&& !refuserid.equals("external-jobpost")){
RefferalEmployee refEmp = RefBO.getRefferalEmployee(refuserid);
locale= refEmp.getLocale();
timezone = refEmp.getTimezone();
 }else{
	 String locale_code = (String)session.getAttribute("locale_code");
	 locale= new com.bean.Locale();
	locale.setLocaleCode(locale_code);
 }



String actionName = (String)request.getAttribute("action_name");
String idvalue = (String)request.getAttribute("idvalue");
%>

<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<script language="javascript">


function deleteAttachment(url){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",locale)%>");
	  if (doyou == true){
		
		  document.applicantForm.action = url;
	   document.applicantForm.submit();
	  // window.location.reload();
	 
	   } 
}

function addattachment(url){

	
	var alertstr = "";
  var showalert=false;
	var filename = document.applicantForm.attachmentdata.value;
	var filedetails = document.applicantForm.filedetails.value;
	
	if(filename == "" || filename == null){
     	alertstr = alertstr + "Please select a file.<br>";
		showalert = true;
		}

	if(filedetails == "" || filedetails == null){
     	alertstr = alertstr + "Please add file details.<br>";
		showalert = true;
		}
	
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
 	 document.applicantForm.action = url;
	   document.applicantForm.submit();
}

function closerefresh(){


	//window.opener.location.reload();
	window.opener.location.href= window.opener.location.href+"&fromattachmentpage=yes";
	window.close();

}
function processStateChangeNo() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	
    	} 
    	
  	}
}
</script>

<html:form action="/refferaluser?method=actionattachmentaddscr" enctype="multipart/form-data">
<table valign="top" border="0" width="80%">
<%
String attachurl = "refferaluser.do?method=actionattachmentadd&idvalue="+idvalue+"&action="+actionName+"&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid()+"&refuserid="+refuserid+"&uuid="+uuid;
%>
<tr>
<td>File details : </td><td> <input type="text" name="filedetails" value=""></td>
<td>File : </td><td> <html:file property="attachmentdata"/> </td>
<td><a class="closelink" href="#" onClick="addattachment('<%=attachurl%>');return false"><img src="jsp/images/ButtonInsert.gif" border="0" alt="Add" title="Add" vspace="10px"/></a></td>

</tr>
 </table>


<fieldset><legend><%=Constant.getResourceStringValue("hr.applicant.attachments",locale)%></legend>

	<table border="0" width="100%"> 

		<tr>
<td></td>
<td>
<%
List appattachmentList = form.getActionAttachmentList();

if(appattachmentList != null && appattachmentList.size()>0){
	%>
	<table cellspacing="5">
	<tr>
	<td><%=Constant.getResourceStringValue("hr.applicant.attachment.details",locale)%></td>
	<td><%=Constant.getResourceStringValue("hr.applicant.attachment.name",locale)%></td>	
	<td><%=Constant.getResourceStringValue("hr.applicant.action.added.on",locale)%></td>
	<td><%=Constant.getResourceStringValue("hr.applicant.action.added.by",locale)%></td>
	</tr>

<%
		for(int i=0;i<appattachmentList.size();i++){
		ActionsAttachment actionattach = (ActionsAttachment)appattachmentList.get(i);
		String dleteurl = request.getContextPath()+"/refferaluser.do?method=deleteattachmentpopup&actionname="+actionName+"&applicantId="+form.getApplicantId()+"&idvalue="+actionattach.getIdvalue()+"&uuid="+actionattach.getUuid()+"&refuserid="+refuserid;
		

%>
<tr>
<td><%=(actionattach.getAttahmentdetails()==null)?"":actionattach.getAttahmentdetails()%></td>
<td>
<a href="applicantuserops.do?method=attachmentdetails&uuid=<%=actionattach.getUuid()%>"><%=actionattach.getAttahmentname()%></a>
<a href="#" onClick="deleteAttachment('<%=dleteurl%>')"><img src="jsp/images/delete.gif" border="0" alt="delete attachment" 
title="<%=Constant.getResourceStringValue("Requisition.delete.attachment",locale)%>" height="10"  width="9"/></a>
</td>
<td>
<% if(timezone != null){%>
<%=DateUtil.convertSourceToTargetTimezone(actionattach.getCreatedDate(),timezone.getTimezoneCode(), locale)%>
<%}else{%>
<%=DateUtil.convertDateDisplayFormat(actionattach.getCreatedDate(),locale)%>
<%}%>
</td>
<td>
<%=actionattach.getCreatedBy()%>
</td>
</tr>
<%}%>
</table>
<%}%>

</table>
</fieldset>
<br>
 <a class="closelink" href="#" onClick="closerefresh()" >Close window </a>
<!-- <a href="refferaluser.do?method=backtoadditionaldetails&applicantId=<%=applicantId%>&uuid=<%=uuid%>&refuserid=<%=refuserid%>&saveAndNext=1" >Close window </a>-->

 </html:form>