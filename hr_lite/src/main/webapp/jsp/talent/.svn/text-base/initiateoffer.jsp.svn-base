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
String ctcexeedssalaryplan=(String)request.getAttribute("ctcexeedssalaryplan");
String budgetexceederror=(String)request.getAttribute("budgetexceederror");

%>
	
	<br>


	
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
 <style>
span1{color:#ff0000;}
span#approversblk{
	border: 1px solid #999999; 	position: relative;	width:50px; margin: 0pt; padding: 1em; background-color: #ffff99;
}
</style>
<style type="text/css">
span.blue {background-color:white;width:250px;height:50px;}
span.green {color:darkolivegreen;font-weight:bold}
</style>

<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
	<script language="javascript">
function closewindow(){
	  parent.parent.GB_hide();
}

	function opensearchassignedto(){
     var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=intschofferowner");
   window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=600");

 // window.open("user.do?method=assignedtoselector&boxnumber=intschofferowner", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}


	function opensearchwatchlist(){
		var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=watchlistselector");
  window.open(url, "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=600");
}

	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		   window.top.hidePopWin();
		   parent.parent.GB_hide();
		   } 
		}

	function savedata(){

		var alertstr = "";
   var targerofferdate = document.applicantForm.targerofferdate.value;
   var targetjoiningdate = document.applicantForm.targetjoiningdate.value;
   var currectctc = document.applicantForm.currectctc.value;
   var expectedctc = document.applicantForm.expectedctc.value;
   var offeredctc = document.applicantForm.offeredctc.value;
	//var interviewer = document.scheduleInterviewForm.reviewerid.value;
		
	var showalert=false;

	/*if(targerofferdate == "" || targerofferdate == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Targetofferdate_alert",user1.getLocale())%><br>";
		showalert = true;
		}*/
	
	if(targetjoiningdate == "" || targetjoiningdate == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Targetjoiningdate_alert",user1.getLocale())%><br>";
		showalert = true;
		}
	
	if(isNaN(currectctc)){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Creent_CTC",user1.getLocale())%><br>";
			showalert = true;
		}

		if(isNaN(expectedctc)){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Expected_CTC",user1.getLocale())%><br>";
			showalert = true;
		}
	 if(isNaN(offeredctc)){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Offered_CTC",user1.getLocale())%><br>";
			showalert = true;
		}
	 if (showalert){
     	alert(alertstr);
        return false;
          }


	
		
		  document.applicantForm.action = "applicant.do?method=initiateoffersubmit&applicantId=<%=sform.getApplicantId()%>";
	   document.applicantForm.submit();
	   //window.top.hidePopWin();
	   
		}




