

<%
String path2 = (String)request.getAttribute("filePath");
User user2 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern2 = DateUtil.getDatePatternFormat(user2.getLocale());
%>

<head>
<link rel="stylesheet" type="text/css" href="jsp/css/ajaxtabs.css" />

<script type="text/javascript" src="jsp/js/ajaxtabs.js"></script>

</head>
<script language="javascript">
       
	  document.getElementById('progressbartable1').style.display = 'inline';   

</script>
<body>


<ul id="countrytabs" class="shadetabs">
<%
String questionHometab = (String)request.getAttribute("questionHome");
if(questionHometab != null && questionHometab.equals("questiongroup")){
%>
   <li><a href="questiongroup.do?method=questionsgrouplist" rel="" class="selected"><%=Constant.getResourceStringValue("hr.applicant.Questionnaire",user2.getLocale())%></a></li>

<%}else{%>
   <li><a href="questiongroup.do?method=questionsgrouplist" rel="" class=""><%=Constant.getResourceStringValue("hr.applicant.Questionnaire",user2.getLocale())%></a></li>
<%}%>

<% if(questionHometab != null && questionHometab.equals("question")){
%>
   <li><a href="question.do?method=questionslist" rel="" class="selected"><%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.question",user2.getLocale())%></a></li>
<%}else{%>
   <li><a href="question.do?method=questionslist" rel=""><%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.question",user2.getLocale())%></a></li>
<%}%>



<!--<li><a href="externalnested.htm" rel="countrycontainer">Tab 4</a></li> -->
</ul>

<div id="countrydivcontainer" style="border:1px solid gray; width:1210px; margin-bottom: 1em; padding: 10px">
<span id='progressbartable1'>

</span>
<%
String questionHome = (String)request.getAttribute("questionHome");
	
if(questionHome != null && questionHome.equals("questiongroup")){
%>
    <%@ include file="questionsGroupListBody.jsp" %>

<%}else
if(questionHome != null && questionHome.equals("question")){%>

   <%@ include file="questionListBody.jsp" %>
<%}%>


</div>


		<script type="text/javascript">

		var countries=new ddajaxtabs("countrytabs", "countrydivcontainer")
		countries.setpersist(true)
		countries.setselectedClassTarget("link") //"link" or "linkparent"
		countries.init()

		countries.onajaxpageload=function(pageurl){
		if (pageurl.indexOf("externalnested.htm")!=-1){
		var provinces=new ddajaxtabs("provincetabs", "provincedivcontainer")
		provinces.setpersist(true)
		provinces.setselectedClassTarget("link") //"link" or "linkparent"
		provinces.init()
		}

		}


		</script>

	
</body>
</html>
