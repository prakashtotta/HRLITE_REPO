
<%@ include file="../common/autosuggest.jsp" %>





<table border="0" cellpadding="0" cellspacing="0" width="100%">
<%
	
%>


<tr bgcolor="#000066" valign="top">
      <td><a href="#"><img valign="top" align="left" src="jsp/images/logo.png" border="0" height="50" width="200"></a></td>
<td align="right">
<body>

<form method="get" name="reqautoselect" onSubmit="return false">
<select name="reqauto">
  <option value="REQ_NO">Req No</option>
  <option value="REQ_NAME">Req Name</option>
  <option value="REQ_CODE">Job Code</option>
</select>
<input size="20" type="text" id="reqinput" value="Requisition search"  onclick="this.value='';"  /> 

</form>

</body>
</td>
<td width="25">
<form method="POST" name="applicantsearch" action="applicant.do?method=searchapplicantheader">
<input size="15" type="text" name="appid" value="Applicant no search"  onclick="this.value='';"  /> 

</form>

</td>
</tr>


</table>
<script type="text/javascript">
	

	var options_xml = {
		script:"jsp/talent/getRequitionData.jsp?cri="+document.reqautoselect.reqauto.value+"&",
		varname:"input",
	    cache:false,
		delay:"500",
		timeout:"2500",
		callback: function (obj) { location.href="jobreq.do?method=requistionapplicantlist&state=0&requistionId="+obj.id; }
	};
	var as_xml = new bsn.AutoSuggest('reqinput', options_xml);
</script>

<%@ include file="../common/focusTextTextArea.jsp" %>



        