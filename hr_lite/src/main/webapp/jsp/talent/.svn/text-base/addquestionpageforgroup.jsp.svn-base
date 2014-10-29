<%@ include file="../common/include.jsp" %>
<%
String path6 = (String)request.getAttribute("filePath");
User user16 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern6 = DateUtil.getDatePatternFormat(user16.getLocale());
%>
<style type="text/css">

.initial2{font-weight:bold;background-color:#c00;color:#fff;}

</style>
<bean:define id="questionsgroupform" name="questionGroupForm" type="com.form.QuestionsGroupForm" />
<script language="javascript">



function change(e, color){
    var el=window.event? event.srcElement: e.target
    if (el.tagName=="INPUT"&&el.type=="button")
    el.style.backgroundColor=color
}

function createNewQuestion(){	
   var id=document.questionGroupForm.questionId.value;

   document.questionGroupForm.action = "questiongroup.do?method=createnewQuestion&name=<%=questionsgroupform.getQuestiongroupName()%>&description=<%=questionsgroupform.getQuestiongroupDesc()%>&quesgroupId=<%=questionsgroupform.getQuestiongroupId()%>";
   document.questionGroupForm.submit();
}

function addQuestion3(){
	var alertstr = "";
	var showalert=false;
    var id=document.questionGroupForm.questionId.value;
    if(id == "0"){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.question_notselect",user16.getLocale())%><BR>";
		showalert = true;
	}

	 if (showalert){
     	alert(alertstr);
        return false;
          }
    document.questionGroupForm.action = "questiongroup.do?method=addQuestion&questiongroupid=<%=questionsgroupform.getQuestiongroupId()%>&questionid="+id;
    document.questionGroupForm.submit();
}


function backtolistpage(){
	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user16.getLocale())%>");
	 if (doyou == true){
     document.questionGroupForm.action = "questiongroup.do?method=questionsgrouplist";
     document.questionGroupForm.submit();
}
}
</script>
<html:form action="/questiongroup.do?method=abc" target="_parent" >

<table>	
 <tr>
    <td><b><%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.addquestion",user16.getLocale())%></b></td><td></td>
    </tr>
    <br>
 <tr>
   <td><%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.question",user16.getLocale())%>&nbsp;&nbsp;
   <html:select property="questionId">
   <bean:define name="questionGroupForm" property="questions" id="questions" />
   <option value="0"><%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.question_option",user16.getLocale())%></option>	
   <html:options collection="questions" property="questionId"  labelProperty="questionName"/>
   </html:select>
    </td>
    <td></td>
 </tr>
 <tr>
   <td>&nbsp;&nbsp;</td>
   <td>&nbsp;&nbsp;</td>
 </tr>
 <tr>
    <td>&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;</td>
 </tr>
 <tr>

   <td>
      <input type="button" name="add" value="<%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.buttonvalue.add",user16.getLocale())%>" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.buttonvalue.add.alt",user16.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.buttonvalue.add.alt",user16.getLocale())%>" class="button" onClick="addQuestion3()"/>
      &nbsp;
      <input type="button" name="create" value="<%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.buttonvalue.button.createnew",user16.getLocale())%>" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.buttonvalue.button.createnew.alt",user16.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.buttonvalue.button.createnew.alt",user16.getLocale())%>" class="button" onClick="createNewQuestion()"/>
      &nbsp;
      <input type="button" name="cancel" value="<%=Constant.getResourceStringValue("hr.button.cancel",user16.getLocale())%>" border="0" alt="<%=Constant.getResourceStringValue("hr.button.cancel.alt",user16.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.button.cancel.alt",user16.getLocale())%>" class="button" onclick="backtolistpage()"/>
  </td>
  <td></td>
</tr>
</table>
</html:form>

