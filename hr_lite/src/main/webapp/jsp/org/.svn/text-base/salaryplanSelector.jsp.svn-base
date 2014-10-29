<%@ include file="../common/include.jsp" %>
<%@ taglib uri="http://paginationtag.miin.com" prefix="pagination-tag"%>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<title>Salary plan selector </title>
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
var len = document.lovForm.salaryplanId.length; 
if(len == undefined) len = 1; 

if (document.lovForm.salaryplanId.length != undefined)
{

 for (var i=0; i < document.lovForm.salaryplanId.length; i++)
   {
   if (document.lovForm.salaryplanId[i].checked == true)
      {
	   
      c_value =document.lovForm.salaryplanId[i].value + "\n";
	  id_value =  document.lovForm.salaryplanId[i].id;
      }
   }
}else{
	if (document.lovForm.salaryplanId.checked == true) {
	c_value = document.lovForm.salaryplanId.value + "\n";
	  id_value = document.lovForm.salaryplanId.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

var tempurl = "<a href='#' onClick=window.open("+"'"+"salaryplan.do?method=editsaaryplan&readPreview=2&planId="+id_value+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=230"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="salaryplanId">'+tempurl+'</span>';
//window.opener.document.getElementById("salaryplanId").innerHTML = dyvalue;
if(PFormName == 'jobRequisitionTemplateForm'){

opener.document[PFormName].salaryplanId.value=id_value;


}

 if(PFormName == 'jobRequisitionForm'){

opener.document[PFormName].salaryplanId.value=id_value;


}

//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;

//opener.document[PFormName].salaryplanId.value=id_value;
//opener.document[PFormName].evaluationcriteria.value=c_value;




self.close();
	   
		}

		function searchcri(){
       
	   document.lovForm.action = "lov.do?method=searchsalaryplan&orgid=<%=form.getOrgId()%>";
	   document.lovForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}
function resetData(){
  document.lovForm.criteria.value="";
  document.lovForm.salaryplanyear.value="";
  
  
    
 }  
function init(){
	document.lovForm.criteria.focus();
	}
	</script>
<body class="yui-skin-sam" onLoad="init()">

<html:form action="/lov.do?method=searchsalaryplan">
<div class="div">
	<table border="0" width="100%" >
	
	<tr>
		<td bgcolor="gray" colspan="2"><b> <%=Constant.getResourceStringValue("admin.SalaryPlan.SearchSalaryPlans",user1.getLocale())%> </b></td>
	</tr>
    <tr>
				<td width="250"><%=Constant.getResourceStringValue("admin.SalaryPlan.SalaryPlan",user1.getLocale())%> :<html:text  property="criteria" maxlength="300"/></td>
				<td width="400"><%=Constant.getResourceStringValue("admin.SalaryPlan.year",user1.getLocale())%> :<html:text size="4" property="salaryplanyear" maxlength="10"/></td>
				
			</tr>
			<tr>
			<td width=""><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
                <input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button"></td>
			</tr>

			
				
</table>	
</div>
<br><br>

<div class="div">

<table border="0" width="100%">
<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("admin.SalaryPlan.SalaryPlans",user1.getLocale())%></b></td>
</tr>
</table>
<%
List salaryplanList = form.getSalaryplanList();
 if(salaryplanList != null && salaryplanList.size()>0){
%>
<pagination-tag:pager start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
 <%

 if(salaryplanList != null){
 if(salaryplanList.size()>0){
%>
<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("admin.SalaryPlan.SalaryPlan",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.SalaryPlan.PlanDescription",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.SalaryPlan.year",user1.getLocale())%></td>
</tr>
<%
	 for(int i=0;i<salaryplanList.size();i++){

		SalaryPlan slplan = (SalaryPlan)salaryplanList.get(i);
		String bgcolor = "";
		if(i%2 == 0)bgcolor ="#B2D2FF";

%>

<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="salaryplanId" id="<%=slplan.getSalaryplanId()%>" value="<%=slplan.getSalaryPlanName()%>">

<a href="#" onClick="window.open('salaryplan.do?method=editsaaryplan&readPreview=2&planId=<%=slplan.getSalaryplanId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=230')"><%=slplan.getSalaryPlanName()%></a>

	</script>
</td>
<td>
<%=slplan.getSalaryPlanDesc()%>
</td>
<td>
<%=slplan.getSalaryPlanYear()%>
</td>
</tr>


<%
	 }
 %>
		  
		      <tr>
				<td>
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
				</td>
				
			</tr>


</table>
</div>
<% }else{%>
<%=Constant.getResourceStringValue("admin.salaryplan.notfound",user1.getLocale())%>

<%}
}
 %>
 
</html:form>