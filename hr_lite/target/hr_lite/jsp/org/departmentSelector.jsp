<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


<bean:define id="form" name="departmentForm" type="com.form.DepartmentForm" />



<script language="javascript">

var  PFormName= opener.document.forms[0].name;  


	function discard(){ 
       self.close();
		}

	function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";

var c_value = "";
var id_value = "";
var len = document.departmentForm.departmentId.length; 
if(len == undefined) len = 1; 

if (document.departmentForm.departmentId.length != undefined)
{

 for (var i=0; i < document.departmentForm.departmentId.length; i++)
   {
   if (document.departmentForm.departmentId[i].checked == true)
      {
	   
      c_value =document.departmentForm.departmentId[i].value + "\n";
	  id_value =  document.departmentForm.departmentId[i].id;
      }
   }
}else{
	if (document.departmentForm.departmentId.checked == true) {
	c_value = document.departmentForm.departmentId.value + "\n";
	  id_value = document.departmentForm.departmentId.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

var tempurl = "<a href='#' onClick=window.open("+"'"+"dept.do?method=editDepartmentDetails&readPreview=2&id="+id_value+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=230"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="departmentId">'+tempurl+'</span>';
window.opener.document.getElementById("departmentId").innerHTML = dyvalue;


//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;

opener.document[PFormName].departmentId.value=id_value.trim();
opener.document[PFormName].department.value=c_value.trim();
//opener.document[PFormName].evaluationcriteria.value=c_value;

if(PFormName == 'departmentForm' || PFormName == 'jobRequisitionTemplateForm'){
	
	window.opener.document.getElementById("projectcodeId").innerHTML = "";
	opener.document[PFormName].projectcodeId.value="";
	opener.document[PFormName].projectcode.value="";
}


self.close();
	   
		}

		function searchcri(){
       
	   document.departmentForm.action = "dept.do?method=searchdepartment&readPreview=2&orgid=<%=form.getOrgId()%>";
	   document.departmentForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}
		 function resetData(){
  document.departmentForm.departmentName.value="";
  document.departmentForm.departmentCode.value="";
  document.departmentForm.orgnizationList.value="";
  
    
 }  

document.onkeypress=function(e){
var e=window.event || e
//alert("CharCode value: "+e.charCode)
//alert("Character: "+String.fromCharCode(e.charCode))
	if(e.keyCode==13)
	{
	setTimeout("document.departmentForm.search.click();",200);
}
}
function setFocus(){
document.departmentForm.departmentName.focus();
// document.getElementById('roleCode').focus();
}
</script>
<body onLoad="setFocus()" onkeypress="onkeypress()">

<html:form action="/dept.do?method=searchdepartment&readPreview=2&orgid=<%=form.getOrgId()%>">
	<table border="0" width="100%" colspan="2">
	<div class="div">
     <th width="40%" valign=top>
     <table border="0" width="100%">
     <tr>
         <td bgcolor="gray"><%=Constant.getResourceStringValue("admin.Deparment.searchdept",user1.getLocale())%></td>
     </tr>
	 <tr>
	           <td><%=Constant.getResourceStringValue("admin.Deparment.name",user1.getLocale())%></td>
	 </tr>
	 <tr>
				<td width="300"><html:text   property="departmentName" maxlength="300"/></td>
	 </tr>
	  <tr>
	           <td><%=Constant.getResourceStringValue("admin.Deparment.deptcode",user1.getLocale())%></td>
	 </tr>
	 <tr>
				<td width="300"><html:text property="departmentCode" maxlength="300"/></td>
	 </tr>
	  
	 <tr>
			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
			</tr>
			<tr><td>
			<span id="orgid">
			<% if(form.getOrgId() != 0){%>
			<html:select property="orgId" disabled="true">
			<bean:define name="departmentForm" property="orgnizationList" id="orgnizationList" />

            <html:options collection="orgnizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
			<%}else{%>
            <html:select property="orgId">
			<bean:define name="departmentForm" property="orgnizationList" id="orgnizationList" />

            <html:options collection="orgnizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>
            <%}%>
			</span>
			</td>
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
</div>
<div class="div">
<th width="60%" valign=top>
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%> </td>
</tr>
</table>
<table border="0" width="100%">
<tr>
<td><b><%=Constant.getResourceStringValue("admin.Deparment.name",user1.getLocale())%></b></td><td><b><%=Constant.getResourceStringValue("admin.Deparment.deptcode",user1.getLocale())%></b></td><td><b><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></b></td>
</tr>
 <%
 List departmentList = form.getDepartmentList();
 
 if(departmentList != null){

	 for(int i=0;i<departmentList.size();i++){

		 Department dept = (Department)departmentList.get(i);
String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";

%>


<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="departmentId" id="<%=dept.getDepartmentId()%>" value="<%=dept.getDepartmentName()%>">

<a href="#" onClick="window.open('dept.do?method=editDepartmentDetails&readPreview=2&id=<%=dept.getDepartmentId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=230')"><%=dept.getDepartmentName()%></a>

	</script>
</td>
<td><%=dept.getDepartmentCode()%></td><td><%=dept.getOrganization().getOrgName()%></td>
</tr>


<%

	 }
 }

 %>
		  
		      
<%  if(departmentList != null && departmentList.size()<1){ %>
<tr>
<td>
<font color="red"><b><%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%></b></font>
 </td>	
</tr>
<%}%>
		  
	<tr>
				<td>
				<%  if(departmentList != null && departmentList.size()>0){ %>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}%>
				</td>
				
			</tr>
</table></th>
</div>
</table>
</html:form>