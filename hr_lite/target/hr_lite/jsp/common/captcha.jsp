 <%//response.addHeader("Cache-Control","no-cache");%> 
 <%//response.addHeader("Expires","-1");%> 
 <%//response.addHeader("Pragma","no-cache");%> 




<div style="background: #f3f3f3; text-align:left"><a href="#" rel="toggle[captcha]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> Captcha</div>


<div>
<table border="0" width="<%=width%>">

<tr>
			<td width="40%" valign="top"><%=Constant.getResourceStringValue("admin.captcha.display.note",locale)%>
			
			</td>
			<td>
<iframe frameborder="0" height="50px" width="180px" src="jsp/imagecreator/captcha.jsp"></iframe>

 </td>
		</tr>
<tr>
			<td width="40%" valign="top">		
			</td>
			<td>
		<input type="text" class="textdynamic" name="captcharan" value="">
		</td>
</tr>

</table>
</div>