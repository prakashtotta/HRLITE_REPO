<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<%
////response.setHeader("Cache-Control", "no-cache");
//		//response.setHeader("Pragma", "no-cache");
	//	//response.setIntHeader("Expires", 0);
%>
<style>
span1{color:#ff0000;}
</style>
<html>
<bean:define id="cform" name="groupCharForm" type="com.form.GroupCharForm" />
<script language="javascript">
var returnVal = "something11";
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		 // self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}
  
function closewindow2(){
	// self.parent.location.reload();
	 parent.parent.GB_hide();

	  
	}
	function init(){
setTimeout ( "document.groupCharForm.groupCharName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){

	var alertstr = "";
	var showalert=false;
		
	var name = document.groupCharForm.groupCharName.value.trim();
			
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
	var compchar = document.groupCharForm.charids.value.trim();
	if(compchar == 0){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Competencies_is_mandatory",user1.getLocale())%><BR>";
		showalert = true;
	}
	if(document.groupCharForm.groupCharDesc.value.length > 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.groupCharForm.action = "groupchar.do?method=saveGroupChar";
   document.groupCharForm.submit();
  
   
	}


function updatedata(){
var alertstr = "";
	var showalert=false;
		
	var name = document.groupCharForm.groupCharName.value.trim();
			
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Name_is_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
	//var compchar = document.groupCharForm.charids.value.trim();
	//if(compchar == 0){
	//	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Competencies_is_mandatory",user1.getLocale())%><BR>";
	//	showalert = true;
	//}
	if(document.groupCharForm.groupCharDesc.value.length > 500 ){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Desc_exceeded_500_characters",user1.getLocale())%><BR>";
		showalert = true;
	}
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.groupCharForm.action = "groupchar.do?method=updateGroupChar&groupCharId=<%=cform.getGroupCharId()%>";
   document.groupCharForm.submit();
  
   
	}

function deletedata(){
	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>)");
	  if (doyou == true){
	  document.groupCharForm.action = "groupchar.do?method=deleteGroupChar&groupCharId=<%=cform.getGroupCharId()%>";
   document.groupCharForm.submit();
	  }
  
   
	}



function opensearch(){
 var url = "jsp/emp/temp.jsp?reurl="+encodeURIComponent("scheduleInterview.do?method=addevaluationcriteria");
  window.open(url, "SearchEvaluationCriteria","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=400");
 //window.open("scheduleInterview.do?method=addevaluationcriteria", "SearchEvaluationCriteria","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=400");
}

</script>
<body class="yui-skin-sam" onLoad="init()" >
<%
String savedgroupchars = (String)request.getAttribute("savedgroupchars");
	
if(savedgroupchars != null && savedgroupchars.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.competenciesgroup.addedmsg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<%
String updatedgroupchars = (String)request.getAttribute("updatedgroupchars");
	
if(updatedgroupchars != null && updatedgroupchars.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.competenciesgroup.updatemsg",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}%>

<%
String deletegroupchars = (String)request.getAttribute("deletegroupchars");
	
if(deletegroupchars != null && deletegroupchars.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.competenciesgroup.deletemsg",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow2()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></td>
		</tr>
		
	</table>
</div>

<%}else{%>

<br>
<br>
<html:form action="/groupchar.do?method=saveChar" >




<div align="center" class="div">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td></td>
			<td></td>
		</tr>
	  
		<tr>
			<td><%=Constant.getResourceStringValue("admin.competenciesgroup.CompetencygroupName",user1.getLocale())%><span1>*<span1></td>
			<td><html:text property="groupCharName" size="40" maxlength="500"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.competenciesgroup.desc",user1.getLocale())%></td>
			<td><html:textarea property="groupCharDesc" cols="30" rows="5" /></td>
		</tr>
  
		<%
		String deptvalue ="<span id=\"evaluationcriteria\">";
        if(cform.getCharList() != null && cform.getCharList().size()>0){

   String deptpurl = "";

for(int i=0;i<cform.getCharList().size();i++){
	Characteristic ch = (Characteristic)cform.getCharList().get(i);
	deptpurl = deptpurl + ch.getCharName() + "<br>";

}

 deptvalue = "<span id=\"evaluationcriteria\">"+deptpurl+"</span>";
		}

		%>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.competenciesgroup.comp",user1.getLocale())%><span1>*<span1></td>
			<td>
			<input type="hidden" name="charids" >
			<a href="#" onClick="opensearch()"><img src="jsp/images/selector.gif" border="0"/></a><br>
			<%=deptvalue%>
			
			</td>
		</tr>
		
		
		<tr>
			<td>
			<% if(cform.getGroupCharId() > 0){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%} else {%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
				<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>

	</table>
</div>

</html:form>

<%}%>