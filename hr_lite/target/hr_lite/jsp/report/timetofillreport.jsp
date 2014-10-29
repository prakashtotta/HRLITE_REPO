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
	var requitionIds ="";
	var jobgradeids ="";
	var hiringmgrids = $('input[name="users"]').val();
	var recruiterids = $('input[name="users2"]').val();
	var selectorgcount=0;
	var selectedorgcount=0;
	var selectdeptcount=0;
	var selectedeptcount=0;
	var selectreqcount=0;
	var selectedreqcount=0;
	var selectyearcount=0;
	var selectedyearcount=0;
	var selectjobgradeidscount=0;
	var selectedjobgradeidscount=0;


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
	  for (var i = 0; i < document.reportForm.requitionIds.options.length; i++){
		    if (document.reportForm.requitionIds.options[ i ].selected){
		    	selectreqcount++;
			}
	  }
	  
	  for (var i = 0; i < document.reportForm.requitionIds.options.length; i++){
		    if (document.reportForm.requitionIds.options[ i ].selected){
		    	selectedreqcount++;
		    	if(selectreqcount != selectedreqcount){
		    		requitionIds = requitionIds + document.reportForm.requitionIds.options[ i ].value + ",";
				}else{
					requitionIds = requitionIds + document.reportForm.requitionIds.options[ i ].value ;
				}
	  		}		
	  }

	  for (var i = 0; i < document.reportForm.jobgradeIds.options.length; i++){
		    if (document.reportForm.jobgradeIds.options[ i ].selected){
		    	selectjobgradeidscount++;
			}
	  }
	  
	  for (var i = 0; i < document.reportForm.jobgradeIds.options.length; i++){
		    if (document.reportForm.jobgradeIds.options[ i ].selected){
		    	selectedjobgradeidscount++;
		    	if(selectjobgradeidscount != selectedjobgradeidscount){
		    		jobgradeids = jobgradeids + document.reportForm.jobgradeIds.options[ i ].value + ",";
				}else{
					jobgradeids = jobgradeids + document.reportForm.jobgradeIds.options[ i ].value ;
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
		orgids = allorgids;
	}



	var tu = "/jsp/report/time_to_fill.jsp?reportfilename=Time_to_fill.jasper&orgids="+orgids+"&deptids="+deptids+"&hiringMgrIds="+hiringmgrids+"&recruiterIds="+recruiterids+"&requitionIds="+requitionIds+"&jobGradeIds="+jobgradeids+"&year="+years;
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
<h3><%=Constant.getResourceStringValue("home.Reports.Time_To_Fill",user2.getLocale())%></h3>
<br>
<table border="0" width="100%" align="left" class="div">

		<tr>
			<td width="21%"><%=Constant.getResourceStringValue("admin.organization.organization",user2.getLocale())%></td>
			<td>

				<html:select property="orgIds" styleClass="multilistsmall" multiple="true" size="3" onchange="retrieveURLOrg('reports.do?method=deptlistmultiple');">
				<bean:define name="reportForm" property="organizationList" id="organizationList" />
           	 	<html:options collection="organizationList" property="orgId"  labelProperty="orgName"/>
				</html:select>	
					&nbsp;
			

			
			</td>

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
			<td ><%=Constant.getResourceStringValue("Requisition.jobreqname",user2.getLocale())%></td>
			<td>
			<html:select  property="requitionIds" styleClass="multilistsmall" multiple="true" size="3">
			<bean:define name="reportForm" property="jobtitleList" id="jobtitleList" />
            <html:options collection="jobtitleList" property="jobreqId"  labelProperty="jobTitle"/>
			</html:select>

			</td>
			<td ><%=Constant.getResourceStringValue("hr.user.job_grade",user2.getLocale())%></td>

			<td>
				<html:select property="jobgradeIds" styleClass="multilistsmall" multiple="true" size="3">
				<bean:define name="reportForm" property="jobGradeList" id="jobGradeList" />
	            <html:options collection="jobGradeList" property="jobgradeId"  labelProperty="jobGradeName"/>
				</html:select>			
		
			</td>


		</tr>
		<tr>

			<td ><%=Constant.getResourceStringValue("hr.year",user2.getLocale())%></td>
			<td>
	
			<html:select  property="year" styleClass="multilistsmall" multiple="true" size="3">
			<bean:define name="reportForm" property="yearList" id="yearList" />
            	<html:options collection="yearList" property="key"  labelProperty="value"/>
			</html:select>

			
			</td>




		</tr>



		<tr>
		<td></td>
		<td><span class="textboxlabel" id="loadingd" STYLE="font-size: smaller;Visibility:hidden";>
						<%=Constant.getResourceStringValue("admin.Deparment.loadingdept.only",user2.getLocale())%> ....</span></td>
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
			<td width="21%"><%=Constant.getResourceStringValue("Requisition.hiringmgr",user2.getLocale())%></td>
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
					<% 
								 
					String data2="";
					if(aform.getUsersset() != null && aform.getUsersset().size()>0){
		             Iterator itr = aform.getUsersset().iterator();
		
						while(itr.hasNext()){
							User touser = (User)itr.next();
										
							data2 = data2 + "{ "+"\""+"id"+"\""+":"+"\""+touser.getUserId()+"\","+"  "+"\""+"name"+"\""+":"+"\""+touser.getFirstName()+ " "+touser.getLastName()+"\""+"}"+",";
						}
				
						if(!StringUtils.isNullOrEmpty(data2)){
							data2 = data2.substring(0,data2.length()-1);
						}
					}
					
			%>
		<tr>
			<td ><%=Constant.getResourceStringValue("Requisition.Recruiter",user2.getLocale())%></td>
  
			<td>
			
		         
		        <input type="text" id="demo-input-pre-populated2" name="users2" />
				<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";><%=Constant.getResourceStringValue("autosuggest.message",user2.getLocale())%></span>
		        <script type="text/javascript">
		        $(document).ready(function() {
		            $("#demo-input-pre-populated2").tokenInput("jsp/talent/getUserDataJsonMuti.jsp", {
						 preventDuplicates: true,
					     hintText: "Type in a search user",
		                prePopulate: [
								<%=data2%>
		                ]
		            });
		        });
		        </script>
		   
		       </td>
		</tr>

		<tr>
			<td><br><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.run",user2.getLocale())%>" onClick="runreport()" class="button"></td>
			<td></td>

		</tr>
			

</table>
</span>
</html:form>
</html>