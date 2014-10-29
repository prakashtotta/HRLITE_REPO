<%@ include file="../common/include.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>
<%@ page import="com.form.*" %>
<%@ page import="com.bean.lov.*" %>

<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.bean.filter.*" %>
<%
System.out.println(" createbusinessrule.jsp .... ");
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

String savebusinessrule =(String)request.getAttribute("saveapplicationfilter");
String updatebusinessrule =(String)request.getAttribute("updatebusinessrule");
%>
<bean:define id="bForm" name="businessRuleForm" type="com.form.BusinessRuleForm" />
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

function deleteApplicantFilter(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	document.businessRuleForm.action = "businessrule.do?method=deleteApplicantFilter&id="+'<bean:write name="businessRuleForm" property="businessCriteraId"/>';
	  document.businessRuleForm.submit();
	  }
}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){

	 parent.parent.GB_hide();
	 
	   } 
	}
function savedata(){

	var alertstr = "";
	var showalert=false;

	var name = document.businessRuleForm.name.value.trim();
	var variableCriteria = document.businessRuleForm.variableCriteria.value.trim();
	var vartype = document.businessRuleForm.variableType.value.trim();
	var filterValue1= document.businessRuleForm.filterValue1.value.trim();
	var filterValue2= document.businessRuleForm.filterValue2.value.trim();

	
	

	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

    if(vartype == "numeric"){
   	 if(variableCriteria == "" || variableCriteria == null || variableCriteria == "BETWEEN"){
   	   if(isNaN(filterValue1) || isNaN(filterValue2)){
   	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.filtervalues_is_numeric",user1.getLocale())%><BR>";
   			showalert = true;
   		}
   	 }else{
   		document.businessRuleForm.filterValue2.value="";
		if(variableCriteria == "" || variableCriteria == null || variableCriteria == "NOT_NULL"){
   			document.businessRuleForm.filterValue1.value="";
   			document.businessRuleForm.filterValue2.value="";
   	   	}else{
      	  if(isNaN(filterValue1)){
   	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.filtervalue_is_numeric",user1.getLocale())%><BR>";
   			showalert = true;
   			}
   	   	}
   	 }
	}

	 if (showalert){
	     alert(alertstr);
	     return false;
	 }

	  document.businessRuleForm.action = "businessrule.do?method=saveApplicantFilter";
	  document.businessRuleForm.submit();
}


function updatedata(){

	var alertstr = "";
	var showalert=false;

	var name = document.businessRuleForm.name.value.trim();
	var variableCriteria = document.businessRuleForm.variableCriteria.value.trim();
	var vartype = document.businessRuleForm.variableType.value.trim();
	var filterValue1= document.businessRuleForm.filterValue1.value.trim();
	var filterValue2= document.businessRuleForm.filterValue2.value.trim();
	

	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}



    if(vartype == "numeric"){
      	 if(variableCriteria == "" || variableCriteria == null || variableCriteria == "BETWEEN"){
      	   if(isNaN(filterValue1) || isNaN(filterValue2)){
      	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.filtervalues_is_numeric",user1.getLocale())%><BR>";
      			showalert = true;
      		}
      	 }else{
      		document.businessRuleForm.filterValue2.value="";
			if(variableCriteria == "" || variableCriteria == null || variableCriteria == "NOT_NULL"){
	   			document.businessRuleForm.filterValue1.value="";
	   			document.businessRuleForm.filterValue2.value="";
	   	   	}else{
	      	  if(isNaN(filterValue1)){
	   	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.filtervalue_is_numeric",user1.getLocale())%><BR>";
	   			showalert = true;
	   			}
	   	   	}
      	 }
   	}

	 if (showalert){
	     alert(alertstr);
	     return false;
	 }

	  document.businessRuleForm.action = "businessrule.do?method=updateApplicantFilter&fltid=<%=bForm.getBusinessCriteraId()%>";
	  document.businessRuleForm.submit();
}


