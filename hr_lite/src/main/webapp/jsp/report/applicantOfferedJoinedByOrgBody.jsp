<%@ include file="../common/include.jsp" %>
<bean:define id="aform" name="reportForm" type="com.form.ReportForm" />



<script language="javascript">
function opensearchorg(){
  window.open("org.do?method=orgselector&readPreview=3&orgId=1", "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function runreport(){

      var alertstr = "";
    var orgid = document.reportForm.parentOrgId.value;
		var showalert=false;

	if(orgid == "" || orgid == null){
     	alertstr = alertstr + "Please select organization.<BR>";
		showalert = true;
		}

	

	 if (showalert){
     	alert(alertstr);
        return false;
          }


	  document.reportForm.action = "<%=request.getContextPath()%>/jsp/report/applicant_offered_joined_by_org.jsp?reportfilename=report3.jasper";
   document.reportForm.submit();
	}
</script>
<b>Total applicants vs offered by Organisation</b>
<html:form action="/reports.do?method=logon" >

<table border="0" cellpadding="0" cellspacing="0">
<br><br>








<%
		String orgdyvalue1 ="<span id=\"parentOrgId\">";
        if(aform.getOrgId() > 0){

  String tempurl1 = "<a href='#' onClick=window.open("+"'"+"org.do?method=editorg&readPreview=2&orgid="+aform.getOrgId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=500,height=300"+"'"+")>"+aform.getOrgName()+"</a>";
 orgdyvalue1 = "<span id=\"parentOrgId\">"+tempurl1+"</span>";
		}

		%>


		<tr>
				<td width="200">Organization</td>
				<td><%=orgdyvalue1%></span>&nbsp;&nbsp;<a href="#" onClick="opensearchorg()"><img src="jsp/images/selector.gif" border="0"/></a>
				<input type="hidden"  name="parentOrgId" value="<%=aform.getOrgId()%>"/>
				<input type="hidden"  name="parentOrgName" value="<%=aform.getOrgName()%>"/>
				
				</td>
			</tr>


			<tr>
				<td width="200"></td>
				<td>
				
				</td>
			</tr>
			

<tr>
				<td width="200"><input type="button" name="login" value="Run" onClick="runreport()"></td>
				<td>
				
				</td>
			</tr>
			

</table>
</html:form>
    	
