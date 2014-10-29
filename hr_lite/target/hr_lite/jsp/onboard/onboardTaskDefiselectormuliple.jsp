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
<%@ page import="java.util.*"%>

<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<%
String boxnumber = (String)request.getAttribute("boxnumber");

%>

<title><%=Constant.getResourceStringValue("admin.Onboarding.task.definition.selector",user1.getLocale())%></title>

<bean:define id="form" name="OnboardingtemplateForm" type="com.form.OnBoardingTemplateForm" />

<%
String start = form.getStart();
String range = form.getRange();
String results = form.getResults();
String close = request.getParameter("close");
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
		window.location.href = "lov.do?method=removereqidsinsession";
       self.close();
		}



if(close1 == "yes"){
	alert(close1);
	var reqrr = '<%=reqidstr%>'.split(','); 
var reqnamearr = '<%=reqnamestr%>'.split(','); 

var reqlen=reqrr.length;
var reqnamelen=reqnamearr.length;
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

var dyvalue = '<span id="primaryOwnerId">'+d_value+'</span>';
window.opener.document[PFormName].primaryOwnerId.value=id_value.trim();
window.opener.document[PFormName].primaryOwnerName.value=c_value.trim();
self.close();
}


 function searchcri(){ 
	   document.OnboardingtemplateForm.action = "OnBoardingTemplate.do?method=searchOnboaedtemplateDefination&boxnumber=<%=boxnumber%>";
	   document.OnboardingtemplateForm.submit();	
	   
		}

	function discard(){
		//window.location.href = "OnBoardingTaskDefi.do?method="";
       self.close();
		}


function savedata(){

var boxnu = "<%=boxnumber%>";

var pary = window.opener.document['OnboardingtemplateForm'].aaa.value;
var c_value = "";
var id_value = "";
var len = document.OnboardingtemplateForm.taskdefid.length; 

if(len == undefined) len = 1; 

if (document.OnboardingtemplateForm.taskdefid.length != undefined)
{

 for (var i=0; i < document.OnboardingtemplateForm.taskdefid.length; i++)
   {
   if (document.OnboardingtemplateForm.taskdefid[i].checked)
      {
	   
      c_value = c_value + document.OnboardingtemplateForm.taskdefid[i].value + "\n";
	  id_value = id_value +  document.OnboardingtemplateForm.taskdefid[i].id;
      }
   }
}else{
	if (document.OnboardingtemplateForm.taskdefid.checked) {
	c_value = c_value + document.OnboardingtemplateForm.taskdefid.value + "<br>";
	  id_value = id_value +document.OnboardingtemplateForm.taskdefid.id;

	}
}
//alert(" >> c_value >>"+c_value+" >> id_value >>"+id_value);
//alert(pary);
	var check1="false";
	var array1=new Array();
	var array1new=new Array();
	array1=pary;
	for(var i=0;i<array1.length;i++){
	
		if(id_value == array1[i] && boxnu != id_value){
		check1="true";
		break;
		}
	}


var idtasklabel='taskdefid_'+boxnu;
var idvaltask=window.opener.document['OnboardingtemplateForm'][idtasklabel].value;

if(check1=="false"){
var tempurl = "<a href='#' onClick=window.open("+"'"+"OnBoardingTaskDefi.do?method=OnBoardingTaskDefiDetails&readPreview=3&id="+id_value+"'"+","+ "'"+"userPreview"+ "'"+","+"'"+"location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=550"+"'"+")>"+c_value+"</a>";
var dyvalue = '<span id="<%=form.getCountOnboardingTask()%>">'+tempurl+'</span>';

window.opener.document.getElementById(document.OnboardingtemplateForm.countOnboardingTask.value).innerHTML = dyvalue;
window.opener.document['OnboardingtemplateForm'].<%=form.getCounttaskdefid()%>.value=id_value.trim();
window.opener.document['OnboardingtemplateForm'].<%=form.getCounttaskName()%>.value=c_value;

 var array3=new Array();
   array3=pary;
   var array3new=new Array();
for(var i=0;i<array3.length;i++){
if(idvaltask!=array3[i]){
array3new[i]=array3[i];
}
}

 window.opener.document['OnboardingtemplateForm'].aaa.value=array3new+" "+id_value;
self.close();
	   
		
}else{
alert("<%=Constant.getResourceStringValue("admin.OnBoardTemplate.selector.noduplicatemessage",user1.getLocale())%>");
}
}

function validateUserhm(){

	document.OnboardingtemplateForm.primaryOwnerName.value=document.OnboardingtemplateForm.primaryownernamehidden.value;

}
function resetdata(){

document.OnboardingtemplateForm.criteria.value="";
document.OnboardingtemplateForm.primaryOwnerName.value="";
document.OnboardingtemplateForm.primaryOwnerId.value="";

}

</script>
<script type="text/javascript">
	
$(function() {

	$("#primaryOwnerName").autocomplete({
		url: 'jsp/talent/getUserData.jsp',
	     
	
			onItemSelect: function(item) {
		    var text = 'You selected <b>' + item.value + '</b>';
		    if (item.data.length) {
		        text += ' <i>' + item.data.join(', ') + '</i>';
		    }
		 
		document.OnboardingtemplateForm.primaryOwnerId.value=+item.data;
		document.OnboardingtemplateForm.primaryownernamehidden.value=item.value;
		
		}
		});

     

});


