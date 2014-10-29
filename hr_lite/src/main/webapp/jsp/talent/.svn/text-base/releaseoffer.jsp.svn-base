	<%@ include file="../common/include.jsp" %>

	<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>

	<bean:define id="sform" name="applicantForm" type="com.form.ApplicantForm" />

	<%
	//response.setHeader("Cache-Control", "no-cache");
			//response.setHeader("Pragma", "no-cache");
			//response.setIntHeader("Expires", 0);
	%>
	
	  <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String budgetexceederror=(String)request.getAttribute("budgetexceederror");
String numberofpositionfilledexceeds=(String)request.getAttribute("numberofpositionfilledexceeds");

%>
	
	<br>

<style>
span1{color:#ff0000;}
</style>
	
   <STYLE>
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation
			{
			background-color:#6677DD;
			text-align:center;
			vertical-align:center;
			text-decoration:none;
			color:#FFFFFF;
			font-weight:bold;
			}
	.TESTcpDayColumnHeader,
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation,
	.TESTcpCurrentMonthDate,
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDate,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDate,
	.TESTcpCurrentDateDisabled,
	.TESTcpTodayText,
	.TESTcpTodayTextDisabled,
	.TESTcpText
			{
			font-family:arial;
			font-size:8pt;
			}
	TD.TESTcpDayColumnHeader
			{
			text-align:right;
			border:solid thin #6677DD;
			border-width:0 0 1 0;
			}
	.TESTcpCurrentMonthDate,
	.TESTcpOtherMonthDate,
	.TESTcpCurrentDate
			{
			text-align:right;
			text-decoration:none;
			}
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDateDisabled
			{
			color:#D0D0D0;
			text-align:right;
			text-decoration:line-through;
			}
	.TESTcpCurrentMonthDate
			{
			color:#6677DD;
			font-weight:bold;
			}
	.TESTcpCurrentDate
			{
			color: #FFFFFF;
			font-weight:bold;
			}
	.TESTcpOtherMonthDate
			{
			color:#808080;
			}
	TD.TESTcpCurrentDate
			{
			color:#FFFFFF;
			background-color: #6677DD;
			border-width:1;
			border:solid thin #000000;
			}
	TD.TESTcpCurrentDateDisabled
			{
			border-width:1;
			border:solid thin #FFAAAA;
			}
	TD.TESTcpTodayText,
	TD.TESTcpTodayTextDisabled
			{
			border:solid thin #6677DD;
			border-width:1 0 0 0;
			}
	A.TESTcpTodayText,
	SPAN.TESTcpTodayTextDisabled
			{
			height:20px;
			}
	A.TESTcpTodayText
			{
			color:#6677DD;
			font-weight:bold;
			}
	SPAN.TESTcpTodayTextDisabled
			{
			color:#D0D0D0;
			}
	.TESTcpBorder
			{
			border:solid thin #6677DD;
			}
</STYLE>
 
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
	<script language="javascript">

	function closewindow(){
	parent.parent.GB_hide();
	  
	}

	function opensearchwatchlist(){
		var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=watchlistselector");
  window.open(url, "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}
	function opensearchemailtemplate(){
		var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("orgemailtemplate.do?method=orgEmaillistselector");
		  window.open(url, "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

		//  window.open("orgemailtemplate.do?method=orgEmaillistselector", "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
		}
	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		  parent.parent.GB_hide();
		   } 
		}

	function savedata(){

		var alertstr = "";
  /*  var date = document.scheduleInterviewForm.date.value;
	var interviewer = document.scheduleInterviewForm.reviewerid.value;
		
	var showalert=false;

	if(date == "" || date == null){
     	alertstr = alertstr + "Date is  mandatory.<br>";
		showalert = true;
		}

	if(interviewer == "" || interviewer == null){
     	alertstr = alertstr + "Reviewer is  mandatory.<br>";
		showalert = true;
		}
	
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }*/


	 //  var doyou = confirm("Are you confirm ? offer will be sent to applicant (OK = Yes   Cancel = No)");
	   document.getElementById('progressbartable1').style.display = 'inline';
		  document.applicantForm.action = "applicant.do?method=releaseoffer&applicantId=<%=sform.getApplicantId()%>";
	   document.applicantForm.submit();
		    
	   
		}

		function init(){

		document.getElementById('progressbartable1').style.display = 'none';
		}

function addattachment(){
  var alertstr = "";
  var showalert=false;
	var filename = document.applicantForm.attachmentdata.value;

	
	if(filename == "" || filename == null){
     	alertstr = alertstr + " <%=Constant.getResourceStringValue("Requisition.attacment.alert1",user1.getLocale())%><br>";
		showalert = true;
		}

	
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
 	 document.applicantForm.action = "applicant.do?method=releasepfferattachment&applicantId=<%=sform.getApplicantId()%>";
	   document.applicantForm.submit();
}

function deleteattachment(url){
     
	 document.applicantForm.action = url;
	   document.applicantForm.submit();
}

		
function opensearch(){
		var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("evtmpl.do?method=selectevtemplates");
  window.open(url, "SearchEvaluationTemplate","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  //window.open("evtmpl.do?method=selectevtemplates", "SearchEvaluationTemplate","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}


function opensearchapprover(boxnumber){
	var tu ="user.do?method=userselector&boxnumber="+boxnumber;
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

 // window.open("user.do?method=userselector&boxnumber="+boxnumber, "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}


	</script>

<%

String attachmentsizeexceed = (String)request.getAttribute("attachmentsizeexceed");

if(attachmentsizeexceed != null && attachmentsizeexceed.equals("yes")){
	int limitfileSize =  new Integer(Constant.getValue("attachment.file.size.limit")).intValue();
	 limitfileSize = limitfileSize / (1024*1024);
	 String mbsize= String.valueOf(limitfileSize) + "MB";
	String[] mbargs = {mbsize};
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>

			<td><font color="red"><%=Constant.getResourceStringValue("aquisition.applicant.attachmentsize.exceed",user1.getLocale(),mbargs)%></font> </td>
	<td><!--  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>

		</tr>
		
	</table>
</div>
<%}%>

<%  if(budgetexceederror != null && budgetexceederror.equals("yes")){ %>
     <div align="center" class="msg">
		<table border="0" width="100%">
	
	
			<tr>
			<td><font color="red"><b> <%=Constant.getResourceStringValue("aquisition.applicant.Budget_range_exceeds",user1.getLocale())%> </b> </font></td>
		</tr>
		
	</table>
</div>
		   <%}%>
<%  if(numberofpositionfilledexceeds != null && numberofpositionfilledexceeds.equals("yes")){ %>
      <div align="center" class="msg">
		<table border="0" width="100%">
	
	
			<tr>
			<td><font color="red"><b><%=Constant.getResourceStringValue("aquisition.applicant.Offer_release_not_allowed",user1.getLocale())%>  </b> </font></td>
		</tr>
		
	</table>
</div>
		   <%}%>

<%

String errormessage = (String)request.getAttribute("errormessage");		   
%>

<%  if(errormessage != null){ %>
           <font color="red"><b> <%=errormessage%> . Please contact system administrator.</b> </font>
		   <%}%>

<%
String offerreleased = (String)request.getAttribute("offerreleased");
	
if(offerreleased != null && offerreleased.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>

			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.Offer_release_TO_applicnt",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>


		</tr>
		
	</table>
</div>
<br>
<%}else {%>

	<body class="yui-skin-sam" onLoad="init()">
	<html:form action="/applicant.do?method=releaseoffer" enctype="multipart/form-data">
	<font color = red ><html:errors /> </font>
	<div class="div">
		<table border="0" width="70%" cellspacing="10">
		



				<!--  <%=Constant.getResourceStringValue("aquisition.applicant.Release_offer",user1.getLocale())%> -->

		<% if(sform.getSalaryPlan() != null && !StringUtils.isNullOrEmpty(sform.getOfferedctc()) && sform.getSalaryPlan().getToRangeAmount() < new Long(sform.getOfferedctc()).longValue()){%>
		<br>
	<div align="center" class="msg">
		<font color="white">
			
			<%=Constant.getResourceStringValue("admin.offeredctc.exceed.from.salaryplan",user1.getLocale())%>

			</font></div><br>
		<%}%>
	<%
       
  String tempurl1 = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId="+EncryptDecrypt.encrypt(String.valueOf(sform.getRequitionId()))+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=930,height=800"+"'"+")>"+sform.getJobTitle()+"</a>";
		%>
		
<tr>
<td>
<%=Constant.getResourceStringValue("aquisition.applicant",user1.getLocale())%> #  <%=sform.getFullName()%>
</td>
<td>
<%=Constant.getResourceStringValue("Requisition.jobrequisition",user1.getLocale())%> #  <%=tempurl1%>
			
</td>
</tr>
           <tr>

				<td><%=Constant.getResourceStringValue("aquisition.applicant.Target_joining_date",user1.getLocale())%><font color="red">*</font></td>
      <td>
	  <SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv2");
cal1xx.showNavigationDropdowns();

</SCRIPT>
	 <INPUT TYPE="text" NAME="targetjoiningdate" readonly="true" value="<%=sform.getTargetjoiningdate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.applicantForm.targetjoiningdate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.applicantForm.targetjoiningdate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx">
<img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

</td>
</tr>

       <tr>
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.Offered_CTC",user1.getLocale())%>: </td>
			<td class="bodytext"><bean:write name="applicantForm" property="offeredctc"/>&nbsp;<bean:write name="applicantForm" property="offeredctccurrencycode"/>
			&nbsp;&nbsp;
			<% if(sform.getSalaryPlan() != null){
				if(!StringUtils.isNullOrEmpty(sform.getOfferedctc()) && sform.getSalaryPlan().getToRangeAmount() < new Long(sform.getOfferedctc()).longValue()){
				%>
			<font color="red">
			<%=Constant.getResourceStringValue("Requisition.salaryplan",user1.getLocale())%> #
			<%=sform.getSalaryPlan().getFromRangeAmount()%> -- <%=sform.getSalaryPlan().getToRangeAmount()%>  <%=sform.getSalaryPlan().getCurrencyCode()%>
			</font>
			<%}else{%>
		
			<%=Constant.getResourceStringValue("Requisition.salaryplan",user1.getLocale())%> #
			<%=sform.getSalaryPlan().getFromRangeAmount()%> -- <%=sform.getSalaryPlan().getToRangeAmount()%>  <%=sform.getSalaryPlan().getCurrencyCode()%>
			
			<%}}%>
			</td>
		</tr>

          	<tr>
				<td><%=Constant.getResourceStringValue("aquisition.applicant.Note",user1.getLocale())%>:</td>
				<td><html:textarea property="offerReleaseComment" cols="60" rows="5" /></td>
			</tr>







			 	<tr>

<%
String watchlistvalue ="<span id=\"watchlist\"></span>";
String watchlisttempurl = "";
String ids ="";
String uname="";
List watchlist = sform.getOfferwatchList();

if(watchlist != null){

for(int i=0;i<watchlist.size();i++){
OfferWatchList ch = (OfferWatchList)watchlist.get(i);


  ids = ids + ch.getUserId()+",";
  uname = uname + ch.getUsername()+",";
  watchlisttempurl   = watchlisttempurl + "<a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+ch.getUserId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600"+"'"+")>"+ch.getUsername()+"</a>&nbsp;&nbsp;";


}

watchlistvalue = "<span id=\"watchlist\">"+watchlisttempurl+"</span>";

} // end null check


	%>



				<td><%=Constant.getResourceStringValue("aquisition.applicant.Watch_list",user1.getLocale())%></td>
				<td><%=watchlistvalue%><a href="#" onClick="opensearchwatchlist()"><img src="jsp/images/selector.gif" border="0"/></a>
				<input type="hidden" name="watchlistids" value=<%=ids%>/>
				<input type="hidden" name="watchlistnames" value='<%=uname%>'/>
				</td>
			</tr>

<tr>
<td>
</td>
<td>

</td>
</tr>

<tr>
<td><%=Constant.getResourceStringValue("Requisition.attachments",user1.getLocale())%></td>
<td>
<%=Constant.getResourceStringValue("aquisition.applicant.File_details",user1.getLocale())%> : <input type="text" name="filedetails" value="">
<%=Constant.getResourceStringValue("aquisition.applicant.File",user1.getLocale())%> <html:file property="attachmentdata"/> 
<a href="#" onClick="addattachment()"><img src="jsp/images/ButtonInsert.gif" align="bottom" border="0" alt="Add" title="Add"/></a>
</td>
</tr>
<tr>
<td></td>
<td>
<%
List attachmentList = sform.getOfferAttachmentList();

if(attachmentList.size()>0){
	%>
	<table border="0" width="100%" class="div">
	<tr>
<td><b><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("aquisition.applicant.AddedBy",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("aquisition.applicant.added_date",user1.getLocale())%></b></td>
</tr>
<%
		for(int i=0;i<attachmentList.size();i++){
		ApplicantOfferAttachment offerattach = (ApplicantOfferAttachment)attachmentList.get(i);
		String deleteurl = "applicant.do?method=deleteattachmentofferrelease&uuid="+offerattach.getUuid()+"&applicantId="+sform.getApplicantId();

%>
<tr>
<td><%=(offerattach.getAttahmentdetails()==null)?"":offerattach.getAttahmentdetails()%></td>
<td>
<a href="applicant.do?method=offerattachmentdetails&uuid=<%=offerattach.getUuid()%>"><%=offerattach.getAttahmentname()%></a></td><td><%=offerattach.getCreatedBy()%></td><td>
<%=DateUtil.convertSourceToTargetTimezone(offerattach.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%>
</td>
<td>
<a href="#" onClick="deleteattachment('<%=deleteurl%>')">
<img src="jsp/images/delete.gif" border="0" alt="Delete" title="delete"/>

</a>
</td>


</tr>
<%}%>
</table>
<%}%>
</td>
</tr>

<tr>
<td colspan="2">
<%  if((budgetexceederror != null && budgetexceederror.equals("yes"))||(numberofpositionfilledexceeds != null && numberofpositionfilledexceeds.equals("yes"))){ %>
<%}else{%>
						<span id='progressbartable1'>
						<img src="jsp/images/indicator.gif" height="20"  width="40"/>
						</span>
<input type="button" name="release offer" value="<%=Constant.getResourceStringValue("aquisition.applicant.Release_offer",user1.getLocale())%>" onclick="savedata()" class="button"/>
<%}%>
 
<input type="button" name="release offer cancel" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onclick="discard()" class="button"/>
</td>
</table>

</div>







	<br>		

			
</html:form>

	<%}%>




	</body>

