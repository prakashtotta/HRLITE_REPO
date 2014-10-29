<%@ include file="../common/include.jsp" %>
<%@ taglib uri="http://paginationtag.miin.com" prefix="pagination-tag"%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


<bean:define id="formvariablesmapform" name="formVariablesMapForm" type="com.form.FormVariablesMapForm" />

<%
String start = formvariablesmapform.getStart();
String range = formvariablesmapform.getRange();
String results = formvariablesmapform.getResults();

%>
 <%
String boxnumber = (String)request.getAttribute("boxnumber");

%>
<script language="javascript">

var  PFormName= opener.document.forms[0].name;  
var boxnu = "<%=boxnumber%>";

	function discard(){
       self.close();
		}

	function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";

var c_value = "";
var id_value = "";
var len = document.formVariablesMapForm.variableId.length; 
if(len == undefined) len = 1; 

if (document.formVariablesMapForm.variableId.length != undefined)
{

 for (var i=0; i < document.formVariablesMapForm.variableId.length; i++)
   {
   if (document.formVariablesMapForm.variableId[i].checked == true)
      {
	   
      c_value =document.formVariablesMapForm.variableId[i].value + "\n";
	  id_value =  document.formVariablesMapForm.variableId[i].id;
      }
   }
}else{
	if (document.formVariablesMapForm.variableId.checked == true) {
	c_value = document.formVariablesMapForm.variableId.value + "\n";
	  id_value = document.formVariablesMapForm.variableId.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

var tempurl = "<a href='#' onClick=window.open("+"'"+"variable.do?method=editVariable&readPreview=2&variableId="+id_value+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=310"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="varspan_'+boxnu+'">'+tempurl+'</span>';

var tmp = "varspan_"+boxnu;

window.opener.document.getElementById(tmp).innerHTML = dyvalue;



opener.document[PFormName].<%=formvariablesmapform.getVariableidval()%>.value=id_value;
opener.document[PFormName].<%=formvariablesmapform.getVariablevalue()%>.value=c_value;



self.close();
	   
		}




		function searchcri(){
       
	   document.formVariablesMapForm.action = "variablemap.do?method=searchVariables&boxnu="+boxnu;
	   document.formVariablesMapForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}

function resetData(){
  document.formVariablesMapForm.variablename.value="";
  document.formVariablesMapForm.variabletype.value="";
  
  
    
 }  



</script>



<html:form action="/variablemap.do?method=searchjobgrade">
<div class="div">
	<table border="0" width="100%">
	<tr><td bgcolor="gray" colspan="2"><b><%=Constant.getResourceStringValue("admin.variable.selector.search_variables",user1.getLocale())%> </b></td></tr>
    <tr>
    	<td><%=Constant.getResourceStringValue("admin.variable.variable_name",user1.getLocale())%></td>
		<td><html:text property="variablename" size="30"/></td>
	    
	</tr>
	<tr>
				
			
			<td><%=Constant.getResourceStringValue("admin.variable.variable_type",user1.getLocale())%></td>
			<td>
			    <html:select property="variabletype">
				<option value=""></option>
			    <bean:define name="formVariablesMapForm" property="variabletypeList" id="variabletypeList"/>
                <html:options collection="variabletypeList" property="key"  labelProperty="value"/>
			    </html:select>
			
				<input type="hidden" name="idval" id="idval" value="<%=formvariablesmapform.getIdval()%>"/>
			</td>

	</tr>
	<tr>
				<td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
				<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button"></td>
			</tr>
</table>
</div>

<br>
<div  class="div">
	<table border="0" width="100%">
<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("admin.variable.selector.displaylable.variable",user1.getLocale())%></b></td>
</tr>
		
				
</table>	

<%
List variableList = formvariablesmapform.getVariableList();
 if(variableList != null && variableList.size()>0){
%>
<pagination-tag:pager action="variablemap" start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>

<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("admin.variable.variable_name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.variable.variable_code",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.variable.variable_type",user1.getLocale())%></td>
</tr>
 <%

 
 

	 for(int i=0;i<variableList.size();i++){

		 Variables variable = (Variables)variableList.get(i);
String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";

%>


<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="variableId" id="<%=variable.getVariableId()%>" value="<%=variable.getVariableName()%>">

<a href="#" onClick="window.open('variable.do?method=editVariable&readPreview=2&variableId=<%=variable.getVariableId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=180')"><%=variable.getVariableName()%></a>


	</script>
</td>
<td>
<%=variable.getVariableCode()%>

</td>
<td><%=variable.getVariableType()%></td>
</tr>




<%

	 }%>
	 <tr>
				<td>
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">&nbsp;&nbsp;
			  
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
				</td>
				
			</tr>

<% }else{%>
<%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%>
	<%}%>

 
		  
		      

</table>
</div>
</html:form>