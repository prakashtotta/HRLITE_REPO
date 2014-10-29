

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<head>
<link rel="stylesheet" type="text/css" href="jsp/css/ajaxtabs.css" />

<script type="text/javascript" src="jsp/js/ajaxtabs.js">

/***********************************************
* Ajax Tabs Content script v2.2- � Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>



</head>
<script language="javascript">
       
	  document.getElementById('progressbartable1').style.display = 'inline';   

</script>
<body>

<br>
<ul id="countrytabs" class="shadetabs">
<%
String competenciesHometab = (String)request.getAttribute("competenciesHome");
if(competenciesHometab != null && competenciesHometab.equals("competencies")){
%>
   <li><a href="char.do?method=charlist" rel="" class="selected"><%=Constant.getResourceStringValue("admin.Competencies",user1.getLocale())%></a></li>

<%}else{%>
   <li><a href="char.do?method=charlist" rel="" class=""><%=Constant.getResourceStringValue("admin.Competencies",user1.getLocale())%></a></li>
<%}%>

<% if(competenciesHometab != null && competenciesHometab.equals("accomplishmnet")){
%>
   <li><a href="accomplishment.do?method=Accomplishmentlist" rel="" class="selected"><%=Constant.getResourceStringValue("admin.Accomplishment.accomplishment",user1.getLocale())%></a></li>
<%}else{%>
   <li><a href="accomplishment.do?method=Accomplishmentlist" rel=""><%=Constant.getResourceStringValue("admin.Accomplishment.accomplishment",user1.getLocale())%></a></li>
<%}%>

<% if(competenciesHometab != null && competenciesHometab.equals("technicalskills")){
%>
   <li><a href="technicalskills.do?method=technicalskillslist" rel="" class="selected"><%=Constant.getResourceStringValue("admin.technicalskills.techskillls",user1.getLocale())%></a></li>
<%}else{%>
   <li><a href="technicalskills.do?method=technicalskillslist" rel=""><%=Constant.getResourceStringValue("admin.technicalskills.techskillls",user1.getLocale())%></a></li>
<%}%>

<% if(competenciesHometab != null && competenciesHometab.equals("education")){
%>
   <li><a href="education.do?method=educationlist" rel="" class="selected"><%=Constant.getResourceStringValue("admin.education.education",user1.getLocale())%></a></li>
<%}else{%>
   <li><a href="education.do?method=educationlist" rel=""><%=Constant.getResourceStringValue("admin.education.education",user1.getLocale())%></a></li>
<%}%>



<!--<li><a href="externalnested.htm" rel="countrycontainer">Tab 4</a></li> -->
</ul>

<div id="countrydivcontainer" style="border:1px solid gray; width:1200px; margin-bottom: 1em; padding: 10px">
<span id='progressbartable1'>

</span>
<%
String competenciesHome = (String)request.getAttribute("competenciesHome");
	
if(competenciesHome != null && competenciesHome.equals("competencies")){
%>
    <%@ include file="charlistBody.jsp" %>

<%}else if(competenciesHome != null && competenciesHome.equals("accomplishmnet")){%>

   <%@ include file="accomplishmentlistBody.jsp" %>
   
<%}else if(competenciesHome != null && competenciesHome.equals("technicalskills")){%>

 <%@ include file="technicalskillsBody.jsp" %>
 
<%} if(competenciesHome != null && competenciesHome.equals("education")){%>

 <%@ include file="educationBody.jsp" %>

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
