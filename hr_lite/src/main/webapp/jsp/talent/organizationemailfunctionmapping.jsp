<%@ include file="../common/include.jsp" %>
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
<%@ include file="../common/autocomplete.jsp" %>
<%@ page import="com.form.*" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String mappingsave = (String)request.getAttribute("mappingsave");

%>
<style>
span1{color:#ff0000;}
</style>
<html>
<bean:define id="createorgemailtempform" name="organizationEmailTemplateForm" type="com.form.OrganizationEmailTemplateForm" />



<script language="javascript">
function opensearchorganizationemailtemplate(i){
  window.open("orgemailtemplate.do?method=orgEmaillistselector&emailvalue=emailtemplate_"+i+"&emailtemplateidval=emailtemplateId_"+i+"&emailtemplatenameval=emailtemplatenameval_"+i, "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
  //alert(i);
}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}


function savedata(){
	//var i=0;
//var countvalue=document.organizationEmailTemplateForm.countlist.value;
//for(i=1; i<countvalue; i++){
 document.organizationEmailTemplateForm.action = "orgemailtemplate.do?method=saveOrganizationEmailTemplate&readPreview=2";
 document.organizationEmailTemplateForm.submit();
//alert(i);
//}
 
	}

function updatedata(){
	
	  document.organizationEmailTemplateForm.action = "orgemailtemplate.do?method=updateOrganizationEmailTemplate&readPreview=2&id="+'<bean:write name="organizationEmailTemplateForm" property="orgemailtemplid"/>';
 document.organizationEmailTemplateForm.submit();



 
	}


	function closewindow(){
		 self.parent.location.reload();
		 parent.parent.GB_hide();
}

function loadEmailtemplate(){
       document.organizationEmailTemplateForm.action ="orgemailtemplate.do?method=loadEmailtemplate&readPreview=1";
	    document.organizationEmailTemplateForm.submit();

}

</script>



<body class="yui-skin-sam">
<br>
<b><%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.heading",user1.getLocale())%></b><br><br>
<html:form action="/orgemailtemplate.do?method=saveOrganizationEmailTemplate">


<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
	<input type="hidden" name="countlist" id="countlist" value='<%=createorgemailtempform.getOrgemailtempList().size()%>'/>

		
	
	



	<tr>
			<td width=100><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			
			<td width=310>
			
			<html:select property="orgId" onchange="loadEmailtemplate()">
			<bean:define name="organizationEmailTemplateForm" property="organizationList" id="organizationList" />

            <html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>&nbsp;
			
		
			</td>
			<td ><input type="button" name="save" value="Save" onClick="savedata()" /></td>
		</tr>
		
		<tr>
       <td></td><td></td>
        
		</tr>

	   		
		</table>
		</div>
		<br>
		<% if(! StringUtils.isNullOrEmpty(mappingsave) && mappingsave.equals("yes")){
		%>
		<table>
			<tr>
				<td align="left">
				<font color="green">Email function mapping save successfully</font>
				</td>			
			</tr>
		</table>
		<%} %>
		<br><br><br>





<table border="0" width="100%" cellspacing="4" cellpadding="4">

<tr bgcolor="#f3f3f3"><td WIDTH=400><b>Function name</b> </td>
<td WIDTH=400><b>EmailTemplate</b></td>
<td WIDTH=200><b>Status</b></td>
<td WIDTH=400><b>Available tags</b></td>
</tr>

<% 
    List orgemailtemplateLIst = createorgemailtempform.getOrgemailtempList();
%>

<%
if(orgemailtemplateLIst != null){

	for(int i=0;i<orgemailtemplateLIst.size();i++){

	OrganizationEmailTemplate emailevents = (OrganizationEmailTemplate)orgemailtemplateLIst.get(i);
    String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";

%>


<tr bgcolor=<%=bgcolor%>>
<td WIDTH=400><%=emailevents.getEmailevent().getEventCode()%></td>

<%
       
        String tcompname = "emailtemplate_"+i;
		String emailtemplateid="emailtemplateId_"+i;
		String emailtemplatename="emailtemplatenameval_"+i;
		String jobdyvalue ="<span id="+tcompname+">";
       
		String emiltemplatename1 = (emailevents.getEmailtemplate().getEmailtemplatename() == null )?"":emailevents.getEmailtemplate().getEmailtemplatename();
		if(emiltemplatename1 != null && emiltemplatename1.equals("null")){
			emiltemplatename1 = "";
		}
  String tempurl1 = "<a href='#' onClick=window.open("+"'"+"emailtemplate.do?method=editemailtemplate&emailtemplateId="+emailevents.getEmailtemplate().getEmailtemplateId()+"&fn_name="+emailevents.getEmailevent().getEventCode()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=800"+"'"+");return false;>"+emiltemplatename1+"</a>";
  
 jobdyvalue = "<span id="+tcompname+">"+tempurl1+"</span>";

 
	
		
		

		
%>	
            
		
			<td WIDTH=400><%=jobdyvalue%></span>

   <a href="#" onClick="opensearchorganizationemailtemplate(<%=i%>);return false;">
<img src="jsp/images/selector.gif" border="0"/></a> 

<input type="hidden" id="emailEventId_<%=i%>" name="emailEventId_<%=i%>" value="<%=emailevents.getEmailevent().getEmailEventId()%>"/>
<input type="hidden" id="eventCode_<%=i%>" name="eventCode_<%=i%>" value="<%=emailevents.getEmailevent().getEventCode()%>"/>
<input type="hidden" id="<%=emailtemplateid%>" name="<%=emailtemplateid%>" value='<%=emailevents.getEmailtemplate().getEmailtemplateId()%>'/>
<input type="hidden" id="<%=emailtemplatename%>" name="<%=emailtemplatename%>" value='<%=emailevents.getEmailtemplate().getEmailtemplatename()%>'/>
<input type="hidden"  id="emailtemplatename" name="emailtemplatename" value=''/>	
</td>
<td WIDTH=200><%=Constant.getResourceStringValue("hr.radio.enabled",user1.getLocale())%>
<input type="radio" name="isenabled_<%=i%>" maxlength="10" id="1" <%=(emailevents.getStatus()!= null && emailevents.getStatus().equals("Y"))? "Checked=true" : "" %> value="Y">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.radio.disabled",user1.getLocale())%><input type="radio" name="isenabled_<%=i%>" id="0" <%=(emailevents.getStatus()!= null && emailevents.getStatus().equals("N"))? "Checked=true" : "" %> value="N"></td>

<td> 
 <%=com.util.MergeUtil.getAvailableTags(emailevents.getEmailevent().getEventCode())%>
</td>
  <% 
	
	}
	}
	%>

</table>

</html:form>





