<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


<bean:define id="form" name="evaluationTemplateForm" type="com.form.EvaluationTemplateForm" />


<script language="javascript">

var  PFormName= opener.document.forms[0].name;  


	function discard(){
       self.close();
		}

	function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";

var c_value = "";
var id_value = "";
var len = document.evaluationTemplateForm.evcheckboxes.length; 
if(len == undefined) len = 1; 

if (document.evaluationTemplateForm.evcheckboxes.length != undefined)
{

 for (var i=0; i < document.evaluationTemplateForm.evcheckboxes.length; i++)
   {
   if (document.evaluationTemplateForm.evcheckboxes[i].checked)
      {
	   
      c_value = c_value + document.evaluationTemplateForm.evcheckboxes[i].value + "<br>";
	  id_value = id_value +  document.evaluationTemplateForm.evcheckboxes[i].id+",";
      }
   }
}else{
	if (document.evaluationTemplateForm.evcheckboxes.checked) {
	c_value = c_value + document.evaluationTemplateForm.evcheckboxes.value + "<br>";
	  id_value = id_value +  document.evaluationTemplateForm.evcheckboxes.id+",";
	}
}
//opener.element.getElementByID("evaluationcriterias").value = c_value;
//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;

opener.document[PFormName].charids.value=id_value;
window.opener.document.getElementById("evaluationcriteria").innerHTML = c_value;




self.close();
	   
		}

		function searchcri(){
       
	   document.evaluationTemplateForm.action = "evtmpl.do?method=searchgroupchars";
	   document.evaluationTemplateForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}
		 function resetData(){
  document.evaluationTemplateForm.criteria.value="";
  
   
   
   
    
 }  


function init(){
document.evaluationTemplateForm.criteria.focus();
}
</script>
<body class="yui-skin-sam" onLoad="init()">

<html:form action="/evtmpl.do?method=searchgroupchars">


	<table border="0" width="100%">
	<h3> <%=Constant.getResourceStringValue("admin.competenciesgroup.Searchcompetencygroup",user1.getLocale())%></h3>
    <tr>
				<td width="300"><input type="text" name="criteria"></td>
				<td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()">
				<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()"></td>

			</tr>

			
				
</table>	
<table border="0" width="100%">
<tr>
<td bgcolor="gray"> <%=Constant.getResourceStringValue("admin.competenciesgroup.compgroup",user1.getLocale())%></td>
</tr>
</table>
<table border="0" width="100%">
<tr>
<td> <%=Constant.getResourceStringValue("admin.competenciesgroup.CompetencygroupName",user1.getLocale())%></td>
</tr>
 <%
 List groupcharList = form.getGroupcharList();
 
 if(groupcharList != null){

	 for(int i=0;i<groupcharList.size();i++){

		 GroupCharacteristic grpcharacteristic = (GroupCharacteristic)groupcharList.get(i);


%>


<tr>
<td ><input type="checkbox" name="evcheckboxes" id="<%=grpcharacteristic.getGroupCharId()%>" value="<%=grpcharacteristic.getGroupCharName()%>"><%=grpcharacteristic.getGroupCharName()%></td>
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
				
			</tr>

</table>
</html:form>