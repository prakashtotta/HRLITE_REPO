<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


<bean:define id="form" name="lovForm" type="com.form.LovForm" />

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
var len = document.lovForm.projectcodeId.length; 
if(len == undefined) len = 1; 

if (document.lovForm.projectcodeId.length != undefined)
{

 for (var i=0; i < document.lovForm.projectcodeId.length; i++)
   {
   if (document.lovForm.projectcodeId[i].checked == true)
      {
	   
      c_value =document.lovForm.projectcodeId[i].value + "\n";
	  id_value =  document.lovForm.projectcodeId[i].id;
      }
   }
}else{
	if (document.lovForm.projectcodeId.checked == true) {
	c_value = document.lovForm.projectcodeId.value + "\n";
	  id_value = document.lovForm.projectcodeId.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

var tempurl = "<a href='#' onClick=window.open("+"'"+"projectcodes.do?method=editProjectCodes&readPreview=2&projectCodeId="+id_value+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=290"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="projectcodeId">'+tempurl+'</span>';
window.opener.document.getElementById("projectcodeId").innerHTML = dyvalue;


//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;

opener.document[PFormName].projectcodeId.value=id_value.trim();
opener.document[PFormName].projectcode.value=c_value.trim();
//opener.document[PFormName].evaluationcriteria.value=c_value;




self.close();
	   
		}

		function searchcri(){
       
	   document.lovForm.action = "lov.do?method=searchprojectcodes&departmentid=<%=form.getDepartmentId()%>&orgid=<%=form.getOrgId()%>";
	   document.lovForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}
		function resetData(){
  document.lovForm.projCode.value="";
  document.lovForm.projName.value="";
  document.lovForm.departmentId.value="";
  
    
 }  
document.onkeypress=function(e){
var e=window.event || e
//alert("CharCode value: "+e.charCode)
//alert("Character: "+String.fromCharCode(e.charCode))
	if(e.keyCode==13)
	{
	setTimeout("document.lovForm.search.click();",200);
}
}

function setFocus(){
document.lovForm.projCode.focus();
// document.getElementById('roleCode').focus();
}

</script>
<body onLoad="setFocus()" onkeypress="onkeypress()">

<html:form action="/lov.do?method=searchprojectcodes&orgid=<%=form.getOrgId()%>&departmentid=<%=form.getDepartmentId()%>">
	<div class="div">
	<table border="0" width="100%" colspan="2">
    <th width="30%" valign=top>
		<div class="div">

    <table border="0" width="100%">
    <tr>
        <td bgcolor="gray"><%=Constant.getResourceStringValue("admin.ProjectCode.SearchProjectCodes",user1.getLocale())%></td>
    </tr> 
	<tr>
	    <td><%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%></td>
	</tr>
    <tr>
	     <td><html:text property="projCode" maxlength="200"/></td>
	</tr>
	<tr>
	    <td><%=Constant.getResourceStringValue("admin.ProjectCode.name",user1.getLocale())%></td>
	</tr>
    <tr>
	     <td><html:text property="projName" maxlength="500"/></td>
	</tr>
	
	<tr>	
	     <td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td></tr>
		<tr> <td>
		 <% if(form.getDepartmentId() != 0){%>
			 <html:select name="lovForm"  property="departmentId" disabled="true">
			 <bean:define name="lovForm" property="departmentList" id="departmentList" />

             <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			 </html:select>
			<%}else{%>
			<html:select name="lovForm"  property="departmentId">
			 <bean:define name="lovForm" property="departmentList" id="departmentList" />

             <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
			 </html:select>
			<%}%>
		</td>				
	</tr>			
     <tr>				   
	       <td>
		       <input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
			    <input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button">
			   <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">

		    </td>
			<td></td>
	</tr>
				
</table>
</div>
</th>
<th width="70%" valign=top>
	<div class="div">

<table border="0" width="100%">

<tr>
<td bgcolor="gray"><%=Constant.getResourceStringValue("admin.ProjectCode.name",user1.getLocale())%></td>
</tr>
</table>
<table border="0" width="100%">

<%
List projectcodeList = form.getProjectcodeList();
 if(projectcodeList != null && projectcodeList.size()>0){
%>
<pagination-tag:pager start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("admin.ProjectCode.pcode",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.ProjectCode.name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.Deparment.depts",user1.getLocale())%></td>
</tr>
 <%
 
 
 if(projectcodeList != null){

	 for(int i=0;i<projectcodeList.size();i++){

		 ProjectCodes pcode = (ProjectCodes)projectcodeList.get(i);

String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
%>


<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="projectcodeId" id="<%=pcode.getProjectId()%>" value="<%=pcode.getProjCode()%>">

<a href="#" onClick="window.open('projectcodes.do?method=editProjectCodes&readPreview=2&projectCodeId=<%=pcode.getProjectId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=290')"><%=pcode.getProjCode()%></a>

	</script>
</td>
<td>
<%=pcode.getProjName()%>
</td>
<td>
<%=pcode.getDepartment().getDepartmentName()%>
</td>
</tr>


<%

	 }%>


	 <%  if(projectcodeList != null && projectcodeList.size()<1){ %>
<tr>
				<td>
<font color="red"><b> <%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%> </b></font>
</td>
				
			</tr>
<%}%>
 <%  if(projectcodeList != null && projectcodeList.size()>0){ %>
	 <tr>				   
	       <td>
<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">

		    </td>	
			<td>	
<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
</td>
</td></td>
	</tr>
<%}%>
<% }

 %>
		  

</table>
</div>
</th>
</table>
</html:form>