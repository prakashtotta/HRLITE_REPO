<%
String iconprofileimage = "//graph.facebook.com/"+fuser.getFacebookid()+"/picture?type=large";
String useridswithcomma=freader.getFriendsUids(fuser,40);

int total_count = freader.getFriendsCount(fuser);
%>
<SCRIPT type="text/javascript">

function sendRequestToRecipients() {

	FB.init({
        appId: '395990430460186',
        status: true, 
        cookie: true, 
        xfbml: true 
    }); 

  FB.ui({method: 'apprequests',
    message: 'My Great Request',
    to: '<%=useridswithcomma%>'
  }, requestCallback);
}

function sendRequestToRecipientsPlain() {

	FB.init({
        appId: '395990430460186',
        status: true, 
        cookie: true, 
        xfbml: true 
    }); 

  FB.ui({method: 'apprequests',
    message: 'My Great Request'
   
  }, requestCallback);
}


function requestCallback(response) {
}
function postJob(){

	$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

	location.href="fbjob.do?method=postJob";
	
}
function managejobs(){

	$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

	location.href="fbjob.do?method=manageJob";
	
}

function askforendorsement() {

	$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

	location.href="networkhome.do?method=askForEndorsement";
}

function endorseFriend(id){
	var url="../../networkhome.do?method=endorsementScreen&uids="+id;
	GB_showCenter('Endorse Friend',url, 300,400, messageret1);
}

function messageret1(){
	
			}

function endorseReceived(){
   
	$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

	location.href="networkhome.do?method=endorseReceived";
 
} 

function endorseGiven(){
   
	$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

	location.href="networkhome.do?method=endorseGiven";
 
} 
function myProfile(fid,sessionkey){
   
	$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

	location.href="networkhome.do?method=home&fid="+fid+"&key="+sessionkey;
 
} 

function savedJobs(){
   
	$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });

	location.href="fbjobs.do?method=savedjobs";
 
} 


</script>
<table>
<tr>
<td>
<a href="networkhome.do?method=home&fid=<%=fuser.getFacebookid()%>&key=<%=fuser.getSessionKey()%>"><img src="<%=iconprofileimage%>" border="0" width="150px" height="150px"/></a>
</td>
<td valign="top">
Total connections<br>
<font size="20"><a href="networkhome.do?method=askForEndorsement"><%=total_count%></a></font> <br>
<a href="#" title="add connection" onClick="sendRequestToRecipients()"><img src="talentnetwork/images/addconnection.png" border="0" width="130px"/></a><br>
<a href="#" onClick="myProfile('<%=fuser.getFacebookid()%>','<%=fuser.getSessionKey()%>')">My profile</a>
</td>
</tr>
<tr>
<td>
<%
String idAskEndorse = BOFactory.getFbUserBO().getAllAskEndorsesToIds(fuser.getFacebookid());

if(!StringUtils.isNullOrEmpty(idAskEndorse)){
List askEndorseList = freader.getAskingForEndorse(fuser,idAskEndorse);
%>

<% if(askEndorseList != null){
		%>
		
		<%
			int j=0;
			for(int i=0;i<askEndorseList.size();i++){
				FaceBookUser fbuseren1 = (FaceBookUser)askEndorseList.get(i);
				String trvalue="";
				String trvalueclose="";
				if((j%2)==0){
				trvalue="</tr><tr>";
				
				}


		%>
		<%=trvalue%>
		<td bgcolor="#D8D8D8">
      <a href="<%=fbuseren1.getLink()%>" target="new"><img src="//graph.facebook.com/<%=fbuseren1.getFacebookid()%>/picture" border="0"/></a><br>
      <a href="<%=fbuseren1.getLink()%>" target="new"><font size="2"><%=fbuseren1.getFullName()%></font></a><br>
	  <a href="#" title="Endorse" onClick="endorseFriend('<%=fbuseren1.getFacebookid()%>')"><img src="talentnetwork/images/endorse.png" border="0" /></a>
	  </td>
		<%
		j++;	
		}%>
		</tr>
		<%}%>
		
	  </table>
<%}%>
</td>
</tr>
</table>
<table>
<tr></td></td></tr>
<tr>
<td>
<a href="#" onClick="askforendorsement();return false;">Ask for Endorsements</a>
</td>
</tr>
<tr></td></td></tr>
<tr>
<td>
<a href="#" onClick="askforendorsement();return false;">Endorse a Friend</a>
</td>
</tr>
<tr></td></td></tr>
<tr>
<td>
<a href="#" onClick="endorseGiven();return false;">Endorse given</a>
</td>
</tr>
<tr></td></td></tr>
<tr>
<td>
<a href="#" onClick="endorseReceived();return false;">Endorse received</a>
</td>
</tr>
<tr></td></td></tr>
<tr>
<td>
<a href="#" onClick="savedJobs();return false;">Saved jobs</a>
</td>
</tr>
</table>


<div style="background: #FFCC66;  text-align:center;">Are you hiring ?</div>
<table width="300px">
<tr>
<td align="center">
<a href="#" title="Post job" onClick="postJob();return false"><img src="talentnetwork/images/postjob.png" border="0" height="30px"/></a>
</td>
</tr>
</table>
<div style="background: #FFCC66;  text-align:center;">Are you recruiter ?</div>
<table width="300px">
<tr>
<td align="center">
<a href="#" title="Manage Job" onclick="managejobs();return false"><img src="talentnetwork/images/managejobs.png" border="0"/></a>
</td>
</tr>
</table>


