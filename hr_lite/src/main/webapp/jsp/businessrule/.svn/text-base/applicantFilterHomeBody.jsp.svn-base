

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



</head>
<script language="javascript">
       
	  document.getElementById('progressbartable1').style.display = 'inline';   

</script>
<body>

<br>
<ul id="countrytabs" class="shadetabs">
<%
String applicantFilterHometab = (String)request.getAttribute("applicantFilterHome");
if(applicantFilterHometab != null && applicantFilterHometab.equals("applicantFilter")){
%>
   <li><a href="businessrule.do?method=filterList" rel="" class="selected"><%=Constant.getResourceStringValue("admin.applicant.filters.tablabel",user1.getLocale())%></a></li>

<%}else{%>
   <li><a href="businessrule.do?method=filterList" rel="" class=""><%=Constant.getResourceStringValue("admin.applicant.filters.tablabel",user1.getLocale())%></a></li>
<%}%>

<% if(applicantFilterHometab != null && applicantFilterHometab.equals("applicantFilterGroup")){
%>
   <li><a href="businessrule.do?method=applicantFilters" rel="" class="selected"><%=Constant.getResourceStringValue("admin.businessRule.applicantfiltergroup",user1.getLocale())%></a></li>
<%}else{%>
   <li><a href="businessrule.do?method=applicantFilters" rel=""><%=Constant.getResourceStringValue("admin.businessRule.applicantfiltergroup",user1.getLocale())%></a></li>
<%}%>



<!--<li><a href="externalnested.htm" rel="countrycontainer">Tab 4</a></li> -->
</ul>

<div id="countrydivcontainer" style="border:1px solid gray; width:1200px; margin-bottom: 1em; padding: 10px">
<span id='progressbartable1'>

</span>
<%
String applicantFilterHome = (String)request.getAttribute("applicantFilterHome");
	
if(applicantFilterHome != null && applicantFilterHome.equals("applicantFilter")){
%>
    <%@ include file="filtersBody.jsp" %>

<%}else
if(applicantFilterHome != null && applicantFilterHome.equals("applicantFilterGroup")){%>

   <%@ include file="applicantFiltersBody.jsp" %>
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
