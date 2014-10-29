<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
String tagExisted = (String)request.getAttribute("tagExisted");
%>
<html>
<bean:define id="tform" name="tagsform" type="com.form.TagsForm" /> 

<style>
span1{color:#ff0000;}
</style>
<style type="text/css">
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
	legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
	</style>




<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
			 parent.parent.GB_hide();
	   } 
	}

function init(){
setTimeout ( "document.tagsform.tagName.focus(); ", 200 );
}

function savedata(){
	var alertstr = "";
	var showalert=false;	
	var name=document.tagsform.tagName.value.trim();
			
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.tags.createpage.mandatory.name",user1.getLocale())%><br>";
		showalert = true;
		}
	
	 if (showalert){
     	alert(alertstr);
        return false;
          }
		 
	  document.tagsform.action = "tags.do?method=saveTags";
      document.tagsform.submit();

 
	}

function updatedata(){
	
	var alertstr = "";
	var showalert=false;	
	var name=document.tagsform.tagName.value.trim();		
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.tags.createpage.mandatory.name",user1.getLocale())%><br>";
		showalert = true;
		}
	 
	 if (showalert){
     	alert(alertstr);
        return false;
          }
		 
	  document.tagsform.action = "tags.do?method=updateTags&id="+'<bean:write name="tagsform" property="tagId"/>';
 document.tagsform.submit();

 
	}
function deletedata(){

	 var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		  document.tagsform.action = "tags.do?method=deleteTags&tagid="+'<bean:write name="tagsform" property="tagId"/>';

		  document.tagsform.submit();
	  }
	
}

function closewindow(){
	parent.parent.GB_hide();

}
</script>

<%
String saveTags = (String)request.getAttribute("saveTags");
	
if(saveTags != null && saveTags.equals("yes")){
%>
<div class="msg" align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.tags.createpage.savedmessage",user1.getLocale())%></font>
			 <!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String updateTags = (String)request.getAttribute("updateTags");
	
if(updateTags != null && updateTags.equals("yes")){
%>
<div class="msg" align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.tags.createpage.updatedmessage",user1.getLocale())%></font>
			<!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>

<%}%>
<%
String deleteTags = (String)request.getAttribute("deleteTags");
	
if(deleteTags != null && deleteTags.equals("yes")){
%>
<div class="msg"  align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.tags.createpage.deletedmessage",user1.getLocale())%></font>
			<!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<%}else{%>	
<body class="yui-skin-sam" onload="init()">

<html:form action="/tags.do?method=saveTags">
<%

	
if(tagExisted != null && tagExisted.equals("yes")){
%>
<div  align="left">
	<table border="0" width="100%">
	
	
	<tr>
			<td><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="red"><%=Constant.getResourceStringValue("admin.tags.existed",user1.getLocale())%></font>
			<!-- <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
		</tr>
		
	</table>
</div>
<br>
<%}%>


<div align="center" class="div">

	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.configutaion.tagName",user1.getLocale())%><font color="red">*</font></td>
			<td><html:text property="tagName" size="60" maxlength="200"/></td>
		</tr>
		
		<input type="hidden" name="tagType" value="APPLICANT"/>
		


		<tr>
			<td colspan="2"><%if (tform.getTagId()!=0){%>
		<br>		<br>

			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			<%}%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>
		
		</table>
		
	  </div>


</html:form>
	
</body>

		<%}%>