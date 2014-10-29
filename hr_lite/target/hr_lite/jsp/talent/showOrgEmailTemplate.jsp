<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String fn_name = (String)request.getAttribute("fn_name");
long emailtemplateid = (Long)request.getAttribute("emailtemplateid");

System.out.println("fn_name >> "+fn_name);
%>
<html>
<bean:define id="aform" name="emailTemplatesForm" type="com.form.EmailTemplatesForm" />

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
                ed.selection.setContent('');
            }
        });
    }
});
</script>
<script language="javascript">



function saveemailtemplate(){
	//alert("hello");
	
	var alertstr = "";
	var showalert=false;
		
	 
	var name=document.emailTemplatesForm.emailtemplatename.value.trim();
	var subject=document.emailTemplatesForm.emailSubject.value.trim();
	var emailmessage = document.emailTemplatesForm.emailmessage.value;
			
	if(name == "" || name == null){
     	alertstr = alertstr + "Email Template Name is required.<br>";
		showalert = true;
		}
	if(subject == "" || subject == null){
     	alertstr = alertstr + "Email Template subject is required.<br>";
		showalert = true;
		}

	if(emailmessage.length > 10000 ){
     	alertstr = alertstr + "Email Template size is too long.<br>";
     	showalert = true;
		}
	 if (showalert){
     	alert(alertstr);
        return false;

       // document.emailTemplatesForm.action="emailtemplate.do?method=updateemailtemplate";
       // document.emailTemplatesForm.submit();
	
}

function init(){
	setTimeout ( "document.emailTemplatesForm.emailtemplatename.focus(); ", 200 );

	}
function PreviewMessage(emailtemplateid){
	var alertstr = "";
	  var showalert=false;
		var messageString =document.emailTemplatesForm.editor.value;

	  if(messageString == "" || messageString == null){
	     	alertstr = alertstr + "<%=Constant.getResourceStringValue("user.massemail.emptyMessage",user1.getLocale())%><br>";
			showalert = true;
		}
	  if (showalert){
	     	alert(alertstr);
	        return false;
	          }
	var url ="emailtemplate.do?method=previewMessage&emailtemplateid="+emailtemplateid;
	window.open(url, "PreviewMessage","location=0,status=0,scrollbars=1,menubar=0,resizable=1,width=600,height=600");

}
</script>


<div id="status"></div>

<body class="yui-skin-sam" onload="init()">
<html:form action="/emailtemplate.do?method=updateemailtemplate">


	

		<%

		String emailtemplatedeleted = (String)request.getAttribute("emailtemplatedeleted");
	
		if(emailtemplatedeleted != null && emailtemplatedeleted.equals("yes")){
		%>
		<font color=green><%=Constant.getResourceStringValue("admin.EmailTemplate.deletemsg",user1.getLocale())%> </font> 
		<%}
		%>
		<div class="div">
    <table border="0" width="1000">
	<tr>
	<td>	<font color = red ><html:errors /> </font>

			<table border="0" width="700">
			
			<tr>
					<!-- <td><%=Constant.getResourceStringValue("admin.EmailTemplate.Emailtemplate",user1.getLocale())%></td> -->
					<td></td>
				</tr>
			 
				<tr>
					<td width="100"><%=Constant.getResourceStringValue("admin.EmailTemplate.Templatename",user1.getLocale())%><font color="red">*</font></td>
					<td>
					<html:text property="emailtemplatename" size="80" maxlength="500"/></td>
				</tr>
				<tr></tr><tr></tr><tr></tr><tr></tr>
				<tr>
					<td><%=Constant.getResourceStringValue("admin.EmailTemplate.Template_Subject",user1.getLocale())%><font color="red">*</font></td>
					<td>
					<html:text property="emailSubject" size="80" maxlength="500"/></td>
				</tr>
				<tr></tr><tr></tr><tr></tr><tr></tr>
				<tr>
					<td><%=Constant.getResourceStringValue("admin.EmailTemplate.Template",user1.getLocale())%></td>
					<td >
					<html:textarea property="emailmessage" rows="20" cols="90" value="<%=aform.getEmailtemplateData()%>"> </html:textarea>
		
			  
					
					</td>
				</tr>
		
			</table>
</td>
<% if(fn_name != null){%>
<td width="200">
<table >
		<tr>
			<td valign="top">
			<b><%=Constant.getResourceStringValue("admin.EmailTemplate.available.tags",user1.getLocale())%></b>
			</td>
		</tr>
		<tr>
			<td >
			<%=com.util.MergeUtil.getAvailableTags(fn_name)%>
			</td>
		</tr>

		
</table>
</td>

<%}%>
</tr>
</table>

	
<table>

		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>


			<td><input type="Button" title="<%=Constant.getResourceStringValue("user.massemail.preview",user1.getLocale())%>" value="<%=Constant.getResourceStringValue("user.massemail.preview",user1.getLocale())%>" onclick="PreviewMessage('<%=emailtemplateid%>')"></td>

			<td>
			<button type="button" onclick="saveemailtemplate()" class="button"><%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%></button>&nbsp;
			</td>
			<td></td>
		</tr>
</table>

</html:form>


<script>
(function() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event,
        status = null;
        
        var handleSuccess = function(o) {
			document.getElementById ("saveast").innerHTML="";
            status.innerHTML = '<font color=green><%=Constant.getResourceStringValue("validation.TempUpdateSuccessfully",user1.getLocale())%></font>  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>';
           // myEditor.setEditorHTML(data.Results.data);
           
        }
        var handleFailure = function(o) {
           
        }

        var callback = {
            success: handleSuccess,
            failure: handleFailure
        };

    
    YAHOO.log('Create Button Control (#toggleEditor)', 'info', 'example');
    var _button = new YAHOO.widget.Button('submitEditor');

    var myConfig = {
        height: '470px',
        width: '1000px',
        animate: true,
        dompath: true
    };

    YAHOO.log('Create the Editor..', 'info', 'example');
    var myEditor = new YAHOO.widget.Editor('editor', myConfig);
    myEditor.render();

    _button.on('click', function(ev) {
    	var d = document.emailTemplatesForm.editor.value;
    	var alertstr = "";
    	var showalert=false;
    		
    	 
    	var name=document.emailTemplatesForm.emailtemplatename.value.trim();
    	var subject=document.emailTemplatesForm.emailSubject.value.trim();
    			
    	if(name == "" || name == null){
         	alertstr = alertstr + "Email Template Name is required.<br>";
    		showalert = true;
    		}
    	if(subject == "" || subject == null){
         	alertstr = alertstr + "Email Template subject is required.<br>";
    		showalert = true;
    		}
    	//alert(d.length);
    	if(d.length > 10000 ){
         	alertstr = alertstr + "Email Template size is too long.<br>";
         	showalert = true;
    		}
    	 if (showalert){
         	alert(alertstr);
            return false;
             }else{

		        YAHOO.log('Button clicked, initiate transaction', 'info', 'example');
		        Event.stopEvent(ev);
		        myEditor.saveHTML();
		        window.setTimeout(function() {
		            var sUrl = "emailtemplate.do?method=updateemailtemplate";
					var securityparam = "&csrfcode="+document.emailTemplatesForm["csrfcode"].value;
		            var data = 'emailtemplateId='+ <%=aform.getEmailtemplateId()%> + '&templatename='+ document.emailTemplatesForm.emailtemplatename.value +'&emailsubject='+document.emailTemplatesForm.emailSubject.value+'&editor_data=' + encodeURIComponent(myEditor.get('textarea').value)+securityparam;
		            var request = YAHOO.util.Connect.asyncRequest('POST', sUrl, callback, data);
		        }, 200);
           }
    });


    Event.onDOMReady(function() {
        status = Dom.get('status');
		
    });
})();
</script>



</body>