function addattachment(){

	var filename = document.applicantForm.attachmentdata.value;
	if(filename == "" || filename == null){
		alert("Please select a file");
	return false;
	}
 	 document.applicantForm.action = "applicant.do?method=initiatepfferattachment&applicantId=<%=sform.getApplicantId()%>";
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


function opensearchapprover(bn){
	//alert(boxnumber);
	var t = "user.do?method=approverselector&stringboxval=approver&boxnumber="+bn;
	var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(t);
  
  window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
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
			<td><!--  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a --></td>
		</tr>
		
	</table>
</div>

<%}%>


<%  if(ctcexeedssalaryplan != null && ctcexeedssalaryplan.equals("yes")){ %>
<div align="center" class="msg">
	<table border="0" width="100%">
	<tr>
			<td> <font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.Offered_CTC_exceedsalert",user1.getLocale())%> </font>
			</td>
		</tr>
		
	</table>
</div>
		   <%}%>
<%  if(budgetexceederror != null && budgetexceederror.equals("yes")){ %>
  <div align="center" class="msg">
	<table border="0" width="100%">
	<tr>
		<td>
		   <font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.Budget_range_exceeds",user1.getLocale())%>  </font>
		   </td>
		</tr>
		
	</table>
</div>
		   <%}%>
<%
String isofferinitiated = (String)request.getAttribute("isofferinitiated");
	
if(isofferinitiated != null && isofferinitiated.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>

			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.AppSentmsg",user1.getLocale())%></font>
			<!-- <a href="#" onClick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>--></td>

			<td> </td>

		</tr>
		
	</table>
</div>

<%}else {%>

	<body class="yui-skin-sam">
	<html:form action="/applicant.do?method=initiateoffersubmit" enctype="multipart/form-data">

	<div align="left" class="div">
		<table border="0" width="70%" cellspacing="10">
		<font color = red ><html:errors /> </font>
		
	<%
       
  String tempurl1 = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=editjobreqselector&approverstatus=yes&offerapplicant=yes&jobreqId="+EncryptDecrypt.encrypt(String.valueOf(sform.getRequitionId()))+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=930,height=800"+"'"+")>"+sform.getJobTitle()+"</a>";
		%>	
<tr>
<td width="33%">
<b><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_name",user1.getLocale())%> #</b>  <%=sform.getFullName()%>
</td>
<td>
<b><%=Constant.getResourceStringValue("aquisition.Bulkupload.Job_Requistion",user1.getLocale())%> #</b>  <%=tempurl1%>
			
</td>
<td align="right">
<b><%=Constant.getResourceStringValue("Requisition.salaryplan",user1.getLocale())%> #</b>
<% if(sform.getSalaryPlan() == null ){%>
<%=Constant.getResourceStringValue("admin.NA",user1.getLocale())%>
<%}else{%>
<%=sform.getSalaryPlan().getFromRangeAmount()%> -- <%=sform.getSalaryPlan().getToRangeAmount()%>  <%=sform.getSalaryPlan().getCurrencyCode()%>
<%}%>

</td>

</tr>

</table>

<table border="0" width="100%" cellspacing="5" class="div">


    <tr>

				<td><%=Constant.getResourceStringValue("aquisition.applicant.Target_offer_date",user1.getLocale())%></td>
      <td>
	  <SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
	 <INPUT TYPE="text" NAME="targerofferdate" readonly="true" value="<%=sform.getTargerofferdate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.applicantForm.targerofferdate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="select date" NAME="anchor1xx" ID="anchor1xx">
<img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

</td>
</tr>
<tr>
<td><%=Constant.getResourceStringValue("aquisition.applicant.Target_joining_date",user1.getLocale())%><font color="red">*</font></td>
      <td>
	  <SCRIPT LANGUAGE="JavaScript" ID="jscal1xx1">
var cal1xx1 = new CalendarPopup("testdiv2");
cal1xx1.showNavigationDropdowns();

</SCRIPT>
	 <INPUT TYPE="text" NAME="targetjoiningdate" readonly="true" value="<%=sform.getTargetjoiningdate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx1.select(document.applicantForm.targetjoiningdate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="select date" NAME="anchor1xx1" ID="anchor1xx1">
<img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

<%if(sform.getInterviewState() != null && sform.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION)){%>
<%if(sform.getChagetargetjoiningdate() != null){%>
<font color="red"><%=Constant.getResourceStringValue("aquisition.applicant.request_target_joining_date",user1.getLocale())%> : <bean:write name="applicantForm" property="chagetargetjoiningdate"/> </font>
<%}}%>


</td>
</tr>

<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Current_CTC",user1.getLocale())%></td>
			<td><html:text property="currectctc" size="20"/>
			<html:hidden property="currectctccurrencycode" />
			<%=sform.getCurrectctccurrencycode()%>
			</td>
</tr>
<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Expected_CTC",user1.getLocale())%></td>
			<td><html:text property="expectedctc" size="20"/>
			<html:hidden property="expectedctccurrencycode"/>
			<%=sform.getExpectedctccurrencycode()%>
			</td>
		</tr>

<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Offered_CTC",user1.getLocale())%></td>
			<td><html:text property="offeredctc" size="20"/>
			<html:hidden property="offeredctccurrencycode"/>
			<%=sform.getOfferedctccurrencycode()%>

			<%if(sform.getInterviewState() != null && sform.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION)){%>
<%if(sform.getChangeofferedctc() != null){%>
<font color="red"><%=Constant.getResourceStringValue("aquisition.applicant.request_CTC",user1.getLocale())%> : <bean:write name="applicantForm" property="changeofferedctc"/>&nbsp;<bean:write name="applicantForm" property="changeofferedctccurrencycode"/> </font>
<%}}%>

			</td>
			
		</tr>
