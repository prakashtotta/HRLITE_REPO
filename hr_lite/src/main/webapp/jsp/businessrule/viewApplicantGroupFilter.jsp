<%@ include file="../common/include.jsp" %>
<%@ include file="../common/autocomplete.jsp" %>
<%@ page import="com.form.*" %>
<%@ page import="com.bean.lov.*" %>

<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.bean.filter.*" %>
<%
System.out.println(" createbusinessrule.jsp .... ");
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());

String savebusinessrule =(String)request.getAttribute("saveapplicationfilter");
%>
<bean:define id="bForm" name="businessRuleForm" type="com.form.BusinessRuleForm" />

<script language="javascript">

function viewFilter(id){
	//alert(id);
	var url = "businessrule.do?method=viewFilter&filterid="+id;
	window.open(url,  "SearchFilter","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=400,height=300");
}
</script>
<body class="yui-skin-sam" onload="load()">

<html:form action="/businessrule.do?method=savesbusinessrule">

<% if(! StringUtils.isNullOrEmpty(savebusinessrule) && savebusinessrule.equals("yes")) {%>
<div class="msg"><font color="green"><%=Constant.getResourceStringValue("admin.applicant.filter.savemesssage",user1.getLocale())%></font></div>
<%} %>
<br>
<div class="div">
<table>
	<tr>
		<td></td>
		<td><%=Constant.getResourceStringValue("admin.businessRule.name",user1.getLocale())%></td><td> : </td>
		<td><bean:write name="businessRuleForm" property="name" /></td>
	</tr>
	<tr>
		<td></td>
		<td><%=Constant.getResourceStringValue("admin.businessRule.desc",user1.getLocale())%> </td><td>: </td>

		<td><bean:write name="businessRuleForm" property="desc" /></td>
	</tr>
	
</table>
<br>
<fieldset><legend><%=Constant.getResourceStringValue("admin.businessRule.filter.precondition",user1.getLocale())%></legend>
<table>
	<tr>
		
		<td><%=Constant.getResourceStringValue("admin.screenfield.Field_Name",user1.getLocale())%> </td><td>: </td>
		<td><bean:write name="businessRuleForm" property="variableName" /></td>
	</tr>
	<tr>
		<td><%=Constant.getResourceStringValue("admin.businessRule.criteria",user1.getLocale())%> </td><td>:</td>
		<td><bean:write name="businessRuleForm" property="variableCriteria" /></td>
	</tr>
	<tr>
	<td><%=Constant.getResourceStringValue("admin.businessRule.filterValue",user1.getLocale())%> </td><td>:</td>
		<td><bean:write name="businessRuleForm"  property="filterValue1" /></td>
		<%if(bForm.getVariableCriteria() != null && bForm.getVariableCriteria().equals("BETWEEN") ){ %>
		<td><%=Constant.getResourceStringValue("admin.businessRule.and",user1.getLocale())%></td>
		<%} %>
		<td><bean:write name="businessRuleForm"  property="filterValue2" /></td>
	</tr>
</table>
</fieldset>
<br>
<fieldset><legend><%=Constant.getResourceStringValue("admin.businessRule.filters",user1.getLocale())%></legend>
<%
List filtersList = bForm.getFiltersList();
%>
<table>
	<tr>
		
		<td>
		<input type="hidden" name="idlistvalflt" value=""/>
		<input type="hidden" value="<%=filtersList.size()%>" id="theValue2" />

		</td>
		<td>

		</td>
	</tr>
    <tr>
	<td>
	<div id="myDiv2"> 

<%
int k=1;
if(filtersList != null){
for(int i=0;i<filtersList.size();i++){
	BusinessFilterConditions japp = (BusinessFilterConditions)filtersList.get(i);
     String tdiv = "my2"+k+"Div";
	 String tspan = "approverspan_"+k;
	 String tfiltername = "filtername_"+k;
	 String tuserid ="filterid_"+k;
	 String apptx = " Approved";
	 String leveltext1 = "Filter-"+k+"&nbsp;&nbsp";
	 String filterlink = "";

		filterlink="	<img src=\"jsp/images/filter-icon.gif\" width='19' height='19'>"+
		"<a href=\"#\" onClick=\"viewFilter("+japp.getFilterConditionId()+");return false\">"+japp.getName()+"</a>";
	
	String tempdivapp = "<div id='"+tdiv+"'"+">"+
		"<span class='blue' id='"+tspan+"'"+">"+ leveltext1 +
		"<input type=\"hidden\" value='"+japp.getName()+"' name='"+tfiltername+"'"+"/>"+" "+filterlink + "  "+
       "<input type=\"hidden\"   value="+japp.getFilterConditionId()+" name='"+tuserid+"'"+"/>"+
	   	"</span>";



String tempdivapp1 =  "</div>";

	tempdivapp = tempdivapp  + tempdivapp1;


	k++;
%>

<%=tempdivapp%>

<%}}%>


</div>
	</td>
	<td>
	</td>
	</tr>

</table>
</div>
</fieldset>
</html:form>
</body>