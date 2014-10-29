	<%@ include file="../common/include.jsp" %>
	<%@ include file="../common/greybox.jsp" %>
	<%@ include file="../common/autosuggesttextarea.jsp" %>
	<%@ include file="../common/autocomplete.jsp" %>
	<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

	<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>

	<bean:define id="sform" name="scheduleInterviewForm" type="com.form.ScheduleInterviewForm" />

	<%
	//response.setHeader("Cache-Control", "no-cache");
			//response.setHeader("Pragma", "no-cache");
			//response.setIntHeader("Expires", 0);
	%>
	<%
    
	String datastring1 = (String)request.getAttribute("timedatastring1");

	%>

	
	<br>

	<style type="text/css">
	#myAutoComplete {
		width:5em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}
	#myAutoComplete1 {
		width:5em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}

  
	</style>
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

<script type="text/javascript">
var ccids="";
var ccnames="";


String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
String.prototype.ltrim = function() {
	return this.replace(/^\s+/,"");
}
String.prototype.rtrim = function() {
	return this.replace(/\s+$/,"");
}
function strStartsWith(str, prefix) { 
	return str.indexOf(prefix) === 0;
	}

$(function() {

	//$("#reviewername").autocomplete('jsp/talent/getUserData.jsp');
	$("#reviewername").autocomplete({
		url: 'jsp/talent/getUserUserGroupData.jsp',
	     minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
	
			onItemSelect: function(item) {
		    
		  
		  var itemvaluedata = ""+item.data;
		  var itemvaluedataname = ""+item.value;
		  
		 
         if(strStartsWith(itemvaluedata,"g")){
			 document.scheduleInterviewForm.reviewername.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 var idval=itemvaluedata;
			 document.scheduleInterviewForm.reviewerid.value=idval.substring(1,idval.length);
			 document.scheduleInterviewForm.isgroup.value="Y";
			 document.scheduleInterviewForm.reviewernamehidden.value=itemvaluedataname.substring(44,itemvaluedataname.length);
			 			 			 
		 }else{
		document.scheduleInterviewForm.reviewerid.value=item.data;
		 document.scheduleInterviewForm.isgroup.value="N";
		 document.scheduleInterviewForm.reviewername.value=itemvaluedataname.substring(33,itemvaluedataname.length);
         document.scheduleInterviewForm.reviewernamehidden.value=itemvaluedataname.substring(33,itemvaluedataname.length);
		 }
		
		//alert(item.data);
		}
		});

		$("#watchlistnamesnew").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
	     
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		 ccids = item.data + ","+ ccids;
		 ccnames =  item.value + ","+ ccnames;
		document.scheduleInterviewForm.watchlistids.value=ccids;
		document.scheduleInterviewForm.watchlistnames.value=ccnames;

		
		var tempurl = '<a href="#" onclick=window.open('+"user.do?method=edituser&readPreview=2&userId="+item.data+")>"+item.value+"</a>"

		document.getElementById("watchlist").innerHTML = document.getElementById("watchlist").innerHTML + " , " +tempurl;
		document.scheduleInterviewForm.watchlistnamesnew.value="";
		
		//alert(item.data);
		}
		});



});

	</script>


	<script language="javascript">


