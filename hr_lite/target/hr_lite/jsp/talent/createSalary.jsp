<%@ include file="../common/include.jsp" %>
 <html>
  <HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>

  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<style>
span1{color:#ff0000;}
</style>
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
<style type="text/css">
<style type="text/css">


<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 750px;
	border: 1px solid #999;  
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}



	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
/* Clear calendar's float, using dialog inbuilt form element */
    #container .bd form {
        clear:left;
    }

    /* Have calendar squeeze upto bd bounding box */
    #container .bd {
        padding:0;
    }

    #container .hd {
        text-align:left;
    }

    /* Center buttons in the footer */
    #container .ft .button-group {
        text-align:center;
    }

    /* Prevent border-collapse:collapse from bleeding through in IE6, IE7 */
    #container_c.yui-overlay-hidden table {
        *display:none;
    }

    /* Remove calendar's border and set padding in ems instead of px, so we can specify an width in ems for the container */
    #cal {
        border:none;
        padding:1em;
    }

    /* Datefield look/feel */
    .datefield {
        position:relative;
        top:10px;
        left:10px;
        white-space:nowrap;
        border:1px solid black;
        background-color:#eee;
        width:25em;
        padding:5px;
    }

    .datefield input,
    .datefield button,
    .datefield label  {
        vertical-align:middle;
    }

    .datefield label  {
        font-weight:bold;
    }

    .datefield input  {
        width:15em;
    }

    .datefield button  {
        padding:0 5px 0 5px;
        margin-left:2px;
    }

    .datefield button img {
        padding:0;
        margin:0;
        vertical-align:middle;
    }

    /* Example box */
    .box {
        position:relative;
        height:30em;
    }
	</style>

 </HEAD>
<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
<bean:define id="salaryplanform" name="salaryForm" type="com.form.SalaryForm" />


<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}

function closewindow()
		{
	// self.parent.location.reload();
	 parent.parent.GB_hide();
	}
