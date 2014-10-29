<%@ page import="java.util.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bo.*"%>
<%@ page import="com.dao.*"%>
<%@ page import="com.test.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.bean.testengine.*"%>


<%

String testid = (String)request.getParameter("testid");
String qid  = (String)request.getParameter("qid");

String cmd  = (String)request.getParameter("cmd");

int catid1=0;

if(cmd != null && cmd.equals("result")){
}else{

System.out.println("2");
MockTestResult mockResult = BOFactory.getMockTestBO().getMockTestResult(new Long(testid).longValue(),new Long(qid).longValue());
MockTestQuestion question = BOFactory.getMockTestBO().getMockQuestionById(new Long(qid).longValue());

catid1 = question.getCatId();

	String group1 = (String)request.getParameter("group1");

	if(!StringUtils.isNullOrEmpty(group1)){
			if(new Integer(group1).intValue()==question.getCorrect()){
				mockResult.setIsCorrect(1);
			}else{
				mockResult.setIsCorrect(0);
			}
			mockResult.setAnsNo(new Integer(group1).intValue());
		}else{
			mockResult.setIsCorrect(0);
			mockResult.setAnsNo(0);
		}

      if(mockResult.getIsMarked() == 1 && mockResult.getAnsNo() == 0){


	  }else if(mockResult.getIsMarked() == 1 && mockResult.getAnsNo() != 0){

       BOFactory.getMockTestBO().updateMockTestResult(mockResult);

	  }else {

      BOFactory.getMockTestBO().updateMockTestResult(mockResult);
	  }


}





List questionsIdList = (List)session.getAttribute("questionsIdList");

if(questionsIdList != null){
session.removeAttribute("questionsIdList");
}



List mockTestResultList = BOFactory.getMockTestBO().getAllMockTestResult(new Long(testid).intValue());

float correctCount=0;
 for (int i =0; i<mockTestResultList.size();i++){
	MockTestResult mkr1 = (MockTestResult)mockTestResultList.get(i);

	if(mkr1.getIsCorrect()==1){
		correctCount++;
	}
 }



float percent = (correctCount/mockTestResultList.size())*100;

int p = (int)percent;

String examname = "PMP";
String catid = (String)session.getAttribute("catid");

if(catid != null && Integer.parseInt(catid) == 61){
	examname = "SCJP";
}
if(catid != null && Integer.parseInt(catid) == 60){
	examname = "SCJP";
}

%>
<table width="100%" border="0" cellpadding="3" cellspacing="0" height="40px">
  <tr><td width="10%"></td>
  <td>
<h3> Total Correct Answers : <%=(int)correctCount%> </h3>

<h3> Percentage : <%=p%> % </h3>

<%  if(percent > 75) {%>
<img src="tick.png"> <font color=green size=4> <b>Go ahead with <%=examname%> exam !!!</b> </font>
	<br>
<%}else{%>
<img src="cross.png"> <font color=red size=4> <b>need more practice !!!</b> </font>
	<br>
<%}%>
<br><br>

<% if(catid1 == 3){%>
<jsp:include page="subresult1.jsp">	
   <jsp:param name="testid" value="<%=testid%>" />	
</jsp:include>
<%}%>

  
<table cellspacing=15>
<tr bgcolor="#F2F4F4">
<td><h3>Question No.</h3></td><td><h3>Selected Choice</h3></td><td><h3>Correct Answer</h3></td><td><h3>Status</h3></td><td><h3>Explanations</h3></td> 
</tr>

<% for (int kk =0; kk<mockTestResultList.size();kk++){
	MockTestResult mkr = (MockTestResult)mockTestResultList.get(kk);
%>

<% if ((kk % 2) == 0 ){%>
<tr bgcolor="#F2F4F4">
<%} else {%>
<tr bgcolor="white">
<%}%>
<td><%=mkr.getQuestionno()%></td><td><%=(mkr.getAnsNo()==0)?"":mkr.getAnsNo()%></td><td><%=mkr.getCorrect()%></td>
	<td>
	<% if(mkr.getIsCorrect()==1){%> <img src="tick.png"> <%}else{%><img src="cross.png"><%}%></td>
	<td><a href="pmpans.jsp?qid=<%=mkr.getMockQuestionId()%>&testid=<%=testid%>&sl=<%=mkr.getAnsNo()%>">details</a></td>
</tr>



<%}%>

</table>
</td>
	</tr>
	</table>




  

	

