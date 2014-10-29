<%@ include file="../common/include.jsp" %>
<%@ taglib uri="http://paginationtag.miin.com" prefix="pagination-tag"%>
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

<bean:define id="form" name="questionGroupForm" type="com.form.QuestionsGroupForm" />


<%
String start = form.getStart();
String range = form.getRange();
String results = form.getResults();

%>
<%
String boxnumber = (String)request.getAttribute("boxnumber");

%>

<script language="javascript">

var  PFormName= opener.document.forms[0].name;  


	function discard(){
       self.close();
		}

	//function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";
//window.location.href = "orgemailtemplate.do?method=emaillitsearch&boxnumber=<%=boxnumber%>" +"&close=yes"";
//	}
	function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";

var c_value = "";
var id_value = "";
var len = document.questionGroupForm.questionId.length; 

if(len == undefined) len = 1; 

if (document.questionGroupForm.questionId.length != undefined)
{

 for (var i=0; i < document.questionGroupForm.questionId.length; i++)
   {
   if (document.questionGroupForm.questionId[i].checked)
      {
	   
      c_value = c_value + document.questionGroupForm.questionId[i].value + "<br>";
	  id_value = id_value +  document.questionGroupForm.questionId[i].id+",";
      }
   }
}else{
	if (document.questionGroupForm.questionId.checked) {
	c_value = c_value + document.questionGroupForm.questionId.value + "<br>";
	  id_value = id_value +  document.questionGroupForm.questionId.id+",";

	}
}
//opener.element.getElementByID("evaluationcriterias").value = c_value;
//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;
opener.document[PFormName].questionId.value=id_value;
window.opener.document.getElementById("questiongroup").innerHTML = c_value;







self.close();
	   
		}
/*	
	function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";

var c_value = "";
var id_value = "";
var len = document.questionGroupForm.questionId.length; 
if(len == undefined) len = 1; 

if (document.questionGroupForm.questionId.length != undefined)
{

 for (var i=0; i < document.questionGroupForm.questionId.length; i++)
   {
   if (document.questionGroupForm.questionId[i].checked == true)
      {
	   
      c_value =document.questionGroupForm.questionId[i].value + "\n";
	  id_value =  document.questionGroupForm.questionId[i].id;
      }
   }
}else{
	if (document.questionGroupForm.questionId.checked == true) {
	c_value = document.questionGroupForm.questionId.value + "\n";
	  id_value = document.questionGroupForm.questionId.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

var tempurl = "<a href='#' onClick=window.open("+"'"+"questiongroup.do?method=searchQuestionGroup&readPreview=2&Id="+id_value+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=270"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="question">'+tempurl+'</span>';
window.opener.document.getElementById("question").innerHTML = dyvalue;


//opener.element.getElementByID("charids").value = id_value;

//opener.document..charids.value=id_value;
//opener.document.foname.evaluationcriteria.value=id_value;

opener.document.questionGroupForm.questionId.value=id_value;
//opener.document[PFormName].evaluationcriteria.value=c_value;




self.close();
	   
		}		  
*/
		function searchcri(){
		var questiongroupName = document.questionGroupForm.questiongroupName.value;

	  	document.questionGroupForm.action = "questiongroup.do?method=searchQuestionGroup&boxnumber="+<%=boxnumber%>+"&questiongroupName="+questiongroupName;
	  	document.questionGroupForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}
function resetdata(){
	document.questionGroupForm.questiongroupName.value="";

	}
function init(){
	document.questionGroupForm.questiongroupName.focus();
	}
	</script>
	<body class="yui-skin-sam" onLoad="init()" >

<html:form action="/questiongroup.do?method=searchQuestionsGroupAll&boxnumber=<%=boxnumber%>">
<div class="div">
	<table border="0" width="100%">

	<h3><b><%=Constant.getResourceStringValue("admin.QuestionsGroup.searchque",user1.getLocale())%></b> </h3>
    	<tr>
	   		<td>
	   		<html:text property="questiongroupName" maxlength="500"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   		<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
	   		<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()" class="button">
	   		</td>
	   </tr>

			
				
</table>
</div>
<br></br>
<div class="div">

<table border="0" width="100%">
<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("admin.QuestionsGroup.name",user1.getLocale())%></b></td>
</tr>
</table>

<div id="dynamicdata" class="div">
<%
List checkeduserids = new ArrayList();
if(session.getAttribute("checkeduserids") != null){
checkeduserids = (ArrayList)session.getAttribute("checkeduserids");

}
 List queList = form.getQuestions();

 if(queList != null && queList.size()>0){
%>
<pagination-tag:pager action="questiongroup" start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
<table border="0" width="100%">
<tr>
<!--  <td><%=Constant.getResourceStringValue("admin.QuestionsGroup.name",user1.getLocale())%></td>-->
</tr>
 <%
	if(queList != null){

	 for(int i=0;i<queList.size();i++){
       Questions ques = (Questions)queList.get(i);
	
    String val = ques.getQuestionName();
	String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
  
/*
 
 if(orgemailList != null){

	 for(int i=0;i<orgemailList.size();i++){

		EmailTemplates  orgemailtemp = (EmailTemplates)orgemailList.get(i);
String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
*/
%>
     <tr bgcolor=<%=bgcolor%>>
<td ><input type="checkbox" name="questionId" id="<%=ques.getQuestionId()%>" value="<%=val%>">

<a href="#" onClick="window.open('question.do?method=editquestionForSelector&readPreview=2&edit=2&value=5&questionId=<%=ques.getQuestionId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=ques.getQuestionName()%></a>

	</script>

</td>

<%

	 }
 }

 %>

<%  if(queList != null && queList.size()<1){ %>
 <%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%> 
<%}%>
		  
	<tr>
				<td>
				<%  if(queList != null && queList.size()>0){ %>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%> " onClick="savedata()" class="button">&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}%>
				</td>
				
			</tr>
			</div>
</table>
</html:form>






