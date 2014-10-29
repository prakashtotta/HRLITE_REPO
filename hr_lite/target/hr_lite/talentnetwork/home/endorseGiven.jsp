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
			<LI><A href="#tab_1"><SPAN>Endorse given</SPAN></A></LI>
			
		</UL>
		<DIV class="ui-layout-content ui-widget-content ui-corner-bottom" style="border-top: 0; padding-bottom: 1em;">
			<DIV id="tab_1">
				
			<%
			List endList = BOFactory.getFbUserBO().getAllEndorsementsGiven(fuser.getFacebookid());
			%>

			<ol class="commentlist group">

			<%
				for(int i=0;i<endList.size();i++){
									FbEndorsements endorse = (FbEndorsements)endList.get(i);
									FaceBookUser fuserend = freader.getUserDataFew(endorse.getToFbId(),fuser.getSessionKey());
			%>
			<li class="comment even thread-even depth-1 group" id="li-comment-10053">
						
						
									
							
				
				<div class="comment-content-triumph">
				<a href="<%=fuserend.getLink()%>" target="new"><img src="//graph.facebook.com/<%=endorse.getToFbId()%>/picture" border="0"/></a>

				<a href="<%=fuserend.getLink()%>" target="new"><%=fuserend.getFullName()%></a>
                    <%=(endorse.getEndorse()==null)?"":StringUtils.doSpecialCharacters(endorse.getEndorse())%>  <font size="1">Posted <%=DateUtil.convertDateToStringDate(endorse.getCreatedDate(),DateUtil.defaultdateformatforSQL)%></font>
					
				</div>
				
			
         <br>
		</li>

          <%}%>
		</ol>

				


				
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

