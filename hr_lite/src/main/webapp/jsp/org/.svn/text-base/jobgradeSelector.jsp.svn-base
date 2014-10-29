<%@ include file="../common/include.jsp" %>
<%@ taglib uri="http://paginationtag.miin.com" prefix="pagination-tag"%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<title>Job grade selector </title>

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
var len = document.lovForm.jobgradeId.length; 
if(len == undefined) len = 1; 

if (document.lovForm.jobgradeId.length != undefined)
{

 for (var i=0; i < document.lovForm.jobgradeId.length; i++)
   {
   if (document.lovForm.jobgradeId[i].checked == true)
      {
	   
      c_value =document.lovForm.jobgradeId[i].value + "\n";
	  id_value =  document.lovForm.jobgradeId[i].id;
      }
   }
}else{
	if (document.lovForm.jobgradeId.checked == true) {
	c_value = document.lovForm.jobgradeId.value + "\n";
	  id_value = document.lovForm.jobgradeId.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

var tempurl = "<a href='#' onClick=window.open("+"'"+"jobgrade.do?method=jobGradeDetails&readPreview=2&id="+id_value+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=180"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="jobgradeId">'+tempurl+'</span>';
//window.opener.document.getElementById("jobgradeId").innerHTML = dyvalue;

if(PFormName == 'jobRequisitionTemplateForm'){

opener.document[PFormName].jobgradeId.value=id_value;


}

 if(PFormName == 'jobRequisitionForm'){

opener.document[PFormName].jobgradeId.value=id_value;


}
//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;

//opener.document[PFormName].jobgradeId.value=id_value;
//opener.document[PFormName].evaluationcriteria.value=c_value;

self.close();
	   
		}




		function searchcri(){
       
	   document.lovForm.action = "lov.do?method=searchjobgrade";
	   document.lovForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}

function resetData(){
  document.lovForm.criteria.value="";
  
  
    
 }  

function init(){
document.lovForm.criteria.focus();
// document.getElementById('roleCode').focus();
}

</script>

<body class="yui-skin-sam" onLoad="init()">

<html:form action="/lov.do?method=searchjobgrade">
<div  class="div">
	<table border="0" width="100%">
	
		<tr>
		<td bgcolor="gray" colspan="3"><b> <%=Constant.getResourceStringValue("admin.SearchJobGrades",user1.getLocale())%> </b></td>
		</tr>
    	<tr>
				<td width="300"><html:text size="50" property="criteria"/></td>
				<td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
				<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button"></td>
		</tr>

			
				
</table>	
</div>
<br>
<div class="div">

<table border="0" width="100%">
<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("admin.JobGrade.jobgrades",user1.getLocale())%></b></td>
</tr>
</table>
<%
List jobgradeList = form.getJobgradeList();
 if(jobgradeList != null && jobgradeList.size()>0){
%>
<pagination-tag:pager start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>

<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("admin.JobGrade.jobgrade",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.JobGrade.desc",user1.getLocale())%></td>
</tr>
 <%

 
 if(jobgradeList != null){

	 for(int i=0;i<jobgradeList.size();i++){

		 JobGrade jbgrade = (JobGrade)jobgradeList.get(i);
String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";

%>


<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="jobgradeId" id="<%=jbgrade.getJobgradeId()%>" value="<%=jbgrade.getJobGradeName()%>">

<a href="#" onClick="window.open('jobgrade.do?method=jobGradeDetails&readPreview=2&id=<%=jbgrade.getJobgradeId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=180')"><%=jbgrade.getJobGradeName()%></a>

	</script>
</td>
<td>
<%=jbgrade.getJobGradeDesc()%>
</td>
</tr>


<%

	 }
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
<%}else{%> 
<%=Constant.getResourceStringValue("admin.JobGrade.notfound",user1.getLocale())%>
<%}%>
</html:form>