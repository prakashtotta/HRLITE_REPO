<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%
 String path = (String)request.getAttribute("filePath");
 String datepattern = Constant.getValue("defaultdateformat");
 String source = (String)request.getParameter("source");
 String reqid = (String)request.getParameter("reqid");
 String secureid = (String)request.getParameter("secureid");
User user1 = new User();

if(source==null)source="OURCOMPANY";
com.bean.Locale locale = new com.bean.Locale();
		locale.setLocaleCode("en_US"); 
user1.setLocale(locale);
String superUserKey = (String)session.getAttribute(Common.SUPER_USER_KEY);
UserRegData userregdata = BOFactory.getUserBO().getUserRegDataById(new Long(EncryptDecrypt.decrypt(superUserKey)).longValue());

%>

<bean:define id="form" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<%
Map<String,List<String>> screenMap = BOFactory.getBusinessFilterBO().getVisibleAndMandatoryScreenFiledsByScreenCode(Common.REQUISITION_SCREEN,userregdata.getUserRegId());
List<String> screenFields = screenMap.get(Common.VISIBLE_FIELDS_LIST);
   %>

<head>
<link rel="stylesheet" type="text/css" href="jsp/css/ajaxtabs.css" />

<script type="text/javascript" src="jsp/js/ajaxtabs.js">

/***********************************************
* Ajax Tabs Content script v2.2- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

</script>

<script language="javascript">


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

</script>

<style type="text/css">

table.ex2
{
border-collapse:separate;
border-spacing:15px 3px;
}
</style>
</head>

<%

String backurl = request.getContextPath()+"/jobs.do?method=jobsearch&l_code="+user1.getLocale().getLocaleCode()+"&superUserKey="+superUserKey;
String addbookmarkdone = (String)request.getAttribute("addbookmarkdone");
String alreadyBookmarked = (String)request.getAttribute("alreadyBookmarked");

String jobapply = (String)request.getAttribute("jobapply");
if(addbookmarkdone != null && addbookmarkdone.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("Requisition.Bookmark_plz_click",user1.getLocale())%>  <a href="agjob.do?method=mybookmarks&requistionId=<%=form.getJobreqId()%>"><font color="white"><%=Constant.getResourceStringValue("Requisition.My_bookmarks",user1.getLocale())%></a> </font></td>
			
		</tr>
		
	</table>
</div>
<%}%>

<body>
<!--
<div style="background: #f3f3f3;">
<a  href="<%=backurl%>"> << <%=Constant.getResourceStringValue("Requisition.back.to.job.list",user1.getLocale())%></a>
</div>-->

<!--  <font size="5" face="verdana"><b><%=Constant.getResourceStringValue("Requisition.jobreqdetails",user1.getLocale())%></b></font>-->
 <br>
  <a class="button"  href="<%=request.getContextPath()%>/refferaluser.do?method=refferaluserdetails&from=applicantexternal&source=<%=source%>&secureid=<%=secureid%>&jobreqid=<%=reqid%>&locale_code=<%=user1.getLocale().getLocaleCode()%>" target="new"><%=Constant.getResourceStringValue("Requisition.apply.for.this.job",user1.getLocale())%></a>

<html:form action="/agjob.do?method=saveapplicantData">

<br>
 <%@ include file="jobdetailsbodycommon.jsp" %>


      
  <tr>
 
  <td colspan="2"> </td>
  </tr>

		
</html:form>	
</body>
</html>
