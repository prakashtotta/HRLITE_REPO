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

String savebusinessrule =(String)request.getAttribute("savebusinessrule");
String updatebusinessrule=(String)request.getAttribute("updatebusinessrule");
String deletefilter=(String)request.getAttribute("deletefilter");

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

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){

	 parent.parent.GB_hide();
	 
	   } 
	}

function closewindow(){
	parent.parent.GB_hide();

}
function savedata(){

	var alertstr = "";
	var showalert=false;

	var name = document.businessRuleForm.name.value.trim();
	var variableCriteria = document.businessRuleForm.variableCriteria.value.trim();
	var variableName = document.businessRuleForm.variableName.value.trim();
	var vartype = document.businessRuleForm.variableType.value.trim();
	var filterValue1= document.businessRuleForm.filterValue1.value.trim();
	var filterValue2= document.businessRuleForm.filterValue2.value.trim();
	//alert(vartype);

	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

   if(variableName == "" || variableName == null ){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Fieldname_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

   
    if(variableCriteria == "" || variableCriteria == null || variableCriteria == "NoValue"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Criteria_is_mandatory",user1.getLocale())%><BR>";
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

	  document.businessRuleForm.action = "businessrule.do?method=saveFilter";
	  document.businessRuleForm.submit();
}


function updatedata(){

	var alertstr = "";
	var showalert=false;

	var name = document.businessRuleForm.name.value.trim();
	var variableCriteria = document.businessRuleForm.variableCriteria.value.trim();
	var variableName = document.businessRuleForm.variableName.value.trim();
	var vartype = document.businessRuleForm.variableType.value.trim();
	var filterValue1= document.businessRuleForm.filterValue1.value.trim();
	var filterValue2= document.businessRuleForm.filterValue2.value.trim();

	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

   if(variableName == "" || variableName == null ){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Fieldname_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
		}

    if(variableCriteria == "" || variableCriteria == null || variableCriteria == "NoValue"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Criteria_is_mandatory",user1.getLocale())%><BR>";
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

	  document.businessRuleForm.action = "businessrule.do?method=updateFilter&fltid=<%=bForm.getBusinessCriteraId()%>";
	  document.businessRuleForm.submit();
}
function deletefilter(){
	var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	  document.businessRuleForm.action = "businessrule.do?method=deleteFilter&fltid=<%=bForm.getBusinessCriteraId()%>";
	  document.businessRuleForm.submit();
	  }

}

function checkboxChecked(){
	
	alert(document.businessRuleForm.type.value.trim());
}
function load(){
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
		document.getElementById("filter15").style.visibility = "hidden";
			
	}
	
	if(vartype == 'text' || vartype == 'numeric' || vartype == 'textarea'){
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
			document.getElementById("filter15").style.visibility = "hidden";
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
			document.getElementById("filter15").style.visibility = "hidden";
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
			document.getElementById("filter15").style.visibility = "hidden";
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
			document.getElementById("filter15").style.visibility = "hidden";
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
			document.getElementById("filter15").style.visibility = "hidden";
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
			document.getElementById("filter15").style.visibility = "hidden";
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
			document.getElementById("filter15").style.visibility = "hidden";
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
			document.getElementById("filter15").style.visibility = "hidden";
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
			document.getElementById("filter15").style.visibility = "hidden";	
				

		}else if(varName == 'COUNTRY'){
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
			document.getElementById("filter15").style.visibility = "hidden";
		}else if(varName == 'STATE'){
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
			document.getElementById("filter15").style.visibility = "hidden";
		}else if(varName == 'WORK_ON_WEEKENDS'){
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
			document.getElementById("filter15").style.visibility = "hidden";
		}else if(varName == 'WORK_ON_EVENINGS'){
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
			document.getElementById("filter15").style.visibility = "hidden";
		}else if(varName == 'WORK_ON_OVERTIME'){
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
			document.getElementById("filter15").style.visibility = "hidden";
		}else if(varName == 'WANT_TO_RELOCATE'){
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
			document.getElementById("filter15").style.visibility = "hidden";
		}else if(varName == 'PRIMARY_SKILL'){
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
			document.getElementById("filter15").style.visibility = "hidden";
		}else if(varName == 'FELONY_CONVICTION'){
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
			document.getElementById("filter15").style.visibility = "hidden";
		}else if(varName == 'GENDER'){
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
			document.getElementById("filter15").style.visibility = "hidden";	
		}else{
			document.getElementById("filter15").style.visibility = "visible";
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
	}

}
function showFilterTextBox(selectedCriteria,varName,vartype){
	//alert("");
	if(selectedCriteria == 'NoValue'){
		document.businessRuleForm.filterValue1.value="";
		document.businessRuleForm.filterValue2.value="";
	}

	if(vartype == 'text' || vartype == 'numeric' || vartype == 'textarea'){
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
		}else if(varName == 'COUNTRY'){
			document.getElementById("filter6").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}else if(varName == 'STATE'){
			document.getElementById("filter7").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}else if(varName == 'WORK_ON_WEEKENDS'){
			document.getElementById("filter8").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}else if(varName == 'WORK_ON_EVENINGS'){
			document.getElementById("filter9").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}else if(varName == 'WORK_ON_OVERTIME'){
			document.getElementById("filter10").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}else if(varName == 'WANT_TO_RELOCATE'){
			document.getElementById("filter11").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}else if(varName == 'PRIMARY_SKILL'){
			document.getElementById("filter12").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}else if(varName == 'FELONY_CONVICTION'){
			document.getElementById("filter13").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}else if(varName == 'GENDER'){
			document.getElementById("filter14").style.visibility = "visible";
			document.getElementById("filter1").style.visibility = "hidden";	
			document.getElementById("filter2").style.visibility = "hidden";	
		}else{
			document.getElementById("filter15").style.visibility = "visible";
			var urlnew="businessrule.do?method=getVariablelistdata&variablecode="+varName;

			//alert(urlnew);
			
		  	//Do the AJAX call
		  	if (window.XMLHttpRequest) { 
			    req = new XMLHttpRequest();    	// Non-IE browsers
		    	req.onreadystatechange = processStateChangelist;

			    try {
		    		req.open("GET", urlnew, true);
					
			    } catch (e) {}
			    req.send(null);
		  	} else if (window.ActiveXObject) {
		       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
		    	if (req) {
					alert(urlnew);
			    	req.onreadystatechange=processStateChangelist;
			        req.open("GET", urlnew, true);
				    req.send();
					
		    	}
		  	}
		//alert(varName);
		}
	}
}


