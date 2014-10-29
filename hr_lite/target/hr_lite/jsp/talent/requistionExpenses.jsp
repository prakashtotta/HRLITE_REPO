	<%@ include file="../common/include.jsp" %>
	<%@ include file="../common/autocomplete.jsp" %>
	<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

	<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>

	<bean:define id="wform" name="requistionExpenseForm" type="com.form.RequistionExpenseForm" />
<HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
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

<style type="text/css">
	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
</style>
</HEAD>

<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>

	<%
	//response.setHeader("Cache-Control", "no-cache");
			//response.setHeader("Pragma", "no-cache");
			//response.setIntHeader("Expires", 0);
	%>

<script language="javascript"> 

window.name = 'myModal';
</script>

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
		$("#voucherOwnerName").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
			minChars: '<%=Constant.getValue("autocomplete.min.chars")%>',
		     
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		  
		document.requistionExpenseForm.voucherOwnerId.value=item.data;
		document.requistionExpenseForm.voucherOwnerNamehidden.value=item.value;
		//alert(item.data);
		}
		});

	});
</script>
<script type="text/javascript">

function validateUser(){
	if(document.requistionExpenseForm.voucherOwnerNamehidden.value == "" || document.requistionExpenseForm.voucherOwnerNamehidden.value == null){
		document.requistionExpenseForm.voucherOwnerName.value = document.requistionExpenseForm.voucherOwnerName.value;
	}else{
	document.requistionExpenseForm.voucherOwnerName.value=document.requistionExpenseForm.voucherOwnerNamehidden.value;

	}
}

		function opensearchassignedto(){
			
		   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("user.do?method=assignedtoselector&boxnumber=reqexpenseuseradd");
			window.open(url, "serachuser","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

			}

			

		function savedata(){

		var alertstr = "";
  
	var voucherid = document.requistionExpenseForm.voucherId.value;
		
	var showalert=false;

	
	if(voucherid == "" || voucherid == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.intervieewlog.VoucherId_is_mandatory",user1.getLocale())%>";
		showalert = true;
		}


	
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }


		  document.requistionExpenseForm.action = "reqexp.do?method=addExpense&jobreqId=<%=wform.getJobReqId()%>";
	   document.requistionExpenseForm.submit();
	   
		}


	function removedata(){
		var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
        
	document.requistionExpenseForm.action = "reqexp.do?method=removeExpenses&jobreqId=<%=wform.getJobReqId()%>";
	   document.requistionExpenseForm.submit();
	  }
	}

	function checkAll(checkname, exby) {
		var len = checkname.length; 
if(len == undefined) len = 1; 
    if(len==1){
		document.requistionExpenseForm.expensechk.checked = exby.checked? true:false;
	}else{
	for (i = 0; i < len; i++){	  
  checkname[i].checked = exby.checked? true:false;
	}
	}
	}



	</script>
	
	<body class="yui-skin-sam">
	<html:form action="/reqexp.do?method=requistionExpenses" target='myModal'>


<b><%=Constant.getResourceStringValue("Requisition.add.expenses",user1.getLocale())%></b>
<br><br>
	<!-- add text box start-->
	<div class="div">
   <table>
