<%@ include file="../header.jsp" %>
<%@ include file="../greybox.jsp" %>
<bean:define id="aform" name="fbJobsForm" type="network.form.FbJobsForm" />

<%

String jobId=(String)request.getAttribute("jobId");

List applicantlist = aform.getApplicantList();
System.out.println("applicants size >> "+applicantlist.size());
%>

	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />


	<link rel='stylesheet' type='text/css' href='talentnetwork/css/tab.css' />


	<LINK rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/redmond/jquery-ui.css">
	<LINK rel="stylesheet" type="text/css" href="talentnetwork/jquery/layout-default-latest.css">

	<STYLE type="text/css">
	/* Using an 'optional-container' instead of 'body', so need body to have a 'height' */
	html, body {
		width:		100%;
		height:		100%;
		padding:	0;
		margin:		0;
		overflow:	hidden !important;
	}
	#optional-container {
		width:			100%;
		height:			100%;
		

	}
	
	

	</STYLE>

	<link rel='stylesheet' type='text/css' href='talentnetwork/css/searchbutton.css' />


	<SCRIPT type="text/javascript" src="talentnetwork/jquery/js/jquery-ui-latest.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="talentnetwork/jquery/js/jquery.layout-latest.js"></SCRIPT>


	<SCRIPT type="text/javascript">
	var myLayout;

	$(document).ready(function(){

		$("#tabs_div").tabs();

		$(".header-footer").hover(
			function(){ $(this).addClass('ui-state-hover'); }
		,	function(){ $(this).removeClass('ui-state-hover'); }
		);

		myLayout = $('#optional-container').layout();
		myLayout.sizePane("west", 300);


		//addThemeSwitcher('.ui-layout-north',{ top: '13px', right: '20px' });

	});


	</SCRIPT>



<SCRIPT type="text/javascript">


</SCRIPT>



<br>

<%@ include file="../tab2.jsp" %>
<%
List frindsUsingList = freader.getFriendsUsingThisApps(fuser,4);
%>
<DIV id="optional-container">
<DIV class="ui-layout-north">
        <table height="50px">
		<tr>
		<td>
		<%@ include file="../searchJobs.jsp" %>
	
		</td>
		<td>
		<font size="1">Friends using TalentNetwork</font>
		<table>
		<tr>
		<% if(frindsUsingList != null){
			for(int i=0;i<frindsUsingList.size();i++){
				FaceBookUser fbuser = (FaceBookUser)frindsUsingList.get(i);

		%>
		<td>
      <a href="<%=fbuser.getLink()%>" target="new"><img src="//graph.facebook.com/<%=fbuser.getFacebookid()%>/picture" border="0"/></a><br>
      <a href="<%=fbuser.getLink()%>" target="new"><font size="2"><%=fbuser.getFullName()%></font></a>
	  </td>
		<%}
		}%>
		<tr>
		</table>
		</td>
		<td width="35px">
		<a href="">View All >> </a>
		</td>
		</tr>
		</table>
	</DIV>


	<DIV id="tabs_div" class="ui-layout-center">
		
		<UL style="-moz-border-radius-bottomleft: 0; -moz-border-radius-bottomright: 0;">
			<LI><A href="#tab_1"><SPAN>Applicants</SPAN></A></LI>
		
		</UL>
		<DIV class="ui-layout-content ui-widget-content ui-corner-bottom" style="border-top: 0; padding-bottom: 1em;">
			<DIV id="tab_1">
		<html:form action="/fbjob.do?method=saveFbJobs">


		
                  <div id="containernew">		
			        <table width="900px">
					
			      
					<%  
					int jj=0;
						if(applicantlist.size() > 0){
					%>
					
					<%
						
						for(int i=0;i<applicantlist.size();i++){
							FaceBookUser applicant = (FaceBookUser)applicantlist.get(i);

							if(i ==0){
			
					%>
							<tr><td colspan="2" style="background: #FFCC66;  text-align:left;">&nbsp;<b>Applicants</b></td></tr>
							<%} %>
						<tr>
							<td width="450px" height="100px" bgcolor="#E8E8E8" valign="top">
							<%=applicant.getFullName() %>

				 	 		</td>
						</tr>
						<%} %>
					<%
					jj++;	
					}else{%>
					<tr>
						<td>Not applied yet</td>
					</tr>
					
					<%} %>
				
					<tr>
						<td><a href="fbjob.do?method=manageJob">Back to Manage Jobs</a></td>
					</tr>
				  </table>



			</div>
            
            </html:form>

				
			</DIV>
			

		</DIV>

	

	</DIV>

	<DIV class="ui-layout-west"> 
	<%@ include file="../home/east.jsp" %>
	
	</DIV>

	<DIV class="ui-layout-south"> South </DIV>

</DIV>



</body>
</html>

<script language="javascript">

</script>

<style>

ol.row {
	list-style:none
}
ol.row li {
	position:relative;
	border-bottom:1px solid #EEEEEE;
	padding:8px;
}
ol.row li:hover {
	background-color:#F7F7F7;
}
ol.row li:first-child {
}
#containernew {
	
	width:900px
}
.load_more {
	background-color:#FFFFFF;
	background-image:url("talentnetwork/images/more.gif");
	background-position:left top;
	background-repeat:repeat-x;
	border-color:#DDDDDD #AAAAAA #AAAAAA #DDDDDD;
	border-style:solid;
	border-width:1px;
	display:block;
	font-size:14px;
	font-weight:bold;
	height:22px;
	line-height:1.5em;
	margin-bottom:6px;
	outline:medium none;
	padding:6px 0;
	text-align:center;
	text-shadow:1px 1px 1px #FFFFFF;
	width:100%;
}
.load_more {
	-moz-border-radius:5px 5px 5px 5px;
}
.load_more:hover {
	background-position:left;
	border:1px solid #BBBBBB;
	text-decoration:none;
}
.load_more:active {
	background-position:left;
	color:#666666;
}

img {
	border : none;
}
</style>