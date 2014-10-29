<%@ include file="../common/include.jsp" %>

<%
//String path = request.getContextPath()+request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<bean:define id="form" name="applicantForm" type="com.form.ApplicantForm" />

<script type="text/javascript">
document.documentElement.className = "yui-pe";
</script>
	
<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
</style>

<style type="text/css">
.yui-navset button {
    position:absolute;
    top:0;
    right:0;
}
</style>



<script type="text/javascript">
document.documentElement.className = "yui-pe";
</script>

<style type="text/css">
#example {
    height:30em;
}

label { 
    display:block;
    float:left;
    width:45%;
    clear:left;
}

.clear {
    clear:both;
}

#resp {
    margin:10px;
    padding:5px;
    border:1px solid #ccc;
    background:#fff;
}

#resp li {
    font-family:monospace
}

.yui-pe .yui-pe-content {
    display:none;
}
</style>



<body class="yui-skin-sam">



<%=Constant.getResourceStringValue("aquisition.applicant.grades",user1.getLocale())%> 

<br><br>
<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Competencies_required",user1.getLocale())%></legend>

<table>
<tr>
<td width="33%"><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
<td width="43%"><b><%=Constant.getResourceStringValue("aquisition.applicant.Minimum_score_required",user1.getLocale())%></b></td>
<td width="23%"><b> <%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
</tr>
<%
List comptetencyList = form.getComptetencyList();
if(comptetencyList != null && comptetencyList.size()>0){
   for(int i=0;i<comptetencyList.size();i++){
	JobTemplateCompetency comp = (JobTemplateCompetency)comptetencyList.get(i);
%>

<tr>
<td><%=comp.getCharName()%></td><td><%=comp.getImportance()%></td><td><%=(comp.getMandatory() != null && comp.getMandatory().equals("on"))?"Yes":"No"%></td>
</tr>

<%
   }
}else{
%>
<%=Constant.getResourceStringValue("aquisition.applicant.Competencies_are_not_defind",user1.getLocale())%>
<%
}
%>
</table>
</fieldset>
<br>
<fieldset><legend><%=Constant.getResourceStringValue("aquisition.applicant.Accomplishments_required",user1.getLocale())%></legend>

<table>
<tr>
<td width="63%"><b><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%></b></td>
<td width="23%"><b> <%=Constant.getResourceStringValue("aquisition.applicant.Is_mandatory",user1.getLocale())%></b></td>
</tr>
<%
List accList = form.getAccomplishmentList();
if(accList != null && accList.size()>0){
   for(int i=0;i<accList.size();i++){
	JobTemplateAccomplishment acc = (JobTemplateAccomplishment)accList.get(i);
%>

<tr>
<td><%=acc.getAccName()%></td><td><%=(acc.getMandatory() != null && acc.getMandatory().equals("on"))?"Yes":"No"%></td>
</tr>

<%
   }
}else{
%>
<%=Constant.getResourceStringValue("aquisition.applicant.Accomplishments_are_not_defind",user1.getLocale())%>
<%
}
%>
</table>

</fieldset>



</body>
</html>

