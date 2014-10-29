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
<html>

<script language="javascript">
var returnVal = "something11";
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
	   window.top.hidePopWin();
	   } 
	}

function savedata(){
	
	  document.selectionForm.action = "selection.do?method=saveSelection";
   document.selectionForm.submit();
  
 //  alert(returnVal);
    window.top.hidePopWin(true);
	
   
	}



</script>

<html:form action="/selection.do?method=saveSelection" >

<div align="center">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.Create_Selection_Cycle",user1.getLocale())%></td>
			<td></td>
		</tr>
	  
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.name",user1.getLocale())%> :</td>
			<td><html:text property="selName" size="40" maxlength="500"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.desc",user1.getLocale())%> :</td>
			<td><html:textarea property="selDesc" cols="30" rows="5" /></td>
		</tr>
		
		
		<tr>
			<td><input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>

	</table>
</div>

</html:form>