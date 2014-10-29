<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>


<bean:define id="form" name="scheduleInterviewForm" type="com.form.ScheduleInterviewForm" />
<%
String ststusmsg="";
String status = form.getStatus();
long applicantId = form.getApplicantId();
int eventype = form.getEventType();

System.out.println("eventype"+eventype);


%>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 600px;
	border: 1px solid #999;
	padding: 10px;
}

legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
#modelDescription {
	background: #eee;

	
}

</style>

<script language="javascript">





function closewindow(){
	        self.parent.location.reload();
	 	    window.top.hidePopWin();
	  
	}

	function discard(){
		  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
		  if (doyou == true){
		   window.top.hidePopWin();
		   } 
		}

	function savedata(){
   
		 document.scheduleInterviewForm.action = "scheduleInterview.do?method=holdsubmit&applicantId=<%=applicantId%>&eventype=<%=eventype%>";
	   document.scheduleInterviewForm.submit();
	
	   
		}
</script>

<script type="text/javascript" src="jsp/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript">
tinyMCE.init({
    mode : "textareas",
    theme : "advanced",
    theme_advanced_buttons1 : "mybutton,bold,italic,underline,separator,strikethrough,justifyleft,justifycenter,justifyright, justifyfull,bullist,numlist,undo,redo,link,unlink",
    theme_advanced_buttons2 : "",
    theme_advanced_buttons3 : "",
    theme_advanced_toolbar_location : "top",
    theme_advanced_toolbar_align : "left",
      plugins : 'inlinepopups',
    setup : function(ed) {
        // Display an alert onclick
        

        // Add a custom button
        ed.addButton('mybutton', {
            title : 'My button',
            image : 'jsp/jscripts/tiny_mce/example.gif',
            onclick : function() {
                ed.selection.setContent('<STRONG>Hello world!</STRONG>');
            }
        });
    }
});
</script>
<body>

<%
String interviwercommmented = (String)request.getAttribute("interviwercommmented");
	
if(interviwercommmented != null && interviwercommmented.equals("yes")){
%>
<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("aquisition.applicant.Applicant_added_to_hold_state_successfully",user1.getLocale())%></font></td>
			<td> </td>
		</tr>
		
	</table>
</div>

<%}else {%>
<html:form action="/scheduleInterview.do?method=holdsubmit" enctype="multipart/form-data">
	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<h3> <%=Constant.getResourceStringValue("aquisition.applicant.On_hold",user1.getLocale())%> </h3>







<tr>
				<td width="30%"><%=Constant.getResourceStringValue("Requisition.Comment",user1.getLocale())%></td>
				<td width="70%">
				<html:textarea property="interviewercomments" cols="60" rows="5"/>
				
</td>
			</tr>
			

			<tr>
				<td>
				
				
				
				<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.submit",user1.getLocale())%>" onClick="savedata()">
			
				 <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()"></td>
			</tr>

</table>
</html:form>
<%}%>
</body>