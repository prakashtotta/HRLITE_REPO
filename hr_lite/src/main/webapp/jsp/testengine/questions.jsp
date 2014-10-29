<%@ page import="java.util.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bo.*"%>
<%@ page import="com.dao.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.util.*"%>


<%
List questionsIdList = (List)session.getAttribute("questionsIdList");
%>

<%
boolean ismarked = false;
boolean isNotMarked = false;
String in = (String)request.getParameter("in");
Long qnid = (Long)questionsIdList.get(new Integer(in).intValue());

int in1= new Integer(in).intValue();

MockTestQuestion  mockTestQuestion = BOFactory.getMockTestBO().getMockQuestionById(qnid.longValue());




String cmd = (String)request.getParameter("cmd");
System.out.println("000");
Long testid  = (Long)session.getAttribute("testid");



System.out.println("11");

if(cmd != null && cmd.equals("qns")){
    String qid  = (String)request.getParameter("qid");
	mockTestQuestion = BOFactory.getMockTestBO().getMockQuestionById(new Long(qid).longValue());

}

if(cmd != null && cmd.equals("showm")){

	List mockTestResultList = BOFactory.getMockTestBO().getAllMarkedMockTestResult(testid.longValue());
	if(mockTestResultList.size()>0){
	questionsIdList = new ArrayList();
	for(int j=0;j<mockTestResultList.size();j++){
		MockTestResult mk = (MockTestResult)mockTestResultList.get(j);
		questionsIdList.add(new Long(mk.getMockQuestionId()));
	}
    session.setAttribute("questionsIdList",questionsIdList);
	Long qnid1 = (Long)questionsIdList.get(new Integer(in).intValue());
	mockTestQuestion = BOFactory.getMockTestBO().getMockQuestionById(qnid1.longValue());
	} else {
    isNotMarked = true;
	}

}

if(cmd != null && cmd.equals("shows")){

	List mockTestResultList = BOFactory.getMockTestBO().getAllSkipedMockTestResult(testid.longValue());
	questionsIdList = new ArrayList();
	for(int j=0;j<mockTestResultList.size();j++){
		MockTestResult mk = (MockTestResult)mockTestResultList.get(j);
		questionsIdList.add(new Long(mk.getMockQuestionId()));
	}
    session.setAttribute("questionsIdList",questionsIdList);
	Long qnid1 = (Long)questionsIdList.get(new Integer(in).intValue());
	mockTestQuestion = BOFactory.getMockTestBO().getMockQuestionById(qnid1.longValue());

}