</table>
<table border="0" width="100%" cellspacing="5" class="div">
<tr>
<td>
<%List benefitList = sform.getOtherBenefitsList();%>
<input type="hidden" value="<%=(benefitList == null)?"0":benefitList.size()%>" id="theValue3" />
<a href="javascript:;" onclick="addElement3();"><%=Constant.getResourceStringValue("aquisition.applicant.Add_other_benefits",user1.getLocale())%></a>
</td>
<td>
</td>
</tr>
<tr>
<td>
</td>
<td>
<div id="myDiv3">
<%
				int k=1;
			    if(benefitList != null && benefitList.size()>0){
					for(int i=0;i<benefitList.size();i++){
                      ApplicantOtherBenefits otherbenefit = (ApplicantOtherBenefits)benefitList.get(i);

						 String tdiv = "my3"+k+"Div";
						 String tspan = "benefit_"+k;
						 String benefitname = "benefitname_"+k;
						 String benefitamount ="benefitamount_"+k;
						 String currecncycodetxt = otherbenefit.getCurrencyCode();
						 String leveltext1 = "Benefit-"+k+"&nbsp;&nbsp";
		
                 	String tempdivappotherbenefit = "<div id='"+tdiv+"'"+">"+
								"<span id='"+tspan+"'"+">"+ leveltext1 +
								"<input type=\"text\" size='40' value='"+otherbenefit.getName()+"' name='"+benefitname+"'"+"/>"+"&nbsp;&nbsp;"+
								 "<input type=\"text\" size='10'  value="+otherbenefit.getAmount()+" name='"+benefitamount+"'"+"/>&nbsp;&nbsp"+currecncycodetxt+
								 "</span>"+
									"";

String tempdivappdel =	" <a href=\'#\' onclick=\'removeElement3("+tdiv+","+k+")\'><img src=\"jsp/images/delete.gif\" border=\"0\" width=\"18\" height=\"18\"/></a>";
String tempdivapp1 =  "</div>";
String finaldivotherbenefit = tempdivappotherbenefit+tempdivappdel+tempdivapp1;
			%>
<%=finaldivotherbenefit%>
			
			<%
				k++;
					}// end for loop	
			}
			%>
</div>
</td>
<input type="hidden" name="idlistbenefits" value=""/>
</tr>



<%
		String offerownervalue ="<span id=\"assignedto\"></span>";
      
   String offerownertempurl = "<a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+user1.getUserId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600"+"'"+")>"+user1.getFirstName()+"&nbsp;"+user1.getLastName()+"</a>";

if(sform.getOfferownerId()>0){

	  offerownertempurl = "<a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+sform.getOfferownerId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600"+"'"+")>"+sform.getOfferownerName()+"</a>";

}

 offerownervalue = "<span id=\"assignedto\">"+offerownertempurl+"</span>";


		%>

</table>
<table border="0" width="100%" cellspacing="5" class="div">

 	<tr>
					<td><%=Constant.getResourceStringValue("aquisition.applicant.Offer_owner",user1.getLocale())%><font color="red">*</font></td>

		
				<td>
				<%=offerownervalue%>
