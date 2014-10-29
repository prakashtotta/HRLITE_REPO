<%@ include file="../common/include.jsp" %>
  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

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
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.Please_select_org_first",user1.getLocale())%><BR>";
		showalert = true;
		}

	

	 if (showalert){
     	alert(alertstr);
        return false;
          }


	  document.reportForm.action = "<%=request.getContextPath()%>/jsp/report/requistion_opening_stat.jsp?reportfilename=requistion_opening_stat.jasper";
   document.reportForm.submit();
	}
</script>
<b><%=Constant.getResourceStringValue("Requisition.Requisition opening statistics",user1.getLocale())%></b>
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
				<td width="200"><%=Constant.getResourceStringValue("admin.organization.orglistbody.Organization",user1.getLocale())%></td>
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
				<td width="200"><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.run",user1.getLocale())%>" onClick="runreport()"></td>
				<td>
				
				</td>
			</tr>
			

</table>
</html:form>
    	
