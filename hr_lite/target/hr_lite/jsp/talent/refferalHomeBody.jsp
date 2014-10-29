

<%
String path2 = (String)request.getAttribute("filePath");
User user2 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern2 = DateUtil.getDatePatternFormat(user2.getLocale());
%>


<link rel="stylesheet" type="text/css" href="jsp/css/ajaxtabs.css" />
<html>
<head>
<script type="text/javascript" src="jsp/js/ajaxtabs.js">

/***********************************************
* Ajax Tabs Content script v2.2- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>





<script language="javascript">
       
	  document.getElementById('progressbartable1').style.display = 'inline';   

</script>
</head>

<body>
<br>
<ul id="countrytabs" class="shadetabs">
<%
String refferalHometab = (String)request.getAttribute("refferalHome");
if(refferalHometab != null && refferalHometab.equals("budgetcode")){
%>
   <li><a href="refferalBudgetcode.do?method=refferalbudgetCodelist" rel="" class="selected"><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.rbcoe",user2.getLocale())%></a></li>

<%}else{%>
   <li><a href="refferalBudgetcode.do?method=refferalbudgetCodelist" rel="" class=""><%=Constant.getResourceStringValue("admin.RefferalBudgetCode.rbcoe",user2.getLocale())%></a></li>
<%}%>



<% if(refferalHometab != null && refferalHometab.equals("refferalscheme")){
%>
   <li><a href="refferalscheme.do?method=refferalSchemelist" rel="" class="selected"><%=Constant.getResourceStringValue("admin.RefferalScheme.Referralschemes",user2.getLocale())%></a></li>
<%}else{%>
   <li><a href="refferalscheme.do?method=refferalSchemelist" rel=""><%=Constant.getResourceStringValue("admin.RefferalScheme.Referralschemes",user2.getLocale())%></a></li>
<%}%>





<!--<li><a href="externalnested.htm" rel="countrycontainer">Tab 4</a></li> -->
</ul>

<div id="countrydivcontainer" style="border:1px solid gray; width:1200px; margin-bottom: 1em; padding: 10px">
<span id='progressbartable1'>

</span>
<%
String refferalHome = (String)request.getAttribute("refferalHome");
	
if(refferalHome != null && refferalHome.equals("refferalscheme")){
%>
    <%@ include file="refferalSchemeListBody.jsp" %>

<%}else if(refferalHome != null && refferalHome.equals("budgetcode")){%>

<%@ include file="refferalBudgetCodeListBody.jsp" %>



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
