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
<%@ page import="com.bean.onboard.*" %>
<%@ include file="../common/autocomplete.jsp" %>
<%@ page import="com.form.*" %>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<title></title>
<bean:define id="form" name="organizationEmailTemplateForm" type="com.form.OrganizationEmailTemplateForm" />

<%
String start = form.getStart();
String range = form.getRange();
String results = form.getResults();
String close = request.getParameter("close");
%>
<%
String boxnumber = (String)request.getAttribute("boxnumber");


%>
<%
List checkedreqids1 = new ArrayList();
List checkedreqnames1 = new ArrayList();
if(session.getAttribute("checkedreqids") != null){
checkedreqids1 = (ArrayList)session.getAttribute("checkedreqids");

}
if(session.getAttribute("checkedreqnames") != null){
checkedreqnames1 = (ArrayList)session.getAttribute("checkedreqnames");

}

String reqidstr = "";
for(int i=0;i<checkedreqids1.size();i++){
	reqidstr = reqidstr + checkedreqids1.get(i)+",";
}
if(reqidstr != null && reqidstr.length()>0){
reqidstr = reqidstr.substring(0, reqidstr.length()-1);
}

String reqnamestr = "";
for(int i=0;i<checkedreqnames1.size();i++){
	System.out.println("checkedreqnames1.get(i)"+checkedreqnames1.get(i));
	reqnamestr = reqnamestr + checkedreqnames1.get(i)+",";
}
if(reqnamestr != null && reqnamestr.length()>0){
reqnamestr = reqnamestr.substring(0, reqnamestr.length()-1);
}

%>
<script language="javascript">

var  PFormName= opener.document.forms[0].name;  
var close1 = "<%=close%>";

var clickcountuserids = "";


	function discard(){
       self.close();
		}