function validateUser(){
	//alert(document.scheduleInterviewForm.reviewerid.value);
	document.scheduleInterviewForm.reviewername.value=document.scheduleInterviewForm.reviewernamehidden.value;
}
	function closewindow(){
		parent.parent.GB_hide();
    window.top.location.reload();

	  
	}

	function opensearchassignedto(){
		   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=intschreassign");
  window.open(url, "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  //window.open("user.do?method=assignedtoselector&boxnumber=intschreassign", "SearchHiringMgr","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function opensearchwatchlist(){
  		   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=watchlistselector");
  window.open(url, "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  //window.open("user.do?method=watchlistselector", "SearchUsers","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		   window.top.hidePopWin();
		   } 
		}

	function savedata(){

		var alertstr = "";
    var date = document.scheduleInterviewForm.date.value;
	var interviewer = document.scheduleInterviewForm.reviewerid.value;
//	var commentsa = document.scheduleInterviewForm.notes.value;
		
	var showalert=false;

	if(date == "" || date == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Date_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}

	if(interviewer == "" || interviewer == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Reviewer_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}


	
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }


	
		
		  document.scheduleInterviewForm.action = "scheduleInterview.do?method=scheduleInterview&applicantId=<bean:write name="scheduleInterviewForm" property="applicantId"/>&eventype=<%=sform.getEventType()%>&reschedule=<%=sform.getReschedule()%>&reviewerid="+interviewer;
	   document.scheduleInterviewForm.submit();
	   //window.top.hidePopWin();
	   
		}

		
function opensearch(){
     var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("evtmpl.do?method=selectevtemplates");
  window.open(url, "SearchEvaluationTemplate","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  //window.open("evtmpl.do?method=selectevtemplates", "SearchEvaluationTemplate","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}



	</script>


<%
String errorsendingemail = (String)request.getAttribute("errorsendingemail");
if(errorsendingemail != null && errorsendingemail.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("hr.interview.schedule.error.msg",user1.getLocale())%></font></td>
			<td> </td>
		</tr>
		
	</table>
</div>
<%}%>
<%
String isinterviewscheduled = (String)request.getAttribute("isinterviewscheduled");



	
if(isinterviewscheduled != null && isinterviewscheduled.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>

	

			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Applicant_sent_to_reviewer",user1.getLocale())%></font></td>
			<td><!-- <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>

		</tr>
		
	</table>
</div>
<br>
<!--<body class="yui-skin-sam" onload='top.location.reload(true);'>-->
<%}else {%>

	<body class="yui-skin-sam">
	<html:form action="/scheduleInterview.do?method=scheduleInterview"   enctype="multipart/form-data">

	<div align="center" class="div">
		<table border="0" width="100%">
		<font color = red ><html:errors /> </font>
		
<%
String roundtext = "";
if(sform.getEventType() == 0) roundtext="screening";
if(sform.getEventType() > 0) {
	roundtext="interview round - "+sform.getEventType();
}

%>

			<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.SendAppProfile",user1.getLocale())%> <%=roundtext%> 
			
			
			
			<br>

<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Mode_of_Interview",user1.getLocale())%></td>
			<td>
			<html:select  property="modofinterviewid">
			<bean:define name="scheduleInterviewForm" property="modeofInterviewList" id="modeofInterviewList" />

            <html:options collection="modeofInterviewList" property="modofinterviewid"  labelProperty="modofinterviewName"/>
			</html:select>
			</td>
		</tr>
		
  <tr>
				<td><%=Constant.getResourceStringValue("hr.dates.date",user1.getLocale())%><span1>*<span1></td>
      <td>
	  <SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
	 <INPUT TYPE="text" NAME="date" readonly="true" value="" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.scheduleInterviewForm.date,'anchor1xx','<%=datepattern%>'); return false;" TITLE="select date" NAME="anchor1xx" ID="anchor1xx">
<img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

     <select name="hr">
  
  <option value="1"><%=Constant.getResourceStringValue("hr.dates.1",user1.getLocale())%></option>
  <option value="2"><%=Constant.getResourceStringValue("hr.dates.2",user1.getLocale())%></option>
  <option value="3"><%=Constant.getResourceStringValue("hr.dates.3",user1.getLocale())%></option>
  <option value="4"><%=Constant.getResourceStringValue("hr.dates.4",user1.getLocale())%></option>
  <option value="5"><%=Constant.getResourceStringValue("hr.dates.5",user1.getLocale())%></option>
  <option value="6"><%=Constant.getResourceStringValue("hr.dates.6",user1.getLocale())%></option>
  <option value="7"><%=Constant.getResourceStringValue("hr.dates.7",user1.getLocale())%></option>
  <option value="8"><%=Constant.getResourceStringValue("hr.dates.8",user1.getLocale())%></option>
  <option value="9"><%=Constant.getResourceStringValue("hr.dates.9",user1.getLocale())%></option>
  <option value="10"><%=Constant.getResourceStringValue("hr.dates.10",user1.getLocale())%></option>
  <option value="11"><%=Constant.getResourceStringValue("hr.dates.11",user1.getLocale())%></option>
  <option value="12"><%=Constant.getResourceStringValue("hr.dates.12",user1.getLocale())%></option>
  </select>

   <select name="minute">
  <option value="00"><%=Constant.getResourceStringValue("hr.dates.00",user1.getLocale())%></option>
  <option value="15"><%=Constant.getResourceStringValue("hr.dates.15",user1.getLocale())%></option>
  <option value="30"><%=Constant.getResourceStringValue("hr.dates.30",user1.getLocale())%></option>
  <option value="45"><%=Constant.getResourceStringValue("hr.dates.45",user1.getLocale())%></option>
 
  </select>

  <input type="radio" name="ampm" value="am" checked><%=Constant.getResourceStringValue("hr.dates.am",user1.getLocale())%>
  <input type="radio" name="ampm" value="pm"><%=Constant.getResourceStringValue("hr.dates.pm",user1.getLocale())%><br>

	 </td>
	  </tr>
<tr>
				<td><%=Constant.getResourceStringValue("hr.dates.Duration",user1.getLocale())%></td>
				<td>
				
				<select name="dhr">
  <option value="0"><%=Constant.getResourceStringValue("hr.dates.0",user1.getLocale())%> <%=Constant.getResourceStringValue("hr.dates.hr",user1.getLocale())%></option>
   <option value="1"><%=Constant.getResourceStringValue("hr.dates.1",user1.getLocale())%> <%=Constant.getResourceStringValue("hr.dates.hr",user1.getLocale())%></option>
  <option value="2"><%=Constant.getResourceStringValue("hr.dates.2",user1.getLocale())%> <%=Constant.getResourceStringValue("hr.dates.hrs",user1.getLocale())%></option>
  <option value="3"><%=Constant.getResourceStringValue("hr.dates.3",user1.getLocale())%> <%=Constant.getResourceStringValue("hr.dates.hrs",user1.getLocale())%></option>
  <option value="4"><%=Constant.getResourceStringValue("hr.dates.4",user1.getLocale())%> <%=Constant.getResourceStringValue("hr.dates.hrs",user1.getLocale())%></option>
  <option value="5"><%=Constant.getResourceStringValue("hr.dates.5",user1.getLocale())%> <%=Constant.getResourceStringValue("hr.dates.hrs",user1.getLocale())%></option>
  <option value="6"><%=Constant.getResourceStringValue("hr.dates.6",user1.getLocale())%> <%=Constant.getResourceStringValue("hr.dates.hrs",user1.getLocale())%></option>
  <option value="7"><%=Constant.getResourceStringValue("hr.dates.7",user1.getLocale())%> <%=Constant.getResourceStringValue("hr.dates.hrs",user1.getLocale())%></option>
  <option value="8"><%=Constant.getResourceStringValue("hr.dates.8",user1.getLocale())%> <%=Constant.getResourceStringValue("hr.dates.hrs",user1.getLocale())%></option>
  <option value="9"><%=Constant.getResourceStringValue("hr.dates.9",user1.getLocale())%> <%=Constant.getResourceStringValue("hr.dates.hrs",user1.getLocale())%></option>
  <option value="10"><%=Constant.getResourceStringValue("hr.dates.10",user1.getLocale())%> <%=Constant.getResourceStringValue("hr.dates.hrs",user1.getLocale())%></option>
   </select>

   <select name="dminute">
  <option value="00"><%=Constant.getResourceStringValue("hr.dates.00",user1.getLocale())%></option>
  <option value="15"><%=Constant.getResourceStringValue("hr.dates.15",user1.getLocale())%></option>
  <option value="30"><%=Constant.getResourceStringValue("hr.dates.30",user1.getLocale())%></option>
  <option value="45"><%=Constant.getResourceStringValue("hr.dates.45",user1.getLocale())%></option>
 
  </select>
				
				</td>
			</tr>
 	<tr>
					<td><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.Reviewer",user1.getLocale())%><span1>*<span1></td>

		
				<td>
                <input type="hidden" name="reviewernamehidden">
                 <input type="text" id="reviewername" name="reviewername" autocomplete="off"   onblur="validateUser()">

				<span id="assignedto"></span>
<a href="#" onClick="opensearchassignedto();return false;"><img src="jsp/images/selector.gif" border="0"/></a>
<input type="hidden" name="reviewerid" />
<input type="hidden" name="isgroup" />


				</td>
			</tr>

<%-- 
<tr>
					<td><%=Constant.getResourceStringValue("aquisition.applicant.Watch_list",user1.getLocale())%></td>

		
				<td>
				<span id="watchlist"></span>
<a href="#" onClick="opensearchwatchlist();return false;"><img src="jsp/images/selector.gif" border="0"/></a>
				<input type="hidden" name="watchlistids" value=''/>
				<input type="hidden" name="watchlistnames" value=''/> 

<textarea NAME="watchlistnames" class="wickEnabled" cols="60" rows="4" ></textarea>

				</td>
			</tr>
		--%>	
			<tr>
				<td><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%></td>
				<td><html:textarea property="notes" cols="50" rows="4"/></td>
			</tr>

<!--
			<tr>
				<td>Evaluation Template</td>
				<td>&nbsp;&nbsp;<span id="evaluationsheet"></span>&nbsp;&nbsp;<a href="#" onClick="opensearch()">add evaluation sheet</a>
				<input type="hidden" name="evid" value=""/>
				<input type="hidden" name="evname" value=""/>
				
				</td>
			</tr>
-->
			<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.AttachEvaluationTemplateFile",user1.getLocale())%></td>
			<td><html:file property="evtmplFile"/> </td>
		</tr>
			<!--<tr>
				<td></td>
				<td><input type="hidden" name="charids" value="">
				<textarea NAME="evaluationcriteria" COLS=50 ROWS=5 readonly=true></textarea>
				</td>
			</tr>-->


			<tr>
				<td colspan="2">
				
				
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.send",user1.getLocale())%>" onClick="savedata()" class="button">
			
				 <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			</tr>

		</table>
	</div>

	</html:form>


	<%}%>



<script type="text/javascript">
	YAHOO.example.BasicLocal = function() {
		// Use a LocalDataSource
		var oDS = new YAHOO.util.LocalDataSource(<%=datastring1%>);
		// Optional to define fields for single-dimensional array
		oDS.responseSchema = {fields : ["state"]};

		// Instantiate the AutoComplete
		var oAC = new YAHOO.widget.AutoComplete("myInput", "myContainer", oDS);
		var oAC = new YAHOO.widget.AutoComplete("myInput1", "myContainer1", oDS);
		oAC.prehighlightClassName = "yui-ac-prehighlight";
		oAC.useShadow = true;
		
		return {
			oDS: oDS,
			oAC: oAC
		};
	}();
	</script>

	</body>