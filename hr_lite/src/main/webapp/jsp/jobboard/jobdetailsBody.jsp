<%@ include file="../common/include.jsp" %>

<!doctype html>

<html>


<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="description" content="A brief statement describing your company or product." />
	<meta name="keywords" content="keywords, related to, your company, or your product" />

	<link href="jsp/jobboard/css/reset.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="jsp/jobboard/css/core.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="jsp/jobboard/css/colors_blue_and_green.css" rel="stylesheet" type="text/css" title="Blue and green" media="screen" />
	
	<link href="jsp/jobboard/css/slider.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="jsp/jobboard/css/lightbox.css" rel="stylesheet" type="text/css" media="screen" />
	<!--[if lte IE 8]>
		<link href="css/ie.css" rel="stylesheet" type="text/css" media="screen" />
	<![endif]-->
	<link href="http://fonts.googleapis.com/css?family=PT+Sans" rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="jsp/jobboard/scripts/styleswitcher.js"></script>

	<title>Hires360 hiring made easy</title>

</head>
<%
System.out.println("jobboard >> job details..");
 String path = (String)request.getAttribute("filePath");
 String datepattern = Constant.getValue("defaultdateformat");
 String source = (String)request.getParameter("source");
 String reqid = (String)request.getParameter("reqid");
 String secureid = (String)request.getParameter("secureid");
User user1 = new User();
com.bean.Locale locale = new com.bean.Locale();
		locale.setLocaleCode("en_US"); 
user1.setLocale(locale);
 String subdomain = (String)session.getAttribute("subdomain");


String superUserKey = (String)session.getAttribute(Common.SUPER_USER_KEY);

String backurl = request.getContextPath()+"/jobs.do?method=jobsearch&l_code="+user1.getLocale().getLocaleCode()+"&subdomain="+subdomain;
String addbookmarkdone = (String)request.getAttribute("addbookmarkdone");
String alreadyBookmarked = (String)request.getAttribute("alreadyBookmarked");
  String orgname = (String)session.getAttribute("orgname");

String jobapply = (String)request.getAttribute("jobapply");
UserRegData userregdata = BOFactory.getUserBO().getUserRegDataById(new Long(EncryptDecrypt.decrypt(superUserKey)).longValue());

String subdomainname = userregdata.getSubdomain()+"."+Constant.getValue("domain.name");

%>
<bean:define id="form" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<%

Map<String,List<String>> screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.REQUISITION_SCREEN,userregdata.getUserRegId());
List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);
   %>
<head>


<script language="javascript">
var subdomain = "<%=subdomainname%>";

function createapplicant(){
	var url = "<%=request.getContextPath()%>/agencyops.do?method=createApplicantPage&requistionId=<%=form.getJobreqId()%>&frm=ag";
	//parent.setPopTitle1("Create applicant");
	GB_showFullScreen('Applicant details',url,messageret);
}
function back(url){

	  document.jobRequisitionForm.action = url;
	  document.jobRequisitionForm.submit();
}
function createapplicantRef(url){
	//var url = "<%=request.getContextPath()%>/applicantoffer.do?method=createPage&requistionId=<%=form.getJobreqId()%>&frm=ag;
	//parent.setPopTitle1("Create applicant");
	
	GB_showFullScreen('Applicant details',url,messageret);

	
}

	
	function messageret(){
	window.location.reload();
	//history.go(0);

			}
			function messageret1(){
	
			}


function addbookmarksubmit(url){
	var comment = document.jobRequisitionForm.comment.value;
	if(comment == "" || comment == null){
     	alert("Name of the bookmark required");
		return false;
		}
		  document.jobRequisitionForm.action = url;
	  document.jobRequisitionForm.submit();
}

function loadagain(){
	
	location.href="http://"+subdomain;
}



</script>

<style type="text/css">

table.ex2
{
border-collapse:separate;
border-spacing:15px 3px;
height:100%
}
</style>
</head>



<body class="yui-skin-sam">

<%
String wsint = (String)session.getAttribute("wsint");
if(wsint == null || wsint.equals("no")){
%>
	<div id="header_wrap">

	<div id="header">
	
		<%
		    
			if(userregdata.getLogoPhotoId() == 0){
			%>
			
		<a href="http://<%=subdomainname%>" ><img src="jsp/jobboard/images/logo_green.png" width="165" height="53" alt="" border="0"/></a>
			<%}else{
				String imgurl = "jsp/emp/profilePhoto.jsp?id="+userregdata.getLogoPhotoId();
			%>

			<a href="#" ><img src="<%=imgurl%>" border="0" width="165" height="53" alt="" /></a>

			<%}%>
		
			
			<ol id="nav">
				<li><a href="http://<%=subdomainname%>"> Jobs at <b><%=userregdata.getOrgName()%> </b></a></li>
								
			</ol>
						
			<div class="clear"></div>

		</div> <!-- End of header -->

	</div> <!-- End of header wrap -->
<%}%>
   <%