if(cmd != null && cmd.equals("n")){


	String qid  = (String)request.getParameter("qid");

System.out.println("2");
MockTestResult mockResult = BOFactory.getMockTestBO().getMockTestResult(testid.longValue(),new Long(qid).longValue());
MockTestQuestion  question = BOFactory.getMockTestBO().getMockQuestionById(new Long(qid).longValue());
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
System.out.println("3");
if(cmd != null && cmd.equals("p")){
	int i = new Integer(in).intValue()-1;
	in = String.valueOf(i);


}

if(cmd != null && cmd.equals("m")){
	String qid  = (String)request.getParameter("qid");

MockTestResult mockResult = BOFactory.getMockTestBO().getMockTestResult(testid.longValue(),new Long(qid).longValue());
	MockTestQuestion  question = BOFactory.getMockTestBO().getMockQuestionById(new Long(qid).longValue());
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
      BOFactory.getMockTestBO().updateMockTestResult(mockResult);

System.out.println("2");

mockResult.setIsMarked(1);
 BOFactory.getMockTestBO().updateMockTestResult(mockResult);

}

if(cmd != null && cmd.equals("mk")){
	String qid  = (String)request.getParameter("qid");

MockTestResult mockResult = BOFactory.getMockTestBO().getMockTestResult(testid.longValue(),new Long(qid).longValue());
	MockTestQuestion  question = BOFactory.getMockTestBO().getMockQuestionById(new Long(qid).longValue());
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
      BOFactory.getMockTestBO().updateMockTestResult(mockResult);

System.out.println("2");

mockResult.setIsMarked(0);
 BOFactory.getMockTestBO().updateMockTestResult(mockResult);

}



MockTestResult mockResult1 = BOFactory.getMockTestBO().getMockTestResult(testid.longValue(),qnid.longValue());
int select = 0;

if(mockResult1.getAnsNo() != 0){	

	select = mockResult1.getAnsNo();
}

if(mockResult1.getIsMarked()==1){
	ismarked=true;
}

%>
<form name="regform" method="post">
<table width="100%" border="0">
  <tr><td width="10%"></td>
  <td>
<table bgcolor=white border="0" width="90%">  
<%if(cmd != null && cmd.equals("view")){%>
<tr>
		<td height="20" width="662" bgcolor="#000066">
		<input type="button" value="end exam" onclick="end()">  &nbsp;&nbsp;&nbsp;<input type="button" value="show marked" onclick="showmarked()"> 
		&nbsp;&nbsp;&nbsp;<input type="button" value="show skiped" onclick="showskiped()"> 
		</td>
</tr>
<tr>
<td height="500" width="662" bgcolor="white">
<table height="500" width="662" >
<%
List mockTestResultList = BOFactory.getMockTestBO().getAllMockTestResult(testid.longValue());
questionsIdList = new ArrayList();
for(int j1=0;j1<mockTestResultList.size();j1++){
		MockTestResult mk = (MockTestResult)mockTestResultList.get(j1);
		questionsIdList.add(new Long(mk.getMockQuestionId()));
	}
    session.setAttribute("questionsIdList",questionsIdList);

%>
<% for (int kk =0; kk<mockTestResultList.size();kk++){
	MockTestResult mkr = (MockTestResult)mockTestResultList.get(kk);
%>
<tr>
<td height="20" width="140" border=1 bgcolor=#1692B8><input type="button" value="Q.<%=mkr.getQuestionno()%>" onclick="questions('<%=mkr.getMockQuestionId()%>','<%=mkr.getQuestionno()-1%>')">&nbsp;&nbsp;<%=(mkr.getAnsNo()==0)?"":mkr.getAnsNo()%>	&nbsp;&nbsp;<font color="red"><%=(mkr.getIsMarked()==0)?"":"marked"%></font></td>
<%
	++kk;
    if(kk < mockTestResultList.size()){
	mkr = (MockTestResult)mockTestResultList.get(kk);
%>
<td height="20" width="140" border=1 bgcolor=#1692B8><input type="button" value="Q.<%=mkr.getQuestionno()%>" onclick="questions('<%=mkr.getMockQuestionId()%>','<%=mkr.getQuestionno()-1%>')">&nbsp;&nbsp;<%=(mkr.getAnsNo()==0)?"":mkr.getAnsNo()%>	&nbsp;&nbsp;<font color="red"><%=(mkr.getIsMarked()==0)?"":"marked"%></font></td>
	<%}%>
<%
	++kk;
if(kk < mockTestResultList.size()){
	mkr = (MockTestResult)mockTestResultList.get(kk);
%>
<td height="20" width="140" border=1 bgcolor=#1692B8><input type="button" value="Q.<%=mkr.getQuestionno()%>" onclick="questions('<%=mkr.getMockQuestionId()%>','<%=mkr.getQuestionno()-1%>')">&nbsp;&nbsp;<%=(mkr.getAnsNo()==0)?"":mkr.getAnsNo()%>	&nbsp;&nbsp;<font color="red"><%=(mkr.getIsMarked()==0)?"":"marked"%></font></td>
	<%}%>
	<%
	++kk;
if(kk < mockTestResultList.size()){
	mkr = (MockTestResult)mockTestResultList.get(kk);
%>
<td height="20" width="140" border=1 bgcolor=#1692B8><input type="button" value="Q.<%=mkr.getQuestionno()%>" onclick="questions('<%=mkr.getMockQuestionId()%>','<%=mkr.getQuestionno()-1%>')">&nbsp;&nbsp;<%=(mkr.getAnsNo()==0)?"":mkr.getAnsNo()%>	&nbsp;&nbsp;<font color="red"><%=(mkr.getIsMarked()==0)?"":"marked"%></font></td>
	<%}%>

</tr>
<%}%>

</table>
</td>
</tr>

<tr>
		<td height="20" width="662" bgcolor="#000066">
		<input type="button" value="end exam" onclick="end()">  &nbsp;&nbsp;&nbsp;<input type="button" value="show marked" onclick="showmarked()"> 
		&nbsp;&nbsp;&nbsp;<input type="button" value="show skiped" onclick="showskiped()"> 
		</td>
</tr>
<%}else{%>

<% if(isNotMarked){%>

<tr bgcolor="red">
		<td bordercolor="#C4D9EC" class=bodytext height="139" width="662" valign="top"><b>Not marked any questions. </b><br> 
		<br></td>
	</tr>
<%}%>

	<tr bgcolor="white">
		<td bordercolor="#C4D9EC" class=bodytext height="16" width="662" valign="top">
	
	</td>
	</tr>

<tr bgcolor="white">
	
		<td bordercolor="#C4D9EC"  width="662" valign="top"><b>Question <%=mockTestQuestion.getQuestionno()%>  of <%=questionsIdList.size()%> </b>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<% if(ismarked){%>
	   &nbsp;&nbsp;&nbsp;
		<input type=checkbox onClick="marked()" checked=true><font color=red> marked for review</font> </input>   
	   </span>
     <%}else{%>
			&nbsp;&nbsp;&nbsp;
		   <input type=checkbox onClick="mark()"> <font color=red>mark for review</font> </input>
 
	<%}%>	
		<br>
		<%=StringUtils.doSpecialCharacters(mockTestQuestion.getQuestion())%>
		<br></td>
	</tr>
	<tr bgcolor="white">
		<td bordercolor="#C4D9EC" class=bodytext height="16" width="662" valign="top">
	
	</td>
	</tr>
	<tr bgcolor="white">
		<td bordercolor="#C4D9EC" class=bodytext height="16" width="662" valign="top">1.&nbsp;&nbsp;<input type="radio" name="group1" value="1" <%=(select==1)?"checked":""%>>  <%=StringUtils.doSpecialCharacters(mockTestQuestion.getAns1())%></td>
	</tr>
	<tr bgcolor="white">
		<td bordercolor="#C4D9EC" class=bodytext height="16" width="662" valign="top">2.&nbsp;&nbsp;<input type="radio" name="group1" value="2" <%=(select==2)?"checked":""%>> <%=StringUtils.doSpecialCharacters(mockTestQuestion.getAns2())%></td>
	</tr>
	<tr bgcolor="white">
		<td bordercolor="#C4D9EC" class=bodytext height="16" width="662" valign="top">3.&nbsp;&nbsp;<input type="radio" name="group1" value="3" <%=(select==3)?"checked":""%>> <%=StringUtils.doSpecialCharacters(mockTestQuestion.getAns3())%></td>
	</tr>
	<tr bgcolor="white">
		<td bordercolor="#C4D9EC" class=bodytext height="16" width="662" valign="top">4.&nbsp;&nbsp;<input type="radio" name="group1" value="4" <%=(select==4)?"checked":""%>> <%=StringUtils.doSpecialCharacters(mockTestQuestion.getAns4())%></td>
	</tr>

	<tr  bgcolor="white" width="662" >
		<td height="40px" width="662" bgcolor="white">
<% if(in1 > 0){%>
       <input type="button" value="<<previous" onclick="previous()">
	   <%}%>
	   &nbsp;&nbsp;&nbsp;
	   <% if(in1 < questionsIdList.size()-1){%>
	   <input type="button" value="next>>" onclick="next()"> 
	   <%}%>
	
		
       &nbsp;&nbsp;&nbsp;
       <input type="button" value="view all answers" onclick="view()">  &nbsp;&nbsp;&nbsp;<input type="button" value="finish exam" onclick="end()"> 
		</td>
	</tr>

</table>
	</td>
	</tr>
	</table>
<%}%>
</form>
	

<script language="javascript">

	function next() {
				document.regform.action ="<%=request.getContextPath()%>/jsp/testengine/questions.jsp?cmd=n&in=<%=in1+1%>&qid=<%=mockTestQuestion.getMockquestionId()%>";
		document.regform.submit();
	}

	function previous() {
				document.regform.action ="<%=request.getContextPath()%>/jsp/testengine/questions.jsp?cmd=p&in=<%=in1-1%>";
		document.regform.submit();
	}

    function mark() {
				document.regform.action ="<%=request.getContextPath()%>/jsp/testengine/questions.jsp?cmd=m&in=<%=in1%>&qid=<%=mockTestQuestion.getMockquestionId()%>";
		document.regform.submit();
	}

	function marked() {
				document.regform.action ="<%=request.getContextPath()%>/jsp/testengine/questions.jsp?cmd=mk&in=<%=in1%>&qid=<%=mockTestQuestion.getMockquestionId()%>";
		document.regform.submit();
	}

	function view() {
		document.regform.action ="<%=request.getContextPath()%>/jsp/testengine/questions.jsp?cmd=view&in=<%=in1%>&qid=<%=mockTestQuestion.getMockquestionId()%>";				
		document.regform.submit();
	}

	function questions(qid,qno) {
				document.regform.action ="<%=request.getContextPath()%>/jsp/testengine/questions.jsp?cmd=qns&in="+qno+"&qid="+qid;
		document.regform.submit();
	}

    function showmarked() {
		document.regform.action ="<%=request.getContextPath()%>/jsp/testengine/questions.jsp?cmd=showm&in=0";				
		document.regform.submit();
	}

	 function showskiped() {
		document.regform.action ="<%=request.getContextPath()%>/jsp/testengine/questions.jsp?cmd=shows&in=0";				
		document.regform.submit();
	}

  


	function end(){
		var fRet;
fRet = confirm('Are you sure to end this test? You will not able to start test again');

if(fRet){
document.regform.action ="<%=request.getContextPath()%>/jsp/testengine/pmpresult.jsp?testid=<%=testid%>&qid=<%=mockTestQuestion.getMockquestionId()%>";				
		document.regform.submit();

}

	}


	</script>