function init(){
setTimeout ( "document.salaryForm.salaryPlanName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){
	var alertstr = "";
	var showalert=false;
	var numbers=/^[0-9]+$/;
	var name = document.salaryForm.salaryPlanName.value.trim();
	var decs=document.salaryForm.salaryPlanDesc.value.trim();
	var frmrangeamt = document.salaryForm.fromRangeAmount.value.trim();
	var torangeamt=document.salaryForm.toRangeAmount.value.trim();
	var curcode=document.salaryForm.currencyCode.value.trim();
	var year=document.salaryForm.salaryPlanYear.value.trim();
			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		if(decs == "" || decs == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Disc_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		if(numbers.test(frmrangeamt)==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.enter_From_amt_in_numbers",user1.getLocale())%><br>";
		showalert = true;
		}
		if(numbers.test(torangeamt)==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.enter_To_amt_in_numbers",user1.getLocale())%><br>";
		showalert = true;
		}
		if(year == "" || year == null){
		}else{
		if(numbers.test(year)==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.enter_To_amt_in_integer",user1.getLocale())%><br>";
		showalert = true;
		}
		}
		/* if(frmrangeamt == "" || frmrangeamt == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.enter_From_amt_in_numbers",user1.getLocale())%><br>";
		showalert = true;
		}
		if(torangeamt == "" || torangeamt == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.enter_To_amt_in_numbers",user1.getLocale())%><br>";
		showalert = true;
		}*/
		 
		if(curcode == "" || curcode == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Currency_code_required",user1.getLocale())%><br>";
		showalert = true;
		}
		if(decs.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.SalaryPlan.PlanDescription",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
		}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.salaryForm.action = "salaryplan.do?method=saveslaryplan&readPreview=3";
 document.salaryForm.submit();
 //self.parent.location.reload();

 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;
	var numbers=/^[0-9]+$/;	
	var name = document.salaryForm.salaryPlanName.value.trim();
	var decs=document.salaryForm.salaryPlanDesc.value.trim();
	var frmrangeamt = document.salaryForm.fromRangeAmount.value.trim();
	var torangeamt=document.salaryForm.toRangeAmount.value.trim();
	var curcode=document.salaryForm.currencyCode.value.trim();
	var year=document.salaryForm.salaryPlanYear.value.trim();
			 
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		if(decs == "" || decs == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Disc_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		if(numbers.test(frmrangeamt)==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.enter_From_amt_in_numbers",user1.getLocale())%><br>";
		showalert = true;
		}
		if(numbers.test(torangeamt)==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.enter_To_amt_in_numbers",user1.getLocale())%><br>";
		showalert = true;
		}
		if(year == "" || year == null){
		}else{
		if(numbers.test(year)==false){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.enter_To_amt_in_integer",user1.getLocale())%><br>";
		showalert = true;
		}
		}
		/*if(frmrangeamt == "" || frmrangeamt == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.enter_From_amt_in_numbers",user1.getLocale())%><br>";
		showalert = true;
		}
		if(torangeamt == "" || torangeamt == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.enter_To_amt_in_numbers",user1.getLocale())%><br>";
		showalert = true;
		}*/
		 
		if(curcode == "" || curcode == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Currency_code_required",user1.getLocale())%><br>";
		showalert = true;
		}
		if(decs.length > 500){
			alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.SalaryPlan.PlanDescription",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
			showalert = true;
		}
		 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.salaryForm.action = "salaryplan.do?method=updateslaryplan&readPreview=1&id="+'<bean:write name="salaryForm" property="salaryplanId"/>';
 document.salaryForm.submit();
 //self.parent.location.reload();

 //window.top.hidePopWin();
 
	}


</script>
<%
//String savesalary = (String)request.getAttribute("savesalary");
//String updatesalary  = (String)request.getAttribute("updatesalary ");
//String deletesalary  = (String)request.getAttribute("deletesalary ");

%>
<%
String savesalary = (String)request.getAttribute("savesalary");
	
if(savesalary != null && savesalary.equals("savesalary")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.SalaryPlan.savemsg",user1.getLocale())%> </font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updatesalary = (String)request.getAttribute("updatesalary");
	
if(updatesalary != null && updatesalary.equals("updatesalary")){
%>
<div align="center"class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.SalaryPlan.updatemsg",user1.getLocale())%></font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String deletesalary = (String)request.getAttribute("deletesalary");
	
if(deletesalary != null && deletesalary.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.SalaryPlan.deletemsg",user1.getLocale())%></font></td>
			<td><!--  <a class="closelink" href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </a> --></td>
		</tr>
		
	</table>
</div>
	<%}else{%>	
<br>
<body onload="init()" >

<html:form action="/salaryplan.do?method=saveslaryplan">


<div align="center" class="div">
	
<%

if ((salaryplanform.getReadPreview()).equals("2")){

%>
<br>
	 <fieldset><legend> <%=Constant.getResourceStringValue("admin.SalaryPlan.SalaryDetails",user1.getLocale())%></legend>
	 <table border="0" width="100%">
	<tr>
			<td></td>
			<td></td>
		</tr>
	
	 
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.PlanName",user1.getLocale())%></td>
			<td><%=(salaryplanform.getSalaryPlanName()==null)?"":salaryplanform.getSalaryPlanName()%></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.PlanDescription",user1.getLocale())%></td>
			<td><%=(salaryplanform.getSalaryPlanDesc()==null)?"":salaryplanform.getSalaryPlanDesc()%></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.FromAmount",user1.getLocale())%></td>
			<td><%=salaryplanform.getFromRangeAmount()%></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.ToAmount",user1.getLocale())%></td>
			<td><%=salaryplanform.getToRangeAmount()%></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.CurrencyCode",user1.getLocale())%></td>
			<td><%=(salaryplanform.getCurrencyCode()==null)?"":salaryplanform.getCurrencyCode()%></td> 
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.year",user1.getLocale())%></td>
			<td><%=(salaryplanform.getSalaryPlanYear()==null)?"":salaryplanform.getSalaryPlanYear()%></td>
		</tr>
		
		</table>
		</fieldset>
<!--		
	 <tr>
			<td>Start Date*</td>
			<td>	
	<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
	
<INPUT TYPE="text" NAME="effectiveStartDate" readonly="true" value="<%=salaryplanform.getEffectiveStartDate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.salaryForm.effectiveStartDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.salaryForm.effectiveStartDate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			</td>
		</tr>
		
	 <tr>
				<td>End Date*</td>
      <td>
	  
	   <SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code 

<INPUT TYPE="text" NAME="effectiveEndDate" readonly="true" value="<%=salaryplanform.getEffectiveEndDate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.salaryForm.effectiveEndDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.salaryForm.effectiveEndDate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			</td>
		</tr>
-->
	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
	  <tr><td></td><td></td></tr>
	  
<%}else{%>	
<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
			

	 
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.PlanName",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="salaryPlanName" size="50" maxlength="300"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.PlanDescription",user1.getLocale())%><font color="red">*</font></td>
			<td><html:textarea property="salaryPlanDesc" cols="50" rows="5"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.FromAmount",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="fromRangeAmount" maxlength="9"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.ToAmount",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="toRangeAmount" maxlength="9"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.CurrencyCode",user1.getLocale())%><font color="red">*</font></td>
			<td>
			<html:select  property="currencyCode">
			<bean:define name="salaryForm" property="currencyCodeList" id="currencyCodeList" />

            <html:options collection="currencyCodeList" property="currencyCode"  labelProperty="currencyName"/>
			</html:select>
			</td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.SalaryPlan.year",user1.getLocale())%></td>
			<td><html:text property="salaryPlanYear" maxlength="10"/></td>
		</tr>
<!--		
	 <tr>
			<td>Start Date*</td>
			<td>	
	<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
	 
<INPUT TYPE="text" NAME="effectiveStartDate" readonly="true" value="<%=salaryplanform.getEffectiveStartDate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.salaryForm.effectiveStartDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.salaryForm.effectiveStartDate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			</td>
		</tr>
		
	 <tr>
				<td>End Date*</td>
      <td>
	  
	   <SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>
The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code 

<INPUT TYPE="text" NAME="effectiveEndDate" readonly="true" value="<%=salaryplanform.getEffectiveEndDate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.salaryForm.effectiveEndDate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="cal1xx.select(document.salaryForm.effectiveEndDate,'anchor1xx','<%=datepattern%>'); return false;" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>

			
			</td>
		</tr>
-->
		<tr>
			<td colspan="2"><% if ((salaryplanform.getReadPreview()).equals("1")){%>			
			
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">		
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<%}%>
			<td></td>
		</tr>
	<%}%>
		
		</table>
		
</div>

</html:form>

</body>
<%}%>
