<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


<bean:define id="form" name="scheduleInterviewForm" type="com.form.ScheduleInterviewForm" />


<script language="javascript">

var  PFormName= opener.document.forms[0].name;  


	function discard(){
       self.close();
		}

	function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";

var c_value = "";
var id_value = "";
var len = document.scheduleInterviewForm.evcheckboxes.length; 
if(len == undefined) len = 1; 

if (document.scheduleInterviewForm.evcheckboxes.length != undefined)
{

 for (var i=0; i < document.scheduleInterviewForm.evcheckboxes.length; i++)
   {
   if (document.scheduleInterviewForm.evcheckboxes[i].checked)
      {
	   
      c_value = c_value + document.scheduleInterviewForm.evcheckboxes[i].value + "<br>";
	  id_value = id_value +  document.scheduleInterviewForm.evcheckboxes[i].id+",";
      }
   }
}else{
	if (document.scheduleInterviewForm.evcheckboxes.checked) {
	c_value = c_value + document.scheduleInterviewForm.evcheckboxes.value + "<br>";
	  id_value = id_value +  document.scheduleInterviewForm.evcheckboxes.id+",";
	}
}
//opener.element.getElementByID("evaluationcriterias").value = c_value;
//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;

window.opener.document.getElementById("evaluationcriteria").innerHTML = c_value;

opener.document[PFormName].charids.value=id_value;





self.close();
	   
		}

		function searchcri(){
       
	   document.scheduleInterviewForm.action = "scheduleInterview.do?method=searchevaluationcriterias";
	   document.scheduleInterviewForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
		}

	function resetData(){
  document.scheduleInterviewForm.criteria.value="";
	   
		}

function init(){
document.scheduleInterviewForm.criteria.focus();
}
</script>
<body class="yui-skin-sam" onLoad="init()" >
<html:form action="/scheduleInterview.do?method=searchevaluationcriterias">
	<table border="0" width="100%">
	<h3> <%=Constant.getResourceStringValue("admin.competenciesgroup.SearchCompetencies",user1.getLocale())%></h3>
    <tr>
				<td width="300"><input type="text" name="criteria"></td>
				<td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()">
				<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()"></td>
			</tr>

			
				
</table>	
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><%=Constant.getResourceStringValue("admin.competenciesgroup.comp",user1.getLocale())%></td>
</tr>
</table>
<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("admin.Competencies.Competencyname",user1.getLocale())%> </td><td><%=Constant.getResourceStringValue("admin.Competencies.Weight",user1.getLocale())%></td>
</tr>
 <%
 List charList = form.getCharList();
 
 if(charList != null){

	 for(int i=0;i<charList.size();i++){

		 Characteristic characteristic = (Characteristic)charList.get(i);


%>


<tr>
<td ><input type="checkbox" name="evcheckboxes" id="<%=characteristic.getCharId()%>" value="<%=characteristic.getCharName()%>"><%=characteristic.getCharName()%></td><td><%=characteristic.getWeight()%></td>
</tr>


<%

	 }
 }

 %>
		  
		      <tr>
				<td>
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()">&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()">
				</td>
				<td></td>
			</tr>

</table>
</html:form>