<a href="#" onClick="opensearchassignedto()"><img src="jsp/images/selector.gif" border="0"/></a>
<input type="hidden" name="offerownerId" value="<%=user1.getUserId()%>"/>
<input type="hidden" name="offerownerName" value="<%=user1.getFirstName()+" "+user1.getLastName()%>"/>
<input type="hidden" name="isgroup" />
<input type="hidden" name="idlistvaluser" value=""/>
				</td>

			</tr>

<%
String watchlistvalue ="<span id=\"watchlist\"></span>";
String watchlisttempurl = "";
String ids ="";
String uname="";
List watchlist = sform.getInitiateOfferwatchList();

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



			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%></td>
				<td><html:textarea property="offerInitiationComment" cols="60" rows="4" /></td>
			</tr>
<%if(sform.getInterviewState() != null && sform.getInterviewState().equals(Common.OFFER_IN_NEGOTIATION)){%>
<%if(sform.getChangeofferComment() != null){%>
<font color="red">
         <tr>
				<td><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_comment",user1.getLocale())%></td>
				<td><bean:write name="applicantForm" property="changeofferComment"/></td>
			</tr>

</font>
<%}}%>
			
</table>
<table border="0" width="100%" cellspacing="5" class="div">

<tr>
<td>

<%
List offerapproverList = sform.getOfferApproverList();
	%>
	<%=Constant.getResourceStringValue("aquisition.applicant.Add_approvers",user1.getLocale())%>

<input type="hidden" value="<%=offerapproverList.size()%>" id="theValue2" />
<p><a href="javascript:;" onclick="addElement2();return false"><img src="jsp/images/add.gif" border="0" alt="add approver" title="<%=Constant.getResourceStringValue("aquisition.applicant.Add_approvers",user1.getLocale())%>" height="20"  width="19"/></a></p>
</td>
<td>

</td>
</tr>
<tr>
<td>

</td>
<td>
<div id="myDiv2"> 

<%
k=1;
for(int i=0;i<offerapproverList.size();i++){
	OfferApprover japp = (OfferApprover)offerapproverList.get(i);
     String tdiv = "my2"+k+"Div";
	 String tspan = "approverspan_"+k;
	 String tusername = "username_"+k;
	 String tuserid ="userid_"+k;
	 String apptx = " Approved";
	 String approvedtext = (japp.getApproved().equals("Y"))?apptx:"";
	 String leveltext1 = "Order-"+k+"&nbsp;&nbsp";
	 String usergroupvanme = "isGroup_"+k;
	 String isusergroup = "N";
	 String userlink = "";
     if(japp.getIsGroup() != null && japp.getIsGroup().equals("Y")){
		 isusergroup = "Y";

		 userlink="	<img src=\"jsp/images/User-Group-icon.png\">"+
		"<a href=\"#\" onClick=\"window.open('user.do?method=edituser&readPreview=2&userId="+japp.getApproverId()+"', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')\">"+japp.getApproverName()+"</a>";
	 }else{
		userlink="	<img src=\"jsp/images/user.gif\">"+
		"<a href=\"#\" onClick=\"window.open('user.do?method=edituser&readPreview=2&userId="+japp.getApproverId()+"', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')\">"+japp.getApproverName()+"</a>";
	 }
	String tempdivapp = "<div id='"+tdiv+"'"+">"+
		"<span class='blue' id='"+tspan+"'"+">"+ leveltext1 +
		"<input type=\"hidden\" value='"+japp.getApproverName()+"' name='"+tusername+"'"+"/>"+" "+userlink + "  "+
       "<input type=\"hidden\"   value="+japp.getApproverId()+" name='"+tuserid+"'"+"/>"+
	   "<input type=\"hidden\"   value="+isusergroup+" name='"+usergroupvanme+"'"+"/>"+	
	"</span>"+
		"<a href=\'#\' onclick=\'opensearchapprover("+k+")\'><img src=\"jsp/images/selector.gif\" border=\"0\"/></a> ";

	String tempdivappsys = "<div id='"+tdiv+"'"+">"+
		"<span class='blue' id='"+tspan+"'"+">"+ leveltext1 +
		"<input type=\"hidden\" value='"+japp.getApproverName()+"' name='"+tusername+"'"+"/>"+" "+userlink + "  "+
       "<input type=\"hidden\"   value="+japp.getApproverId()+" name='"+tuserid+"'"+"/>"+
	 "<input type=\"hidden\"   value="+isusergroup+" name='"+usergroupvanme+"'"+"/>"+		
	"</span>"+
		"";

String tempdivappdel =	" <a href=\'#\' onclick=\'removeElement2("+tdiv+","+k+");return false\'><img src=\"jsp/images/delete.gif\" width=\"18\" height=\"18\" border=\"0\"/></a>";

String tempdivapp1 = approvedtext + "</div>";
if(japp.getIsFromSystemRule().equals("N")){
	tempdivapp = tempdivapp + tempdivappdel + tempdivapp1;
}else{
	tempdivapp = tempdivappsys +   Constant.getResourceStringValue("approver.system.rule.defind",user1.getLocale())  + tempdivapp1;
}

	k++;
%>

<%=tempdivapp%>

<%}%>