String facebookurl = "http://"+userregdata.getSubdomain()+"."+Constant.getValue("domain.name")+"/hr_lite/jobs.do?method=jobdetailsext&reqid="+form.getJobreqId()+"&secureid="+form.getUuid()+"&source=FACEBOOK";
String linkedinurl = "http://"+userregdata.getSubdomain()+"."+Constant.getValue("domain.name")+"/hr_lite/jobs.do?method=jobdetailsext&reqid="+form.getJobreqId()+"&secureid="+form.getUuid()+"&source=LINKEDIN";
String othersocialurl = "http://"+userregdata.getSubdomain()+"."+Constant.getValue("domain.name")+"/hr_lite/jobs.do?method=jobdetailsext&reqid="+form.getJobreqId()+"&secureid="+form.getUuid()+"&source=OTHERSOCIAL";

   %>	
<table width="98%">
<tr>
<td width="800px" valign="top">	


<font size="3" face="verdana"><b><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></b></font>
 <br>
<table width="100%" cellspacing="0" class="div">
<tr>
<td>
  <a  href="<%=request.getContextPath()%>/refferaluser.do?method=refferaluserdetails&from=applicantexternal&source=<%=source%>&secureid=<%=secureid%>&jobreqid=<%=reqid%>&locale_code=<%=user1.getLocale().getLocaleCode()%>" target="new"><%=Constant.getResourceStringValue("Requisition.apply.for.this.job",user1.getLocale())%></a>
 </td>

  

 <td valign="right">
  <a href="#" onClick="loadagain()"> << back to job listing </a>
  </td>
 </tr>
</table>

  <html:form action="/agjob.do?method=saveapplicantData">


 
 <%@ include file="../job/jobdetailsbodycommon.jsp" %>
    <table>
	<tr>
	<td>
<a href="#" onClick="tweetUrl(); return false;"><img src="jsp/images/twitter.png" align="top" height="30" width="30" border="0"></img></a>
</td>
	
		<td>
<a target="_blank"  name="" href="#" onClick="window.open('http://www.facebook.com/sharer.php?u=<%=facebookurl%>','facebook_window','width=600,height=600,resizable=no,scrollbars=no,toolbar=no,location=no,menubar=no'); return false;"><img src="jsp/images/facebook.png" align="top" height="30" width="30" border="0"></img></a>
</td>
	
	   <td>
<a target="_blank"  name="" href="#" onClick="window.open('http://www.linkedin.com/shareArticle?mini=true&url=<%=linkedinurl%>','facebook_window','width=600,height=600,resizable=no,scrollbars=no,toolbar=no,location=no,menubar=no'); return false;"><img src="jsp/images/linkedin.png" height="30" width="30" border="0"></img></a>
</td>
	 
	  <td>
<a target="_blank"  name="" href="#" onClick="window.open('http://www.addthis.com/bookmark.php?source=tbx32nj-1.0&amp;=250&amp;pubid=<%=Constant.getValue("addthis.pubid")%>&amp;url=<%=othersocialurl%>','Other_social','width=600,height=600,resizable=no,scrollbars=no,toolbar=no,location=no,menubar=no'); return false;"><img src="jsp/images/more.png" height="30" width="30" border="0"></img></a>
</td>
</tr>
</table>
	

   
      


</html:form>	

</td>
<td width="10px">
</td>
<td valign="top" align="left" width="600px">
<div id="content">

<%
String defaultvalue="Thanks for visiting our Job Board. Please review our open positions and apply to the positions that match your qualifications";
%>
<%=(userregdata.getCompanyInfo()==null)?defaultvalue:userregdata.getCompanyInfo()%>
</div>

</td>
</tr>
</table>
</body>
</html>
<%
String twitterurl = "http://"+userregdata.getSubdomain()+"."+Constant.getValue("domain.name")+"/hr_lite/jobs.do?method=jobdetailsext&reqid="+form.getJobreqId()+"&secureid="+form.getUuid()+"&source=TWITTER";
%>
<script language="javascript">

var urrr = null;
function get_short_url(long_url, login, api_key, func) 
{ 
    $.getJSON( 
        "http://api.bitly.com/v3/shorten?callback=?",  
        {  
            "format": "json", 
            "apiKey": api_key, 
            "login": login, 
            "longUrl": long_url 
        }, 
        function(response) 
        { 
            func(response.data.url); 
        } 
    ); 
} 

var login = "satyadas2000"; 
var api_key = "R_2c0b54223fbe6f4723c57446140b790f"; 
var tweet_url = "<%=twitterurl%>"; 
 
 var refval="";

function setTweetValue(){

get_short_url(tweet_url, login, api_key, function(short_url) {
	
    urrr = short_url;
});
}

function tweetUrl(){
	
var url = "http://twitter.com/share?url="+urrr+"&text=We are looking for a <%=form.getJobTitle()%>";
	window.open(url,'tweet_window','width=600,height=600,resizable=no,scrollbars=no,toolbar=no,location=no,menubar=no');
}


setTweetValue();
</script>
