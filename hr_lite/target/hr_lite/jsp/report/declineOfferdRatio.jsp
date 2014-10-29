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
	 var allorgids ="";
    var orgids ="";
    var deptids="";
    var years ="";
    var hiringmgrids = $('input[name="users"]').val();
    var selectorgcount=0;
    var selectedorgcount=0;
    var selectyearcount=0;
    var selectedyearcount=0

	  for (var i = 0; i < document.reportForm.orgIds.options.length; i++){
		  allorgids = allorgids+ document.reportForm.orgIds.options[ i ].value + ",";
		    if (document.reportForm.orgIds.options[ i ].selected){
		    	selectorgcount++;
			}
		  }
    
	  for (var i = 0; i < document.reportForm.orgIds.options.length; i++){
	    if (document.reportForm.orgIds.options[ i ].selected){
	    	selectedorgcount++;
	    	if(selectorgcount != selectedorgcount){
	    		orgids = orgids + document.reportForm.orgIds.options[ i ].value + ",";
	    	}else{
	    		orgids = orgids + document.reportForm.orgIds.options[ i ].value; 
		    }
		}
	  }

    var selectdeptcount=0;
    var selectedeptcount=0
    if(typeof document.reportForm.departmentIds != 'undefined'){
		  for (var i = 0; i < document.reportForm.departmentIds.options.length; i++){
			    if (document.reportForm.departmentIds.options[ i ].selected){
			    	selectdeptcount++;
				}
		  }
		  
		  for (var i = 0; i < document.reportForm.departmentIds.options.length; i++){
			    if (document.reportForm.departmentIds.options[ i ].selected){
			    	selectedeptcount++;
			    	if(selectdeptcount != selectedeptcount){
			    		deptids = deptids + document.reportForm.departmentIds.options[ i ].value + ",";
					}else{
						deptids = deptids + document.reportForm.departmentIds.options[ i ].value ;
					}
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

	  
	if(orgids == null || orgids == ""){
		//orgids = allorgids;
	}

		  
	//alert(orgids +" >> "+deptids +" >> "+hiringmgrids+" >> "+years);
	var tu = "/jsp/report/declined_offer.jsp?reportfilename=Decline_offer_new.jasper&orgids="+orgids+"&deptids="+deptids+"&hiringMgrIds="+hiringmgrids+"&year="+years;
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
<h3><%=Constant.getResourceStringValue("home.Reports.Declined_offer_Ratio",user2.getLocale())%></h3>
<br>
<table border="0" width="100%" class="div">

		



		<tr>
			<td width="21%"><%=Constant.getResourceStringValue("admin.organization.organization",user2.getLocale())%></td>
			<td>

				<html:select property="orgIds" styleClass="multilistsmall" multiple="true" size="3" onchange="retrieveURLOrg('reports.do?method=deptlistmultiple');">
				<bean:define name="reportForm" property="organizationList" id="organizationList" />
           	 	<html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
			</html:select>	
			
					&nbsp;
					<span class="textboxlabel" id="loadingd" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept.only",user2.getLocale())%> ....</span>
		

			
			</td>
		</tr>
		<tr>
			<td ><%=Constant.getResourceStringValue("admin.Deparment.depts",user2.getLocale())%></td>

			<td>
                <span id="departments">
				<html:select property="departmentIds" styleClass="multilistsmall" multiple="true" size="3">
				<bean:define name="reportForm" property="departmentList" id="departmentList" />
	
	            <html:options collection="departmentList" property="departmentId"  labelProperty="departmentName"/>
				</html:select>
	            </span>
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
			<% 
							 
				String data="";
				if(aform.getUsersset() != null && aform.getUsersset().size()>0){
	             Iterator itr = aform.getUsersset().iterator();
	
						while(itr.hasNext()){
							User touser = (User)itr.next();
										
							 data = data + "{ "+"\""+"id"+"\""+":"+"\""+touser.getUserId()+"\","+"  "+"\""+"name"+"\""+":"+"\""+touser.getFirstName()+ " "+touser.getLastName()+"\""+"}"+",";
						}
			
					if(!StringUtils.isNullOrEmpty(data)){
						data = data.substring(0,data.length()-1);
					}
				}
				
			%>
		<tr>

			<td ><%=Constant.getResourceStringValue("Requisition.hiringmgr",user2.getLocale())%></td>
			<td>
	     
		        <input type="text" id="demo-input-pre-populated" name="users" />
				<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user2.getLocale())%></span>
		        <script type="text/javascript">
		        $(document).ready(function() {
		            $("#demo-input-pre-populated").tokenInput("jsp/talent/getUserDataJsonMuti.jsp", {
						 preventDuplicates: true,
					     hintText: "Type in a search user",
		                prePopulate: [
								<%=data%>
		                ]
		            });
		        });
		        </script>

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