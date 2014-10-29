<%@ include file="../common/include.jsp" %>
<%@ taglib uri="http://paginationtag.miin.com" prefix="pagination-tag"%>
<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.resources.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.bean.filter.*"%>


  <%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


<%
String boxnumber = (String)request.getAttribute("boxnumber");

%>
<title><%=Constant.getResourceStringValue("admin.businessRule.applicant.filter.selector",user1.getLocale())%>  </title>
<bean:define id="bForm" name="businessRuleForm" type="com.form.BusinessRuleForm" />

<%
String start = bForm.getStart();
String range = bForm.getRange();
String results = bForm.getResults();

%>
<script language="javascript">

var  PFormName= opener.document.forms[0].name;  

var boxnu = "<%=boxnumber%>";


	function discard(){
       self.close();
		}

	function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";
var c_value = "";
var id_value = "";
var len = document.businessRuleForm.filterId.length; 
if(len == undefined) len = 1; 

if (document.businessRuleForm.filterId.length != undefined)
{

 for (var i=0; i < document.businessRuleForm.filterId.length; i++)
   {
   if (document.businessRuleForm.filterId[i].checked == true)
      {
	   
      c_value =document.businessRuleForm.filterId[i].value + "\n";
	  id_value =  document.businessRuleForm.filterId[i].id;
      }
   }
}else{
	if (document.businessRuleForm.filterId.checked == true) {
	c_value = document.businessRuleForm.filterId.value + "\n";
	  id_value = document.businessRuleForm.filterId.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;


if(boxnu != -1 && boxnu != 99){
	var check1="false";
	var approvenum = 'filterid_'+boxnu;
	var chechapprovenum=window.opener.document['businessRuleForm'][approvenum].value;
   var pary = window.opener.document['businessRuleForm'].idlistvalflt.value;
   var array1=new Array();
   var array1new=new Array();
array1=pary;
for(var i=0;i<array1.length;i++){

if(id_value == array1[i]){
check1="true";
break;
}
}
if(check1=="false"){
	  var spanstart = '<span  id="approverspan_'+boxnu+'">' + 'Filter-'+boxnu+'&nbsp;&nbsp';
	  var username1 = '<input type="hidden" value="'+c_value+'" name="filtername_'+boxnu+'"/>';
	  var userid1 = '<input type="hidden"  value='+id_value+'  name="filterid_'+boxnu+'"/>';
	  var isusergroup ="";
	  var userlink ="";

		userlink = "<img src='jsp/images/applicant_filter_icon.gif' width='19' height='19'><a href='#' onClick=window.open("+"'"+"businessrule.do?method=viewApplicantFilter&filterid="+id_value+"'"+","+ "'"+"filterPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550"+"'"+")>"+c_value+"</a>";

	  var spanend = '</span>'; 

	var newspanvalue = spanstart+username1+userlink+userid1+spanend;
	var tmp = "approverspan_"+boxnu;

	  window.opener.document.getElementById(tmp).innerHTML = newspanvalue;


  var array3=new Array();
   array3=pary;
   var array3new=new Array();
for(var i=0;i<array3.length;i++){
if(chechapprovenum!=array3[i]){
array3new[i]=array3[i];
}
}
  window.opener.document['businessRuleForm'].idlistvalflt.value=array3new+" "+id_value;
 // alert(chechapprovenum);
 self.close();

}else{
alert("<%=Constant.getResourceStringValue("admin.systemrules.approver.applicantfilterselector.existsmessage",user1.getLocale())%>");
}

   





/*if(boxnu == -1){

var tempurl = "<a href='#' onClick=window.open("+"'"+"user.do?method=edituser&readPreview=2&userId="+id_value+"'"+","+ "'"+"userPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="hiringMgrId">'+tempurl+'</span>';

window.opener.document.getElementById("hiringMgrId").innerHTML = dyvalue;

opener.document[PFormName].hiringMgrId.value=id_value;

}*/




//self.close();
}
}

		function searchcri(){
       
	   document.businessRuleForm.action = "businessrule.do?method=searchApplicantFilters&boxnumber=<%=boxnumber%>";
	   document.businessRuleForm.submit();
	   
		}



function discard(){
       self.close();
		}
function resetData(){
	document.businessRuleForm.name.value="";
	document.businessRuleForm.type.value="";

}

</script>
<body class="yui-skin-sam"  onSubmit="searchcri()">

<html:form action="/businessrule.do?method=searchApplicantFilters&boxnumber=<%=boxnumber%>">
<div  class="div">
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("admin.businessRule.search.filter",user1.getLocale())%></b></td>
</tr>
</table>

     <table border="0" width="100%">

    <tr>
		<td><%=Constant.getResourceStringValue("admin.filter.name",user1.getLocale())%> :</td>
		<td><html:text  property="name" maxlength="200"/></td>
		<td><%=Constant.getResourceStringValue("admin.filter.precondition.type",user1.getLocale())%> :</td>
		<td>
		<html:select  property="type">
		<option value=""></option>
				<bean:define name="businessRuleForm" property="typeList" id="typeList" />
	            <html:options collection="typeList" property="key"  labelProperty="value"/>
			</html:select>
		</td>
	</tr><tr></tr><tr></tr><tr></tr><tr></tr>
	<tr>
		<td colspan="4">
		<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()" class="button">
        <input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()" class="button">
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
		</td>
		 
	</tr>
	</table>
	</div>
<br><br>
<div  class="div">
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("admin.businessRule.filters",user1.getLocale())%></b></td>
</tr>
</table>
<div id="dynamicdata">
<%
 List filterList = bForm.getFilterCriteriasList();
 if(filterList != null && filterList.size()>0){
%>
<pagination-tag:pager action="user" start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>

<%

 if(filterList != null && filterList.size()>0){
%>
<!--users list -->
<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("admin.filter.name",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.filter.type",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.filter.varible.name",user1.getLocale())%></td>


</tr>
 <%

 


	 for(int i=0;i<filterList.size();i++){

		 BusinessCriteria bc = (BusinessCriteria)filterList.get(i);
   
	String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";

%>


<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="filterId" id="<%=bc.getBusinessCriteraId()%>" value="<%=bc.getName()%>">
<img src="jsp/images/applicant_filter_icon.gif" width="19" height="19">
<a href="#" onClick="window.open('businessrule.do?method=viewApplicantFilter&filterid=<%=bc.getBusinessCriteraId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=bc.getName()%></a>


</td>
<td>
<%=(bc.getVariableType()==null)?"":bc.getVariableType()%>
</td>
<td>
<%=(bc.getVariableName()==null)?"":bc.getVariableName()%>

</td>
</tr>


<%

	 }
 }

 %>
<!--user list end -->

<%  if(filterList != null && filterList.size()<1){ %>
 <%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%>
<%}%>
		  
	<tr>
				<td>
				<%  if(filterList != null && filterList.size()>0){ %>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			<%}%>
				</td>
				
			</tr>	      
</table>

</div>
</html:form>
</body>