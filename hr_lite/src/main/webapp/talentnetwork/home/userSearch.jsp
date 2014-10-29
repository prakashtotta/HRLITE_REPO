<!DOCTYPE html> 
<%@ include file="../header.jsp" %>
<%@ include file="../greybox.jsp" %>
<bean:define id="faceBookUserForm" name="faceBookUserForm" type="network.form.FaceBookUserForm" />

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
			<LI><A href="#tab_1"><SPAN>Users</SPAN></A></LI>
			<a href="#" onClick="sendMessage()"><img src="talentnetwork/images/sendMessage.png" width="150px" height="35px" border="0" title="Send Message"/></a>
		
		</UL>
		<DIV class="ui-layout-content ui-widget-content ui-corner-bottom" style="border-top: 0; padding-bottom: 1em;">
			<DIV id="tab_1">

			<div style="background: #FFCC66;  text-align:center;">Search result for query......<b><%=faceBookUserForm.getSearchQuery()%></b></div>

            <%
			List usersList = faceBookUserForm.getUsersList();
			%>

		<div id="containernew">				
		<form name="userssearchform">
    
		<table width="900px">
					
        
		<%  
		int jj=0;
			if(usersList != null){
		%>
		
		<%
			
			for(int i=0;i<usersList.size();i++){
				FaceBookUser fbuseren = (FaceBookUser)usersList.get(i);
				String trvalue="";
				String trvalueclose="";
				if((jj%5)==0){
				trvalue="</tr><tr>";
				
				}


		%>
		<%=trvalue%>
		<td bgcolor="#D8D8D8" width="190px" valign="top">
		
      <a href="<%=fbuseren.getLink()%>" target="new"><img src="//graph.facebook.com/<%=fbuseren.getFacebookid()%>/picture" border="0"/></a><br>
	  <font size="2" color="#3366CC"><%=StringUtils.isNullOrEmpty(fbuseren.getTopLine())?"":fbuseren.getTopLine()%></font>
	  <br>
      <a href="p/<%=fbuseren.getFuserName()%>" target="new"><font size="2"><%=fbuseren.getFullName()%></font></a>
	  <br>
	  <% if(!StringUtils.isNullOrEmpty(fbuseren.getEmailId())){%>
	  <input type="checkbox" name="endusers" value="<%=fbuseren.getFacebookid()%>" />
	  <%}else{%>
	  <!--<input type="checkbox" disabled="true" name="endusers" value="<%=fbuseren.getFacebookid()%>" />-->
	  <%}%>
	  
	  </td>
		<%
		jj++;	
		}%>
		</tr>
		<%}%>
		
	  </table>
       
			
		<table id="updates" width="900px">
		<tr>
		<td>
		
		</td>
		</tr>

   		</table>
	</form>
		<div id="more" style="margin-top: 20px;"> <a  id="0" class="load_more" href="#">more</a> </div>
		</div>
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

<script type="text/javascript">
$(function() {//When the Dom is ready

$('.load_more').live("click",function() {//If user clicks on hyperlink with class name = load_more
var last_msg_id = $(this).attr("id");
//Get the id of this hyperlink 
//this id indicate the row id in the database 
if(last_msg_id!='end'){
    //if  the hyperlink id is not equal to "end"
$.ajax({//Make the Ajax Request
type: "POST",
url: "talentnetwork/home/userSearchsmore.jsp",
data: "query=<%=faceBookUserForm.getSearchQuery()%>&lastmsg="+ last_msg_id, 
beforeSend:  function() {
$('a.load_more').html('<img src="talentnetwork/images/indicator.gif" />');//Loading image during the Ajax Request
  
},
success: function(html){//html = the server response html code
    $("#more").remove();//Remove the div with id=more 
$("#updates").append(html);//Append the html returned by the server .
//$("ol#updates").replaceWith ('<ol class="row" id="updates">'+html+'</ol>');//Replace the html returned by the server .

}
});
  
}

return false;


});
});

</script>