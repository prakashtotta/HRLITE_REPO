<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<html>
<bean:define id="aform" name="emailTemplatesForm" type="com.form.EmailTemplatesForm" />



<style type="text/css">


<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
fieldset {
	width: 750px;
	border: 1px solid #999;
	padding: 10px;
}

fieldset1 {
	width: 750px;
	border: 1px solid #999;
	
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



	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {  
	margin:0;
	padding:0;
}
/* Clear calendar's float, using dialog inbuilt form element */
    #container .bd form {
        clear:left;
    }

    /* Have calendar squeeze upto bd bounding box */
    #container .bd {
        padding:0;
    }

    #container .hd {
        text-align:left;
    }

    /* Center buttons in the footer */
    #container .ft .button-group {
        text-align:center;
    }

    /* Prevent border-collapse:collapse from bleeding through in IE6, IE7 */
    #container_c.yui-overlay-hidden table {
        *display:none;
    }

    /* Remove calendar's border and set padding in ems instead of px, so we can specify an width in ems for the container */
    #cal {
        border:none;
        padding:1em;
    }

    /* Datefield look/feel */
    .datefield {
        position:relative;
        top:10px;
        left:10px;
        white-space:nowrap;
        border:1px solid black;
        background-color:#eee;
        width:25em;
        padding:5px;
    }

    .datefield input,
    .datefield button,
    .datefield label  {
        vertical-align:middle;
    }

    .datefield label  {
        font-weight:bold;
    }

    .datefield input  {
        width:15em;
    }

    .datefield button  {
        padding:0 5px 0 5px;
        margin-left:2px;
    }

    .datefield button img {
        padding:0;
        margin:0;
        vertical-align:middle;
    }

    /* Example box */
    .box {
        position:relative;
        height:30em;
    }
	</style>

<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		 // self.parent.location.reload();
			 parent.parent.GB_hide();
	   } 
	}



	function updatedata(){
	/*var d = document.emailTemplatesForm.editor.value;
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
	 	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }*/
	myEditor.get('textarea').value
	alert(d);
	//  document.emailTemplatesForm.action = "emailtemplate.do?method=updateemailtemplate&emailtemplateId="+'<bean:write name="emailTemplatesForm" property="emailtemplateId"/>';
  // document.emailTemplatesForm.submit();
      
	}

function savas(){
		var alertstr = "";
	var showalert=false;
		
	 
	var name=document.emailTemplatesForm.emailtemplatename.value.trim();
	var subject=document.emailTemplatesForm.emailSubject.value.trim();
			
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.EmailTempName_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(subject == "" || subject == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.EmailTempSub_required",user1.getLocale())%><br>";
		showalert = true;
		}
	 	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	 document.emailTemplatesForm.action = "emailtemplate.do?method=saveas&emailtemplateId="+'<bean:write name="emailTemplatesForm" property="emailtemplateId"/>';
  document.emailTemplatesForm.submit();
      
	}

function deletetemplate(){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  document.emailTemplatesForm.action = "emailtemplate.do?method=deletetemplate&emailtemplateId="+'<bean:write name="emailTemplatesForm" property="emailtemplateId"/>';
	//  document.emailTemplatesForm.submit();
	//self.parent.location.reload();
	self.close();
	   } 
	
}

function closewindow(){
	//self.parent.location.reload();
	 parent.parent.GB_hide();
	  
	}
function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		 // self.parent.location.reload();
	 parent.parent.GB_hide();
	 
	   } 
	}
function init(){
	setTimeout ( "document.emailTemplatesForm.emailtemplatename.focus(); ", 200 );

	}
</script>


<div id="status"></div>

<%
String emailtemplatesave = (String)request.getAttribute("emailtemplatesave");
if(emailtemplatesave != null && emailtemplatesave.equals("yes")){
%>

<span id="saveast">

</span>
<%}else{%>
<span id="saveast"></span>
<%}%>
<body class="yui-skin-sam" onload="init()">
<html:form action="/emailtemplate.do?method=saveemailtemplate" >

<div align="center" class="div">
	


	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<!-- <td><%=Constant.getResourceStringValue("admin.EmailTemplate.Emailtemplate",user1.getLocale())%></td> -->
			<td></td>
		</tr>
	 
		<tr>
			<td><%=Constant.getResourceStringValue("admin.EmailTemplate.name",user1.getLocale())%></td>
			<td><html:text property="emailtemplatename" size="60" maxlength="500"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.EmailTemplate.sub",user1.getLocale())%></td>
			<td><html:text property="emailSubject" size="60" maxlength="500"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.EmailTemplate.Template",user1.getLocale())%></td>
			<td>
			
			<textarea id="editor" name="editor" rows="20" cols="75">
           <%=(aform.getEmailtemplateData() == null)?"":aform.getEmailtemplateData()%>
            </textarea>
	  
			
			</td>
		</tr>


		<tr>
			<td>
			
			
			<button type="button" id="submitEditor" class="button"><%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%></button>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			</td>
			<td></td>
		</tr>

	</table>
</div>

</html:form>


<script>
(function() {
    var Dom = YAHOO.util.Dom,
        Event = YAHOO.util.Event,
        status = null;
        
        var handleSuccess = function(o) {
			closewindow();
			document.getElementById ("saveast").innerHTML="";
            status.innerHTML = '<font color=green><%=Constant.getResourceStringValue("admin.EmailTemplate.savemsg",user1.getLocale())%></font>  <a href="#" onclick="closewindow()"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>';
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
        YAHOO.log('Button clicked, initiate transaction', 'info', 'example');
        Event.stopEvent(ev);
        myEditor.saveHTML();
        window.setTimeout(function() {
            var sUrl = "emailtemplate.do?method=saveemailtemplate";
			var securityparam = "&csrfcode="+document.emailTemplatesForm["csrfcode"].value;
            var data = 'templatename='+ document.emailTemplatesForm.emailtemplatename.value +'&emailsubject='+document.emailTemplatesForm.emailSubject.value+'&editor_data=' + encodeURIComponent(myEditor.get('textarea').value)+securityparam;
            var request = YAHOO.util.Connect.asyncRequest('POST', sUrl, callback, data);
        }, 200);
    });


    Event.onDOMReady(function() {
        status = Dom.get('status');
		
    });
})();
</script>



</body>