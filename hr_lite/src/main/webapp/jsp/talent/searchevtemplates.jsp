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
var len = document.evaluationTemplateForm.evtemplates.length; 
if(len == undefined) len = 1; 

if (document.evaluationTemplateForm.evtemplates.length != undefined)
{

 for (var i=0; i < document.evaluationTemplateForm.evtemplates.length; i++)
   {
   if (document.evaluationTemplateForm.evtemplates[i].checked == true)
      {
	   
      c_value =document.evaluationTemplateForm.evtemplates[i].value + "\n";
	  id_value =  document.evaluationTemplateForm.evtemplates[i].id;
      }
   }
}else{
	if (document.evaluationTemplateForm.evtemplates.checked == true) {
	c_value = document.evaluationTemplateForm.evtemplates.value + "\n";
	  id_value = document.evaluationTemplateForm.evtemplates.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

var tempurl = "<a href='#' onClick=window.open("+"'"+"evtmpl.do?method=evtmplpreview&evtmplid="+id_value+"'"+","+ "'"+"EvaluationTemplate"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="evaluationsheet"><b>'+tempurl+'</b></span>';
window.opener.document.getElementById("evaluationsheet").innerHTML = dyvalue;


//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;

opener.document[PFormName].evid.value=id_value;
opener.document[PFormName].evname.value=c_value;

//opener.document[PFormName].evaluationcriteria.value=c_value;




self.close();
	   
		}

		function searchcri(){
       
	   document.evaluationTemplateForm.action = "evtmpl.do?method=searchevaluationtemplates";
	   document.evaluationTemplateForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}

</script>
<html:form action="/evtmpl.do?method=searchevaluationtemplates">
	<table border="0" width="100%">
	<h3><%=Constant.getResourceStringValue("admin.EvaluationTemplate.SearchEvaluationTemplates",user1.getLocale())%>  </h3>
    <tr>
				<td width="300"><input type="text" name="criteria"></td>
	<td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()"></td>
			</tr>

			
				
</table>	
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><%=Constant.getResourceStringValue("admin.EvaluationTemplate.EvaluationTemplates",user1.getLocale())%></td>
</tr>
</table>
<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("admin.EvaluationTemplate.evaluationsheet",user1.getLocale())%></td>
</tr>
 <%
 List evaluationTmplList = form.getEvaluationTmplList();
 
 if(evaluationTmplList != null){

	 for(int i=0;i<evaluationTmplList.size();i++){

		 EvaluationTemplate evtmpl = (EvaluationTemplate)evaluationTmplList.get(i);


%>


<tr>
<td ><input type="radio" name="evtemplates" id="<%=evtmpl.getEvtmplId()%>" value="<%=evtmpl.getEvTmplName()%>">

<a href="#" onClick="window.open('evtmpl.do?method=evtmplpreview&evtmplid=<%=evtmpl.getEvtmplId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=evtmpl.getEvTmplName()%></a>

	</script>
</td>
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