function checkboxChecked(){
	
	alert(document.businessRuleForm.type.value.trim());
}
function load(){
/*	var selectedCriteria = document.businessRuleForm.variableCriteria.value.trim()
	if(selectedCriteria == 'NoValue'){
	document.getElementById("filter1").style.visibility = "hidden";	
	document.getElementById("filter2").style.visibility = "hidden";	
	}else if(selectedCriteria == 'BETWEEN'){
		document.getElementById("filter1").style.visibility = "visible";	
		document.getElementById("filter2").style.visibility = "visible";
	}else if(selectedCriteria == 'NOT_NULL'){
		document.getElementById("filter1").style.visibility = "hidden";
		document.getElementById("filter2").style.visibility = "hidden";
	}else{
		document.getElementById("filter1").style.visibility = "visible";
		document.getElementById("filter2").style.visibility = "hidden";
	}

*/
	var selectedCriteria = document.businessRuleForm.variableCriteria.value.trim()
	var varName = document.businessRuleForm.variableName.value.trim()
	var vartype = document.businessRuleForm.variableType.value.trim()
	
	if(selectedCriteria == 'NoValue'){
		document.getElementById("filter1").style.visibility = "hidden";	
		document.getElementById("filter2").style.visibility = "hidden";	
		document.getElementById("filter3").style.visibility = "hidden";	
		document.getElementById("filter4").style.visibility = "hidden";
		document.getElementById("filter5").style.visibility = "hidden";	
		document.getElementById("filter6").style.visibility = "hidden";	
		document.getElementById("filter7").style.visibility = "hidden";
		document.getElementById("filter8").style.visibility = "hidden";
		document.getElementById("filter9").style.visibility = "hidden";
		document.getElementById("filter10").style.visibility = "hidden";
		document.getElementById("filter11").style.visibility = "hidden";
		document.getElementById("filter12").style.visibility = "hidden";
		document.getElementById("filter13").style.visibility = "hidden";	
		document.getElementById("filter14").style.visibility = "hidden";	
	}
	
	if(vartype == 'text' || vartype == 'numeric'){
		if(selectedCriteria == 'BETWEEN'){
			document.getElementById("filter1").style.visibility = "visible";	
			document.getElementById("filter2").style.visibility = "visible";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}else if(selectedCriteria == 'NoValue'){
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}else if(selectedCriteria == 'NOT_EMPTY'){
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}else{
			document.getElementById("filter1").style.visibility = "visible";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}
	}
	if(vartype == 'date'){
		if(selectedCriteria == 'BETWEEN'){
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
				document.getElementById("filter3").style.visibility = "visible";	
				document.getElementById("filter4").style.visibility = "visible";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";
		}else if(selectedCriteria == 'NoValue'){
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}else if(selectedCriteria == 'NOT_EMPTY'){
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}else{
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
				document.getElementById("filter3").style.visibility = "visible";
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}	
	}
	if(vartype == 'list'){
		if(varName == 'SOURCE_SUBSOURCE'){
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
				document.getElementById("filter5").style.visibility = "visible";
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
				

		}
		if(varName == 'COUNTRY'){
				document.getElementById("filter6").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}
		if(varName == 'STATE'){
			document.getElementById("filter7").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}
		if(varName == 'WORK_ON_WEEKENDS'){
			document.getElementById("filter8").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}
		if(varName == 'WORK_ON_EVENINGS'){
			document.getElementById("filter9").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}
		if(varName == 'WORK_ON_OVERTIME'){
			document.getElementById("filter10").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}
		if(varName == 'WANT_TO_RELOCATE'){
			document.getElementById("filter11").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}
		if(varName == 'PRIMARY_SKILL'){
			document.getElementById("filter12").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}
		if(varName == 'FELONY_CONVICTION'){
			document.getElementById("filter13").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";	
			document.getElementById("filter14").style.visibility = "hidden";	
		}
		if(varName == 'GENDER'){
			document.getElementById("filter14").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";	
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";	
			document.getElementById("filter6").style.visibility = "hidden";	
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";		
		}
	}
	
}
function showFilterTextBox(selectedCriteria,varName,vartype){
	/*if(selectedCriteria == 'NoValue'){
		document.businessRuleForm.filterValue1.value="";
		document.businessRuleForm.filterValue2.value="";
	}
	if(selectedCriteria == 'BETWEEN'){
		document.getElementById("filter1").style.visibility = "visible";	
		document.getElementById("filter2").style.visibility = "visible";
	}else if(selectedCriteria == 'NoValue'){
		document.getElementById("filter1").style.visibility = "hidden";
		document.getElementById("filter2").style.visibility = "hidden";
	}else if(selectedCriteria == 'NOT_EMPTY'){
		document.getElementById("filter1").style.visibility = "hidden";
		document.getElementById("filter2").style.visibility = "hidden";
	}else{
		document.getElementById("filter1").style.visibility = "visible";
		document.getElementById("filter2").style.visibility = "hidden";
	}*/

	if(selectedCriteria == 'NoValue'){
		document.businessRuleForm.filterValue1.value="";
		document.businessRuleForm.filterValue2.value="";
	}

	if(vartype == 'text' || vartype == 'numeric'){
		if(selectedCriteria == 'BETWEEN'){
			document.getElementById("filter1").style.visibility = "visible";	
			document.getElementById("filter2").style.visibility = "visible";
		}else if(selectedCriteria == 'NoValue'){
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
		}else if(selectedCriteria == 'NOT_EMPTY'){
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
		}else{
			document.getElementById("filter1").style.visibility = "visible";
			document.getElementById("filter2").style.visibility = "hidden";
		}
	}	
	if(vartype == 'date'){
		if(selectedCriteria == 'BETWEEN'){
			document.getElementById("filter3").style.visibility = "visible";	
			document.getElementById("filter4").style.visibility = "visible";
		}else if(selectedCriteria == 'NoValue'){
			document.getElementById("filter3").style.visibility = "hidden";
			document.getElementById("filter4").style.visibility = "hidden";
		}else if(selectedCriteria == 'NOT_EMPTY'){
			document.getElementById("filter3").style.visibility = "hidden";
			document.getElementById("filter4").style.visibility = "hidden";
		}else{
			document.getElementById("filter3").style.visibility = "visible";
			document.getElementById("filter4").style.visibility = "hidden";
		}	
	}
	if(vartype == 'list'){
		if(varName == 'SOURCE_SUBSOURCE'){
			document.getElementById("filter5").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}
		if(varName == 'COUNTRY'){
			document.getElementById("filter6").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}
		if(varName == 'STATE'){
			document.getElementById("filter7").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}
		if(varName == 'WORK_ON_WEEKENDS'){
			document.getElementById("filter8").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}
		if(varName == 'WORK_ON_EVENINGS'){
			document.getElementById("filter9").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}
		if(varName == 'WORK_ON_OVERTIME'){
			document.getElementById("filter10").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}
		if(varName == 'WANT_TO_RELOCATE'){
			document.getElementById("filter11").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}
		if(varName == 'PRIMARY_SKILL'){
			document.getElementById("filter12").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}
		if(varName == 'FELONY_CONVICTION'){
			document.getElementById("filter13").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}
		if(varName == 'GENDER'){
			document.getElementById("filter14").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}
	}
}


