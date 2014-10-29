<!DOCTYPE html> 
<%@ include file="../header.jsp" %>
<%@ include file="../greybox.jsp" %>
<%
String jobdetailsId=(String)request.getAttribute("jobDetailsId");
FbJobs fbJobs = BOFactory.getFbUserBO().getFBJobsByJobId(new Long(jobdetailsId).longValue());
String fromWhere=(String)request.getAttribute("fromWhere");
String jobApplied=(String)request.getAttribute("jobApplied");
Boolean isApplied = (Boolean)request.getAttribute("isApplied");
System.out.println("isApplied >> "+isApplied);
%>
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

function backtojobs(){
$.blockUI({ message: '<h1><img src="jsp/images/loading_circle.gif" /> Please wait...</h1>' });
	  location.href = "fbjobs.do?method=search";

}

function apply(){

	  document.faceBookUserForm.action ="fbjobs.do?method=applyJob&jobdetailsId=<%=jobdetailsId%>";
	  document.faceBookUserForm.submit();
	
}

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
			<LI><A href="#tab_1"><SPAN>Job Details</SPAN></A></LI>
		
		</UL>
		<DIV class="ui-layout-content ui-widget-content ui-corner-bottom" style="border-top: 0; padding-bottom: 1em;">
			<DIV id="tab_1">
			<%if(!StringUtils.isNullOrEmpty(jobApplied) && jobApplied.equals("yes")){ %>
				<fieldset>
					<table border="0" width="100%">
						<tr>
						<td><img src='jsp/images/greentick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;<font color="green">Applied successfully</font></td>
	
						</tr>
					</table>
				</fieldset>
			
			<%} %>
				
			<fieldset>
				<table border="0" width="100%" cellspacing="2" cellpadding="2">
					<tr>
						<td width="40%">Job Title:</td>
						<td><%=fbJobs.getJobTitle()==null?"":fbJobs.getJobTitle()%>
						
						
						</td>
					</tr>
					<tr></tr><tr></tr>
					<tr>
						<td>Headline:</td>
						<td><%=fbJobs.getHeadline() ==null?"":fbJobs.getHeadline()%>
					
						
						</td>
					</tr>
					<tr></tr><tr></tr>
					<tr>
						<td>Company Name:</td>
						<td><%=fbJobs.getCompanyName() ==null?"":fbJobs.getCompanyName()%>
					
						</td>
					</tr>
					<tr></tr><tr></tr>
					<tr>
						<td>City:</td>
						<td><%=fbJobs.getCity() ==null?"":fbJobs.getCity()%>
						
						</td>
					</tr>
					<tr>
						<td>Country:</td>
						<td><%=fbJobs.getCountry() ==null?"":fbJobs.getCountry()%>
						
						</td>
					</tr>
					
					
					<tr>
						<td>State:</td>
						<td><%=fbJobs.getState() ==null?"":fbJobs.getState()%>

						
						</td>
					</tr>
					<tr>
						<td>Postal Code:</td>
						<td><%=fbJobs.getPostalcode() ==null?"":fbJobs.getPostalcode()%>
						
						</td>
					</tr>
					<tr>
						<td>Description:</td>
						<td><%=fbJobs.getDescription() ==null?"":fbJobs.getDescription()%>
						
						</td>
					</tr>
					<tr>
						<td>Job Category:</td>
						<td><%=fbJobs.getJobcategory() ==null?"":fbJobs.getJobcategory()%>
						
						</td>
					</tr>
					<tr>
						<td>Experience</td>
						<td><%=fbJobs.getExperience() ==null?"":fbJobs.getExperience()%>
						
						</td>
					</tr>
					<tr>
						<td>Tenure</td>
						<td><%=fbJobs.getTenure()== null?"":fbJobs.getTenure()%>
						
						</td>
					</tr>
					<tr>
						<td>Perks:</td>
						<td><%=fbJobs.getPerks()== null?"":fbJobs.getPerks()%>
						
						</td>
					</tr>
	
					<tr>
						<td>Vetarans:</td>
						<td><%=fbJobs.getVeterans() == null?"":fbJobs.getVeterans()%>
						
						</td>
					</tr>
					
					<tr>
						<td>Reference Code:</td>
						<td><%=fbJobs.getReferencecode() == null?"":fbJobs.getReferencecode()%>
						
						</td>
					</tr>
					<%if(fbJobs.getApplyauto().equals("Y")){ %>
					<!-- 
					<tr>
						<td>Apply with Talent Network</td><td>Y</td>
					</tr>
					 -->
					<%}else{ %>
					<tr>
						<td >Apply with External :</td>


						<td>URL:&nbsp;&nbsp;&nbsp;
						<a href="<%=fbJobs.getAppyurl() == null?"":fbJobs.getAppyurl()%>" target=""><%=fbJobs.getAppyurl() == null?"":fbJobs.getAppyurl()%></a>
						
						</td>
					</tr>
					<tr>
						<td></td>
						<td>Email:&nbsp;
						<%=fbJobs.getAppyemail() == null?"":fbJobs.getAppyemail()%>
						
						</td>
					</tr>
						
					<%} %>
							
	
					<tr>

						<td >
						<%if(!StringUtils.isNullOrEmpty(fromWhere) && fromWhere.equals("jobs") ){ %>
							<a href="fbjobs.do?method=search">Back to Jobs</a>
						<%}else if(!StringUtils.isNullOrEmpty(fromWhere) && fromWhere.equals("managejobs") ){ %>
							<a href="fbjob.do?method=manageJob">Back to Manage Jobs</a>
						<%} %>
						</td>
						<td>
						<%
						if(fbJobs.getApplyauto().equals("Y")){
								
						  if(! isApplied ){
								%>
							<%if(!StringUtils.isNullOrEmpty(fromWhere) && fromWhere.equals("jobs") ){ %>
								<a href="fbjobs.do?method=applyJob&jobdetailsId=<%=jobdetailsId%>&fromWhere=jobs" class="button">Apply</a>
							<%}else if(!StringUtils.isNullOrEmpty(fromWhere) && fromWhere.equals("managejobs") ){ %>
								<a href="fbjobs.do?method=applyJob&jobdetailsId=<%=jobdetailsId%>&fromWhere=managejobs" class="button">Apply</a>
							<%}
						  }else{%>
							 <font color="red"><b>Applied</b></font>
						<%}
						}%>
						</td>

					</tr>
					
					<tr><td>&nbsp;</td></tr>
			</table>
				
				</fieldset>
				

                

				
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

<script type="text/javascript">
$(function() {//When the Dom is ready

$('.load_more').live("click",function() {//If user clicks on hyperlink with class name = load_more
var last_msg_id = $(this).attr("id");
//alert(last_msg_id);
//Get the id of this hyperlink 
//this id indicate the row id in the database 
if(last_msg_id!='end'){
    //if  the hyperlink id is not equal to "end"
$.ajax({//Make the Ajax Request
type: "POST",
url: "talentnetwork/jobs/searchJobsmore.jsp",
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


