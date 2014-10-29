<%@ include file="../common/include.jsp" %>
<%@ taglib uri="http://paginationtag.miin.com" prefix="pagination-tag"%>
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
var len = document.lovForm.questiongroupId.length; 
if(len == undefined) len = 1; 

if (document.lovForm.questiongroupId.length != undefined)
{

 for (var i=0; i < document.lovForm.questiongroupId.length; i++)
   {
   if (document.lovForm.questiongroupId[i].checked == true)
      {
	   
      c_value =document.lovForm.questiongroupId[i].value + "\n";
	  id_value =  document.lovForm.questiongroupId[i].id;
      }
   }
}else{
	if (document.lovForm.questiongroupId.checked == true) {
	c_value = document.lovForm.questiongroupId.value + "\n";
	  id_value = document.lovForm.questiongroupId.id;
	}
}
//window.opener.document.getElementByID("questiongroup").innerHTML = c_value;

var tempurl = "<a href='#' onClick=window.open("+"'"+"questiongroup.do?method=questionsGroupsummary&questiongroupId="+id_value+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="questiongroupId">'+tempurl+'</span>';

window.opener.document.getElementById("questiongroup").innerHTML = dyvalue;
opener.document[PFormName].questiongroupId.value=id_value.trim();
opener.document[PFormName].questiongroupName.value=c_value.trim();
self.close();
	   
		}

		function searchcri(){
       
	   document.lovForm.action = "lov.do?method=searchquestiongroups";
	   document.lovForm.submit();
	  // window.opener.location.href="/lov.do?method=rolelist&a=1";
	//self.close();
	
	   
		}

	function resetdata(){
          document.lovForm.criteria.value="";
	}

</script>
<html:form action="/lov.do?method=searchquestiongroups">
	<div class="div">
	<table border="0" width="100%">
	<tr><td><b><%=Constant.getResourceStringValue("admin.QuestionsGroup.searchquegrp",user1.getLocale())%></b></td><td></td></tr>
    <tr>
				<td width="300"><html:text size="50" property="criteria"/></td>
				<td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button"><input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata()" class="button"><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			</tr>

			
				
</table>
</div>
<br><br>
	<div class="div">

<table border="0" width="100%">
<tr>
<td bgcolor="gray"><%=Constant.getResourceStringValue("admin.QuestionsGroup",user1.getLocale())%></td>
</tr>
</table>
<%
List qnsgroupList = form.getQuestiongroupList();
 if(qnsgroupList != null && qnsgroupList.size()>0){
%>
<pagination-tag:pager action="lov" start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
<table border="0" width="100%">
<% if(qnsgroupList != null && qnsgroupList.size()>0){%>
<tr>
<td><%=Constant.getResourceStringValue("admin.QuestionsGroup.name",user1.getLocale())%></td>
</tr>
<%}%>
 <%

 
 if(qnsgroupList != null){

	 for(int i=0;i<qnsgroupList.size();i++){

		 QuestionGroups jbtmpl = (QuestionGroups)qnsgroupList.get(i);
String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";

%>

<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="questiongroupId" id="<%=jbtmpl.getQuestiongroupId()%>" value="<%=jbtmpl.getQuestiongroupName()%>">

<a href="#" onClick="window.open('questiongroup.do?method=questionsGroupsummary&readPreview=2&questiongroupId=<%=jbtmpl.getQuestiongroupId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=400')"><%=jbtmpl.getQuestiongroupName()%></a>

	</script>
</td>

</tr>


<%

	 }
 }

 %>
 <%  if(qnsgroupList != null && qnsgroupList.size()<1){ %>
 <%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%>
 <%}%> 
		      <tr>
				<td>
				<% if(qnsgroupList != null && qnsgroupList.size()>0){%>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="div">&nbsp;&nbsp;
				
				<%}%>
                 
				
				
				</td>
				
			</tr>

</table>
</div>
</html:form>