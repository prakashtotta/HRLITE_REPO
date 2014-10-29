<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
 <html>
  <HEAD>


 <style>
span1{color:#ff0000;}
</style>


<bean:define id="questionsgroupform" name="questionGroupForm" type="com.form.QuestionsGroupForm" />




<body>

<html:form action="/questiongroup.do?method=saveQuestionsGroup">

<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	

	<tr>
			<td><u><%=Constant.getResourceStringValue("admin.QuestionsGroup.detail",user1.getLocale())%> </u></td>
			<td></td>
		</tr>
	  
	   <tr>
			<td><%=Constant.getResourceStringValue("admin.QuestionsGroup.name",user1.getLocale())%> :</td>
			<td><%= questionsgroupform.getQuestiongroupName() %></td>
		</tr>	
		<tr>
			<td><%=Constant.getResourceStringValue("admin.QuestionsGroup.desc",user1.getLocale())%> :</td>
			<td><%= questionsgroupform.getQuestiongroupDesc() %></td>
		</tr>

        		<%
		String deptvalue ="<span id=\"questiongroup\">";
		
		//getQuestions() get null
        if(questionsgroupform.getQuestions()!= null && questionsgroupform.getQuestions().size()>0){
        	System.out.println("**** getquestion : "+questionsgroupform.getQuestions());
    		System.out.println("**** getquestion  size : "+questionsgroupform.getQuestions().size());
   		String deptpurl = "";

		for(int i=0;i<questionsgroupform.getQuestions().size();i++){
			Questions ch = (Questions)questionsgroupform.getQuestions().get(i);
			deptpurl = deptpurl + ch.getQuestionName() + "<br>";

		}

 		deptvalue = "<span id=\"questiongroup\">"+deptpurl+"</span>";
		}

		%>
		        <tr>
			<td><%=Constant.getResourceStringValue("admin.QuestionsGroup.questions",user1.getLocale())%> :</td>
			<td>
				<span id="question"></span>
				<input type="hidden" name="questionId" value=''/>
				
				<br><%=deptvalue%>
				</td>
		</tr>

				
		</table>
</div>

</html:form>
</body>