function init(){
	setTimeout ( "document.OnboardingtemplateForm.criteria.focus(); ", 200 );
	//document.roleForm.roleCode.focus();
	}
</script>
<body class="yui-skin-sam" onLoad="init()">
<html:form action="/OnBoardingTemplate.do?method=searchOnboaedtemplateDefination">
<div class="div">
<table border="0" width="100%">

<html:hidden  property="countOnboardingTask" />
<html:hidden  property="counttaskdefid" />
<html:hidden  property="counttaskName" />

		<tr><td bgcolor="gray" colspan="4">
<b><%=Constant.getResourceStringValue("admin.OnBoardTemplate.selectorpage.SearchOnBoardingTaskDefination",user1.getLocale())%></b><br>
		</td></tr>
			 <tr>
								
				<td><%=Constant.getResourceStringValue("admin.OnBoardTemplate.selectorpage.SearchOnBoardingTaskDefination.TaskDefinationName",user1.getLocale())%></td>
				<td><html:text  property="criteria"/></td>
				<td><%=Constant.getResourceStringValue("admin.OnBoardTemplate.selectorpage.SearchOnBoardingTaskDefination.PrimaryOwnerName",user1.getLocale())%></td>
				
				<td>
				<input type="hidden" name="primaryownernamehidden">
				<input type="text"  id="primaryOwnerName" name="primaryOwnerName"  autocomplete="off" value="<%=(form.getPrimaryOwnerName()==null)?"":form.getPrimaryOwnerName()%>" onblur="validateUserhm()" >
				<span id="primaryOwnerId"></span>
				<%
                String primaryownerId = String.valueOf(form.getPrimaryOwnerId());
                 %>
                  <html:hidden  property="primaryOwnerId" value="<%=primaryownerId%>"/>


				</td>

			</tr>
				
	 <tr>
	   <td colspan="4">
		<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",user1.getLocale())%>" onClick="searchcri() "class="button">
		<input type="button" name="reset" value="<%=Constant.getResourceStringValue("hr.button.reset",user1.getLocale())%>" onClick="resetdata() "class="button">
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard() "class="button">
		</td><td></td><td></td>
	 </tr>
			
</table>
</div>
<br><br>
<div class="div">
<table border="0" width="100%">

<tr>
<td bgcolor="gray"><b><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.Create_OnBoardTaskDef",user1.getLocale())%></b></td>
</tr>
</table>
<%
List taskdefList = form.getOnboardtaskList();
 if(taskdefList != null && taskdefList.size()>0){
%>
<pagination-tag:pager action="OnBoardingTemplate" start="<%=start%>" range="<%=range%>" results="<%=results%>" styleClass="paginationClass"/>

<%}%>
<%

 if(taskdefList != null && taskdefList.size()>0){
%>
<table border="0" width="100%">
<tr>
<td><%=Constant.getResourceStringValue("task.taskname",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.Deparment.desc",user1.getLocale())%></td>
<td><%=Constant.getResourceStringValue("admin.OnBoardTaskDef.PrimaryOwnerName",user1.getLocale())%></td>

</tr>

 <%



	 for(int i=0;i<taskdefList.size();i++){

		 OnBoardingTaskDefinitions onboardtaskdef = (OnBoardingTaskDefinitions)taskdefList.get(i);
String bgcolor = "";
	if(i%2 == 0)bgcolor ="#B2D2FF";
	

%>


<tr bgcolor=<%=bgcolor%>>
<td ><input type="radio"  name="taskdefid" id="<%=onboardtaskdef.getTaskdefid()%>" value="<%=onboardtaskdef.getTaskName()%>" >

<a href="#" onClick="window.open('OnBoardingTaskDefi.do?method=OnBoardingTaskDefiDetails&readPreview=3&id=<%=onboardtaskdef.getTaskdefid()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=550')"><%=onboardtaskdef.getTaskName()%></a>

	</script>
</td>
<td><%=onboardtaskdef.getTaskDesc()%></td>
<td>
<%if(!StringUtils.isNullOrEmpty(onboardtaskdef.getIsGroup()) && onboardtaskdef.getIsGroup().equals("Y")){%>
<img src="jsp/images/User-Group-icon.png"><a href="#" onClick="window.open('usergroup.do?method=editusergroup&readPreview=2&usergroupid=<%=onboardtaskdef.getPrimaryOwnerId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600')"><%=onboardtaskdef.getPrimaryOwnerName()%></a> 
<%}else{%>
<img src="jsp/images/user.gif"><a href="#" onClick="window.open('user.do?method=edituser&readPreview=2&userId=<%=onboardtaskdef.getPrimaryOwnerId()%>', 'EvaluationTemplate','location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=550')"><%=onboardtaskdef.getPrimaryOwnerName()%></a> 
<%}%>
</td>

</tr>


<%

	 }
 }

 %>
<%  if(taskdefList != null && taskdefList.size()<1){ %>
<tr>
				<td>
<%=Constant.getResourceStringValue("hr.methods.datanotfoundmsg",user1.getLocale())%>
</td>
	<td></td><td></td>	<td></td>		
			</tr>
<%}%>
		  
	<tr>
				<td>
				<%  if(taskdefList != null && taskdefList.size()>0){ %>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()" class="button">&nbsp;&nbsp;
			
			    <%}%>
				</td>
				<td>
                  
				 </td>
				 <td></td><td></td>
				
			</tr>	 		     

</table>
</div>
</html:form>
</body>
