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

int correctCount=0;
 for (int i =0; i<mockTestResultList.size();i++){
	MockTestResult mkr1 = (MockTestResult)mockTestResultList.get(i);

	if(mkr1.getIsCorrect()==1){
		correctCount++;
	}
 }

BOFactory.getMockTestBO().finishMockTest(testid,correctCount,mockTestResultList.size());

%>
<table width="100%" border="0">
  <tr><td width="10%"></td>
  <td>
<table bgcolor=white border="0" width="90%"> 
<tr>
<td>
<b> You are done with the test. we will get back to you.</b>
</td>
</tr>
</table>
</td>
</tr>
</table>



  

	

