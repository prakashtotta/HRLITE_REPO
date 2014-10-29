<!DOCTYPE html> 
<%@ include file="../header.jsp" %>
<%@ include file="../greybox.jsp" %>
<%
String useridswithcommaend=freader.getFriendsUids(fuser,40);
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


function addskills(){
	
	var url="login.do";
	GB_showCenter('test',url, 400,400, messageret1);
}

function messageret1(){
	
			}

function askEndorsement()
{
 var checkedvalues="";
  var e= document.endorsemnt.elements.length;
  var cnt=0;

  for(cnt=0;cnt<e;cnt++)
  {
    if(document.endorsemnt.elements[cnt].name=="endusers"){
         if(document.endorsemnt.elements[cnt].checked == true){
		 checkedvalues = checkedvalues + document.endorsemnt.elements[cnt].value + ",";
		 }
    }
  }

 

 if(checkedvalues == ""){
sendRequestToEndorsement();
 }else{
	  checkedvalues = checkedvalues.slice(0, -1)
sendRequestToEndorsementselected(checkedvalues);
 }
}

function sendRequestToEndorsementselected(ids) {

	FB.init({
        appId: '395990430460186',
        status: true, 
        cookie: true, 
        xfbml: true 
    }); 

  FB.ui({method: 'apprequests',
    message: '<%=fuser.getFullName()%> asking for endrosement through talentNetwork',
    to: ids
  }, requestCallbackEndorse);


}

function sendRequestToEndorsement() {

	FB.init({
        appId: '395990430460186',
        status: true, 
        cookie: true, 
        xfbml: true 
    }); 

  FB.ui({method: 'apprequests',
    message: '<%=fuser.getFullName()%> asking for endrosement through talentNetwork',
    to: '<%=useridswithcommaend%>'
  }, requestCallbackEndorse);
}

function requestCallbackEndorse(response) {
	
if(response.to != ""){
  $.ajax({
	type: 'GET',
  url: "networkhome.do?method=askForEndorsementsave&uids="+response.to+"&ddd="+(new Date).getTime(),
  success: function(data){
  
  }
});
}

}



function endorseFriends()
{

	
 var checkedvalues="";
  var e= document.endorsemnt.elements.length;
  var cnt=0;

  for(cnt=0;cnt<e;cnt++)
  {
    if(document.endorsemnt.elements[cnt].name=="endusers"){
         if(document.endorsemnt.elements[cnt].checked == true){
		 checkedvalues = checkedvalues + document.endorsemnt.elements[cnt].value + ",";
		 }
    }
  }

 

 if(checkedvalues == ""){
sendEndorseToFriends();
 }else{
	  checkedvalues = checkedvalues.slice(0, -1)
sendEndorsementToFriendsselected(checkedvalues);
 }
}


function sendEndorseToFriends() {

	FB.init({
        appId: '395990430460186',
        status: true, 
        cookie: true, 
        xfbml: true 
    }); 

  FB.ui({method: 'apprequests',
    message: '<%=fuser.getFullName()%> endrosed you through talentNetwork',
    to: '<%=useridswithcommaend%>'
  }, requestCallbackEndorseafterendore);
}


function sendEndorsementToFriendsselected(ids) {

	FB.init({
        appId: '395990430460186',
        status: true, 
        cookie: true, 
        xfbml: true 
    }); 

  FB.ui({method: 'apprequests',
    message: '<%=fuser.getFullName()%> endrosed you through talentNetwork',
    to: ids
  }, requestCallbackEndorseafterendore);
}


function requestCallbackEndorseafterendore(response) {
	
if(response.to != ""){
	
	var url="../../networkhome.do?method=endorsementScreen&uids="+response.to;
	//window.open(url);
	GB_showCenter('Endorse Friends',url, 300,400, messageret1);
  
}
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
			<LI><A href="#tab_1"><SPAN>Friends</SPAN></A></LI>
			<a href="#" onClick="askEndorsement()"><img src="talentnetwork/images/askfriends.png" width="150px" height="35px" border="0" title="Ask for Endorsements "/></a>
		 <a href="#" onClick="endorseFriends()"><img src="talentnetwork/images/endorefriends.png" width="150px" height="35px" border="0" title="Endorse Friends "/></a>
		</UL>
		<DIV class="ui-layout-content ui-widget-content ui-corner-bottom" style="border-top: 0; padding-bottom: 1em;">
			<DIV id="tab_1">
				
<%
List allfrindsList = freader.getAllFriendsForEndorse(fuser);
%>

				<div style="background: #FFCC66;  text-align:center;">Ask friends to endorse you!</div>

				 . <br>

		
		<form name="endorsemnt">
         <table>
		 <tr>
		 <td>Profiles with endorsements are much more likely to land a job</td>
		 <!--<td>
		 <a href="#" onClick="askEndorsement()"><img src="talentnetwork/images/askfriends.png" width="150px" height="35px" border="0" title="Ask for Endorsements "/></a></td>
		 <td><a href="#" onClick="endorseFriends()"><img src="talentnetwork/images/endorefriends.png" width="150px" height="35px" border="0" title="Endorse Friends "/></a></td>-->
		 
		 
		 
		 </tr>
		 </table>
		<table>
		

		<% if(allfrindsList != null){
		%>
		
		<%
			int j=0;
			for(int i=0;i<allfrindsList.size();i++){
				FaceBookUser fbuseren = (FaceBookUser)allfrindsList.get(i);
				String trvalue="";
				String trvalueclose="";
				if((j%5)==0){
				trvalue="</tr><tr>";
				
				}


		%>
		<%=trvalue%>
		<td bgcolor="#D8D8D8">
      <a href="<%=fbuseren.getLink()%>" target="new"><img src="//graph.facebook.com/<%=fbuseren.getFacebookid()%>/picture" border="0"/></a><br>
      <a href="<%=fbuseren.getLink()%>" target="new"><font size="2"><%=fbuseren.getFullName()%></font></a><br>
	  <input type="checkbox" name="endusers" value="<%=fbuseren.getFacebookid()%>" />
	  </td>
		<%
		j++;	
		}%>
		</tr>
		<%}%>
		
	  </table>
       </form>
				
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

