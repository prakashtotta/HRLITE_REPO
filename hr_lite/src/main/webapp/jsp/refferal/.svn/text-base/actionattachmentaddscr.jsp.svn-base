<%@ include file="../common/include.jsp" %>
  <%

String datepattern = Constant.getValue("defaultdateformat");
  RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
com.bean.Locale locale=null;
com.bean.Timezone timezone = null;
if(user1 != null){
locale = user1.getLocale();
timezone = user1.getTimezone();
}


String actionName = (String)request.getAttribute("action_name");
String idvalue = (String)request.getAttribute("idvalue");
%>

<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<script language="javascript">
function closerefresh(){
	
	//window.opener.location.reload(true);
	window.opener.location.href= window.opener.location.href+"&fromattachmentpage=yes";

  	window.close();
	  
	
}

function processOrg() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlOrg(spanElements);
			
		    //onOtherStateSel();
    	} 
    	//document.getElementById("loading3").style.visibility = "hidden";	
  	}
}
function replaceExistingWithNewHtmlOrg(newTextElements){
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
				  
		    	  if(name="prevorgdetails")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

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


</script>

<html:form action="/applicantoffer.do?method=actionattachmentaddscr" enctype="multipart/form-data">
<table valign="top" border="0" width="80%">
<%
String attachurl = "applicantoffer.do?method=actionattachmentadd&idvalue="+idvalue+"&action="+actionName+"&applicantId="+form.getApplicantId()+"&secureid="+form.getUuid();
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
		String dleteurl = request.getContextPath()+"/applicantoffer.do?method=deleteattachmentpopup&actionname="+actionName+"&applicantId="+form.getApplicantId()+"&idvalue="+actionattach.getIdvalue()+"&uuid="+actionattach.getUuid();
		

%>
<tr>
<td><%=(actionattach.getAttahmentdetails()==null)?"":actionattach.getAttahmentdetails()%></td>
<td>
<a href="applicantuserops.do?method=attachmentdetails&uuid=<%=actionattach.getUuid()%>"><%=actionattach.getAttahmentname()%></a>
<a href="#" onClick="deleteAttachment('<%=dleteurl%>')"><img src="jsp/images/delete.gif" border="0" alt="delete attachment" 
title="<%=Constant.getResourceStringValue("aquisition.applicant.delete_organization",locale)%>" height="10"  width="9"/></a>
</td>
<td>
<%=DateUtil.convertSourceToTargetTimezone(actionattach.getCreatedDate(),timezone.getTimezoneCode(), locale)%>
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
<a class="closelink" href="#" onClick="closerefresh()" >close window </a>

 </html:form>