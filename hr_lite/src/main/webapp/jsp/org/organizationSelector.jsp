<%@ include file="../common/include.jsp" %>
<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%> 
<%@ page import="com.bean.*"%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<title><%=Constant.getResourceStringValue("admin.organization.orglistbody.orgselector",user1.getLocale())%> </title>

<bean:define id="form" name="organizationForm" type="com.form.OrganizationForm" />
<%

Organization org1 = form.getOrganization();
if(org1 != null){
request.setAttribute("org1",org1);
}
%>
<%
String start = form.getStart();
String range = form.getRange();
String results = form.getResults();

%>
<script language="javascript">

var  PFormName= opener.document.forms[0].name;  


	function discard(){
       self.close();
		}

	function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";

var c_value = "";
var id_value = "";
var len = document.organizationForm.orgId.length; 
var getorgid=document.organizationForm.getorgid.value; 
if(len == undefined) len = 1; 

if (document.organizationForm.orgId.length != undefined)
{

 for (var i=0; i < document.organizationForm.orgId.length; i++)
   {
   if (document.organizationForm.orgId[i].checked == true)
      {
	   
      c_value =document.organizationForm.orgId[i].value + "\n";
	  id_value =  document.organizationForm.orgId[i].id;
      }
   }
}else{
	if (document.organizationForm.orgId.checked == true) {
	c_value = document.organizationForm.orgId.value + "\n";
	  id_value = document.organizationForm.orgId.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

if(id_value!=getorgid){


var tempurl = "<a href='#' onClick=window.open("+"'"+"org.do?method=editorg&readPreview=2&orgid="+id_value+"'"+","+ "'"+"OrgPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=330"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="parentOrgId">'+tempurl+'</span>';


	window.opener.document.getElementById("parentOrgId").innerHTML = dyvalue;
	
//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;

opener.document[PFormName].parentOrgId.value=id_value.trim();
opener.document[PFormName].parentOrgName.value=c_value.trim();


//opener.document[PFormName].evaluationcriteria.value=c_value;

if(PFormName == 'jobRequisitionTemplateForm'){
	window.opener.document.getElementById("departmentId").innerHTML = "";
	opener.document[PFormName].departmentId.value="";
	opener.document[PFormName].department.value="";

	window.opener.document.getElementById("projectcodeId").innerHTML = "";
	opener.document[PFormName].projectcodeId.value="";
	opener.document[PFormName].projectcode.value="";
}


self.close();
}else{
	alert("<%=Constant.getResourceStringValue("admin.organization.same_parent_should_not_add",user1.getLocale())%>");

}
	   
		}





		function searchcri(){
        
	   document.organizationForm.action = "org.do?method=searchorganization&readPreview=1&orgId=<%=form.getOrgId()%>";
	   document.organizationForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}
 function resetData(){
  document.organizationForm.orgName.value="";
  document.organizationForm.orgCode.value="";

  document.organizationForm.locationId.value="";
  document.organizationForm.organizationTypeId.value="";
  document.organizationForm.isLegal.value="";
  
    
 }  
document.onkeypress=function(e){
var e=window.event || e
//alert("CharCode value: "+e.charCode)
//alert("Character: "+String.fromCharCode(e.charCode))
	if(e.keyCode==13)
	{
	setTimeout("document.organizationForm.search.click();",200);
}
}
function setFocus(){
document.organizationForm.orgName.focus();
// document.getElementById('roleCode').focus();
}

</script>
<body onLoad="setFocus()" onkeypress="onkeypress()">

<html:form action="/org.do?method=searchorganization&readPreview=1">
<table border="0" width="100%" colspan="2">
 <div class="div">
<tr width="35%" valign=top>
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("admin.organization.orglistbody.searchorg",user1.getLocale())%></b> </td>
</tr>
<!--property="orgName"
property="orgCode"
 property="createdBy"
--> 
<input type="hidden" name="getorgid" value="<%=form.getOrgId()%>"/>
    
    <tr>        
	     <td><%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationName",user1.getLocale())%></td>
	</tr>
	<tr>
         <td><html:text property="orgName" maxlength="500"/></td>
    </tr>	
	<tr>
	    <td><%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationCode",user1.getLocale())%></td>
	</tr>
	<tr>
         <td><html:text property="orgCode" maxlength="300"/></td>
    </tr>
					
	<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%></td>
			</tr>
			<tr><td>
			<span id="location">
			<html:select  property="locationId">
			<option value=""></option>
			<bean:define name="organizationForm" property="locationList" id="locationList" />

            <html:options collection="locationList" property="locationId"  labelProperty="locationName"/>
			</html:select>
			</span>
			</td>
		</tr>	
	
   
	<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationType",user1.getLocale())%></td>
			</tr>
			<tr><td>
			<span id="orgtype">
			<html:select   property="organizationTypeId">
			<option value=""></option>
			<bean:define name="organizationForm" property="orgnizationTypeList" id="orgnizationTypeList" />

            <html:options collection="orgnizationTypeList" property="organizationTypeId"  labelProperty="lorganizationTypeName"/>
			</html:select>
			</span>
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.islegal",user1.getLocale())%></td>
			</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.radio.yes",user1.getLocale())%><input type="radio" name="isLegal" id="1" <%=(form.getIsLegal()!= null && form.getIsLegal().equals("Y"))? "Checked=true" : "" %> value="Y">&nbsp;&nbsp;<%=Constant.getResourceStringValue("hr.radio.no",user1.getLocale())%><input type="radio" name="isLegal" id="0" <%=(form.getIsLegal()!= null && form.getIsLegal().equals("N"))? "Checked=true" : "" %> value="N"></td>
		</tr>
	 
	 <tr>
	   <td>
		<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
       <input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button">
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
		</td>				
	 </tr>
			
			

</table>


</th>
<div class="div">
<tr width="65%" valign=top>
<table border="0" width="100%">

<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("admin.organization.organizations",user1.getLocale())%><b> </td>
</tr>
</table>
<%
 List orgList = form.getOrgList();
 if(orgList != null && orgList.size()>0){
%>
<pagination-tag:pager start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationCode",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.OrganizationType",user1.getLocale())%></td>
</tr>
 <%
 
 
 if(orgList != null){

	 for(int i=0;i<orgList.size();i++){

		 Organization org = (Organization)orgList.get(i);

String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
%>


<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="orgId" id="<%=org.getOrgId()%>" value="<%=org.getOrgName()%>">

<a href="#" onClick="window.open('org.do?method=editorg&readPreview=2&orgid=<%=org.getOrgId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=330')"><%=org.getOrgName()%></a>

	</script>
</td>
<td>
<%=(org.locationValue == null)?"":org.locationValue%>
</td>
<td>
<%=(org.organizationTypeValue == null)?"":org.organizationTypeValue%>

</td>
</tr>


<%

	 }
 }

 %>
		  
<%  if(orgList != null && orgList.size()<1){ %>
<tr>
				<td>
<font color="red"><b><%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%> </b></font>
</td>
				
			</tr>
<%}%>
		  
	<tr>
				<td colspan="3">
				<%  if(orgList != null && orgList.size()>0){ %>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}%>
				</td>
				
			</tr>	 		     

</table>
</tr>
</div>


</table>

</html:form>