<!DOCTYPE html> 
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

	<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

	<%@ page import="com.common.*"%>
	<%@ page import="com.util.*"%>
	<%@ page import="com.bo.*"%>
	<%@ page import="network.common.*"%>
	<%@ page import="network.bean.*"%>
	<%@ page import="network.util.*"%>
	<%@ page import="java.util.*"%>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/talentnetwork/scripts/jquery_minimized_core.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/talentnetwork/scripts/date.format.js"></script>
		 <script src="http://connect.facebook.net/en_US/all.js"> </script>
		 <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/talentnetwork/css/style.css' />

<%
String profileusername = (String)request.getAttribute("username");
System.out.println("profileusername satyaaaaaaaaaaaaaaaaa"+profileusername);
%>


	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />


	<link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/talentnetwork/css/tab.css' />


	<LINK rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/redmond/jquery-ui.css">
	<LINK rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/talentnetwork/jquery/layout-default-latest.css">

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

	<link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/talentnetwork/css/searchbutton.css' />


	<SCRIPT type="text/javascript" src="<%=request.getContextPath()%>/talentnetwork/jquery/js/jquery-ui-latest.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="<%=request.getContextPath()%>/talentnetwork/jquery/js/jquery.layout-latest.js"></SCRIPT>


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


function addskills(){
	
	var url="login.do";
	GB_showCenter('test',url, 400,400, messageret1);
}

function messageret1(){
	
			}

function sendMessage(){
	alert("Only Premium Recruiter can send bulk messages");
	return false;
}

</SCRIPT>
 
<br>


<DIV id="optional-container">
<DIV class="ui-layout-north">
        Profile header
	</DIV>


	<DIV id="tabs_div" class="ui-layout-center">
		
		<UL style="-moz-border-radius-bottomleft: 0; -moz-border-radius-bottomright: 0;">
			<LI><A href="#tab_1"><SPAN>Users</SPAN></A></LI>
			<a href="#" onClick="sendMessage()"><img src="talentnetwork/images/sendMessage.png" width="150px" height="35px" border="0" title="Send Message"/></a>
		
		</UL>
		<DIV class="ui-layout-content ui-widget-content ui-corner-bottom" style="border-top: 0; padding-bottom: 1em;">
			<DIV id="tab_1">

           profile
			</DIV>
		</DIV>

	

	</DIV>

	<DIV class="ui-layout-west"> 
	<%@ include file="eastprofile.jsp" %>
	
	</DIV>

	<DIV class="ui-layout-south"> South </DIV>

</DIV>



</body>
</html>