</div>
</td>
</tr>
</table>
<table border="0" width="100%" cellspacing="5" class="div">

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
<a href="#" onClick="addattachment()"><img src="jsp/images/ButtonInsert.gif" border="0" alt="Add" title="<%=Constant.getResourceStringValue("Requisition.add",user1.getLocale())%>"/></a>
</td>
</tr>
<tr>
<td></td>
<td>
<%
List attachmentList = sform.getOfferAttachmentList();

if(attachmentList != null && attachmentList.size()>0){
	%>
	<table>
<th><%=Constant.getResourceStringValue("aquisition.applicant.details",user1.getLocale())%></th>
<th><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></th>
<th><%=Constant.getResourceStringValue("aquisition.applicant.AddedBy",user1.getLocale())%></th>
<th><%=Constant.getResourceStringValue("aquisition.applicant.added_date",user1.getLocale())%></th><th></th>
<%
		for(int i=0;i<attachmentList.size();i++){
		ApplicantOfferAttachment offerattach = (ApplicantOfferAttachment)attachmentList.get(i);
		String deleteurl = "applicant.do?method=deleteattachment&uuid="+offerattach.getUuid()+"&applicantId="+sform.getApplicantId();

%>
<tr>
<td><%=offerattach.getAttahmentdetails()%></td>
<td>
<a href="applicant.do?method=offerattachmentdetails&uuid=<%=offerattach.getUuid()%>"  ><%=offerattach.getAttahmentname()%></a></td><td><%=offerattach.getCreatedBy()%></td><td>
<%=DateUtil.convertSourceToTargetTimezone(offerattach.getCreatedDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%>
</td>
<td>
<a href="#" onClick="deleteattachment('<%=deleteurl%>')">
<img src="jsp/images/delete.gif" border="0" alt="delete" title="delete" width="18" height="18"/>

</a>
</td>


</tr>
<%}%>
</table>
<%}%>
</td>
</tr>
</table>
<table border="0" width="100%" cellspacing="5">

<tr>
<td>

<input type="button" name="initiate offer" value="<%=Constant.getResourceStringValue("aquisition.applicant.Initiate_offer",user1.getLocale())%>" onclick="savedata()" class="button"/>
<input type="button" name="initiate offer" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onclick="discard()" class="button"/>

</td>
<td>
</td>
</table>









	<br>		

			
</html:form>

	<%}%>




	</body>

