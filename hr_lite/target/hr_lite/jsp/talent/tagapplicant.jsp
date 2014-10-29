	<%@ include file="../common/include.jsp" %>


	<%@ page import="com.bo.*"%>
	<%@ page import="java.util.*"%>
	<%@ page import="com.util.*"%>

	<bean:define id="sform" name="applicantForm" type="com.form.ApplicantForm" />

	<%
	//response.setHeader("Cache-Control", "no-cache");
			//response.setHeader("Pragma", "no-cache");
			//response.setIntHeader("Expires", 0);
	%>
	
	  <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>
	
	<br>

 <style>
span1{color:#ff0000;}
</style>

<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
	<script language="javascript">
function closewindow(){
	  parent.parent.GB_hide();
}

	
	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		   parent.parent.GB_hide();
		   } 
		}

	function savedata(){

				
		  document.applicantForm.action = "applicant.do?method=addapplicanttag&applicantId=<%=sform.getApplicantId()%>&secureid=<%=sform.getUuid()%>";
	   document.applicantForm.submit();
	   //window.top.hidePopWin();
	   
		}




	</script>


	<body class="yui-skin-sam">
	
<%
String addapplicanttag = (String)request.getAttribute("addapplicanttag");
	
if(addapplicanttag != null && addapplicanttag.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color ="green"><%=Constant.getResourceStringValue("aquisition.applicant.tag_added_successfully_to_applicant",user1.getLocale())%></font>&nbsp;&nbsp;&nbsp;<!-- <a href="#" onClick="closewindow();return false;"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
			<td> </td>
		</tr>
		
	</table>
</div>

<%}else {%>


<html:form action="/applicant.do?method=addapplicanttag">
	<div align="left">
		<table border="0" width="100%">
		<font color = red ><html:errors /> </font>
			<tr>
				<td><%=Constant.getResourceStringValue("aquisition.applicant.tag",user1.getLocale())%></td>
				<td>
				<html:select  property="tagId" >
			<bean:define name="applicantForm" property="tagList" id="tagList" />
            <html:options collection="tagList" property="tagId"  labelProperty="tagName"/>
			</html:select>
			</td>
			</tr>
			<tr>
				<td><%=Constant.getResourceStringValue("hr.comment",user1.getLocale())%></td>
				<td>
				<html:textarea property="comment" cols="50" rows="4"/>
				
			</td>
			</tr>
			</table>
			<br>
			<table>
			<tr>
				<td>
				
				
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()">
			
				
				 <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>"     onClick="discard()"></td>
			</tr>
	</table>
</div>
	</html:form>

<%}%>
