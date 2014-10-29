<%@ include file="../common/include.jsp" %>

<%
String path = (String)request.getAttribute("filePath");
String errMsg = (String)request.getAttribute("errMsg");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<%
////response.setHeader("Cache-Control", "no-cache");
	//	//response.setHeader("Pragma", "no-cache");
	//	//response.setIntHeader("Expires", 0);
%>

<bean:define id="jobreqform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<%
String hideval = "inline";
long jobreqid = 0;
 if(jobreqform.getJobreqId() > 0){
	 hideval = "none";
	 jobreqid = jobreqform.getJobreqId();
 }
%>
<html>
<style>
span1{color:#ff0000;}
</style>
<script language="javascript">
var returnVal = "something11";



var hidevalue1 = "<%=hideval%>";
var jobreqidscript = "<%=jobreqid%>";


if(0 == jobreqidscript){
   
}else{
	returnVal = jobreqidscript;

	//document.getElementById('progressbartable1').style.display = 'none'; 
	//var callme = 'editJobReq(\'' + jobreqidscript + '\')';
	//setTimeout(callme, 5000);
    //editJobReq(jobreqidscript);
	window.top.setReqMenuValue(returnVal);
	window.top.hidePopWin(true);
	//return false;
}

function discard(){
	//  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	 // if (doyou == true){
	   window.top.hidePopWin();
	  // } 
	}

	
	
function initfoscus(){
setTimeout ( "document.jobRequisitionForm.jobreqName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){
	var alertstr = "";
	var showalert=false;
		var tr = document.jobRequisitionForm.withtmpl[0].checked;
	var jobreqName1 =  document.jobRequisitionForm.jobreqName.value.trim();
	if(jobreqName1 == ""||jobreqName1 == null){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.req.name.alert",user1.getLocale())%><BR>";
		showalert = true;
	}
	var templateId1 =  document.jobRequisitionForm.templateId.value.trim();
	if(tr == true && templateId1 == 0){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("Requisition.temp.name.alert",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	document.getElementById('progressbartable1').style.display = 'inline'; 
	  document.jobRequisitionForm.action = "jobreq.do?method=copytemplatetojob&withtml="+tr;
   document.jobRequisitionForm.submit();
  
 //  alert(returnVal);
  //  window.top.hidePopWin(true);
	
   
	}


function opensearchjobreqtemplates(){
 // var url = "jsp/common/preload.jsp?reurl="+"lov.do?method=reqtmplselector";
  var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("lov.do?method=reqtmplselector");
  window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=850,height=600");
}

function messageret(){
	//window.location.reload();

			}
function editJobReq(jb_id){
	
	var url = "jobreq.do?method=editjobreq&jobreqId="+jb_id;
	//window.open(url, "SearchBudgetCode","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=800,height=600");
	GB_showFullScreen('createJobRequisition', url, messageret);
	//window.top.hidePopWin();
}

function init(){
       
	  document.getElementById('progressbartable1').style.display = 'none'; 

	initfoscus();

	hideundidetmpllistbox();
	   
		}


function hideundidetmpllistbox(){
	var tr = document.jobRequisitionForm.withtmpl[0].checked;
	if(tr == false){

		var selects = jobRequisitionForm.getElementsByTagName("select"); 
		for (i = 0; i < selects.length; i++) { 
		 selects[i].disabled = true; 
		} 

	}else{
	var selects = jobRequisitionForm.getElementsByTagName("select"); 
		for (i = 0; i < selects.length; i++) { 
		 selects[i].disabled = false; 
		} 
	}
}

</script>
<% if(errMsg != null){%>
<div>
<font color="red"><%=errMsg%></font>
	</div>
<%}%>
<body onload="init()">
<html:form action="/jobreq.do?method=copytemplatetojob" >


	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<!--  <td><%=Constant.getResourceStringValue("Requisition.createjobreq",user1.getLocale())%> </td>-->
			<td></td>
		</tr>
	  
		<tr>
			<td></td>
			<td>
			
			<input type="radio" name="withtmpl" value="Y" onClick="hideundidetmpllistbox()" /><%=Constant.getResourceStringValue("admin.with.template",user1.getLocale())%>&nbsp;&nbsp;
			<input type="radio" name="withtmpl" value="N" onClick="hideundidetmpllistbox()" checked=true/> <%=Constant.getResourceStringValue("admin.without.template",user1.getLocale())%> </td>
		</tr>


		<tr>
			<td><%=Constant.getResourceStringValue("Requisition.jobreqname",user1.getLocale())%>:</td>
			<td><html:text property="jobreqName" size="40" maxlength="500"/></td>
		</tr>
		
		

		<tr>
				<td><%=Constant.getResourceStringValue("Requisition.tempname",user1.getLocale())%><font color="red">*</font></td>
				<td>
				
			<html:select  property="templateId">
			<option value=""></option>
			<bean:define name="jobRequisitionForm" property="jobTemplateList" id="jobTemplateList" />
            <html:options collection="jobTemplateList" property="templateId"  labelProperty="templateName"/>
			</html:select>
				
			<span id="templateIdt"></span><a href="#" onClick="opensearchjobreqtemplates()"><img src="jsp/images/selector.gif" border="0"/></a>
				
				
				</td>
		</tr>
		
		
		
		<tr>
			<td><input type="button" name="login" value="<%=Constant.getResourceStringValue("Requisition.createjobreq",user1.getLocale())%>" onClick="savedata()" class="button"> </td>
			<td></td>
		</tr>

	</table>

<table id='progressbartable1' class="dialogBackground" width="100%" height="100%">

<tr>
    <td></td>
    <td  style="vertical-align:middle"><img src="jsp/images/loading.gif"></td>
    <td></td>
</tr>


</table>


</html:form>

</body>