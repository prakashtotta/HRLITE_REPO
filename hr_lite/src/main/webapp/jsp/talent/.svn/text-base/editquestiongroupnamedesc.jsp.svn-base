<%@ include file="../common/include.jsp" %>
<%@ page import="com.bean.*"%>
<%
User user15 = (User)request.getSession().getAttribute(Common.USER_DATA);
%>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<style type="text/css">

.initial2{font-weight:bold;background-color:#c00;color:#fff;}

</style>
</head>


<script language="javascript">
function change(e, color){
    var el=window.event? event.srcElement: e.target
    if (el.tagName=="INPUT"&&el.type=="button")
    el.style.backgroundColor=color
}
	</script>
 
 <html:form action="/questiongroup.do?method=saveQuestionsGroup34345">
<bean:define id="questionsgroupform" name="questionGroupForm" type="com.form.QuestionsGroupForm" />
			
<span id="editNameDesc">
<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">
<tr>
<td valign="top" width="20"></td>
<td valign="top" width="2000">
<table border="0" cellpadding="0" cellspacing="0">
 

 <tr> <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="100" height="1"></td>
      <td width="240" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="40" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="850" height="1"></td>
      <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
 </tr>




<tr>

<td colspan="4">

<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">



<tr>
<td> 
  <tr>
    <td><b><%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.name",user15.getLocale())%></b><font color="red">*</font></td>
    <td>
	
	<html:text property="questiongroupName" maxlength="200" size="83"/></td>

  </tr>
  <tr>
    <td>
    <b><%=Constant.getResourceStringValue("admin.JobGrade.desc",user15.getLocale())%></b><font color="red">*</font></td>

<td valign="top">

 <textarea name="questiongroupDesc" id="questiongroupDesc" rows="3" cols="80"><%=questionsgroupform.getQuestiongroupDesc()%></textarea> 
<img src="jsp/images/spacer.gif" width="98" height="1" >

<input type="button" name="save" value="<%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.button.update",user15.getLocale())%>" border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.updatebutton.alt",user15.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.updatebutton.alt",user15.getLocale())%>" onClick="updatedata()" class="button"/>&nbsp;&nbsp;

<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user15.getLocale())%>" border="0" alt="<%=Constant.getResourceStringValue("hr.button.cancel.alt",user15.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.button.cancel",user15.getLocale())%>" onClick="backtoaddquestionlistpage2()" class="button"></td>
</tr>








</td>

</tr>
</table>
	
	
</td>
</tr>

<tr><td colspan="5" class="BodyToolboxHeader">&nbsp;&nbsp;</td></tr>
<tr> <td colspan="5" bgcolor="#999999"><img src="jsp/images/spacer.gif"  height="1"></td></tr>
</table>
</td>

</tr>
</table>






















 
 

</span>

</html:form>
</html:html>