function retrieveCriterias() {
   //convert the url to a string
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
		document.getElementById("filter15").style.visibility = "hidden";
		

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
function processStateChangelist() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtmllist(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading").style.visibility = "hidden";	
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
function replaceExistingWithNewHtmllist(newTextElements){
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
				  
		    	  if(name="variablelistdata")
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
</script>
<style>
span1{color:#ff0000;}
</style>
<body class="yui-skin-sam" onload="load()">

<html:form action="/businessrule.do?method=editFilter">
&nbsp;<font color = red ><html:errors /> </font>
<br>
<% if(! StringUtils.isNullOrEmpty(savebusinessrule) && savebusinessrule.equals("yes")) {%>
<div class="msg">
	<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.filter.savemesssage",user1.getLocale())%></font>
</div>
<%} %>
<% if(! StringUtils.isNullOrEmpty(updatebusinessrule) && updatebusinessrule.equals("yes")) {%>
<div class="msg">
	<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.filter.updatemesssage",user1.getLocale())%></font>
</div>
<%} %>
<% if(! StringUtils.isNullOrEmpty(deletefilter) && deletefilter.equals("yes")) {%>
<div class="msg">
	<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.filter.deletemesssage",user1.getLocale())%></font>
<!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> -->
</div>
<%}else{ %>
<div class="div">
<table>
	<tr>

		<td><%=Constant.getResourceStringValue("admin.businessRule.name",user1.getLocale())%><font color="red">*</font>&nbsp;</td><td></td>
		<td>&nbsp;&nbsp;<html:text property="name" size="50" maxlength="600"/></td>
	</tr>
	<tr>
	
		<td><%=Constant.getResourceStringValue("admin.businessRule.desc",user1.getLocale())%> </td><td></td>
		<td>&nbsp;&nbsp;<html:textarea property="desc" cols="47" rows="5"/></td>
	</tr>
	</table>
<table border="0">
	<tr>
		
		<td>
		<%=Constant.getResourceStringValue("admin.screenfield.Field_Name",user1.getLocale())%><font color="red">*</font>&nbsp;
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
		   <td><%=Constant.getResourceStringValue("admin.businessRule.criteria",user1.getLocale())%>  <font color="red">*</font> &nbsp;</td>
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
	<tr>	<td></td>
			<td>
			<span id="details_data">
				<div id="filter1"><html:text property="filterValue1" size="25" maxlength="600"/></div>
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
				<div id="filter15">
				<span id ="variablelistdata">
					<html:select  property="variableListDataId">
					<option value=""></option>
					<bean:define name="businessRuleForm" property="varibalesListdata" id="varibalesListdata" />
		
		            <html:options collection="varibalesListdata" property="variableListDataId"  labelProperty="variableValue"/>
					</html:select>
				</span>
				</div>
			
				</span>
			</td>
			<td>
		
				
			</td>


	</tr>
</table>
<br><br>
<table width="100%">
	<tr>
		<td >
      <% if(bForm.getBusinessCriteraId()>0){%>
         <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
       	 <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletefilter()" class="button">
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