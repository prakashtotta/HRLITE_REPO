<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<%
////response.setHeader("Cache-Control", "no-cache");
	//	//response.setHeader("Pragma", "no-cache");
	//	//response.setIntHeader("Expires", 0);
%>
<style>
span1{color:#ff0000;}
</style>
<bean:define id="cform" name="evaluationTemplateForm" type="com.form.EvaluationTemplateForm" />
<html>

<script language="javascript">
//var returnVal = "something11";


function closewindow2(){
	// self.parent.location.reload();
	 parent.parent.GB_hide();

	  
	}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		 // self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	} 
function closewindow(){
	// self.parent.location.reload();
	 parent.parent.GB_hide();
}
function init(){
setTimeout ( "document.evaluationTemplateForm.evTmplName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){

	var alertstr = "";
	var showalert=false;
		
	var name = document.evaluationTemplateForm.evTmplName.value.trim();
			
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		var compchar = document.evaluationTemplateForm.charids.value.trim();
	if(compchar == 0){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Compgroup_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
	}
if(document.evaluationTemplateForm.evTmplDesc.value.length > 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.evaluationTemplateForm.action = "evtmpl.do?method=saveEvTmpl";
   document.evaluationTemplateForm.submit();
	
   
	}

function updatedata(){

	var alertstr = "";
	var showalert=false;
		
	var name = document.evaluationTemplateForm.evTmplName.value.trim();
			
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
		//var compchar = document.evaluationTemplateForm.charids.value.trim();
		//if(compchar == 0){
		//	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Compgroup_is_mandatory",user1.getLocale())%><BR>";
		//	showalert = true;
		//}
if(document.evaluationTemplateForm.evTmplDesc.value.length > 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	
	  document.evaluationTemplateForm.action = "evtmpl.do?method=updateEvTemplate&evtmplid=<%=cform.getEvtmplId()%>";
   document.evaluationTemplateForm.submit();
	
   
	}

function deletedata(){
	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
	  document.evaluationTemplateForm.action = "evtmpl.do?method=deleteEvTemplate&evtmplid=<%=cform.getEvtmplId()%>";
   document.evaluationTemplateForm.submit();
	  }
  
   
	}
	

function opensearch(){
   var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("evtmpl.do?method=selectgroupchar");
  window.open(url, "SearchEvaluationCriteriaGroups","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

  //window.open("evtmpl.do?method=selectgroupchar", "SearchEvaluationCriteriaGroups","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");
}

</script>
<body class="yui-skin-sam" onLoad="init()">

<%
String templatesaved = (String)request.getAttribute("templatesaved");
	
if(templatesaved != null && templatesaved.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.EvaluationTemplate.savemsg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String UpdateTemplate = (String)request.getAttribute("UpdateTemplate");
	
if(UpdateTemplate != null && UpdateTemplate.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.EvaluationTemplate.updatemsg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String templatedeleted = (String)request.getAttribute("templatedeleted");
	
if(templatedeleted != null && templatedeleted.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.EvaluationTemplate.deletemsg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}else{%>

<html:form action="/evtmpl.do?method=saveChar" >


<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td></td>
			<td></td>
		</tr>
	  
		<tr>
			<td><%=Constant.getResourceStringValue("admin.EvaluationTemplate.evaluationsheet",user1.getLocale())%> <span1>*<span1></td>
			<td><html:text property="evTmplName" size="40" maxlength="500"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.EvaluationTemplate.desc",user1.getLocale())%></td>
			<td><html:textarea property="evTmplDesc" cols="50" rows="5" /></td>
		</tr>

				<%
		String deptvalue ="<span id=\"evaluationcriteria\">";
        if(cform.getGroupcharList() != null && cform.getGroupcharList().size()>0){

   String deptpurl = "";

for(int i=0;i<cform.getGroupcharList().size();i++){
	GroupCharacteristic ch = (GroupCharacteristic)cform.getGroupcharList().get(i);
	deptpurl = deptpurl + ch.getGroupCharName() + "<br>";

}

 deptvalue = "<span id=\"evaluationcriteria\">"+deptpurl+"</span>";
		}

		%>
        
		<tr>
			<td><%=Constant.getResourceStringValue("admin.EvaluationTemplate.Competencygroups",user1.getLocale())%><span1>*<span1></td>
			<td>
				<input type="hidden" name="charids" >
			<a href="#" onClick="opensearch()"><img src="jsp/images/selector.gif" border="0"/></a><br>
			<%=deptvalue%>
			
			</td>
		</tr>
		
		
		<tr>
			<td>
			
				<% if(cform.getEvtmplId() == 0){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<%} else {%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<%}%>
			<td></td>
		</tr>

	</table>
</div>

</html:form>

<%}%>