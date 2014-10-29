<%@ include file="../common/include.jsp" %>
<%@ page import="com.common.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%
String path5 = (String)request.getAttribute("filepath5");
User user15 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern5 = DateUtil.getDatePatternFormat(user15.getLocale());
%>
<style>
span1{color:#ff0000;}
</style>
<style type="text/css">

.initial2{font-weight:bold;background-color:#c00;color:#fff;"}

</style>

<bean:define id="questionsgroupform" name="questionGroupForm" type="com.form.QuestionsGroupForm" />


<script language="javascript">

function change(e, color){
var el=window.event? event.srcElement: e.target
if (el.tagName=="INPUT"&&el.type=="button")
el.style.backgroundColor=color
}

function savedata(){
		var alertstr = "";
	var showalert=false;
		
	 
	var name=document.questionGroupForm.questiongroupName.value.trim();
	var desc=document.questionGroupForm.questiongroupDesc.value.trim();
	
	
		
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ques_group_Name_required",user15.getLocale())%><br>";
		showalert = true;
		}
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ques_disc_required",user15.getLocale())%><br>";
		showalert = true;
		}
	
	if(desc.length > 500){

     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.QuestionsGroup.desc",user15.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user15.getLocale())%><br>";
		showalert = true;
	}		
	 	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.questionGroupForm.action = "questiongroup.do?method=saveQuestionsGroup&readPreview=3";
 document.questionGroupForm.submit();

}



function updatedata(){
		var alertstr = "";
	var showalert=false;
		
	 
	var name=document.questionGroupForm.questiongroupName.value.trim();
	var desc=document.questionGroupForm.questiongroupDesc.value.trim();
	
	
		
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ques_group_Name_required",user15.getLocale())%><br>";
		showalert = true;
		}
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Ques_disc_required",user15.getLocale())%><br>";
		showalert = true;
		}
	
	if(desc.length > 500){

     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.QuestionsGroup.desc",user15.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user15.getLocale())%><br>";
		showalert = true;
	}		
	 	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.questionGroupForm.action = "questiongroup.do?method=updategroupNameDesc&questiongroupid=<%=questionsgroupform.getQuestiongroupId()%>&questiongroupname="+name+"&questiongroupdesc="+desc;
 document.questionGroupForm.submit();

}




function backtoaddquestionlistpage2(){

var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user15.getLocale())%>");
if (doyou == true){
document.questionGroupForm.action = "questiongroup.do?method=backAddQuestionpage&questiongroupId=<%=questionsgroupform.getQuestiongroupId()%>";
      document.questionGroupForm.submit();

}

}
</script>


<html:form action="/questiongroup.do?method=saveQuestionsGroup34345" target="_parent">

<%
String DeleteQuestionsGroup = (String)request.getAttribute("DeleteQuestionsGroup");

	if(DeleteQuestionsGroup != null && DeleteQuestionsGroup.equals("yes")){%>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <div class="msg"> <font color="white"><%=Constant.getResourceStringValue("admin.QuestionsGroup.deletemessage.q_group",user15.getLocale())%></font></div>


<%}else{%>

<%
String quesgroupedit = (String)request.getAttribute("quesgroupedit");
%>

    <%if(quesgroupedit != null && quesgroupedit.equals("yes")){%>

  <table>
  <tr>
    <td><b><%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.name",user15.getLocale())%></b><font color="red">*</font></td>
    <td><html:text property="questiongroupName" maxlength="200" size="83"/></td>
  </tr>
  <tr>
    <td>
    <b><%=Constant.getResourceStringValue("admin.JobGrade.desc",user15.getLocale())%></b><font color="red">*</font></td>

<td valign="top">

 <textarea name="questiongroupDesc" id="questiongroupDesc" rows="3" cols="80"><%=questionsgroupform.getQuestiongroupDesc()%></textarea> 
<img src="jsp/images/spacer.gif" width="98" height="1" >

<input type="button" name="save" value="<%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.button.update",user15.getLocale())%>" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.updatebutton.alt",user15.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.updatebutton.alt",user15.getLocale())%>" onClick="updatedata()" class="button"/>&nbsp;&nbsp;

<input type="button" name="login" border="0" alt="<%=Constant.getResourceStringValue("hr.button.cancel.alt",user15.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.button.cancel.alt",user15.getLocale())%>" class="button" onClick="backtoaddquestionlistpage2()"></a></td>
</tr>
</table>
<%}else{%>

<table>
<tr>

<b><%=Constant.getResourceStringValue("admin.QuestionsGroup.CreateQuestionsGroup",user15.getLocale())%></b>

</tr>
<br>
<tr>
<td><%=Constant.getResourceStringValue("admin.QuestionsGroup.name",user15.getLocale())%><font color="red">*</font></td>

<td><html:text property="questiongroupName" maxlength="200" size="55"/></td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("admin.QuestionsGroup.desc",user15.getLocale())%><font color="red">*</font></td>
 
 <td><textarea name="questiongroupDesc" id="questiongroupDesc" rows="3" cols="52"><%=(questionsgroupform.getQuestiongroupDesc()==null)?"":questionsgroupform.getQuestiongroupDesc()%></textarea> </td>
</tr>

<tr><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td></tr>
<tr><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td></tr>

<tr>
<td><input type="button" name="save" value="<%=Constant.getResourceStringValue("admin.QuestionsGroup.CreateQuestionsGroup",user15.getLocale())%>" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.CreateQuestionsGroup.createbutton.alt",user15.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.CreateQuestionsGroup.createbutton.alt",user15.getLocale())%>" onClick="savedata()" class="button"/></a></td>
<td></td>

</tr>


</table>




<%}%>
<%}%>
</html:form>