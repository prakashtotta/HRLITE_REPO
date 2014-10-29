<%
    //int cpYear = Calendar.getInstance().get(Calendar.YEAR);

%>
<script language="javascript">
function addfeedback(){
	//alert("hi...");
	var url="feedbacks.do?method=addFeedbackScr";
	window.open(url, "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=300");
	
}
function aboutproduct(){
	//alert("hi...");
	var url="jsp/common/browsercompatibility.html";
	window.open(url, "SearchApprover","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=700,height=300");
	
}
</script>


<table border="0"  cellpadding="0" cellspacing="0" width="100%">

<tr  valign="right">
<td align="left" valign="buttom" width="40%">
<font size="1"><a href="#" onclick="aboutproduct();return false"><font color="blue" size="2">Browser compatibility</font></a></font>
					<!-- GoStats Simple HTML Based Code -->
<a target="_blank" title="site tracking" href="http://gostats.com"><img alt="site tracking" 
src="http://c3.gostats.com/bin/count/a_362124/t_7/i_3/z_0/show_hits/counter.png" 
style="border-width:0" /></a>
<!-- End GoStats Simple HTML Based Code -->
</td>
	<td align="right" valign="bottom" width="46%"><a href="#" onclick="addfeedback();return false"><font color="blue" size="2">Add your feedback</font></a></td>
    <td align="right" valign="bottom">

<font size="2">powered by </font><a href="http://www.hires360.com" target="new"><img src="jsp/jobboard/images/logo_green_small.png" border="0"></a>
    </td>
  </tr>
  <!--

  <tr bgcolor="black" valign="center">

    <td align="center">


            <span class="navwhite">&nbsp;
            <a href="http://www.abc.com" class="navwhite">Home</a>&nbsp;|&nbsp;
            </span>
    </td>
  </tr>
  <tr>

  	<td align="center" valign="top" class="ModuleText">
  	 Please review our online <a href="#" target="_new">Privacy Policy</a> and <a href="#" target="_new">Terms of Use.</a><br>
  	&#169; Copyright 2012-2013 Chiteswar Solutions. All rights reserved. We welcome your comments. Send e-mail to&nbsp;<a href="mailto:info@chiteswarsolutions.com"></a>info@chiteswarsolutions.com</td>
  </tr>
-->
</table>



<script language="javascript">	
String.prototype.trim = function() { 
    return this.replace(/^\s+|\s+$/g,""); 
} 
String.prototype.ltrim = function() { 
    return this.replace(/^\s+/,""); 
} 
String.prototype.rtrim = function() { 
    return this.replace(/\s+$/,""); 
} 
</script>


