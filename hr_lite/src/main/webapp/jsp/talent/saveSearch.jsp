<%@ include file="../common/include.jsp" %>
 <bean:define id="aform" name="applicantForm" type="com.form.ApplicantForm" />
<%

String datepattern = Constant.getValue("defaultdateformat");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String searchuuid = (String)request.getAttribute("searchuuid");

String savedsearch = (String)request.getAttribute("savedsearch");


%>



<script language="javascript">

function closewindow(){
	  parent.parent.GB_hide();
}

function savesearchdata1(){

	var alertstr = "";
    var savedsearchname = document.applicantForm.savedsearchname.value;
		var showalert=false;

	if(savedsearchname == "" || savedsearchname == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("aquisition.applicant.save_search_name",user1.getLocale())%>";
		showalert = true;
		}

	if (showalert){
     	alert(alertstr);
        return false;
          }
	
document.applicantForm.action = "applicant.do?method=savesearchapplicantbyvendor&searchuuid=<%=searchuuid%>";
document.applicantForm.submit();

}

</script>

<% if(savedsearch != null && savedsearch.equals("yes")){%>
<font color="green"><%=Constant.getResourceStringValue("aquisition.applicant.searchsavemsg",user1.getLocale())%> </font><!--  <a href="#" onClick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </a> -->
	
<%}else{%>
<br><br>
<html:form action="/applicant.do?method=savesearchapplicantbyvendor">
<table>
<tr>
<td>
<%=Constant.getResourceStringValue("aquisition.applicant.Save_search_name",user1.getLocale())%> :
</td>
<td>
<input type='text' size='60' name='savedsearchname'> 
</td>
</tr>
<td>
<input type='button' name='savesearchdata' value='<%=Constant.getResourceStringValue("aquisition.applicant.save_search_save",user1.getLocale())%>' onClick='savesearchdata1()'>
</td>
<td>
</td>
</tr>
</table>
</html:form>

<%}%>