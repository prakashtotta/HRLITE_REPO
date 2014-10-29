<!DOCTYPE html> 
<%@ include file="../header.jsp" %>
<%@ include file="../greybox.jsp" %>



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
	function educationworkexpdetails() { 
 document.getElementById("educationworkexpdetails").style.visibility = "visible";
 
$.ajax({
	type: 'GET',
  url: "talentnetwork/home/educationworkexpdetails.jsp"+"?ddd="+(new Date).getTime(),
  success: function(data){
  $('#educationworkexp').html(data);
	document.getElementById("educationworkexpdetails").style.visibility = "hidden";	
  }
});
} 

function addskills(){
	
	var url="login.do";
	GB_showCenter('test',url, 400,400, messageret1);
}

function messageret1(){
	
			}

</SCRIPT>

<br>
<%@ include file="../tab1.jsp" %>

<%
List frindsUsingList = freader.getFriendsUsingThisApps(fuser,4);
%>
<DIV id="optional-container">
<DIV class="ui-layout-north">
        <table height="50px">
		<tr>
		<td>
		<%@ include file="../searchpeople.jsp" %>
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
			<LI><A href="#tab_1"><SPAN>My profile</SPAN></A></LI>
			<LI><A href="#tab_2"><SPAN>My Exams</SPAN></A></LI>
			<LI><A href="#tab_3"><SPAN>My Friends companies</SPAN></A></LI>
		</UL>
		<DIV class="ui-layout-content ui-widget-content ui-corner-bottom" style="border-top: 0; padding-bottom: 1em;">
			<DIV id="tab_1">
				
				<span id="educationworkexp">
				</span>
				<span  id="educationworkexpdetails">
				 <img src="talentnetwork/images/indicator.gif"/>
				 </span>


				
			</DIV>
			<DIV id="tab_2">
				<P>Nam non hendrerit augue. Nunc sit amet est lectus. Morbi non nisl eget dolor rutrum ullamcorper. 
				Sed dictum commodo elit sed rutrum. Nunc eu massa nulla, at gravida dolor. Aenean at interdum nisi. 
				Integer consequat malesuada urna quis dignissim. Duis luctus porta ullamcorper. 
				Aliquam tortor nunc, porta vel vestibulum at, egestas id mi. 
				In quis arcu in felis laoreet varius a et ligula. 
				Sed in magna a orci posuere ullamcorper ultrices ut ante. Suspendisse velit enim, venenatis et 
				pharetra sed, mollis ut dui. Donec erat eros, dignissim ac ultrices ac, hendrerit a elit.</P>
			</DIV>
			<DIV id="tab_3">
				<P>Cras nec arcu sed nisi varius fermentum ut non nulla. Pellentesque ultricies condimentum nibh, 
				nec imperdiet felis laoreet sit amet. Aenean a molestie tortor. 
				Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. 
				Praesent enim magna, imperdiet adipiscing tempus nec, molestie id elit. Ut varius ante gravida 
				est dignissim sodales. Nulla consectetur nibh eget metus sodales vulputate. 
				Mauris lacinia risus nec ipsum sodales elementum. Nunc non tortor turpis. 
				Vestibulum a euismod ligula.</P>
			</DIV>
		</DIV>

	

	</DIV>

	<DIV class="ui-layout-west"> 
	<%@ include file="east.jsp" %>
	
	</DIV>

	<DIV class="ui-layout-south"> South </DIV>

</DIV>



</body>
</html>

<script language="javascript">
educationworkexpdetails();
</script>