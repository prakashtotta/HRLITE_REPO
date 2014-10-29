


<script>
function redirect() {
	window.location ='/VrtAdmin/VrtAdmin/ManageAssets.jsp?c=LOGIN';
}
</script>

<form name="login" method="post" action="/controller?" >
<div  class="div">

<table border="0" cellpadding="0" cellspacing="0">
 <tr><td colspan="5"><img src="jsp/images/spacer.gif" width="20"></td></tr>
 <tr> <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
      <td width="100" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="240" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="240" height="1"></td>
      <td width="20" bgcolor="#999999"><img src="jsp/images/spacer.gif" width="20" height="1"></td>
      <td width="1" bgcolor="#999999" rowspan="10"><img src="jsp/images/spacer.gif" width="1" height="1"></td>
 </tr>

 <tr><td colspan="5" class="BodyToolboxHeader">&nbsp;&nbsp;Add Message</td></tr>
 <tr>
 <td colspan="5"  background="jsp/images/tool_dots.gif"></td>
 </tr>

<tr>
<td>
<img src="jsp/images/spacer.gif" border="0" width="20">
</td>

<%	
	
%>
<td colspan="4">

	<table border="0" bordercolor="#999999" cellpadding="1" cellspacing="1">

	<tr><td ><b>Name:</b></td>
	    <td class="BodyText"><input type="text" name="rr" MAXLENGTH="100" value=""></td>
	</tr>
	<tr><td><b>Value:</b></td>
	    <td class="BodyText"><TEXTAREA NAME="rr" ROWS=4 COLS=40 value="" >hh</TEXTAREA></td>
	</tr>


	<tr><td>&nbsp;</td><td align="right">
	
	

	</td>
	</tr>

	<tr><td colspan="2">&nbsp;</td></tr>

	</table>
	<input type="hidden" name="tt" value="Save">
	<input type="hidden" name="tt" value="MGC">
</td>
</tr>

<tr> <td colspan="5" bgcolor="#999999"><img src="jsp/images/spacer.gif"  height="1"></td></tr>


</table>
</div>
</form>
