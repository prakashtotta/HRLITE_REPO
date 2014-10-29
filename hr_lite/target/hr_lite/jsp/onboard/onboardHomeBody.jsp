

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<head>
<link rel="stylesheet" type="text/css" href="jsp/css/ajaxtabs.css" />

<script type="text/javascript" src="jsp/js/ajaxtabs.js">

/***********************************************
* Ajax Tabs Content script v2.2- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>


<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 350px;
	height: 300px;
	
}

legend {
	background: #eee;
}
#modelDescription {
	background: #eee;
}

</style>


</head>
<script language="javascript">
       
	  document.getElementById('progressbartable1').style.display = 'inline';   

</script>
<br>
<body>

<ul id="countrytabs" class="shadetabs">
<%
String onboardingHomefortab = (String)request.getAttribute("onboardingHome");
if(onboardingHomefortab != null && onboardingHomefortab.equals("onboardtaskdefination")){
%>
   <li><a href="OnBoardingTaskDefi.do?method=OnBoardingTaskDefilist" rel="" class="selected"><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.Create_OnBoardTaskDef",user1.getLocale())%></a></li>

<%}else{%>
   <li><a href="OnBoardingTaskDefi.do?method=OnBoardingTaskDefilist" rel="" class=""><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.Create_OnBoardTaskDef",user1.getLocale())%></a></li>
<%}%>

<% if(onboardingHomefortab != null && onboardingHomefortab.equals("template")){
%>
   <li><a href="OnBoardingTemplate.do?method=OnBoardMainTemplatelistPage" rel="" class="selected"><%=Constant.getResourceStringValue("admin.OnBoardTemplate",user1.getLocale())%></a></li>
<%}else{%>
   <li><a href="OnBoardingTemplate.do?method=OnBoardMainTemplatelistPage" rel=""><%=Constant.getResourceStringValue("admin.OnBoardTemplate",user1.getLocale())%></a></li>
<%}%>



<!--<li><a href="externalnested.htm" rel="countrycontainer">Tab 4</a></li> -->
</ul>

<div id="countrydivcontainer" style="border:1px solid gray; width:1200px; margin-bottom: 1em; padding: 10px">
<span id='progressbartable1'>

</span>
<%
String onboardingHome = (String)request.getAttribute("onboardingHome");
	
if(onboardingHome != null && onboardingHome.equals("template")){
%>
    <%@ include file="OnBoardingTemplatelistBody.jsp" %>

<%}else
if(onboardingHome != null && onboardingHome.equals("onboardtaskdefination")){%>

   <%@ include file="OnBoardingTaskDefilistBody.jsp" %>
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