if(close1 == "yes"){
	alert(close1);
	var reqrr = '<%=reqidstr%>'.split(','); 
var reqnamearr = '<%=reqnamestr%>'.split(','); 

var reqlen=reqrr.length;
var reqnamelen=reqnamearr.length;

    // opener.element.getElementByID("tx1").value = "search result";
var c_value = "";
var id_value = "";
var d_value = "";
 


if (reqlen != undefined)
{

 for (var i=0; i < reqlen; i++)
   {
 
	  if (reqrr[i] != undefined) { 
      c_value = c_value + reqnamearr[i] + ",";
	  id_value = id_value + reqrr[i] +",";
	  var tempurl = "<a href='#' onClick=window.open("+"'"+"OnBoardingTaskDefi.do?method=OnBoardingTaskDefiDetails&id="+reqrr[i]+"'"+","+ "'"+"userPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600"+"'"+")>"+reqnamearr[i]+"</a>&nbsp;&nbsp;";
      d_value = d_value + tempurl;
	  }
   }
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;


var dyvalue = '<span id="primaryOwnerId">'+d_value+'</span>';

//window.opener.document.getElementById("primaryOwnerId").innerHTML = dyvalue;

window.opener.document[PFormName].primaryOwnerId.value=id_value.trim();
window.opener.document[PFormName].primaryOwnerName.value=c_value.trim();

//window.location.href = "lov.do?method=removereqidsinsession";
self.close();
}

	//function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";
//window.location.href = "orgemailtemplate.do?method=emaillitsearch&boxnumber=<%=boxnumber%>" +"&close=yes"";
//	}

	
function savedata(){
    // opener.element.getElementByID("tx1").value = "search result";

var c_value = "";
var id_value = "";
var len = document.organizationEmailTemplateForm.emailtemplateId.length; 
if(len == undefined) len = 1; 

if (document.organizationEmailTemplateForm.emailtemplateId.length != undefined)
{

 for (var i=0; i < document.organizationEmailTemplateForm.emailtemplateId.length; i++)
   {
   if (document.organizationEmailTemplateForm.emailtemplateId[i].checked == true)
      {
	   
      c_value =document.organizationEmailTemplateForm.emailtemplateId[i].value + "\n";
	  id_value =  document.organizationEmailTemplateForm.emailtemplateId[i].id;
      }
   }
}else{
	if (document.organizationEmailTemplateForm.emailtemplateId.checked == true) {
	c_value = document.organizationEmailTemplateForm.emailtemplateId.value + "\n";
	  id_value = document.organizationEmailTemplateForm.emailtemplateId.id;
	}
}
//opener.getElementByID("evaluationsheet").innerHTML = c_value;

var tempurl = "<a href='#' onClick=window.open("+"'"+"emailtemplate.do?method=editemailtemplate&readPreview=2&emailtemplateId="+id_value+"'"+","+ "'"+"LocationPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=800"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="<%=form.getEmailvaluespan()%>">'+tempurl+'</span>';
window.opener.document.getElementById("<%=form.getEmailvaluespan()%>").innerHTML = dyvalue;

//opener.document[PFormName].emailtemplateId.value=2;
//opener.document[PFormName].emailtemplatename.value=s;
window.opener.document['organizationEmailTemplateForm'].<%=form.getEmailtemplatenameval()%>.value=c_value;

window.opener.document['organizationEmailTemplateForm'].<%=form.getEmailtemplateidval()%>.value=id_value.trim();

//alert(id_value);
self.close();
	   
		}


		function searchcri(){

	   document.organizationEmailTemplateForm.action = "orgemailtemplate.do?method=searchEmailtemplates&boxnumber="+<%=boxnumber%>;
	   document.organizationEmailTemplateForm.submit();

	
	   
		}
 function resetData(){
   document.organizationEmailTemplateForm.emailtemplatename.value="";
 }

function init(){
document.organizationEmailTemplateForm.emailtemplatename.focus();
// document.getElementById('roleCode').focus();
}

</script>
<body onLoad="init()">

<html:form action="/orgemailtemplate.do?method=emaillitsearch&boxnumber=<%=boxnumber%>">
	<table border="0" width="100%">

	<h3><%=Constant.getResourceStringValue("admin.OrganizationEmailTemplate.searchemailtemp",user1.getLocale())%> </h3>
    <html:hidden property="emailvaluespan" value="<%=form.getEmailvaluespan()%>"/>
    <html:hidden property="emailtemplateidval" value="<%=form.getEmailtemplateidval()%>"/>
    <html:hidden property="emailtemplatenameval" value="<%=form.getEmailtemplatenameval()%>"/>
    <tr>

	   <tr>
		   <td><%=Constant.getResourceStringValue("admin.EmailTemplate.name",user1.getLocale())%></td><td><html:text  property="emailtemplatename"/></td>
	   </tr>	
		   <td><input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri()">
		   <input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetData()">
</td>
	   </tr>

			
				
</table>	
<table border="0" width="100%">
<tr>
<td bgcolor="gray"><%=Constant.getResourceStringValue("admin.EmailTemplate.name",user1.getLocale())%> </td>
</tr>
</table>
<div id="dynamicdata">
<%
List checkeduserids = new ArrayList();
if(session.getAttribute("checkeduserids") != null){
checkeduserids = (ArrayList)session.getAttribute("checkeduserids");

}

List orgemailList = form.getEmailtemplateList();
 if(orgemailList != null && orgemailList.size()>0){
%>
<pagination-tag:pager action="orgemailtemplate" start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>
<%}%>
<table border="0" width="100%">
<tr>
<td></td>
</tr>
 <%
	if(orgemailList != null){

	 for(int i=0;i<orgemailList.size();i++){

		EmailTemplates orgemail = (EmailTemplates)orgemailList.get(i);
    String val = orgemail.getEmailtemplatename();
	String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
  
/*
 
 if(orgemailList != null){

	 for(int i=0;i<orgemailList.size();i++){

		EmailTemplates  orgemailtemp = (EmailTemplates)orgemailList.get(i);
String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
*/
%>
     <tr bgcolor=<%=bgcolor%>>
<td ><input type="radio" name="emailtemplateId" id="<%=orgemail.getEmailtemplateId()%>" value="<%=val%>">

<a href="#" onClick="window.open('emailtemplate.do?method=editemailtemplate&readPreview=2&emailtemplateId=<%=orgemail.getEmailtemplateId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=750,height=900')"><%=orgemail.getEmailtemplatename()%></a>

	</script>

</td>

<%

	 }
 }

 %>

<%  if(orgemailList != null && orgemailList.size()<1){ %>
  <%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%>
<%}%>
		  
	<tr>
				<td>
				<%  if(orgemailList != null && orgemailList.size()>0){ %>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()">&nbsp;&nbsp;
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()">
			<%}%>
				</td>
				
			</tr>
			</div>
</table>
</html:form>