<tr>
<td><b><%=Constant.getResourceStringValue("Requisition.voucher.no",user1.getLocale())%></b><span1>*</span1></td>
<td><b><%=Constant.getResourceStringValue("Requisition.voucher.date",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("Requisition.voucher.amount",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("Requisition.expensetype",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("Requisition.voucher.owner.name",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("Requisition.voucher.applicant",user1.getLocale())%></b></td>
<td></td>
</tr>
   <tr>
   <td>
   <html:text property="voucherId" size="20" maxlength="200"/>
   </td>
  <td>
			
<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();
</SCRIPT>


<INPUT TYPE="text" NAME="expenseDate" readonly="true" value="">

<A HREF="#" onClick="cal1xx.select(document.requistionExpenseForm.expenseDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.requistionExpenseForm.expenseDate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
</td>

   <td>
   <html:text property="totalamount" size="20" maxlength="200"/> <%=wform.getCurrencyCode()%>
   </td>
 <td>			
<html:select property="expenseTypesId">
<bean:define name="requistionExpenseForm" property="expenseTypeList" id="expenseTypeList" />
<html:options collection="expenseTypeList" property="expenseTypesId"  labelProperty="expenseTypesName"/>
</html:select>
	  
</td>

   <td>
 <input type="hidden" name="voucherOwnerNamehidden">
 <input type="text" id="voucherOwnerName" name="voucherOwnerName" autocomplete="off"   onblur="validateUser()">

				<span id="assignedto"></span>
<a href="#" onClick="opensearchassignedto();return false;"><img src="jsp/images/selector.gif" border="0"/></a>
<input type="hidden" name="voucherOwnerId" />
</td>

   <td>
   <input type="text" id="applicantName" name="applicantName">
   </td>


</tr>
</table>
<table>
<tr>
<td>
<%=Constant.getResourceStringValue("Requisition.expenses.note",user1.getLocale())%> : 

</td>
<td>
   <html:textarea property="note" rows="2" cols="50"/>    </td>
 <td>
<input type="button" name="login" value="<%=Constant.getResourceStringValue("Requisition.add",user1.getLocale())%>" onClick="savedata()" class="button">
</td>
</tr>
</table>
</div>
<br><br>

<%

List expenseLists = wform.getExpensesList();
 if(expenseLists != null && expenseLists.size()>0){
	 double total = 0;
%>
<div class="div">
	<table>
	<tr>
   <td>
   <input type="checkbox"  name="checkall"  onClick="checkAll(document.requistionExpenseForm.expensechk,this)">
   </td>
   <td>
   <b><%=Constant.getResourceStringValue("Requisition.expenses",user1.getLocale())%></b>
   </td>
	</tr>


<tr bgcolor="#f3f3f3">
<td></td>
<td><b><%=Constant.getResourceStringValue("Requisition.voucher.no",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("Requisition.voucher.date",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("Requisition.voucher.amount",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("Requisition.expensetype",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("Requisition.voucher.owner.name",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("Requisition.voucher.applicant",user1.getLocale())%></b></td>
<td><b><%=Constant.getResourceStringValue("Requisition.expenses.note",user1.getLocale())%></b></td>
<td></td>
</tr>

<% for(int i=0;i<expenseLists.size();i++){
   RequistionExpenses expenses = (RequistionExpenses)expenseLists.get(i);
   	String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
	total= total + expenses.getTotalamount();

%>


<tr bgcolor=<%=bgcolor%>>

   
   <td>
   <input type="checkbox"  name="expensechk" value="<%=expenses.getExpenseId()%>">
   </td>
   <td>
   <%=expenses.getVoucherId()%>
   </td>
      <td>
 
   <%=(expenses.getExpenseDate()==null)?"":DateUtil.convertSourceToTargetTimezoneWithoutTime(expenses.getExpenseDate(), user1.getTimezone().getTimezoneCode(), user1.getLocale())%>
   </td>
   <td>
   <%=expenses.getTotalamount()%>&nbsp;&nbsp;<%=expenses.getCurrencyCode()%>
   </td>
      <td>
   <%=(expenses.getExpenseType()==null)?"":expenses.getExpenseType().getExpenseTypesName()%>
   </td>

   <td>
   <% if( expenses.getVoucherOwner() != null){%>
<img src="jsp/images/user.gif">
<a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=expenses.getVoucherOwner().getUserId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=expenses.getVoucherOwner().getFirstName()+" "+expenses.getVoucherOwner().getLastName()%></a> 
<%}%>
   </td>
   <td>
   <%=(expenses.getApplicant()==null)?"":expenses.getApplicant().getFullName()%>
   </td>
     <td>
   <%=(expenses.getNote()==null)?"":expenses.getNote()%>
   </td>

	</tr>
<%}%>

<tr>
<td></td>
<td></td>
<td><b><%=Constant.getResourceStringValue("Requisition.expenses.total",user1.getLocale())%></b></td>
<td><%=total%>&nbsp;&nbsp;<%=wform.getCurrencyCode()%></td>

<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>

  <%}%>
<tr>
   <td>
  <input type="button" name="login" value="<%=Constant.getResourceStringValue("Requisition.watchers.remove",user1.getLocale())%>" onClick="removedata()" class="button">
   </td>
   <td>
   
   </td>
	</tr>

</table>

</div>
	</html:form>