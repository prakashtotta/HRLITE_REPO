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
var len = document.lovForm.jobcodeId.length; 
if(len == undefined) len = 1; 

if (document.lovForm.jobcodeId.length != undefined)
{

 for (var i=0; i < document.lovForm.jobcodeId.length; i++)
   {
   if (document.lovForm.jobcodeId[i].checked == true)
      {
	   
      c_value =document.lovForm.jobcodeId[i].value + "\n";
	  id_value =  document.lovForm.jobcodeId[i].id;
      }
   }
}else{
	if (document.lovForm.jobcodeId.checked == true) {
	c_value = document.lovForm.jobcodeId.value + "\n";
	  id_value = document.lovForm.jobcodeId.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

var tempurl = "<a href='#' onClick=window.open("+"'"+"lov.do?method=jobgradepreview&jobcodeId="+id_value+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="jobcodeId">'+tempurl+'</span>';
window.opener.document.getElementById("jobcodeId").innerHTML = dyvalue;


//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;

opener.document[PFormName].jobcodeId.value=id_value;
//opener.document[PFormName].evaluationcriteria.value=c_value;




self.close();
	   
		}

		function searchcri(){
       
	   document.lovForm.action = "lov.do?method=searchjobcode";
	   document.lovForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}

function init(){
document.lovForm.criteria.focus();
// document.getElementById('roleCode').focus();
}

</script>
<body onLoad="init()">

<html:form action="/lov.do?method=searchjobcode">
<div class="div">
	<table bgcolor="#B2D2FF" border="0" width="100%" >
	<h3> <%=Constant.getResourceStringValue("admin.JobCode.SearchJobCodes",user1.getLocale())%> </h3>
    <tr>
				<td width="300"><html:text size="50" property="criteria"/></td>
				<td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button"></td>
			</tr>

			
				
</table>
</div>
<div  class="div">
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><%=Constant.getResourceStringValue("admin.JobCode.JobCodes",user1.getLocale())%></td>
</tr>
</table>
<%
List jobcodeList = form.getJobcodeList();
 if(jobcodeList != null && jobcodeList.size()>0){
%>
<pagination-tag:pager start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("admin.JobCode.JobCode",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.JobCode.JobName",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.JobCode.JobDescription",user1.getLocale())%></td>
</tr>
 <%

 
 if(jobcodeList != null){

	 for(int i=0;i<jobcodeList.size();i++){

		 JobCode jbcode = (JobCode)jobcodeList.get(i);
String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";

%>


<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="jobcodeId" id="<%=jbcode.getJobcodeId()%>" value="<%=jbcode.getJobCode()%>">

<a href="#" onClick="window.open('lov.do?method=jobgradepreview&jobcodeId=<%=jbcode.getJobcodeId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=jbcode.getJobCode()%></a>

	</script>
</td>
<td>
<%=jbcode.getJobName()%>
</td>
<td>
<%=jbcode.getJobDesc()%>
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
</html:form>