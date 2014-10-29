<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>



<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>

<style>
span1{color:#ff0000;}
</style>


<bean:define id="aform" name="declinedResonsForm" type="com.form.DeclinedResonsForm" /> 


<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		//  self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}
function closewindow(){
	// self.parent.location.reload();
	 parent.parent.GB_hide();
}
function init(){
setTimeout ( "document.declinedResonsForm.offerdecliedreasonName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){
	var alertstr = "";
	var showalert=false;
		
	var name=document.declinedResonsForm.offerdecliedreasonName.value.trim();
	var desc = document.declinedResonsForm.offerdecliedreasonDesc.value.trim();
			
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.declinecancelreason.declined_reason_name_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobGradeDesc_is_required",user1.getLocale())%><br>";
		showalert = true;
		}

	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}	
 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.declinedResonsForm.action = "declinedresonslov.do?method=saveDeclineReasons";
 	  document.declinedResonsForm.submit();

 
	}

function updatedata(){
	var alertstr = "";
	var showalert=false;
	
	var name=document.declinedResonsForm.offerdecliedreasonName.value.trim();
	var desc = document.declinedResonsForm.offerdecliedreasonDesc.value.trim();
	

			
	if(name == "" || name == null){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.declinecancelreason.declined_reason_name_mandatory",user1.getLocale())%><br>";
		showalert = true;
		}
	if(desc == "" || desc == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.JobGradeDesc_is_required",user1.getLocale())%><br>";
		showalert = true;
		}

	if(desc.length > 500){
		alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.Designation.DesignationDesc",user1.getLocale())%>"+" <%=Constant.getResourceStringValue("hr.Validation.sizeislong",user1.getLocale())%><br>";
		showalert = true;
	}	
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.declinedResonsForm.action = "declinedresonslov.do?method=updateDeclineReasons&declinedreasonid="+'<bean:write name="declinedResonsForm" property="offerdeclinedreasonId"/>';
      document.declinedResonsForm.submit();

 
	}
function deletedata(){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  document.declinedResonsForm.action = "declinedresonslov.do?method=updateDeclineReasons&delete=yes&declinedreasonid="+'<bean:write name="declinedResonsForm" property="offerdeclinedreasonId"/>';

		  document.declinedResonsForm.submit();
	  }
	
}
</script>

<%
String savedeclinedreason = (String)request.getAttribute("savedeclinedreason");
	
if(savedeclinedreason != null && savedeclinedreason.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.declinecancelreason.save",user1.getLocale())%></font></td>
			<td><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updateddeclinedreason = (String)request.getAttribute("updateddeclinedreason");
	
if(updateddeclinedreason != null && updateddeclinedreason.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.declinecancelreason.update",user1.getLocale())%></font></td>
			<td><!--   <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a>--></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String deletedeclinedreason = (String)request.getAttribute("deletedeclinedreason");
	
if(deletedeclinedreason != null && deletedeclinedreason.equals("yes")){
%>
<div align="center" class="msg">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="white"><%=Constant.getResourceStringValue("admin.declinecancelreason.delete",user1.getLocale())%></font></td>
			<td> <!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a>--></td>
		</tr>
		
	</table>
</div>
<%}else{%>	
<body class="yui-skin-sam" onload="init()">

<html:form action="/declinedresonslov.do?method=saveDeclinedResons">


<div align="center" class="div">

	

	<table border="0" width="100%">

	<tr>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.declinecancelreason.declined_reason_name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="offerdecliedreasonName" size="60" maxlength="300"/></td>
		</tr>
		
		
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.declinecancelreason.declined_reason_desc",user1.getLocale())%><font color="red">*</font></td>
			<td><html:textarea property="offerdecliedreasonDesc" cols="45" rows="5"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.declinecancelreason.reason_type",user1.getLocale())%><font color="red">*</font></td>
			<td>
			<html:select  property="resonType">
			<bean:define name="declinedResonsForm" property="reasonTypeList" id="reasonTypeList" />
            <html:options collection="reasonTypeList" property="key"  labelProperty="value"/>
			</html:select>

			</td>
		</tr>
		


	</table>
		<table border="0" width="100%">
		<tr>
			<td>
			<%if (aform.getOfferdeclinedreasonId()!= 0){%>

				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%}else{%>
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
		
		</table>
		
</div>

</html:form>
	
</body>

		<%}%>