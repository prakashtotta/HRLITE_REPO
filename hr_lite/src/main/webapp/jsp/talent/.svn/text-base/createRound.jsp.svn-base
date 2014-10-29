<%@ include file="../common/include.jsp" %>
<%
////response.setHeader("Cache-Control", "no-cache");
//		//response.setHeader("Pragma", "no-cache");
	//	//response.setIntHeader("Expires", 0);
%>
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
%>
<html>

<script language="javascript">
var returnVal = "Created Successfully!!!";
function discard(){
	  var doyou = confirm("Are you confirm Discard the changes? (OK = Yes   Cancel = No)");
	  if (doyou == true){
	   window.top.hidePopWin();
	   } 
	}
function init(){
setTimeout ( "document.roundForm.roundName.focus(); ", 200 );
//document.roleForm.roleCode.focus();
}

function savedata(){
	
	  document.roundForm.action = "round.do?method=saveRound";
   document.roundForm.submit();
  
 //  alert(returnVal);
    window.top.hidePopWin(true);
	
   
	}



</script>

<html:form action="/round.do?method=saveRound" >
<body onload="init()">

<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td><%=Constant.getResourceStringValue("applicant.Round.Create_Round",user1.getLocale())%></td>
			<td></td>
		</tr>
	  
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.name",user1.getLocale())%> :</td>
			<td><html:text property="roundName" size="40" maxlength="500"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Competencies.desc",user1.getLocale())%> :</td>
			<td><html:textarea property="roundDesc" cols="30" rows="5" /></td>
		</tr>
		
		
		<tr>
			<td><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>

	</table>
</div>

</html:form>