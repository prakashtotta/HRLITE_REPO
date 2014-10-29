<%@ include file="../common/include.jsp" %>
<%@ page import="com.bean.*"%>
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
System.out.println("............... 1 >>");
%>

<html:html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">

<style type="text/css">

.initial2{font-weight:bold;background-color:#c00;color:#fff;}

</style>
</head>
<bean:define id="massemailform" name="massEmailForm" type="com.form.MassEmailForm" />

<script language="javascript">
function change(e, color){
    var el=window.event? event.srcElement: e.target
    if (el.tagName=="INPUT"&&el.type=="button")
    el.style.backgroundColor=color
}

	</script>

<html:form action="/massemail?method=logon" >



			
<span id="applicants">
<table>
<tr>
<td>

			



<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">
<tr >

<td valign="top" width="500" bgcolor="#B2D2FF">
<table border="0" cellpadding="0" cellspacing="0">
 

 <tr> <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="240" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="400" height="1"></td>
      <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
 </tr>




<tr>
<td>
<img src="jsp/images/spacer.gif" border="0" width="20">
</td>
<td colspan="4">

<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1" >



<tr valign="center">
<td > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i><font color="red"><%=Constant.getResourceStringValue("user.massemail.matching_applicants_appear_here",user1.getLocale())%></font></i>


</td>

</tr>
</table>
	
	
</td>
</tr>

<tr><td colspan="5" class="BodyToolboxHeader">&nbsp;&nbsp;</td></tr>
<tr> <td colspan="5" bgcolor="#999999"><img src="jsp/images/spacer.gif"  height="1"></td></tr>
</table>
</td>

</tr>
</table>

<td valign="top">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="sentemail" disabled="true" value="<%=Constant.getResourceStringValue("user.massemail..send_Batch_Message",user1.getLocale())%>" border="0" alt="<%=Constant.getResourceStringValue("user.massemail..send_Batch_Message",user1.getLocale())%>" title="<%=Constant.getResourceStringValue("user.massemail..send_Batch_Message",user1.getLocale())%>" height="20"  width="19"  class="initial2"  onMouseover="change(event, 'darksalmon')" onMouseout="change(event, '#c00')" />

</td>
</tr>
</table>

</span>


			
</html:form>
</html:html>



