<script language="javascript">

function manageApplicantFlters(){
	var url = "businessrule.do?method=manageApplicantFilterSrc&type=TEMPLATE&reqId=<%=jobreqtmplform.getTemplateId()%>";
     var url1 = "jsp/emp/temp.jsp?reurl="+encodeURIComponent(url);
 
  window.showModalDialog(url,"<%=Constant.getResourceStringValue("admin.manage.applicant.filters",user1.getLocale())%>","dialogHeight: 434px; dialogWidth: 750px; dialogTop: 50px; dialogLeft: 301px; edge: Raised; center: Yes; resizable: Yes; status: off;");

 window.document.location.href="jobtemplate.do?method=edittemplate&templateId=<%=jobreqtmplform.getTemplateId()%>&tabno=6";
 
}
</script>

<%
List filtersList = jobreqtmplform.getFilters();
%>
<fieldset><legend><%=Constant.getResourceStringValue("admin.applicant.filters",user1.getLocale())%>
<a href="#" class="titleHintBox"  title="<%=Constant.getResourceStringValue("Requisition.templates.filters.help",user1.getLocale())%>"><img src="jsp/images/questionMark.jpg" border="0" width="18" height="18"/></a>
</legend>
<table  width="100%">
<tr>
<td>
<a href="#" onClick="manageApplicantFlters()"><%=Constant.getResourceStringValue("admin.manage.applicant.filters",user1.getLocale())%></a> <br>
</td>
<td>
</td>
</table>

<% 
	if(filtersList != null && filtersList.size()>0){
	
%>

<table>
    <tr>
	<td width="500"><b><%=Constant.getResourceStringValue("admin.businessRule.applicant.filter",user1.getLocale())%></b></td>
	<td><b><%=Constant.getResourceStringValue("admin.filter.is.silent",user1.getLocale())%></b></td>
	</tr>
    


<%

for(int i=0;i<filtersList.size();i++){
	BusinessCriteria japp = (BusinessCriteria)filtersList.get(i);


	String	filterlink="	<img src=\"jsp/images/applicant_filter_icon.gif\" width='19' height='19'>"+
		"<a href=\"#\" onClick=\"window.open('businessrule.do?method=viewApplicantFilter&filterid="+japp.getBusinessCriteraId()+"', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')\">"+japp.getName()+"</a>";
	
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

<%}%>
</fieldset>