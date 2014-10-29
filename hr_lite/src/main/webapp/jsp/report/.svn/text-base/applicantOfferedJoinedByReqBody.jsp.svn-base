<%@ include file="../common/include.jsp" %>
<bean:define id="aform" name="reportForm" type="com.form.ReportForm" />



<script language="javascript">
function opensearchjobreq(){
	 window.open("lov.do?method=requisitionselector", "SearchLocation","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

function runreport(){

      var alertstr = "";
    var reqid1 = document.reportForm.reqid1.value;
		var showalert=false;

	if(reqid1 == "" || reqid1 == null){
     	alertstr = alertstr + "Please select Requisition.<BR>";
		showalert = true;
		}

	

	 if (showalert){
     	alert(alertstr);
        return false;
          }


	  document.reportForm.action = "<%=request.getContextPath()%>/jsp/report/applicant_offered_joined_by_req.jsp?reportfilename=report4.jasper";
   document.reportForm.submit();
	}
</script>
<b>Total applicants vs offered by Requisition</b>
<html:form action="/reports.do?method=logon" >

<table border="0" cellpadding="0" cellspacing="0">
<br><br>








<%
		String jobdyvalue ="<span id=\"requitionId\">";
        if(aform.getRequitionId() != 0){

  String tempurl1 = "<a href='#' onClick=window.open("+"'"+"jobreq.do?method=editjobreq&jobreqId="+aform.getRequitionId()+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=700"+"'"+")>"+aform.getJobTitle()+"</a>";
 jobdyvalue = "<span id=\"requitionId\">"+tempurl1+"</span>";
		}
%>
		<tr>
				<td>Job Title*</td>

				<td>
				<input type="hidden"  name="reqid1" value="<%=aform.getRequitionId()%>"/>
				<input type="hidden" name="jobt1"  value="<%=aform.getJobTitle()%>"/>
				<%=jobdyvalue%></span><a href="#" onClick="opensearchjobreq()"><img src="jsp/images/selector.gif" border="0"/></a>
				
				
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
    	
