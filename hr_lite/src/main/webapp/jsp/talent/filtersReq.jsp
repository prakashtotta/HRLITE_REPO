<script language="javascript">

function manageApplicantFlters(){
	var url = "businessrule.do?method=manageApplicantFilterSrc&type=REQUISITION&reqId=<%=jobreqform.getJobreqId()%>";
     var url1 = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(url);
 
  window.showModalDialog(url,"<%=Constant.getResourceStringValue("admin.manage.applicant.filters",user1.getLocale())%>","dialogHeight: 434px; dialogWidth: 750px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; center: Yes; resizable: Yes; status: off;");

  window.document.location.href="jobreq.do?method=editjobreq&jobreqId=<%=jobreqform.getJobreqId()%>&tabno=7";
}
</script>
<br>
<b><%=Constant.getResourceStringValue("admin.applicant.filters",user1.getLocale())%></b>
<a href="#" class="titleHintBox"  title="<%=Constant.getResourceStringValue("Requisition.filters.help",user1.getLocale())%>"><img src="jsp/images/questionMark.jpg" border="0" width="18" height="18"/></a>
<table  width="100%">
<tr>
<td>
<a class="closelink" href="#" onClick="manageApplicantFlters()"><%=Constant.getResourceStringValue("admin.manage.applicant.filters",user1.getLocale())%></a> <br>
</td>
<td>
</td>
</table>
<%
List filtersList = jobreqform.getFilters();
%>
<div class="div">
<table>
    <tr>
	<td width="500"><b><%=Constant.getResourceStringValue("admin.businessRule.applicant.filter",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("admin.filter.is.silent",user1.getLocale())%></b></td>
	</tr>
    


<%

for(int i=0;i<filtersList.size();i++){
	BusinessCriteria japp = (BusinessCriteria)filtersList.get(i);


	String	filterlink="	<img src=\"jsp/images/applicant_filter_icon.gif\" width='19' height='19'>"+
		"<a href=\"#\" onClick=\"window.open('businessrule.do?method=viewApplicantFilter&filterid="+japp.getBusinessCriteraId()+"', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=400,height=400')\">"+japp.getName()+"</a>";
	
%>
<tr>
	<td width="500">
	<%=filterlink%>

	</td>
	<td>
	<%=japp.getIsSilent()%>

	</td>
</tr>



<%}%>



	

</table>
</div>