<script language="javascript">
	function addElement2() {

  var ni = document.getElementById('myDiv2');
  var numi = document.getElementById('theValue2');
  var num = (document.getElementById('theValue2').value -1)+ 2;
  if(num < 50){
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my2'+num+'Div';
  newdiv.setAttribute('id',divIdName);

  var spanstart = '<span class="blue" id="approverspan_'+num+'">';
  var username1 = '<input type="hidden"  value="" name="username_'+num+'"/>';
  var userid1 = '<input type="hidden"    name="userid_'+num+'"/>';
   var levelid = '<input type="hidden"    name="level_'+num+'"/>';
   var isusergroup = '<input type="hidden"    name="isGroup_'+num+'"/>';
   var leveltext = 'Order-'+num+'&nbsp;&nbsp';
    var spanend = '</span>'; 
  newdiv.innerHTML = ''+spanstart+''+leveltext+''+levelid+''+username1+''+userid1+ ''+''+isusergroup+ ''+spanend+''+'<a href=\'#\' onclick=\'opensearchapprover('+num+')\'><img src=\"jsp/images/selector.gif\" border=\"0\"/></a>'+''+'  <a href=\'#\' onclick=\'removeElement2('+divIdName+','+num+');return false\'><img src=\"jsp/images/delete.gif\" width=\"18\" height=\"18\" border=\"0\"/></a>';
  ni.appendChild(newdiv);
  } else {
	  alert("maximum level exceed");
  }
}




function removeElement2(divNum,num) {
	var approvenum = 'userid_'+num;
	var idapprove=document.applicantForm[approvenum].value;
	var listidapprover=document.applicantForm.idlistvaluser.value;
var d = document.getElementById('myDiv2');
d.removeChild(divNum);
//document.getElementById('theValue2').value=num-1;
var array1=new Array();
var array1new=new Array();
//array1=listidapprover.split(/\.\d{2},?/);
array1=listidapprover;
for(var i=0;i<array1.length;i++){
	
	
	if(idapprove!=array1[i]){
		
			
			
       array1new[i]=array1[i];
	}
	
}
document.applicantForm.idlistvaluser.value=array1new;
//alert(array1new);
}


function addElement3() {

  var ni = document.getElementById('myDiv3');
  var numi = document.getElementById('theValue3');
  var num = (document.getElementById('theValue3').value -1)+ 2;
  if(num < 50){
  numi.value = num;
  var newdiv = document.createElement('div');
  var divIdName = 'my3'+num+'Div';
  newdiv.setAttribute('id',divIdName);

     var spanstart = '<span id="benefit_'+num+'">';
	var bname = '<input type="text"  size="40"  name="benefitname_'+num+'"/>&nbsp;&nbsp';
   var bamount = '<input type="text" size="10"   name="benefitamount_'+num+'"/>';
   var currencycodelevel = "&nbsp;&nbsp<%=sform.getOfferedctccurrencycode()%>";
   var leveltext = 'Benefit-'+num+'&nbsp;&nbsp';
    var spanend = '</span>'; 
  newdiv.innerHTML = ''+spanstart+''+leveltext+''+bname+''+bamount+ ''+currencycodelevel+''+spanend+''+'  <a href=\'#\' onclick=\'removeElement3('+divIdName+','+num+')\'><img src=\"jsp/images/delete.gif\" border=\"0\"/></a>';
  ni.appendChild(newdiv);
  } else {
	  alert("maximum level exceed");
  }
}

function removeElement3(divNum,num) {
	var approvenum = 'benefitname_'+num;
	var idapprove=document.applicantForm[approvenum].value;
	var listidapprover=document.applicantForm.idlistbenefits.value;
var d = document.getElementById('myDiv3');
d.removeChild(divNum);
//document.getElementById('theValue2').value=num-1;
var array1=new Array();
var array1new=new Array();
//array1=listidapprover.split(/\.\d{2},?/);
array1=listidapprover;
for(var i=0;i<array1.length;i++){
	
	
	if(idapprove!=array1[i]){
		
			
			
       array1new[i]=array1[i];
	}
	
}
document.applicantForm.idlistbenefits.value=array1new;
//alert(array1new);
}

</script>