<%@ page import="com.resources.*"%>
<link rel="stylesheet" type="text/css" href="jsp/autosuggest/wick.css" />
<%
System.out.println("satyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
String userbuffer = Constant.getUserBuffer();
%>
 <script type="text/javascript">
collection =
[
<%=userbuffer%>
];
 </script>
<script type="text/javascript" language="JavaScript" src="jsp/autosuggest/wick.js"></script> <!-- WICK STEP 3: INSERT WICK LOGIC -->
<script type="text/javascript" language="JavaScript">
function checkForm() {
answer = true;
if (siw && siw.selectingSomething)
	answer = false;
return answer;
}//
</script>

<%
System.out.println("satyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
%>