function retrieveCriterias() {
	document.businessRuleForm.variableCriteria.value = "NoValue";
			document.getElementById("filter1").style.visibility = "hidden";
			document.getElementById("filter2").style.visibility = "hidden";
			document.getElementById("filter3").style.visibility = "hidden";
			document.getElementById("filter4").style.visibility = "hidden";
			document.getElementById("filter5").style.visibility = "hidden";
			document.getElementById("filter6").style.visibility = "hidden";
			document.getElementById("filter7").style.visibility = "hidden";
			document.getElementById("filter8").style.visibility = "hidden";
			document.getElementById("filter9").style.visibility = "hidden";
			document.getElementById("filter10").style.visibility = "hidden";
			document.getElementById("filter11").style.visibility = "hidden";
			document.getElementById("filter12").style.visibility = "hidden";
			document.getElementById("filter13").style.visibility = "hidden";
			document.getElementById("filter14").style.visibility = "hidden";
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";


	var urlnew="businessrule.do?method=loadCriterias"+"&variablename="+document.businessRuleForm.variableName.value.trim();;

	//alert(urlnew);
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChangeResumeSource;

	    try {
    		req.open("GET", urlnew, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
			alert(urlnew);
	    	req.onreadystatechange=processStateChangeResumeSource;
	        req.open("GET", urlnew, true);
		    req.send();
			
    	}
  	}
}

