<%@ include file="../common/include.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


<bean:define id="form" name="evaluationTemplateForm" type="com.form.EvaluationTemplateForm" />



<html:form action="/evtmpl.do?method=searchgroupchars">
	<table border="0" width="100%">
	<h3><%=Constant.getResourceStringValue("admin.EvaluationTemplate.Evaluation_Sheet_for_Interviews",user1.getLocale())%></h3>
    <tr>
				<td width="300"><%=Constant.getResourceStringValue("admin.EvaluationTemplate.evaluationsheet",user1.getLocale())%> :</td>
				<td><bean:write name="evaluationTemplateForm" property="evTmplName"/></td>
			</tr>

	    <tr>
				<td width="300"><%=Constant.getResourceStringValue("admin.EvaluationTemplate.desc",user1.getLocale())%> :</td>
				<td><bean:write name="evaluationTemplateForm" property="evTmplDesc"/></td>
			</tr>		
				
</table>
<%
 List groupcharList = form.getGroupcharList();
  if(groupcharList != null){
System.out.println("groupcharList"+groupcharList.size());
	 for(int i=0;i<groupcharList.size();i++){

		 GroupCharacteristic grpcharacteristic = (GroupCharacteristic)groupcharList.get(i);

%>

<table border="0" width="100%">
<tr>
<td bgcolor="gray" width="60%"><%=grpcharacteristic.getGroupCharName()%></td><td bgcolor="gray"><%=Constant.getResourceStringValue("admin.EvaluationTemplate.Weight",user1.getLocale())%></td>
</tr>
</table>
<%
	List charList = grpcharacteristic.getCharList();
 
 if(charList != null){

	 for(int j=0;j<charList.size();j++){

		 Characteristic characteristic = (Characteristic)charList.get(j);
%>
<table border="0" width="100%">
<tr>
<td width="60%"><%=characteristic.getCharName()%></td><td><%=characteristic.getWeight()%>
</tr>
</table>
 <%
	 }
	 }
	 }
  }

%>




</html:form>