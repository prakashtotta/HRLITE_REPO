<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.lov.*"%>
<%@ page import="com.bean.dto.*"%>
<%@ page import="com.bean.system.*"%>
<%@ page import="com.bean.testengine.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="com.bean.filter.*"%>
<%@ page import="com.form.*"%>
<%@ page import="com.performance.bean.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.text.*"%>
<%@ include file="../common/autocompleteMultiple.jsp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
	<%
User user2 = (User)request.getSession().getAttribute(Common.USER_DATA);


%>
<bean:define id="aform" name="reportForm" type="com.form.ReportForm" />
<html>
<head>
	<STYLE type="text/css">

	

.button {
   border-top: 1px solid #96d1f8;
   background: #3B5999;
   padding: 5px 10px;
   border-radius: 8px;
   text-shadow: rgba(0,0,0,.4) 0 1px 0;
   color: #ccc;
   font-size: 14px;
   font-family: Georgia, serif;
   text-decoration: none;
   vertical-align: middle;
   }
.button1:hover {
   border-top-color: #3B5998;
   background: #3B5998;
   color: white;
   }

.div
{
font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	color: #000000;
	padding: 5px 22px;
	background: -moz-linear-gradient(
		top,
		#ffffff 0%,
		#FFFFFF);
	background: -webkit-gradient(
		linear, left top, left bottom, 
		from(#ffffff),
		to(#FFFFFF));
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
	border-radius: 4px;
	border: 1px solid #585858;

		}

</style>

<script language="javascript">


function runreport(){


    var budgetids="";
    var allbudgetids="";

    var selectbudgetcount=0;
    var selectedbudgetcount=0;

	  for (var i = 0; i < document.reportForm.budgetIds.options.length; i++){
		  allbudgetids = allbudgetids + document.reportForm.budgetIds.options[ i ].value + ",";
			 if (document.reportForm.budgetIds.options[ i ].selected){
				 selectbudgetcount++;
			}
		  }
	  for (var i = 0; i < document.reportForm.budgetIds.options.length; i++){
		 if (document.reportForm.budgetIds.options[ i ].selected){
			 selectedbudgetcount++;;
			 if(selectedbudgetcount != selectbudgetcount){
				 budgetids = budgetids + document.reportForm.budgetIds.options[ i ].value + ",";
			   }else{
				   budgetids = budgetids + document.reportForm.budgetIds.options[ i ].value ;
			  }
			
		}
	  }

	if(budgetids == null || budgetids == ""){
		budgetids=allbudgetids;
	} 

	var tu = "/jsp/report/budget_tracking.jsp?reportfilename=Budget tracking with No.OF Possition.jasper&budgetIds="+budgetids;
	   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
	   window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700");
	   
	}



	
function retrieveURLOrg(urlvar) {
	   //convert the url to a string
	    document.getElementById("loadingd").style.visibility = "visible";

		
		//url=url+"&parentorgId="+document.reportForm.orgIds.value;

	    var ids ="";
	    
		  for (var i = 0; i < document.reportForm.orgIds.options.length; i++){
		    if (document.reportForm.orgIds.options[ i ].selected){
				ids = ids + document.reportForm.orgIds.options[ i ].value + ",";
			}
		  }

		
		  urlvar=urlvar+"&orgId="+ids;

        
		$.ajax({
			type: 'GET',
		  url: urlvar,
		  success: function(data){
	
		  $('#departments').html(data);

		  }
		});
		document.getElementById("loadingd").style.visibility = "hidden";		


	}
</script>
</head>

<html:form action="/reports.do?method=logon" >
<br>
<span id="reportdata">
<h3><%=Constant.getResourceStringValue("home.Reports.Budget_tracking",user2.getLocale())%></h3>
<br>
<table border="0" width="100%" class="div">

		<tr>
			<td width="21%"><%=Constant.getResourceStringValue("admin.BudgetCode.BudgetCode",user2.getLocale())%></td>

			<td >

				<html:select property="budgetIds" styleClass="multilistsmall" multiple="true" size="3">
				<bean:define name="reportForm" property="budgetCodeList" id="budgetCodeList" />
	
	            <html:options collection="budgetCodeList" property="budgetId"  labelProperty="budgetCode"/>
				</html:select>
	
			</td>

		</tr>




		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
		<tr>
			<td><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.run",user2.getLocale())%>" onClick="runreport()" class="button"></td>
			<td></td>
		</tr>
			

</table>
</span>
</html:form>
</html>