function processStateChangeResumeSource() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmlResumeSource(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtmlResumeSource(newTextElements){
	//loop through newTextElements
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="criteralist")
   					document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function splitTextIntoSpan(textToSplit){
	//Split the document
  	returnElements=textToSplit.split("</span>")
        
  	//Process each of the elements        
  	for(var i=returnElements.length-1;i>=0;--i){  //Remove everything before the 1st span
	    spanPos = returnElements[i].indexOf("<span");               
                
    	//if we find a match, take out everything before the span
    	if(spanPos>0){
        	  subString=returnElements[i].substring(spanPos);
	          returnElements[i]=subString;
    	} 
  	}
  	return returnElements;
}

function opensearchfilter(bn){
	//alert(boxnumber);
	var t = "businessrule.do?method=filterselector&boxnumber="+bn;
	var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(t);
  
  window.open(url,  "SearchFilter","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

</script>
<style>
span1{color:#ff0000;}
</style>
<%
String deleteappfilter=(String)request.getAttribute("deleteappfilter");
%>



<body class="yui-skin-sam" onload="load()">

<html:form action="/businessrule.do?method=savesbusinessrule">

<% if(! StringUtils.isNullOrEmpty(savebusinessrule) && savebusinessrule.equals("yes")) {%>
<div class="msg">
	<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.applicant.filter.savemesssage",user1.getLocale())%></font>
</div>
<%} %>
<% if(! StringUtils.isNullOrEmpty(updatebusinessrule) && updatebusinessrule.equals("yes")) {%>
<div class="msg">
	<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.applicant.filtergroup.updatedmesssage",user1.getLocale())%></font>
</div>
<%} %>
<% if(! StringUtils.isNullOrEmpty(deleteappfilter) && deleteappfilter.equals("yes")) {%>
<div class="msg">
	<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.applicant.filter.deletemesssage",user1.getLocale())%></font>
</div>

<%}else{ %>
<br><br>
<div class="div">
<table>
	<tr>
		<td></td>
		<td><%=Constant.getResourceStringValue("admin.filter.name",user1.getLocale())%><font color="red">*</font>&nbsp;</td>
		<td><html:text property="name" size="60" maxlength="600"/></td>
	</tr>
	<tr>
		<td></td>
		<td><%=Constant.getResourceStringValue("admin.businessRule.desc",user1.getLocale())%></td>
		<td><html:textarea property="desc" cols="57" rows="5"/></td>
	</tr>
	
</table>
<fieldset><legend><b><%=Constant.getResourceStringValue("admin.businessRule.filter.precondition",user1.getLocale())%></b></legend>
<table>
	<tr>
		
		<td>
		<%=Constant.getResourceStringValue("admin.screenfield.Field_Name",user1.getLocale())%> :
         </td>
		   <td>
            <html:select  property="variableName" onchange="retrieveCriterias();">
			<option value=""><%=Constant.getResourceStringValue("admin.Location.select",user1.getLocale())%></option>
	            
	            <optgroup label="<%=Constant.getResourceStringValue("admin.screenfield.screen.fields",user1.getLocale())%>"> 
				<bean:define name="businessRuleForm" property="screenfieldsList" id="screenfieldsList" />
	            <html:options collection="screenfieldsList" property="key"  labelProperty="value"/>
                </optgroup> 
				<optgroup label="<%=Constant.getResourceStringValue("admin.screenfield.custom.fields",user1.getLocale())%>">
                <bean:define name="businessRuleForm" property="customVariablesList" id="customVariablesList" />
	            <html:options collection="customVariablesList" property="key"  labelProperty="value"/>
                </optgroup> 
	            
			</html:select>

			<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
				<%=Constant.getResourceStringValue("admin.screenfield.Loading",user1.getLocale())%></span>
           </td>
           </tr>
		   <tr>
		   <td>
			<%=Constant.getResourceStringValue("admin.businessRule.criteria",user1.getLocale())%> :
		</td>
		   <td>
	<span id ="criteralist">
              <html:select  property="variableCriteria" onchange="showFilterTextBox(document.businessRuleForm.variableCriteria.value,document.businessRuleForm.variableName.value,document.businessRuleForm.variableType.value);">
				
				<bean:define name="businessRuleForm" property="filterCriteriasList" id="filterCriteriasList" />
	
	            <html:options collection="filterCriteriasList" property="key"  labelProperty="value"/>

			
			</html:select>
			<html:hidden property="variableType"/>	
		</span>
			
			
		</td>
	</tr>
	<tr>
	<td></td>
		<td><div id="filter1"><html:text property="filterValue1" size="25" maxlength="600"/></div>
		<div id="filter2"><html:text property="filterValue2" size="25" maxlength="600"/></div>
		<div id="filter4">
					<SCRIPT LANGUAGE="JavaScript" ID="jscal2xx">
					var cal2xx = new CalendarPopup("testdiv2");
					cal2xx.showNavigationDropdowns();
					
					</SCRIPT>
					<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
					
					<INPUT TYPE="text" NAME="filterdate2" readonly="true" value='<%=(bForm.getFilterdate2() == null ?"":bForm.getFilterdate2())%>' SIZE=25>
					<A HREF="#" onClick="cal2xx.select(document.businessRuleForm.filterdate2,'anchor2xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor2xx" ID="anchor2xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
					
					<div ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></div>
				</div>
		<div id="filter3">
					<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
					var cal1xx = new CalendarPopup("testdiv1");
					cal1xx.showNavigationDropdowns();
					
					</SCRIPT>
					<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->
					
					<INPUT TYPE="text" NAME="filterdate1" readonly="true"  value='<%=(bForm.getFilterdate1() == null ?"":bForm.getFilterdate1())%>' SIZE=25>
					<A HREF="#" onClick="cal1xx.select(document.businessRuleForm.filterdate1,'anchor1xx','<%=datepattern%>'); return false;" TITLE="<%=Constant.getResourceStringValue("aquisition.applicant.select.date",user1.getLocale())%>" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
					
					<div ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></div>
				</div>
				<div id="filter5">
					<html:select  property="sourceTypeId" >
					<option value=""></option>
					<bean:define name="businessRuleForm" property="sourceTypeList" id="sourceTypeList" />
		
		            <html:options collection="sourceTypeList" property="resumeSourceTypeId"  labelProperty="resumeSourceTypeName"/>
					</html:select>
					
				</div>
				<div id="filter6">
					<html:select  property="countryId">
					<option value=""></option>
					<bean:define name="businessRuleForm" property="countryList" id="countryList" />
		
		            <html:options collection="countryList" property="countryId"  labelProperty="countryName"/>
					</html:select>
					
				</div>
				<div id="filter7">
					<html:select  property="stateId">
					<option value=""></option>
					<bean:define name="businessRuleForm" property="stateList" id="stateList" />
		
		            <html:options collection="stateList" property="stateId"  labelProperty="stateName"/>
					</html:select>
				</div>
				<div id="filter8">
					<html:select  property="work_on_weekends" >
					<option value=""></option>
					<bean:define name="businessRuleForm" property="yesnofulllist" id="yesnofulllist" />
		
		            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
					</html:select>
				</div>
				<div id="filter9">
					<html:select  property="work_on_evenings" >
					<option value=""></option>
					<bean:define name="businessRuleForm" property="yesnofulllist" id="yesnofulllist" />
		
		            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
					</html:select>
				</div>
				<div id="filter10">
					<html:select  property="work_on_overtime" >
					<option value=""></option>
					<bean:define name="businessRuleForm" property="yesnofulllist" id="yesnofulllist" />
		
		            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
					</html:select>
				</div>
				<div id="filter11">
					<html:select  property="want_to_relocate" >
					<option value=""></option>
					<bean:define name="businessRuleForm" property="yesnofulllist" id="yesnofulllist" />
		
		            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
					</html:select>
				</div>
				<div id="filter12">
					<html:select  property="primarySkill" >
					<bean:define name="businessRuleForm" property="lovList" id="lovList" />
		            <html:options collection="lovList" property="technialSkillName"  labelProperty="technialSkillName"/>
					</html:select>
				</div>
				<div id="filter13">
					<html:select  property="felony_conviction" >
					<option value=""></option>
					<bean:define name="businessRuleForm" property="yesnofulllist" id="yesnofulllist" />
		
		            <html:options collection="yesnofulllist" property="key"  labelProperty="value"/>
					</html:select>
				</div>
				<div id="filter14">
					<html:select  property="gender" >
					
					<bean:define name="businessRuleForm" property="genderlist" id="genderlist" />
		
		            <html:options collection="genderlist" property="key"  labelProperty="value"/>
					</html:select>
				</div>
		
	
	
		
		</td>


</tr>
</table>
</fieldset>
<br>
<fieldset><legend><b><%=Constant.getResourceStringValue("admin.businessRule.filters.criterias",user1.getLocale())%></b></legend>
<%
List filtersList = bForm.getFiltersList();
%>
<table>
	<tr>
		
		<td>
		<input type="hidden" name="idlistvalflt" value=""/>
		<input type="hidden" value="<%=filtersList.size()%>" id="theValue2" />
<p><a href="javascript:;" onclick="addElement2();return false"><img src="jsp/images/add.gif" border="0" alt="add filter criteria" title="<%=Constant.getResourceStringValue("admin.businessRule.add.filter",user1.getLocale())%>" height="20"  width="19"/></a></p>
		</td>
		<td>

		</td>
	</tr>
    <tr>
	<td>
	<div id="myDiv2"> 

<%
int k=1;
for(int i=0;i<filtersList.size();i++){
	BusinessFilterConditions japp = (BusinessFilterConditions)filtersList.get(i);
     String tdiv = "my2"+k+"Div";
	 String tspan = "approverspan_"+k;
	 String tfiltername = "filtername_"+k;
	 String tuserid ="filterid_"+k;
	 String apptx = " Approved";
	 String leveltext1 = "Filter-"+k+"&nbsp;&nbsp";
	 String filterlink = "";

		filterlink="	<img src=\"jsp/images/filter-icon.gif\" width='19' height='19'>"+
		"<a href=\"#\" onClick=\"window.open('businessrule.do?method=viewFilter&filterid="+japp.getFilterConditionId()+"', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=400,height=300')\">"+japp.getName()+"</a>";
	
	String tempdivapp = "<div id='"+tdiv+"'"+">"+
		"<span class='blue' id='"+tspan+"'"+">"+ leveltext1 +
		"<input type=\"hidden\" value='"+japp.getName()+"' name='"+tfiltername+"'"+"/>"+" "+filterlink + "  "+
       "<input type=\"hidden\"   value="+japp.getFilterConditionId()+" name='"+tuserid+"'"+"/>"+
	   	"</span>"+
		"<a href=\'#\' onclick=\'opensearchfilter("+k+")\'><img src=\"jsp/images/selector.gif\" border=\"0\"/></a> ";


String tempdivappdel =	" <a href=\'#\' onclick=\'removeElement2("+tdiv+","+k+");return false\'><img src=\"jsp/images/delete.gif\" border=\"0\" width=\"15em\" hieght=\"15em\"/></a>";

String tempdivapp1 =  "</div>";

	tempdivapp = tempdivapp + tempdivappdel + tempdivapp1;


	k++;
%>

<%=tempdivapp%>

<%}%>


</div>
	</td>
	<td>
	</td>
	</tr>

</table>

</fieldset>
<br>
<table width="100%">
	<tr>
		<td>
      <% if(bForm.getBusinessCriteraId()>0){%>
         <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
		 <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deleteApplicantFilter()" class="button">
        <%}else{%>
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">
		<%}%>
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
		</td>
	</tr>
</table>
<%} %>
</html:form>
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
  var username1 = '<input type="hidden"  value="" name="filtername_'+num+'"/>';
  var userid1 = '<input type="hidden"    name="filterid_'+num+'"/>';
   var levelid = '<input type="hidden"    name="level_'+num+'"/>';
    var leveltext = 'Filter-'+num+'&nbsp;&nbsp';
    var spanend = '</span>'; 
  newdiv.innerHTML = ''+spanstart+''+leveltext+''+levelid+''+username1+''+userid1+ ''+spanend+''+'<a href=\'#\' onclick=\'opensearchfilter('+num+')\'><img src=\"jsp/images/selector.gif\" border=\"0\"/></a>'+''+'  <a href=\'#\' onclick=\'removeElement2('+divIdName+','+num+');return false\'><img src=\"jsp/images/delete.gif\" border=\"0\" width=\"15em\" hieght=\"15em\" /></a>';
  ni.appendChild(newdiv);
  } else {
	  alert("maximum level exceed");
  }
}




function removeElement2(divNum,num) {
	var approvenum = 'filterid_'+num;
	var idapprove=document.businessRuleForm[approvenum].value;
	var listidapprover=document.businessRuleForm.idlistvalflt.value;
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
document.businessRuleForm.idlistvalflt.value=array1new;
//alert(array1new);
}

</script>