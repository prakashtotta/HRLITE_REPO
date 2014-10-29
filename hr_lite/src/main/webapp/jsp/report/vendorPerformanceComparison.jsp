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
    var userIds ="";
    var alluserIds ="";
    var years ="";

    var selectcount=0;
    var selectedcount=0
    var selectyearcount=0;
    var selectedyearcount=0
	  for (var i = 0; i < document.reportForm.userIds.options.length; i++){
		  alluserIds=alluserIds + document.reportForm.userIds.options[ i ].value + ",";
		    if (document.reportForm.userIds.options[ i ].selected ){
		    	selectcount++;
		    	
			}
		  }
	  
	  for (var i = 0; i < document.reportForm.userIds.options.length; i++){
	    if (document.reportForm.userIds.options[ i ].selected ){
	    	selectedcount++;
	    	if(selectedcount != selectcount){
	    	userIds = userIds + document.reportForm.userIds.options[ i ].value + ",";
	    	}else{
	    		userIds = userIds + document.reportForm.userIds.options[ i ].value;
	    	}
		}
	  }

	  for (var i = 0; i < document.reportForm.year.options.length; i++){
		 
			 if (document.reportForm.year.options[ i ].selected){
				 selectyearcount++;
			}
		  }
	  for (var i = 0; i < document.reportForm.year.options.length; i++){
		 if (document.reportForm.year.options[ i ].selected){
			 selectedyearcount++;;
			 if(selectedyearcount != selectyearcount){
				 years = years + document.reportForm.year.options[ i ].value + ",";
			   }else{
			    	 years = years + document.reportForm.year.options[ i ].value ;
			  }
			
		}
	  }
	if(userIds == null || userIds == ""){
		userIds=alluserIds;
	}  


	var tu = "/jsp/report/vendor_performance_comparison.jsp?reportfilename=VendorWiseApplicant.jasper&vendorIds="+userIds+"&year="+years;
	   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(tu);
	   window.open(url,  "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700");
	   
	}



	

</script>
</head>

<html:form action="/reports.do?method=logon" >
<br>
<span id="reportdata">
<h3><%=Constant.getResourceStringValue("home.Reports.Vendor_Performance_Comparison",user2.getLocale())%></h3>
<br>
<table border="0" width="100%" class="div">

		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Vendor",user2.getLocale())%></td>
			<td>
				<html:select property="userIds" styleClass="multilistsmall" multiple="true" size="3">
				<bean:define name="reportForm" property="vendorList" id="vendorList" />
	            <html:options collection="vendorList" property="userId"  labelProperty="firstName"/>
				</html:select>			
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("hr.year",user2.getLocale())%></td>
			<td >
	
			<html:select  property="year" styleClass="multilistsmall" multiple="true" size="3">
			<bean:define name="reportForm" property="yearList" id="yearList" />
            	<html:options collection="yearList" property="key"  labelProperty="value"/>
			</html:select>

			</td>

		</tr>

	

		<tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr>
		<tr>
			<td width="200"><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.run",user2.getLocale())%>" onClick="runreport()" class="button"></td>

		</tr>
			

</table>
</span>
</html:form>
</html>